
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.admin')
            .controller('adminRolepermissionController', adminRolepermissionController)

            .controller('RolepermissionTableController', RolepermissionTableController)

            .controller('adminRolepermissionDialogController', adminRolepermissionDialogController)
            .controller('adminRolepermissionViewDialogController', adminRolepermissionViewDialogController)
            .service("adminrolepermissioncommonobject", adminrolepermissioncommonobject);

    function adminRolepermissionController($scope, adminrolepermissioncommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, adminrolepermissioncommonobject, sessionobject, $localStorage, triMenu, triLayout);
        adminrolepermissioncommonobject.type = 1;
    }
    function init($scope, adminrolepermissioncommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/admin/flight");
            adminrolepermissioncommonobject.addTodo(ev, 0);
        }
    }
    function adminRolepermissionDialogController($scope, $mdDialog, adminrolepermissioncommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.adminrolepermission = adminrolepermissioncommonobject.adminrolepermission;
        $scope.adminrolepermissioncommonobject = adminrolepermissioncommonobject;
        $scope.adminrolepermission = adminrolepermissioncommonobject.adminrolepermission;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function adminRolepermissionViewDialogController($scope, $mdDialog, adminrolepermissioncommonobject, sessionobject, rolepermissionobj) {
        $scope.rolepermissionobj = rolepermissionobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function RolepermissionTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, adminrolepermissioncommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.adminrolepermissioncommonobject = adminrolepermissioncommonobject;
        var vm = this;
        adminrolepermissioncommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.adminrolepermission = {
        };
        $scope.adminrolepermission = vm.adminrolepermission;
        $scope.adminrolepermission.selectlistbox = [];
        $scope.adminrolepermission.selectlistbox.rolepermissiontypeid = true;
        $scope.adminrolepermission.selectlistbox.rolepermissionlocalityid = true;
        $scope.adminrolepermission.selectamountform = 1000;
        $scope.adminrolepermission.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '18',
            order: '-roleid.name',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            rolepermissionid: 'adminmenu.rolepermission.GRID.rolepermissionid',
            pfunction: 'adminmenu.rolepermission.GRID.pfunction',
            roleid: 'adminmenu.rolepermission.GRID.roleid',
            operationid: 'adminmenu.rolepermission.GRID.operationid',
            active: 'adminmenu.rolepermission.GRID.active',
            createddate: 'adminmenu.rolepermission.GRID.createddate',
            modifyddate: 'adminmenu.rolepermission.GRID.modifyddate',
            userid: 'adminmenu.rolepermission.GRID.userid',
            recordorder: 'adminmenu.rolepermission.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(adminrolepermissioncommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            if (vm.adminrolepermission.selectlistbox.rolepermissionroleid) {
                formData.filter += "roleid.roleid|" + angular.toJson(vm.adminrolepermission.selectrolepermissionroleidtbl) + ";";
            }
            vm.promise = sessionobject.getTable($scope, vm, "rolepermission", adminrolepermissioncommonobject, formData);
        }
        function upload($files) {
            adminrolepermissioncommonobject.files = $files;
        }
        vm.object = adminrolepermissioncommonobject;
       
            sessionobject.activate(vm, $scope, adminrolepermissioncommonobject);
           
        
         vm.getRolepermissionFormColUpdate = getRolepermissionFormColUpdate;
            function getRolepermissionFormColUpdate(id, val, col, obj) {
            if (obj.operationid.funname === "Menu") {
                for (var i = 0; i < vm.contents.items.length; i++) {
                    if (obj.operationid.name === vm.contents.items[i].operationid.name &&
                            obj.roleid.name === vm.contents.items[i].roleid.name
                            &&  vm.contents.items[i].operationid.funname !== "Combo") { 
                         vm.contents.items[i].pfunname=val;
                        $http.get(sessionobject.getURL("/getRolepermissionFormColUpdate.json", "") + "&id=" +   vm.contents.items[i].rolepermissionid + "&val=" + val + "&col=" + col)
                                .success(function (response) {
getTable();
                                }).
                                error(function (response) {
                                    sessionobject.showERROR(response);

                                });
                    }

                }
            } else {
                $http.get(sessionobject.getURL("/getRolepermissionFormColUpdate.json", "") + "&id=" + id + "&val=" + val + "&col=" + col)
                        .success(function (response) {

                        }).
                        error(function (response) {
                            sessionobject.showERROR(response);

                        });
            }
        }

     
    }
    function adminrolepermissioncommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var adminrolepermissioncommonobject = this;
        adminrolepermissioncommonobject.role = 'Rolepermission';
        adminrolepermissioncommonobject.createObject = function () {
            return  {
                rolepermissionid: 0,
                pfunction: false,
                selectroleid: 0,
                selectoperationid: 0,
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };



 

        adminrolepermissioncommonobject.showRolepermission = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = adminrolepermissioncommonobject.createObject();
            obj1.rolepermissionid = obj.rolepermissionid;

            $mdDialog.show({
                templateUrl: 'app/admin/rolepermission/RolepermissionView.tmpl.html',
                targetEvent: ev,
                controller: 'adminRolepermissionViewDialogController',
                locals: {
                    rolepermissionobj: obj
                },
            }).then(function () {
                // adminrolepermissioncommonobject.save(ev, id);
            });
        }


        adminrolepermissioncommonobject.showDiloag = function (ev, id) {
            //    adminrolepermissioncommonobject.adminrolepermission.createddate = $filter('date')(adminrolepermissioncommonobject.adminrolepermission.createddate, "medium");//"{{adminrolepermissioncommonobject.adminrolepermission.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/admin/rolepermission/RolepermissionAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'adminRolepermissionDialogController'
            }).then(function (answer) {
                adminrolepermissioncommonobject.save(ev, id);
            });
        }

        adminrolepermissioncommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, adminrolepermissioncommonobject, "Rolepermission", adminrolepermissioncommonobject.adminrolepermission);

            });
        };

        adminrolepermissioncommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, adminrolepermissioncommonobject, $mdDialog, triLoaderService, "rolepermission", "tabletitle");
        };
        adminrolepermissioncommonobject.editTodo = function (ev) {
            if (adminrolepermissioncommonobject.tableobject.selected.length >= 1) {
                adminrolepermissioncommonobject.addTodo(ev, adminrolepermissioncommonobject.tableobject.selected[adminrolepermissioncommonobject.tableobject.selected.length - 1].rolepermissionid);
            }
        };
        adminrolepermissioncommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, adminrolepermissioncommonobject, "rolepermission", "adminrolepermission");
        };
    }
})();
