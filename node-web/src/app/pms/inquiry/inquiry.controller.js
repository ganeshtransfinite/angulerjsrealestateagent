
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsInquiryController', pmsInquiryController)

            .controller('InquiryTableController', InquiryTableController)

            .controller('pmsInquiryDialogController', pmsInquiryDialogController)
            .controller('pmsInquiryViewDialogController', pmsInquiryViewDialogController)
            .service("pmsinquirycommonobject", pmsinquirycommonobject);

    function pmsInquiryController($scope, pmsinquirycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsinquirycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsinquirycommonobject.type = 1;
    }
    function init($scope, pmsinquirycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmsinquirycommonobject.addTodo(ev, 0);
        };
    }
    function pmsInquiryDialogController($scope, $mdDialog, pmsinquirycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsinquiry = pmsinquirycommonobject.pmsinquiry;
        $scope.pmsinquirycommonobject = pmsinquirycommonobject;
        $scope.pmsinquiry = pmsinquirycommonobject.pmsinquiry;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    }
    function pmsInquiryViewDialogController($scope, $mdDialog, pmsinquirycommonobject, sessionobject, inquiryobj) {
        $scope.inquiryobj = inquiryobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function InquiryTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $mdDialog, $filter, sessionobject, pmsinquirycommonobject, $state) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsinquirycommonobject = pmsinquirycommonobject;
        var vm = this;
        pmsinquirycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsinquiry = {
        };
        $scope.pmsinquiry = vm.pmsinquiry;
        $scope.pmsinquiry.selectlistbox = [];
        $scope.pmsinquiry.selectlistbox.inquirytypeid = true;
        $scope.pmsinquiry.selectlistbox.inquirylocalityid = true;
        $scope.pmsinquiry.selectamountform = 1000;
        $scope.pmsinquiry.selectamountto = 100000000;
        vm.query = {
            filter: '',
            limit: '50',
            order: '-fromperiod',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            inquiryid: 'MENU.inquiry.GRID.inquiryid',
            dateofinquiry: 'MENU.inquiry.GRID.dateofinquiry',
            name: 'MENU.inquiry.GRID.name',
            mobileno: 'MENU.inquiry.GRID.mobileno',
            email: 'MENU.inquiry.GRID.email',
            budget: 'MENU.inquiry.GRID.budget',
            fromperiod: 'MENU.inquiry.GRID.fromperiod',
            propertypriorityid: 'MENU.inquiry.GRID.propertypriorityid',
            propertylbedroomsid: 'MENU.inquiry.GRID.propertylbedroomsid',
            cityid: 'MENU.inquiry.GRID.cityid',
            propertylocalityid: 'MENU.inquiry.GRID.propertylocalityid',
            propertyfacingid: 'MENU.inquiry.GRID.propertyfacingid',
            userid: 'MENU.inquiry.GRID.userid',
            active: 'MENU.inquiry.GRID.active',
            createddate: 'MENU.inquiry.GRID.createddate',
            modifyddate: 'MENU.inquiry.GRID.modifyddate',
            recordorder: 'MENU.inquiry.GRID.recordorder',
            propertytypeid: 'MENU.property.GRID.propertytypeid'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;

        vm.showInquiry1 = function (ev, window) {
            if (pmsinquirycommonobject.tableobject.selected.length >= 1) {
                var obj = pmsinquirycommonobject.tableobject.selected[pmsinquirycommonobject.tableobject.selected.length - 1];
                $localStorage["inquiryform"] = obj;
                if (window == 1)
                    $state.go('triangular.forms-PropertyRent')
                else
                    $state.go('triangular.forms-PropertySale')
            }
        }
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsinquirycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            if (vm.pmsinquiry.selectlistbox.inquirypropertypriorityid) {
                formData.filter += sessionobject.chkData("propertypriorityid.propertypriorityid|" + angular.toJson(vm.pmsinquiry.selectinquirypropertypriorityidtbl) + ";");
            }
            if (vm.pmsinquiry.selectlistbox.inquirypropertylbedroomsid) {
                formData.filter += sessionobject.chkData("propertylbedroomsid.propertylbedroomsid|" + angular.toJson(vm.pmsinquiry.selectinquirypropertylbedroomsidtbl) + ";");
            }
            if (vm.pmsinquiry.selectlistbox.inquirycityid) {
                formData.filter += sessionobject.chkData("cityid.cityid|" + angular.toJson(vm.pmsinquiry.selectinquirycityidtbl) + ";");
            }
            if (vm.pmsinquiry.selectlistbox.inquirypropertylocalityid) {
                formData.filter += sessionobject.chkData("propertylocalityid.propertylocalityid|" + angular.toJson(vm.pmsinquiry.selectinquirypropertylocalityidtbl) + ";");
            }
            if (vm.pmsinquiry.selectlistbox.inquirypropertyfacingid) {
                formData.filter += sessionobject.chkData("propertyfacingid.propertyfacingid|" + angular.toJson(vm.pmsinquiry.selectinquirypropertyfacingidtbl) + ";");
            }
                if (vm.pmsinquiry.selectlistbox.inquirypropertytobeid) {
                formData.filter += "propertytobeid.propertytobeid|" + angular.toJson(vm.pmsinquiry.selectinquirypropertytobeidtbl) + ";";
            }
            if (angular.isDefined(vm.pmsinquiry.selectlistbox) && vm.pmsinquiry.selectlistbox.propertyavailablefrom) {
                formData.filter += "fromperiod|" + $filter('date')(vm.pmsinquiry.availablefromdate_from, "MMM d, yyyy") + '-To-' + $filter('date')(vm.pmsinquiry.availablefromdate_to, "MMM d, yyyy") + ";";
            }
            if (angular.isDefined(vm.pmsinquiry.selectlistbox) && vm.pmsinquiry.selectlistbox.inquirybudget) {
                formData.filter += "budget|" + vm.pmsinquiry.amountform + '-' + vm.pmsinquiry.amountto + ";";
            }
            vm.promise = sessionobject.getTable($scope, vm, "inquiry", pmsinquirycommonobject, formData);
        }
        function upload($files) {
            pmsinquirycommonobject.files = $files;
        }
        vm.object = pmsinquirycommonobject;


        sessionobject.activate(vm, $scope, pmsinquirycommonobject);


        $scope.addeditform = false;
        // triLayout.layout.sideMenuSize = 'icon';


        pmsinquirycommonobject.mdDialog = $mdDialog;
        $scope.hide = function (id) {
            $scope.addeditform = false;
            //     alert(vm.selected);

            pmsinquirycommonobject.tableobject.selected = [];
            pmsinquirycommonobject.save(null, id);
        };
        $scope.cancel = function () {
            pmsinquirycommonobject.tableobject.selected = [];
            $scope.addeditform = false;
        };
        vm.showDilog = function () {
            $scope.addeditform = true;
            $scope.sessionobject = sessionobject;
            pmsinquirycommonobject.pmsinquiry.selectlistbox = $scope.pmsinquiry.selectlistbox;
            $scope.pmsinquiry = pmsinquirycommonobject.pmsinquiry;
            $scope.pmsinquirycommonobject = pmsinquirycommonobject;
            $scope.pmsinquiry = pmsinquirycommonobject.pmsinquiry;


        };

    }
    function pmsinquirycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsinquirycommonobject = this;
        pmsinquirycommonobject.role = 'Inquiry';
        pmsinquirycommonobject.createObject = function () {
            return  {
                inquiryid: 0,
                dateofinquiry: new Date(),
                name: '',
                mobileno: '',
                email: '',
                budget: null,
                budget1: "",
                budget2: "",
                closeflag: false,
                closeby: "",
                excutive: "",
                 
                budget3: "",
                budget4: "",
                budget5: "",
                budget6: "",
                budget7: "",
                budget8: "",
                budget9: "",
                fromperiod: new Date(),
                selectpropertypriorityid: [],
                selectpropertylbedroomsid: [],
                selectcityid: 1,
                selectpropertylocalityid: [],
                selectpropertytobeid: [],
                selectpropertyfacingid: 0,
                selectuserid: 0,
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                nextdate: new Date(),
                recordorder: 0
            };
        };





        pmsinquirycommonobject.showInquiry = function (ev) {
            var obj = [];
            var obj1 = pmsinquirycommonobject.createObject();
            obj1.inquiryid = obj.inquiryid;
            if (pmsinquirycommonobject.tableobject.selected.length >= 1) {
                obj = pmsinquirycommonobject.tableobject.selected[pmsinquirycommonobject.tableobject.selected.length - 1];

            }
            $mdDialog.show({
                templateUrl: 'app/pms/inquiry/InquiryView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsInquiryViewDialogController',
                locals: {
                    inquiryobj: obj
                },
            }).then(function () {
                // pmsinquirycommonobject.save(ev, id);
            });
        }


        pmsinquirycommonobject.showDiloag = function (ev, id) {
            //    pmsinquirycommonobject.pmsinquiry.createddate = $filter('date')(pmsinquirycommonobject.pmsinquiry.createddate, "medium");//"{{pmsinquirycommonobject.pmsinquiry.createddate | date:'medium'}}";
            if (id !== 0) {
                pmsinquirycommonobject.pmsinquiry.dateofinquiry = new Date(pmsinquirycommonobject.pmsinquiry.dateofinquiry);
                pmsinquirycommonobject.pmsinquiry.nextdate = new Date(pmsinquirycommonobject.pmsinquiry.nextdate);
                pmsinquirycommonobject.pmsinquiry.fromperiod = new Date(pmsinquirycommonobject.pmsinquiry.fromperiod);
                pmsinquirycommonobject.pmsinquiry.budget = sessionobject.formatNumber(pmsinquirycommonobject.pmsinquiry.budget);

            }
            pmsinquirycommonobject.tableobject.showDilog(id);
//            $mdDialog.show({
//                templateUrl: 'app/pms/inquiry/InquiryAddEdit.tmpl.html',
//                targetEvent: ev,
//                controller: 'pmsInquiryDialogController'
//            }).then(function (answer) {
//                pmsinquirycommonobject.save(ev, id);
//            });
        };

        pmsinquirycommonobject.save = function (ev, id)
        {
            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);


            $q.all(requestPromise).then(function (data) {
                var value = pmsinquirycommonobject.pmsinquiry.budget;
                if (value !== null
                        && value.length >= 1) {
                    pmsinquirycommonobject.pmsinquiry.budget = parseFloat(value.toString().replace(/,/g, ''));
                }

                sessionobject.save(ev, id, triLoaderService, pmsinquirycommonobject, "Inquiry", pmsinquirycommonobject.pmsinquiry);

            });
        };

        pmsinquirycommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmsinquirycommonobject, $mdDialog, triLoaderService, "inquiry", "tabletitle");
        };
        pmsinquirycommonobject.editTodo = function (ev) {
            if (pmsinquirycommonobject.tableobject.selected.length >= 1) {
                pmsinquirycommonobject.addTodo(ev, pmsinquirycommonobject.tableobject.selected[pmsinquirycommonobject.tableobject.selected.length - 1].inquiryid);
            }
        };
        pmsinquirycommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmsinquirycommonobject, "inquiry", "pmsinquiry");
        };
        pmsinquirycommonobject.getColor = function (type, fromdate) {
            var now = moment(new Date());
            var then = moment(new Date(fromdate));
            var days1 = moment.duration(now.diff(then));
            var days = days1._data.days + days1._data.months * 30 + days1._data.years * 365;
            var color = "red:";

            if (type === 'Rent') {
                if (days < 15) {
                    color += "50";
                } else if (days < 30) {
                    color += "100";
                } else if (days < 45) {
                    color += "200";
                }

            } else {

                if (days < 60) {
                    color += "50";
                } else if (days < 120) {
                    color += "100";
                } else if (days < 180) {
                    color += "200";
                }


            }
            return color;

        };

        pmsinquirycommonobject.getDiff = function (fromdate) {
            var now = moment(new Date());
            var then = moment(new Date(fromdate));
            var days1 = moment.duration(now.diff(then));
            var days = days1._data.days + days1._data.months * 30 + days1._data.years * 365;


            return days;

        };

        pmsinquirycommonobject.chengetext = function (current, obj) {
            var budget = "";
            for (var i = 9; i > 0; i--) {
                if (obj["budget" + i].length > 1) {
                    obj["budget" + i] = obj["budget" + i].substring(obj["budget" + i].length - 1, obj["budget" + i].length);
                }
                budget += obj["budget" + i];
            }
            obj.budget = budget;
            if (obj["budget" + current].length >= 1) {
                if (current !== 1) {
                    document.getElementById("budget" + (current - 1)).focus();
                }
            }
        };
    }

})();
