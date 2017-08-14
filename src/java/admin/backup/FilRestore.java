/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.backup;

import admin.file.FileForm;
import admin.user.UserForm;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pms.city.CityForm;
import pms.property.PropertyForm;
import pms.propertyfurnished.PropertyfurnishedForm;
import pms.propertylbedrooms.PropertylbedroomsForm;
import pms.propertylocality.PropertylocalityForm;
import pms.propertypriority.PropertypriorityForm;
import pms.propertytype.PropertytypeForm;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pms.customer.CustomerForm;
import pms.flatamenities.FlatamenitiesForm;
import pms.gender.GenderForm;

import pms.propertyfacing.PropertyfacingForm;

import pms.propertytobe.PropertytobeForm;
import pms.societyamenities.SocietyamenitiesForm;

/**
 *
 * @author user
 */
public class FilRestore {

    public static Date getDate(String str) {
        Date dt = null;
        try {
            SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
            dt = sd.parse(str);

        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }
        return dt;
    }

    public static float getAmoount(String str) {
        float amount = 0;
        try {
            str = str.toLowerCase().replaceAll(",", "").replaceAll("(negotiable )", "");

            if (str.indexOf("lac") != -1 || str.indexOf("lakh") != -1 || str.indexOf("lakhs") != -1) {
                str = str.replaceAll("lakhs", "").replaceAll("lakh", "").replaceAll("lac", str);
                String t[] = str.trim().split(" ");
                amount = Float.parseFloat(t[0].trim()) * 100000;

            } else if (str.indexOf("crore") != -1 || str.indexOf("cr") != -1) {
                str = str.replaceAll("crore ", "").replaceAll("cr", str);
                String t[] = str.trim().split(" ");
                amount = Float.parseFloat(t[0].trim()) * 10000000;

            } else {
                String t[] = str.trim().split(" ");
                amount = Float.parseFloat(t[0].trim());
            }
        } catch (Exception e) {
            System.out.println(str);
            e.printStackTrace();
        }
        return amount;
    }

    public void loadData(Session sessionfactory, String file1) {
        try {

            FileInputStream file = new FileInputStream(new File(file1));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            ArrayList<String> col = new ArrayList<String>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                col.add(cell.getStringCellValue());
            }
            int k = 0;
            while (rowIterator.hasNext()) {
                System.out.println("Row : " + k++);
                if (k > 1000) {
                    break;
                }

                Hashtable<String, Object> ht = new Hashtable<>();
                //For each row, iterate through all the columns
                row = rowIterator.next();

                cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int i = cell.getColumnIndex();
                    //Check the cell type and format accordingly
                    System.out.print("\n" + col.get(i) + ":" + cell.getColumnIndex() + ":");

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            ht.put(col.get(i), cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            ht.put(col.get(i), cell.getStringCellValue().trim());
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
//                        default:
//                            ht.put(col.get(i), cell.getStringCellValue());
//                            break; 
                    }
                    //   i++;
                }
                if (ht.size() > 10) {
                    PropertyForm pr = new PropertyForm();
                    if (ht.get("Posted On") != null) {
                        pr.setPostedon(getDate(ht.get("Posted On").toString()));
                    }
                    Criteria cr = sessionfactory.createCriteria(CityForm.class);
                    cr.add(Restrictions.like("name", "Pune"));
                    List l = cr.list();
                    pr.setCityid((CityForm) l.get(0));

                    if (ht.get("Location") != null) {
                        cr = sessionfactory.createCriteria(PropertylocalityForm.class);
                        cr.add(Restrictions.like("name", ht.get("Location").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            PropertylocalityForm form = new PropertylocalityForm();
                            form.setCityid(pr.getCityid());
                            form.setName(ht.get("Location").toString());
                            sessionfactory.save(form);
                            l.add(form);
                        }
                        pr.setPropertylocalityid((PropertylocalityForm) l.get(0));
                    }
                    if (ht.get("BHK") != null) {
                        cr = sessionfactory.createCriteria(PropertylbedroomsForm.class);
                        cr.add(Restrictions.like("name", ht.get("BHK").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            PropertylbedroomsForm form = new PropertylbedroomsForm();
                            form.setName(ht.get("BHK").toString());
                            sessionfactory.save(form);
                            l.add(form);
                        }
                        pr.setPropertylbedroomsid((PropertylbedroomsForm) l.get(0));
                    }
                    if (ht.get("Furnishing") != null) {
                        cr = sessionfactory.createCriteria(PropertyfurnishedForm.class);
                        cr.add(Restrictions.like("name", ht.get("Furnishing").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            PropertyfurnishedForm form = new PropertyfurnishedForm();
                            form.setName(ht.get("Furnishing").toString());
                            sessionfactory.save(form);
                            l.add(form);
                        }
                        pr.setPropertyfurnishedid((PropertyfurnishedForm) l.get(0));
                    }
                    if (ht.get("Prefference") != null) {
                        cr = sessionfactory.createCriteria(PropertypriorityForm.class);
                        cr.add(Restrictions.like("name", ht.get("Prefference").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            PropertypriorityForm form = new PropertypriorityForm();
                            form.setName(ht.get("Prefference").toString());
                            sessionfactory.save(form);
                            l.add(form);
                        }
                        //    pr.setPropertypriorityid((PropertypriorityForm) l.get(0));
                    }
                    if (ht.get("Property Type") != null) {
                        cr = sessionfactory.createCriteria(PropertytypeForm.class);
                        cr.add(Restrictions.like("name", ht.get("Property Type").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            PropertytypeForm form = new PropertytypeForm();
                            form.setName(ht.get("Property Type").toString());
                            sessionfactory.save(form);
                            l.add(form);
                        }
                        pr.setPropertytypeid((PropertytypeForm) l.get(0));
                    }
                    if (ht.get("Available From") != null) {

                        pr.setAvailablefrom(getDate(ht.get("Available From").toString()));

                    }
                    if (ht.get("Society") != null) {
                        pr.setApt(ht.get("Society").toString());
                    }
                    if (ht.get("Rent") != null) {
                        //      pr.setPropertytobeid((PropertytobeForm) l.get(0));

                        pr.setAmount(getAmoount(ht.get("Rent").toString()));
                    }
                    if (ht.get("Deposit") != null) {

                        cr = sessionfactory.createCriteria(PropertytobeForm.class);
                        cr.add(Restrictions.like("name", "rent"));
                        l = cr.list();
                        pr.setPropertytobeid((PropertytobeForm) l.get(0));

                        pr.setDeposit(getAmoount(ht.get("Deposit").toString()));

                    } else if (ht.get("Sale Amount") != null) {

                        cr = sessionfactory.createCriteria(PropertytobeForm.class);
                        cr.add(Restrictions.like("name", "sale"));
                        l = cr.list();
                        pr.setPropertytobeid((PropertytobeForm) l.get(0));

                        float amount = getAmoount(ht.get("Sale Amount").toString());//.toString().toLowerCase().replaceAll(",", "").replaceAll("(negotiable )", "");

                    }
                    if (ht.get("Owner") != null) {
                        cr = sessionfactory.createCriteria(CustomerForm.class);
                        cr.add(Restrictions.like("fullname", ht.get("Owner").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            CustomerForm form = new CustomerForm();
                            form.setFullname(ht.get("Owner").toString());
                            if (ht.get("Mobile") != null) {
                                form.setMobileno(ht.get("Mobile").toString());
                            }
                            cr = sessionfactory.createCriteria(GenderForm.class);
                            List l1 = cr.list();
                            form.setGenderid((GenderForm) l1.get(0));

                            sessionfactory.save(form);
                            l.add(form);
                        }
                        pr.setCustomerid((CustomerForm) l.get(0));
                    }
                    if (ht.get("Person") != null) {
                        cr = sessionfactory.createCriteria(CustomerForm.class);
                        cr.add(Restrictions.like("fullname", ht.get("Person").toString()));
                        l = cr.list();
                        if (l.size() == 0) {
                            CustomerForm form = new CustomerForm();
                            form.setFullname(ht.get("Person").toString());
                            if (ht.get("Mobile") != null) {
                                form.setMobileno(ht.get("Mobile").toString());
                            }
                            cr = sessionfactory.createCriteria(GenderForm.class);
                            List l1 = cr.list();
                            form.setGenderid((GenderForm) l1.get(0));

                            sessionfactory.save(form);
                            l.add(form);
                        }
                        pr.setCustomerid((CustomerForm) l.get(0));
                    }
                    if (ht.get("Key") != null) {
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertykeyForm.class);
//                        cr.add(Restrictions.like("name", ht.get("Key").toString()));
//                        l = cr.list();
//                        if (l.size() == 0) {
//                            PropertykeyForm form = new PropertykeyForm();
//                            form.setName(ht.get("Key").toString());
//
//                            sessionfactory.getCurrentSession().save(form);
//                            l.add(form);
//                        }
//                        pr.setPropertykeyid((PropertykeyForm) l.get(0));
                    }
                    if (ht.get("Address") != null) {
                        pr.setAddress(ht.get("Address").toString());
                    }
                    if (ht.get("Landmark") != null) {
                        pr.setLandmark(ht.get("Landmark").toString());
                    }
                    if (ht.get("Remark") != null) {
                        pr.setRemark(ht.get("Remark").toString());
                    }
                    if (ht.get("Floor") == null) {
                        pr.setFlatno("Not Avilable");
                    } else {
                        pr.setFlatno(ht.get("Floor").toString());
                    }
                    if (ht.get("AgeOfConstr") != null) {
                        pr.setPropertyage(ht.get("AgeOfConstr").toString());
                    } else {
                        pr.setPropertyage("0");
                    }
                    if (ht.get("Built-up Area") != null) {
                        pr.setTotalarea(ht.get("Built-up Area").toString());
                    } else {
                        pr.setTotalarea("0");
                    }
                    if (ht.get("Floor") != null) {
                        pr.setFloorno(ht.get("Floor").toString());
                    } else {
                        pr.setFloorno("Not Avilable");
                    }

                    if (ht.get("Key") != null) {
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertykeyForm.class);
//                        cr.add(Restrictions.like("name", ht.get("Key").toString()));
//                        l = cr.list();
//                        if (l.size() == 0) {
//                            PropertykeyForm form = new PropertykeyForm();
//                            form.setName(ht.get("Key").toString());
//
//                            sessionfactory.getCurrentSession().save(form);
//                            l.add(form);
//                        }
//                        pr.setPropertykeyid((PropertykeyForm) l.get(0));
                    }
                    if (ht.get("Key/Appointment") != null) {
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertykeyForm.class);
//                        cr.add(Restrictions.like("name", ht.get("Key/Appointment").toString()));
//                        l = cr.list();
//                        if (l.size() == 0) {
//                            PropertykeyForm form = new PropertykeyForm();
//                            form.setName(ht.get("Key/Appointment").toString());
//
//                            sessionfactory.getCurrentSession().save(form);
//                            l.add(form);
//                        }
//                        pr.setPropertykeyid((PropertykeyForm) l.get(0));
                    }
                    {

                        if (ht.get("Facing") == null) {
                            cr = sessionfactory.createCriteria(PropertyfacingForm.class);
                            l = cr.list();
                            pr.setPropertyfacingid((PropertyfacingForm) l.get(0));
                        } else {
                            cr = sessionfactory.createCriteria(PropertyfacingForm.class);
                            cr.add(Restrictions.like("name", ht.get("Facing").toString()));
                            l = cr.list();
                            if (l.size() == 0) {
                                PropertyfacingForm form = new PropertyfacingForm();
                                form.setName(ht.get("Facing").toString());

                                sessionfactory.save(form);
                                l.add(form);
                            }
                            pr.setPropertyfacingid((PropertyfacingForm) l.get(0));
                        }
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertyavailibilityForm.class);
//                        l = cr.list();
                        //        pr.setPropertyavailibilityid((PropertyavailibilityForm) l.get(0));

                        cr = sessionfactory.createCriteria(UserForm.class);
                        l = cr.list();
                        pr.setUserid((UserForm) l.get(0));

                        pr.setCreateddate(new Date());
                        pr.setModifyddate(new Date());
                        pr.setActive("1");
                    }

                    pr.setFlatamenitiesid(new ArrayList());
                    if (ht.get("Flat Amenities") != null) {

                        String str[] = ht.get("Flat Amenities").toString().replaceAll("â€¢", ",").replaceAll(",", "\n").replaceAll("\\.", "\n").trim().split("\n");

                        for (int i = 0; i < str.length; i++) {

                            str[i] = str[i].trim();
                            if (str[i].length() > 1) {
                                cr = sessionfactory.createCriteria(FlatamenitiesForm.class);
                                cr.add(Restrictions.like("name", str[i]));
                                l = cr.list();
                                if (l.size() == 0) {
                                    FlatamenitiesForm form = new FlatamenitiesForm();
                                    form.setName(str[i]);

                                    sessionfactory.save(form);
                                    l.add(form);
                                }
                                pr.getFlatamenitiesid().add((FlatamenitiesForm) l.get(0));
                            }
                        }
                    }
                    pr.setSocietyamenitiesid(new ArrayList());
                    if (ht.get("Society Amenities") != null) {
                        String str[] = ht.get("Society Amenities").toString().replaceAll("â€¢", ",").replaceAll(",", "\n").replaceAll("\\.", "\n").trim().split("\n");
                        for (int i = 0; i < str.length; i++) {
                            str[i] = str[i].trim();
                            if (str[i].length() > 1) {
                                cr = sessionfactory.createCriteria(SocietyamenitiesForm.class);
                                cr.add(Restrictions.like("name", str[i]));
                                l = cr.list();
                                if (l.size() == 0) {
                                    SocietyamenitiesForm form = new SocietyamenitiesForm();
                                    form.setName(str[i]);

                                    sessionfactory.save(form);
                                    l.add(form);
                                }
                                pr.getSocietyamenitiesid().add((SocietyamenitiesForm) l.get(0));
                            }
                        }
                    }
                    pr.setImage1((FileForm) sessionfactory.get(FileForm.class, 1));
                    sessionfactory.save(pr);
                    System.out.println("");
                    //  sessionfactory.getCurrentSession().
                }
            }
            file.close();
            //      sessionfactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData1() {
        try {
            //= getSessionFactory();
            //   sessionfactory.openSession();

            FileInputStream file = new FileInputStream(new File("G:\\phd\\PMS\\PMS\\db\\Rental XL- 1 March to Till Date1.xls"));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            ArrayList<String> col = new ArrayList<String>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                col.add(cell.getStringCellValue());
            }
            int k = 0;
            while (rowIterator.hasNext()) {
                System.out.println("Row : " + k++);

                Hashtable<String, Object> ht = new Hashtable<>();
                //For each row, iterate through all the columns
                row = rowIterator.next();

                cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int i = cell.getColumnIndex();
                    //Check the cell type and format accordingly
                    System.out.print("\n" + col.get(i) + ":" + cell.getColumnIndex() + ":");

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            ht.put(col.get(i), cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            ht.put(col.get(i), cell.getStringCellValue().trim());
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
//                        default:
//                            ht.put(col.get(i), cell.getStringCellValue());
//                            break;
                    }
                    //   i++;
                }
            }
            file.close();
            //      sessionfactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int loadDataUpload(Session sessionfactory, String file1) {
        //   Transaction t = sessionfactory.beginTransaction();
        int cpount = 0;
        try {

            FileInputStream file = new FileInputStream(new File(file1));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            ArrayList<String> col = new ArrayList<String>();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                col.add(cell.getStringCellValue());
            }
            int k = 0;
            while (rowIterator.hasNext()) {
                System.out.println("Row : " + k++);
                if (k > 1000) {
                    break;
                }

                Hashtable<String, Object> ht = new Hashtable<>();
                //For each row, iterate through all the columns
                row = rowIterator.next();

                cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int i = cell.getColumnIndex();
                    //Check the cell type and format accordingly
                    System.out.print("\n" + col.get(i) + ":" + cell.getColumnIndex() + ":");

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");

                                ht.put(col.get(i), cell.getDateCellValue());
 
                            } else {
                                if (col.get(i).toString().equals("Mobile")) {
                                    DecimalFormat df = new DecimalFormat("#");
                                    df.setMaximumFractionDigits(0);
                                    ht.put(col.get(i),df.format(cell.getNumericCellValue()));
                                } else {

                                    System.out.print(cell.getNumericCellValue() + "\t");
                                    ht.put(col.get(i), cell.getNumericCellValue());
                                }
                            }

                            break;
                        case Cell.CELL_TYPE_STRING:
                            ht.put(col.get(i), cell.getStringCellValue().trim());
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;

//                        default:
//                            ht.put(col.get(i), cell.getStringCellValue());
//                            break; 
                    }
                    //   i++;
                }
                try {

                    if (ht.size() > 10) {
                        PropertyForm pr = new PropertyForm();
                        pr.setPostedon(new Date());
                        pr.setClosedate(new Date());
                        pr.setModifyddate(new Date());

                        Criteria cr = sessionfactory.createCriteria(CityForm.class);
                        cr = cr.add(Restrictions.like("name", "Pune"));
                        List l = cr.list();
                        pr.setCityid((CityForm) l.get(0));

                        if (ht.get("Location") != null) {
                            cr = sessionfactory.createCriteria(PropertylocalityForm.class);
                            cr = cr.add(Restrictions.like("name", ht.get("Location").toString()));
                            l = cr.list();
                            if (l.size() == 0) {
                                PropertylocalityForm form = new PropertylocalityForm();
                                form.setCityid(pr.getCityid());
                                form.setName(ht.get("Location").toString());
                                sessionfactory.save(form);
                                l.add(form);
                            }
                            pr.setPropertylocalityid((PropertylocalityForm) l.get(0));
                        }
                        if (ht.get("BHK") != null) {
                            cr = sessionfactory.createCriteria(PropertylbedroomsForm.class);
                            cr = cr.add(Restrictions.like("name", ht.get("BHK").toString()));
                            cr = cr.add(Restrictions.like("active", "1"));

                            l = cr.list();
                            if (l.size() == 0) {
                                PropertylbedroomsForm form = new PropertylbedroomsForm();
                                form.setName(ht.get("BHK").toString());
                                form.setActive("1");
                                sessionfactory.save(form);
                                l.add(form);
                            }
                            pr.setPropertylbedroomsid((PropertylbedroomsForm) l.get(0));
                        }
                        if (ht.get("Furnishing") != null) {
                            cr = sessionfactory.createCriteria(PropertyfurnishedForm.class);
                            cr = cr.add(Restrictions.like("name", ht.get("Furnishing").toString()));
                            l = cr.list();
                            if (l.size() == 0) {
                                PropertyfurnishedForm form = new PropertyfurnishedForm();
                                form.setName(ht.get("Furnishing").toString());
                                sessionfactory.save(form);
                                l.add(form);
                            }
                            pr.setPropertyfurnishedid((PropertyfurnishedForm) l.get(0));
                        }
                        if (ht.get("Priority") != null) {
                            String st[] = ht.get("Priority").toString().split(",");
                            pr.setPropertypriorityid(new ArrayList());
                            for (int i = 0; i < st.length; i++) {

                                cr = sessionfactory.createCriteria(PropertypriorityForm.class);
                                cr = cr.add(Restrictions.like("name", st[i]));
                                cr = cr.add(Restrictions.like("active", "1"));
                                l = cr.list();
                                if (l.size() == 0) {
                                    PropertypriorityForm form = new PropertypriorityForm();
                                    form.setName(ht.get("Priority").toString());
                                    form.setActive("1");
                                    sessionfactory.save(form);
                                    l.add(form);
                                }
                                pr.getPropertypriorityid().add((PropertypriorityForm) l.get(0));
                            }
                        }
                        if (ht.get("Property Type") != null) {
                            cr = sessionfactory.createCriteria(PropertytypeForm.class);
                            cr = cr.add(Restrictions.like("name", ht.get("Property Type").toString()));
                            cr = cr.add(Restrictions.like("active", "1"));
                            l = cr.list();
                            if (l.size() == 0) {
                                PropertytypeForm form = new PropertytypeForm();
                                form.setActive("1");
                                form.setName(ht.get("Property Type").toString());
                                sessionfactory.save(form);
                                l.add(form);
                            }
                            pr.setPropertytypeid((PropertytypeForm) l.get(0));
                        }
                        if (ht.get("Available From") != null) {

                            pr.setAvailablefrom((Date) ht.get("Available From"));

                        }
                        if (ht.get("Society") != null) {
                            pr.setApt(ht.get("Society").toString());
                        }
                        if (ht.get("Rent") != null) {
                            //      pr.setPropertytobeid((PropertytobeForm) l.get(0));

                            pr.setAmount(getAmoount(ht.get("Rent").toString()));
                        }
                        if (ht.get("Deposit") != null) {

                            cr = sessionfactory.createCriteria(PropertytobeForm.class);
                            cr = cr.add(Restrictions.like("name", "rent"));
                            l = cr.list();
                            pr.setPropertytobeid((PropertytobeForm) l.get(0));
                            pr.setDeposit(getAmoount(ht.get("Deposit").toString()));

                        } else if (ht.get("Sale Amount") != null) {

                            cr = sessionfactory.createCriteria(PropertytobeForm.class);
                            cr = cr.add(Restrictions.like("name", "sale"));
                            l = cr.list();
                            pr.setPropertytobeid((PropertytobeForm) l.get(0));
                            float amount = getAmoount(ht.get("Sale Amount").toString());//.toString().toLowerCase().replaceAll(",", "").replaceAll("(negotiable )", "");

                        }
                        if (ht.get("Owner") != null) {
                            String st[] = ht.get("Mobile").toString().trim().split(",");
                            cr = sessionfactory.createCriteria(CustomerForm.class);
                            cr = cr.add(Restrictions.like("fullname", ht.get("Owner").toString()));
                            cr = cr.add(Restrictions.like("mobileno", ht.get("Mobile").toString()));
                            cr = cr.add(Restrictions.like("active", "1"));
                            l = cr.list();
                            if (l.size() == 0) {

                                if (st.length > 0) {
                                    for (int i = 0; i < st.length; i++) {
                                        cr = sessionfactory.createCriteria(CustomerForm.class);
                                        cr = cr.add(Restrictions.like("fullname", ht.get("Owner").toString().trim() + " (" + st[0].trim() + ")"));
                                        cr = cr.add(Restrictions.like("active", "1"));
                                        l = cr.list();
                                    }
                                }
                            }
                            if (l.size() == 0) {
                                CustomerForm form = new CustomerForm();
                                form.setFullname(ht.get("Owner").toString() + " (" + st[0].trim() + ")");
                                form.setActive("1");
                                if (st.length > 0) {
                                    form.setMobileno(st[0]);
                                    if (st.length >= 2) {
                                        form.setContact1(st[1]);
                                    }
                                    if (st.length >= 3) {
                                        form.setContact2(st[2]);
                                    }
                                    if (st.length >= 4) {
                                        form.setContact3(st[3]);
                                    }
                                }
                                cr = sessionfactory.createCriteria(GenderForm.class);

                                List l1 = cr.list();
                                form.setGenderid((GenderForm) l1.get(0));

                                sessionfactory.save(form);
                                l.add(form);
                            }
                            pr.setCustomerid((CustomerForm) l.get(0));
                        }

                        if (ht.get("Key") != null) {
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertykeyForm.class);
//                        cr.add(Restrictions.like("name", ht.get("Key").toString()));
//                        l = cr.list();
//                        if (l.size() == 0) {
//                            PropertykeyForm form = new PropertykeyForm();
//                            form.setName(ht.get("Key").toString());
//
//                            sessionfactory.getCurrentSession().save(form);
//                            l.add(form);
//                        }
//                        pr.setPropertykeyid((PropertykeyForm) l.get(0));
                        }
                        if (ht.get("Address") != null) {
                            pr.setAddress(ht.get("Address").toString());
                        }
                        if (ht.get("Landmark") != null) {
                            pr.setLandmark(ht.get("Landmark").toString());
                        }
                        if (ht.get("Remark") != null) {
                            pr.setRemark(ht.get("Remark").toString());
                        }
                        if (ht.get("Floor") == null) {
                            pr.setFlatno("Not Avilable");
                        } else {
                            pr.setFlatno(ht.get("Floor").toString());
                        }
                        if (ht.get("AgeOfConstr") != null) {
                            pr.setPropertyage(ht.get("AgeOfConstr").toString());
                        } else {
                            pr.setPropertyage("0");
                        }
                        if (ht.get("Built-up Area") != null) {
                            pr.setTotalarea(ht.get("Built-up Area").toString());
                        } else {
                            pr.setTotalarea("0");
                        }
                        if (ht.get("Floor") != null) {
                            pr.setFloorno(ht.get("Floor").toString());
                        } else {
                            pr.setFloorno("Not Avilable");
                        }

                        if (ht.get("Key") != null) {
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertykeyForm.class);
//                        cr.add(Restrictions.like("name", ht.get("Key").toString()));
//                        l = cr.list();
//                        if (l.size() == 0) {
//                            PropertykeyForm form = new PropertykeyForm();
//                            form.setName(ht.get("Key").toString());
//
//                            sessionfactory.getCurrentSession().save(form);
//                            l.add(form);
//                        }
//                        pr.setPropertykeyid((PropertykeyForm) l.get(0));
                        }
                        if (ht.get("Key/Appointment") != null) {
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertykeyForm.class);
//                        cr.add(Restrictions.like("name", ht.get("Key/Appointment").toString()));
//                        l = cr.list();
//                        if (l.size() == 0) {
//                            PropertykeyForm form = new PropertykeyForm();
//                            form.setName(ht.get("Key/Appointment").toString());
//
//                            sessionfactory.getCurrentSession().save(form);
//                            l.add(form);
//                        }
//                        pr.setPropertykeyid((PropertykeyForm) l.get(0));
                        }
                        {

                            if (ht.get("Facing") == null) {
                                cr = sessionfactory.createCriteria(PropertyfacingForm.class);
                                cr.add(Restrictions.like("active", "1"));
                                l = cr.list();
                                pr.setPropertyfacingid((PropertyfacingForm) l.get(0));
                            } else {
                                cr = sessionfactory.createCriteria(PropertyfacingForm.class);
                                cr = cr.add(Restrictions.like("name", ht.get("Facing").toString()));
                                cr = cr.add(Restrictions.like("active", "1"));
                                l = cr.list();
                                if (l.size() == 0) {
                                    PropertyfacingForm form = new PropertyfacingForm();
                                    form.setName(ht.get("Facing").toString());
                                    form.setActive("1");
                                    sessionfactory.save(form);
                                    l.add(form);
                                }
                                pr.setPropertyfacingid((PropertyfacingForm) l.get(0));
                            }
//                        cr = sessionfactory.getCurrentSession().createCriteria(PropertyavailibilityForm.class);
//                        l = cr.list();
                            //        pr.setPropertyavailibilityid((PropertyavailibilityForm) l.get(0));

                            cr = sessionfactory.createCriteria(UserForm.class);
                            l = cr.list();
                            pr.setUserid((UserForm) l.get(0));

                            pr.setCreateddate(new Date());
                            pr.setModifyddate(new Date());
                            pr.setActive("1");
                        }

                        pr.setFlatamenitiesid(new ArrayList());
                        if (ht.get("Flat Amenities") != null) {

                            String str[] = ht.get("Flat Amenities").toString().replaceAll("â€¢", ",").replaceAll(",", "\n").replaceAll("\\.", "\n").trim().split("\n");

                            for (int i = 0; i < str.length; i++) {

                                str[i] = str[i].trim();
                                if (str[i].length() > 1) {
                                    cr = sessionfactory.createCriteria(FlatamenitiesForm.class);
                                    cr = cr.add(Restrictions.like("name", str[i]));
                                    cr = cr.add(Restrictions.like("active", "1"));
                                    l = cr.list();
                                    if (l.size() == 0) {
                                        FlatamenitiesForm form = new FlatamenitiesForm();
                                        form.setName(str[i]);
                                        form.setActive("1");
                                        sessionfactory.save(form);
                                        l.add(form);
                                    }
                                    pr.getFlatamenitiesid().add((FlatamenitiesForm) l.get(0));
                                }
                            }
                        }
                        pr.setSocietyamenitiesid(new ArrayList());
                        if (ht.get("Society Amenities") != null) {
                            String str[] = ht.get("Society Amenities").toString().replaceAll("â€¢", ",").replaceAll(",", "\n").replaceAll("\\.", "\n").trim().split("\n");
                            for (int i = 0; i < str.length; i++) {
                                str[i] = str[i].trim();
                                if (str[i].length() > 1) {
                                    cr = sessionfactory.createCriteria(SocietyamenitiesForm.class);
                                    cr = cr.add(Restrictions.like("name", str[i]));
                                    cr = cr.add(Restrictions.like("active", "1"));
                                    l = cr.list();
                                    if (l.size() == 0) {
                                        SocietyamenitiesForm form = new SocietyamenitiesForm();
                                        form.setName(str[i]);
                                        form.setActive("1");
                                        sessionfactory.save(form);
                                        l.add(form);
                                    }
                                    pr.getSocietyamenitiesid().add((SocietyamenitiesForm) l.get(0));
                                }
                            }
                        }
                        //      pr.setImage1((FileForm) sessionfactory.get(FileForm.class, 1));
                        sessionfactory.save(pr);
                        cpount++;
                        System.out.println("");
                        //  sessionfactory.getCurrentSession().
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            file.close();
            //      sessionfactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // t.commit();
        return cpount;
    }

    private SessionFactory getSessionFactory() {
        // create configuration using hibernate API
        Configuration configuration = new Configuration();
        configuration.setProperty("connection.driver_class",
                "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost/pms");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");

        return configuration.buildSessionFactory();
    }

    public static void main(String[] args) {
        new FilRestore().loadData1();
    }
}
