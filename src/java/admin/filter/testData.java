/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class testData {

    public static void main(String[] args) {
        try {

            SimpleDateFormat sd = new SimpleDateFormat("MMM d, yyyy");
            Date dt = sd.parse("Mar 17, 2016");
            System.out.println(dt);
            String str = "{\"propertyid\":0,\"selectpropertytypeid\":\"1\",\"selectcustomerid\":\"2\",\"totalarea\":\"2000\","
                    + "\"amount\":\"12\",\"selectpropertypriorityid\":\"3\",\"flatno\":\"213\",\"apt\":\"asd\",\"address\":\"sad\",\"roadname\":\"sd\","
                    + "\"selectcityid\":\"1\",\"selectpropertylocalityid\":\"1\",\"landmark\":\"sad\",\"propertyage\":\"12\",\"leaseperiod\":\"12\","
                    + "\"selectpropertylbedroomsid\":\"1\",\"floorno\":\"3\",\"selectpropertyfacingid\":\"3\",\"selectpropertyavailibilityid\":\"2\","
                    + "\"selectpropertyfurnishedid\":\"2\",\"selectimage1\":{},\"selectimage2\":0,\"selectimage3\":0,\"selectimage4\":0,\"selectimage5\":0,\"remark\":\"asl\",\"selectuserid\":1,\"active\":\"\",\"createddate\":\"2016-06-05T07:59:42.736Z\",\"modifyddate\":\"2016-06-05T07:59:42.736Z\",\"deposit\":\"2\",\"sqfeetrate\":\"2000\",\"bookingcharges\":\"200\",\"selectimage6\":0,\"selectimage7\":0,\"selectimage8\":0,\"selectimage9\":0,\"selectimage10\":0,\"postedon\":\"2016-06-05T07:59:42.736Z\",\"selectpropertytobeid\":0,\"availablefrom\":\"2016-06-05T07:59:42.736Z\",\"selectpropertykeyid\":\"2\",\"selectflatamenitiesid\":[\"1\",\"2\"],\"status\":true,\"selectsocietyamenitiesid\":[\"1\"]}";
            ObjectMapper mapper = new ObjectMapper();
//            PropertyForm user = mapper.readValue(str, PropertyForm.class);
//            System.out.println(user.getTotalarea());
        } catch (Exception ex) {
            Logger.getLogger(testData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
