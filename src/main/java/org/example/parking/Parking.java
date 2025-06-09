package org.example.parking;

import org.example.model.Car;
import java.util.ArrayList;
import java.util.List;

public class Parking {
    private List<Car> passengerCarSpots = new ArrayList<>();
    private int passengerCarSpotsMax;

    private List<Car> deliveryCarSpots = new ArrayList<>();
    private int deliveryCarSpotsMax;

    private List<Car> motorbikeSpots = new ArrayList<>();
    private int motorbikeSpotsMax;

    private List<Car> electricCarSpots = new ArrayList<>();
    private int electricCarSpotsMax;

    public Parking(int passengerCarSpotsMax, int deliveryCarSpotsMax, int motorbikeCarSpotMax, int electricCarSpotsMax) {
        this.passengerCarSpotsMax = passengerCarSpotsMax;
        this.deliveryCarSpotsMax = deliveryCarSpotsMax;
        this.motorbikeSpotsMax = motorbikeCarSpotMax;
        this.electricCarSpotsMax = electricCarSpotsMax;
    }

    public List<Car> getPassengerCarSpots() {
        return passengerCarSpots;
    }

    public void setPassengerCarSpots(List<Car> passengerCarSpots) {
        this.passengerCarSpots = passengerCarSpots;
    }

    public int getPassengerCarSpotsMax() {
        return passengerCarSpotsMax;
    }

    public List<Car> getDeliveryCarSpots() {
        return deliveryCarSpots;
    }

    public void setDeliveryCarSpots(List<Car> deliveryCarSpots) {
        this.deliveryCarSpots = deliveryCarSpots;
    }

    public int getDeliveryCarSpotsMax() {
        return deliveryCarSpotsMax;
    }

    public List<Car> getMotorbikeSpots() {
        return motorbikeSpots;
    }

    public void setMotorbikeSpots(List<Car> motorbikeSpots) {
        this.motorbikeSpots = motorbikeSpots;
    }

    public int getMotorbikeSpotsMax() {
        return motorbikeSpotsMax;
    }

    public List<Car> getElectricCarSpots() {
        return electricCarSpots;
    }

    public void setElectricCarSpots(List<Car> electricCarSpots) {
        this.electricCarSpots = electricCarSpots;
    }

    public int getElectricCarSpotsMax() {
        return electricCarSpotsMax;
    }

    public void listCars() {
        System.out.println("Passenger car spots (" + passengerCarSpots.size() + "/" + passengerCarSpotsMax + "):");
        for (Car car : passengerCarSpots) {
            System.out.println(car);
        }

        System.out.println("\nDelivery car spots (" + deliveryCarSpots.size() + "/" + deliveryCarSpotsMax + "):");
        for (Car car : deliveryCarSpots) {
            System.out.println(car);
        }

        System.out.println("\nMotorbike spots (" + motorbikeSpots.size() + "/" + motorbikeSpotsMax + "):");
        for (Car car : motorbikeSpots) {
            System.out.println(car);
        }

        System.out.println("\nElectric car spots (" + electricCarSpots.size() + "/" + electricCarSpotsMax + "):");
        for (Car car : electricCarSpots) {
            System.out.println(car);
        }
    }
}
