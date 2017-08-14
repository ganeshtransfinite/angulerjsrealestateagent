(function() {
    'use strict';

    angular
        .module('app.admin.authentication')
        .config(moduleConfig);

    /* @ngInject */
    function moduleConfig( $stateProvider, triMenuProvider) {
     //   $translatePartialLoaderProvider.addPart('app/admin/authentication');

        $stateProvider
        .state('authentication', {
            abstract: true,
           // templateUrl: 'app/admin/authentication/layouts/authentication.tmpl.html'
              views: {
                'root': {
                    templateUrl: 'app/admin/authentication/layouts/authentication.tmpl.html'
                }
            },
            data: {
                permissions: {
                    only: ['viewAuthentication']
                }
            }
        }) 
        .state('authentication.login', {
            url: '/login',
            templateUrl: 'app/admin/authentication/login/login.tmpl.html',
            controller: 'LoginController',
            controllerAs: 'vm'
        })
         .state('authentication.forgot', {
            url: '/forgot',
            templateUrl: 'app/admin/authentication/forgot/forgot.tmpl.html',
            controller: 'ForgotController',
            controllerAs: 'vm'
        })
        .state('triangular.profile', {
            url: '/profile',
            templateUrl: 'app/admin/authentication/profile/profile.tmpl.html',
            controller: 'ProfileController',
            controllerAs: 'vm'
        });

//        triMenuProvider.addMenu({
//            name: 'adminmenu.AUTH.AUTH',
//            icon: 'zmdi zmdi-account',
//            type: 'dropdown',
//            priority: 4.1,
//            children: [
//                {
//                name: 'adminmenu.AUTH.LOGIN',
//                state: 'authentication.login',
//                type: 'link'
//            }, {
//                name: 'adminmenu.AUTH.SIGN_UP',
//                state: 'authentication.signup',
//                type: 'link'
//            },{
//                name: 'adminmenu.AUTH.FORGOT',
//                state: 'authentication.forgot',
//                type: 'link'
//            },{
//                name: 'adminmenu.AUTH.LOCK',
//                state: 'authentication.lock',
//                type: 'link'
//            },{
//                name: 'adminmenu.AUTH.PROFILE',
//                state: 'triangular.profile',
//                type: 'link'
//            }]
//        });
    }
})();