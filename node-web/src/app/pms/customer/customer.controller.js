
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsCustomerController', pmsCustomerController)

            .controller('CustomerTableController', CustomerTableController)

            .controller('pmsCustomerDialogController', pmsCustomerDialogController)
            .controller('pmsCustomerDialoglOCALController', pmsCustomerDialoglOCALController)
            .controller('pmsCustomerViewDialogController', pmsCustomerViewDialogController)
            .service("pmscustomercommonobject", pmscustomercommonobject);

    function pmsCustomerController($scope, pmscustomercommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmscustomercommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmscustomercommonobject.type = 1;
    }
    function init($scope, pmscustomercommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmscustomercommonobject.addTodo(ev, 0);
        }
    }


    function pmsCustomerDialogController($scope, $mdDialog, pmscustomercommonobject, sessionobject, fullname) {
        $scope.sessionobject = sessionobject;

        pmscustomercommonobject.pmscustomer = pmscustomercommonobject.createObject();

        $scope.pmscustomer = pmscustomercommonobject.pmscustomer;

        $scope.checkExist = checkExist;
        function checkExist(col, val, id, myForm) {
            pmscustomercommonobject.checkExist(col, val, id, myForm, $scope);
        }

        $scope.pmscustomercommonobject = pmscustomercommonobject;
        $scope.pmscustomer = pmscustomercommonobject.pmscustomer;
        $scope.pmscustomer.fullname = fullname;
        $scope.hide = function () {
            $mdDialog.hide(pmscustomercommonobject.pmscustomer);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        for (var i = 0; i < sessionobject.comboboxlist.customertypeidlist.length; i++) {
            if (sessionobject.comboboxlist.customertypeidlist[i].text === 'Owner') {
                pmscustomercommonobject.pmscustomer.selectcustomertypeid = sessionobject.comboboxlist.customertypeidlist[i].id;
            }
        }
    }



    function pmsCustomerDialoglOCALController($scope, $mdDialog, pmscustomercommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        if (pmscustomercommonobject.pmscustomer === undefined) {
            pmscustomercommonobject.pmscustomer = pmscustomercommonobject.createObject();
        }
        $scope.pmscustomer = pmscustomercommonobject.pmscustomer;

        $scope.checkExist = checkExist;
        function checkExist(col, val, id, myForm) {
            pmscustomercommonobject.checkExist(col, val, id, myForm, $scope);
        }

        $scope.pmscustomercommonobject = pmscustomercommonobject;
        $scope.pmscustomer = pmscustomercommonobject.pmscustomer;

        $scope.hide = function () {
            $mdDialog.hide(pmscustomercommonobject.pmscustomer);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsCustomerViewDialogController($scope, $mdDialog, pmscustomercommonobject, sessionobject, customerobj) {
        $scope.customerobj = customerobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function CustomerTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmscustomercommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmscustomercommonobject = pmscustomercommonobject;

        pmscustomercommonobject.scope = $scope;
        var vm = this;
        pmscustomercommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmscustomer = {
        };
        $scope.pmscustomer = vm.pmscustomer;
        $scope.pmscustomer.selectlistbox = [];
        $scope.pmscustomer.selectlistbox.customertypeid = true;
        $scope.pmscustomer.selectlistbox.customerlocalityid = true;
        $scope.pmscustomer.selectamountform = 1000;
        $scope.pmscustomer.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-customerid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            customerid: 'MENU.customer.GRID.customerid',
            fullname: 'MENU.customer.GRID.fullname',
            customertypeid: 'MENU.customer.GRID.customertypeid',
            occupationid: 'MENU.customer.GRID.occupationid',
            dateofbirth: 'MENU.customer.GRID.dateofbirth',
            genderid: 'MENU.customer.GRID.genderid',
            email: 'MENU.customer.GRID.email',
            mobileno: 'MENU.customer.GRID.mobileno',
            landlineno: 'MENU.customer.GRID.landlineno',
            address: 'MENU.customer.GRID.address',
            cityid: 'MENU.customer.GRID.cityid',
            pincode: 'MENU.customer.GRID.pincode',
            active: 'MENU.customer.GRID.active',
            createddate: 'MENU.customer.GRID.createddate',
            modifyddate: 'MENU.customer.GRID.modifyddate',
            userid: 'MENU.customer.GRID.userid',
            recordorder: 'MENU.customer.GRID.recordorder'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmscustomercommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            if (vm.pmscustomer.selectlistbox.customercustomertypeid) {
                formData.filter += "customertypeid.customertypeid|" + angular.toJson(vm.pmscustomer.selectcustomercustomertypeidtbl) + ";";
            }
            if (vm.pmscustomer.selectlistbox.customeroccupationid) {
                formData.filter += "occupationid.occupationid|" + angular.toJson(vm.pmscustomer.selectcustomeroccupationidtbl) + ";";
            }
            if (vm.pmscustomer.selectlistbox.customercityid) {
                formData.filter += "cityid.cityid|" + angular.toJson(vm.pmscustomer.selectcustomercityidtbl) + ";";
            }
            vm.promise = sessionobject.getTable($scope, vm, "customer", pmscustomercommonobject, formData);
        }
        function upload($files) {
            pmscustomercommonobject.files = $files;
        }
        vm.object = pmscustomercommonobject;

        sessionobject.activate(vm, $scope, pmscustomercommonobject);

    }
    function pmscustomercommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmscustomercommonobject = this;
        pmscustomercommonobject.role = 'Customer';
        pmscustomercommonobject.createObject = function () {
            return  {
                customerid: 0,
                fullname: '',
                selectcustomertypeid: 0,
                selectoccupationid: 0,
                dateofbirth: new Date(),
                selectgenderid: 0,
                email: '',
                mobileno: '',
                landlineno: '',
                address: '',
                selectcityid: 1,
                pincode: '',
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                selectuserid: 0,
                recordorder: 0
            };

        };


        pmscustomercommonobject.checkExist = checkExist;
        function checkExist(col, val, id, myForm, $scope) {

            $http.get(sessionobject.getURL("/checkExistCustomer.json") + "&col=" + col + "&val=" + val + "&id=" + id)
                    .success(function (response) {


                        if (col === 'email') {
                            pmscustomercommonobject.checkExistCustomer = response;
                        }
                        if (col === 'fullname') {
                            pmscustomercommonobject.checkExistCustomerFName = response;
                        }
                        if (col === 'mobileno') {
                            var form = $scope['customerform'][col];
                            form.$setValidity("check", !response);

                            pmscustomercommonobject.checkExistCustomerMobile = response;
                        }
                    }).
                    error(function (response) {
                        sessionobject.showERROR(response);

                    });
        }


        pmscustomercommonobject.showCustomer = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmscustomercommonobject.createObject();
            obj1.customerid = obj.customerid;

            $mdDialog.show({
                templateUrl: 'app/pms/customer/CustomerView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCustomerViewDialogController',
                locals: {
                    customerobj: obj
                },
            }).then(function () {
                // pmscustomercommonobject.save(ev, id);
            });
        };


        pmscustomercommonobject.showDiloag = function (ev, id) {
            //    pmscustomercommonobject.pmscustomer.createddate = $filter('date')(pmscustomercommonobject.pmscustomer.createddate, "medium");//"{{pmscustomercommonobject.pmscustomer.createddate | date:'medium'}}";
            if (id !== 0) {
                pmscustomercommonobject.pmscustomer.dateofbirth = new Date(pmscustomercommonobject.pmscustomer.dateofbirth);

            } else {
                for (var i = 0; i < sessionobject.comboboxlist.customertypeidlist.length; i++) {
                    if (sessionobject.comboboxlist.customertypeidlist[i].text === 'Owner') {
                        pmscustomercommonobject.pmscustomer.selectcustomertypeid = sessionobject.comboboxlist.customertypeidlist[i].id;
                    }
                }
            }
            $mdDialog.show({
                templateUrl: 'app/pms/customer/CustomerAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCustomerDialoglOCALController'
            }).then(function (answer) {
                pmscustomercommonobject.save(ev, id);
            });
        };

        pmscustomercommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmscustomercommonobject, "Customer", pmscustomercommonobject.pmscustomer);

            });
        };

        pmscustomercommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmscustomercommonobject, $mdDialog, triLoaderService, "customer", "tabletitle");
        };
        pmscustomercommonobject.editTodo = function (ev) {
            if (pmscustomercommonobject.tableobject.selected.length >= 1) {
                pmscustomercommonobject.addTodo(ev, pmscustomercommonobject.tableobject.selected[pmscustomercommonobject.tableobject.selected.length - 1].customerid);
            }
        };
        pmscustomercommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmscustomercommonobject, "customer", "pmscustomer");
        };
    }
})();
