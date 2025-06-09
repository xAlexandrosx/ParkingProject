package org.example.feeadder;

import org.example.model.Car;
import org.example.parking.Parking;

import java.util.ArrayList;
import java.util.List;

import static org.example.timesimulator.TimeSimulator.localTime;

public class FeeAdderImpl implements FeeAdder {
    private int feeValue;
    private int freeHours;
    private Parking parking;

    public FeeAdderImpl(int feeValue, int freeHours, Parking parking) {
        this.feeValue = feeValue;
        this.freeHours = freeHours;
        this.parking = parking;
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

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void checkParking() {
        List<Car> cars = new ArrayList<>();
        cars.addAll(parking.getPassengerCarSpots());
        cars.addAll(parking.getDeliveryCarSpots());
        cars.addAll(parking.getMotorbikeSpots());
        cars.addAll(parking.getElectricCarSpots());
        
        for (Car car : cars) {
            addFee(car);
        }
    }

    @Override
    public void addFee(Car car) {
        if (checkFeeEligible(car)) {
            car.setBonusFee(car.getBonusFee() + feeValue);
        }
    }

    private boolean checkFeeEligible(Car car) {
        if (car.getTimeOfArrival() == null) {
            return false;
        }

        long hoursParked = java.time.Duration.between(car.getTimeOfArrival(), localTime).toHours();
        return hoursParked > freeHours;
    }
}
