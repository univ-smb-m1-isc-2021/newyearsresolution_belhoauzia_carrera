import com.example.newyear.Api.NewYearController;
import com.example.newyear.persistence.Resolution;
import com.example.newyear.persistence.ResolutionHome;
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
        List<Resolution> d = new ArrayList<>();
        Resolution r1 = new Resolution("Test","test",0,1);
        r1.setId(1L);
        r1.setId(2L);
        Resolution r2 = new Resolution("Test","test",0,1);
        assertTrue(rs.isInArray(l,r1) == false);
        l.add(new ResolutionHome(r1,10));
        assertTrue(rs.isInArray(l,r1) == true);
        d.add(r1);
        assertTrue(rs.isDone(l,d) == true);
        d.add(r2);
        assertTrue(rs.isDone(l,d) == false);

    }

}