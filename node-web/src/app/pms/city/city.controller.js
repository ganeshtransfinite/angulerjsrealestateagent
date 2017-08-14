
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsCityController', pmsCityController)

            .controller('CityTableController', CityTableController)

            .controller('pmsCityDialogController', pmsCityDialogController)
            .controller('pmsCityViewDialogController', pmsCityViewDialogController)
            .service("pmscitycommonobject", pmscitycommonobject);

    function pmsCityController($scope, pmscitycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmscitycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmscitycommonobject.type = 1;
    }
    function init($scope, pmscitycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmscitycommonobject.addTodo(ev, 0);
        }
    }
    function pmsCityDialogController($scope, $mdDialog, pmscitycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmscity = pmscitycommonobject.pmscity;
        $scope.pmscitycommonobject = pmscitycommonobject;
        $scope.pmscity = pmscitycommonobject.pmscity;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsCityViewDialogController($scope, $mdDialog, pmscitycommonobject, sessionobject, cityobj) {
        $scope.cityobj = cityobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function CityTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmscitycommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmscitycommonobject = pmscitycommonobject;
        var vm = this;
        pmscitycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmscity = {
        };
        $scope.pmscity = vm.pmscity;
        $scope.pmscity.selectlistbox = [];
        $scope.pmscity.selectlistbox.citytypeid = true;
        $scope.pmscity.selectlistbox.citylocalityid = true;
        $scope.pmscity.selectamountform = 1000;
        $scope.pmscity.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-cityid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            cityid: 'MENU.city.GRID.cityid',
            name: 'MENU.city.GRID.name',
            active: 'MENU.city.GRID.active',
            createddate: 'MENU.city.GRID.createddate',
            modifyddate: 'MENU.city.GRID.modifyddate',
            userid: 'MENU.city.GRID.userid',
            recordorder: 'MENU.city.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmscitycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "city", pmscitycommonobject, formData);
        }
        function upload($files) {
            pmscitycommonobject.files = $files;
        }
        vm.object = pmscitycommonobject;



        sessionobject.activate(vm, $scope, pmscitycommonobject);

    }
    function pmscitycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmscitycommonobject = this;
        pmscitycommonobject.role = 'City';
        pmscitycommonobject.createObject = function () {
            return  {
                cityid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };


 

        pmscitycommonobject.showCity = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmscitycommonobject.createObject();
            obj1.cityid = obj.cityid;

            $mdDialog.show({
                templateUrl: 'app/pms/city/CityView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCityViewDialogController',
                locals: {
                    cityobj: obj
                },
            }).then(function () {
                // pmscitycommonobject.save(ev, id);
            });
        }


        pmscitycommonobject.showDiloag = function (ev, id) {
            //    pmscitycommonobject.pmscity.createddate = $filter('date')(pmscitycommonobject.pmscity.createddate, "medium");//"{{pmscitycommonobject.pmscity.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/city/CityAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCityDialogController'
            }).then(function (answer) {
                pmscitycommonobject.save(ev, id);
            });
        }

        pmscitycommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmscitycommonobject, "City", pmscitycommonobject.pmscity);

            });
        };

        pmscitycommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmscitycommonobject, $mdDialog, triLoaderService, "city", "tabletitle");
        };
        pmscitycommonobject.editTodo = function (ev) {
            if (pmscitycommonobject.tableobject.selected.length >= 1) {
                pmscitycommonobject.addTodo(ev, pmscitycommonobject.tableobject.selected[pmscitycommonobject.tableobject.selected.length - 1].cityid);
            }
        };
        pmscitycommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmscitycommonobject, "city", "pmscity");
        };
    }
})();
