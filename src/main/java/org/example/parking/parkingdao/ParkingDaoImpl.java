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

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public HistoryManager getHistoryManager() {
        return historyManager;
    }

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public void addCar(Car car) {
        List<Car> allCars = new ArrayList<>();
        allCars.addAll(parking.getPassengerCarSpots());
        allCars.addAll(parking.getDeliveryCarSpots());

        for (Car c : allCars) {
            if (c.getRegistration().equalsIgnoreCase(car.getRegistration())) {
                System.out.println("Error. Car with " + car.getRegistration() + " already exists.");
                return;
            }
        }

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

        historyManager.addToHistory(car, "enter");
        System.out.println("Car added succesfully!");
    }

    @Override
    public void removeCar(Car car) {
        if (parking.getPassengerCarSpots().contains(car)
                || parking.getDeliveryCarSpots().contains(car)) {
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
