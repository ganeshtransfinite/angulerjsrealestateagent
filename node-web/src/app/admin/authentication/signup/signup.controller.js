(function() {
    'use strict';

    angular
        .module('app.admin.authentication')
        .controller('SignupController', SignupController);

    /* @ngInject */
    function SignupController($scope, $state, $mdToast, $http, $filter, triSettings) {
        var vm = this;
        vm.triSettings = triSettings;
        vm.signupClick = signupClick;
        vm.user = {
            name: '',
            email: '',
            password: '',
            confirm: ''
        };

        ////////////////

        function signupClick() {
            $mdToast.show(
                $mdToast.simple()
                .content($filter('translate')('SIGNUP.MESSAGES.CONFIRM_SENT'))
                .position('bottom right')
                .action($filter('translate')('SIGNUP.MESSAGES.LOGIN_NOW'))
                .highlightAction(true)
                .hideDelay(0)
            ).then(function() {
                $state.go('authentication.login');
            });
        }
    }
})();
