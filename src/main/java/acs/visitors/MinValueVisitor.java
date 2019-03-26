package acs.visitors;

import acs.entities.Pair;
import acs.entities.Stand;
import acs.entities.Station;

import java.util.List;

/**
 * Visitor that calculates and prints minimum measured value of selected data
 */
public class MinValueVisitor implements ISystemVisitor {
    public void accept(List<Station> stations) {
        Double min = 1000000.0;
        String minParam = "";
        for(Station s : stations){
            for(Stand st : s.getStands()){
                for(Pair<String, Double> record : st.getRecords()){
                    if(record.getSecond() < min && record.getSecond() > 0) min = record.getSecond();
                    minParam = st.getParam().getName();
                }
            }
        }
        System.out.println("Najmniejszą wartość osiągnął parametr " + minParam + ": " + min);
    }
}
