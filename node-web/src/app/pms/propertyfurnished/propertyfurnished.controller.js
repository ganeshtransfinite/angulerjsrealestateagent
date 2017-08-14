
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertyfurnishedController', pmsPropertyfurnishedController)

            .controller('PropertyfurnishedTableController', PropertyfurnishedTableController)

            .controller('pmsPropertyfurnishedDialogController', pmsPropertyfurnishedDialogController)
            .controller('pmsPropertyfurnishedViewDialogController', pmsPropertyfurnishedViewDialogController)
            .service("pmspropertyfurnishedcommonobject", pmspropertyfurnishedcommonobject);

    function pmsPropertyfurnishedController($scope, pmspropertyfurnishedcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertyfurnishedcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertyfurnishedcommonobject.type = 1;
    }
    function init($scope, pmspropertyfurnishedcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertyfurnishedcommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertyfurnishedDialogController($scope, $mdDialog, pmspropertyfurnishedcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertyfurnished = pmspropertyfurnishedcommonobject.pmspropertyfurnished;
        $scope.pmspropertyfurnishedcommonobject = pmspropertyfurnishedcommonobject;
        $scope.pmspropertyfurnished = pmspropertyfurnishedcommonobject.pmspropertyfurnished;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsPropertyfurnishedViewDialogController($scope, $mdDialog, pmspropertyfurnishedcommonobject, sessionobject, propertyfurnishedobj) {
        $scope.propertyfurnishedobj = propertyfurnishedobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertyfurnishedTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertyfurnishedcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertyfurnishedcommonobject = pmspropertyfurnishedcommonobject;
        var vm = this;
        pmspropertyfurnishedcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertyfurnished = {
        };
        $scope.pmspropertyfurnished = vm.pmspropertyfurnished;
        $scope.pmspropertyfurnished.selectlistbox = [];
        $scope.pmspropertyfurnished.selectlistbox.propertyfurnishedtypeid = true;
        $scope.pmspropertyfurnished.selectlistbox.propertyfurnishedlocalityid = true;
        $scope.pmspropertyfurnished.selectamountform = 1000;
        $scope.pmspropertyfurnished.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertyfurnishedid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            propertyfurnishedid: 'MENU.propertyfurnished.GRID.propertyfurnishedid',
            name: 'MENU.propertyfurnished.GRID.name',
            active: 'MENU.propertyfurnished.GRID.active',
            createddate: 'MENU.propertyfurnished.GRID.createddate',
            modifyddate: 'MENU.propertyfurnished.GRID.modifyddate',
            userid: 'MENU.propertyfurnished.GRID.userid',
            recordorder: 'MENU.propertyfurnished.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertyfurnishedcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "propertyfurnished", pmspropertyfurnishedcommonobject, formData);
        }
        function upload($files) {
            pmspropertyfurnishedcommonobject.files = $files;
        }
        vm.object = pmspropertyfurnishedcommonobject;
 


        sessionobject.activate(vm, $scope, pmspropertyfurnishedcommonobject);

    }
    function pmspropertyfurnishedcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertyfurnishedcommonobject = this;
        pmspropertyfurnishedcommonobject.role = 'Propertyfurnished';
        pmspropertyfurnishedcommonobject.createObject = function () {
            return  {
                propertyfurnishedid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };




        pmspropertyfurnishedcommonobject.initCombo = initCombo;
        function initCombo(requestPromise, httpPromise, flag) {




            //   if (requestPromise.length !== 0) {

//            } else {
//                sessionobject.activate(vm, $scope, pmspropertyfurnishedcommonobject);
//            }
        }

        pmspropertyfurnishedcommonobject.showPropertyfurnished = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertyfurnishedcommonobject.createObject();
            obj1.propertyfurnishedid = obj.propertyfurnishedid;

            $mdDialog.show({
                templateUrl: 'app/pms/propertyfurnished/PropertyfurnishedView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyfurnishedViewDialogController',
                locals: {
                    propertyfurnishedobj: obj
                },
            }).then(function () {
                // pmspropertyfurnishedcommonobject.save(ev, id);
            });
        }


        pmspropertyfurnishedcommonobject.showDiloag = function (ev, id) {
            //    pmspropertyfurnishedcommonobject.pmspropertyfurnished.createddate = $filter('date')(pmspropertyfurnishedcommonobject.pmspropertyfurnished.createddate, "medium");//"{{pmspropertyfurnishedcommonobject.pmspropertyfurnished.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertyfurnished/PropertyfurnishedAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyfurnishedDialogController'
            }).then(function (answer) {
                pmspropertyfurnishedcommonobject.save(ev, id);
            });
        }

        pmspropertyfurnishedcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmspropertyfurnishedcommonobject, "Propertyfurnished", pmspropertyfurnishedcommonobject.pmspropertyfurnished);

            });
        };

        pmspropertyfurnishedcommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmspropertyfurnishedcommonobject, $mdDialog, triLoaderService, "propertyfurnished", "tabletitle");
        };
        pmspropertyfurnishedcommonobject.editTodo = function (ev) {
            if (pmspropertyfurnishedcommonobject.tableobject.selected.length >= 1) {
                pmspropertyfurnishedcommonobject.addTodo(ev, pmspropertyfurnishedcommonobject.tableobject.selected[pmspropertyfurnishedcommonobject.tableobject.selected.length - 1].propertyfurnishedid);
            }
        };
        pmspropertyfurnishedcommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmspropertyfurnishedcommonobject, "propertyfurnished", "pmspropertyfurnished");
        };
    }
})();
