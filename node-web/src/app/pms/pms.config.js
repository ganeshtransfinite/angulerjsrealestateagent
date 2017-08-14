
(function () {
    'use strict';
    angular
            .module('app.module.pms')
            .config(moduleConfig);
    /* @ngInject */
    function moduleConfig($stateProvider, triMenuProvider) {
        //  $translatePartialLoaderProvider.addPart('app/pms');
        $stateProvider.state('triangular.forms-Propertyfurnished', {
            url: '/pmsPropertyfurnished',
            templateUrl: 'app/pms/propertyfurnished/PropertyfurnishedList.tmpl.html',
            controller: 'pmsPropertyfurnishedController',
            controllerAs: 'vm'
        }).state('triangular.forms-Agent', {
            url: '/pmsAgent',
            templateUrl: 'app/pms/agent/AgentList.tmpl.html',
            controller: 'pmsAgentController',
            controllerAs: 'vm',
            data: {
                layout: {
                    sideMenuSize: 'hidden'
                }
            }
        }).state('triangular.forms-Occupation', {
            url: '/pmsOccupation',
            templateUrl: 'app/pms/occupation/OccupationList.tmpl.html',
            controller: 'pmsOccupationController',
            controllerAs: 'vm'
        }).state('triangular.forms-Gender', {
            url: '/pmsGender',
            templateUrl: 'app/pms/gender/GenderList.tmpl.html',
            controller: 'pmsGenderController',
            controllerAs: 'vm'
        }).state('triangular.forms-City', {
            url: '/pmsCity',
            templateUrl: 'app/pms/city/CityList.tmpl.html',
            controller: 'pmsCityController',
            controllerAs: 'vm'
        }).state('triangular.forms-Propertytype', {
            url: '/pmsPropertytype',
            templateUrl: 'app/pms/propertytype/PropertytypeList.tmpl.html',
            controller: 'pmsPropertytypeController',
            controllerAs: 'vm'
        }).state('triangular.forms-Propertylbedrooms', {
            url: '/pmsPropertylbedrooms',
            templateUrl: 'app/pms/propertylbedrooms/PropertylbedroomsList.tmpl.html',
            controller: 'pmsPropertylbedroomsController',
            controllerAs: 'vm'
        }).state('triangular.forms-Budgetrent', {
            url: '/pmsBudgetrent',
            templateUrl: 'app/pms/budgetrent/BudgetrentList.tmpl.html',
            controller: 'pmsBudgetrentController',
            controllerAs: 'vm'
        }).state('triangular.forms-Propertytobe', {
            url: '/pmsPropertytobe',
            templateUrl: 'app/pms/propertytobe/PropertytobeList.tmpl.html',
            controller: 'pmsPropertytobeController',
            controllerAs: 'vm'
        }).state('triangular.forms-Inquiry', {
            url: '/pmsInquiry',
            templateUrl: 'app/pms/inquiry/InquiryList.tmpl.html',
            controller: 'pmsInquiryController',
            controllerAs: 'vm',
            data: {
                layout: {
                    sideMenuSize: 'hidden'
                }
            }
        }).state('triangular.forms-Flatamenities', {
            url: '/pmsFlatamenities',
            templateUrl: 'app/pms/flatamenities/FlatamenitiesList.tmpl.html',
            controller: 'pmsFlatamenitiesController',
            controllerAs: 'vm'
        })
//                .state('triangular.forms-Property', {
//            url: '/pmsProperty',
//            templateUrl: 'app/pms/property/PropertyList.tmpl.html',
//            controller: 'pmsPropertyController',
//            controllerAs: 'vm'
//        })
                .state('triangular.forms-Modeoperation', {
                    url: '/pmsModeoperation',
                    templateUrl: 'app/pms/modeoperation/ModeoperationList.tmpl.html',
                    controller: 'pmsModeoperationController',
                    controllerAs: 'vm'
                }).state('triangular.forms-Societyamenities', {
            url: '/pmsSocietyamenities',
            templateUrl: 'app/pms/societyamenities/SocietyamenitiesList.tmpl.html',
            controller: 'pmsSocietyamenitiesController',
            controllerAs: 'vm'
        }).state('triangular.forms-Customertype', {
            url: '/pmsCustomertype',
            templateUrl: 'app/pms/customertype/CustomertypeList.tmpl.html',
            controller: 'pmsCustomertypeController',
            controllerAs: 'vm'
        }).state('triangular.forms-Propertypriority', {
            url: '/pmsPropertypriority',
            templateUrl: 'app/pms/propertypriority/PropertypriorityList.tmpl.html',
            controller: 'pmsPropertypriorityController',
            controllerAs: 'vm'
        }).state('triangular.forms-Budgetsale', {
            url: '/pmsBudgetsale',
            templateUrl: 'app/pms/budgetsale/BudgetsaleList.tmpl.html',
            controller: 'pmsBudgetsaleController',
            controllerAs: 'vm'
        }).state('triangular.forms-Test', {
            url: '/pmsTest',
            templateUrl: 'app/pms/test/TestList.tmpl.html',
            controller: 'pmsTestController',
            controllerAs: 'vm'
        }).state('triangular.forms-Propertylocality', {
            url: '/pmsPropertylocality',
            templateUrl: 'app/pms/propertylocality/PropertylocalityList.tmpl.html',
            controller: 'pmsPropertylocalityController',
            controllerAs: 'vm'
        }).state('triangular.forms-Propertyfacing', {
            url: '/pmsPropertyfacing',
            templateUrl: 'app/pms/propertyfacing/PropertyfacingList.tmpl.html',
            controller: 'pmsPropertyfacingController',
            controllerAs: 'vm'
        }).state('triangular.forms-Customer', {
            url: '/pmsCustomer',
            templateUrl: 'app/pms/customer/CustomerList.tmpl.html',
            controller: 'pmsCustomerController',
            controllerAs: 'vm',
            data: {
                layout: {
                    sideMenuSize: 'hidden'
                }
            }
        }).state('triangular.forms-PropertyRent', {
            url: '/pmsPropertyRentController',
            templateUrl: 'app/pms/property/PropertyList.tmpl.html',
            controller: 'pmsPropertyRentController',
            controllerAs: 'vm',
            data: {
                layout: {
                    sideMenuSize: 'hidden'
                }
            }
        }).state('triangular.forms-PropertySale', {
            url: '/pmsPropertySaleController',
            templateUrl: 'app/pms/property/PropertyList.tmpl.html',
            controller: 'pmsPropertySaleController',
            controllerAs: 'vm',
            data: {
                layout: { 
                    sideMenuSize: 'hidden'
                }
            }

        }).state('triangular.forms-PropertyReportRent', {
            url: '/pmsreportPropertyRentController',
            templateUrl: 'app/pms/report/PropertyList.tmpl.html',
            controller: 'pmsreportPropertyRentController',
            controllerAs: 'vm'
            ,
            data: {
                layout: {
                    sideMenuSize: 'hidden'
                }
            }
        }).state('triangular.forms-PropertyReportCloseRent', {
            url: '/pmsreportclosePropertyRentController',
            templateUrl: 'app/pms/report/PropertyList.tmpl.html',
            controller: 'pmsreportclosePropertyRentController',
            controllerAs: 'vm'
        }).state('triangular.forms-PropertyReportCloseSale', {
            url: '/pmsreportclosePropertySaleController',
            templateUrl: 'app/pms/report/PropertyList.tmpl.html',
            controller: 'pmsreportclosePropertySaleController',
            controllerAs: 'vm'

        }).state('triangular.forms-PropertyReport', {
            url: '/pmsPropertyReport',
            templateUrl: 'app/pms/property/PropertyReportList.tmpl.html',
            controller: 'pmsPropertyReportController',
            controllerAs: 'vm'
        });

//        triMenuProvider.addMenu({
//            name: 'MENU.pms.NAME',
//            icon: 'icon-home',
//            type: 'dropdown',
//            display:true,
//            priority: 4.1, 
        /*            children: [{
         name: 'MENU.propertyfurnished.NAME',
         state: 'triangular.forms-Propertyfurnished', //'pms.Propertyfurnished',
         display: $localStorage.clientloginuserid.role['Property Furnished'].pmenu,
         type: 'link'
         },{
         name: 'MENU.agent.NAME',
         state: 'triangular.forms-Agent', //'pms.Agent',
         display: $localStorage.clientloginuserid.role['Agent'].pmenu,
         type: 'link'
         },{
         name: 'MENU.occupation.NAME',
         state: 'triangular.forms-Occupation', //'pms.Occupation',
         display: $localStorage.clientloginuserid.role['Occupation'].pmenu,
         type: 'link'
         },{
         name: 'MENU.gender.NAME',
         state: 'triangular.forms-Gender', //'pms.Gender',
         display: $localStorage.clientloginuserid.role['Gender'].pmenu,
         type: 'link'
         },{
         name: 'MENU.city.NAME',
         state: 'triangular.forms-City', //'pms.City',
         display: $localStorage.clientloginuserid.role['City'].pmenu,
         type: 'link'
         },{
         name: 'MENU.propertytype.NAME',
         state: 'triangular.forms-Propertytype', //'pms.Propertytype',
         display: $localStorage.clientloginuserid.role['Property Type'].pmenu,
         type: 'link'
         },{
         name: 'MENU.propertylbedrooms.NAME',
         state: 'triangular.forms-Propertylbedrooms', //'pms.Propertylbedrooms',
         display: $localStorage.clientloginuserid.role['Property Bed Rooms'].pmenu,
         type: 'link'
         },{
         name: 'MENU.budgetrent.NAME',
         state: 'triangular.forms-Budgetrent', //'pms.Budgetrent',
         display: $localStorage.clientloginuserid.role['Budget Rent'].pmenu,
         type: 'link'
         },{
         name: 'MENU.propertytobe.NAME',
         state: 'triangular.forms-Propertytobe', //'pms.Propertytobe',
         display: $localStorage.clientloginuserid.role['Property To be'].pmenu,
         type: 'link'
         },{
         name: 'MENU.inquiry.NAME',
         state: 'triangular.forms-Inquiry', //'pms.Inquiry',
         display: $localStorage.clientloginuserid.role['Inquiry'].pmenu,
         type: 'link'
         },{
         name: 'MENU.flatamenities.NAME',
         state: 'triangular.forms-Flatamenities', //'pms.Flatamenities',
         display: $localStorage.clientloginuserid.role['Flat Amenities'].pmenu,
         type: 'link'
         },{
         name: 'MENU.property.NAME',
         state: 'triangular.forms-Property', //'pms.Property',
         display: $localStorage.clientloginuserid.role['Property'].pmenu,
         type: 'link'
         },{
         name: 'MENU.modeoperation.NAME',
         state: 'triangular.forms-Modeoperation', //'pms.Modeoperation',
         display: $localStorage.clientloginuserid.role['Mode of Operation'].pmenu,
         type: 'link'
         },{
         name: 'MENU.societyamenities.NAME',
         state: 'triangular.forms-Societyamenities', //'pms.Societyamenities',
         display: $localStorage.clientloginuserid.role['Society Amenities'].pmenu,
         type: 'link'
         },{
         name: 'MENU.customertype.NAME',
         state: 'triangular.forms-Customertype', //'pms.Customertype',
         display: $localStorage.clientloginuserid.role['Customer Type'].pmenu,
         type: 'link'
         },{
         name: 'MENU.propertypriority.NAME',
         state: 'triangular.forms-Propertypriority', //'pms.Propertypriority',
         display: $localStorage.clientloginuserid.role['Property Priority'].pmenu,
         type: 'link'
         },{
         name: 'MENU.budgetsale.NAME',
         state: 'triangular.forms-Budgetsale', //'pms.Budgetsale',
         display: $localStorage.clientloginuserid.role['Budget Sale'].pmenu,
         type: 'link'
         },{
         name: 'MENU.test.NAME',
         state: 'triangular.forms-Test', //'pms.Test',
         display: $localStorage.clientloginuserid.role['Test'].pmenu,
         type: 'link'
         },{
         name: 'MENU.propertylocality.NAME',
         state: 'triangular.forms-Propertylocality', //'pms.Propertylocality',
         display: $localStorage.clientloginuserid.role['Property Locality'].pmenu,
         type: 'link'
         },{
         name: 'MENU.propertyfacing.NAME',
         state: 'triangular.forms-Propertyfacing', //'pms.Propertyfacing',
         display: $localStorage.clientloginuserid.role['Property Facing'].pmenu,
         type: 'link'
         },{
         name: 'MENU.customer.NAME',
         state: 'triangular.forms-Customer', //'pms.Customer',
         display: $localStorage.clientloginuserid.role['Customer'].pmenu,
         type: 'link'
         }] 
         
         */
//        });
    }
})();
