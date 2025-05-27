package org.example.menu;

import org.example.feeadder.FeeAdder;
import org.example.feeadder.FeeAdderImpl;
import org.example.historymanager.HistoryManager;
import org.example.historymanager.HistoryManagerImpl;
import org.example.model.Car;
import org.example.model.Delivery;
import org.example.model.Passenger;
import org.example.parking.Parking;
import org.example.parking.parkingdao.ParkingDao;
import org.example.parking.parkingdao.ParkingDaoImpl;
import org.example.timesimulator.TimeSimulator;

import java.util.Scanner;

import static org.example.timesimulator.TimeSimulator.localTime;

public class Menu {
    private Parking parking = new Parking(2, 2);
    private HistoryManager historyManager = new HistoryManagerImpl();
    private ParkingDao parkingDao = new ParkingDaoImpl(parking, historyManager);
    private FeeAdder feeAdder = new FeeAdderImpl(10, 2, parking);
    private Scanner scanner = new Scanner(System.in);

    public void beginParking() {
        while (true) {
            System.out.println("\n--- Parking System Menu ---");
            System.out.println("1. Register a car");
            System.out.println("2. Unregister a car");
            System.out.println("3. List parked cars");
            System.out.println("4. Advance time by 1 hour");
            System.out.println("5. Display parking history so far");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter registration: ");
                    String reg = scanner.nextLine();
                    System.out.println("Car type (PASSENGER/DELIVERY):");
                    System.out.println("1. Passenger");
                    System.out.println("2. Delivery");
                    System.out.print("Select option: ");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    Car car = handleUserChoice(type, reg);

                    if (car != null) {
                        car.setTimeOfArrival(localTime);
                        parkingDao.addCar(car);
                    } else {
                        System.out.println("Invalid car type selected");
                    }
                }
                case 2 -> {
                    System.out.print("Enter registration to remove: ");
                    String reg = scanner.nextLine();

                    Car car = ParkingDao.findCarByReg(parking, reg);
                    if (car != null) {
                        parkingDao.removeCar(car);
                    } else {
                        System.out.println("Car not found.");
                    }
                }
                case 3 -> parking.listCars();
                case 4 -> {
                    TimeSimulator.forwardTime();
                    feeAdder.checkParking();
                    System.out.println("Time advanced by 1 hour.");
                }
                case 5 -> {
                    historyManager.getHistory();
                }
                case 6 -> {
                    System.out.println(historyManager.generateEndOfDayReport());
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static Car handleUserChoice(int type, String reg) {
        return switch (type) {
            case 1 -> new Passenger(reg);
            case 2 -> new Delivery(reg);
            default -> null;
        };
    }
}
