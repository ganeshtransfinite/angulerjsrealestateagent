(function() {
    'use strict';

    angular
        .module('app', [
            'ui.router',  
            'triangular',
            'ngAnimate', 'ngCookies', 'ngSanitize', 'ngMessages', 'ngMaterial','ngStorage',
            'googlechart', 'chart.js', 'linkify', 'ui.calendar', 'angularMoment',   'uiGmapgoogle-maps', 'hljs',
            'md.data.table', angularDragula(angular), 'ngFileUpload','ngMaterialDatePicker',  
            // 'seed-module',
            // uncomment above to activate the example seed module
            'app.translate',
            // only need one language?  if you want to turn off translations
            // comment out or remove the 'app.translate', line above
            
            // dont need permissions?  if you want to turn off permissions
            // comment out or remove the 'app.permission', line above
            // also remove 'permission' from the first line of dependencies
            // https://github.com/Narzerus/angular-permission see here for why
            'app.examples',  'app.admin.authentication','app.module.admin' ,'app.module.pms','app.examples.dashboards'

        ])

        // set a constant for the API we are connecting to
        .constant('API_CONFIG', {
            'url':  'http://triangular-api.oxygenna.com/'
        });
})();
