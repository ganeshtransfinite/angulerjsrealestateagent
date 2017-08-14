
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsCustomertypeController', pmsCustomertypeController)
  
            .controller('CustomertypeTableController', CustomertypeTableController)
 
            .controller('pmsCustomertypeDialogController', pmsCustomertypeDialogController)
            .controller('pmsCustomertypeViewDialogController', pmsCustomertypeViewDialogController)
            .service("pmscustomertypecommonobject", pmscustomertypecommonobject);
   
    function pmsCustomertypeController($scope, pmscustomertypecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmscustomertypecommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmscustomertypecommonobject.type = 1;
    }
    function init($scope, pmscustomertypecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmscustomertypecommonobject.addTodo(ev, 0);
        }
    }
    function pmsCustomertypeDialogController($scope, $mdDialog, pmscustomertypecommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmscustomertype = pmscustomertypecommonobject.pmscustomertype;
        $scope.pmscustomertypecommonobject = pmscustomertypecommonobject;
        $scope.pmscustomertype = pmscustomertypecommonobject.pmscustomertype;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsCustomertypeViewDialogController($scope, $mdDialog, pmscustomertypecommonobject, sessionobject, customertypeobj) {
        $scope.customertypeobj = customertypeobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function CustomertypeTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmscustomertypecommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmscustomertypecommonobject = pmscustomertypecommonobject;
        var vm = this;
        pmscustomertypecommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmscustomertype = {
        };
        $scope.pmscustomertype = vm.pmscustomertype;
        $scope.pmscustomertype.selectlistbox = [];
        $scope.pmscustomertype.selectlistbox.customertypetypeid = true;
        $scope.pmscustomertype.selectlistbox.customertypelocalityid = true;
        $scope.pmscustomertype.selectamountform = 1000;
        $scope.pmscustomertype.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-customertypeid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               customertypeid: 'MENU.customertype.GRID.customertypeid',
name: 'MENU.customertype.GRID.name',
active: 'MENU.customertype.GRID.active',
createddate: 'MENU.customertype.GRID.createddate',
modifyddate: 'MENU.customertype.GRID.modifyddate',
userid: 'MENU.customertype.GRID.userid',
recordorder: 'MENU.customertype.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmscustomertypecommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "customertype", pmscustomertypecommonobject, formData);
        }
        function upload($files) {
            pmscustomertypecommonobject.files = $files;
        }
        vm.object = pmscustomertypecommonobject;
   sessionobject.activate(vm, $scope, pmscustomertypecommonobject);

        
    }
    function pmscustomertypecommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmscustomertypecommonobject = this;
        pmscustomertypecommonobject.role = 'Customertype';
        pmscustomertypecommonobject.createObject = function () {
            return  {
                customertypeid: 0,
name: '',
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
        
        pmscustomertypecommonobject.showCustomertype = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmscustomertypecommonobject.createObject();
            obj1.customertypeid = obj.customertypeid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/customertype/CustomertypeView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCustomertypeViewDialogController',
                locals: {
                    customertypeobj: obj
                },
            }).then(function () {
                // pmscustomertypecommonobject.save(ev, id);
            });
        }
      
        
        pmscustomertypecommonobject.showDiloag = function (ev, id) {
            //    pmscustomertypecommonobject.pmscustomertype.createddate = $filter('date')(pmscustomertypecommonobject.pmscustomertype.createddate, "medium");//"{{pmscustomertypecommonobject.pmscustomertype.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/customertype/CustomertypeAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCustomertypeDialogController'
            }).then(function (answer) {
                pmscustomertypecommonobject.save(ev, id);
            });
        }
        
      pmscustomertypecommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmscustomertypecommonobject, "Customertype", pmscustomertypecommonobject.pmscustomertype);
 
            });
        };
      
        pmscustomertypecommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmscustomertypecommonobject, $mdDialog, triLoaderService,  "customertype", "tabletitle");       
          };
        pmscustomertypecommonobject.editTodo = function (ev) {
            if (pmscustomertypecommonobject.tableobject.selected.length >= 1) {
                pmscustomertypecommonobject.addTodo(ev, pmscustomertypecommonobject.tableobject.selected[pmscustomertypecommonobject.tableobject.selected.length - 1].customertypeid);
            }
        };
        pmscustomertypecommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmscustomertypecommonobject,"customertype","pmscustomertype");
        };
    }
})();
