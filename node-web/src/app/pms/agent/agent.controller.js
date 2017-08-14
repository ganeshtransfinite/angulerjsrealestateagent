
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsAgentController', pmsAgentController)

            .controller('AgentTableController', AgentTableController)

            .controller('pmsAgentDialogController', pmsAgentDialogController)
            .controller('pmsAgentViewDialogController', pmsAgentViewDialogController)
            .service("pmsagentcommonobject", pmsagentcommonobject);
    function pmsAgentController($scope, pmsagentcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsagentcommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsagentcommonobject.type = 1;
    }
    function init($scope, pmsagentcommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsagentcommonobject.addTodo(ev, 0);
        }
    }
    function pmsAgentDialogController($scope, $mdDialog, pmsagentcommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsagent = pmsagentcommonobject.pmsagent;
        $scope.pmsagentcommonobject = pmsagentcommonobject;
        $scope.pmsagent = pmsagentcommonobject.pmsagent;
        $scope.hide = function () {
            $mdDialog.hide(pmsagentcommonobject.pmsagent);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
    }
    function pmsAgentViewDialogController($scope, $mdDialog, pmsagentcommonobject, sessionobject, agentobj) {
        $scope.agentobj = agentobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function AgentTableController($q, $scope, $http,$mdDialog , triLayout, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsagentcommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsagentcommonobject = pmsagentcommonobject;
        var vm = this;
        pmsagentcommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsagent = {
        };
        $scope.pmsagent = vm.pmsagent;
        $scope.pmsagent.selectlistbox = [];
        $scope.pmsagent.selectlistbox.agenttypeid = true;
        $scope.pmsagent.selectlistbox.agentlocalityid = true;
        $scope.pmsagent.selectamountform = 1000;
        $scope.pmsagent.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-agentid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            agentid: 'MENU.agent.GRID.agentid',
            name: 'MENU.agent.GRID.name',
            mobile: 'MENU.agent.GRID.mobile',
            email: 'MENU.agent.GRID.email',
            companyname: 'MENU.agent.GRID.companyname',
            address: 'MENU.agent.GRID.address',
            cityid: 'MENU.agent.GRID.cityid',
            active: 'MENU.agent.GRID.active',
            createddate: 'MENU.agent.GRID.createddate',
            modifyddate: 'MENU.agent.GRID.modifyddate',
            userid: 'MENU.agent.GRID.userid',
            recordorder: 'MENU.agent.GRID.recordorder',
            propertylocalityid: 'MENU.agent.GRID.propertylocalityid',
            modeoperationid: 'MENU.agent.GRID.modeoperationid'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsagentcommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
            if (vm.pmsagent.selectlistbox.agentcityid) {
                formData.filter +=sessionobject.chkData( "cityid.cityid|" + angular.toJson(vm.pmsagent.selectagentcityidtbl) + ";");
            }
            if (vm.pmsagent.selectlistbox.agentpropertylocalityid) {
                formData.filter += sessionobject.chkData("propertylocalityid.propertylocalityid|" + angular.toJson(vm.pmsagent.selectagentpropertylocalityidtbl) + ";");
            }
            if (vm.pmsagent.selectlistbox.agentmodeoperationid) {
                formData.filter += sessionobject.chkData("modeoperationid.modeoperationid|" + angular.toJson(vm.pmsagent.selectagentmodeoperationidtbl) + ";");
            }
            vm.promise = sessionobject.getTable($scope, vm, "agent", pmsagentcommonobject, formData);
        }
        function upload($files) {
            pmsagentcommonobject.files = $files;
        }
        vm.object = pmsagentcommonobject;
        sessionobject.initComboCountInitALL("Property", pmsagentcommonobject.type, true);
        sessionobject.activate(vm, $scope, pmsagentcommonobject);
//        triLayout.layout.sideMenuSize = 'icon';

 $scope.addeditform = false;
        // triLayout.layout.sideMenuSize = 'icon';


        pmsagentcommonobject.mdDialog = $mdDialog;
        $scope.hide = function (id) {
            $scope.addeditform = false;
       //     alert(vm.selected);
           
             pmsagentcommonobject.tableobject.selected=[];
            pmsagentcommonobject.save(null, id);
        };
        $scope.cancel = function () {
           pmsagentcommonobject.tableobject.selected=[];
            $scope.addeditform = false;
        };
        vm.showDilog = function () {
            $scope.addeditform = true;
            $scope.sessionobject = sessionobject;
            pmsagentcommonobject.pmsagent.selectlistbox = $scope.pmsagent.selectlistbox;
            $scope.pmsagent = pmsagentcommonobject.pmsagent;
            $scope.pmsagentcommonobject = pmsagentcommonobject;
            $scope.pmsagent = pmsagentcommonobject.pmsagent;


        };

    }
    function pmsagentcommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsagentcommonobject = this;
        pmsagentcommonobject.role = 'Agent';
        pmsagentcommonobject.createObject = function () {
            return  {
                agentid: 0,
                name: '',
                mobile: '',
                email: '',
                companyname: '',
                address: '',
                selectcityid: 1,
                selectpropertylocalityid: [],
                selectmodeoperationid: [],
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0,
                selectremark: []
            };
        };
        pmsagentcommonobject.showAgent = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsagentcommonobject.createObject();
            obj1.agentid = obj.agentid;
            $mdDialog.show({
                templateUrl: 'app/pms/agent/AgentView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsAgentViewDialogController',
                locals: {
                    agentobj: obj
                },
            }).then(function () {
                // pmsagentcommonobject.save(ev, id);
            });
        };


        pmsagentcommonobject.showDiloag = function (ev, id) {
            if (id !== 0) {

                pmsagentcommonobject.pmsagent.createddate = new Date(pmsagentcommonobject.pmsagent.createddate);
                pmsagentcommonobject.pmsagent.modifyddate = new Date(pmsagentcommonobject.pmsagent.modifyddate);
            }
            pmsagentcommonobject.tableobject.showDilog(id);
//    pmsagentcommonobject.pmsagent.createddate = $filter('date')(pmsagentcommonobject.pmsagent.createddate, "medium");//"{{pmsagentcommonobject.pmsagent.createddate | date:'medium'}}";
           
        };

        pmsagentcommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmsagentcommonobject, "Agent", pmsagentcommonobject.pmsagent);
            });
        };
        pmsagentcommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmsagentcommonobject, $mdDialog, triLoaderService, "agent", "tabletitle");
        };
        pmsagentcommonobject.editTodo = function (ev) {
            if (pmsagentcommonobject.tableobject.selected.length >= 1) {
                pmsagentcommonobject.addTodo(ev, pmsagentcommonobject.tableobject.selected[pmsagentcommonobject.tableobject.selected.length - 1].agentid);
            }
        };
        pmsagentcommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmsagentcommonobject, "agent", "pmsagent");
        };
    }
})();
