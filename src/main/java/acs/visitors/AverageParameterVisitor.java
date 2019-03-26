package acs.visitors;

import acs.entities.Pair;
import acs.entities.Station;

import java.util.List;

/**
 * Visitor that is used to print average value of inserted data
 */
public class AverageParameterVisitor implements ISystemVisitor {
    public void accept(List<Station> stations) {
        double sum = 0;
        int count = 0;
        if(stations.isEmpty()) {
            throw new IllegalArgumentException("Nie znaleziono stacji o podanej nazwie mierzącej podany parametr");
        }
        Station s = stations.get(0);
        if(s.getStands().isEmpty()) throw new IllegalArgumentException("Nie znaleziono stanowisk pomiarowych mierzących podany parametr dla stacji: " + s.getName());
        if(s.getStands().get(0).getRecords().isEmpty()) throw new IllegalArgumentException("Nie odnotowano pomiarów danego parametru w podanym czasie");
        List<Pair<String, Double>> records = s.getStands().get(0).getRecords();
        for(Pair<String, Double> record : records){
            sum += record.getSecond();
            count++;
        }
        double average = sum/count;
        System.out.println("Wartość średnia parametru " + s.getStands().get(0).getParam().toString() + " dla stacji " + s.getName() + " wynosi: " + average);
    }
}
