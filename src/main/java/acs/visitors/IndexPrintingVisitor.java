package acs.visitors;

import acs.entities.Station;

import java.util.List;

/**
 * Visitor that prints current air index of selected station
 */
public class IndexPrintingVisitor implements ISystemVisitor {
    public void accept(List<Station> stations) {
        if(stations.isEmpty()){
            System.out.println("Nie znaleziono żadnej stacji o podanej nazwie");
            return;
        }
        for(Station s : stations){
            System.out.println(s.getName() + ": " + s.getIndexLevel());
        }
    }
}
