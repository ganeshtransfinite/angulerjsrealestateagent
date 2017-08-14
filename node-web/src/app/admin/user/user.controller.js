
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.admin')
            .controller('adminUserController', adminUserController)
            .controller('adminUserLoadController', adminUserLoadController)

            .controller('UserTableController', UserTableController)
            .controller('UserLoadTableController', UserLoadTableController)

            .controller('adminUserDialogController', adminUserDialogController)
            .controller('adminUserViewDialogController', adminUserViewDialogController)
            .service("adminusercommonobject", adminusercommonobject);

    function adminUserController($scope, adminusercommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, adminusercommonobject, sessionobject, $localStorage, triMenu, triLayout);
        adminusercommonobject.type = 1;
    }

    function adminUserLoadController($scope, adminusercommonobject, sessionobject, $localStorage, triMenu, triLayout) {

    }
    function init($scope, adminusercommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/admin/flight");
            adminusercommonobject.addTodo(ev, 0);
        }
    }
    function adminUserDialogController($scope, $mdDialog, adminusercommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.adminuser = adminusercommonobject.adminuser;
        $scope.adminusercommonobject = adminusercommonobject;
        $scope.adminuser = adminusercommonobject.adminuser;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function adminUserViewDialogController($scope, $mdDialog, adminusercommonobject, sessionobject, userobj) {
        $scope.userobj = userobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    function UserLoadTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, $state, triMenu, triSettings) {
        var requestPromise = [];
        var httpPromise = null;
        var vm = this;
        var vm = this;
        vm.triSettings = triSettings;
        vm.isActive = true;
        triMenu.removeAllMenu();
        sessionobject.showmenu(triMenu);
        if ($localStorage.clientloginuserid.refresh) {
          //  $http(sessionobject.getHttpHeader("/dashgetproperty.json", "", "admin"));
           var httpPromise =
                    $http.get(sessionobject.getURL("/dashgetproperty.json"))
                    .success(function (response) {
                        $localStorage.clientloginuserid.desktop = response;
                    }).
                    error(function (response) {
                        sessionobject.showERROR(response);
                    });
            requestPromise.push(httpPromise);
            sessionobject.initComboCountALL("ALL", false, requestPromise);
            sessionobject.initComboALL(false, requestPromise);
            $localStorage.clientloginuserid.refresh = false;
        }
        $q.all(requestPromise).then(function (data) {
            vm.isActive = false;

            $state.go('triangular.dashboard-analytics');
        });

    }

    /* @ngInject */
    function UserTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, adminusercommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.adminusercommonobject = adminusercommonobject;
        var vm = this;
        adminusercommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.adminuser = {
        };
        $scope.adminuser = vm.adminuser;
        $scope.adminuser.selectlistbox = [];
        $scope.adminuser.selectlistbox.usertypeid = true;
        $scope.adminuser.selectlistbox.userlocalityid = true;
        $scope.adminuser.selectamountform = 1000;
        $scope.adminuser.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-userid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            userid: 'adminmenu.user.GRID.userid',
            email: 'adminmenu.user.GRID.email',
            password: 'adminmenu.user.GRID.password',
            confirmpassword: 'adminmenu.user.GRID.confirmpassword',
            roleid: 'adminmenu.user.GRID.roleid',
            active: 'adminmenu.user.GRID.active',
            createddate: 'adminmenu.user.GRID.createddate',
            modifyddate: 'adminmenu.user.GRID.modifyddate',
            fullname: 'adminmenu.user.GRID.fullname',
            recordorder: 'adminmenu.user.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;


        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(adminusercommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            if (vm.adminuser.selectlistbox.userroleid) {
                formData.filter += "roleid.roleid|" + angular.toJson(vm.adminuser.selectuserroleidtbl) + ";";
            }
            vm.promise = sessionobject.getTable($scope, vm, "user", adminusercommonobject, formData);
        }
        function upload($files) {
            adminusercommonobject.files = $files;
        }
        vm.object = adminusercommonobject;
        var requestPromise = [];
        var httpPromise = null;

        sessionobject.activate(vm, $scope, adminusercommonobject);


    }
    function adminusercommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var adminusercommonobject = this;
        adminusercommonobject.role = 'User';
        adminusercommonobject.createObject = function () {
            return  {
                userid: 0,
                email: '',
                password: '',
                confirmpassword: '',
                selectroleid: 0,
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                fullname: '',
                recordorder: 0
            };
        };






        adminusercommonobject.showUser = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = adminusercommonobject.createObject();
            obj1.userid = obj.userid;

            $mdDialog.show({
                templateUrl: 'app/admin/user/UserView.tmpl.html',
                targetEvent: ev,
                controller: 'adminUserViewDialogController',
                locals: {
                    userobj: obj
                },
            }).then(function () {
                // adminusercommonobject.save(ev, id);
            });
        }


        adminusercommonobject.showDiloag = function (ev, id) {
            //    adminusercommonobject.adminuser.createddate = $filter('date')(adminusercommonobject.adminuser.createddate, "medium");//"{{adminusercommonobject.adminuser.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/admin/user/UserAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'adminUserDialogController'
            }).then(function (answer) {
                adminusercommonobject.save(ev, id);
            });
        }

        adminusercommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, adminusercommonobject, "User", adminusercommonobject.adminuser);

            });
        };

        adminusercommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, adminusercommonobject, $mdDialog, triLoaderService, "user", "tabletitle");
        };
        adminusercommonobject.editTodo = function (ev) {
            if (adminusercommonobject.tableobject.selected.length >= 1) {
                adminusercommonobject.addTodo(ev, adminusercommonobject.tableobject.selected[adminusercommonobject.tableobject.selected.length - 1].userid);
            }
        };
        adminusercommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, adminusercommonobject, "user", "adminuser");
        };
    }
})();
