import acs.Utility;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
    @Test
    public void getAllTest() throws IOException {
        assertEquals(Utility.getAll(), Utility.getAll());
    }
}