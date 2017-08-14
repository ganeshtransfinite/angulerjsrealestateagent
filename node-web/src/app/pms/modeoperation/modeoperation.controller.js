
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsModeoperationController', pmsModeoperationController)
  
            .controller('ModeoperationTableController', ModeoperationTableController)
 
            .controller('pmsModeoperationDialogController', pmsModeoperationDialogController)
            .controller('pmsModeoperationViewDialogController', pmsModeoperationViewDialogController)
            .service("pmsmodeoperationcommonobject", pmsmodeoperationcommonobject);
   
    function pmsModeoperationController($scope, pmsmodeoperationcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsmodeoperationcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsmodeoperationcommonobject.type = 1;
    }
    function init($scope, pmsmodeoperationcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsmodeoperationcommonobject.addTodo(ev, 0);
        }
    }
    function pmsModeoperationDialogController($scope, $mdDialog, pmsmodeoperationcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsmodeoperation = pmsmodeoperationcommonobject.pmsmodeoperation;
        $scope.pmsmodeoperationcommonobject = pmsmodeoperationcommonobject;
        $scope.pmsmodeoperation = pmsmodeoperationcommonobject.pmsmodeoperation;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsModeoperationViewDialogController($scope, $mdDialog, pmsmodeoperationcommonobject, sessionobject, modeoperationobj) {
        $scope.modeoperationobj = modeoperationobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function ModeoperationTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsmodeoperationcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsmodeoperationcommonobject = pmsmodeoperationcommonobject;
        var vm = this;
        pmsmodeoperationcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsmodeoperation = {
        };
        $scope.pmsmodeoperation = vm.pmsmodeoperation;
        $scope.pmsmodeoperation.selectlistbox = [];
        $scope.pmsmodeoperation.selectlistbox.modeoperationtypeid = true;
        $scope.pmsmodeoperation.selectlistbox.modeoperationlocalityid = true;
        $scope.pmsmodeoperation.selectamountform = 1000;
        $scope.pmsmodeoperation.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-modeoperationid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               modeoperationid: 'MENU.modeoperation.GRID.modeoperationid',
name: 'MENU.modeoperation.GRID.name',
active: 'MENU.modeoperation.GRID.active',
createddate: 'MENU.modeoperation.GRID.createddate',
modifyddate: 'MENU.modeoperation.GRID.modifyddate',
userid: 'MENU.modeoperation.GRID.userid',
recordorder: 'MENU.modeoperation.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsmodeoperationcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
            vm.promise = sessionobject.getTable($scope, vm, "modeoperation", pmsmodeoperationcommonobject, formData);
        }
        function upload($files) {
            pmsmodeoperationcommonobject.files = $files;
        }
        vm.object = pmsmodeoperationcommonobject;
 
        
                sessionobject.activate(  vm, $scope, pmsmodeoperationcommonobject);
                //    vm.fillCombo('modeoperationtypeid', vm);
                //  vm.fillCombo('modeoperationlocalityid', vm);
           
    }
    function pmsmodeoperationcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsmodeoperationcommonobject = this;
        pmsmodeoperationcommonobject.role = 'Modeoperation';
        pmsmodeoperationcommonobject.createObject = function () {
            return  {
                modeoperationid: 0,
name: '',
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
       
        
        
        pmsmodeoperationcommonobject.showModeoperation = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsmodeoperationcommonobject.createObject();
            obj1.modeoperationid = obj.modeoperationid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/modeoperation/ModeoperationView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsModeoperationViewDialogController',
                locals: {
                    modeoperationobj: obj
                },
            }).then(function () {
                // pmsmodeoperationcommonobject.save(ev, id);
            });
        }
      
        
        pmsmodeoperationcommonobject.showDiloag = function (ev, id) {
            //    pmsmodeoperationcommonobject.pmsmodeoperation.createddate = $filter('date')(pmsmodeoperationcommonobject.pmsmodeoperation.createddate, "medium");//"{{pmsmodeoperationcommonobject.pmsmodeoperation.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/modeoperation/ModeoperationAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsModeoperationDialogController'
            }).then(function (answer) {
                pmsmodeoperationcommonobject.save(ev, id);
            });
        }
        
      pmsmodeoperationcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmsmodeoperationcommonobject, "Modeoperation", pmsmodeoperationcommonobject.pmsmodeoperation);
 
            });
        };
      
        pmsmodeoperationcommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmsmodeoperationcommonobject, $mdDialog, triLoaderService,  "modeoperation", "tabletitle");       
          };
        pmsmodeoperationcommonobject.editTodo = function (ev) {
            if (pmsmodeoperationcommonobject.tableobject.selected.length >= 1) {
                pmsmodeoperationcommonobject.addTodo(ev, pmsmodeoperationcommonobject.tableobject.selected[pmsmodeoperationcommonobject.tableobject.selected.length - 1].modeoperationid);
            }
        };
        pmsmodeoperationcommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmsmodeoperationcommonobject,"modeoperation","pmsmodeoperation");
        };
    }
})();
