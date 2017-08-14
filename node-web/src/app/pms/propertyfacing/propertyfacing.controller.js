
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertyfacingController', pmsPropertyfacingController)
  
            .controller('PropertyfacingTableController', PropertyfacingTableController)
 
            .controller('pmsPropertyfacingDialogController', pmsPropertyfacingDialogController)
            .controller('pmsPropertyfacingViewDialogController', pmsPropertyfacingViewDialogController)
            .service("pmspropertyfacingcommonobject", pmspropertyfacingcommonobject);
   
    function pmsPropertyfacingController($scope, pmspropertyfacingcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertyfacingcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertyfacingcommonobject.type = 1;
    }
    function init($scope, pmspropertyfacingcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertyfacingcommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertyfacingDialogController($scope, $mdDialog, pmspropertyfacingcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertyfacing = pmspropertyfacingcommonobject.pmspropertyfacing;
        $scope.pmspropertyfacingcommonobject = pmspropertyfacingcommonobject;
        $scope.pmspropertyfacing = pmspropertyfacingcommonobject.pmspropertyfacing;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsPropertyfacingViewDialogController($scope, $mdDialog, pmspropertyfacingcommonobject, sessionobject, propertyfacingobj) {
        $scope.propertyfacingobj = propertyfacingobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertyfacingTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertyfacingcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertyfacingcommonobject = pmspropertyfacingcommonobject;
        var vm = this;
        pmspropertyfacingcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertyfacing = {
        };
        $scope.pmspropertyfacing = vm.pmspropertyfacing;
        $scope.pmspropertyfacing.selectlistbox = [];
        $scope.pmspropertyfacing.selectlistbox.propertyfacingtypeid = true;
        $scope.pmspropertyfacing.selectlistbox.propertyfacinglocalityid = true;
        $scope.pmspropertyfacing.selectamountform = 1000;
        $scope.pmspropertyfacing.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertyfacingid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               propertyfacingid: 'MENU.propertyfacing.GRID.propertyfacingid',
name: 'MENU.propertyfacing.GRID.name',
active: 'MENU.propertyfacing.GRID.active',
createddate: 'MENU.propertyfacing.GRID.createddate',
modifyddate: 'MENU.propertyfacing.GRID.modifyddate',
userid: 'MENU.propertyfacing.GRID.userid',
recordorder: 'MENU.propertyfacing.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertyfacingcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "propertyfacing", pmspropertyfacingcommonobject, formData);
        }
        function upload($files) {
            pmspropertyfacingcommonobject.files = $files;
        }
        vm.object = pmspropertyfacingcommonobject;
 
      
                sessionobject.activate(  vm, $scope, pmspropertyfacingcommonobject);
               
    }
    function pmspropertyfacingcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertyfacingcommonobject = this;
        pmspropertyfacingcommonobject.role = 'Propertyfacing';
        pmspropertyfacingcommonobject.createObject = function () {
            return  {
                propertyfacingid: 0,
name: '',
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
       
        
        
        pmspropertyfacingcommonobject.showPropertyfacing = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertyfacingcommonobject.createObject();
            obj1.propertyfacingid = obj.propertyfacingid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/propertyfacing/PropertyfacingView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyfacingViewDialogController',
                locals: {
                    propertyfacingobj: obj
                },
            }).then(function () {
                // pmspropertyfacingcommonobject.save(ev, id);
            });
        }
      
        
        pmspropertyfacingcommonobject.showDiloag = function (ev, id) {
            //    pmspropertyfacingcommonobject.pmspropertyfacing.createddate = $filter('date')(pmspropertyfacingcommonobject.pmspropertyfacing.createddate, "medium");//"{{pmspropertyfacingcommonobject.pmspropertyfacing.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertyfacing/PropertyfacingAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyfacingDialogController'
            }).then(function (answer) {
                pmspropertyfacingcommonobject.save(ev, id);
            });
        }
        
      pmspropertyfacingcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmspropertyfacingcommonobject, "Propertyfacing", pmspropertyfacingcommonobject.pmspropertyfacing);
 
            });
        };
      
        pmspropertyfacingcommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmspropertyfacingcommonobject, $mdDialog, triLoaderService,  "propertyfacing", "tabletitle");       
          };
        pmspropertyfacingcommonobject.editTodo = function (ev) {
            if (pmspropertyfacingcommonobject.tableobject.selected.length >= 1) {
                pmspropertyfacingcommonobject.addTodo(ev, pmspropertyfacingcommonobject.tableobject.selected[pmspropertyfacingcommonobject.tableobject.selected.length - 1].propertyfacingid);
            }
        };
        pmspropertyfacingcommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmspropertyfacingcommonobject,"propertyfacing","pmspropertyfacing");
        };
    }
})();
