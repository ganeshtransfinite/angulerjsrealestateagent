(function () {
    'use strict';
    angular
            .module('sessionmdule', [
            ])
            .service('sessionobject', sessionobject)
            .service('base64', base64)
            .filter('cut', cut);
    function cut() {
        return filterFunction;
        ////////////////

        function filterFunction(value, wordwise, max, tail) {
            if (!value) {
                return '';
            }

            max = parseInt(max, 10);
            if (!max) {
                return value;
            }
            if (value.length <= max) {
                return value;
            }

            value = value.substr(0, max);
            if (wordwise) {
                var lastspace = value.lastIndexOf(' ');
                if (lastspace !== -1) {
                    value = value.substr(0, lastspace);
                }
            }

            return value + (tail || ' …');
        }
    }
    function base64($localStorage, $state, $http, $q, $timeout, $mdSidenav, $window) {
        var vm = this;
        this._PADCHAR = "=";
        this._ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        this._VERSION = "1.0";
        this._getbyte64 = function (s, i) {
            // This is oddly fast, except on Chrome/V8.
            // Minimal or no improvement in performance by using a
            // object with properties mapping chars to value (eg. 'A': 0)

            var idx = vm._ALPHA.indexOf(s.charAt(i));
            if (idx === -1) {
                throw "Cannot decode base64";
            }

            return idx;
        };
        this._decode = function (s) {
            var pads = 0,
                    i,
                    b10,
                    imax = s.length,
                    x = [];
            s = String(s);
            if (imax === 0) {
                return s;
            }

            if (imax % 4 !== 0) {
                throw "Cannot decode base64";
            }

            if (s.charAt(imax - 1) === vm._PADCHAR) {
                pads = 1;
                if (s.charAt(imax - 2) === vm._PADCHAR) {
                    pads = 2;
                }

                // either way, we want to ignore this last block
                imax -= 4;
            }

            for (i = 0; i < imax; i += 4) {
                b10 = (vm._getbyte64(s, i) << 18) | (vm._getbyte64(s, i + 1) << 12) | (vm._getbyte64(s, i + 2) << 6) | vm._getbyte64(s, i + 3);
                x.push(String.fromCharCode(b10 >> 16, (b10 >> 8) & 0xff, b10 & 0xff));
            }

            switch (pads) {
                case 1:
                    b10 = (vm._getbyte64(s, i) << 18) | (vm._getbyte64(s, i + 1) << 12) | (vm._getbyte64(s, i + 2) << 6);
                    x.push(String.fromCharCode(b10 >> 16, (b10 >> 8) & 0xff));
                    break;
                case 2:
                    b10 = (vm._getbyte64(s, i) << 18) | (vm._getbyte64(s, i + 1) << 12);
                    x.push(String.fromCharCode(b10 >> 16));
                    break;
            }

            return x.join("");
        }


        this._getbyte = function (s, i) {
            var x = s.charCodeAt(i);
            if (x > 255) {
                throw "INVALID_CHARACTER_ERR: DOM Exception 5";
            }

            return x;
        }


        this._encode = function (s) {
            if (arguments.length !== 1) {
                throw "SyntaxError: exactly one argument required";
            }

            s = String(s);
            var i,
                    b10,
                    x = [],
                    imax = s.length - s.length % 3;
            if (s.length === 0) {
                return s;
            }

            for (i = 0; i < imax; i += 3) {
                b10 = (vm._getbyte(s, i) << 16) | (vm._getbyte(s, i + 1) << 8) | vm._getbyte(s, i + 2);
                x.push(vm._ALPHA.charAt(b10 >> 18));
                x.push(vm._ALPHA.charAt((b10 >> 12) & 0x3F));
                x.push(vm._ALPHA.charAt((b10 >> 6) & 0x3f));
                x.push(vm._ALPHA.charAt(b10 & 0x3f));
            }

            switch (s.length - imax) {
                case 1:
                    b10 = vm._getbyte(s, i) << 16;
                    x.push(vm._ALPHA.charAt(b10 >> 18) + vm._ALPHA.charAt((b10 >> 12) & 0x3F) + vm._PADCHAR + vm._PADCHAR);
                    break;
                case 2:
                    b10 = (vm._getbyte(s, i) << 16) | (vm._getbyte(s, i + 1) << 8);
                    x.push(vm._ALPHA.charAt(b10 >> 18) + vm._ALPHA.charAt((b10 >> 12) & 0x3F) + vm._ALPHA.charAt((b10 >> 6) & 0x3f) + vm._PADCHAR);
                    break;
            }

            return x.join("");
        };
    }

    function sessionobject($localStorage, $state, $http, $q, $timeout, $filter, $mdSidenav, $window, base64) {
        var sessionobject = this;
        this.isAuthenticate = isAuthenticate;
        this.chkURL = chkURL;
        this.getFileHttpHeader = getFileHttpHeader;
        this.getHttpHeader = getHttpHeader;
        this.setURL = setURL;
        this.comboboxlist = {
        };
        this.initComboCountInitALL = function (table, type, flag) {
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertytypeForm", "Property", null, null, flag, type);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("CustomerForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertypriorityForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("CityForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertylocalityForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertylbedroomsForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertyfacingForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertyfurnishedForm", "Property", null, null, flag, type);


            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertytobeForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("FlatamenitiesForm", "Property", null, null, flag, type);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("SocietyamenitiesForm", "Property", null, null, flag, type);

            }
        }
        this.initComboCountALL = function (table, flag, requestPromise) {


            if (table === 'Agent' || table === 'ALL') {
                sessionobject.initCombocount("CityForm", "Agent", requestPromise, null, flag, 1);
            }
            if (table === 'Agent' || table === 'ALL') {
                sessionobject.initCombocount("PropertylocalityForm", "Agent", requestPromise, null, flag, 1);
            }
            if (table === 'Agent' || table === 'ALL') {
                sessionobject.initCombocount("ModeoperationForm", "Agent", requestPromise, null, flag, 1);
            }
            if (table === 'Inquiry' || table === 'ALL') {
                sessionobject.initCombocount("PropertypriorityForm", "Inquiry", requestPromise, null, flag, 1);
            }
            if (table === 'Inquiry' || table === 'ALL') {
                sessionobject.initCombocount("PropertylbedroomsForm", "Inquiry", requestPromise, null, flag, 1);
            }
            if (table === 'Inquiry' || table === 'ALL') {
                sessionobject.initCombocount("CityForm", "Inquiry", requestPromise, null, flag, 1);
            }
            if (table === 'Inquiry' || table === 'ALL') {
                sessionobject.initCombocount("PropertylocalityForm", "Inquiry", requestPromise, null, flag, 1);
            }
            if (table === 'Inquiry' || table === 'ALL') {
                sessionobject.initCombocount("PropertyfacingForm", "Inquiry", requestPromise, null, flag, 1);
            }
            if (table === 'Inquiry' || table === 'ALL') {
                sessionobject.initCombocount("PropertytobeForm", "Inquiry", requestPromise, null, flag, 1);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertytypeForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertytypeForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertytypeForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertytypeForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("CustomerForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("CustomerForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("CustomerForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("CustomerForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertypriorityForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertypriorityForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertypriorityForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertypriorityForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("CityForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("CityForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("CityForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("CityForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertylocalityForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertylocalityForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertylocalityForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertylocalityForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertylbedroomsForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertylbedroomsForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertylbedroomsForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertylbedroomsForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertyfacingForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertyfacingForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertyfacingForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertyfacingForm", "Property", requestPromise, null, flag, 4);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertyfurnishedForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertyfurnishedForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertyfurnishedForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertyfurnishedForm", "Property", requestPromise, null, flag, 4);

            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("PropertytobeForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("PropertytobeForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("PropertytobeForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("PropertytobeForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("FlatamenitiesForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("FlatamenitiesForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("FlatamenitiesForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("FlatamenitiesForm", "Property", requestPromise, null, flag, 4);
            }
            if (table === 'Property' || table === 'ALL') {
                sessionobject.initCombocount("SocietyamenitiesForm", "Property", requestPromise, null, flag, 1);
                sessionobject.initCombocount("SocietyamenitiesForm", "Property", requestPromise, null, flag, 2);
                sessionobject.initCombocount("SocietyamenitiesForm", "Property", requestPromise, null, flag, 3);
                sessionobject.initCombocount("SocietyamenitiesForm", "Property", requestPromise, null, flag, 4);
                sessionobject.initCombocount("PropertyForm", "Property", requestPromise, 'apt', flag);
            }
            if (table === 'Rolepermission' || table === 'ALL') {
                sessionobject.initCombocount("RoleForm", "Rolepermission", requestPromise, null, flag, 1);
            }
            if (table === 'Test' || table === 'ALL') {
                sessionobject.initCombocount("FlatamenitiesForm", "Test", requestPromise, null, flag, 1);
            }
            if (table === 'Test' || table === 'ALL') {
                sessionobject.initCombocount("SocietyamenitiesForm", "Test", requestPromise, null, flag, 1);
            }
            if (table === 'Propertylocality' || table === 'ALL') {
                sessionobject.initCombocount("CityForm", "Propertylocality", requestPromise, null, flag, 1);
            }
            if (table === 'User' || table === 'ALL') {
                sessionobject.initCombocount("RoleForm", "User", requestPromise, null, flag, 1);
            }
            if (table === 'Customer' || table === 'ALL') {
                sessionobject.initCombocount("CustomertypeForm", "Customer", requestPromise, null, flag, 1);
            }
            if (table === 'Customer' || table === 'ALL') {
                sessionobject.initCombocount("OccupationForm", "Customer", requestPromise, null, flag, 1);
            }
            if (table === 'Customer' || table === 'ALL') {
                sessionobject.initCombocount("CityForm", "Customer", requestPromise, null, flag, 1);
            }



        };
        this.chkData = function (data) {
            if (data.toString().indexOf("undefined") > 0) {
                return "";
            }
            if (data.toString().indexOf(":true") === -1) {
                return "";
            }
            return data;
        };
        this.initComboALL = function (flag, requestPromise) {

            sessionobject.initCombo("PropertyfurnishedForm", null, null, requestPromise, flag);
            sessionobject.initCombo("RoleForm", null, null, requestPromise, flag);
            sessionobject.initCombo("OccupationForm", null, null, requestPromise, flag);
            sessionobject.initCombo("GenderForm", null, null, requestPromise, flag);
            sessionobject.initCombo("CityForm", null, null, requestPromise, flag);
            sessionobject.initCombo("PropertytypeForm", null, null, requestPromise, flag);
            sessionobject.initCombo("PropertylbedroomsForm", null, null, requestPromise, flag);
            sessionobject.initCombo("BudgetrentForm", null, null, requestPromise, flag);

            sessionobject.initCombo("PropertytobeForm", null, null, requestPromise, flag);
            sessionobject.initCombo("InquiryForm", null, null, requestPromise, flag);
            sessionobject.initCombo("FlatamenitiesForm", null, null, requestPromise, flag);
            sessionobject.initCombo("PropertyForm", null, null, requestPromise, flag);
            sessionobject.initCombo("ModeoperationForm", null, null, requestPromise, flag);
            sessionobject.initCombo("DatarecoveryForm", null, null, requestPromise, flag);
            sessionobject.initCombo("SocietyamenitiesForm", null, null, requestPromise, flag);
            sessionobject.initCombo("CustomertypeForm", null, null, requestPromise, flag);
            sessionobject.initCombo("PropertypriorityForm", null, null, requestPromise, flag);
            sessionobject.initCombo("RolepermissionForm", null, null, requestPromise, flag);
            sessionobject.initCombo("BudgetsaleForm", null, null, requestPromise, flag);
            sessionobject.initCombo("TestForm", null, null, requestPromise, flag);
            sessionobject.initCombo("PropertylocalityForm", null, null, requestPromise, flag);
            sessionobject.initCombo("PropertyfacingForm", null, null, requestPromise, flag);
            sessionobject.initCombo("UserForm", null, null, requestPromise, flag);
            sessionobject.initCombo("OperationForm", null, null, requestPromise, flag);
            sessionobject.initCombo("CustomerForm", null, null, requestPromise, flag);




        }
        this.uploadfiletoserver = function (type, requestPromise, commonobject, formobject) {
            if (commonobject["select" + type] != null && commonobject["select" + type].length > 0) {
                var formData = new FormData();
                // alert(commonobject.selectimage9[0].name);
                formData.append("userid", $localStorage.clientloginuserid.userid);
                formData.append("file", commonobject["select" + type][0]);
                formData.append("type", "property");
                if (commonobject[type] !== null
                        && commonobject[type] !== undefined) {
                    formData.append("fileid", commonobject[type].fileid);
                } else {
                    formData.append("fileid", 0);
                }
                var httpPromise = $http(this.getFileHttpHeader('/uploaddata', formData, commonobject.role))
                        .success(function (data, status) {
                            //sessionobject.showERROR(status);
                            if (!sessionobject.replyChk(data)) {
                                return;
                            }
                            formobject["select" + type] = data;
                        }).error(function (data, status) {
                    //sessionobject.showERROR(status);
                });
                requestPromise.push(httpPromise);
            }
        }


        this.capitalizeFirstLetter = function (string) {
            return string.charAt(0).toUpperCase() + string.slice(1);
        }
        this.initCombocount = function (type, table, requestPromise, col, flag, tebletype) {

            if (flag && angular.isDefined(sessionobject.comboboxlist[table.toLowerCase() + type.toLowerCase().replace("form", "") +
                    "idcntlist" + tebletype])) {
                sessionobject.comboboxlist[table.toLowerCase() + type.toLowerCase().replace("form", "") +
                        "idcntlist" + (col === null ? '' : col) ] = this.comboboxlist[table.toLowerCase() +
                        type.toLowerCase().replace("form", "") + "idcntlist" + tebletype + (col === null ? '' : col)];
                return sessionobject.comboboxlist[table.toLowerCase() + type.toLowerCase().replace("form", "") +
                        "idcntlist" + (col === null ? '' : col) ];
            } else {
                var httpPromise = null;
                httpPromise =
                        $http.get(sessionobject.getURL("/get" + type + "combolist.json", "admin") + "&table=" + table + "&type=" + tebletype + (col === null ? '' : "&col" + "=" + col), "admin")
                        .success(function (response) {
                            if (!sessionobject.replyChk(response)) {
                                return;
                            }
                            sessionobject.comboboxlist[table.toLowerCase() + type.toLowerCase().replace("form", "") + "idcntlist" + tebletype + (col === null ? '' : col)] = response;
                            sessionobject.comboboxlist[table.toLowerCase() + type.toLowerCase().replace("form", "") + "idcntlist" + (col === null ? '' : col)] = response;
                        }).
                        error(function (response) {
                            sessionobject.showERROR(response);
                        });
                if (requestPromise !== null)
                    requestPromise.push(httpPromise);
            }
        }
        this.initCombo = function (type, col, val, requestPromise, flag) {

            var httpPromise = null;
            if (flag && angular.isDefined(sessionobject.comboboxlist[type.toLowerCase().replace("form", "") + "idlist"])) {
                return sessionobject.comboboxlist[type];
            } else {

                httpPromise =
                        $http.get(this.getURL("/get" + type + "combolist.json", 'user') + (col === null ? '' : "&" + col + "=" + val))
                        .success(function (response) {
                            if (!sessionobject.replyChk(response)) {
                                return;
                            }
                            sessionobject.comboboxlist[type.toLowerCase().replace("form", "") + "idlist"] = response;
                        }).
                        error(function (response) {
                            sessionobject.showERROR(response);
                        });
                if (requestPromise !== null)
                    requestPromise.push(httpPromise);
            }

        };
        this.loadimage = function (id) {

            var httpPromise = null;
            if ($localStorage.image === undefined) {
                $localStorage.image = {};
            }
            if ($localStorage.image[id] !== undefined) {
                return $localStorage.image[id];
            } else {
                var formobject = {
                    fileid: id
                };
                $http(sessionobject.getHttpHeader('/editfile.json.json', formobject, "admin"))
                        .success(function (data) {
                            if (data.errors) {
                                // Showing errors.
                                sessionobject.showERROR(data.errors);
                            } else {
                                $localStorage.image[id] = data.content;
                                return data.content;
                            }
                        });


            }

        };

        this.simulateQuery = false;
        this.querySearch = querySearch;
        function querySearch(query, list) {
            var results = query ? list.filter(sessionobject.createFilterForText(query)) : list,
                    deferred;
            if (sessionobject.simulateQuery) {
                deferred = $q.defer();
                $timeout(function () {
                    deferred.resolve(results);
                }, Math.random() * 1000, false);
                return deferred.promise;
            } else {
                return results;
            }
        }
        this.createFilterForText = createFilterForText;
        function createFilterForText(query) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(state) {

                return (angular.lowercase(state.text).indexOf(lowercaseQuery) >= 0);
            };
        }
        function getFileHttpHeader(url1, formData, role) {


            return {method: 'POST',
                url: getURL(url1, role), //!++ Set your own location
                // if you set Content-Type, ; boundary= won't be in header so set undefined which will force the browser to fill
                //x headers: { 'Content-Type': 'multipart/form-data' },
                headers: {
                    'Content-Type': undefined,
                    // 'Access-Control-Request-Headers': 'Origin',
                    //     'Access-Control-Request-Method': 'POST'
                },
                data: formData,
                transformRequest: function (data) {
                    return data;
                }
            }
        }
        this.addTodo = function (ev, id, commonobject, tablename, formobject) {
            var requestPromise = [];
            var httpPromise = null;
            commonobject[formobject] = commonobject.createObject();
            commonobject[formobject].selectuserid = $localStorage.clientloginuserid.userid;
            commonobject.files = null;
            //           $q.all(requestPromise).then(function (data) {
            if (id !== 0) {
                commonobject[formobject][tablename + 'id'] = id;
                $http(sessionobject.getHttpHeader('/edit' + tablename + '.json', commonobject[formobject], commonobject.role))
                        .success(function (data) {
                            if (data.errors) {
                                // Showing errors.
                                sessionobject.showERROR(data.errors);
                            } else {
                                if (!sessionobject.replyChk(data)) {
                                    return;
                                }
                                commonobject[formobject] = data;
                                commonobject.showDiloag(ev, id);
                            }
                        });
            } else {
                //  alert('seccond dialog');
                commonobject.showDiloag(ev, id);
            }
            //         });

        };
        this.save = function (ev, id, triLoaderService, commonobject, tablename, formobject)
        {

            var str = angular.toJson(formobject);
            //  alert(angular.toJson(formobject));
            var httpPromise = $http(sessionobject.getHttpHeader('/save' + tablename + '.json', formobject, commonobject.role))
                    .success(function (data) {
                        if (data.errors) {
                            sessionobject.showERROR(data.errors);
                        } else {

                            triLoaderService.setLoaderActive(false);
                            if (!sessionobject.replyChk(data)) {
                                return;
                            }
                            //   commonobject.tableobject.getPropertylocality();
                            if (angular.isDefined(sessionobject.comboboxlist[tablename + 'idlist'])) {
                                delete sessionobject.comboboxlist[tablename.toLowerCase() + 'idlist'];
                            }
                            if (id !== 0) {
                                for (var index = 0; index < commonobject.tableobject.contents.items.length; ++index) {
                                    if (commonobject.tableobject.contents.items[index][tablename.toLowerCase() + 'id'] === id) {
                                        commonobject.tableobject.contents.items[index] = data;
                                    }
                                }
                                formobject[tablename.toLowerCase() + 'id'] = 0;
                            } else {

                                formobject[tablename.toLowerCase() + 'id'] = 0;
                                commonobject.tableobject.contents.items.splice(0, 0, data);//insert(0,data);
                            }
                            if (tablename !== 'Property') {
                                sessionobject.initComboCountALL(tablename, false, null);
                            }
                            sessionobject.initCombo(tablename + "Form", null, null, null, false);
                        }
                    });
        };

        this.format = function (fun, val, arg1) {
            return $filter(fun)(val, arg1)
        };

        // Internal method
        this.showAlert = function ($mdDialog, vtitle, vtextContent, ev) {
            $mdDialog.show(
                    $mdDialog.alert()
                    .parent(angular.element(document.querySelector('#popupContainer')))
                    .clickOutsideToClose(true)
                    .title(vtitle)
                    .textContent(vtextContent)
                    .ariaLabel(vtitle)
                    .ok('OK')
                    .targetEvent(ev)
                    );

//            $scope.vtitle = vtitle;
//            $scope.vtextContent = vtextContent;
//
//            $mdDialog.show({
//                templateUrl: 'app/pms/ShowDialog.html',
//                scope: $scope,
//                controller: function DialogController($scope, $mdDialog) {
//                    $scope.closeDialog = function () {
//                        $mdDialog.cancel();
//                    }
//                }
//            }).then(function (answer) {
//
//            }, function () {
//
//            });
        };

        this.removeTodo = function (ev, commonobject, $mdDialog, triLoaderService, tablenme, msg) {
            var data = "";
            for (var i = 0; i < commonobject.tableobject.selected.length; i++) {
                data += commonobject.tableobject.selected[i][tablenme + 'id'] + ",";
            }
            var confirm = $mdDialog.confirm()
                    .title('Would you like to delete your ' + msg + " '")
                    .textContent('Selectd Propertylocality id ' + data + ".")
                    .ariaLabel('Delete Confirm')
                    .targetEvent(ev)
                    .ok('Yes')
                    .cancel('No');
            $mdDialog.show(confirm).then(function () {
                triLoaderService.setLoaderActive(true);
                //  formobject.propertylocalityid = id;
                //alert(id);
                $http(sessionobject.getHttpHeader('/delete' + tablenme + '.json', data, commonobject.role)).success(function (data) {
                    if (data.errors) {
                        // Showing errors.'
                        sessionobject.showERROR(data.errors);
                        //       formobject.propertylocalityid = 0;
                        triLoaderService.setLoaderActive(false);
                    } else {
                        triLoaderService.setLoaderActive(false);
                        if (!sessionobject.replyChk(data)) {
                            return;
                        }

                        commonobject.tableobject.getTable();
                        tablenme = sessionobject.capitalizeFirstLetter(tablenme);
                        sessionobject.initCombo(tablenme + "Form", null, null, null, false);
                        sessionobject.initComboCountALL(tablenme, false, null);
                    }
                });
            }, function () {
                //  $scope.status = 'You decided to keep your debt.';
            });
        }

        this.activate = function (vm, $scope, commonobject) {
            var bookmark;
            $scope.$watch('vm.query.filter', function (newValue, oldValue) {
                if (!oldValue) {
                    bookmark = vm.query.page;
                }
                if (newValue !== oldValue) {
                    vm.query.page = 1;
                }
                if (!newValue) {
                    vm.query.page = bookmark;
                }
                vm.getTable();
            });
        }


        this.labelXData = function (data) {
            var label = [];
            var label_str = [];
            for (var i = 0; i < data.length; i++) {
                for (var k = 0; k < data[i].length; k++) {
                    var mon = (data[i][k].month <= 9 ? ("0" + data[i][k].month) : data[i][k].month);
                    var val = parseInt(data[i][k].year + "" + mon);
                    if (label.indexOf(val) == -1)
                        label.push(val);
                }
            }

            label.sort(sessionobject.sortNumber);
            for (var i = 0; i < label.length; i++) {
                label_str.push(label[i].toString().substr(0, 4) + "-" + label[i].toString().substr(4, 2));
            }
            return label_str;
        };

        this.labelXDataText = function (data) {
            var label = [];
            var label_str = [];
            for (var i = 0; i < data.length; i++) {
                for (var k = 0; k < data[i].length; k++) {

                    var val = (data[i][k].name);
                    if (label.indexOf(val) == -1)
                        label.push(val);
                }
            }

            label.sort();
            for (var i = 0; i < label.length; i++) {
                label_str.push(label[i].toString());
            }
            return label_str;
        };
        this.sortNumber = function (a, b) {
            return a - b;
        };
        this.getData = function (data, label) {
            var count_data = [];

            for (var i = 0; i < data.length; i++) {
                count_data.push([]);
                for (var k = 0; k < label.length; k++) {
                    count_data[i].push(0);
                }
                for (var k = 0; k < data[i].length; k++) {
                    var mon = (data[i][k].month <= 9 ? ("0" + data[i][k].month) : data[i][k].month);
                    count_data[i][label.indexOf(data[i][k].year + "-" + mon)] = data[i][k].count;
                    //  label.push(parseInt(data[i][k].year + "" + data[i][k].month));
                }
            }

            return count_data;


        };

        this.getDataText = function (data, label) {
            var count_data = [];

            for (var i = 0; i < data.length; i++) {
                count_data.push([]);
                for (var k = 0; k < label.length; k++) {
                    count_data[i].push(0);
                }
                for (var k = 0; k < data[i].length; k++) {

                    count_data[i][label.indexOf(data[i][k].name)] = data[i][k].count;
                    //  label.push(parseInt(data[i][k].year + "" + data[i][k].month));
                }
            }

            return count_data;


        };

        this.openSidebar = function (id) {
            $mdSidenav(id).toggle();
        };
        this.getTable = function ($scope, vm, tablename, commonobject, formData) {
            $scope.promise = $timeout(function () {
                vm.promise = sessionobject.getServerTable(formData, vm, tablename, commonobject);
                vm.promise.then(function (tabledata) {
                    vm.contents = tabledata.data;
                    commonobject.contentstable = vm.contents;
                });
            }, 5);
        }

        this.getServerTable = function (formData, vm, tablename, commonobject) {

            return $http(sessionobject.getHttpHeader('/get' + tablename + 'list.json', formData, commonobject.role)).
                    success(function (data) {
                        var s1 = vm.query.order;
                        if (!sessionobject.replyChk(data)) {
                            return;
                        }

                        return data;
                    }).error(function (data, status) {
                sessionobject.showERROR(data.errors);
            });
        }

        function getHttpHeader(url1, data, role) {


            return     {
                method: 'POST',
                url: getURL(url1, role),
                data: angular.toJson(data),
                headers: {'Content-Type': 'application/json',
//                    'Access-Control-Request-Headers'
//                            : 'Origin',
//                    'Access-Control-Request-Method'
//                            : 'POST'
                }
            }

        }
        this.replyChk = function (data) {
            if (data === "LOGIN ERROR") {
                $state.go("authentication.login");
                return false;
            } else {
                return true;
            }
        }

        function setURL() {
            $localStorage.clientloginuserid = {
                userid: 0,
                auth: true,
                JSESSIONID: null,
                url: 'http://localhost:8084/WebPMStest'
            };
        }

        function chkURL() {
            if ($localStorage.clientloginuserid == null) {
                setURL();
            }
        }

        function isAuthenticate() {
            chkURL();
            if ($localStorage.clientloginuserid.userid == 0) {
                $state.go("authentication.login");
            } else {
                //    if (sessionobject.comboboxlist === undefined) {
                sessionobject.comboboxlist = $localStorage.clientloginuserid.comboboxlist;
                //  }
                return true;
            }

            //alert('From notify two');
        }


        this.getURL = getURL;
        function getURL(url, role) {
            if ($localStorage.clientloginuserid.userid !== 0) {
                return $localStorage.clientloginuserid.url + url + "?JSESSIONID=" + $localStorage.clientloginuserid.JSESSIONID + "&userid=" + $localStorage.clientloginuserid.userid + "&role=" + role;
            } else {
                return $localStorage.clientloginuserid.url + url;
            }
        }



        this.showERROR = showERROR;
        function showERROR(error) {
            alert(error);
        }

        this.printIt = function (divcontent) {
//   var contents = $('#'+divcontent).html();
//
// var mywindow = window.open('', 'new div', 'height=400,width=600');
//    mywindow.document.write('<html><head><title>U and V Propert</title>');
//    mywindow.document.write(' <link rel="stylesheet" href="app/app.css">');
//    mywindow.document.write('</head><body >');
//    mywindow.document.write(contents);
//    mywindow.document.write('</body></html>'); 
//
//    mywindow.print();
//    mywindow.close();

            var contents = $('#' + divcontent).html();
            var frame1 = $('<iframe />');
            frame1[0].name = "frame1";
            frame1.css({"position": "absolute", "top": "-1000000px"});
            $("body").append(frame1);
            var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
            frameDoc.document.open();
            //Create a new HTML document.
            frameDoc.document.write('<html><head><title>U and V Property</title>');
            frameDoc.document.write('</head><body  >');
            //Append the external CSS file.

            frameDoc.document.write(' <link rel="stylesheet" href="app/app.css">');
            //Append the DIV contents.
            frameDoc.document.write(contents);
            frameDoc.document.write('</body></html>');
            frameDoc.document.close();
            setTimeout(function () {
                $window.frames["frame1"].focus();
                $window.frames["frame1"].print();
                frame1.remove();
            }, 500);
//            var table = document.getElementById('printArea').innerHTML;
//            var myWindow = $window.open('', '', 'width=800, height=600');
//            myWindow.document.write(table);
//            myWindow.print();
        };
        this.tableExport = function (options) {
            var tablevm = this;
            var defaults = {
                separator: ',',
                ignoreColumn: [],
                tableName: 'yourTableName',
                type: 'csv',
                pdfFontSize: 14,
                pdfLeftMargin: 20,
                escape: 'true',
                htmlContent: 'false',
                consoleLog: 'false',
                content: 'printArea'
            };
            var content_data = '';
            tablevm.parseString = function (data) {

                if (defaults.htmlContent == 'true') {
                    content_data = data.html().trim();
                } else {
                    content_data = data.text().trim();
                }

                if (defaults.escape == 'true') {
                    content_data = escape(content_data);
                }



                return content_data;
            };
            var options = $.extend(defaults, options);
            var el = document.getElementById(defaults.content);
            if (defaults.type == 'csv' || defaults.type == 'txt') {

                // Header
                var tdData = "";
                $(el).find('thead').find('tr').each(function () {
                    tdData += "\n";
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                tdData += '"' + tablevm.parseString($(this)) + '"' + defaults.separator;
                            }
                        }

                    });
                    tdData = $.trim(tdData);
                    tdData = $.trim(tdData).substring(0, tdData.length - 1);
                });
                // Row vs Column
                $(el).find('tbody').find('tr').each(function () {
                    tdData += "\n";
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                tdData += '"' + tablevm.parseString($(this)) + '"' + defaults.separator;
                            }
                        }
                    });
                    //tdData = $.trim(tdData);
                    tdData = $.trim(tdData).substring(0, tdData.length - 1);
                });
                var base64data = "base64," + base64._encode(tdData);
                $window.open('data:application/' + defaults.type + ';filename=exportData;' + base64data);
            } else if (defaults.type == 'sql') {

                // Header
                var tdData = "INSERT INTO `" + defaults.tableName + "` (";
                $(el).find('thead').find('tr').each(function () {

                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                tdData += '`' + tablevm.parseString($(this)) + '`,';
                            }
                        }

                    });
                    tdData = $.trim(tdData);
                    tdData = $.trim(tdData).substring(0, tdData.length - 1);
                });
                tdData += ") VALUES ";
                // Row vs Column
                $(el).find('tbody').find('tr').each(function () {
                    tdData += "(";
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                tdData += '"' + tablevm.parseString($(this)) + '",';
                            }
                        }
                    });
                    tdData = $.trim(tdData).substring(0, tdData.length - 1);
                    tdData += "),";
                });
                tdData = $.trim(tdData).substring(0, tdData.length - 1);
                tdData += ";";
                //output
                //console.log(tdData);

                if (defaults.consoleLog == 'true') {
                    console.log(tdData);
                }

                var base64data = "base64," + base64._encode(tdData);
                $window.open('data:application/sql;filename=exportData;' + base64data);
            } else if (defaults.type == 'json') {

                var jsonHeaderArray = [];
                $(el).find('thead').find('tr').each(function () {
                    var tdData = "";
                    var jsonArrayTd = [];
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                jsonArrayTd.push(parseString($(this)));
                            }
                        }
                    });
                    jsonHeaderArray.push(jsonArrayTd);
                });
                var jsonArray = [];
                $(el).find('tbody').find('tr').each(function () {
                    var tdData = "";
                    var jsonArrayTd = [];
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                jsonArrayTd.push(tablevm.parseString($(this)));
                            }
                        }
                    });
                    jsonArray.push(jsonArrayTd);
                });
                var jsonExportArray = [];
                jsonExportArray.push({header: jsonHeaderArray, data: jsonArray});
                //Return as JSON
                //console.log(angular.toJson(jsonExportArray));

                //Return as Array
                //console.log(jsonExportArray);
                if (defaults.consoleLog == 'true') {
                    console.log(angular.toJson(jsonExportArray));
                }
                var base64data = "base64," + base64._encode(angular.toJson(jsonExportArray));
                $window.open('data:application/json;filename=exportData;' + base64data);
            } else if (defaults.type == 'xml') {

                var xml = '<?xml version="1.0" encoding="utf-8"?>';
                xml += '<tabledata><fields>';
                // Header
                $(el).find('thead').find('tr').each(function () {
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                xml += "<field>" + tablevm.parseString($(this)) + "</field>\n";
                            }
                        }
                    });
                });
                xml += '</fields>\n<data>';
                // Row Vs Column
                var rowCount = 1;
                $(el).find('tbody').find('tr').each(function () {
                    xml += '<row id="' + rowCount + '">';
                    var colCount = 0;
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                xml += "<column-" + colCount + ">" + tablevm.parseString($(this)) + "</column-" + colCount + ">\n";
                            }
                        }
                        colCount++;
                    });
                    rowCount++;
                    xml += '</row>\n';
                });
                xml += '</data></tabledata>'
                while (xml.indexOf("&") != -1) {
                    xml = xml.replace("&", "And");
                }
                if (defaults.consoleLog == 'true') {
                    console.log(xml);
                }

                var base64data = "base64," + base64._encode(xml);
                $window.open('data:application/xml;' + base64data, '_blank', 'toolbar=0,location=0,directories=0,status=0, scrollbars=1, resizable=1, copyhistory=1, menuBar=1, width=640,height=480, left=50, top=50');
            } else if (defaults.type == 'excel' || defaults.type == 'doc' || defaults.type == 'powerpoint') {
                //console.log($(this).html());
                var excel = "<table>";
                // Header
                $(el).find('thead').find('tr').each(function () {
                    excel += "<tr>";
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                excel += "<td>" + tablevm.parseString($(this)) + "</td>";
                            }
                        }
                    });
                    excel += '</tr>';
                });
                // Row Vs Column
                var rowCount = 1;
                $(el).find('tbody').find('tr').each(function () {
                    excel += "<tr>";
                    var colCount = 0;
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                excel += "<td>" + tablevm.parseString($(this)) + "</td>";
                            }
                        }
                        colCount++;
                    });
                    rowCount++;
                    excel += '</tr>';
                });
                excel += '</table>'

                if (defaults.consoleLog == 'true') {
                    console.log(excel);
                }

                var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:" + defaults.type + "' xmlns='http://www.w3.org/TR/REC-html40'>";
                excelFile += "<head>";
                excelFile += "<!--[if gte mso 9]>";
                excelFile += "<xml>";
                excelFile += "<x:ExcelWorkbook>";
                excelFile += "<x:ExcelWorksheets>";
                excelFile += "<x:ExcelWorksheet>";
                excelFile += "<x:Name>";
                excelFile += "{worksheet}";
                excelFile += "</x:Name>";
                excelFile += "<x:WorksheetOptions>";
                excelFile += "<x:DisplayGridlines/>";
                excelFile += "</x:WorksheetOptions>";
                excelFile += "</x:ExcelWorksheet>";
                excelFile += "</x:ExcelWorksheets>";
                excelFile += "</x:ExcelWorkbook>";
                excelFile += "</xml>";
                excelFile += "<![endif]-->";
                excelFile += "</head>";
                excelFile += "<body>";
                excelFile += excel;
                excelFile += "</body>";
                excelFile += "</html>";
                var base64data = "base64," + base64._encode(excelFile);
                $window.open('data:application/vnd.ms-' + defaults.type + ';filename=exportData.' + defaults.type + ';' + base64data);
            } else if (defaults.type == 'png') {
                html2canvas($(el), {
                    onrendered: function (canvas) {
                        var img = canvas.toDataURL("image/png");
                        window.open(img);
                    }
                });
            } else if (defaults.type == 'pdf') {

                var doc = new jsPDF('p', 'pt', 'a4', true);
                doc.setFontSize(defaults.pdfFontSize);
                // Header
                var startColPosition = defaults.pdfLeftMargin;
                $(el).find('thead').find('tr').each(function () {
                    $(this).filter(':visible').find('th').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                var colPosition = startColPosition + (index * 50);
                                doc.text(colPosition, 20, tablevm.parseString($(this)));
                            }
                        }
                    });
                });
                // Row Vs Column
                var startRowPosition = 20;
                var page = 1;
                var rowPosition = 0;
                $(el).find('tbody').find('tr').each(function (index, data) {
                    rowCalc = index + 1;
                    if (rowCalc % 26 == 0) {
                        doc.addPage();
                        page++;
                        startRowPosition = startRowPosition + 10;
                    }
                    rowPosition = (startRowPosition + (rowCalc * 10)) - ((page - 1) * 280);
                    $(this).filter(':visible').find('td').each(function (index, data) {
                        if ($(this).css('display') != 'none') {
                            if (defaults.ignoreColumn.indexOf(index) == -1) {
                                var colPosition = startColPosition + (index * 50);
                                doc.text(colPosition, rowPosition, tablevm.parseString($(this)));
                            }
                        }

                    });
                });
                // Output as Data URI
                doc.output('datauri');
            }


        };
        this.dateAdd = dateAdd;
        function dateAdd(object, availabledate_from, availabledate_to, day) {
            object[availabledate_to] = new Date();
            object[availabledate_to].setDate(object[availabledate_to].getDate() + day);
            object[availabledate_from] = new Date();
        }
        this.getPlaletColor = function (data) {

            for (var key in data) {
                if (data[key]) {
                    return "rgb(0,250,0)";
                }
            }
            return "rgb(250,250,250)"; // return "gray:200" ;
        };
        this.getPlaletCount = function (data) {

            var cnt = 0;
            for (var key in data) {
                if (data[key] === true) {
                    cnt++;
                }
            }
            if (cnt > 6) {
                return 0;
            }
            return 3;
        };
        this.getPlaletColorText = function (data) {

            if (data !== undefined && data !== null && data !== '') {
                return "rgb(0,250,0)";
            }
            return "rgb(250,250,250)"; // return "gray:200" ;
        };


        var NS = [
            {value: 1000000000000000000000, str: "sextillion"},
            {value: 1000000000000000000, str: "quintillion"},
            {value: 1000000000000000, str: "quadrillion"},
            {value: 1000000000000, str: "trillion"},
            {value: 1000000000, str: "billion"},
            {value: 10000000, str: "crore"},
            {value: 100000, str: "lakhs"},
            {value: 1000, str: "thousand"},
            {value: 100, str: "hundred"},
            {value: 90, str: "ninety"},
            {value: 80, str: "eighty"},
            {value: 70, str: "seventy"},
            {value: 60, str: "sixty"},
            {value: 50, str: "fifty"},
            {value: 40, str: "forty"},
            {value: 30, str: "thirty"},
            {value: 20, str: "twenty"},
            {value: 19, str: "nineteen"},
            {value: 18, str: "eighteen"},
            {value: 17, str: "seventeen"},
            {value: 16, str: "sixteen"},
            {value: 15, str: "fifteen"},
            {value: 14, str: "fourteen"},
            {value: 13, str: "thirteen"},
            {value: 12, str: "twelve"},
            {value: 11, str: "eleven"},
            {value: 10, str: "ten"},
            {value: 9, str: "nine"},
            {value: 8, str: "eight"},
            {value: 7, str: "seven"},
            {value: 6, str: "six"},
            {value: 5, str: "five"},
            {value: 4, str: "four"},
            {value: 3, str: "three"},
            {value: 2, str: "two"},
            {value: 1, str: "one"}
        ];
        this.formatNumber = function (num) {
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

        this.intToEnglish = function (number) {
//alert(number);
            var result = '';
            for (var i = 0; i < NS.length; i++) {
                var n = NS[i];
                if (number >= n.value) {
                    if (number <= 20) {
                        result += n.str;
                        number -= n.value;
                        if (number > 0)
                            result += ' ';
                    } else {
                        var t = Math.floor(number / n.value);
                        var d = number % n.value;
                        if (number < 100) {
                            if (d > 0) {
                                return   n.str + ' ' + sessionobject.intToEnglish(d);
                            } else {
                                return    n.str;
                            }
                        } else {
                            if (d > 0) {
                                return sessionobject.intToEnglish(t) + ' ' + n.str + ' ' + sessionobject.intToEnglish(d);
                            } else {
                                return sessionobject.intToEnglish(t) + ' ' + n.str;
                            }
                        }

                    }
                }
            }
            return result;
        };
        this.trim = new function (str) {
            if(str=== undefined || str===null)
                return "";
            return str.trim();
        };
        this.showmenu = showmenu;
        function showmenu(triMenu) {
            //     triMenu.removeAllMenu();
            if (triMenu.menu.length <= 1 && $localStorage.clientloginuserid !== null && ($localStorage.clientloginuserid.userid !== 0)) {
                triMenu.addMenu({
                    name: 'DASHBOARDS',
                    icon: 'zmdi zmdi-home',
                    display: true,
                    type: 'dropdown',
                    priority: 1.1,
                    children: [
                        {
                            name: 'ANALYTICS',
                            state: 'triangular.dashboard-analytics',
                            display: true,
                            type: 'link'
                        }

                    ]
                });
                triMenu.addMenu({
                    name: 'UI',
                    icon: 'zmdi zmdi-ruler',
                    type: 'dropdown',
                    display: true,
                    priority: 4.7,
                    children: [
//            
                        {
                            name: 'SKINS',
                            display: true,
                            state: 'triangular.ui-skins',
                            type: 'link'
                        }
//             
                    ]
                });
                triMenu.addMenu({
                    name: 'adminmenu.admin.ADMIN',
                    icon: 'fa fa-users',
                    display: $localStorage.clientloginuserid.role['User'].Menu,
                    type: 'dropdown',
                    priority: 4.1,
                    children: [{
                            name: 'adminmenu.role.NAME',
                            icon: 'zmdi zmdi-account-box-o',
                            display: $localStorage.clientloginuserid.role['Role'].Menu,
                            state: 'triangular.forms-Role', //'admin.Role',
                            type: 'link'
                        }, {
                            name: 'adminmenu.user.NAME',
                            icon: 'zmdi zmdi-account',
                            display: $localStorage.clientloginuserid.role['User'].Menu,
                            state: 'triangular.forms-User', //'admin.User',
                            type: 'link'
                        }, {
                            name: 'adminmenu.rolepermission.NAME',
                            icon: 'fa fa-users',
                            display: $localStorage.clientloginuserid.role['Role Permission'].Menu,
                            state: 'triangular.forms-Rolepermission', //'admin.Rolepermission',
                            type: 'link'
                        }, {
                            name: 'adminmenu.operation.NAME',
                            icon: 'zmdi zmdi-album',
                            display: $localStorage.clientloginuserid.role['Operation'].Menu,
                            state: 'triangular.forms-Operation', //'admin.Operation',
                            type: 'link'
                        }]
                });
                triMenu.addMenu({
                    name: 'adminmenu.admin.pms',
                    icon: 'fa fa-building',
                    display: $localStorage.clientloginuserid.role['Property'].Menu,
                    type: 'dropdown',
                    priority: 4.1,
                    children: [{
                            name: 'MENU.customer.NAME',
                            display: $localStorage.clientloginuserid.role['Customer'].Menu,
                            icon: 'zmdi zmdi-accounts-list',
                            state: 'triangular.forms-Customer', //'pms.Customer',
                            type: 'link'
                        },
//                        {
//                            name: 'MENU.test.NAME',
//                            state: 'triangular.forms-Test', //'pms.Test',
//                            display: $localStorage.clientloginuserid.role['Test'].Menu,
//                            type: 'link'
//                        },
                        {
                            name: 'MENU.property.Rent',
                            icon: 'fa fa-building',
                            display: $localStorage.clientloginuserid.role['Property'].Menu,
                            state: 'triangular.forms-PropertyRent', //'pms.Property', triangular.forms-Property-search
                            type: 'link'
                        }, {
                            name: 'MENU.property.Sale',
                            icon: 'fa fa-building',
                            display: $localStorage.clientloginuserid.role['Property'].Menu,
                            state: 'triangular.forms-PropertySale', //'pms.Property', triangular.forms-Property-search
                            type: 'link'
                        }, {
                            name: 'MENU.inquiry.NAME',
                            icon: 'fa fa-list-ul',
                            display: $localStorage.clientloginuserid.role['Inquiry'].Menu,
                            state: 'triangular.forms-Inquiry', //'pms.Inquiry',
                            type: 'link'
                        }, {
                            name: 'MENU.agent.NAME',
                            icon: 'fa fa-list-ul',
                            display: $localStorage.clientloginuserid.role['Agent'].Menu,
                            state: 'triangular.forms-Agent', //'pms.Inquiry',
                            type: 'link'
                        }]
                });
                triMenu.addMenu({
                    name: 'Report',
                    icon: 'fa fa-print',
                    display: $localStorage.clientloginuserid.role['Property'].Report,
                    type: 'dropdown',
                    priority: 4.1,
                    children: [
                        {
                            name: 'Rent Available',
                            icon: 'fa fa-building',
                            display: $localStorage.clientloginuserid.role['Property'].Report,
                            state: 'triangular.forms-PropertyReportRent', //'pms.Property', triangular.forms-Property-search
                            type: 'link'
                        }, {
                            name: 'Rent Close',
                            icon: 'fa fa-building',
                            display: $localStorage.clientloginuserid.role['Property'].Report,
                            state: 'triangular.forms-PropertyReportCloseRent', //'pms.Property', triangular.forms-Property-search
                            type: 'link'
                        }, {
                            name: 'Sale Close',
                            icon: 'fa fa-building',
                            display: $localStorage.clientloginuserid.role['Property'].Report,
                            state: 'triangular.forms-PropertyReportCloseSale', //'pms.Property', triangular.forms-Property-search
                            type: 'link'
                        }
                    ]
                });
                triMenu.addMenu({
                    name: 'adminmenu.admin.dropdown',
                    icon: 'zmdi zmdi-dropbox',
                    display: $localStorage.clientloginuserid.role['Customer Type'].Menu,
                    type: 'dropdown',
                    priority: 4.1,
                    children: [{
                            name: 'MENU.customertype.NAME',
                            icon: 'fa fa-sitemap',
                            display: $localStorage.clientloginuserid.role['Customer Type'].Menu,
                            state: 'triangular.forms-Customertype', //'pms.Customertype',
                            type: 'link'
                        }, {
                            name: 'MENU.propertypriority.NAME',
                            icon: 'fa fa-reorder',
                            display: $localStorage.clientloginuserid.role['Property Priority'].Menu,
                            state: 'triangular.forms-Propertypriority', //'pms.Propertypriority',
                            type: 'link'
                        }, {
                            name: 'MENU.propertyfurnished.NAME',
                            icon: 'zmdi zmdi-album',
                            display: $localStorage.clientloginuserid.role['Property Furnished'].Menu,
                            state: 'triangular.forms-Propertyfurnished', //'pms.Propertyfurnished',
                            type: 'link'
                        }, {
                            name: 'MENU.occupation.NAME',
                            icon: 'zmdi zmdi-account-box-o',
                            display: $localStorage.clientloginuserid.role['Occupation'].Menu,
                            state: 'triangular.forms-Occupation', //'pms.Occupation',
                            type: 'link'
                        }, {
                            name: 'MENU.gender.NAME',
                            icon: 'zmdi zmdi-male-female',
                            display: $localStorage.clientloginuserid.role['Gender'].Menu,
                            state: 'triangular.forms-Gender', //'pms.Gender',
                            type: 'link'
                        }, {
                            name: 'MENU.city.NAME',
                            icon: 'zmdi zmdi-city',
                            display: $localStorage.clientloginuserid.role['City'].Menu,
                            state: 'triangular.forms-City', //'pms.City',
                            type: 'link'
                        }, {
                            name: 'MENU.propertylocality.NAME',
                            icon: 'zmdi zmdi-gps',
                            display: $localStorage.clientloginuserid.role['Property Locality'].Menu,
                            state: 'triangular.forms-Propertylocality', //'pms.Propertylocality',
                            type: 'link'
                        }, {
                            name: 'MENU.propertytype.NAME',
                            icon: 'fa fa-building-o',
                            display: $localStorage.clientloginuserid.role['Property Type'].Menu,
                            state: 'triangular.forms-Propertytype', //'pms.Propertytype',
                            type: 'link'
                        },
//                        {
//                            name: 'MENU.propertyavailibility.NAME',
//                            icon: 'fa fa-adjust',
//                            display: $localStorage.clientloginuserid.role['Property Availibility'].Menu,
//                            state: 'triangular.forms-Propertyavailibility', //'pms.Propertyavailibility',
//                            type: 'link'
//                        }, 
                        {
                            name: 'MENU.propertylbedrooms.NAME',
                            display: $localStorage.clientloginuserid.role['Property Bed Rooms'].Menu,
                            icon: 'zmdi zmdi-hotel',
                            state: 'triangular.forms-Propertylbedrooms', //'pms.Propertylbedrooms',
                            type: 'link'
                        }, {
                            name: 'MENU.propertyfacing.NAME',
                            icon: 'fa fa-compass',
                            display: $localStorage.clientloginuserid.role['Property Facing'].Menu,
                            state: 'triangular.forms-Propertyfacing', //'pms.Propertyfacing',
                            type: 'link'
                        }, {
                            name: 'MENU.budgetrent.NAME',
                            icon: 'fa fa-rupee',
                            display: $localStorage.clientloginuserid.role['Budget Rent'].Menu,
                            state: 'triangular.forms-Budgetrent', //'pms.Budget',
                            type: 'link'
                        }, {
                            name: 'MENU.budgetsale.NAME',
                            icon: 'fa fa-rupee',
                            display: $localStorage.clientloginuserid.role['Budget Sale'].Menu,
                            state: 'triangular.forms-Budgetsale', //'pms.Budget',
                            type: 'link'
                        }, {
                            name: 'MENU.societyamenities.NAME',
                            icon: 'fa fa-building-o',
                            state: 'triangular.forms-Societyamenities', //'pms.Propertytobe',
                            display: $localStorage.clientloginuserid.role['Society Amenities'].Menu,
                            type: 'link'
                        }, {
                            name: 'MENU.propertytobe.NAME',
                            icon: 'fa fa-building-o',
                            state: 'triangular.forms-Propertytobe', //'pms.Propertytobe',
                            display: $localStorage.clientloginuserid.role['Property To be'].Menu,
                            type: 'link'
                        }, {
                            name: 'MENU.flatamenities.NAME',
                            icon: 'fa fa-building-o',
                            state: 'triangular.forms-Flatamenities', //'pms.Flatamenities',
                            display: $localStorage.clientloginuserid.role['Flat Amenities'].Menu,
                            type: 'link'
                        }, {
                            name: 'MENU.modeoperation.NAME',
                            icon: 'fa fa-building-o',
                            state: 'triangular.forms-Modeoperation', //'pms.Flatamenities',
                            display: $localStorage.clientloginuserid.role['Mode of Operation'].Menu,
                            type: 'link'
                        }]
                });
            }

        }

        this.flex_dialog = 60;
    }


})();