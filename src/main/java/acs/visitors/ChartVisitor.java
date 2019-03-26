package acs.visitors;

import acs.entities.Pair;
import acs.entities.Stand;
import acs.entities.Station;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Visitor that prints charts of selected data
 */
public class ChartVisitor implements ISystemVisitor {
    private final int nameLength = 60;
    private final char chartSign = '|';
    public void accept(List<Station> stations) {
        Double max = 0.0;
        TreeMap<String, Double> values = new TreeMap<String, Double>();
        if(stations.isEmpty()) throw new IllegalArgumentException("Nie znaleziono żadnej stacji");
        for(Station s : stations){
            String name = s.getName();
            for(Stand st : s.getStands()){
                for(Pair<String, Double> record : st.getRecords()){
                    Double current = record.getSecond();
                    String date = record.getFirst();
                    if(current > max) max = current;
                    values.put(date + " " + name, current);
                }
            }
        }
        for(Map.Entry<String, Double> pair : values.entrySet()){
            printRow(pair.getKey(), pair.getValue(), max);
        }
    }

    private void printRow(String name, Double current, Double max){
        if(current == 0) return;
        int length = (int)((current/max)*50);
        StringBuilder nameBuilder = new StringBuilder(name);
        while(nameBuilder.length() < nameLength){
            nameBuilder.append(" ");
        }
        name = nameBuilder.toString();
        System.out.print(name);
        for(int i = 1; i <= length; i++) System.out.print(chartSign);
        System.out.println(" " + current);
    }
}
