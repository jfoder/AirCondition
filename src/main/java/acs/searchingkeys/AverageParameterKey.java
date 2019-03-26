package acs.searchingkeys;

import acs.entities.Parameter;
import acs.entities.Stand;
import acs.entities.Station;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Key that is used to get average values of selected parameter and time period
 * @see ISearchKey
 */
public class AverageParameterKey implements ISearchKey {
    private Date beginDate, endDate;
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date toCheck;
        try{
            toCheck = simpleDateFormat.parse(date);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Incorrect date format, expected YYYY-MM-DD HH:MM:SS but received: " + date);
        }
        return toCheck.compareTo(beginDate) >= 0 && toCheck.compareTo(endDate) <= 0;
    }

    public AverageParameterKey(String beginDay, String beginHour, String endDay, String endHour, String stationName, String parameter){
        Parameter.getParameter(parameter);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try{
            this.beginDate = simpleDateFormat.parse(beginDay + " " + beginHour);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Incorrect date format, expected YYYY-MM-DD HH:MM:SS but received: " + beginDay + " " + beginHour);
        }
        try{
            this.endDate = simpleDateFormat.parse(endDay + " " + endHour);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Incorrect date format, expected YYYY-MM-DD HH:MM:SS but received: " + beginDay + " " + beginHour);
        }
        this.stationName = stationName;
        this.parameter = parameter;
    }
}
