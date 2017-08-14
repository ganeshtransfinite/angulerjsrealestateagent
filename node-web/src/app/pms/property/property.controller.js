(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsPropertyRentController', pmsPropertyRentController)
            .controller('pmsPropertySaleController', pmsPropertySaleController)
            .controller('PropertyTableController', PropertyTableController)
            .controller('PrppertyGalleryDialogController', PrppertyGalleryDialogController)
            .controller('pmsPropertyDialogController', pmsPropertyDialogController)
            .controller('pmsPropertyViewDialogController', pmsPropertyViewDialogController)
            .controller('pmsPropertyExcelViewDialogController', pmsPropertyExcelViewDialogController)

            .service("pmspropertycommonobject", pmspropertycommonobject);
    function PrppertyGalleryDialogController(pmspropertycommonobject) {
        var vm = this;
        pmspropertycommonobject.imagesvm = vm;
        vm.showprogressbar = true;
    }
    function pmsPropertyExcelViewDialogController($scope, $mdDialog, pmspropertytypecommonobject, sessionobject) {

        $scope.hide = function () {
            $mdDialog.hide(pmspropertycommonobject.selectimage11[0]);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.uploadimage11 = uploadimage11;
        function uploadimage11($files) {
            pmspropertycommonobject.selectimage11 = $files;
        }
    }
    function openimage(vm, images) {
        vm.showprogressbar = false;
        vm.currentImage = images[0];
        vm.next = next;
        vm.prev = prev;
        function next() {
            var index = images.indexOf(vm.currentImage);
            index = index + 1 < images.length ? index + 1 : 0;
            vm.currentImage = images[index];
        }

        function prev() {
            var index = images.indexOf(vm.currentImage);
            index = index - 1 < 0 ? images.length - 1 : index - 1;
            vm.currentImage = images[index];
        }
    }

    function pmsPropertyRentController($scope, pmspropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {

        init($scope, pmspropertycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertycommonobject.type = 1;
    }

    function init($scope, pmspropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pms/flight");
            pmspropertycommonobject.addTodo(ev, 0);
        };

    }
    function pmsPropertySaleController($scope, pmspropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmspropertycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmspropertycommonobject.type = 2;
    }

    function pmsPropertyDialogController($scope, $mdDialog, pmspropertycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsproperty = pmspropertycommonobject.pmsproperty;
        $scope.pmspropertycommonobject = pmspropertycommonobject;
        $scope.pmsproperty = pmspropertycommonobject.pmsproperty;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
    }

    function pmsPropertyViewDialogController($scope, $mdDialog, pmspropertycommonobject, sessionobject, propertyobj) {
        $scope.propertyobj = propertyobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertyTableController($q, triLayout, $mdDialog, triLoaderService, $scope, $http, $localStorage, $state, $timeout, $mdSidenav, $filter, sessionobject, pmspropertycommonobject) {
//        

        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmspropertycommonobject = pmspropertycommonobject;
        var vm = this;
        pmspropertycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsproperty = {
        };
        $scope.pmsproperty = vm.pmsproperty;
        $scope.pmsproperty.selectlistbox = [];
        $scope.pmsproperty.selectlistbox.propertytypeid = true;
        $scope.pmsproperty.selectlistbox.propertylocalityid = true;
        $scope.pmsproperty.selectlistbox.propertylocalityid = true;
//        $scope.pmsproperty.selectamountform = 1000;
//        $scope.pmsproperty.selectamountto = 100000000;
        if (pmspropertycommonobject.type === 1) {
            $scope.pmsproperty.amountform = 1000;
            $scope.pmsproperty.amountto = 20000;
        } else {
            $scope.pmsproperty.amountform = 100000;
            $scope.pmsproperty.amountto = 50000000;
        }
        vm.query = {
            filter: '',
            limit: '50',
            order: '-propertyid',
            limitoption: [50, 150, 200, 400, 800, 2000, 4000],
            page: 1
        };
        vm.selected = [];
        vm.columns = {
            propertyid: 'MENU.property.GRID.propertyid',
            propertytypeid: 'MENU.property.GRID.propertytypeid',
            customerid: 'MENU.property.GRID.customerid',
            totalarea: 'MENU.property.GRID.totalarea',
            amount: 'MENU.property.GRID.amount',
            propertypriorityid: 'MENU.property.GRID.propertypriorityid',
            flatno: 'MENU.property.GRID.flatno',
            apt: 'MENU.property.GRID.apt',
            address: 'MENU.property.GRID.address',
            roadname: 'MENU.property.GRID.roadname',
            cityid: 'MENU.property.GRID.cityid',
            propertylocalityid: 'MENU.property.GRID.propertylocalityid',
            landmark: 'MENU.property.GRID.landmark',
            propertyage: 'MENU.property.GRID.propertyage',
            leaseperiod: 'MENU.property.GRID.leaseperiod',
            propertylbedroomsid: 'MENU.property.GRID.propertylbedroomsid',
            floorno: 'MENU.property.GRID.floorno',
            propertyfacingid: 'MENU.property.GRID.propertyfacingid',
            propertyavailibilityid: 'MENU.property.GRID.propertyavailibilityid',
            propertyfurnishedid: 'MENU.property.GRID.propertyfurnishedid',
            image1: 'MENU.property.GRID.image1',
            image2: 'MENU.property.GRID.image2',
            image3: 'MENU.property.GRID.image3',
            image4: 'MENU.property.GRID.image4',
            image5: 'MENU.property.GRID.image5',
            remark: 'MENU.property.GRID.remark',
            userid: 'MENU.property.GRID.userid',
            active: 'MENU.property.GRID.active',
            createddate: 'MENU.property.GRID.createddate',
            modifyddate: 'MENU.property.GRID.modifyddate',
            deposit: 'MENU.property.GRID.deposit',
            sqfeetrate: 'MENU.property.GRID.sqfeetrate',
            bookingcharges: 'MENU.property.GRID.bookingcharges',
            image6: 'MENU.property.GRID.image6',
            image7: 'MENU.property.GRID.image7',
            image8: 'MENU.property.GRID.image8',
            image9: 'MENU.property.GRID.image9',
            image10: 'MENU.property.GRID.image10',
            postedon: 'MENU.property.GRID.postedon',
            propertytobeid: 'MENU.property.GRID.propertytobeid',
            availablefrom: 'MENU.property.GRID.availablefrom',
            propertykeyid: 'MENU.property.GRID.propertykeyid',
            status: 'MENU.property.GRID.status'
        };
        $scope.contents = [];
        vm.upload = upload;
        vm.getTable = getTable;
        var inqueryform = $localStorage["inquiryform"];
        var flaginquiry = true;
        if (inqueryform !== undefined) {
            flaginquiry = false;
        }
        pmspropertycommonobject.dateAdd($scope.pmsproperty, -30);
        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmspropertycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";
            // if (vm.pmsproperty.selectlistbox.propertytobeid) {
            formData.filter += "propertytobeid.propertytobeid|" + angular.toJson(pmspropertycommonobject.type) + ";"; //""

            formData.filter += "status|false;";
            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.aptselect) {
                formData.filter += "apt|" + angular.toJson(vm.pmsproperty.selectaptselecttbl) + ";";
            }

            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertycustomerid) {
                formData.filter += "customerid.customerid|" + vm.pmsproperty.selectcustomeridtbl + ";";
            }
            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertyamount) {
                formData.filter += "amount|" + vm.pmsproperty.amountform + '-' + vm.pmsproperty.amountto + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertypropertytypeid) {
                formData.filter += sessionobject.chkData("propertytypeid.propertytypeid|" + angular.toJson(vm.pmsproperty.selectpropertypropertytypeidtbl) + ";");
            }
            if (vm.pmsproperty.selectlistbox.propertypropertypriorityid) {
                formData.filter += sessionobject.chkData("propertypriorityid.propertypriorityid|" + angular.toJson(vm.pmsproperty.selectpropertypropertypriorityidtbl) + ";");
            }
            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertyapt) {
                formData.filter += "apt|" + vm.pmsproperty.apttbl + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertycityid) {
                formData.filter += sessionobject.chkData("cityid.cityid|" + angular.toJson(vm.pmsproperty.selectpropertycityidtbl) + ";");
            }
            if (vm.pmsproperty.selectlistbox.propertypropertylocalityid) {
                formData.filter += sessionobject.chkData("propertylocalityid.propertylocalityid|" + angular.toJson(vm.pmsproperty.selectpropertypropertylocalityidtbl) + ";");
            }
            if (vm.pmsproperty.selectlistbox.propertypropertylbedroomsid) {
                formData.filter += sessionobject.chkData("propertylbedroomsid.propertylbedroomsid|" + angular.toJson(vm.pmsproperty.selectpropertypropertylbedroomsidtbl) + ";");
            }
            if (vm.pmsproperty.selectlistbox.propertypropertyfacingid) {
                formData.filter += sessionobject.chkData("propertyfacingid.propertyfacingid|" + angular.toJson(vm.pmsproperty.selectpropertypropertyfacingidtbl) + ";");
            }
            if (vm.pmsproperty.selectlistbox.propertypropertyfurnishedid) {
                formData.filter += sessionobject.chkData("propertyfurnishedid.propertyfurnishedid|" + angular.toJson(vm.pmsproperty.selectpropertypropertyfurnishedidtbl) + ";");
            }

            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertyavailablefrom) {
                formData.filter += "availablefrom|" + $filter('date')(vm.pmsproperty.availablefromdate_from, "MMM d, yyyy") + '-To-' + $filter('date')(vm.pmsproperty.availablefromdate_to, "MMM d, yyyy") + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertyflatamenitiesid) {
                formData.filter += sessionobject.chkData("flatamenitiesid.flatamenitiesid|" + angular.toJson(vm.pmsproperty.selectpropertyflatamenitiesidtbl) + ";");
            }

            if (vm.pmsproperty.selectlistbox.propertysocietyamenitiesid) {
                formData.filter += sessionobject.chkData("societyamenitiesid.societyamenitiesid|" + angular.toJson(vm.pmsproperty.selectpropertysocietyamenitiesidtbl) + ";");
            }

            $timeout(function () {
                if (flaginquiry) {
                    vm.promise = sessionobject.getTable($scope, vm, "property", pmspropertycommonobject, formData);
                }
                flaginquiry = true;
            }, 100);
        }
        function upload($files) {
            pmspropertycommonobject.files = $files;
        }
        vm.object = pmspropertycommonobject;
        sessionobject.initComboCountInitALL("Property", pmspropertycommonobject.type, true);
        sessionobject.activate(vm, $scope, pmspropertycommonobject);
        //    vm.fillCombo('propertytypeid', vm);
        //  vm.fillCombo('propertylocalityid', vm);
        $timeout(function () {

            if (inqueryform !== undefined) {
                var list = (pmspropertycommonobject.type === 1 ? sessionobject.comboboxlist.budgetrentidlist : sessionobject.comboboxlist.budgetsaleidlist);
                var firstindex = 0, secondindex = 0;
                var budeget1 = inqueryform.budget - (inqueryform.budget * 0.20);
                var budeget2 = inqueryform.budget + (inqueryform.budget * 0.20);
                for (var i = 1; i < list.length; i++) {
                    if (list[i].id > budeget1) {
                        firstindex = i - 1;
                        break;
                    }
                }

                for (var i = 1; i < list.length; i++) {
                    if (list[i].id > budeget2) {
                        secondindex = i;
                        break;
                    }
                }
                if (firstindex === secondindex) {
                    if (secondindex + 1 < list.length) {
                        secondindex = secondindex + 1;
                    }
                }
                vm.pmsproperty.selectpropertyamounttbl = {};
                vm.pmsproperty.selectlistbox.propertyamount = true;
                vm.pmsproperty.selectamountform = list[firstindex].id;
                vm.pmsproperty.selectamountto = list[secondindex].id;

                vm.pmsproperty.selectpropertypropertypriorityidtbl = {};
                if (inqueryform.propertypriorityid !== undefined && inqueryform.propertypriorityid !== null) {
                    vm.pmsproperty.selectlistbox.propertypropertypriorityid = true;
                    for (var i = 0; i < inqueryform.propertypriorityid.length; i++) {
                        vm.pmsproperty.selectpropertypropertypriorityidtbl[ inqueryform.propertypriorityid[i].propertypriorityid] = true;
                    }
                }
                if (inqueryform.propertytobeid !== undefined && inqueryform.propertytobeid !== null) {

                    vm.pmsproperty.selectpropertypropertytobeidtbl = {};
                    vm.pmsproperty.selectlistbox.propertypropertytobeid = true;
                    for (var i = 0; i < inqueryform.propertytobeid.length; i++) {
                        vm.pmsproperty.selectpropertypropertytobeidtbl[ inqueryform.propertytobeid[i].propertytobeid] = true;
                    }

                }
//
//                vm.pmsproperty.selectpropertypropertypriorityidtbl = [];
//                vm.pmsproperty.selectlistbox.propertypropertypriorityid = true;
//                vm.pmsproperty.selectpropertypropertypriorityidtbl[ inqueryform.propertypriorityid.propertypriorityid] = true;

                vm.pmsproperty.selectpropertypropertylbedroomsidtbl = {};
                if (inqueryform.propertylbedroomsid !== undefined && inqueryform.propertylbedroomsid !== null) {

                    vm.pmsproperty.selectlistbox.propertypropertylbedroomsid = true;
                    for (var i = 0; i < inqueryform.propertylbedroomsid.length; i++) {
                        vm.pmsproperty.selectpropertypropertylbedroomsidtbl[ inqueryform.propertylbedroomsid[i].propertylbedroomsid] = true;
                    }
                }
//            vm.pmsproperty.selectpropertypropertylbedroomsidtbl[ inqueryform.propertylbedroomsid.propertylbedroomsid] = true;

//                vm.pmsproperty.selectpropertycityidtbl = {};
//                vm.pmsproperty.selectlistbox.propertycityid = true;
//
//                vm.pmsproperty.selectpropertycityidtbl[ inqueryform.cityid.cityid] = true;

                vm.pmsproperty.selectpropertypropertylocalityidtbl = {};
                if (inqueryform.propertylocalityid !== undefined && inqueryform.propertylocalityid !== null) {

                    vm.pmsproperty.selectlistbox.propertypropertylocalityid = true;
                    for (var i = 0; i < inqueryform.propertylocalityid.length; i++) {
                        vm.pmsproperty.selectpropertypropertylocalityidtbl[ inqueryform.propertylocalityid[i].propertylocalityid] = true;
                    }
                }
                //    vm.pmsproperty.selectpropertypropertylocalityidtbl[ inqueryform.propertylocalityid.propertylocalityid] = true;

                vm.pmsproperty.selectpropertypropertyfacingidtbl = {};

                if (inqueryform.propertyfacingid !== undefined && inqueryform.propertyfacingid !== null) {
                    vm.pmsproperty.selectlistbox.propertypropertyfacingid = true;
                    vm.pmsproperty.selectpropertypropertyfacingidtbl[ inqueryform.propertyfacingid.propertyfacingid] = true;
                }
                //  vm.pmsproperty.selectpropertypropertyfurnishedidtbl =  {};
                vm.pmsproperty.selectlistbox.propertypropertyfurnishedid = false;
                vm.pmsproperty.selectlistbox.propertypropertytypeid = false;
                $localStorage["inquiryform"] = undefined;
                //  vm.pmsproperty.selectpropertypropertyfurnishedidtbl[ inqueryform.propertyfurnishedid.propertyfurnishedid] = true;
                vm.getTable();
            }
        }, 100);


//        $timeout(function () {
//
//            if (inqueryform !== undefined) {
//                var list = (pmspropertycommonobject.type === 1 ? sessionobject.comboboxlist.budgetrentidlist : sessionobject.comboboxlist.budgetsaleidlist);
//                var firstindex = 0, secondindex = 0;
//                var budeget1 = inqueryform.budget - (inqueryform.budget * 0.20);
//                var budeget2 = inqueryform.budget + (inqueryform.budget * 0.20);
//
//                vm.pmsproperty.selectpropertyamounttbl = {};
//                vm.pmsproperty.selectlistbox.propertyamount = true;
//                vm.pmsproperty.amountform = budeget1;
//                vm.pmsproperty.amountto = budeget2;
//                vm.pmsproperty.selectpropertypropertypriorityidtbl = {};
//                vm.pmsproperty.selectlistbox.propertypropertypriorityid = true;
//                for (var i = 0; i < inqueryform.propertypriorityid.length; i++) {
//                    vm.pmsproperty.selectpropertypropertypriorityidtbl[ inqueryform.propertypriorityid[i].propertypriorityid] = true;
//                }
//
//                vm.pmsproperty.selectpropertypropertytobeidtbl = {};
//                vm.pmsproperty.selectlistbox.propertypropertytobeid = true;
//                for (var i = 0; i < inqueryform.propertytobeid.length; i++) {
//                    vm.pmsproperty.selectpropertypropertytobeidtbl[ inqueryform.propertytobeid[i].propertytobeid] = true;
//                }
//
//                vm.pmsproperty.selectpropertypropertylbedroomsidtbl = {};
//                vm.pmsproperty.selectlistbox.propertypropertylbedroomsid = true;
//                for (var i = 0; i < inqueryform.propertylbedroomsid.length; i++) {
//                    vm.pmsproperty.selectpropertypropertylbedroomsidtbl[ inqueryform.propertylbedroomsid[i].propertylbedroomsid] = true;
//                }
//
//                vm.pmsproperty.selectpropertypropertylocalityidtbl = {};
//                vm.pmsproperty.selectlistbox.propertypropertylocalityid = true;
//                for (var i = 0; i < inqueryform.propertylocalityid.length; i++) {
//                    vm.pmsproperty.selectpropertypropertylocalityidtbl[ inqueryform.propertylocalityid[i].propertylocalityid] = true;
//                }
//                //    vm.pmsproperty.selectpropertypropertylocalityidtbl[ inqueryform.propertylocalityid.propertylocalityid] = true;
//
//                vm.pmsproperty.selectpropertypropertyfacingidtbl = {};
//                vm.pmsproperty.selectlistbox.propertypropertyfacingid = true;
//                vm.pmsproperty.selectpropertypropertyfacingidtbl[ inqueryform.propertyfacingid.propertyfacingid] = true;
//                //  vm.pmsproperty.selectpropertypropertyfurnishedidtbl =  {};
//                vm.pmsproperty.selectlistbox.propertypropertyfurnishedid = false;
//                vm.pmsproperty.selectlistbox.propertypropertytypeid = false;
//                $localStorage["inquiryform"] = undefined;
//                //  vm.pmsproperty.selectpropertypropertyfurnishedidtbl[ inqueryform.propertyfurnishedid.propertyfurnishedid] = true;
//                vm.getTable();
//            }
//        }, 100);
        vm.refreshCount = function () {

            var requestPromise = [];
            sessionobject.initComboCountALL("Property", false, requestPromise);
            triLoaderService.setLoaderActive(true);
            $q.all(requestPromise).then(function (data) {
                triLoaderService.setLoaderActive(false);
                $state.reload();
            });
        };
        $scope.addeditform = false;
        // triLayout.layout.sideMenuSize = 'icon';


        pmspropertycommonobject.mdDialog = $mdDialog;
        $scope.hide = function (id) {
            var res = sessionobject.comboboxlist.customeridlist.filter(pmspropertycommonobject.createFilterForText(pmspropertycommonobject.pmsproperty.customeridtxt));
            if (angular.isDefined(res) && res.length === 0) {
                pmspropertycommonobject.showCustomerDiloag(null, pmspropertycommonobject.pmsproperty.customeridtxt, pmspropertycommonobject.pmsproperty.proprtyid)
            } else {
                $scope.addeditform = false;
                //     alert(vm.selected);

                pmspropertycommonobject.tableobject.selected = [];
                pmspropertycommonobject.save(null, id);
            }
        };
        $scope.cancel = function () {
            pmspropertycommonobject.tableobject.selected = [];
            $scope.addeditform = false;
        };
        vm.showDilog = function () {
            $scope.addeditform = true;
            $scope.sessionobject = sessionobject;
            pmspropertycommonobject.pmsproperty.selectlistbox = $scope.pmsproperty.selectlistbox;
            $scope.pmsproperty = pmspropertycommonobject.pmsproperty;
            $scope.pmspropertycommonobject = pmspropertycommonobject;
            $scope.pmsproperty = pmspropertycommonobject.pmsproperty;
        };
//        triLayout.layout.sideMenuSize = 'icon';
        $scope.uploadimage1 = uploadimage1;
        function uploadimage1($files) {
            pmspropertycommonobject.selectimage1 = $files;
        }
        $scope.uploadimage2 = uploadimage2;
        function uploadimage2($files) {
            pmspropertycommonobject.selectimage2 = $files;
        }
        $scope.uploadimage3 = uploadimage3;
        function uploadimage3($files) {
            pmspropertycommonobject.selectimage3 = $files;
        }
        $scope.uploadimage4 = uploadimage4;
        function uploadimage4($files) {
            pmspropertycommonobject.selectimage4 = $files;
        }
        $scope.uploadimage5 = uploadimage5;
        function uploadimage5($files) {
            pmspropertycommonobject.selectimage5 = $files;
        }
        $scope.uploadimage6 = uploadimage6;
        function uploadimage6($files) {
            pmspropertycommonobject.selectimage6 = $files;
        }
        $scope.uploadimage7 = uploadimage7;
        function uploadimage7($files) {
            pmspropertycommonobject.selectimage7 = $files;
        }
        $scope.uploadimage8 = uploadimage8;
        function uploadimage8($files) {
            pmspropertycommonobject.selectimage8 = $files;
        }
        $scope.uploadimage9 = uploadimage9;
        function uploadimage9($files) {
            pmspropertycommonobject.selectimage9 = $files;
        }
        $scope.uploadimage10 = uploadimage10;
        function uploadimage10($files) {
            pmspropertycommonobject.selectimage10 = $files;
        }
        var vdata = 0;
        $scope.uploadExcel = uploadExcel;
        function uploadExcel(ev) {
            $mdDialog.show({
                templateUrl: 'app/pms/property/PropertyAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyExcelViewDialogController',

            }).then(function (file) {
                var requestPromise = [];
                var formData = new FormData();
                // alert(commonobject.selectimage9[0].name);
                formData.append("userid", $localStorage.clientloginuserid.userid);
                formData.append("file", file);
                formData.append("type", "uploadexcel");

                formData.append("fileid", 0);
                triLoaderService.setLoaderActive(true);
                var httpPromise = $http(sessionobject.getFileHttpHeader('/uploaddata', formData, "admin"))
                        .success(function (data) {
                            //sessionobject.showERROR(status);
                            if (!sessionobject.replyChk(data)) {
                                return;
                            }
                            vdata = data;
                            $mdDialog.cancel();
                            triLoaderService.setLoaderActive(false);





                        }).error(function (data, status) {
                    //sessionobject.showERROR(status);
                });
                requestPromise.push(httpPromise);
                $q.all(requestPromise).then(function (data) {
                    sessionobject.showAlert($mdDialog, "Excel Upload", vdata === 0 ? "Excel Upload Fail. " : "Total Succesfullly uploaded record : " + vdata + ".", ev);


                });
                // pmspropertytypecommonobject.save(ev, id);
            });
        }
        ;

    }

    function pmspropertycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmspropertycommonobject = this;
        pmspropertycommonobject.role = 'Property';
        pmspropertycommonobject.createObject = function () {

            pmspropertycommonobject.selectimage1 = undefined;
            pmspropertycommonobject.selectimage2 = undefined;
            pmspropertycommonobject.selectimage3 = undefined;
            pmspropertycommonobject.selectimage4 = undefined;
            pmspropertycommonobject.selectimage5 = undefined;
            pmspropertycommonobject.selectimage6 = undefined;
            pmspropertycommonobject.selectimage7 = undefined;
            pmspropertycommonobject.selectimage8 = undefined;
            pmspropertycommonobject.selectimage9 = undefined;
            pmspropertycommonobject.selectimage10 = undefined;
            return  {
                propertyid: 0,
                selectpropertytypeid: 0,
                selectcustomerid: 0,
                totalarea: null,
                amount: null,
                amountform: 1000,
                amountto: 20000,
                selectpropertypriorityid: [],
                flatno: '',
                apt: '',
                address: '',
                selectcityid: 1,
                selectpropertylocalityid: 0,
                landmark: '',
                propertyage: '',
                leaseperiod: '',
                selectpropertylbedroomsid: 0,
                floorno: '',
                selectpropertyfacingid: 0,
                selectpropertyfurnishedid: 0,
                selectimage1: 0,
                selectimage2: 0,
                selectimage3: 0,
                selectimage4: 0,
                selectimage5: 0,
                remark: '',
                selectuserid: 0,
                active: '',
                createddate: new Date(),
                modifyddate: new Date(),
                deposit: null,
                sqfeetrate: 0,
                selectimage6: 0,
                selectimage7: 0,
                selectimage8: 0,
                selectimage9: 0,
                selectimage10: 0,
                postedon: new Date(),
                selectpropertytobeid: pmspropertycommonobject.type,
                availablefrom: new Date(),
                selectflatamenitiesid: [],
                status: false,
                selectsocietyamenitiesid: [],
                recordorder: 0,
                balcony: '',
                bathroom: '',
                totalfloorno: '',
                parking: true,
                keyavailability: '',
                keyname: '',
                keymobile: '',
                keyaddress: '',
                image1: null,
                image2: null,
                image3: null,
                image4: null,
                image5: null,
                image6: null,
                image7: null,
                image8: null,
                image9: null,
                image10: null
            };
        };
        pmspropertycommonobject.openImage = openImage;
        function openImage(item, $event) {


            $mdDialog.show({
                controller: 'PrppertyGalleryDialogController',
                controllerAs: 'vm',
                templateUrl: 'app/pms/property/gallery-dialog.tmpl.html',
                clickOutsideToClose: true,
                focusOnOpen: false,
                targetEvent: $event,
                locals: {
                    pmspropertycommonobject: pmspropertycommonobject
                }
            });
            var obj = pmspropertycommonobject.createObject();
            obj.propertyid = item.propertyid;
            $http(sessionobject.getHttpHeader('/editproperty.json', obj, pmspropertycommonobject.role))
                    .success(function (data) {
                        item = data;
                        if (data.errors) {
                            // Showing errors.
                            sessionobject.showERROR(data.errors);
                        } else {
                            var images = [];
                            if (item.image1 !== null
                                    && item.image1 !== undefined) {
                                images.push({title: "1", content: item.image1.content});
                            }
                            if (item.image2 !== null
                                    && item.image2 !== undefined) {
                                images.push({title: "2", content: item.image2.content});
                            }
                            if (item.image3 !== null
                                    && item.image3 !== undefined) {
                                images.push({title: "3", content: item.image3.content});
                            }
                            if (item.image4 !== null
                                    && item.image4 !== undefined) {
                                images.push({title: "4", content: item.image4.content});
                            }
                            if (item.image5 !== null
                                    && item.image5 !== undefined) {
                                images.push({title: "5", content: item.image5.content});
                            }
                            if (item.image6 !== null
                                    && item.image6 !== undefined) {
                                images.push({title: "6", content: item.image6.content});
                            }
                            if (item.image7 !== null
                                    && item.image7 !== undefined) {
                                images.push({title: "7", content: item.image7.content});
                            }
                            if (item.image8 !== null
                                    && item.image8 !== undefined) {
                                images.push({title: "8", content: item.image8.content});
                            }
                            if (item.image9 !== null
                                    && item.image9 !== undefined) {
                                images.push({title: "9", content: item.image9.content});
                            }
                            if (item.image10 !== null
                                    && item.image10 !== undefined) {
                                images.push({title: "10", content: item.image10.content});
                            }
                            openimage(pmspropertycommonobject.imagesvm, images);
                        }
                    });
        }

        pmspropertycommonobject.dateAdd = dateAdd;
        function dateAdd(pmsproperty, availabledate_from, availabledate_to, day) {
            pmsproperty[availabledate_to] = new Date();
            pmsproperty[availabledate_to].setDate(pmsproperty[availabledate_to].getDate() + day);
            pmsproperty[availabledate_from] = new Date();
        }

        pmspropertycommonobject.clearComma = clearComma;
        function clearComma(value) {
            return   parseFloat(value.replace(/,/g, ''))

        }
        ;




        pmspropertycommonobject.simulateQuery = false;
        pmspropertycommonobject.querySearch = querySearch;
        function querySearch(query, list) {
            var results = query ? list.filter(pmspropertycommonobject.createFilterForText(query)) : list,
                    deferred;
            if (pmspropertycommonobject.simulateQuery) {
                deferred = $q.defer();
                $timeout(function () {
                    deferred.resolve(results);
                }, Math.random() * 1000, false);
                return deferred.promise;
            } else {
                return results;
            }
        }
        pmspropertycommonobject.createFilterForText =
                function (query) {
                    var lowercaseQuery = angular.lowercase(query);
                    return function filterFn(state) {
                        if (state.text === undefined || state.text === null) {
                            return false;
                        }
                        return (angular.lowercase(state.text).indexOf(lowercaseQuery) >= 0);
                    };
                }

        pmspropertycommonobject.showProperty = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmspropertycommonobject.createObject();
            obj1.propertyid = obj.propertyid;
            $http(sessionobject.getHttpHeader('/editproperty.json', obj, pmspropertycommonobject.role))
                    .success(function (data) {
                        //        item = data;
                        obj["image1"] = data["image1"];
                        obj["image2"] = data["image2"];
                        obj["image3"] = data["image3"];
                        obj["image4"] = data["image4"];
                        obj["image5"] = data["image5"];
                        obj["image6"] = data["image6"];
                        obj["image7"] = data["image7"];
                        obj["image8"] = data["image8"];
                        obj["image9"] = data["image9"];
                        obj["image10"] = data["image10"];
                        obj.showprogressbar = false;
                    });
            $mdDialog.show({
                templateUrl: 'app/pms/property/ProperttyView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyViewDialogController',
                locals: {
                    propertyobj: obj
                },
            }).then(function () {


// pmspropertycommonobject.save(ev, id);
            });
        }

        pmspropertycommonobject.pmsproperty = pmspropertycommonobject.createObject();
        pmspropertycommonobject.showCustomerDiloag = function (ev, str, id) {

            if (str === undefined || str === null) {
                str = '';
            }
            var res = sessionobject.comboboxlist.customeridlist.filter(
                    pmspropertycommonobject.createFilterForText(str));
//                if (angular.isDefined(res) && res.length === 0) {

//                    var confirm = $mdDialog.confirm()
//                            .title('Would you like to add New Customer in our Database  ')
//                            .textContent('Selectd Customer NAme ' + str + ".")
//                            .ariaLabel('Add Confirm')
//                            .targetEvent(ev)
//                            .ok('Yes')
//                            .cancel('No');
//                    $mdDialog.show(confirm).then(function () {
            $mdDialog.show({
                templateUrl: 'app/pms/customer/CustomerAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsCustomerDialogController',
                locals: {
                    fullname: str
                }
            }).then(function (answer) {
                //pmscustomercommonobject.save(ev, id);
                var a = 1;
                var httpPromise = $http(sessionobject.getHttpHeader('/saveCustomer.json', answer, pmspropertycommonobject.role))
                        .success(function (data) {
                            pmspropertycommonobject.pmsproperty.selectcustomerid = data.customerid;
                            //  pmspropertycommonobject.save(ev, id);
                            pmspropertycommonobject.pmsproperty.customeridtxt = data.fullname;

                            sessionobject.initCombo("CustomerForm", null, null, null, false);
                        });
            }, function () {

            });
//                    
//                    });
            //pmspropertycommonobject.save(ev, id);
            //pmspropertycommonobject.pmsproperty.createddate = $filter('date')(pmspropertycommonobject.pmsproperty.createddate, "medium");//"{{pmspropertycommonobject.pmsproperty.createddate | date:'medium'}}";

//                } else {
//                    alert("Owner name is alreday in database");
//                }
//            } else {
//                alert("Please Enter Owner name ");
//            }
        };

        pmspropertycommonobject.showDiloag = function (ev, id) {

            if (id !== 0) {
                pmspropertycommonobject.pmsproperty.deposit = sessionobject.formatNumber(pmspropertycommonobject.pmsproperty.deposit);
                pmspropertycommonobject.pmsproperty.sqfeetrate = sessionobject.formatNumber(pmspropertycommonobject.pmsproperty.sqfeetrate);
                pmspropertycommonobject.pmsproperty.amount = sessionobject.formatNumber(pmspropertycommonobject.pmsproperty.amount);

                pmspropertycommonobject.pmsproperty.availablefrom = moment(pmspropertycommonobject.pmsproperty.availablefrom);
                pmspropertycommonobject.pmsproperty.customeridtxt = pmspropertycommonobject.pmsproperty.customerid.fullname;
            }
            pmspropertycommonobject.tableobject.showDilog(id);
            //    pmspropertycommonobject.pmsproperty.createddate = $filter('date')(pmspropertycommonobject.pmsproperty.createddate, "medium");//"{{pmspropertycommonobject.pmsproperty.createddate | date:'medium'}}";
//            $mdDialog.show({
//                templateUrl: 'app/pms/property/PropertyAddEdit.tmpl.html',
//                targetEvent: ev,
//                controller: 'pmsPropertyDialogController',
//            }).then(function (answer) {
//                pmspropertycommonobject.showCustomerDiloag(ev, pmspropertycommonobject.pmsproperty.customeridtxt, id);
//
//            });
        };
        pmspropertycommonobject.save = function (ev, id)
        {
            triLoaderService.setLoaderActive(true);
            var value = pmspropertycommonobject.pmsproperty.deposit;
            if (value !== null
                    && value.length >= 1) {
                pmspropertycommonobject.pmsproperty.deposit = parseFloat(value.toString().replace(/,/g, ''));
            }

            value = pmspropertycommonobject.pmsproperty.sqfeetrate;
            if (value !== null
                    && value.length >= 1) {
                pmspropertycommonobject.pmsproperty.sqfeetrate = parseFloat(value.toString().replace(/,/g, ''));
            }
            value = pmspropertycommonobject.pmsproperty.amount;
            if (value !== null
                    && value.length >= 1) {
                pmspropertycommonobject.pmsproperty.amount = parseFloat(value.toString().replace(/,/g, ''));
            }

            var url = sessionobject.getHttpHeader('/duplicate.json', pmspropertycommonobject.pmsproperty, "");
            var httpPromise = $http(url)
                    .success(function (data) {
                        if (data.errors) {
                            sessionobject.showERROR(data.errors);
                        } else {

                            if (!sessionobject.replyChk(data)) {
                                return;
                            }

                            if (data) {
                                var requestPromise = [];
                                var httpPromise = null;

                                sessionobject.uploadfiletoserver("image1", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image2", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image3", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image4", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image5", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image6", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image7", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image8", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image9", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                sessionobject.uploadfiletoserver("image10", requestPromise, pmspropertycommonobject, pmspropertycommonobject.pmsproperty);
                                $q.all(requestPromise).then(function (data) {

                                    sessionobject.save(ev, id, triLoaderService, pmspropertycommonobject, "Property", pmspropertycommonobject.pmsproperty);
                                });
                            } else {
                                sessionobject.showAlert($mdDialog, "Dublicate Property", "Dublicate Mobile No,BHK,Location.", ev);
                            }
                        }
                    });


        };
        pmspropertycommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmspropertycommonobject, $mdDialog, triLoaderService, "property", "tabletitle");
        };
        pmspropertycommonobject.editTodo = function (ev) {
            if (pmspropertycommonobject.tableobject.selected.length >= 1) {
                pmspropertycommonobject.addTodo(ev, pmspropertycommonobject.tableobject.selected[pmspropertycommonobject.tableobject.selected.length - 1].propertyid);
            }
        };
        pmspropertycommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmspropertycommonobject, "property", "pmsproperty");
        };
    }
    ;
})();
