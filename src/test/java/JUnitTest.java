


import com.example.newyear.InitReact;
import com.example.newyear.persistence.*;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public
class JUnitTest extends TestCase{


    @Test
    public void test(){
        //Init react
        InitReact ir = new InitReact();
        assertTrue(ir.serialize().length() > 0);

        //INFO RESOLUTION
        String inputDate = "07/28/2011 11:06:37 AM";
        Date date = null;
        try {
            date = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(inputDate);
        } catch (ParseException e) {
        }
        InfoResolution info_res = new InfoResolution(date,new ArrayList<ResolutionDo>(),"dd/MM/yyy",true);
        assertTrue(info_res.getStart_date().equals("28/07/2011"));
        assertTrue(info_res.getListe().size() == 0);
        assertTrue(info_res.isValide() == true);


        //USER CLASS
        UserClass u = new UserClass("Test","1234",false);
        assertTrue(UserClass.encrytePassword("1234").length() > 20);
        assertTrue(u.getToken() == null);
        u.setToken();
        assertTrue(u.getToken().length() > 20);


        //UserRes
        Resolution r1 = new Resolution("Test","test",11,1);
        UserRes ur = new UserRes(u,r1,date);
        ur.addResolutionDo(date,10);
        assertTrue(ur.getListe().size() == 1);
        assertTrue(ur.isAccomplish(date) == false);
        ur.addResolutionDo(date,10);
        assertTrue(ur.getListe().size() == 1 && ur.getListe().get(0).getNb_do() == 20);
        assertTrue(ur.isAccomplish(date) == true);
    }

}