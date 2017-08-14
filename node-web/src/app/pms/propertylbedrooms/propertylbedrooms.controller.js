
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertylbedroomsController', pmsPropertylbedroomsController)

            .controller('PropertylbedroomsTableController', PropertylbedroomsTableController)

            .controller('pmsPropertylbedroomsDialogController', pmsPropertylbedroomsDialogController)
            .controller('pmsPropertylbedroomsViewDialogController', pmsPropertylbedroomsViewDialogController)
            .service("pmspropertylbedroomscommonobject", pmspropertylbedroomscommonobject);

    function pmsPropertylbedroomsController($scope, pmspropertylbedroomscommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertylbedroomscommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertylbedroomscommonobject.type = 1;
    }
    function init($scope, pmspropertylbedroomscommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertylbedroomscommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertylbedroomsDialogController($scope, $mdDialog, pmspropertylbedroomscommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertylbedrooms = pmspropertylbedroomscommonobject.pmspropertylbedrooms;
        $scope.pmspropertylbedroomscommonobject = pmspropertylbedroomscommonobject;
        $scope.pmspropertylbedrooms = pmspropertylbedroomscommonobject.pmspropertylbedrooms;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsPropertylbedroomsViewDialogController($scope, $mdDialog, pmspropertylbedroomscommonobject, sessionobject, propertylbedroomsobj) {
        $scope.propertylbedroomsobj = propertylbedroomsobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertylbedroomsTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertylbedroomscommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertylbedroomscommonobject = pmspropertylbedroomscommonobject;
        var vm = this;
        pmspropertylbedroomscommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertylbedrooms = {
        };
        $scope.pmspropertylbedrooms = vm.pmspropertylbedrooms;
        $scope.pmspropertylbedrooms.selectlistbox = [];
        $scope.pmspropertylbedrooms.selectlistbox.propertylbedroomstypeid = true;
        $scope.pmspropertylbedrooms.selectlistbox.propertylbedroomslocalityid = true;
        $scope.pmspropertylbedrooms.selectamountform = 1000;
        $scope.pmspropertylbedrooms.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertylbedroomsid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            propertylbedroomsid: 'MENU.propertylbedrooms.GRID.propertylbedroomsid',
            name: 'MENU.propertylbedrooms.GRID.name',
            active: 'MENU.propertylbedrooms.GRID.active',
            createddate: 'MENU.propertylbedrooms.GRID.createddate',
            modifyddate: 'MENU.propertylbedrooms.GRID.modifyddate',
            userid: 'MENU.propertylbedrooms.GRID.userid',
            recordorder: 'MENU.propertylbedrooms.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertylbedroomscommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "propertylbedrooms", pmspropertylbedroomscommonobject, formData);
        }
        function upload($files) {
            pmspropertylbedroomscommonobject.files = $files;
        }
        vm.object = pmspropertylbedroomscommonobject;



        sessionobject.activate(vm, $scope, pmspropertylbedroomscommonobject);

    }
    function pmspropertylbedroomscommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertylbedroomscommonobject = this;
        pmspropertylbedroomscommonobject.role = 'Propertylbedrooms';
        pmspropertylbedroomscommonobject.createObject = function () {
            return  {
                propertylbedroomsid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };


 
        pmspropertylbedroomscommonobject.showPropertylbedrooms = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertylbedroomscommonobject.createObject();
            obj1.propertylbedroomsid = obj.propertylbedroomsid;

            $mdDialog.show({
                templateUrl: 'app/pms/propertylbedrooms/PropertylbedroomsView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertylbedroomsViewDialogController',
                locals: {
                    propertylbedroomsobj: obj
                },
            }).then(function () {
                // pmspropertylbedroomscommonobject.save(ev, id);
            });
        }


        pmspropertylbedroomscommonobject.showDiloag = function (ev, id) {
            //    pmspropertylbedroomscommonobject.pmspropertylbedrooms.createddate = $filter('date')(pmspropertylbedroomscommonobject.pmspropertylbedrooms.createddate, "medium");//"{{pmspropertylbedroomscommonobject.pmspropertylbedrooms.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertylbedrooms/PropertylbedroomsAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertylbedroomsDialogController'
            }).then(function (answer) {
                pmspropertylbedroomscommonobject.save(ev, id);
            });
        }

        pmspropertylbedroomscommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmspropertylbedroomscommonobject, "Propertylbedrooms", pmspropertylbedroomscommonobject.pmspropertylbedrooms);

            });
        };

        pmspropertylbedroomscommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmspropertylbedroomscommonobject, $mdDialog, triLoaderService, "propertylbedrooms", "tabletitle");
        };
        pmspropertylbedroomscommonobject.editTodo = function (ev) {
            if (pmspropertylbedroomscommonobject.tableobject.selected.length >= 1) {
                pmspropertylbedroomscommonobject.addTodo(ev, pmspropertylbedroomscommonobject.tableobject.selected[pmspropertylbedroomscommonobject.tableobject.selected.length - 1].propertylbedroomsid);
            }
        };
        pmspropertylbedroomscommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmspropertylbedroomscommonobject, "propertylbedrooms", "pmspropertylbedrooms");
        };
    }
})();
