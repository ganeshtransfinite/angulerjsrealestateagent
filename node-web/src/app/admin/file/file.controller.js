
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.admin')
            .controller('adminFileController', adminFileController)
  
            .controller('FileTableController', FileTableController)
 
            .controller('adminFileDialogController', adminFileDialogController)
            .controller('adminFileViewDialogController', adminFileViewDialogController)
            .service("adminfilecommonobject", adminfilecommonobject);
   
    function adminFileController($scope, adminfilecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, adminfilecommonobject, sessionobject, $localStorage, triMenu, triLayout);
        adminfilecommonobject.type = 1;
    }
    function init($scope, adminfilecommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/admin/flight");
            adminfilecommonobject.addTodo(ev, 0);
        }
    }
    function adminFileDialogController($scope, $mdDialog, adminfilecommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.adminfile = adminfilecommonobject.adminfile;
        $scope.adminfilecommonobject = adminfilecommonobject;
        $scope.adminfile = adminfilecommonobject.adminfile;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
      
 $scope.uploadcontent = uploadcontent;
        function uploadcontent($files) {
            adminfilecommonobject.selectcontent = $files;
        }
    }
    function adminFileViewDialogController($scope, $mdDialog, adminfilecommonobject, sessionobject, fileobj) {
        $scope.fileobj = fileobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function FileTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, adminfilecommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.adminfilecommonobject = adminfilecommonobject;
        var vm = this;
        adminfilecommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.adminfile = {
        };
        $scope.adminfile = vm.adminfile;
        $scope.adminfile.selectlistbox = [];
        $scope.adminfile.selectlistbox.filetypeid = true;
        $scope.adminfile.selectlistbox.filelocalityid = true;
        $scope.adminfile.selectamountform = 1000;
        $scope.adminfile.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-fileid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
               fileid: 'adminmenu.file.GRID.fileid',
filename: 'adminmenu.file.GRID.filename',
conenttype: 'adminmenu.file.GRID.conenttype',
content: 'adminmenu.file.GRID.content',
type: 'adminmenu.file.GRID.type',
userid: 'adminmenu.file.GRID.userid',
active: 'adminmenu.file.GRID.active',
createddate: 'adminmenu.file.GRID.createddate',
modifyddate: 'adminmenu.file.GRID.modifyddate',
recordorder: 'adminmenu.file.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
         adminfilecommonobject.initCombo();
   
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(adminfilecommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
             
// FileData
            vm.promise = sessionobject.getTable($scope, vm, "file", adminfilecommonobject, formData);
        }
        function upload($files) {
            adminfilecommonobject.files = $files;
        }
        vm.object = adminfilecommonobject;
      
    }
    function adminfilecommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var adminfilecommonobject = this;
        adminfilecommonobject.role = 'File';
        adminfilecommonobject.createObject = function () {
            return  {
                fileid: 0,
filename: '',
conenttype: '',
selectcontent: 0,
type: '',
selectuserid: 0,
active: '',
createddate: new Date(),
modifyddate: new Date(),
recordorder: 0
            };
        };
     
       
       
       
 adminfilecommonobject.initCombo=initCombo;
  function initCombo(flag) {
            var requestPromise = [];
            var httpPromise = null;
          
            
      
            //   if (requestPromise.length !== 0) {
            $q.all(requestPromise).then(function (data) {
                sessionobject.activate(vm, $scope, adminfilecommonobject);
                //    vm.fillCombo('filetypeid', vm);
                //  vm.fillCombo('filelocalityid', vm);
            });
//            } else {
//                sessionobject.activate(vm, $scope, adminfilecommonobject);
//            }
        }
        
        adminfilecommonobject.showFile = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = adminfilecommonobject.createObject();
            obj1.fileid = obj.fileid;
       
            $mdDialog.show({
                templateUrl: 'app/admin.file.FileView.tmpl.html',
                targetEvent: ev,
                controller: 'adminFileViewDialogController',
                locals: {
                    fileobj: obj
                },
            }).then(function () {
                // adminfilecommonobject.save(ev, id);
            });
        }
      
        
        adminfilecommonobject.showDiloag = function (ev, id) {
            //    adminfilecommonobject.adminfile.createddate = $filter('date')(adminfilecommonobject.adminfile.createddate, "medium");//"{{adminfilecommonobject.adminfile.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/admin.file.FileAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'adminFileDialogController'
            }).then(function (answer) {
                adminfilecommonobject.save(ev, id);
            });
        }
        
      adminfilecommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
  
sessionobject.uploadfiletoserver("content", requestPromise,adminfilecommonobject,adminfilecommonobject.adminfile);
  
            $q.all(requestPromise).then(function (data) {
  sessionobject.save(ev, id, triLoaderService, adminfilecommonobject, "File", adminfilecommonobject.adminfile);
 
            });
        };
      
        adminfilecommonobject.removeTodo = function (ev) {
             sessionobject.removeTodo(ev,  adminfilecommonobject, $mdDialog, triLoaderService,  "file", "tabletitle");       
          };
        adminfilecommonobject.editTodo = function (ev) {
            if (adminfilecommonobject.tableobject.selected.length >= 1) {
                adminfilecommonobject.addTodo(ev, adminfilecommonobject.tableobject.selected[adminfilecommonobject.tableobject.selected.length - 1].fileid);
            }
        };
        adminfilecommonobject.addTodo = function (ev, id) {
          sessionobject.addTodo(ev, id,adminfilecommonobject,"file","adminfile");
        };
    }
})();
