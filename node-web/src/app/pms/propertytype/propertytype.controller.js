
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertytypeController', pmsPropertytypeController)
  
            .controller('PropertytypeTableController', PropertytypeTableController)
 
            .controller('pmsPropertytypeDialogController', pmsPropertytypeDialogController)
            .controller('pmsPropertytypeViewDialogController', pmsPropertytypeViewDialogController)
            .service("pmspropertytypecommonobject", pmspropertytypecommonobject);
   
    function pmsPropertytypeController($scope, pmspropertytypecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertytypecommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertytypecommonobject.type = 1;
    }
    function init($scope, pmspropertytypecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertytypecommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertytypeDialogController($scope, $mdDialog, pmspropertytypecommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertytype = pmspropertytypecommonobject.pmspropertytype;
        $scope.pmspropertytypecommonobject = pmspropertytypecommonobject;
        $scope.pmspropertytype = pmspropertytypecommonobject.pmspropertytype;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsPropertytypeViewDialogController($scope, $mdDialog, pmspropertytypecommonobject, sessionobject, propertytypeobj) {
        $scope.propertytypeobj = propertytypeobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertytypeTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertytypecommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertytypecommonobject = pmspropertytypecommonobject;
        var vm = this;
        pmspropertytypecommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertytype = {
        };
        $scope.pmspropertytype = vm.pmspropertytype;
        $scope.pmspropertytype.selectlistbox = [];
        $scope.pmspropertytype.selectlistbox.propertytypetypeid = true;
        $scope.pmspropertytype.selectlistbox.propertytypelocalityid = true;
        $scope.pmspropertytype.selectamountform = 1000;
        $scope.pmspropertytype.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertytypeid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               propertytypeid: 'MENU.propertytype.GRID.propertytypeid',
name: 'MENU.propertytype.GRID.name',
active: 'MENU.propertytype.GRID.active',
createddate: 'MENU.propertytype.GRID.createddate',
modifyddate: 'MENU.propertytype.GRID.modifyddate',
userid: 'MENU.propertytype.GRID.userid',
recordorder: 'MENU.propertytype.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertytypecommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "propertytype", pmspropertytypecommonobject, formData);
        }
        function upload($files) {
            pmspropertytypecommonobject.files = $files;
        }
        vm.object = pmspropertytypecommonobject;
 
       
         
                sessionobject.activate(  vm, $scope, pmspropertytypecommonobject);
                
    }
    function pmspropertytypecommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertytypecommonobject = this;
        pmspropertytypecommonobject.role = 'Propertytype';
        pmspropertytypecommonobject.createObject = function () {
            return  {
                propertytypeid: 0,
name: '',
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
       
       
 pmspropertytypecommonobject.initCombo=initCombo;
  function initCombo(requestPromise,httpPromise,flag) {
         
          
            
      
            //   if (requestPromise.length !== 0) {
          
//            } else {
//                sessionobject.activate(vm, $scope, pmspropertytypecommonobject);
//            }
        }
        
        pmspropertytypecommonobject.showPropertytype = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertytypecommonobject.createObject();
            obj1.propertytypeid = obj.propertytypeid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/propertytype/PropertytypeView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertytypeViewDialogController',
                locals: {
                    propertytypeobj: obj
                },
            }).then(function () {
                // pmspropertytypecommonobject.save(ev, id);
            });
        }
      
        
        pmspropertytypecommonobject.showDiloag = function (ev, id) {
            //    pmspropertytypecommonobject.pmspropertytype.createddate = $filter('date')(pmspropertytypecommonobject.pmspropertytype.createddate, "medium");//"{{pmspropertytypecommonobject.pmspropertytype.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertytype/PropertytypeAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertytypeDialogController'
            }).then(function (answer) {
                pmspropertytypecommonobject.save(ev, id);
            });
        }
        
      pmspropertytypecommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmspropertytypecommonobject, "Propertytype", pmspropertytypecommonobject.pmspropertytype);
 
            });
        };
      
        pmspropertytypecommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmspropertytypecommonobject, $mdDialog, triLoaderService,  "propertytype", "tabletitle");       
          };
        pmspropertytypecommonobject.editTodo = function (ev) {
            if (pmspropertytypecommonobject.tableobject.selected.length >= 1) {
                pmspropertytypecommonobject.addTodo(ev, pmspropertytypecommonobject.tableobject.selected[pmspropertytypecommonobject.tableobject.selected.length - 1].propertytypeid);
            }
        };
        pmspropertytypecommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmspropertytypecommonobject,"propertytype","pmspropertytype");
        };
    }
})();
