package acs.visitors;

import acs.entities.Pair;
import acs.entities.Parameter;
import acs.entities.Stand;
import acs.entities.Station;

import java.util.List;

/**
 * Visitor that is used to calculate maximum amplitude of measured values
 */
public class MaxAmplitudeVisitor implements ISystemVisitor{
    public void accept(List<Station> stations) {
        double[] amplitudes = new double[7];
        for(int i = 0; i < 6; i++) amplitudes[i] = 0;
        if(stations.isEmpty()) throw new IllegalArgumentException("Nie znaleziono żadnej stacji o nazwie spośród podanych");
        for(Station s : stations){
            for(Stand st : s.getStands()){
                int id = st.getParam().getId();
                Double previous = null;
                double sum = 0;
                for(Pair<String, Double> records : st.getRecords()){
                    if(previous != null){
                        sum += Math.abs(records.getSecond() - previous);
                        previous = records.getSecond();
                    }
                    else previous = records.getSecond();
                }
                if(sum > amplitudes[id]) amplitudes[id] = sum;
            }
        }
        double max = 0;
        int id = 0;
        for(int i = 0; i <= 6; i++) {
            double a = amplitudes[i];
            if (a > max) {
                max = a;
                id = i;
            }
        }
        System.out.println("Największe wahanie zanotowano dla parametru " + Parameter.getParameter(id) + " i wyniosło ono: " + max);
    }
}
