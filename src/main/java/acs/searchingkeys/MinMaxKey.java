package acs.searchingkeys;

import acs.entities.Parameter;
import acs.entities.Stand;
import acs.entities.Station;

/**
 * Key that is used to select data neccessary to calculate minimum and maximum measured value for selected parameter
 */
public class MinMaxKey implements ISearchKey {
    private Parameter p;
    public boolean standsRequired() {
        return true;
    }

    public boolean indexesRequired() {
        return false;
    }

    public boolean checkIfCorrect(Station s) {
        return true;
    }

    public boolean checkIfCorrect(Stand s) {
        return s.getParam() == p;
    }

    public boolean checkIfCorrect(String date) {
        return true;
    }

    public MinMaxKey(String param){
        this.p = Parameter.getParameter(param);
    }
}
