package acs.searchingkeys;

import acs.entities.Stand;
import acs.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Key that is used to select data neccessary to print selected parameter value in specific station and date
 */
public class StationParameterKey implements ISearchKey {
    private Date date;
    private String stationName;
    private String parameter;

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
        return s.getParam().toString().toLowerCase().equals(this.parameter.toLowerCase());
    }

    public boolean checkIfCorrect(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date toCheck;
        try{
            toCheck = sdf.parse(date);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Pobrane dane pomiarowe zawierały datę w nieprawidłowym formacie: " + date);
        }
        return Math.abs(toCheck.getTime() - this.date.getTime()) < 3600000 && toCheck.getHours() == this.date.getHours();
    }

    public StationParameterKey(String day, String hour, String stationName, String parameter){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            this.date = sdf.parse(day + " " + hour);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Podana data jest niepoprawna");
        }
        this.stationName = stationName;
        this.parameter = parameter;
    }
}
