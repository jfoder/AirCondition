package acs.visitors;

import acs.entities.Station;

import java.util.List;

/**
 * Visitor used to print value of selected parameter and stations
 */
public class ParameterPrintingVisitor implements ISystemVisitor {
    public void accept(List<Station> stations) {
        if(stations.isEmpty()) {
            throw new IllegalArgumentException("Nie znaleziono stacji o podanej nazwie mierzącej podany parametr");
        }
        Station s = stations.get(0);
        System.out.print(s.getName() + ": ");
        if(s.getStands().isEmpty()) throw new IllegalArgumentException("Nie znaleziono stanowisk pomiarowych mierzących podany parametr dla stacji: " + s.getName());
        if(s.getStands().get(0).getRecords().isEmpty()) throw new IllegalArgumentException("Nie odnotowano pomiarów danego parametru w podanym czasie");
        System.out.println(s.getStands().get(0).getParam() + " " + s.getStands().get(0).getRecords().get(0).getSecond() + " (" + s.getStands().get(0).getRecords().get(0).getFirst() + ")");
    }
}
