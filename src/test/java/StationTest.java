import acs.entities.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {
    @Test
    public void getNameTest(){
        for(int i = 0; i <= 100000; i++){
            Station s = new Station();
            s.setName(Integer.toString(i));
            assertTrue(s.getName().equals(Integer.toString(i)));
        }
    }


    @Test
    public void getIdTest(){
        for(int i = 0; i <= 100000; i++){
            Station s = new Station();
            s.setId(i);
            assertTrue(s.getId() == i);
        }
    }
}