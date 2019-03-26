package acs.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents single stand assigned to station
 */
public class Stand {
    private int id;
    private Parameter param;
    private List<Pair<String, Double>> records;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stand(){
        this.records = new ArrayList<Pair<String, Double>>();
    }

    /**
     * @param date date of inserted record
     * @param value value of inserted record
     */
    public void putRecord(String date, double value){
        this.records.add(new Pair<String, Double>(date, value));
    }

    /**
     * @return list of records assigned to selected stand
     */
    public List<Pair<String, Double>> getRecords(){
        return this.records;
    }

    /**
     * @return param which is measured by selected stand
     */
    public Parameter getParam() {
        return param;
    }

    public void setParam(Parameter param) {
        this.param = param;
    }
    @Override
    public String toString(){
        return Integer.toString(id) + " " + param + " " + records.toString() + "\n";
    }
}
