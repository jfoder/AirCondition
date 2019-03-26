package acs.visitors;


import acs.entities.Pair;
import acs.entities.Parameter;
import acs.entities.Stand;
import acs.entities.Station;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Visitor that prints stands that measured values above norms
 */
public class AboveNormVisitor implements acs.visitors.ISystemVisitor {
    public void accept(List<Station> stations) {
        TreeMap<Double, String> aboveNorm = new TreeMap<Double, String>();
        boolean anyRecord = false;
        if(stations.isEmpty()) throw new IllegalArgumentException("Nie znaleziono żadnej stacji o podanej nazwie");
        for(Station s : stations){
            for(Stand st : s.getStands()){
                if(st.getRecords().size() > 1) throw new IllegalArgumentException("Wczytano niepoprawne pomiary (więcej niż jeden pomiar dla stanowiska)");
                if(!st.getRecords().isEmpty()){
                    anyRecord = true;
                    Pair<String,Double> record = st.getRecords().get(0);
                    Parameter p = st.getParam();
                    Double above = record.getSecond() - p.getNorm();
                    if(above > 0) {
                        aboveNorm.put(above, Integer.toString(st.getId()) + " (" + st.getParam().toString() + ")");
                    }
                }
            }
        }
        if(!anyRecord) throw new IllegalArgumentException("Nie można odnaleźć żadnych pomiarów ze wskazanej godziny");

        for(Map.Entry<Double, String> pair : aboveNorm.entrySet()){
            System.out.println("ID stacji: " + pair.getValue() + " Norma przekroczona o: " + pair.getKey());
        }
    }
}
