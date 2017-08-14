
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsFlatamenitiesController', pmsFlatamenitiesController)

            .controller('FlatamenitiesTableController', FlatamenitiesTableController)

            .controller('pmsFlatamenitiesDialogController', pmsFlatamenitiesDialogController)
            .controller('pmsFlatamenitiesViewDialogController', pmsFlatamenitiesViewDialogController)
            .service("pmsflatamenitiescommonobject", pmsflatamenitiescommonobject);

    function pmsFlatamenitiesController($scope, pmsflatamenitiescommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsflatamenitiescommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsflatamenitiescommonobject.type = 1;
    }
    function init($scope, pmsflatamenitiescommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsflatamenitiescommonobject.addTodo(ev, 0);
        }
    }
    function pmsFlatamenitiesDialogController($scope, $mdDialog, pmsflatamenitiescommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsflatamenities = pmsflatamenitiescommonobject.pmsflatamenities;
        $scope.pmsflatamenitiescommonobject = pmsflatamenitiescommonobject;
        $scope.pmsflatamenities = pmsflatamenitiescommonobject.pmsflatamenities;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsFlatamenitiesViewDialogController($scope, $mdDialog, pmsflatamenitiescommonobject, sessionobject, flatamenitiesobj) {
        $scope.flatamenitiesobj = flatamenitiesobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function FlatamenitiesTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsflatamenitiescommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsflatamenitiescommonobject = pmsflatamenitiescommonobject;
        var vm = this;
        pmsflatamenitiescommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsflatamenities = {
        };
        $scope.pmsflatamenities = vm.pmsflatamenities;
        $scope.pmsflatamenities.selectlistbox = [];
        $scope.pmsflatamenities.selectlistbox.flatamenitiestypeid = true;
        $scope.pmsflatamenities.selectlistbox.flatamenitieslocalityid = true;
        $scope.pmsflatamenities.selectamountform = 1000;
        $scope.pmsflatamenities.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-flatamenitiesid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            flatamenitiesid: 'MENU.flatamenities.GRID.flatamenitiesid',
            name: 'MENU.flatamenities.GRID.name',
            active: 'MENU.flatamenities.GRID.active',
            createddate: 'MENU.flatamenities.GRID.createddate',
            modifyddate: 'MENU.flatamenities.GRID.modifyddate',
            userid: 'MENU.flatamenities.GRID.userid',
            recordorder: 'MENU.flatamenities.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsflatamenitiescommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "flatamenities", pmsflatamenitiescommonobject, formData);
        }
        function upload($files) {
            pmsflatamenitiescommonobject.files = $files;
        }
        vm.object = pmsflatamenitiescommonobject;

            sessionobject.activate(vm, $scope, pmsflatamenitiescommonobject);

    }
    function pmsflatamenitiescommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsflatamenitiescommonobject = this;
        pmsflatamenitiescommonobject.role = 'Flatamenities';
        pmsflatamenitiescommonobject.createObject = function () {
            return  {
                flatamenitiesid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };



        pmsflatamenitiescommonobject.showFlatamenities = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsflatamenitiescommonobject.createObject();
            obj1.flatamenitiesid = obj.flatamenitiesid;

            $mdDialog.show({
                templateUrl: 'app/pms/flatamenities/FlatamenitiesView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsFlatamenitiesViewDialogController',
                locals: {
                    flatamenitiesobj: obj
                },
            }).then(function () {
                // pmsflatamenitiescommonobject.save(ev, id);
            });
        }


        pmsflatamenitiescommonobject.showDiloag = function (ev, id) {
            //    pmsflatamenitiescommonobject.pmsflatamenities.createddate = $filter('date')(pmsflatamenitiescommonobject.pmsflatamenities.createddate, "medium");//"{{pmsflatamenitiescommonobject.pmsflatamenities.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/flatamenities/FlatamenitiesAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsFlatamenitiesDialogController'
            }).then(function (answer) {
                pmsflatamenitiescommonobject.save(ev, id);
            });
        }

        pmsflatamenitiescommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmsflatamenitiescommonobject, "Flatamenities", pmsflatamenitiescommonobject.pmsflatamenities);

            });
        };

        pmsflatamenitiescommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmsflatamenitiescommonobject, $mdDialog, triLoaderService, "flatamenities", "tabletitle");
        };
        pmsflatamenitiescommonobject.editTodo = function (ev) {
            if (pmsflatamenitiescommonobject.tableobject.selected.length >= 1) {
                pmsflatamenitiescommonobject.addTodo(ev, pmsflatamenitiescommonobject.tableobject.selected[pmsflatamenitiescommonobject.tableobject.selected.length - 1].flatamenitiesid);
            }
        };
        pmsflatamenitiescommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmsflatamenitiescommonobject, "flatamenities", "pmsflatamenities");
        };
    }
})();
