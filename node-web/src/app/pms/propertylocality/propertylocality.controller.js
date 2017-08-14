
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertylocalityController', pmsPropertylocalityController)
  
            .controller('PropertylocalityTableController', PropertylocalityTableController)
 
            .controller('pmsPropertylocalityDialogController', pmsPropertylocalityDialogController)
            .controller('pmsPropertylocalityViewDialogController', pmsPropertylocalityViewDialogController)
            .service("pmspropertylocalitycommonobject", pmspropertylocalitycommonobject);
   
    function pmsPropertylocalityController($scope, pmspropertylocalitycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertylocalitycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertylocalitycommonobject.type = 1;
    }
    function init($scope, pmspropertylocalitycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertylocalitycommonobject.addTodo(ev, 0);
        }
    }
    function pmsPropertylocalityDialogController($scope, $mdDialog, pmspropertylocalitycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmspropertylocality = pmspropertylocalitycommonobject.pmspropertylocality;
        $scope.pmspropertylocalitycommonobject = pmspropertylocalitycommonobject;
        $scope.pmspropertylocality = pmspropertylocalitycommonobject.pmspropertylocality;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsPropertylocalityViewDialogController($scope, $mdDialog, pmspropertylocalitycommonobject, sessionobject, propertylocalityobj) {
        $scope.propertylocalityobj = propertylocalityobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertylocalityTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertylocalitycommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertylocalitycommonobject = pmspropertylocalitycommonobject;
        var vm = this;
        pmspropertylocalitycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmspropertylocality = {
        };
        $scope.pmspropertylocality = vm.pmspropertylocality;
        $scope.pmspropertylocality.selectlistbox = [];
        $scope.pmspropertylocality.selectlistbox.propertylocalitytypeid = true;
        $scope.pmspropertylocality.selectlistbox.propertylocalitylocalityid = true;
        $scope.pmspropertylocality.selectamountform = 1000;
        $scope.pmspropertylocality.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertylocalityid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               propertylocalityid: 'MENU.propertylocality.GRID.propertylocalityid',
name: 'MENU.propertylocality.GRID.name',
cityid: 'MENU.propertylocality.GRID.cityid',
active: 'MENU.propertylocality.GRID.active',
createddate: 'MENU.propertylocality.GRID.createddate',
modifyddate: 'MENU.propertylocality.GRID.modifyddate',
userid: 'MENU.propertylocality.GRID.userid',
recordorder: 'MENU.propertylocality.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertylocalitycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
  if (vm.pmspropertylocality.selectlistbox.propertylocalitycityid) {
                formData.filter += "cityid.cityid|" + angular.toJson(vm.pmspropertylocality.selectpropertylocalitycityidtbl) + ";";
            }
            vm.promise = sessionobject.getTable($scope, vm, "propertylocality", pmspropertylocalitycommonobject, formData);
        }
        function upload($files) {
            pmspropertylocalitycommonobject.files = $files;
        }
        vm.object = pmspropertylocalitycommonobject;
 
        
   
      
                sessionobject.activate(  vm, $scope, pmspropertylocalitycommonobject);
              
    }
    function pmspropertylocalitycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertylocalitycommonobject = this;
        pmspropertylocalitycommonobject.role = 'Propertylocality';
        pmspropertylocalitycommonobject.createObject = function () {
            return  {
                propertylocalityid: 0,
name: '',
selectcityid: 1,
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
        
        
        pmspropertylocalitycommonobject.showPropertylocality = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertylocalitycommonobject.createObject();
            obj1.propertylocalityid = obj.propertylocalityid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/propertylocality/PropertylocalityView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertylocalityViewDialogController',
                locals: {
                    propertylocalityobj: obj
                },
            }).then(function () {
                // pmspropertylocalitycommonobject.save(ev, id);
            });
        }
      
        
        pmspropertylocalitycommonobject.showDiloag = function (ev, id) {
            //    pmspropertylocalitycommonobject.pmspropertylocality.createddate = $filter('date')(pmspropertylocalitycommonobject.pmspropertylocality.createddate, "medium");//"{{pmspropertylocalitycommonobject.pmspropertylocality.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/propertylocality/PropertylocalityAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertylocalityDialogController'
            }).then(function (answer) {
                pmspropertylocalitycommonobject.save(ev, id);
            });
        }
        
      pmspropertylocalitycommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmspropertylocalitycommonobject, "Propertylocality", pmspropertylocalitycommonobject.pmspropertylocality);
 
            });
        };
      
        pmspropertylocalitycommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmspropertylocalitycommonobject, $mdDialog, triLoaderService,  "propertylocality", "tabletitle");       
          };
        pmspropertylocalitycommonobject.editTodo = function (ev) {
            if (pmspropertylocalitycommonobject.tableobject.selected.length >= 1) {
                pmspropertylocalitycommonobject.addTodo(ev, pmspropertylocalitycommonobject.tableobject.selected[pmspropertylocalitycommonobject.tableobject.selected.length - 1].propertylocalityid);
            }
        };
        pmspropertylocalitycommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmspropertylocalitycommonobject,"propertylocality","pmspropertylocality");
        };
    }
})();
