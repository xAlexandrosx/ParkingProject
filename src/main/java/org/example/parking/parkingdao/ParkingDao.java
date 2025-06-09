package org.example.parking.parkingdao;

import org.example.model.Car;
import org.example.parking.Parking;

public interface ParkingDao {
    void addCar(Car car);
    void removeCar(Car car);

    public static Car findCarByReg(Parking parking, String reg) {
        for (Car car : parking.getPassengerCarSpots()) {
            if (car.getRegistration().equalsIgnoreCase(reg)) return car;
        }
        for (Car car : parking.getDeliveryCarSpots()) {
            if (car.getRegistration().equalsIgnoreCase(reg)) return car;
        }
        for (Car car : parking.getMotorbikeSpots()) {
            if (car.getRegistration().equalsIgnoreCase(reg)) return car;
        }
        for (Car car : parking.getElectricCarSpots()) {
            if (car.getRegistration().equalsIgnoreCase(reg)) return car;
        }
        return null;
    }
}
