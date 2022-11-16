package edu.metrostate.cardealer;

public class Vehicle {



    // TODO REMOVE THIS CLASS WE ALREADY HAVE A VEHICLE CLASS
    // only here for now to help as an example

    private final String id;
    private final String model;

    Vehicle(String id, String model){
        this.id = id;
        this.model = model;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

}
