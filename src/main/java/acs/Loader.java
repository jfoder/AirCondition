package acs;

import acs.entities.Parameter;
import acs.entities.Stand;
import acs.entities.Station;
import acs.searchingkeys.ISearchKey;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that is used to load all neccessary data. It uses acs.searchingkeys.ISearchKey to know which data should be stored
 */
public class Loader {

    /**
     * @param searchKey key that tells which objects (stations, stands, records) should be stored to provide specific funcionality
     * @return class that include all selected records
     * @throws IOException
     */
    public static AirConditionSystem load(ISearchKey searchKey) throws IOException {
        List<Station> stations = new ArrayList<Station>();

        //LOADING STANDS
        String allStations = Utility.getAll();
        final JsonParser parser = Json.createParser(new StringReader(allStations));
        Station stationToLoad = new Station();
        boolean insideStation = false;
        boolean insideCity = false;
        while (parser.hasNext()) {
            final Event event = parser.next();
            if(event == Event.KEY_NAME) {
                if (parser.getString().equals("id") && !insideCity) {
                    parser.next();
                    insideStation = true;
                    stationToLoad = new Station();
                    stationToLoad.setId(parser.getInt());
                } else if (parser.getString().equals("stationName")) {
                    parser.next();
                    stationToLoad.setName(parser.getString());
                } else if (parser.getString().equals("name")) {
                    parser.next();
                    stationToLoad.setCity(parser.getString());
                } else if (parser.getString().equals("city")) {
                    insideCity = true;
                }
            }
            else if(event == Event.END_OBJECT){
                if (insideStation && searchKey.checkIfCorrect(stationToLoad)) {
                    insideStation = false;
                    stations.add(stationToLoad);
                }
                if(insideCity) insideCity = false;
            }
        }


        //LOADING AIR CONDITION INDEXES IF NECESSARY
        if (searchKey.indexesRequired()) {
            for (Station s : stations) {
                String index = Utility.getIndex(s.getId());
                final JsonParser indexParser = Json.createParser(new StringReader(index));
                boolean insideSt = false;
                while (indexParser.hasNext()) {
                    final Event event = indexParser.next();
                    if (event == Event.KEY_NAME) {
                        if (indexParser.getString().equals("stIndexLevel")) insideSt = true;
                    } else if (event == Event.VALUE_STRING && insideSt) {
                        s.setIndexLevel(indexParser.getString());
                        break;
                    } else if (event == Event.VALUE_NULL && insideSt) {
                        s.setIndexLevel("Nieznany");
                        break;
                    }
                }
            }
        }

        //LOADING STANDS IF NECESSARY
        if (searchKey.standsRequired()) {
            for (Station s : stations) {
                String stands = Utility.getStands(s.getId());
                final JsonParser standsParser = Json.createParser(new StringReader(stands));
                Stand st = new Stand();
                boolean toSave = false;
                while (standsParser.hasNext()) {
                    final Event event = standsParser.next();
                    if(event == Event.KEY_NAME){
                        if(standsParser.getString().equals("id")){
                            standsParser.next();
                            st.setId(standsParser.getInt());
                        }
                        else if(standsParser.getString().equals("paramCode")){
                            standsParser.next();
                            st.setParam(Parameter.getParameter(standsParser.getString()));
                        }
                    }
                    else if(event == Event.END_OBJECT){
                        if(!toSave) toSave = true;
                        else{
                            if(searchKey.checkIfCorrect(st)) s.putStand(st);
                            st = new Stand();
                            toSave = false;
                        }
                    }
                }

            }
        }

        //LOADING AIR CONDITION DATA ASSIGNED TO STATIONS
        for(Station s: stations){
            List<Stand> l = s.getStands();
            for(Stand stand : l){
                String records = Utility.getRecords(stand.getId());
                final JsonParser recordsParser = Json.createParser(new StringReader(records));
                while(recordsParser.hasNext()){
                    final Event event = recordsParser.next();
                    if(event == Event.KEY_NAME){
                        if(recordsParser.getString().equals("date")){
                            String date;
                            Double value;
                            Event e1, e2;
                            e1 = recordsParser.next();
                            if(e1 == Event.VALUE_NULL) continue;
                            date = recordsParser.getString();
                            recordsParser.next();
                            e2 = recordsParser.next();
                            if(e2 == Event.VALUE_NULL) continue;
                            value = recordsParser.getBigDecimal().doubleValue();
                            if(searchKey.checkIfCorrect(date)) stand.putRecord(date, value);
                        }
                    }
                }
            }
        }
        return new AirConditionSystem(stations);
    }
}
