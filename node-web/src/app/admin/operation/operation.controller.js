
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.admin')
            .controller('adminOperationController', adminOperationController)

            .controller('OperationTableController', OperationTableController)

            .controller('adminOperationDialogController', adminOperationDialogController)
            .controller('adminOperationViewDialogController', adminOperationViewDialogController)
            .service("adminoperationcommonobject", adminoperationcommonobject);

    function adminOperationController($scope, adminoperationcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, adminoperationcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        adminoperationcommonobject.type = 1;
    }
    function init($scope, adminoperationcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/admin/flight");
            adminoperationcommonobject.addTodo(ev, 0);
        }
    }
    function adminOperationDialogController($scope, $mdDialog, adminoperationcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.adminoperation = adminoperationcommonobject.adminoperation;
        $scope.adminoperationcommonobject = adminoperationcommonobject;
        $scope.adminoperation = adminoperationcommonobject.adminoperation;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function adminOperationViewDialogController($scope, $mdDialog, adminoperationcommonobject, sessionobject, operationobj) {
        $scope.operationobj = operationobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function OperationTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, adminoperationcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.adminoperationcommonobject = adminoperationcommonobject;
        var vm = this;
        adminoperationcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.adminoperation = {
        };
        $scope.adminoperation = vm.adminoperation;
        $scope.adminoperation.selectlistbox = [];
        $scope.adminoperation.selectlistbox.operationtypeid = true;
        $scope.adminoperation.selectlistbox.operationlocalityid = true;
        $scope.adminoperation.selectamountform = 1000;
        $scope.adminoperation.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '18',
            order: 'name',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            operationid: 'adminmenu.operation.GRID.operationid',
            name: 'adminmenu.operation.GRID.name',
            funname: 'adminmenu.operation.GRID.funname',
            url: 'adminmenu.operation.GRID.url',
            active: 'adminmenu.operation.GRID.active',
            createddate: 'adminmenu.operation.GRID.createddate',
            modifyddate: 'adminmenu.operation.GRID.modifyddate',
            userid: 'adminmenu.operation.GRID.userid',
            recordorder: 'adminmenu.operation.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;


        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(adminoperationcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "operation", adminoperationcommonobject, formData);
        }
        function upload($files) {
            adminoperationcommonobject.files = $files;
        }
        vm.object = adminoperationcommonobject;
        sessionobject.activate(vm, $scope, adminoperationcommonobject);
    }
    function adminoperationcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var adminoperationcommonobject = this;
        adminoperationcommonobject.role = 'Operation';
        adminoperationcommonobject.createObject = function () {
            return  {
                operationid: 0,
                name: '',
                funname: '',
                url: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };

 

        adminoperationcommonobject.showOperation = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = adminoperationcommonobject.createObject();
            obj1.operationid = obj.operationid;

            $mdDialog.show({
                templateUrl: 'app/admin/operation/OperationView.tmpl.html',
                targetEvent: ev,
                controller: 'adminOperationViewDialogController',
                locals: {
                    operationobj: obj
                },
            }).then(function () {
                // adminoperationcommonobject.save(ev, id);
            });
        }


        adminoperationcommonobject.showDiloag = function (ev, id) {
            //    adminoperationcommonobject.adminoperation.createddate = $filter('date')(adminoperationcommonobject.adminoperation.createddate, "medium");//"{{adminoperationcommonobject.adminoperation.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/admin/operation/OperationAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'adminOperationDialogController'
            }).then(function (answer) {
                adminoperationcommonobject.save(ev, id);
            });
        }

        adminoperationcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, adminoperationcommonobject, "Operation", adminoperationcommonobject.adminoperation);

            });
        };

        adminoperationcommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, adminoperationcommonobject, $mdDialog, triLoaderService, "operation", "tabletitle");
        };
        adminoperationcommonobject.editTodo = function (ev) {
            if (adminoperationcommonobject.tableobject.selected.length >= 1) {
                adminoperationcommonobject.addTodo(ev, adminoperationcommonobject.tableobject.selected[adminoperationcommonobject.tableobject.selected.length - 1].operationid);
            }
        };
        adminoperationcommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, adminoperationcommonobject, "operation", "adminoperation");
        };
    }
})();
