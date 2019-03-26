package acs.entities;

/**
 * Enum that store all measured parameters, it's names and norms
 */
public enum Parameter {
    SO2(0, "SO2", 350.0),
    NO2(1, "NO2", 200.0),
    PM10(2, "PM10", 50.0),
    PM25(3, "PM2.5", 25.0),
    CO(4, "CO", 10000.0),
    C6H6(5, "C6H6", 5.0),
    O3(6, "O3", 120.0);

    private int id;
    private String name;
    private Double norm;

    Parameter(int id, String name, Double norm){
        this.id = id;
        this.name = name;
        this.norm = norm;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public Double getNorm() {return norm;}

    /**
     * @param name name that should be converted to enum format
     * @return enum assigned to inserted name
     */
    public static Parameter getParameter(String name){
        name = name.toUpperCase();
        if(name.equals("PM2.5") || name.equals("PM25")) return PM25;
        Parameter[] p = Parameter.values();
        for(Parameter pm : p){
            if(pm.getName().equals(name)) return pm;
        }
        throw new IllegalArgumentException("Cannot find parameter: " + name);
    }

    /**
     * @param id id that should be converted to enum format
     * @return enum assigned to inserted id
     */
    public static Parameter getParameter(int id){
        Parameter[] p = Parameter.values();
        for(Parameter pm : p){
            if(pm.getId() == id) return pm;
        }
        throw new IllegalArgumentException("Cannot find parameter with ID: " + id);
    }
}
