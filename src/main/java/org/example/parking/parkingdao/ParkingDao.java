package org.example.parking.parkingdao;

import org.example.model.Car;

public interface ParkingDao {
    void addCar(Car car);
    void removeCar(Car car);
}
