
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsTestController', pmsTestController)
  
            .controller('TestTableController', TestTableController)
 
            .controller('pmsTestDialogController', pmsTestDialogController)
            .controller('pmsTestViewDialogController', pmsTestViewDialogController)
            .service("pmstestcommonobject", pmstestcommonobject);
   
    function pmsTestController($scope, pmstestcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmstestcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmstestcommonobject.type = 1;
    }
    function init($scope, pmstestcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmstestcommonobject.addTodo(ev, 0);
        }
    }
    function pmsTestDialogController($scope, $mdDialog, pmstestcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmstest = pmstestcommonobject.pmstest;
        $scope.pmstestcommonobject = pmstestcommonobject;
        $scope.pmstest = pmstestcommonobject.pmstest;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
    }
    function pmsTestViewDialogController($scope, $mdDialog, pmstestcommonobject, sessionobject, testobj) {
        $scope.testobj = testobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function TestTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmstestcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmstestcommonobject = pmstestcommonobject;
        var vm = this;
        pmstestcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmstest = {
        };
        $scope.pmstest = vm.pmstest;
        $scope.pmstest.selectlistbox = [];
        $scope.pmstest.selectlistbox.testtypeid = true;
        $scope.pmstest.selectlistbox.testlocalityid = true;
        $scope.pmstest.selectamountform = 1000;
        $scope.pmstest.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-testid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               testid: 'MENU.test.GRID.testid',
name: 'MENU.test.GRID.name',
active: 'MENU.test.GRID.active',
createddate: 'MENU.test.GRID.createddate',
modifyddate: 'MENU.test.GRID.modifyddate',
userid: 'MENU.test.GRID.userid',
recordorder: 'MENU.test.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
      
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmstestcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
  if (vm.pmstest.selectlistbox.testflatamenitiesid) {
                formData.filter += "flatamenitiesid.flatamenitiesid|" + angular.toJson(vm.pmstest.selecttestflatamenitiesidtbl) + ";";
            }
  if (vm.pmstest.selectlistbox.testsocietyamenitiesid) {
                formData.filter += "societyamenitiesid.societyamenitiesid|" + angular.toJson(vm.pmstest.selecttestsocietyamenitiesidtbl) + ";";
            }
            vm.promise = sessionobject.getTable($scope, vm, "test", pmstestcommonobject, formData);
        }
        function upload($files) {
            pmstestcommonobject.files = $files;
        }
        vm.object = pmstestcommonobject;
 
       
          var requestPromise = [];
            var httpPromise = null;
   pmstestcommonobject.initCombo(requestPromise,httpPromise,true);
   
         $q.all(requestPromise).then(function (data) {
                sessionobject.activate(  vm, $scope, pmstestcommonobject);
                //    vm.fillCombo('testtypeid', vm);
                //  vm.fillCombo('testlocalityid', vm);
            });
    }
    function pmstestcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmstestcommonobject = this;
        pmstestcommonobject.role = 'Test';
        pmstestcommonobject.createObject = function () {
            return  {
                testid: 0,
name: '',
selectflatamenitiesid: [],
selectsocietyamenitiesid: [],
active: '',
createddate: new Date(),
modifyddate: new Date(),
selectuserid: 0,
recordorder: 0
            };
        };
     
       
       
       
 pmstestcommonobject.initCombo=initCombo;
  function initCombo(requestPromise,httpPromise,flag) {
         
          
            
       sessionobject.initCombo("FlatamenitiesForm", null, null, requestPromise, flag, pmstestcommonobject);
       sessionobject.initCombo("SocietyamenitiesForm", null, null, requestPromise, flag, pmstestcommonobject);
      
             sessionobject.initCombocount("FlatamenitiesForm", "Test", requestPromise, pmstestcommonobject,null,flag);  
             sessionobject.initCombocount("SocietyamenitiesForm", "Test", requestPromise, pmstestcommonobject,null,flag);  
            //   if (requestPromise.length !== 0) {
          
//            } else {
//                sessionobject.activate(vm, $scope, pmstestcommonobject);
//            }
        }
        
        pmstestcommonobject.showTest = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmstestcommonobject.createObject();
            obj1.testid = obj.testid;
       
            $mdDialog.show({
                templateUrl: 'app/pms/test/TestView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsTestViewDialogController',
                locals: {
                    testobj: obj
                },
            }).then(function () {
                // pmstestcommonobject.save(ev, id);
            });
        }
      
        
        pmstestcommonobject.showDiloag = function (ev, id) {
            //    pmstestcommonobject.pmstest.createddate = $filter('date')(pmstestcommonobject.pmstest.createddate, "medium");//"{{pmstestcommonobject.pmstest.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/test/TestAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsTestDialogController'
            }).then(function (answer) {
                pmstestcommonobject.save(ev, id);
            });
        }
        
      pmstestcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, pmstestcommonobject, "Test", pmstestcommonobject.pmstest);
 
            });
        };
      
        pmstestcommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  pmstestcommonobject, $mdDialog, triLoaderService,  "test", "tabletitle");       
          };
        pmstestcommonobject.editTodo = function (ev) {
            if (pmstestcommonobject.tableobject.selected.length >= 1) {
                pmstestcommonobject.addTodo(ev, pmstestcommonobject.tableobject.selected[pmstestcommonobject.tableobject.selected.length - 1].testid);
            }
        };
        pmstestcommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,pmstestcommonobject,"test","pmstest");
        };
    }
})();
