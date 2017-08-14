/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author user
 */
public class CommonUtil {

    public static Criteria getFilterData(Criteria cr, TableForm tableForm) {
        if (tableForm.getFilter() != null) {
            String str[] = tableForm.getFilter().split(";");
            for (int i = 0; i < str.length; i++) {
                String str1[] = str[i].split("\\|");
                if (str1.length >= 2 && str1[1] != null && !str1[1].equals("") && !str1[1].equals("undefined")) {
                    ;//.replaceAll("\\{", "[").replaceAll("\\}", "]");
//                    if (CommonController.isInteger(str1[1])) {
//                        cr = cr.add(Restrictions.eq(  str1[0], Integer.parseInt(str1[1])));
//                    } else 
                    if (str1[1].indexOf("{") != -1) {
                        if (str1[1].indexOf(":true") == -1) {
                            continue;
                        }
                        str1[1] = str1[1].replaceAll(":true", "");
                        str1[1] = str1[1].replaceAll("\"", "");
                        str1[1] = str1[1].replaceAll("\\{", "");
                        str1[1] = str1[1].replaceAll("\\}", "");
                        String st[] = str1[1].split(",");
                        Disjunction or = Restrictions.disjunction();
                        if (st.length > 1) {
                            for (int j = 0; j < st.length; j++) {
                                if (st[j].indexOf(":false") == -1) {
                                    if (CommonController.isInteger(st[j])) {
                                        or.add(Restrictions.eq(str1[0], Integer.parseInt(st[j])));
                                    } else {
                                        or.add(Restrictions.like(str1[0], st[j].toString().trim(), MatchMode.ANYWHERE));
                                    }
                                }
                            }
                            cr.add(or);
                        } else if (CommonController.isInteger(str1[1])) {
                            cr = cr.add(Restrictions.eq(str1[0], Integer.parseInt(str1[1])));
                        } else if (str1.length >= 2 && !str1[1].equals("undefined") && !str1[1].equals("null")) {
                            cr = cr.add(Restrictions.like(str1[0], str1[1]));
                        }
                    } else if (str1[1].equals("false") || str1[1].equals("true")) {
                        cr = cr.add(Restrictions.eq(str1[0], Boolean.parseBoolean(str1[1])));
                    } else if (str1[1].indexOf("-To-") != -1) {
                        try {
                            //     str1[1] = str1[1].replaceAll(",", "");
                            String[] tp = str1[1].split("-To-");
                            SimpleDateFormat sd = new SimpleDateFormat("MMM d, yyyy");
                            cr = cr.add(Restrictions.gt(str1[0], sd.parse(tp[0])));
                            cr = cr.add(Restrictions.lt(str1[0], sd.parse(tp[1])));
                        } catch (ParseException ex) {

                        }
                    } else if (str1[1].indexOf("-") != -1) {
                        String temp1 = str1[1];
                        str1[1] = str1[1].replaceAll(",", "");
                        String[] temp = str1[1].split("-");
                        try {
                            cr = cr.add(Restrictions.gt(str1[0], Float.parseFloat(temp[0].trim())));
                            cr = cr.add(Restrictions.lt(str1[0], Float.parseFloat(temp[1].trim())));
                        } catch (Exception e) {
                            cr = cr.add(Restrictions.like(str1[0], temp1));
                        }
                    } else if (CommonController.isInteger(str1[1])) {
                        cr = cr.add(Restrictions.eq(str1[0], Integer.parseInt(str1[1])));
                    } else if (str1.length >= 2 && !str1[1].equals("undefined") && !str1[1].equals("null")) {
                        cr = cr.add(Restrictions.like(str1[0], str1[1]));
                    }
                }
            }
        }
        return cr;
    }
}
