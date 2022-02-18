package se.lexicon.recipeassignmentapi.model;

public class Measurement {
    private String id;
    private String measurement;

    public Measurement(String id, String measurement) {
        this.id = id;
        this.measurement = measurement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }


}
