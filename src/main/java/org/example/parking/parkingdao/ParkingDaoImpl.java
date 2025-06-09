package org.example.parking.parkingdao;

import org.example.historymanager.HistoryManager;
import org.example.model.Car;
import org.example.parking.Parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingDaoImpl implements ParkingDao {
    private Parking parking;
    private HistoryManager historyManager;

    public ParkingDaoImpl(Parking parking, HistoryManager historyManager) {
        this.parking = parking;
        this.historyManager = historyManager;
    }

    @Override
    public void addCar(Car car) {
        List<Car> allCars = new ArrayList<>();
        allCars.addAll(parking.getPassengerCarSpots());
        allCars.addAll(parking.getDeliveryCarSpots());
        allCars.addAll(parking.getMotorbikeSpots());
        allCars.addAll(parking.getElectricCarSpots());

        if (car.getCarType() == Car.CarType.PASSENGER
                && parking.getPassengerCarSpots().size() == parking.getPassengerCarSpotsMax()) {
            System.out.println("Error. All passenger car spots occupied.");
            return;
        }

        if (car.getCarType() == Car.CarType.DELIVERY
                && parking.getDeliveryCarSpots().size() == parking.getDeliveryCarSpotsMax()) {
            System.out.println("Error. All delivery car spots occupied.");
            return;
        }
        if (car.getCarType() == Car.CarType.MOTORBIKE
                && parking.getMotorbikeSpots().size() == parking.getMotorbikeSpotsMax()) {
            System.out.println("Error. All motorbike spots occupied.");
            return;
        }
        if (car.getCarType() == Car.CarType.ELECTRIC
                && parking.getElectricCarSpots().size() == parking.getElectricCarSpotsMax()) {
            System.out.println("Error. All electric car spots occupied.");
            return;
        }

        if (car.getCarType() == Car.CarType.PASSENGER) {
            List<Car> cars = parking.getPassengerCarSpots();
            cars.add(car);
            parking.setPassengerCarSpots(cars);
        }

        if (car.getCarType() == Car.CarType.DELIVERY) {
            List<Car> cars = parking.getDeliveryCarSpots();
            cars.add(car);
            parking.setDeliveryCarSpots(cars);
        }
        if (car.getCarType() == Car.CarType.MOTORBIKE) {
            List<Car> cars = parking.getMotorbikeSpots();
            cars.add(car);
            parking.setMotorbikeSpots(cars);
        }
        if (car.getCarType() == Car.CarType.ELECTRIC) {
            List<Car> cars = parking.getElectricCarSpots();
            cars.add(car);
            parking.setElectricCarSpots(cars);
        }

        historyManager.addToHistory(car, "enter");
        System.out.println("Car added succesfully!");
    }

    @Override
    public void removeCar(Car car) {
        if (parking.getPassengerCarSpots().contains(car)
                || parking.getDeliveryCarSpots().contains(car)
                || parking.getMotorbikeSpots().contains(car)
                || parking.getElectricCarSpots().contains(car)) {
            if (car.getCarType() == Car.CarType.PASSENGER) {
                List<Car> cars = parking.getPassengerCarSpots();
                cars.remove(car);
                parking.setPassengerCarSpots(cars);
            }

            if (car.getCarType() == Car.CarType.DELIVERY) {
                List<Car> cars = parking.getDeliveryCarSpots();
                cars.remove(car);
                parking.setDeliveryCarSpots(cars);
            }
            if (car.getCarType() == Car.CarType.MOTORBIKE) {
                List<Car> cars = parking.getMotorbikeSpots();
                cars.remove(car);
                parking.setMotorbikeSpots(cars);
            }
            if (car.getCarType() == Car.CarType.ELECTRIC) {
                List<Car> cars = parking.getElectricCarSpots();
                cars.remove(car);
                parking.setElectricCarSpots(cars);
            }

            historyManager.addToHistory(car, "exit");
            System.out.println("Car removed succesfully.");
            if (car.getBonusFee() != 0) {
                System.out.println("Bonus overtime fee: " + car.getBonusFee() + "zł");
            }
        } else {
            System.out.println("No such car found.");
        }
    }
}
