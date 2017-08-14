
(function () {
    'use strict';

    angular
            .module('app.admin.authentication')
            .controller('LoginController', LoginController);

    /* @ngInject */
    function LoginController($state, $http, $localStorage, triSettings, sessionobject, $q, triLoaderService, triMenu) {
        var vm = this;
        vm.flagprog = false;
        sessionobject.setURL();

        if (angular.isDefined($localStorage.clientloginuserid)) {
            $localStorage.clientloginuserid.userid = 0;
        } else {
            var adminuser = {};
            $localStorage.clientloginuserid = adminuser;
        }
        vm.flag = false;

        vm.loginClick = loginClick;
        vm.socialLogins = [{
                icon: 'fa fa-twitter',
                color: '#5bc0de',
                url: '#'
            }, {
                icon: 'fa fa-facebook',
                color: '#337ab7',
                url: '#'
            }, {
                icon: 'fa fa-google-plus',
                color: '#e05d6f',
                url: '#'
            }, {
                icon: 'fa fa-linkedin',
                color: '#337ab7',
                url: '#'
            }];
        vm.triSettings = triSettings;
        // create blank user variable for login form
        vm.user = {
            email: '',
            password: ''
        };
        vm.keypress = function ($event) {
            if ($event.keyCode === 13) {
                loginClick();
            }
        };
        ////////////////

        function loginClick() {
            vm.flagprog = true;
            vm.flag = false;
            $http({
                method: 'POST',
                url: sessionobject.getURL('/login.json'),
                data: angular.toJson(vm.user),
                headers: {'Content-Type': 'application/json',
                    //       'Access-Control-Request-Headers': 'Origin',
                    //    'Access-Control-Request-Method': 'POST'
                }
            }).success(function (data) {
                if (data.errors) {
                    // Showing errors.
                    alert(data.errors);
                    vm.flagprog = false;

                } else {
                    if (data.userid !== 0) {
                        triMenu.load($http);
                        $localStorage.clientloginuserid.userid = data.userid;
                        $localStorage.clientloginuserid.JSESSIONID = data.JSESSIONID;
                        $localStorage.clientloginuserid.username = data.username;
                        $localStorage.clientloginuserid.role = data.role;
                        $localStorage.clientloginuserid.user = data.user;
                        sessionobject.comboboxlist = {
                        };
                        $localStorage.clientloginuserid.comboboxlist = sessionobject.comboboxlist;
                        $localStorage.clientloginuserid.refresh = true;
                        //       vm.flagprog = false;
                        $state.go('triangular.forms-UserLoad');//triangular.dashboard-analytics');


                        //    
                        //    sessionobject.initComboAll();
                        //    
                        //    alert(data.userid + ":" + data.JSESSIONID);


                    } else {
                        vm.flag = true;
                        vm.flagprog = false;
                    }

                }
            });


        }
    }
})();
