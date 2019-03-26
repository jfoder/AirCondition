package acs;

import acs.searchingkeys.*;
import acs.visitors.AboveNormVisitor;
import acs.visitors.AverageParameterVisitor;
import acs.visitors.ChartVisitor;
import acs.visitors.IndexPrintingVisitor;
import acs.visitors.MaxAmplitudeVisitor;
import acs.visitors.MinMaxVisitor;
import acs.visitors.MinValueVisitor;
import acs.visitors.ParameterPrintingVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * Funkcja obsługująca menu główne, pętla działa do momentu wybrania opcji nr 9
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Menu wyboru:");
        System.out.println("[1] Wyświetl aktualny indeks stanu powietrza dla podanej stacji");
        System.out.println("[2] Wyświetl wartość podanego parametru dla danej stacji, daty i godziny");
        System.out.println("[3] Wyświetl średnią wartość podanego parametru dla danej stacji i przedziału czasowego");
        System.out.println("[4] Wyświetl parametr, który wahał się najbardziej dzisiejszego dnia od podanej godziny");
        System.out.println("[5] Wyszukaj parametr, którego wartość o podanej godzinie była najmniejsza");
        System.out.println("[6] Wyświetl stanowiska dla podanej stacji, które przekroczyły dozwolone normy");
        System.out.println("[7] Wyświetl największą i najmniejszą odnotowaną wartość dla danego parametru");
        System.out.println("[8] Narysuj wykres podanego parametru dla podanych stacji");
        System.out.println("[9] Wyłącz program");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int choice = Integer.parseInt(reader.readLine());
            switch (choice) {
                case 1: {
                    System.out.println("Podaj pełną nazwę stacji pomiarowej:");
                    String stationName = reader.readLine();
                    AirConditionSystem acs = Loader.load(new CurrentIndexKey(stationName));
                    acs.acceptVisitor(new IndexPrintingVisitor());
                    break;
                }
                case 2: {
                    System.out.println("Podaj datę w formacie YYYY-MM-DD:");
                    String date = reader.readLine();
                    System.out.println("Podaj godzinę w formacie HH:MM:");
                    String time = reader.readLine();
                    System.out.println("Podaj pełną nazwę stacji pomiarowej:");
                    String stationName = reader.readLine();
                    System.out.println("Podaj skrót parametru (PM10, PM25, SO2, O3, (...)):");
                    String parameter = reader.readLine();
                    AirConditionSystem acs = Loader.load(new StationParameterKey(date, time, stationName, parameter));
                    acs.acceptVisitor(new ParameterPrintingVisitor());
                    break;
                }
                case 3: {
                    System.out.println("Podaj datę początku przedziału w formacie YYYY-MM-DD:");
                    String beginDate = reader.readLine();
                    System.out.println("Podaj godzinę początku przedziału w formacie HH:MM:");
                    String beginTime = reader.readLine();
                    System.out.println("Podaj datę końca przedziału w formacie YYYY-MM-DD:");
                    String endDate = reader.readLine();
                    System.out.println("Podaj godzinę końca przedziału w formacie HH:MM:");
                    String endTime = reader.readLine();
                    System.out.println("Podaj pełną nazwę stacji pomiarowej:");
                    String stationName = reader.readLine();
                    System.out.println("Podaj skrót parametru (PM10, PM25, SO2, O3, (...)):");
                    String parameter = reader.readLine();
                    AirConditionSystem acs = Loader.load(new AverageParameterKey(beginDate, beginTime, endDate, endTime, stationName, parameter));
                    acs.acceptVisitor(new AverageParameterVisitor());
                    break;
                }
                case 4: {
                    System.out.println("Podaj godzinę, od której rozpocząć pomiar:");
                    int beginHour = Integer.parseInt(reader.readLine());
                    System.out.println("Podaj pełne nazwy stacji pomiarowych oddzielone znakiem |:");
                    String stationNames[] = reader.readLine().split("\\|");
                    for (int i = 0; i < stationNames.length; i++) stationNames[i] = stationNames[i].trim();
                    AirConditionSystem acs = Loader.load(new MaxAmplitudeKey(beginHour, stationNames));
                    acs.acceptVisitor(new MaxAmplitudeVisitor());
                    break;
                }
                case 5: {
                    System.out.println("Podaj datę w formacie YYYY-MM-DD:");
                    String beginDate = reader.readLine();
                    System.out.println("Podaj godzinę w formacie HH:MM:");
                    String beginTime = reader.readLine();
                    AirConditionSystem acs = Loader.load(new MinValueKey(beginDate, beginTime));
                    acs.acceptVisitor(new MinValueVisitor());
                    break;
                }
                case 6: {
                    System.out.println("Podaj pełną nazwę stacji pomiarowej:");
                    String stationName = reader.readLine();
                    System.out.println("Podaj datę w formacie YYYY-MM-DD:");
                    String beginDate = reader.readLine();
                    System.out.println("Podaj godzinę w formacie HH:MM:");
                    String beginTime = reader.readLine();
                    AirConditionSystem acs = Loader.load(new AboveNormKey(stationName, beginDate, beginTime));
                    acs.acceptVisitor(new AboveNormVisitor());
                    break;
                }
                case 7: {
                    System.out.println("Podaj skrót parametru (PM10, PM25, SO2, O3, (...)):");
                    String parameter = reader.readLine();
                    AirConditionSystem acs = Loader.load(new MinMaxKey(parameter));
                    acs.acceptVisitor(new MinMaxVisitor());
                    break;
                }
                case 8: {
                    System.out.println("Podaj skrót parametru (PM10, PM25, SO2, O3, (...)):");
                    String parameter = reader.readLine();
                    System.out.println("Podaj datę początku przedziału w formacie YYYY-MM-DD:");
                    String beginDate = reader.readLine();
                    System.out.println("Podaj godzinę początku przedziału w formacie HH:MM:");
                    String beginTime = reader.readLine();
                    System.out.println("Podaj datę końca przedziału w formacie YYYY-MM-DD:");
                    String endDate = reader.readLine();
                    System.out.println("Podaj godzinę końca przedziału w formacie HH:MM:");
                    String endTime = reader.readLine();
                    System.out.println("Podaj pełne nazwy stacji pomiarowych oddzielone znakiem |:");
                    String stationNames[] = reader.readLine().split("\\|");
                    for (int i = 0; i < stationNames.length; i++) stationNames[i] = stationNames[i].trim();
                    AirConditionSystem acs = Loader.load(new ChartKey(parameter, beginDate + " " + beginTime, endDate + " " + endTime, stationNames));
                    acs.acceptVisitor(new ChartVisitor());
                    break;
                }
                case 9: {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Podana liczba musi być z zakresu [1..9]");
                }
            }
        }
    }
}
