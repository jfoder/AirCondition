package acs.visitors;

import acs.entities.Pair;
import acs.entities.Stand;
import acs.entities.Station;

import java.util.List;

/**
 * Visitor that calculates and prints maximum and minimum measured values
 */
public class MinMaxVisitor implements ISystemVisitor {
    public void accept(List<Station> stations) {
        Double min = 1000000.0;
        String minText = "";
        Double max = 0.0;
        String maxText = "";
        for(Station s : stations){
            for(Stand st : s.getStands()){
                for(Pair<String, Double> pair : st.getRecords()){
                    if(pair.getSecond() > max){
                        max = pair.getSecond();
                        maxText = s.getName() + " " + pair.getFirst();
                    }
                    if(pair.getSecond() < min && pair.getSecond() > 0){
                        min = pair.getSecond();
                        minText = s.getName() + " " + pair.getFirst();
                    }
                }
            }
        }
        System.out.println("Wartość największa:");
        System.out.println(maxText + " " + max);
        System.out.println("Wartość najmniejsza:");
        System.out.println(minText + " " + min);
    }
}
