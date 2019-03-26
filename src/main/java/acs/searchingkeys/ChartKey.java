package acs.searchingkeys;

import acs.entities.Parameter;
import acs.entities.Stand;
import acs.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Key that is used to select data for making charts
 */
public class ChartKey implements ISearchKey {
    private Parameter parameter;
    private Date beginTime, endTime;
    private String[] stations;

    public boolean standsRequired() {
        return true;
    }

    public boolean indexesRequired() {
        return false;
    }

    public boolean checkIfCorrect(Station s) {
        for(String station : stations){
            if(s.getName().toLowerCase().equals(station.toLowerCase())) return true;
        }
        return false;
    }

    public boolean checkIfCorrect(Stand s) {
        return s.getParam() == this.parameter;
    }

    public boolean checkIfCorrect(String date) {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date toCheck;
        try{
            toCheck = sdf.parse(date);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Pobrane dane pomiarowe zawierały datę w nieprawidłowym formacie: " + date);
        }
        return toCheck.compareTo(beginTime) >= 0 && toCheck.compareTo(endTime) <= 0;
    }

    public ChartKey(String parameter, String beginTime, String endTime, String[] stations){
        beginTime += ":00";
        endTime += ":00";
        this.parameter = Parameter.getParameter(parameter);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            this.beginTime = sdf.parse(beginTime);
            this.endTime = sdf.parse(endTime);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Podane przedziały czasowe są w nieodpowiednim formacie");
        }
        if(this.beginTime.compareTo(this.endTime) > 0) throw new IllegalArgumentException("Podany początek przedziału nie może być datą wcześniejszą niż koniec przedziału");
        this.stations = stations;
    }
}
