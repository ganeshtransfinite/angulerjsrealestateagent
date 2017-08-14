
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsSocietyamenitiesController', pmsSocietyamenitiesController)

            .controller('SocietyamenitiesTableController', SocietyamenitiesTableController)

            .controller('pmsSocietyamenitiesDialogController', pmsSocietyamenitiesDialogController)
            .controller('pmsSocietyamenitiesViewDialogController', pmsSocietyamenitiesViewDialogController)
            .service("pmssocietyamenitiescommonobject", pmssocietyamenitiescommonobject);

    function pmsSocietyamenitiesController($scope, pmssocietyamenitiescommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmssocietyamenitiescommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmssocietyamenitiescommonobject.type = 1;
    }
    function init($scope, pmssocietyamenitiescommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmssocietyamenitiescommonobject.addTodo(ev, 0);
        }
    }
    function pmsSocietyamenitiesDialogController($scope, $mdDialog, pmssocietyamenitiescommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmssocietyamenities = pmssocietyamenitiescommonobject.pmssocietyamenities;
        $scope.pmssocietyamenitiescommonobject = pmssocietyamenitiescommonobject;
        $scope.pmssocietyamenities = pmssocietyamenitiescommonobject.pmssocietyamenities;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsSocietyamenitiesViewDialogController($scope, $mdDialog, pmssocietyamenitiescommonobject, sessionobject, societyamenitiesobj) {
        $scope.societyamenitiesobj = societyamenitiesobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function SocietyamenitiesTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmssocietyamenitiescommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmssocietyamenitiescommonobject = pmssocietyamenitiescommonobject;
        var vm = this;
        pmssocietyamenitiescommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmssocietyamenities = {
        };
        $scope.pmssocietyamenities = vm.pmssocietyamenities;
        $scope.pmssocietyamenities.selectlistbox = [];
        $scope.pmssocietyamenities.selectlistbox.societyamenitiestypeid = true;
        $scope.pmssocietyamenities.selectlistbox.societyamenitieslocalityid = true;
        $scope.pmssocietyamenities.selectamountform = 1000;
        $scope.pmssocietyamenities.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-societyamenitiesid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            societyamenitiesid: 'MENU.societyamenities.GRID.societyamenitiesid',
            name: 'MENU.societyamenities.GRID.name',
            active: 'MENU.societyamenities.GRID.active',
            createddate: 'MENU.societyamenities.GRID.createddate',
            modifyddate: 'MENU.societyamenities.GRID.modifyddate',
            userid: 'MENU.societyamenities.GRID.userid',
            recordorder: 'MENU.societyamenities.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmssocietyamenitiescommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            vm.promise = sessionobject.getTable($scope, vm, "societyamenities", pmssocietyamenitiescommonobject, formData);
        }
        function upload($files) {
            pmssocietyamenitiescommonobject.files = $files;
        }
        vm.object = pmssocietyamenitiescommonobject;


        sessionobject.activate(vm, $scope, pmssocietyamenitiescommonobject);





 $scope.query = {
    order: 'name',
    limit: 10,
    page: 1
  };
  
  $scope.desserts = {
    "count": 9,
    "data": [
      {
        "name": "Frozen yogurt",
        "type": "Ice cream",
        "calories": { "value": 159.0 },
        "fat": { "value": 6.0 },
        "carbs": { "value": 24.0 },
        "protein": { "value": 4.0 },
        "sodium": { "value": 87.0 },
        "calcium": { "value": 14.0 },
        "iron": { "value": 1.0 }
      }, {
        "name": "Ice cream sandwich",
        "type": "Ice cream",
        "calories": { "value": 237.0 },
        "fat": { "value": 9.0 },
        "carbs": { "value": 37.0 },
        "protein": { "value": 4.3 },
        "sodium": { "value": 129.0 },
        "calcium": { "value": 8.0 },
        "iron": { "value": 1.0 }
      }, {
        "name": "Eclair",
        "type": "Pastry",
        "calories": { "value":  262.0 },
        "fat": { "value": 16.0 },
        "carbs": { "value": 24.0 },
        "protein": { "value":  6.0 },
        "sodium": { "value": 337.0 },
        "calcium": { "value":  6.0 },
        "iron": { "value": 7.0 }
      }, {
        "name": "Cupcake",
        "type": "Pastry",
        "calories": { "value":  305.0 },
        "fat": { "value": 3.7 },
        "carbs": { "value": 67.0 },
        "protein": { "value": 4.3 },
        "sodium": { "value": 413.0 },
        "calcium": { "value": 3.0 },
        "iron": { "value": 8.0 }
      }, {
        "name": "Jelly bean",
        "type": "Candy",
        "calories": { "value":  375.0 },
        "fat": { "value": 0.0 },
        "carbs": { "value": 94.0 },
        "protein": { "value": 0.0 },
        "sodium": { "value": 50.0 },
        "calcium": { "value": 0.0 },
        "iron": { "value": 0.0 }
      }, {
        "name": "Lollipop",
        "type": "Candy",
        "calories": { "value": 392.0 },
        "fat": { "value": 0.2 },
        "carbs": { "value": 98.0 },
        "protein": { "value": 0.0 },
        "sodium": { "value": 38.0 },
        "calcium": { "value": 0.0 },
        "iron": { "value": 2.0 }
      }, {
        "name": "Honeycomb",
        "type": "Other",
        "calories": { "value": 408.0 },
        "fat": { "value": 3.2 },
        "carbs": { "value": 87.0 },
        "protein": { "value": 6.5 },
        "sodium": { "value": 562.0 },
        "calcium": { "value": 0.0 },
        "iron": { "value": 45.0 }
      }, {
        "name": "Donut",
        "type": "Pastry",
        "calories": { "value": 452.0 },
        "fat": { "value": 25.0 },
        "carbs": { "value": 51.0 },
        "protein": { "value": 4.9 },
        "sodium": { "value": 326.0 },
        "calcium": { "value": 2.0 },
        "iron": { "value": 22.0 }
      }, {
        "name": "KitKat",
        "type": "Candy",
        "calories": { "value": 518.0 },
        "fat": { "value": 26.0 },
        "carbs": { "value": 65.0 },
        "protein": { "value": 7.0 },
        "sodium": { "value": 54.0 },
        "calcium": { "value": 12.0 },
        "iron": { "value": 6.0 }
      }
    ]
  };
  

    }
    function pmssocietyamenitiescommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmssocietyamenitiescommonobject = this;
        pmssocietyamenitiescommonobject.role = 'Societyamenities';
        pmssocietyamenitiescommonobject.createObject = function () {
            return  {
                societyamenitiesid: 0,
                name: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };
        };




        pmssocietyamenitiescommonobject.initCombo = initCombo;
        function initCombo(requestPromise, httpPromise, flag) {




            //   if (requestPromise.length !== 0) {

//            } else {
//                sessionobject.activate(vm, $scope, pmssocietyamenitiescommonobject);
//            }
        }

        pmssocietyamenitiescommonobject.showSocietyamenities = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmssocietyamenitiescommonobject.createObject();
            obj1.societyamenitiesid = obj.societyamenitiesid;

            $mdDialog.show({
                templateUrl: 'app/pms/societyamenities/SocietyamenitiesView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsSocietyamenitiesViewDialogController',
                locals: {
                    societyamenitiesobj: obj
                },
            }).then(function () {
                // pmssocietyamenitiescommonobject.save(ev, id);
            });
        }


        pmssocietyamenitiescommonobject.showDiloag = function (ev, id) {
            //    pmssocietyamenitiescommonobject.pmssocietyamenities.createddate = $filter('date')(pmssocietyamenitiescommonobject.pmssocietyamenities.createddate, "medium");//"{{pmssocietyamenitiescommonobject.pmssocietyamenities.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/societyamenities/SocietyamenitiesAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsSocietyamenitiesDialogController'
            }).then(function (answer) {
                pmssocietyamenitiescommonobject.save(ev, id);
            });
        }

        pmssocietyamenitiescommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmssocietyamenitiescommonobject, "Societyamenities", pmssocietyamenitiescommonobject.pmssocietyamenities);

            });
        };

        pmssocietyamenitiescommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmssocietyamenitiescommonobject, $mdDialog, triLoaderService, "societyamenities", "tabletitle");
        };
        pmssocietyamenitiescommonobject.editTodo = function (ev) {
            if (pmssocietyamenitiescommonobject.tableobject.selected.length >= 1) {
                pmssocietyamenitiescommonobject.addTodo(ev, pmssocietyamenitiescommonobject.tableobject.selected[pmssocietyamenitiescommonobject.tableobject.selected.length - 1].societyamenitiesid);
            }
        };
        pmssocietyamenitiescommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmssocietyamenitiescommonobject, "societyamenities", "pmssocietyamenities");
        };
    }
})();
