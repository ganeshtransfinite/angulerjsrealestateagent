
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.admin')
            .controller('adminDatarecoveryController', adminDatarecoveryController)
  
            .controller('DatarecoveryTableController', DatarecoveryTableController)
 
            .controller('adminDatarecoveryDialogController', adminDatarecoveryDialogController)
            .controller('adminDatarecoveryViewDialogController', adminDatarecoveryViewDialogController)
            .service("admindatarecoverycommonobject", admindatarecoverycommonobject);
   
    function adminDatarecoveryController($scope, admindatarecoverycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, admindatarecoverycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        admindatarecoverycommonobject.type = 1;
    }
    function init($scope, admindatarecoverycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/admin/flight");
            admindatarecoverycommonobject.addTodo(ev, 0);
        }
    }
    function adminDatarecoveryDialogController($scope, $mdDialog, admindatarecoverycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.admindatarecovery = admindatarecoverycommonobject.admindatarecovery;
        $scope.admindatarecoverycommonobject = admindatarecoverycommonobject;
        $scope.admindatarecovery = admindatarecoverycommonobject.admindatarecovery;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
 $scope.uploadcontent = uploadcontent;
        function uploadcontent($files) {
            admindatarecoverycommonobject.selectcontent = $files;
        }
    }
    function adminDatarecoveryViewDialogController($scope, $mdDialog, admindatarecoverycommonobject, sessionobject, datarecoveryobj) {
        $scope.datarecoveryobj = datarecoveryobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function DatarecoveryTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, admindatarecoverycommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.admindatarecoverycommonobject = admindatarecoverycommonobject;
        var vm = this;
        admindatarecoverycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.admindatarecovery = {
        };
        $scope.admindatarecovery = vm.admindatarecovery;
        $scope.admindatarecovery.selectlistbox = [];
        $scope.admindatarecovery.selectlistbox.datarecoverytypeid = true;
        $scope.admindatarecovery.selectlistbox.datarecoverylocalityid = true;
        $scope.admindatarecovery.selectamountform = 1000;
        $scope.admindatarecovery.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-datarecoveryid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               datarecoveryid: 'adminmenu.datarecovery.GRID.datarecoveryid',
tablename: 'adminmenu.datarecovery.GRID.tablename',
content: 'adminmenu.datarecovery.GRID.content',
userid: 'adminmenu.datarecovery.GRID.userid',
active: 'adminmenu.datarecovery.GRID.active',
createddate: 'adminmenu.datarecovery.GRID.createddate',
modifyddate: 'adminmenu.datarecovery.GRID.modifyddate',
recordorder: 'adminmenu.datarecovery.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
         admindatarecoverycommonobject.initCombo();
   
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(admindatarecoverycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
// FileData
            vm.promise = sessionobject.getTable($scope, vm, "datarecovery", admindatarecoverycommonobject, formData);
        }
        function upload($files) {
            admindatarecoverycommonobject.files = $files;
        }
        vm.object = admindatarecoverycommonobject;
      
    }
    function admindatarecoverycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var admindatarecoverycommonobject = this;
        admindatarecoverycommonobject.role = 'Datarecovery';
        admindatarecoverycommonobject.createObject = function () {
            return  {
                datarecoveryid: 0,
tablename: '',
selectcontent: 0,
selectuserid: 0,
active: '',
createddate: new Date(),
modifyddate: new Date(),
recordorder: 0
            };
        };
     
       
       
       
 admindatarecoverycommonobject.initCombo=initCombo;
  function initCombo(flag) {
            var requestPromise = [];
            var httpPromise = null;
          
            
      
            //   if (requestPromise.length !== 0) {
            $q.all(requestPromise).then(function (data) {
                sessionobject.activate(vm, $scope, admindatarecoverycommonobject);
                //    vm.fillCombo('datarecoverytypeid', vm);
                //  vm.fillCombo('datarecoverylocalityid', vm);
            });
//            } else {
//                sessionobject.activate(vm, $scope, admindatarecoverycommonobject);
//            }
        }
        
        admindatarecoverycommonobject.showDatarecovery = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = admindatarecoverycommonobject.createObject();
            obj1.datarecoveryid = obj.datarecoveryid;
       
            $mdDialog.show({
                templateUrl: 'app/admin/datarecovery/DatarecoveryView.tmpl.html',
                targetEvent: ev,
                controller: 'adminDatarecoveryViewDialogController',
                locals: {
                    datarecoveryobj: obj
                },
            }).then(function () {
                // admindatarecoverycommonobject.save(ev, id);
            });
        }
      
        
        admindatarecoverycommonobject.showDiloag = function (ev, id) {
            //    admindatarecoverycommonobject.admindatarecovery.createddate = $filter('date')(admindatarecoverycommonobject.admindatarecovery.createddate, "medium");//"{{admindatarecoverycommonobject.admindatarecovery.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/admin/datarecovery/DatarecoveryAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'adminDatarecoveryDialogController'
            }).then(function (answer) {
                admindatarecoverycommonobject.save(ev, id);
            });
        }
        
      admindatarecoverycommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
sessionobject.uploadfiletoserver("content", requestPromise,admindatarecoverycommonobject,admindatarecoverycommonobject.admindatarecovery);
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, admindatarecoverycommonobject, "Datarecovery", admindatarecoverycommonobject.admindatarecovery);
 
            });
        };
      
        admindatarecoverycommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  admindatarecoverycommonobject, $mdDialog, triLoaderService,  "datarecovery", "tabletitle");       
          };
        admindatarecoverycommonobject.editTodo = function (ev) {
            if (admindatarecoverycommonobject.tableobject.selected.length >= 1) {
                admindatarecoverycommonobject.addTodo(ev, admindatarecoverycommonobject.tableobject.selected[admindatarecoverycommonobject.tableobject.selected.length - 1].datarecoveryid);
            }
        };
        admindatarecoverycommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,admindatarecoverycommonobject,"datarecovery","admindatarecovery");
        };
    }
})();
