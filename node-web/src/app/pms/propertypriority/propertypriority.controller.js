
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertypriorityController', pmsPropertypriorityController)
  
            .controller('PropertypriorityTableController', PropertypriorityTableController)
 
            .controller('pmsPropertypriorityDialogController', pmsPropertypriorityDialogController)
            .controller('pmsPropertypriorityViewDialogController', pmsPropertypriorityViewDialogController)
            .service("pmspropertyprioritycommonobject", pmspropertyprioritycommonobject);
   
    function pmsPropertypriorityController($scope, pmspropertyprioritycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertyprioritycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertyprioritycommonobject.type = 1;
    }
    function init($scope, pmspropertyprioritycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertyprioritycommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertypriorityDialogController($scope, $mdDialog, pmspropertyprioritycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertypriority = pmspropertyprioritycommonobject.pmspropertypriority;
        $scope.pmspropertyprioritycommonobject = pmspropertyprioritycommonobject;
        $scope.pmspropertypriority = pmspropertyprioritycommonobject.pmspropertypriority;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsPropertypriorityViewDialogController($scope, $mdDialog, pmspropertyprioritycommonobject, sessionobject, propertypriorityobj) {
        $scope.propertypriorityobj = propertypriorityobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertypriorityTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertyprioritycommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertyprioritycommonobject = pmspropertyprioritycommonobject;
        var vm = this;
        pmspropertyprioritycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertypriority = {
        };
        $scope.pmspropertypriority = vm.pmspropertypriority;
        $scope.pmspropertypriority.selectlistbox = [];
        $scope.pmspropertypriority.selectlistbox.propertyprioritytypeid = true;
        $scope.pmspropertypriority.selectlistbox.propertyprioritylocalityid = true;
        $scope.pmspropertypriority.selectamountform = 1000;
        $scope.pmspropertypriority.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertypriorityid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               propertypriorityid: 'MENU.propertypriority.GRID.propertypriorityid',
name: 'MENU.propertypriority.GRID.name',
active: 'MENU.propertypriority.GRID.active',
createddate: 'MENU.propertypriority.GRID.createddate',
modifyddate: 'MENU.propertypriority.GRID.modifyddate',
userid: 'MENU.propertypriority.GRID.userid',
recordorder: 'MENU.propertypriority.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertyprioritycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "propertypriority", pmspropertyprioritycommonobject, formData);
        }
        function upload($files) {
            pmspropertyprioritycommonobject.files = $files;
        }
        vm.object = pmspropertyprioritycommonobject;
 
         
                sessionobject.activate(  vm, $scope, pmspropertyprioritycommonobject);
                 
    }
    function pmspropertyprioritycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertyprioritycommonobject = this;
        pmspropertyprioritycommonobject.role = 'Propertypriority';
        pmspropertyprioritycommonobject.createObject = function () {
            return  {
                propertypriorityid: 0,
name: '',
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
       
       
 pmspropertyprioritycommonobject.initCombo=initCombo;
  function initCombo(requestPromise,httpPromise,flag) {
         
          
            
      
            //   if (requestPromise.length !== 0) {
          
//            } else {
//                sessionobject.activate(vm, $scope, pmspropertyprioritycommonobject);
//            }
        }
        
        pmspropertyprioritycommonobject.showPropertypriority = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertyprioritycommonobject.createObject();
            obj1.propertypriorityid = obj.propertypriorityid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/propertypriority/PropertypriorityView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertypriorityViewDialogController',
                locals: {
                    propertypriorityobj: obj
                },
            }).then(function () {
                // pmspropertyprioritycommonobject.save(ev, id);
            });
        }
      
        
        pmspropertyprioritycommonobject.showDiloag = function (ev, id) {
            //    pmspropertyprioritycommonobject.pmspropertypriority.createddate = $filter('date')(pmspropertyprioritycommonobject.pmspropertypriority.createddate, "medium");//"{{pmspropertyprioritycommonobject.pmspropertypriority.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertypriority/PropertypriorityAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertypriorityDialogController'
            }).then(function (answer) {
                pmspropertyprioritycommonobject.save(ev, id);
            });
        }
        
      pmspropertyprioritycommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmspropertyprioritycommonobject, "Propertypriority", pmspropertyprioritycommonobject.pmspropertypriority);
 
            });
        };
      
        pmspropertyprioritycommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmspropertyprioritycommonobject, $mdDialog, triLoaderService,  "propertypriority", "tabletitle");       
          };
        pmspropertyprioritycommonobject.editTodo = function (ev) {
            if (pmspropertyprioritycommonobject.tableobject.selected.length >= 1) {
                pmspropertyprioritycommonobject.addTodo(ev, pmspropertyprioritycommonobject.tableobject.selected[pmspropertyprioritycommonobject.tableobject.selected.length - 1].propertypriorityid);
            }
        };
        pmspropertyprioritycommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmspropertyprioritycommonobject,"propertypriority","pmspropertypriority");
        };
    }
})();
