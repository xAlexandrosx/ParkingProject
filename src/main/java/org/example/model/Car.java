package org.example.model;

import org.example.timesimulator.TimeSimulator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class Car {
    protected String registration;
    protected LocalDateTime timeOfArrival;
    protected CarType carType;
    protected int bonusFee;

    public Car(String registration, CarType carType, int bonusFee) {
        this.registration = registration;
        this.timeOfArrival = LocalDateTime.now();
        this.carType = carType;
        this.bonusFee = bonusFee;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public LocalDateTime getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(LocalDateTime timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getBonusFee() {
        return bonusFee;
    }

    public void setBonusFee(int bonusFee) {
        this.bonusFee = bonusFee;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return bonusFee == car.bonusFee && Objects.equals(registration, car.registration) && Objects.equals(timeOfArrival, car.timeOfArrival) && carType == car.carType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, timeOfArrival, carType, bonusFee);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "Registration: " + registration +
                "\nType: " + carType +
                "\nTime of Arrival: " + timeOfArrival.format(formatter) +
                "\nCurrent Time: " + TimeSimulator.getFormattedLocalTime() +
                "\nBonus Fee: " + bonusFee + " zł";
    }


    public enum CarType {
        PASSENGER("p"),
        DELIVERY("d"),
        MOTORBIKE("m"),
        ELECTRIC("e");

        private final String code;

        CarType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
