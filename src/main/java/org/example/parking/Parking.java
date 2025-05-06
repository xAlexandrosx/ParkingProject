package org.example.parking;

import org.example.model.Car;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parking {
    private LocalDateTime localTime;
    private List<Car> passengerCarSpots;
    private int passengerCarSpotsMax;
    private List<Car> deliveryCarSpots;
    private int deliveryCarSpotsMax;
    private int feeValue;
    private int freeHours;

    public Parking(int passengerCarSpotsMax, int deliveryCarSpotsMax, int feeValue, int freeHours) {
        this.localTime = LocalDateTime.now();
        this.passengerCarSpots = new ArrayList<>();
        this.passengerCarSpotsMax = passengerCarSpotsMax;
        this.deliveryCarSpots = new ArrayList<>();
        this.deliveryCarSpotsMax = deliveryCarSpotsMax;
        this.feeValue = feeValue;
        this.freeHours = freeHours;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
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

    public int getFeeValue() {
        return feeValue;
    }

    public void setFeeValue(int feeValue) {
        this.feeValue = feeValue;
    }

    public int getFreeHours() {
        return freeHours;
    }

    public void setFreeHours(int freeHours) {
        this.freeHours = freeHours;
    }

    public boolean registerCarEntry(Car car) {
        List<Car> allCars = new ArrayList<>();
        allCars.addAll(passengerCarSpots);
        allCars.addAll(deliveryCarSpots);

        for (Car c : allCars) {
            if (c.getRegistration().equalsIgnoreCase(car.getRegistration())) {
                System.out.println("Error. Car with " + car.getRegistration() + " already exists.");
                return false;
            }
        }

        if (car.getCarType() == Car.CarType.PASSENGER
                && passengerCarSpots.size() == passengerCarSpotsMax) {
            System.out.println("Error. All passenger car spots occupied.");
            return false;
        }

        if (car.getCarType() == Car.CarType.DELIVERY
                && deliveryCarSpots.size() == deliveryCarSpotsMax) {
            System.out.println("Error. All delivery car spots occupied.");
            return false;
        }

        addCar(car);
        return true;
    }

    private void addCar(Car car) {
        if (car.getCarType() == Car.CarType.PASSENGER) {
            passengerCarSpots.add(car);
        }

        if (car.getCarType() == Car.CarType.DELIVERY) {
            deliveryCarSpots.add(car);
        }

        System.out.println("Car added succesfully!");
    }

    public boolean unregisterCar(Car car) {
        if (passengerCarSpots.contains(car) || deliveryCarSpots.contains(car)) {
            removeCar(car);
            return true;
        } else {
            System.out.println("No such car found.");
            return false;
        }
    }

    private void removeCar(Car car) {
        if (car.getCarType() == Car.CarType.PASSENGER) {
            passengerCarSpots.remove(car);
        }

        if (car.getCarType() == Car.CarType.DELIVERY) {
            passengerCarSpots.remove(car);
        }

        System.out.println("Car removed succesfully.");
        if (car.getBonusFee() != 0) {
            System.out.println("Bonus overtime fee: " + car.getBonusFee() + "zł");
        }
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

    private boolean checkFeeEligible(Car car) {
        if (car.getTimeOfArrival() == null) {
            return false;
        }

        long hoursParked = java.time.Duration.between(car.getTimeOfArrival(), localTime).toHours();
        return hoursParked > freeHours;
    }

    private void addFee(Car car) {
        car.setBonusFee(car.getBonusFee() + feeValue);
    }

    public void timeForward() {
        List<Car> allCars = new ArrayList<>();
        allCars.addAll(passengerCarSpots);
        allCars.addAll(deliveryCarSpots);

        this.localTime = this.localTime.plusHours(1);

        for (Car car : allCars) {
            if (checkFeeEligible(car)) {
                addFee(car);
            }
        }
    }
}