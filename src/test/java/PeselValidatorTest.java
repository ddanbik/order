import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PeselValidatorTest {

    @Test
    public void testPoprawnyPesel() {
        //given

        List<Long> pesele = new ArrayList<Long>();
        pesele.add(77123176954L);
        pesele.add(59071858836l);
        pesele.add(75082511874L);
        pesele.add(47052197597L);
        pesele.add(62071266217L);
        pesele.add(66031765352L);
        pesele.add(97101575171L);
        pesele.add(93070362697L);
        pesele.add(56071028212L);
        pesele.add(83011379998L);

        //when
        for (Long aLong : pesele) {
            boolean valid = PeselValidator.valid(aLong);
            assertTrue(valid);
        }


        //then


    }

    @Test
    public void testNiepoprawnyPesel() {

        //given

        long pesel = 7712317654L;
        //when
        boolean valid = PeselValidator.valid(pesel);

        //then
        assertFalse(valid);
    }
}