
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertytobeController', pmsPropertytobeController)

            .controller('PropertytobeTableController', PropertytobeTableController)

            .controller('pmsPropertytobeDialogController', pmsPropertytobeDialogController)
            .controller('pmsPropertytobeViewDialogController', pmsPropertytobeViewDialogController)
            .service("pmspropertytobecommonobject", pmspropertytobecommonobject);

    function pmsPropertytobeController($scope, pmspropertytobecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertytobecommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertytobecommonobject.type = 1;
    }
    function init($scope, pmspropertytobecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertytobecommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertytobeDialogController($scope, $mdDialog, pmspropertytobecommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertytobe = pmspropertytobecommonobject.pmspropertytobe;
        $scope.pmspropertytobecommonobject = pmspropertytobecommonobject;
        $scope.pmspropertytobe = pmspropertytobecommonobject.pmspropertytobe;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsPropertytobeViewDialogController($scope, $mdDialog, pmspropertytobecommonobject, sessionobject, propertytobeobj) {
        $scope.propertytobeobj = propertytobeobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertytobeTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertytobecommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertytobecommonobject = pmspropertytobecommonobject;
        var vm = this;
        pmspropertytobecommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertytobe = {
        };
        $scope.pmspropertytobe = vm.pmspropertytobe;
        $scope.pmspropertytobe.selectlistbox = [];
        $scope.pmspropertytobe.selectlistbox.propertytobetypeid = true;
        $scope.pmspropertytobe.selectlistbox.propertytobelocalityid = true;
        $scope.pmspropertytobe.selectamountform = 1000;
        $scope.pmspropertytobe.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertytobeid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            propertytobeid: 'MENU.propertytobe.GRID.propertytobeid',
            name: 'MENU.propertytobe.GRID.name',
            active: 'MENU.propertytobe.GRID.active',
            createddate: 'MENU.propertytobe.GRID.createddate',
            modifyddate: 'MENU.propertytobe.GRID.modifyddate',
            userid: 'MENU.propertytobe.GRID.userid',
            recordorder: 'MENU.propertytobe.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertytobecommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "propertytobe", pmspropertytobecommonobject, formData);
        }
        function upload($files) {
            pmspropertytobecommonobject.files = $files;
        }
        vm.object = pmspropertytobecommonobject;



        sessionobject.activate(vm, $scope, pmspropertytobecommonobject);

    }
    function pmspropertytobecommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertytobecommonobject = this;
        pmspropertytobecommonobject.role = 'Propertytobe';
        pmspropertytobecommonobject.createObject = function () {
            return  {
                propertytobeid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };




        

        pmspropertytobecommonobject.showPropertytobe = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertytobecommonobject.createObject();
            obj1.propertytobeid = obj.propertytobeid;

            $mdDialog.show({
                templateUrl: 'app/pms/propertytobe/PropertytobeView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertytobeViewDialogController',
                locals: {
                    propertytobeobj: obj
                },
            }).then(function () {
                // pmspropertytobecommonobject.save(ev, id);
            });
        }


        pmspropertytobecommonobject.showDiloag = function (ev, id) {
            //    pmspropertytobecommonobject.pmspropertytobe.createddate = $filter('date')(pmspropertytobecommonobject.pmspropertytobe.createddate, "medium");//"{{pmspropertytobecommonobject.pmspropertytobe.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertytobe/PropertytobeAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertytobeDialogController'
            }).then(function (answer) {
                pmspropertytobecommonobject.save(ev, id);
            });
        }

        pmspropertytobecommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmspropertytobecommonobject, "Propertytobe", pmspropertytobecommonobject.pmspropertytobe);

            });
        };

        pmspropertytobecommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmspropertytobecommonobject, $mdDialog, triLoaderService, "propertytobe", "tabletitle");
        };
        pmspropertytobecommonobject.editTodo = function (ev) {
            if (pmspropertytobecommonobject.tableobject.selected.length >= 1) {
                pmspropertytobecommonobject.addTodo(ev, pmspropertytobecommonobject.tableobject.selected[pmspropertytobecommonobject.tableobject.selected.length - 1].propertytobeid);
            }
        };
        pmspropertytobecommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmspropertytobecommonobject, "propertytobe", "pmspropertytobe");
        };
    }
})();
