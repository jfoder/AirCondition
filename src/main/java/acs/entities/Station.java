package acs.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents single station
 */
public class Station {
    private int id;
    private String name;
    private String city;
    private List<Stand> stands;
    private String indexLevel;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param s stand that should be included to selected station
     */
    public void putStand(Stand s){
        this.stands.add(s);
    }

    /**
     * @return list of stands assigned to selected station
     */
    public List<Stand> getStands(){
        return this.stands;
    }

    public Station(){
        this.stands = new ArrayList<Stand>();
    }

    public String getIndexLevel() {
        return indexLevel;
    }

    public void setIndexLevel(String indexLevel) {
        this.indexLevel = indexLevel;
    }

    @Override
    public String toString(){
        return Integer.toString(id) + " " + name + " " + city + " " + indexLevel + "\n";
    }
}
