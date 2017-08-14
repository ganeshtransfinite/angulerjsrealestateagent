
(function () {
    'use strict';
//    alert('ok1');
    angular
            .module('app.module.pms')
            .controller('pmsreportPropertyRentController', pmsreportPropertyRentController)
            .controller('pmsreportPropertySaleController', pmsreportPropertySaleController)
            .controller('pmsreportclosePropertyRentController', pmsreportclosePropertyRentController)
            .controller('pmsreportclosePropertySaleController', pmsreportclosePropertySaleController)
            .controller('ReportPropertyTableController', ReportPropertyTableController)
            .controller('PrppertyGalleryDialogController', PrppertyGalleryDialogController)
            .controller('pmsreportPropertyDialogController', pmsreportPropertyDialogController)
            .controller('pmsreportPropertyViewDialogController', pmsreportPropertyViewDialogController)
            .service("pmsreportpropertycommonobject", pmsreportpropertycommonobject);

    function PrppertyGalleryDialogController(pmsreportpropertycommonobject) {
        var vm = this;
        pmsreportpropertycommonobject.imagesvm = vm;
        vm.showprogressbar = true;
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

    function pmsreportPropertyRentController($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsreportpropertycommonobject.type = 3;
        pmsreportpropertycommonobject.avilablereoprt = true;
    }
    function pmsreportclosePropertyRentController($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsreportpropertycommonobject.type = 3;
        pmsreportpropertycommonobject.avilablereoprt = false;
    }

    function init($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
//        triLayout.layout.sideMenuSize = 'hidden';
//        triMenu.item.open=false;
        $scope.addTodo = function (ev) {
            //      $stateProvider.url("/pmsreport/flight");
            pmsreportpropertycommonobject.addTodo(ev, 0);
        }
    }
    function pmsreportPropertySaleController($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsreportpropertycommonobject.type = 4;
        pmsreportpropertycommonobject.avilablereoprt = true;
    }

    function pmsreportclosePropertySaleController($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout) {
        init($scope, pmsreportpropertycommonobject, sessionobject, $localStorage, triMenu, triLayout);
        pmsreportpropertycommonobject.type = 4;
        pmsreportpropertycommonobject.avilablereoprt = false;
    }


    function pmsreportPropertyDialogController($scope, $mdDialog, pmsreportpropertycommonobject, sessionobject) {
        $scope.sessionobject = sessionobject;
        $scope.pmsreportproperty = pmsreportpropertycommonobject.pmsreportproperty;
        $scope.pmsreportpropertycommonobject = pmsreportpropertycommonobject;
        $scope.pmsreportproperty = pmsreportpropertycommonobject.pmsreportproperty;
        $scope.hide = function () {
            $mdDialog.hide($scope.item);
        };
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
        $scope.uploadimage1 = uploadimage1;
        function uploadimage1($files) {
            pmsreportpropertycommonobject.selectimage1 = $files;
        }
        $scope.uploadimage2 = uploadimage2;
        function uploadimage2($files) {
            pmsreportpropertycommonobject.selectimage2 = $files;
        }
        $scope.uploadimage3 = uploadimage3;
        function uploadimage3($files) {
            pmsreportpropertycommonobject.selectimage3 = $files;
        }
        $scope.uploadimage4 = uploadimage4;
        function uploadimage4($files) {
            pmsreportpropertycommonobject.selectimage4 = $files;
        }
        $scope.uploadimage5 = uploadimage5;
        function uploadimage5($files) {
            pmsreportpropertycommonobject.selectimage5 = $files;
        }
        $scope.uploadimage6 = uploadimage6;
        function uploadimage6($files) {
            pmsreportpropertycommonobject.selectimage6 = $files;
        }
        $scope.uploadimage7 = uploadimage7;
        function uploadimage7($files) {
            pmsreportpropertycommonobject.selectimage7 = $files;
        }
        $scope.uploadimage8 = uploadimage8;
        function uploadimage8($files) {
            pmsreportpropertycommonobject.selectimage8 = $files;
        }
        $scope.uploadimage9 = uploadimage9;
        function uploadimage9($files) {
            pmsreportpropertycommonobject.selectimage9 = $files;
        }
        $scope.uploadimage10 = uploadimage10;
        function uploadimage10($files) {
            pmsreportpropertycommonobject.selectimage10 = $files;
        }
    }

    function pmsreportPropertyViewDialogController($scope, $mdDialog, pmsreportpropertycommonobject, sessionobject, propertyobj) {
        $scope.propertyobj = propertyobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }


    /* @ngInject */
    function ReportPropertyTableController($q, $scope,triLayout, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmsreportpropertycommonobject) {
//        
        $scope.sessionobject = sessionobject;
        $scope.role = $localStorage.clientloginuserid.role;
        $scope.pmsreportpropertycommonobject = pmsreportpropertycommonobject;
        var vm = this;
        pmsreportpropertycommonobject.tableobject = vm;
        $scope.tableobject = vm;
        vm.pmsreportproperty = {
        };
        $scope.pmsreportproperty = vm.pmsreportproperty;
        $scope.pmsreportproperty.selectlistbox = [];
        $scope.pmsreportproperty.selectlistbox.propertytypeid = true;
        $scope.pmsreportproperty.selectlistbox.propertylocalityid = true;
        $scope.pmsreportproperty.selectamountform = 1000;
        $scope.pmsreportproperty.selectamountto = 100000000;
        if (pmsreportpropertycommonobject.avilablereoprt === true)
            pmsreportpropertycommonobject.dateAdd($scope.pmsreportproperty, "availablefromdate_from", "availablefromdate_to", 90);
        else
            pmsreportpropertycommonobject.dateAdd($scope.pmsreportproperty, "availablefromdate_to", "availablefromdate_from", -90);
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


        function getTable() {
            var formData = {};
            vm.selected = [];
            // alert(pmsreportpropertycommonobject.selectprofileimage[0].name);
            var sort = vm.query.order.lastIndexOf("-") >= 0 ? 'desc' : 'asc';
            formData.q = vm.query.filter;
            formData.order = vm.query.order;
            formData.sort = sort;
            formData.per_page = vm.query.limit;
            formData.page = vm.query.page;
            formData.filter = "";

            // if (vm.pmsreportproperty.selectlistbox.propertytobeid) {
            formData.filter += "propertytobeid.propertytobeid|" + angular.toJson(pmsreportpropertycommonobject.type === 3 ? 1 : 2) + ";"; //""

            formData.filter += "status|true;";

            if (vm.pmsreportproperty.selectlistbox.propertypropertytypeid) {
                formData.filter += "propertytypeid.propertytypeid|" + angular.toJson(vm.pmsreportproperty.selectpropertypropertytypeidtbl) + ";";
            }
            if (angular.isDefined(vm.pmsreportproperty.selectlistbox) && vm.pmsreportproperty.selectlistbox.aptselect) {
                formData.filter += "apt|" + angular.toJson(vm.pmsreportproperty.selectaptselecttbl) + ";";
            }

            if (angular.isDefined(vm.pmsreportproperty.selectlistbox) && vm.pmsreportproperty.selectlistbox.propertycustomerid) {
                formData.filter += "customerid.fullname|" + vm.pmsreportproperty.customeridtbl + ";";
            }
            if (angular.isDefined(vm.pmsreportproperty.selectlistbox) && vm.pmsreportproperty.selectlistbox.propertyamount) {
                formData.filter += "amount|" + vm.pmsreportproperty.selectamountform + '-' + vm.pmsreportproperty.selectamountto + ";";
            }
            if (vm.pmsreportproperty.selectlistbox.propertypropertypriorityid) {
                formData.filter += "propertypriorityid.propertypriorityid|" + angular.toJson(vm.pmsreportproperty.selectpropertypropertypriorityidtbl) + ";";
            }
            if (angular.isDefined(vm.pmsreportproperty.selectlistbox) && vm.pmsreportproperty.selectlistbox.propertyapt) {
                formData.filter += "apt|" + vm.pmsreportproperty.apttbl + ";";
            }
            if (vm.pmsreportproperty.selectlistbox.propertycityid) {
                formData.filter += "cityid.cityid|" + angular.toJson(vm.pmsreportproperty.selectpropertycityidtbl) + ";";
            }
            if (vm.pmsreportproperty.selectlistbox.propertypropertylocalityid) {
                formData.filter += "propertylocalityid.propertylocalityid|" + angular.toJson(vm.pmsreportproperty.selectpropertypropertylocalityidtbl) + ";";
            }
            if (vm.pmsreportproperty.selectlistbox.propertypropertylbedroomsid) {
                formData.filter += "propertylbedroomsid.propertylbedroomsid|" + angular.toJson(vm.pmsreportproperty.selectpropertypropertylbedroomsidtbl) + ";";
            }
            if (vm.pmsreportproperty.selectlistbox.propertypropertyfacingid) {
                formData.filter += "propertyfacingid.propertyfacingid|" + angular.toJson(vm.pmsreportproperty.selectpropertypropertyfacingidtbl) + ";";
            }
            if (vm.pmsreportproperty.selectlistbox.propertypropertyfurnishedid) {
                formData.filter += "propertyfurnishedid.propertyfurnishedid|" + angular.toJson(vm.pmsreportproperty.selectpropertypropertyfurnishedidtbl) + ";";
            }

            if (angular.isDefined(vm.pmsreportproperty.selectlistbox) && vm.pmsreportproperty.selectlistbox.propertyavailablefrom) {
                if (pmsreportpropertycommonobject.avilablereoprt)
                    formData.filter += "nextavilabledate|" + $filter('date')(vm.pmsreportproperty.availablefromdate_from, "MMM d, yyyy") + '-To-' + $filter('date')(vm.pmsreportproperty.availablefromdate_to, "MMM d, yyyy") + ";";
                else
                    formData.filter += "closedate|" + $filter('date')(vm.pmsreportproperty.availablefromdate_from, "MMM d, yyyy") + '-To-' + $filter('date')(vm.pmsreportproperty.availablefromdate_to, "MMM d, yyyy") + ";";

            }

            if (vm.pmsreportproperty.selectlistbox.propertyflatamenitiesid) {
                formData.filter += "flatamenitiesid.flatamenitiesid|" + angular.toJson(vm.pmsreportproperty.selectpropertyflatamenitiesidtbl) + ";";
            }

            if (vm.pmsreportproperty.selectlistbox.propertysocietyamenitiesid) {
                formData.filter += "societyamenitiesid.societyamenitiesid|" + angular.toJson(vm.pmsreportproperty.selectpropertysocietyamenitiesidtbl) + ";";
            }

            vm.promise = sessionobject.getTable($scope, vm, "property", pmsreportpropertycommonobject, formData);
        }
        sessionobject.initComboCountInitALL("Property", pmsreportpropertycommonobject.type, true);
        function upload($files) {
            pmsreportpropertycommonobject.files = $files;
        }
        vm.object = pmsreportpropertycommonobject;

        sessionobject.activate(vm, $scope, pmsreportpropertycommonobject);
        //    vm.fillCombo('propertytypeid', vm);
        //  vm.fillCombo('propertylocalityid', vm);
triLayout.layout.sideMenuSize= 'icon';

    }

    function pmsreportpropertycommonobject($mdDialog, $http, $q, $filter, $localStorage, sessionobject, triLoaderService, $timeout) {
        var pmsreportpropertycommonobject = this;
        pmsreportpropertycommonobject.role = 'ReportProperty';
        pmsreportpropertycommonobject.createObject = function () {

            pmsreportpropertycommonobject.selectimage1 = undefined;
            pmsreportpropertycommonobject.selectimage2 = undefined;
            pmsreportpropertycommonobject.selectimage3 = undefined;
            pmsreportpropertycommonobject.selectimage4 = undefined;
            pmsreportpropertycommonobject.selectimage5 = undefined;
            pmsreportpropertycommonobject.selectimage6 = undefined;
            pmsreportpropertycommonobject.selectimage7 = undefined;
            pmsreportpropertycommonobject.selectimage8 = undefined;
            pmsreportpropertycommonobject.selectimage9 = undefined;
            pmsreportpropertycommonobject.selectimage10 = undefined;


            return  {
                propertyid: 0,
                selectpropertytypeid: 0,
                selectcustomerid: 0,
                totalarea: '',
                amount: 0,
                selectpropertypriorityid: 0,
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
                deposit: 0,
                sqfeetrate: '',
                selectimage6: 0,
                selectimage7: 0,
                selectimage8: 0,
                selectimage9: 0,
                selectimage10: 0,
                postedon: new Date(),
                selectpropertytobeid: pmsreportpropertycommonobject.type,
                availablefrom: new Date(),
                selectflatamenitiesid: [],
                status: false,
                selectsocietyamenitiesid: [],
                recordorder: 0,
                balcony: '',
                bathroom: '',
                totalfloorno: '',
                parking: false,
                keyavailability: '',
                keyname: '',
                keymobile: '',
                keyaddress: ''
            };
        };

        pmsreportpropertycommonobject.openImage = openImage;
        function openImage(item, $event) {


            $mdDialog.show({
                controller: 'PrppertyGalleryDialogController',
                controllerAs: 'vm',
                templateUrl: 'app/pms/report/gallery-dialog.tmpl.html',
                clickOutsideToClose: true,
                focusOnOpen: false,
                targetEvent: $event,
                locals: {
                    pmsreportpropertycommonobject: pmsreportpropertycommonobject
                }
            });

            var obj = pmsreportpropertycommonobject.createObject();
            obj.propertyid = item.propertyid;
            $http(sessionobject.getHttpHeader('/editproperty.json', obj, pmsreportpropertycommonobject.role))
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
                            openimage(pmsreportpropertycommonobject.imagesvm, images);
                        }
                    });

        }

        pmsreportpropertycommonobject.dateAdd = dateAdd;
        function dateAdd(pmsproperty, availabledate_from, availabledate_to, day) {
            pmsproperty[availabledate_to] = new Date();
            pmsproperty[availabledate_to].setDate(pmsproperty[availabledate_to].getDate() + day);
            pmsproperty[availabledate_from] = new Date();
        }
        pmsreportpropertycommonobject.simulateQuery = false;
        pmsreportpropertycommonobject.querySearch = querySearch;
        function querySearch(query, list) {
            var results = query ? list.filter(createFilterForText(query)) : list,
                    deferred;
            if (pmsreportpropertycommonobject.simulateQuery) {
                deferred = $q.defer();
                $timeout(function () {
                    deferred.resolve(results);
                }, Math.random() * 1000, false);
                return deferred.promise;
            } else {
                return results;
            }
        }

        function createFilterForText(query) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(state) {

                return (angular.lowercase(state.text).indexOf(lowercaseQuery) >= 0);
            };
        }

        pmsreportpropertycommonobject.showReportProperty = function (ev, obj) {
            obj.showprogressbar = true;
            var obj1 = pmsreportpropertycommonobject.createObject();
            obj1.propertyid = obj.propertyid;
            $http(sessionobject.getHttpHeader('/editproperty.json', obj, pmsreportpropertycommonobject.role))
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
                templateUrl: 'app/pms/report/ProperttyView.tmpl.html',
                targetEvent: ev,
                controller: 'pmsReportPropertyViewDialogController',
                locals: {
                    propertyobj: obj
                },
            }).then(function () {


                // pmsreportpropertycommonobject.save(ev, id);
            });
        }

        pmsreportpropertycommonobject.pmsproperty = pmsreportpropertycommonobject.createObject();
        pmsreportpropertycommonobject.showCustomerDiloag = function (ev, str, id) {
            if (angular.isDefined(str) && str !== '') {

                var res = sessionobject.comboboxlist.customeridlist.filter(createFilterForText(str));
                if (angular.isDefined(res) && res.length === 0) {

                    var confirm = $mdDialog.confirm()
                            .title('Would you like to add New Customer in our Database  ')
                            .textContent('Selectd Customer NAme ' + str + ".")
                            .ariaLabel('Add Confirm')
                            .targetEvent(ev)
                            .ok('Yes')
                            .cancel('No');
                    $mdDialog.show(confirm).then(function () {
                        $mdDialog.show({
                            templateUrl: 'app/pms/customer/CustomerAddEdit.tmpl.html',
                            targetEvent: ev,
                            controller: 'pmsCustomerDialogController',
                            locals: {
                                fullname: str
                            }
                        }).then(function (answer) {
                            //pmscustomercommonobject.save(ev, id);
                            var httpPromise = $http(sessionobject.getHttpHeader('/saveCustomer.json', answer, pmsreportpropertycommonobject.role))
                                    .success(function (data) {
                                        pmsreportpropertycommonobject.pmsreportproperty.selectcustomerid = data.customerid;
                                    });
                            pmsreportpropertycommonobject.save(ev, id);
                        }, function () {
                            pmsreportpropertycommonobject.save(ev, id);
                            //  $scope.status = 'You decided to keep your debt.';
                        });
//                    
                    });
                    //    pmsreportpropertycommonobject.pmsreportproperty.createddate = $filter('date')(pmsreportpropertycommonobject.pmsreportproperty.createddate, "medium");//"{{pmsreportpropertycommonobject.pmsreportproperty.createddate | date:'medium'}}";

                } else {
                    pmsreportpropertycommonobject.save(ev, id);
                }
            } else {
                pmsreportpropertycommonobject.save(ev, id);
            }
        }

        pmsreportpropertycommonobject.showDiloag = function (ev, id) {

            if (id !== 0) {
                pmsreportpropertycommonobject.pmsreportproperty.customeridtxt = pmsreportpropertycommonobject.pmsreportproperty.customerid.fullname;
            }
            //    pmsreportpropertycommonobject.pmsreportproperty.createddate = $filter('date')(pmsreportpropertycommonobject.pmsreportproperty.createddate, "medium");//"{{pmsreportpropertycommonobject.pmsreportproperty.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/report/ReportPropertyAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsReportPropertyDialogController',
            }).then(function (answer) {
                pmsreportpropertycommonobject.showCustomerDiloag(ev, pmsreportpropertycommonobject.pmsreportproperty.customeridtxt, id);

            });
        }
        pmsreportpropertycommonobject.save = function (ev, id)
        {


            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
            sessionobject.uploadfiletoserver("image1", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image2", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image3", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image4", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image5", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image6", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image7", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image8", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image9", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            sessionobject.uploadfiletoserver("image10", requestPromise, pmsreportpropertycommonobject, pmsreportpropertycommonobject.pmsproperty);
            $q.all(requestPromise).then(function (data) {
                sessionobject.save(ev, id, triLoaderService, pmsreportpropertycommonobject, "ReportProperty", pmsreportpropertycommonobject.pmsproperty);
            });
        };
        pmsreportpropertycommonobject.removeTodo = function (ev) {
            sessionobject.removeTodo(ev, pmsreportpropertycommonobject, $mdDialog, triLoaderService, "property", "tabletitle");
        };
        pmsreportpropertycommonobject.editTodo = function (ev) {
            if (pmsreportpropertycommonobject.tableobject.selected.length >= 1) {
                pmsreportpropertycommonobject.addTodo(ev, pmsreportpropertycommonobject.tableobject.selected[pmsreportpropertycommonobject.tableobject.selected.length - 1].propertyid);
            }
        };
        pmsreportpropertycommonobject.addTodo = function (ev, id) {
            sessionobject.addTodo(ev, id, pmsreportpropertycommonobject, "property", "pmsproperty");
        };
    }
})();
