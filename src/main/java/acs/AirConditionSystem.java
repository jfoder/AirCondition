package acs;

import acs.entities.Station;
import acs.visitors.ISystemVisitor;

import java.util.List;

/**
 * Class that holds all loaded stations (that include stands and records)
 */
public class AirConditionSystem {
    private List<Station> stations;

    public AirConditionSystem(List<Station> stations){
        this.stations = stations;
    }

    public void acceptVisitor(ISystemVisitor visitor){
        visitor.accept(stations);
    }
}
