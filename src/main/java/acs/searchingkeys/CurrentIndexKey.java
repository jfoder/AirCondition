package acs.searchingkeys;

import acs.entities.Stand;
import acs.entities.Station;

/**
 * Key that is used to select data neccessary to print current index of inserted station
 */
public class CurrentIndexKey implements ISearchKey {
    private String name;

    public boolean standsRequired() {
        return false;
    }

    public boolean indexesRequired() {
        return true;
    }

    public boolean checkIfCorrect(Station s) {
        return s.getName().toLowerCase().equals(this.name.toLowerCase());
    }

    public boolean checkIfCorrect(Stand s) {
        return false;
    }

    public boolean checkIfCorrect(String date) {
        return false;
    }

    public CurrentIndexKey(String name){
        this.name = name;
    }
}
