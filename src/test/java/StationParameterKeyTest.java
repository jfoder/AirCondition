import acs.searchingkeys.StationParameterKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationParameterKeyTest {
    @Test
    void standsRequired() {
        StationParameterKey spk = new StationParameterKey("2018-10-20", "12:00", "asd", "pm10");
        assertTrue(spk.standsRequired());
    }

    @Test
    void indexesRequired() {
        StationParameterKey spk = new StationParameterKey("2018-10-20", "12:00", "asd", "pm10");
        assertTrue(!spk.indexesRequired());
    }
}