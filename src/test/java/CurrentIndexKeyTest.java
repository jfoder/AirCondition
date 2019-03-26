import acs.searchingkeys.CurrentIndexKey;
import acs.entities.Parameter;
import acs.entities.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentIndexKeyTest {

    @Test
    void standsRequired() {
        CurrentIndexKey cik = new CurrentIndexKey("");
        assertTrue(!cik.standsRequired());
    }

    @Test
    void indexesRequired() {
        CurrentIndexKey cik = new CurrentIndexKey("");
        assertTrue(cik.indexesRequired());
    }

    @Test
    void checkIfCorrect() {
        for(int i = 0; i <= 100000; i++){
            CurrentIndexKey cik = new CurrentIndexKey(Parameter.values()[i%7].toString() + i);
            Station s = new Station();
            s.setName(Parameter.values()[i%7].toString() + i);
            assertTrue(cik.checkIfCorrect(s));
        }
    }
}