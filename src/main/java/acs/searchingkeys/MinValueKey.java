package acs.searchingkeys;

import acs.entities.Stand;
import acs.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Key that is used to select data neccessary to calculate minimum measured value for selected day and hour
 */
public class MinValueKey implements ISearchKey{
    private Date date;
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

    public MinValueKey(String day, String hour){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            this.date = sdf.parse(day + " " + hour);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Podana data jest niepoprawna");
        }
    }
}
