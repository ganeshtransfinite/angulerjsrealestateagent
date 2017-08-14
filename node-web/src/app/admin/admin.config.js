
(function () {
    'use strict';
    angular
            .module('app.module.admin')
            .config(moduleConfig);
    /* @ngInject */
    function moduleConfig($stateProvider, triMenuProvider) {
        //  $translatePartialLoaderProvider.addPart('app/admin');
        $stateProvider.state('triangular.forms-Rolepermission', {
            url: '/adminRolepermission',
            templateUrl: 'app/admin/rolepermission/RolepermissionList.tmpl.html',
            controller: 'adminRolepermissionController',
            controllerAs: 'vm'
        }).state('triangular.forms-User', {
            url: '/adminUserLoad',
            templateUrl: 'app/admin/user/UserList.tmpl.html',
            controller: 'adminUserController',
            controllerAs: 'vm',
              data: {
                layout: {
                    sideMenuSize: 'hidden'
                }
            }

        }).state('triangular.forms-UserLoad', {
            url: '/adminUser',
            templateUrl: 'app/admin/user/UserLoad.tmpl.html',
            controller: 'adminUserLoadController',
            controllerAs: 'vm',
            data: {
                layout: {
                    sideMenuSize: 'icon'
                },
                permissions: {
                    only: ['viewLayouts']
                }

            }
        }).state('triangular.forms-Operation', {
            url: '/adminOperation',
            templateUrl: 'app/admin/operation/OperationList.tmpl.html',
            controller: 'adminOperationController',
            controllerAs: 'vm'
        }).state('triangular.forms-Role', {
            url: '/adminRole',
            templateUrl: 'app/admin/role/RoleList.tmpl.html',
            controller: 'adminRoleController',
            controllerAs: 'vm'
        }).state('triangular.forms-File', {
            url: '/adminFile',
            templateUrl: 'app/admin/file/FileList.tmpl.html',
            controller: 'adminFileController',
            controllerAs: 'vm'
        });
    }
})();
