
(function () {
    'use strict';
    angular
            .module('app.module.pms', ['sessionmdule',
                'app.admin.authentication', 'app.module.admin'
            ]).directive('currencyMask', function ($timeout, $filter) {
        return {
            restrict: 'A',
            require: '?ngModel',
            link: function (scope, elem, attrs, ctrl) {
                var applyCurrencyFilter, errorPrefix;
                errorPrefix = 'VTEX ngCurrencyMask';
                if (!ctrl) {
                    throw new Error(errorPrefix + " requires ngModel!");
                }
                if (!/input/i.test(elem[0].tagName)) {
                    throw new Error(errorPrefix + " should be binded to <input />.");
                }

                applyCurrencyFilter = function (value) {
                    if (value === null || value === undefined) {
                        value = ctrl.$viewValue || elem[0].value;
                    }
                    if (value !== null) {
                        value = value.toString();
                        if (value.length > 3) {
                            var v1 = parseFloat(value.replace(/,/g, ''));
                            elem[0].value = formatNumber(v1);// $filter('currency')(v1, '', 0);
                        }
                        return elem[0].value;
                    }
                };

                function formatNumber(num) {
                    var n1, n2;
                    num = num + '' || '';
                    // works for integer and floating as well
                    n1 = num.split('.');
                    n2 = n1[1] || null;
                    n1 = n1[0].replace(/(\d)(?=(\d\d)+\d$)/g, "$1,");
                    num = n2 ? n1 + '.' + n2 : n1;
                    return num;
                }
                ;
//
                elem[0].addEventListener('keyup', function () {
                    return applyCurrencyFilter();
                });
//                elem[0].addEventListener('change', function () {
//                    return applyCurrencyFilter();
//                });
//                ctrl.$parsers.unshift(Utils.toIntCents);
//                ctrl.$formatters.unshift(Utils.toFloatString);
                return $timeout(applyCurrencyFilter);
            }
        };
    }).directive('fixedHeader', fixedHeader);

    fixedHeader.$inject = ['$timeout'];

    function fixedHeader($timeout) {
        return {
            restrict: 'A',
            link: link
        };

        function link($scope, $elem, $attrs, $ctrl) {
            var elem = $elem[0];

            // wait for data to load and then transform the table
            $scope.$watch(tableDataLoaded, function(isTableDataLoaded) {
                if (isTableDataLoaded) {
                    transformTable();
                }
            });

            function tableDataLoaded() {
                // first cell in the tbody exists when data is loaded but doesn't have a width
                // until after the table is transformed
                var firstCell = elem.querySelector('tbody tr:first-child td:first-child');
                return firstCell && !firstCell.style.width;
            }

            function transformTable() {
                // reset display styles so column widths are correct when measured below
                angular.element(elem.querySelectorAll('thead, tbody, tfoot')).css('display', '');

                // wrap in $timeout to give table a chance to finish rendering
                $timeout(function () {
                    // set widths of columns
                    angular.forEach(elem.querySelectorAll('tr:first-child th'), function (thElem, i) {

                        var tdElems = elem.querySelector('tbody tr:first-child td:nth-child(' + (i + 1) + ')');
                        var tfElems = elem.querySelector('tfoot tr:first-child td:nth-child(' + (i + 1) + ')');

                        var columnWidth = tdElems ? tdElems.offsetWidth : thElem.offsetWidth;
                        if (tdElems) {
                            tdElems.style.width = columnWidth + 'px';
                        }
                        if (thElem) {
                            thElem.style.width = columnWidth + 'px';
                        }
                        if (tfElems) {
                            tfElems.style.width = columnWidth + 'px';
                        }
                    });

                    // set css styles on thead and tbody
                    angular.element(elem.querySelectorAll('thead, tfoot')).css('display', 'block');

                    angular.element(elem.querySelectorAll('tbody')).css({
                        'display': 'block',
                        'height': $attrs.tableHeight || 'inherit',
                        'overflow': 'auto'
                    });

                    // reduce width of last column by width of scrollbar
                    var tbody = elem.querySelector('tbody');
                    var scrollBarWidth = tbody.offsetWidth - tbody.clientWidth;
                    if (scrollBarWidth > 0) {
                        // for some reason trimming the width by 2px lines everything up better
                        scrollBarWidth -= 2;
                        var lastColumn = elem.querySelector('tbody tr:first-child td:last-child');
                        lastColumn.style.width = (lastColumn.offsetWidth - scrollBarWidth) + 'px';
                    }
                });
            }
        }
    };

})();
