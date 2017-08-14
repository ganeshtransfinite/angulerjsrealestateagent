
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.admin')
            .controller('adminRoleController', adminRoleController)

            .controller('RoleTableController', RoleTableController)

            .controller('adminRoleDialogController', adminRoleDialogController)
            .controller('adminRoleViewDialogController', adminRoleViewDialogController)
            .service("adminrolecommonobject", adminrolecommonobject);

    function adminRoleController($scope, adminrolecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, adminrolecommonobject, sessionobject, $localStorage, triMenu, triLayout);
        adminrolecommonobject.type = 1;
    }
    function init($scope, adminrolecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/admin/flight");
            adminrolecommonobject.addTodo(ev, 0);
        }
    }
    function adminRoleDialogController($scope, $mdDialog, adminrolecommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.adminrole = adminrolecommonobject.adminrole;
        $scope.adminrolecommonobject = adminrolecommonobject;
        $scope.adminrole = adminrolecommonobject.adminrole;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function adminRoleViewDialogController($scope, $mdDialog, adminrolecommonobject, sessionobject, roleobj) {
        $scope.roleobj = roleobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function RoleTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, adminrolecommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.adminrolecommonobject = adminrolecommonobject;
        var vm = this;
        adminrolecommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.adminrole = {
        };
        $scope.adminrole = vm.adminrole;
        $scope.adminrole.selectlistbox = [];
        $scope.adminrole.selectlistbox.roletypeid = true;
        $scope.adminrole.selectlistbox.rolelocalityid = true;
        $scope.adminrole.selectamountform = 1000;
        $scope.adminrole.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-roleid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            roleid: 'adminmenu.role.GRID.roleid',
            name: 'adminmenu.role.GRID.name',
            active: 'adminmenu.role.GRID.active',
            createddate: 'adminmenu.role.GRID.createddate',
            modifyddate: 'adminmenu.role.GRID.modifyddate',
            userid: 'adminmenu.role.GRID.userid',
            recordorder: 'adminmenu.role.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;


        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(adminrolecommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "role", adminrolecommonobject, formData);
        }
        function upload($files) {
            adminrolecommonobject.files = $files;
        }
        vm.object = adminrolecommonobject;
        adminrolecommonobject.vm = vm;
        sessionobject.activate(adminrolecommonobject.vm, $scope, adminrolecommonobject);
    }
    function adminrolecommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var adminrolecommonobject = this;
        adminrolecommonobject.role = 'Role';
        adminrolecommonobject.createObject = function () {
            return  {
                roleid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };


 
        adminrolecommonobject.showRole = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = adminrolecommonobject.createObject();
            obj1.roleid = obj.roleid;

            $mdDialog.show({
                templateUrl: 'app/admin/role/RoleView.tmpl.html',
                targetEvent: ev,
                controller: 'adminRoleViewDialogController',
                locals: {
                    roleobj: obj
                },
            }).then(function () {
                // adminrolecommonobject.save(ev, id);
            });
        }


        adminrolecommonobject.showDiloag = function (ev, id) {
            //    adminrolecommonobject.adminrole.createddate = $filter('date')(adminrolecommonobject.adminrole.createddate, "medium");//"{{adminrolecommonobject.adminrole.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/admin/role/RoleAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'adminRoleDialogController'
            }).then(function (answer) {
                adminrolecommonobject.save(ev, id);
            });
        }

        adminrolecommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, adminrolecommonobject, "Role", adminrolecommonobject.adminrole);

            });
        };

        adminrolecommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, adminrolecommonobject, $mdDialog, triLoaderService, "role", "tabletitle");
        };
        adminrolecommonobject.editTodo = function (ev) {
            if (adminrolecommonobject.tableobject.selected.length >= 1) {
                adminrolecommonobject.addTodo(ev, adminrolecommonobject.tableobject.selected[adminrolecommonobject.tableobject.selected.length - 1].roleid);
            }
        };
        adminrolecommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, adminrolecommonobject, "role", "adminrole");
        };
    }
})();
