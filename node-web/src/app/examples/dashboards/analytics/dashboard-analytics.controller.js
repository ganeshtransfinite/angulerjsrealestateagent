(function () {
    'use strict';

    angular
            .module('app.examples.dashboards')
            .controller('DashboardAnalyticsController', DashboardAnalyticsController);

    /* @ngInject */
    function DashboardAnalyticsController($scope, $timeout, $localStorage, $mdToast, $rootScope, $state, triMenu, sessionobject, triLoaderService, $q) {
        var vm = this;

        sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
        $scope.deshborddata = $localStorage.clientloginuserid.desktop;


        $scope.refreshtable = function (widgetCtrl) {
var i=1;

        };




        $timeout(function () {
            $rootScope.$broadcast('newMailNotification');
            $mdToast.show({
                template: '<md-toast><span flex>You have new available property <a href="" ng-click=vm.viewUnread()>here</a></span></md-toast>',
                controller: newMailNotificationController,
                controllerAs: 'vm',
                position: 'bottom right',
                hideDelay: 5000
            });
        }, 10000);

        //////////////

        function newMailNotificationController() {
            var vm = this;
            vm.viewUnread = function () {
                $state.go('triangular.forms-PropertyReportRent');
            };
        }



    }
})();
