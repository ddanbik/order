import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NipValidatorTest {
    @Test
    public void testPoprawnegoNip() {
        //given
        List<Long> nipy = new ArrayList<Long>();
        nipy.add(5359119042l);
        nipy.add(1124259519l);
        nipy.add(7978931794l);
        nipy.add(3416016090l);
        nipy.add(5196891045l);
        nipy.add(8239373710l);

        //when
        for (Long aLong : nipy) {
            boolean valid = NipValidator.valid(aLong);
            assertTrue(valid);

        }

        //then




    }


    @Test
    public void testNiepoprawnegoNip() {

        //given
        List<Long> nipy = new ArrayList<Long>();
        nipy.add(5359519042l);
        nipy.add(1124659519l);
        nipy.add(7978531794l);
        nipy.add(3416416090l);
        nipy.add(5196391045l);
        nipy.add(8239673710l);


        //when

        for (Long aLong : nipy) {
            boolean valid = NipValidator.valid(aLong);
            assertFalse(valid);

        }

        //then
    }


}