package org.example.parking;

import org.example.model.Car;
import java.util.ArrayList;
import java.util.List;

public class Parking {
    private List<Car> passengerCarSpots = new ArrayList<>();
    private int passengerCarSpotsMax;
    private List<Car> deliveryCarSpots = new ArrayList<>();
    private int deliveryCarSpotsMax;

    public Parking(int passengerCarSpotsMax, int deliveryCarSpotsMax) {
        this.passengerCarSpotsMax = passengerCarSpotsMax;
        this.deliveryCarSpotsMax = deliveryCarSpotsMax;
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

    public void setPassengerCarSpotsMax(int passengerCarSpotsMax) {
        this.passengerCarSpotsMax = passengerCarSpotsMax;
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

    public void setDeliveryCarSpotsMax(int deliveryCarSpotsMax) {
        this.deliveryCarSpotsMax = deliveryCarSpotsMax;
    }

    public void listCars() {
        System.out.println("Passenger car spots ("
                + passengerCarSpots.size()
                + "/" + passengerCarSpotsMax
                + "):");

        for (Car car : passengerCarSpots) {
            System.out.println(car.toString());
            System.out.println();
        }

        System.out.println("Delivery car spots ("
                + deliveryCarSpots.size()
                + "/" + deliveryCarSpotsMax
                + "):");

        for (Car car : deliveryCarSpots) {
            System.out.println(car.toString());
            System.out.println();
        }
    }
}