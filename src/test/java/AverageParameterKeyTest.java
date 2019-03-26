import acs.searchingkeys.AverageParameterKey;
import acs.entities.Parameter;
import acs.entities.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AverageParameterKeyTest {

    @Test
    public void indexesRequiredTest(){
        for(int i = 2000; i <= 3000; i++){
            AverageParameterKey apk = new AverageParameterKey("2018-10-20", "12:24", "2018-10-2" + i%10, "17:42", Integer.toString(i),  Parameter.values()[i%7].toString());
            assertTrue(!apk.indexesRequired(), "acs.searchingkeys.AverageParameterKey nie potrzebuje indeksów stanu powietrza");
        }
    }

    @Test
    public void standsRequiredTest(){
        for(int i = 2000; i <= 3000; i++){
            AverageParameterKey apk = new AverageParameterKey("2018-10-20", "12:24", "2018-10-2" + i%10, "17:42", Integer.toString(i),  Parameter.values()[i%7].toString());
            assertTrue(apk.standsRequired(), "acs.searchingkeys.AverageParameterKey wymaga stanowisk pomiarowych");
        }
    }

    @Test void checkIfCorrectTest(){
        for(int i = 0; i <= 100000; i++){
            Station s = new Station();
            s.setName(Integer.toString(i));
            AverageParameterKey apk = new AverageParameterKey("2018-10-20", "12:24", "2018-10-2" + i%10, "17:42", Integer.toString(i),  Parameter.values()[i%7].toString());
            assertTrue(apk.checkIfCorrect(s), "Nazwa stacji nie została poprawnie odczytana");

        }
    }

}