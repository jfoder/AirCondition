package acs.searchingkeys;

import acs.entities.Stand;
import acs.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that implements acs.searchingkeys.ISearchKey. It is used to select data that is neccessary to find records with values above norms
 */
public class AboveNormKey implements ISearchKey {
    private Date date;
    private String stationName;
    public boolean standsRequired() {
        return true;
    }

    public boolean indexesRequired() {
        return false;
    }

    public boolean checkIfCorrect(Station s) {
        return s.getName().toLowerCase().equals(this.stationName.toLowerCase());
    }

    public boolean checkIfCorrect(Stand s) {
        return true;
    }

    public boolean checkIfCorrect(String date) {
        Date toCheck;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            toCheck = sdf.parse(date);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Podana data jest niepoprawna");
        }
        return Math.abs(toCheck.getTime() - this.date.getTime()) < 3600000 && toCheck.getHours() == this.date.getHours();
    }

    /**
     * @param stationName name of station that should be loaded
     * @param day day of records
     * @param hour hour of records
     */
    public AboveNormKey(String stationName, String day, String hour){
        this.stationName = stationName;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            this.date = sdf.parse(day + " " + hour);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Podana data jest niepoprawna");
        }
    }

}
