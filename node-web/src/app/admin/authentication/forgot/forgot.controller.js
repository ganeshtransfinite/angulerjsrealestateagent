(function() {
    'use strict';

    angular
        .module('app.admin.authentication')
        .controller('ForgotController', ForgotController);

    /* @ngInject */
    function ForgotController($scope, $state, $mdToast, $filter, $http, triSettings) {
        var vm = this;
        vm.triSettings = triSettings;
        vm.user = {
            email: ''
        };
        vm.resetClick = resetClick;

        ////////////////

        function resetClick() {
            $mdToast.show(
                $mdToast.simple()
                .content($filter('translate')('FORGOT.MESSAGES.RESET_SENT'))
                .position('bottom right')
                .action($filter('translate')('FORGOT.MESSAGES.LOGIN_NOW'))
                .highlightAction(true)
                .hideDelay(0)
            ).then(function() {
                $state.go('authentication.login');
            });
        }
    }
})();
