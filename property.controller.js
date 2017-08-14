
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
            .service("pmspropertycommonobject", pmspropertycommonobject);

    function PrppertyGalleryDialogController(pmspropertycommonobject) {
        var vm = this;
        pmspropertycommonobject.imagesvm = vm;
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
        }
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
    }

    function pmsPropertyViewDialogController($scope, $mdDialog, pmspropertycommonobject, sessionobject, propertyobj) {
        $scope.propertyobj = propertyobj;
        $scope.hide = function () {
            $mdDialog.cancel();
        };
    }
    /* @ngInject */
    function PropertyTableController($q, $scope, $http, $localStorage, $timeout, $mdSidenav, $filter, sessionobject, pmspropertycommonobject) {
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
        $scope.pmsproperty.selectamountform = 1000;
        $scope.pmsproperty.selectamountto = 100000000;
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
                formData.filter += "customerid.fullname|" + vm.pmsproperty.customeridtbl + ";";
            }
            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertyamount) {
                formData.filter += "amount|" + vm.pmsproperty.selectamountform + '-' + vm.pmsproperty.selectamountto + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertypropertypriorityid) {
                formData.filter += "propertypriorityid.propertypriorityid|" + angular.toJson(vm.pmsproperty.selectpropertypropertypriorityidtbl) + ";";
            }
            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertyapt) {
                formData.filter += "apt|" + vm.pmsproperty.apttbl + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertycityid) {
                formData.filter += "cityid.cityid|" + angular.toJson(vm.pmsproperty.selectpropertycityidtbl) + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertypropertylocalityid) {
                formData.filter += "propertylocalityid.propertylocalityid|" + angular.toJson(vm.pmsproperty.selectpropertypropertylocalityidtbl) + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertypropertylbedroomsid) {
                formData.filter += "propertylbedroomsid.propertylbedroomsid|" + angular.toJson(vm.pmsproperty.selectpropertypropertylbedroomsidtbl) + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertypropertyfacingid) {
                formData.filter += "propertyfacingid.propertyfacingid|" + angular.toJson(vm.pmsproperty.selectpropertypropertyfacingidtbl) + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertypropertyfurnishedid) {
                formData.filter += "propertyfurnishedid.propertyfurnishedid|" + angular.toJson(vm.pmsproperty.selectpropertypropertyfurnishedidtbl) + ";";
            }

            if (angular.isDefined(vm.pmsproperty.selectlistbox) && vm.pmsproperty.selectlistbox.propertyavailablefrom) {
                formData.filter += "availablefrom|" + $filter('date')(vm.pmsproperty.availablefromdate_from, "MMM d, yyyy") + '-To-' + $filter('date')(vm.pmsproperty.availablefromdate_to, "MMM d, yyyy") + ";";
            }
            if (vm.pmsproperty.selectlistbox.propertyflatamenitiesid) {
                formData.filter += "flatamenitiesid.flatamenitiesid|" + angular.toJson(vm.pmsproperty.selectpropertyflatamenitiesidtbl) + ";";
            }

            if (vm.pmsproperty.selectlistbox.propertysocietyamenitiesid) {
                formData.filter += "societyamenitiesid.societyamenitiesid|" + angular.toJson(vm.pmsproperty.selectpropertysocietyamenitiesidtbl) + ";";
            }

            vm.promise = sessionobject.getTable($scope, vm, "property", pmspropertycommonobject, formData);
        }
        function upload($files) {
            pmspropertycommonobject.files = $files;
        }
        vm.object = pmspropertycommonobject;
        var requestPromise = [];
        var httpPromise = null;
        //   if (requestPromise.length !== 0) {
        pmspropertycommonobject.initCombo(requestPromise, httpPromise, true);
        $q.all(requestPromise).then(function (data) {
            sessionobject.activate(vm, $scope, pmspropertycommonobject);
            //    vm.fillCombo('propertytypeid', vm);
            //  vm.fillCombo('propertylocalityid', vm);
            var inqueryform = $localStorage["inquiryform"];
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
                vm.pmsproperty.selectlistbox.propertypropertypriorityid = true;
                vm.pmsproperty.selectpropertypropertypriorityidtbl[ inqueryform.propertypriorityid.propertypriorityid] = true;

//
//                vm.pmsproperty.selectpropertypropertypriorityidtbl = [];
//                vm.pmsproperty.selectlistbox.propertypropertypriorityid = true;
//                vm.pmsproperty.selectpropertypropertypriorityidtbl[ inqueryform.propertypriorityid.propertypriorityid] = true;

                vm.pmsproperty.selectpropertypropertylbedroomsidtbl = {};
                vm.pmsproperty.selectlistbox.propertypropertylbedroomsid = true;
                vm.pmsproperty.selectpropertypropertylbedroomsidtbl[ inqueryform.propertylbedroomsid.propertylbedroomsid] = true;

                vm.pmsproperty.selectpropertycityidtbl = {};
                vm.pmsproperty.selectlistbox.propertycityid = true;
                vm.pmsproperty.selectpropertycityidtbl[ inqueryform.cityid.cityid] = true;

                vm.pmsproperty.selectpropertypropertylocalityidtbl = {};
                vm.pmsproperty.selectlistbox.propertypropertylocalityid = true;
                vm.pmsproperty.selectpropertypropertylocalityidtbl[ inqueryform.propertylocalityid.propertylocalityid] = true;

                vm.pmsproperty.selectpropertypropertyfacingidtbl = {};
                vm.pmsproperty.selectlistbox.propertypropertyfacingid = true;
                vm.pmsproperty.selectpropertypropertyfacingidtbl[ inqueryform.propertyfacingid.propertyfacingid] = true;
                //  vm.pmsproperty.selectpropertypropertyfurnishedidtbl =  {};
                vm.pmsproperty.selectlistbox.propertypropertyfurnishedid = false;
                vm.pmsproperty.selectlistbox.propertypropertytypeid = false;
                $localStorage["inquiryform"] = undefined;
                //  vm.pmsproperty.selectpropertypropertyfurnishedidtbl[ inqueryform.propertyfurnishedid.propertyfurnishedid] = true;
            }
        });
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
                selectpropertytobeid: pmspropertycommonobject.type,
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

        pmspropertycommonobject.initCombo = initCombo;
        function initCombo(requestPromise, httpPromise, flag) {

            sessionobject.initCombo("PropertytypeForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("CustomerForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("PropertypriorityForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("CityForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("PropertylocalityForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("PropertylbedroomsForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("PropertyfacingForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("PropertyfurnishedForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("PropertytobeForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("FlatamenitiesForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("SocietyamenitiesForm", null, null, requestPromise, flag, pmspropertycommonobject);

            sessionobject.initCombo("CustomertypeForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("OccupationForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("GenderForm", null, null, requestPromise, flag, pmspropertycommonobject);
            sessionobject.initCombo("CityForm", null, null, requestPromise, flag, pmspropertycommonobject);


            sessionobject.initCombocount("PropertytypeForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("CustomerForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertypriorityForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertyForm", "Property", requestPromise, pmspropertycommonobject, 'apt', flag);
            sessionobject.initCombocount("CityForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertylocalityForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertylbedroomsForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertyfacingForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertyfurnishedForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("PropertytobeForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("FlatamenitiesForm", "Property", requestPromise, pmspropertycommonobject, null, flag);
            sessionobject.initCombocount("SocietyamenitiesForm", "Property", requestPromise, pmspropertycommonobject, null, flag);

            sessionobject.initCombo("Budget" + (pmspropertycommonobject.type === 1 ? 'rent' : 'sale') + "Form", null, null, requestPromise, flag, pmspropertycommonobject);


//            } else {
//                sessionobject.activate(vm, $scope, pmspropertycommonobject);
//            }
        }
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
        pmspropertycommonobject.simulateQuery = false;
        pmspropertycommonobject.querySearch = querySearch;
        function querySearch(query, list) {
            var results = query ? list.filter(createFilterForText(query)) : list,
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

        function createFilterForText(query) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(state) {

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
                            var a = 1;
                            var httpPromise = $http(sessionobject.getHttpHeader('/saveCustomer.json', answer, pmspropertycommonobject.role))
                                    .success(function (data) {
                                        pmspropertycommonobject.pmsproperty.selectcustomerid = data.customerid;
                                        pmspropertycommonobject.save(ev, id);
                                    });

                        }, function () {
                            pmspropertycommonobject.save(ev, id);
                            //  $scope.status = 'You decided to keep your debt.';
                        });
//                    
                    });
                    pmspropertycommonobject.save(ev, id);
                    //    pmspropertycommonobject.pmsproperty.createddate = $filter('date')(pmspropertycommonobject.pmsproperty.createddate, "medium");//"{{pmspropertycommonobject.pmsproperty.createddate | date:'medium'}}";

                } else {
                    pmspropertycommonobject.save(ev, id);
                }
            } else {
                pmspropertycommonobject.save(ev, id);
            }
        }

        pmspropertycommonobject.showDiloag = function (ev, id) {

            if (id !== 0) {
                pmspropertycommonobject.pmsproperty.customeridtxt = pmspropertycommonobject.pmsproperty.customerid.fullname;
            }
            //    pmspropertycommonobject.pmsproperty.createddate = $filter('date')(pmspropertycommonobject.pmsproperty.createddate, "medium");//"{{pmspropertycommonobject.pmsproperty.createddate | date:'medium'}}";
            $mdDialog.show({
                templateUrl: 'app/pms/property/PropertyAddEdit.tmpl.html',
                targetEvent: ev,
                controller: 'pmsPropertyDialogController',
            }).then(function (answer) {
                pmspropertycommonobject.showCustomerDiloag(ev, pmspropertycommonobject.pmsproperty.customeridtxt, id);

            });
        }
        pmspropertycommonobject.save = function (ev, id)
        {


            var requestPromise = [];
            var httpPromise = null;
            triLoaderService.setLoaderActive(true);
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
})();
