package admin.file;

import admin.file.FileForm;

import admin.filter.SelectCombo;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import admin.filter.TableForm;
import admin.file.FileForm;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import admin.user.UserForm;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @file Vision
 */
@Repository
public class FileDAOImpl implements FileDAO {

    @Autowired()
    private SessionFactory sessionfactory;

    @Override
    public void addFile(FileForm fileForm) {
        setFK(fileForm);
        sessionfactory.getCurrentSession().save(fileForm);
    }

    @Override
    public TableForm getFileList(TableForm tableForm) {
        Criteria cr = sessionfactory.getCurrentSession().createCriteria(FileForm.class
        );

        // Filedata
        cr = cr.createAlias("userid", "userid", Criteria.LEFT_JOIN);
        if (tableForm.getFilter() != null) {
            String str[] = tableForm.getFilter().split(";");
            for (int i = 0; i < str.length; i++) {
                String str1[] = str[i].split("\\|");
                if (str1.length >= 2 && !(str1[1].equals("0") || str1[1].equals("undefined"))) {
                    cr = cr.add(Restrictions.eq(str1[0] + "." + str1[0], Integer.parseInt(str1[1])));
                }
            }
        }
        if (tableForm.getQ() != null && !tableForm.getQ().equals("")) {
            Disjunction or = Restrictions.disjunction();

            or.add(Restrictions.ilike("filename", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("conenttype", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("type", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            or.add(Restrictions.ilike("active", tableForm.getQ().trim(), MatchMode.ANYWHERE));
            cr.add(or);

            //    cr = cr.add(Restrictions.ilike("fileid", tableForm.getQ(), MatchMode.ANYWHERE));
            //cr = cr.add(Restrictions.ilike("mobile", tableForm.getQ(), MatchMode.ANYWHERE));
// cr = cr.add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE)eq(str1[0] + "." + str1[0], str1[1]));
        }
        List l = cr.list();
        tableForm.setTotal_count(l.size());
        tableForm.setOrder(tableForm.getOrder().replaceAll("-", ""));
        List<FileForm> list = (List<FileForm>) cr.setFirstResult((tableForm.getPage() - 1) * tableForm.getPer_page()).
                setMaxResults(tableForm.getPer_page())
                .addOrder(tableForm.getSort().equals("asc") ? Order.asc(tableForm.getOrder()) : Order.desc(tableForm.getOrder())).list();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContent(null);

        }
        tableForm.setItems(list);
        return tableForm;
    }

    @Override
    public FileForm editFile(Integer id) {
        FileForm fileForm = (FileForm) sessionfactory.getCurrentSession().get(FileForm.class, id);
//
//        if (fileForm.getContent() != null) {
//            fileForm.setSelectcontent(fileForm.getContent().getFileid());
//        }
//        if (fileForm.getUserid() != null) {
//            fileForm.setSelectuserid(fileForm.getUserid().getUserid());
//        }
        return fileForm;
    }

    @Override
    public void updateFile(FileForm fileForm) {
        setFK(fileForm);
        sessionfactory.getCurrentSession().update(fileForm);
    }

    @Override
    public void deleteFile(Integer id) {
        FileForm fileForm = (FileForm) sessionfactory.getCurrentSession().load(FileForm.class, id);
        if (fileForm != null) {
            sessionfactory.getCurrentSession().delete(fileForm);
        }
    }

    @Override
    public List<SelectCombo> getFileComboList(HttpServletRequest request) {
        ArrayList<SelectCombo> al = new ArrayList();

        if (request.getParameter("table") != null) {
            String table = request.getParameter("table").toString().trim();
            List<Object[]> l = sessionfactory.getCurrentSession().createQuery("select file.fileid,file.name,count(" + table.toLowerCase() + ") as cnt from " + table + " " + table.toLowerCase() + "   "
                    + "left join  " + table.toLowerCase() + ".fileid  file where file.fileid = " + table.toLowerCase() + ".fileid.fileid " + " and " + table.toLowerCase() + ".propertytobeid.propertytobeid=" + request.getParameter("type") + " group by file.fileid,file.name  "
                    + "order by cnt desc").list();
            for (int i = 0; i < l.size(); i++) {
                al.add(new SelectCombo(((Integer) l.get(i)[0]).intValue(),
                        (String) l.get(i)[1], 
                        ((Long) l.get(i)[2]).intValue()));
            }
        } else {
            Criteria cr = sessionfactory.getCurrentSession().createCriteria(FileForm.class);

//            if (request.getParameter("userid") != null && !request.getParameter("userid").equals("undefined") && !request.getParameter("userid").equals("0")) {
//                cr = cr.add(Restrictions.eq("userid.userid", Integer.parseInt(request.getParameter("userid"))));
//            }
            List<FileForm> list = (List<FileForm>) cr.addOrder(Order.asc("filename")).list();

            for (FileForm fileForm : list) {
                al.add(new SelectCombo(fileForm.getFileid(), fileForm.getFilename()));
            }
        }
        return al;
    }

    public void setFK(FileForm fileForm) {

        if (fileForm.getSelectuserid() > 0) {
            fileForm.setUserid((UserForm) sessionfactory.getCurrentSession().get(UserForm.class, fileForm.getSelectuserid()));
      
        }
    }
   @Override
    public byte[] getIcon(byte[] imagedata) {
        Icon icon = null;
        //    URL url = null;

        BufferedImage scaledImage = null;
        try {

            icon = new ImageIcon(imagedata);

            BufferedImage bi = new BufferedImage(
                    icon.getIconWidth(),
                    icon.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            // paint the Icon to the BufferedImage.
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            bi = resizeImage(bi, 100, 100);
            scaledImage = bi;// or replace with this line Scalr.resize(bi, 30,30);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, "jpg", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            return imageInByte;
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage resizeImage(BufferedImage image, int areaWidth, int areaHeight) {
        float scaleX = (float) areaWidth / image.getWidth();
        float scaleY = (float) areaHeight / image.getHeight();
        float scale = Math.min(scaleX, scaleY);
        int w = Math.round(image.getWidth() * scale);
        int h = Math.round(image.getHeight() * scale);

        int type = image.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        boolean scaleDown = scale < 1;

        if (scaleDown) {
            // multi-pass bilinear div 2
            int currentW = image.getWidth();
            int currentH = image.getHeight();
            BufferedImage resized = image;
            while (currentW > w || currentH > h) {
                currentW = Math.max(w, currentW / 2);
                currentH = Math.max(h, currentH / 2);

                BufferedImage temp = new BufferedImage(currentW, currentH, type);
                Graphics2D g2 = temp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.drawImage(resized, 0, 0, currentW, currentH, null);
                g2.dispose();
                resized = temp;
            }
            return resized;
        } else {
            Object hint = scale > 2 ? RenderingHints.VALUE_INTERPOLATION_BICUBIC : RenderingHints.VALUE_INTERPOLATION_BILINEAR;

            BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resized.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(image, 0, 0, w, h, null);
            g2.dispose();
            return resized;
        }
    }

}
