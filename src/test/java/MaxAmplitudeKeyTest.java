import acs.searchingkeys.MaxAmplitudeKey;
import acs.entities.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxAmplitudeKeyTest {
    @Test
    void standsRequired() {
        String[] stations = {""};
        MaxAmplitudeKey cik = new MaxAmplitudeKey(12, stations);
        assertTrue(cik.standsRequired());
    }

    @Test
    void indexesRequired() {
        String[] stations = {""};
        MaxAmplitudeKey cik = new MaxAmplitudeKey(12, stations);
        assertTrue(!cik.indexesRequired());
    }

    @Test
    void checkIfCorrect() {
        for(int i = 1; i <= 500; i++){
            String[] stations = new String[i];
            for(int j = 0; j < i; j++) stations[j] = Integer.toString(j);
            MaxAmplitudeKey mak = new MaxAmplitudeKey(12, stations);
            Station s = new Station();
            s.setName(Integer.toString(((i + i%2)/2) - 1));
            assertTrue(mak.checkIfCorrect(s), Integer.toString(i));
        }
    }
}