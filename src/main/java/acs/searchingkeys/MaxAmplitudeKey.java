package acs.searchingkeys;

import acs.entities.Stand;
import acs.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Key that selects data neccessary to calculate maximum amplitude for selected station
 */
public class MaxAmplitudeKey implements ISearchKey {
    private int beginHour;
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
        return true;
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
        return currentDate.getDay() == toCheck.getDay() && toCheck.getHours() >= this.beginHour;
    }

    public MaxAmplitudeKey(int beginHour, String[] stations){
        if(beginHour > new Date().getHours()) throw new IllegalArgumentException("Podana godzina nie może być większa niż aktualna");
        this.beginHour = beginHour;
        this.stations = stations;
    }
}
