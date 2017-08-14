
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsBudgetrentController', pmsBudgetrentController)
  
            .controller('BudgetrentTableController', BudgetrentTableController)
 
            .controller('pmsBudgetrentDialogController', pmsBudgetrentDialogController)
            .controller('pmsBudgetrentViewDialogController', pmsBudgetrentViewDialogController)
            .service("pmsbudgetrentcommonobject", pmsbudgetrentcommonobject);
   
    function pmsBudgetrentController($scope, pmsbudgetrentcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsbudgetrentcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsbudgetrentcommonobject.type = 1;
    }
    function init($scope, pmsbudgetrentcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsbudgetrentcommonobject.addTodo(ev, 0);
        }
    }
    function pmsBudgetrentDialogController($scope, $mdDialog, pmsbudgetrentcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsbudgetrent = pmsbudgetrentcommonobject.pmsbudgetrent;
        $scope.pmsbudgetrentcommonobject = pmsbudgetrentcommonobject;
        $scope.pmsbudgetrent = pmsbudgetrentcommonobject.pmsbudgetrent;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsBudgetrentViewDialogController($scope, $mdDialog, pmsbudgetrentcommonobject, sessionobject, budgetrentobj) {
        $scope.budgetrentobj = budgetrentobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function BudgetrentTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsbudgetrentcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsbudgetrentcommonobject = pmsbudgetrentcommonobject;
        var vm = this;
        pmsbudgetrentcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsbudgetrent = {
        };
        $scope.pmsbudgetrent = vm.pmsbudgetrent;
        $scope.pmsbudgetrent.selectlistbox = [];
        $scope.pmsbudgetrent.selectlistbox.budgetrenttypeid = true;
        $scope.pmsbudgetrent.selectlistbox.budgetrentlocalityid = true;
        $scope.pmsbudgetrent.selectamountform = 1000;
        $scope.pmsbudgetrent.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-budgetrentid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               budgetrentid: 'MENU.budgetrent.GRID.budgetrentid',
name: 'MENU.budgetrent.GRID.name',
amount: 'MENU.budgetrent.GRID.amount',
active: 'MENU.budgetrent.GRID.active',
createddate: 'MENU.budgetrent.GRID.createddate',
modifyddate: 'MENU.budgetrent.GRID.modifyddate',
userid: 'MENU.budgetrent.GRID.userid',
recordorder: 'MENU.budgetrent.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsbudgetrentcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "budgetrent", pmsbudgetrentcommonobject, formData);
        }
        function upload($files) {
            pmsbudgetrentcommonobject.files = $files;
        }
        vm.object = pmsbudgetrentcommonobject;
 
        
                sessionobject.activate(  vm, $scope, pmsbudgetrentcommonobject);
                //    vm.fillCombo('budgetrenttypeid', vm);
                //  vm.fillCombo('budgetrentlocalityid', vm);
            
    }
    function pmsbudgetrentcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsbudgetrentcommonobject = this;
        pmsbudgetrentcommonobject.role = 'Budgetrent';
        pmsbudgetrentcommonobject.createObject = function () {
            return  {
                budgetrentid: 0,
name: '',
amount: 0,
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
        
        
        pmsbudgetrentcommonobject.showBudgetrent = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsbudgetrentcommonobject.createObject();
            obj1.budgetrentid = obj.budgetrentid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/budgetrent/BudgetrentView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsBudgetrentViewDialogController',
                locals: {
                    budgetrentobj: obj
                },
            }).then(function () {
                // pmsbudgetrentcommonobject.save(ev, id);
            });
        }
      
        
        pmsbudgetrentcommonobject.showDiloag = function (ev, id) {
            //    pmsbudgetrentcommonobject.pmsbudgetrent.createddate = $filter('date')(pmsbudgetrentcommonobject.pmsbudgetrent.createddate, "medium");//"{{pmsbudgetrentcommonobject.pmsbudgetrent.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/budgetrent/BudgetrentAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsBudgetrentDialogController'
            }).then(function (answer) {
                pmsbudgetrentcommonobject.save(ev, id);
            });
        }
        
      pmsbudgetrentcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmsbudgetrentcommonobject, "Budgetrent", pmsbudgetrentcommonobject.pmsbudgetrent);
 
            });
        };
      
        pmsbudgetrentcommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmsbudgetrentcommonobject, $mdDialog, triLoaderService,  "budgetrent", "tabletitle");       
          };
        pmsbudgetrentcommonobject.editTodo = function (ev) {
            if (pmsbudgetrentcommonobject.tableobject.selected.length >= 1) {
                pmsbudgetrentcommonobject.addTodo(ev, pmsbudgetrentcommonobject.tableobject.selected[pmsbudgetrentcommonobject.tableobject.selected.length - 1].budgetrentid);
            }
        };
        pmsbudgetrentcommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmsbudgetrentcommonobject,"budgetrent","pmsbudgetrent");
        };
    }
})();
