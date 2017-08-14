
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsGenderController', pmsGenderController)

            .controller('GenderTableController', GenderTableController)

            .controller('pmsGenderDialogController', pmsGenderDialogController)
            .controller('pmsGenderViewDialogController', pmsGenderViewDialogController)
            .service("pmsgendercommonobject", pmsgendercommonobject);

    function pmsGenderController($scope, pmsgendercommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsgendercommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsgendercommonobject.type = 1;
    }
    function init($scope, pmsgendercommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsgendercommonobject.addTodo(ev, 0);
        }
    }
    function pmsGenderDialogController($scope, $mdDialog, pmsgendercommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsgender = pmsgendercommonobject.pmsgender;
        $scope.pmsgendercommonobject = pmsgendercommonobject;
        $scope.pmsgender = pmsgendercommonobject.pmsgender;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsGenderViewDialogController($scope, $mdDialog, pmsgendercommonobject, sessionobject, genderobj) {
        $scope.genderobj = genderobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function GenderTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsgendercommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsgendercommonobject = pmsgendercommonobject;
        var vm = this;
        pmsgendercommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsgender = {
        };
        $scope.pmsgender = vm.pmsgender;
        $scope.pmsgender.selectlistbox = [];
        $scope.pmsgender.selectlistbox.gendertypeid = true;
        $scope.pmsgender.selectlistbox.genderlocalityid = true;
        $scope.pmsgender.selectamountform = 1000;
        $scope.pmsgender.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-genderid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            genderid: 'MENU.gender.GRID.genderid',
            name: 'MENU.gender.GRID.name',
            active: 'MENU.gender.GRID.active',
            createddate: 'MENU.gender.GRID.createddate',
            modifyddate: 'MENU.gender.GRID.modifyddate',
            userid: 'MENU.gender.GRID.userid',
            recordorder: 'MENU.gender.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsgendercommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "gender", pmsgendercommonobject, formData);
        }
        function upload($files) {
            pmsgendercommonobject.files = $files;
        }
        vm.object = pmsgendercommonobject;

 
            sessionobject.activate(vm, $scope, pmsgendercommonobject);
           
    }
    function pmsgendercommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsgendercommonobject = this;
        pmsgendercommonobject.role = 'Gender';
        pmsgendercommonobject.createObject = function () {
            return  {
                genderid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };




        pmsgendercommonobject.initCombo = initCombo;
        function initCombo(requestPromise, httpPromise, flag) {




            //   if (requestPromise.length !== 0) {

//            } else {
//                sessionobject.activate(vm, $scope, pmsgendercommonobject);
//            }
        }

        pmsgendercommonobject.showGender = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsgendercommonobject.createObject();
            obj1.genderid = obj.genderid;

            $mdDialog.show({
                templateUrl: 'app/pms/gender/GenderView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsGenderViewDialogController',
                locals: {
                    genderobj: obj
                },
            }).then(function () {
                // pmsgendercommonobject.save(ev, id);
            });
        }


        pmsgendercommonobject.showDiloag = function (ev, id) {
            //    pmsgendercommonobject.pmsgender.createddate = $filter('date')(pmsgendercommonobject.pmsgender.createddate, "medium");//"{{pmsgendercommonobject.pmsgender.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/gender/GenderAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsGenderDialogController'
            }).then(function (answer) {
                pmsgendercommonobject.save(ev, id);
            });
        }

        pmsgendercommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmsgendercommonobject, "Gender", pmsgendercommonobject.pmsgender);

            });
        };

        pmsgendercommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmsgendercommonobject, $mdDialog, triLoaderService, "gender", "tabletitle");
        };
        pmsgendercommonobject.editTodo = function (ev) {
            if (pmsgendercommonobject.tableobject.selected.length >= 1) {
                pmsgendercommonobject.addTodo(ev, pmsgendercommonobject.tableobject.selected[pmsgendercommonobject.tableobject.selected.length - 1].genderid);
            }
        };
        pmsgendercommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmsgendercommonobject, "gender", "pmsgender");
        };
    }
})();
