package org.example.model;

public class Passenger extends Car {
    public Passenger(String registration) {
        super(registration, CarType.PASSENGER);
        bonusFee = 0;
    }
}
