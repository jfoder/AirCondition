package acs.searchingkeys;

import acs.entities.Stand;
import acs.entities.Station;

/**
 * Interface for all keys that check if inserted object (station, stand, date of record) should be loaded
 */
public interface ISearchKey {
    /**
     * @return true if stands are required for selected funcionality, false otherwise
     */
    boolean standsRequired();

    /**
     * @return true if indexes are required for selected funcionality, false otherwise
     */
    boolean indexesRequired();

    /**
     * @param s station that will be checked
     * @return true if inserted station should be loaded to provide neccessary information for specific funcionality, false otherwise
     */
    boolean checkIfCorrect(Station s);

    /**
     * @param s stand that will be checked
     * @return true if inserted stand should be loaded to provide neccessary information for specific funcionality, false otherwise
     */
    boolean checkIfCorrect(Stand s);

    /**
     * @param date date that will be checked
     * @return true if record with inserted date should be loaded to provide neccessary information for specific funcionality, false otherwise
     */
    boolean checkIfCorrect(String date);
}
