(function () {
    'use strict';

    angular
            .module('app.examples.dashboards')
            .directive('chartjsPropertyavalineWidget', chartjsPropertyavalineWidget);

    /* @ngInject */
    function chartjsPropertyavalineWidget($timeout,sessionobject, $interval) {
        // Usage:
        //
        // Creates:
        //
        var directive = {
            require: 'triWidget',
            link: link,
            restrict: 'A'
        };
        return directive;

        function link($scope, $element, attrs, widgetCtrl) {
            widgetCtrl.setLoading(true);
            var vm = {};
            $timeout(function () {
                widgetCtrl.setLoading(false);
                //  randomData();
            }, 1500);

            widgetCtrl.setMenu({
                icon: 'zmdi zmdi-more-vert',
                items: [{
                        icon: 'zmdi zmdi-refresh',
                        title: 'Refresh',
                        click: function () {
                            $interval.cancel($scope.intervalPromise);
                            widgetCtrl.setLoading(true);
                            $timeout(function () {

                                widgetCtrl.setLoading(false);
                                vm.load();//  randomData();
                            }, 1500);
                        }
                    } 
//                    , {
//                        icon: 'zmdi zmdi-print',
//                        title: 'Print',
//                         click: function () {
//                         sessionobject.printIt("printpropertclose");
//                         }
//                         
//                    }
                ]
            });

            vm.load = function ()
            {
                {
                    $scope.PropertyRentSaleAVA = {};
                    $scope.PropertyRentSaleAVA = {
                        chart: 'chart-bar',
                        type: 'Monthly',
                        options: {
                            datasetFill: false,
                            responsive: true
                        },
                    };
                    var s1 = "Monthly Property Rent Available"  ;
                    var db = [$scope.deshborddata[s1]];
                    var labelx = sessionobject.labelXData(db);
                    var data_count = sessionobject.getData(db, labelx);
                    $scope.PropertyRentSaleAVA.Monthly = {
                        labels: labelx,
                        series: [s1 ],
                        data: data_count
                    };


                }

                {
                    var s1 = "Quarter Property Rent Available" ;
                    var db = [$scope.deshborddata[s1]];
                    var labelx = sessionobject.labelXData(db);
                    var data_count = sessionobject.getData(db, labelx);
                    $scope.PropertyRentSaleAVA.Quarter = {
                        labels: labelx,
                        series: [s1 ],
                        data: data_count
                    };

                }

                {
                    var s1 = "Year Property Rent Available" ;
                    var db = [$scope.deshborddata[s1]];
                    var labelx = sessionobject.labelXData(db);
                    var data_count = sessionobject.getData(db, labelx);
                    $scope.PropertyRentSaleAVA.Year = {
                        labels: labelx,
                        series: [s1 ],
                        data: data_count
                    };
                    for (var i = 0; i < labelx.length; i++) {
                        labelx[i]=labelx[i].toString().substr(0, 4)
                    }

                }
            }
            vm.load();
//            $scope.lineChart = {
//                labels: ['January', 'February', 'March', 'April', 'May'],
//                series: ['Pageviews', 'Visits', 'Sign ups'],
//                options: {
//                    datasetFill: false,
//                    responsive: true
//                },
//                data: []
//            };
//
//            function randomData() {
//                $scope.lineChart.data = [];
//                for(var series = 0; series < $scope.lineChart.series.length; series++) {
//                    var row = [];
//                    for(var label = 0; label < $scope.lineChart.labels.length; label++) {
//                        row.push(Math.floor((Math.random() * 100) + 1));
//                    }
//                    $scope.lineChart.data.push(row);
//                }
//            }

            // Simulate async data update
            //    ./   $scope.intervalPromise = $interval(vm.load(), 5000);
        }
    }
})();