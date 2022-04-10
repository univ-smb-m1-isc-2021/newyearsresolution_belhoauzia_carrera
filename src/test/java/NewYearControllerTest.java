import com.example.newyear.Api.NewYearController;
import com.example.newyear.NewYearApplication;
import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.ResolutionHome;
import com.example.newyear.service.ResolutionService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public
class NewYearControllerTest extends TestCase{


    @Test
    public void test(){
        NewYearController rs = new NewYearController(null);
        ArrayList<ResolutionHome> l = new ArrayList<ResolutionHome>();
        List<ResolutionHome> d = new ArrayList<ResolutionHome>();
        Resolution r1 = new Resolution("Test","test",0,1);
        r1.setId(1L);
        r1.setId(2L);
        Resolution r2 = new Resolution("Test","test",0,1);
        assertTrue(rs.isInArray(l,r1) == false);
        l.add(new ResolutionHome(r1,10));
        assertTrue(rs.isInArray(l,r1) == true);
        d.add(new ResolutionHome(r1,10));
        //assertTrue(rs.isDone(l,d) == true);
        d.add(new ResolutionHome(r2,10));
        //assertTrue(rs.isDone(l,d) == false);

    }

}