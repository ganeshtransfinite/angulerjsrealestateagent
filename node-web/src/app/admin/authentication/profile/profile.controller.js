(function () {
    'use strict';

    angular
            .module('app.admin.authentication')
            .controller('ProfileController', ProfileController);

    /* @ngInject */
    function ProfileController($localStorage, $scope,triMenu, sessionobject, $http) {
        var vm = this;
   sessionobject.isAuthenticate();
        sessionobject.showmenu(triMenu);
        vm.adminuser = $localStorage.clientloginuserid.user;
        $scope.adminuser = vm.adminuser;
        $scope.vm = vm;
        vm.adminuser.error = "";
        vm.adminuser.error1 = "";
        vm.settingsGroups = [{
                name: 'ADMIN.NOTIFICATIONS.ACCOUNT_SETTINGS',
                settings: [{
                        title: 'ADMIN.NOTIFICATIONS.SHOW_LOCATION',
                        icon: 'zmdi zmdi-pin',
                        enabled: true
                    }, {
                        title: 'ADMIN.NOTIFICATIONS.SHOW_AVATAR',
                        icon: 'zmdi zmdi-face',
                        enabled: false
                    }, {
                        title: 'ADMIN.NOTIFICATIONS.SEND_NOTIFICATIONS',
                        icon: 'zmdi zmdi-notifications-active',
                        enabled: true
                    }]
            }, {
                name: 'ADMIN.NOTIFICATIONS.CHAT_SETTINGS',
                settings: [{
                        title: 'ADMIN.NOTIFICATIONS.SHOW_USERNAME',
                        icon: 'zmdi zmdi-account',
                        enabled: true
                    }, {
                        title: 'ADMIN.NOTIFICATIONS.SHOW_PROFILE',
                        icon: 'zmdi zmdi-account-box',
                        enabled: false
                    }, {
                        title: 'ADMIN.NOTIFICATIONS.ALLOW_BACKUPS',
                        icon: 'zmdi zmdi-cloud-upload',
                        enabled: true
                    }]
            }];

        vm.checkExist = function (col, val, id) {

            $http.get(sessionobject.getURL("/checkExistUser.json") + "&col=" + col + "&val=" + val + "&id=" + id)
                    .success(function (response) {

                        vm.adminuser.checkExistCustomerEmail = response;

                    }).
                    error(function (response) {
                        sessionobject.showERROR(response);

                    });
        }
        vm.updateProfile = function () {
            vm.adminuser.option = "profile";
            $http(sessionobject.getHttpHeader("/saveUser.json", vm.adminuser, "admin"))
                    .success(function (response) {

                        vm.adminuser.error = response.option;

                    }).
                    error(function (response) {
                        sessionobject.showERROR(response);

                    });
        };

        vm.updatepwd = function () {
            vm.adminuser.option = "pwd";
            $http(sessionobject.getHttpHeader("/saveUser.json", vm.adminuser, "admin"))
                    .success(function (response) {

                        vm.adminuser.error1 = response.option;

                    }).
                    error(function (response) {
                        sessionobject.showERROR(response);

                    });
        };

    }
})();