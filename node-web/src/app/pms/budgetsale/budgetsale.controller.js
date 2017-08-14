
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsBudgetsaleController', pmsBudgetsaleController)
  
            .controller('BudgetsaleTableController', BudgetsaleTableController)
 
            .controller('pmsBudgetsaleDialogController', pmsBudgetsaleDialogController)
            .controller('pmsBudgetsaleViewDialogController', pmsBudgetsaleViewDialogController)
            .service("pmsbudgetsalecommonobject", pmsbudgetsalecommonobject);
   
    function pmsBudgetsaleController($scope, pmsbudgetsalecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsbudgetsalecommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsbudgetsalecommonobject.type = 1;
    }
    function init($scope, pmsbudgetsalecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsbudgetsalecommonobject.addTodo(ev, 0);
        }
    }
    function pmsBudgetsaleDialogController($scope, $mdDialog, pmsbudgetsalecommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsbudgetsale = pmsbudgetsalecommonobject.pmsbudgetsale;
        $scope.pmsbudgetsalecommonobject = pmsbudgetsalecommonobject;
        $scope.pmsbudgetsale = pmsbudgetsalecommonobject.pmsbudgetsale;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsBudgetsaleViewDialogController($scope, $mdDialog, pmsbudgetsalecommonobject, sessionobject, budgetsaleobj) {
        $scope.budgetsaleobj = budgetsaleobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function BudgetsaleTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsbudgetsalecommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsbudgetsalecommonobject = pmsbudgetsalecommonobject;
        var vm = this;
        pmsbudgetsalecommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsbudgetsale = {
        };
        $scope.pmsbudgetsale = vm.pmsbudgetsale;
        $scope.pmsbudgetsale.selectlistbox = [];
        $scope.pmsbudgetsale.selectlistbox.budgetsaletypeid = true;
        $scope.pmsbudgetsale.selectlistbox.budgetsalelocalityid = true;
        $scope.pmsbudgetsale.selectamountform = 1000;
        $scope.pmsbudgetsale.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-budgetsaleid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               budgetsaleid: 'MENU.budgetsale.GRID.budgetsaleid',
name: 'MENU.budgetsale.GRID.name',
amount: 'MENU.budgetsale.GRID.amount',
active: 'MENU.budgetsale.GRID.active',
createddate: 'MENU.budgetsale.GRID.createddate',
modifyddate: 'MENU.budgetsale.GRID.modifyddate',
userid: 'MENU.budgetsale.GRID.userid',
recordorder: 'MENU.budgetsale.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsbudgetsalecommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "budgetsale", pmsbudgetsalecommonobject, formData);
        }
        function upload($files) {
            pmsbudgetsalecommonobject.files = $files;
        }
        vm.object = pmsbudgetsalecommonobject;
 
        
   
       
                sessionobject.activate(  vm, $scope, pmsbudgetsalecommonobject);
               
    }
    function pmsbudgetsalecommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsbudgetsalecommonobject = this;
        pmsbudgetsalecommonobject.role = 'Budgetsale';
        pmsbudgetsalecommonobject.createObject = function () {
            return  {
                budgetsaleid: 0,
name: '',
amount: 0,
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
       
        
        
        pmsbudgetsalecommonobject.showBudgetsale = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsbudgetsalecommonobject.createObject();
            obj1.budgetsaleid = obj.budgetsaleid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/budgetsale/BudgetsaleView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsBudgetsaleViewDialogController',
                locals: {
                    budgetsaleobj: obj
                },
            }).then(function () {
                // pmsbudgetsalecommonobject.save(ev, id);
            });
        }
      
        
        pmsbudgetsalecommonobject.showDiloag = function (ev, id) {
            //    pmsbudgetsalecommonobject.pmsbudgetsale.createddate = $filter('date')(pmsbudgetsalecommonobject.pmsbudgetsale.createddate, "medium");//"{{pmsbudgetsalecommonobject.pmsbudgetsale.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/budgetsale/BudgetsaleAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsBudgetsaleDialogController'
            }).then(function (answer) {
                pmsbudgetsalecommonobject.save(ev, id);
            });
        }
        
      pmsbudgetsalecommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmsbudgetsalecommonobject, "Budgetsale", pmsbudgetsalecommonobject.pmsbudgetsale);
 
            });
        };
      
        pmsbudgetsalecommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmsbudgetsalecommonobject, $mdDialog, triLoaderService,  "budgetsale", "tabletitle");       
          };
        pmsbudgetsalecommonobject.editTodo = function (ev) {
            if (pmsbudgetsalecommonobject.tableobject.selected.length >= 1) {
                pmsbudgetsalecommonobject.addTodo(ev, pmsbudgetsalecommonobject.tableobject.selected[pmsbudgetsalecommonobject.tableobject.selected.length - 1].budgetsaleid);
            }
        };
        pmsbudgetsalecommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmsbudgetsalecommonobject,"budgetsale","pmsbudgetsale");
        };
    }
})();
