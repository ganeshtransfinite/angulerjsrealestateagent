
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsOccupationController', pmsOccupationController)
  
            .controller('OccupationTableController', OccupationTableController)
 
            .controller('pmsOccupationDialogController', pmsOccupationDialogController)
            .controller('pmsOccupationViewDialogController', pmsOccupationViewDialogController)
            .service("pmsoccupationcommonobject", pmsoccupationcommonobject);
   
    function pmsOccupationController($scope, pmsoccupationcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsoccupationcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsoccupationcommonobject.type = 1;
    }
    function init($scope, pmsoccupationcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsoccupationcommonobject.addTodo(ev, 0);
        }
    }
    function pmsOccupationDialogController($scope, $mdDialog, pmsoccupationcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsoccupation = pmsoccupationcommonobject.pmsoccupation;
        $scope.pmsoccupationcommonobject = pmsoccupationcommonobject;
        $scope.pmsoccupation = pmsoccupationcommonobject.pmsoccupation;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsOccupationViewDialogController($scope, $mdDialog, pmsoccupationcommonobject, sessionobject, occupationobj) {
        $scope.occupationobj = occupationobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function OccupationTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsoccupationcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsoccupationcommonobject = pmsoccupationcommonobject;
        var vm = this;
        pmsoccupationcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsoccupation = {
        };
        $scope.pmsoccupation = vm.pmsoccupation;
        $scope.pmsoccupation.selectlistbox = [];
        $scope.pmsoccupation.selectlistbox.occupationtypeid = true;
        $scope.pmsoccupation.selectlistbox.occupationlocalityid = true;
        $scope.pmsoccupation.selectamountform = 1000;
        $scope.pmsoccupation.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-occupationid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               occupationid: 'MENU.occupation.GRID.occupationid',
name: 'MENU.occupation.GRID.name',
active: 'MENU.occupation.GRID.active',
createddate: 'MENU.occupation.GRID.createddate',
modifyddate: 'MENU.occupation.GRID.modifyddate',
userid: 'MENU.occupation.GRID.userid',
recordorder: 'MENU.occupation.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsoccupationcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "occupation", pmsoccupationcommonobject, formData);
        }
        function upload($files) {
            pmsoccupationcommonobject.files = $files;
        }
        vm.object = pmsoccupationcommonobject;
 
       
         
                sessionobject.activate(  vm, $scope, pmsoccupationcommonobject);
                 
    }
    function pmsoccupationcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsoccupationcommonobject = this;
        pmsoccupationcommonobject.role = 'Occupation';
        pmsoccupationcommonobject.createObject = function () {
            return  {
                occupationid: 0,
name: '',
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
        
        
        pmsoccupationcommonobject.showOccupation = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsoccupationcommonobject.createObject();
            obj1.occupationid = obj.occupationid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/occupation/OccupationView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsOccupationViewDialogController',
                locals: {
                    occupationobj: obj
                },
            }).then(function () {
                // pmsoccupationcommonobject.save(ev, id);
            });
        }
      
        
        pmsoccupationcommonobject.showDiloag = function (ev, id) {
            //    pmsoccupationcommonobject.pmsoccupation.createddate = $filter('date')(pmsoccupationcommonobject.pmsoccupation.createddate, "medium");//"{{pmsoccupationcommonobject.pmsoccupation.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/occupation/OccupationAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsOccupationDialogController'
            }).then(function (answer) {
                pmsoccupationcommonobject.save(ev, id);
            });
        }
        
      pmsoccupationcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmsoccupationcommonobject, "Occupation", pmsoccupationcommonobject.pmsoccupation);
 
            });
        };
      
        pmsoccupationcommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmsoccupationcommonobject, $mdDialog, triLoaderService,  "occupation", "tabletitle");       
          };
        pmsoccupationcommonobject.editTodo = function (ev) {
            if (pmsoccupationcommonobject.tableobject.selected.length >= 1) {
                pmsoccupationcommonobject.addTodo(ev, pmsoccupationcommonobject.tableobject.selected[pmsoccupationcommonobject.tableobject.selected.length - 1].occupationid);
            }
        };
        pmsoccupationcommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmsoccupationcommonobject,"occupation","pmsoccupation");
        };
    }
})();
