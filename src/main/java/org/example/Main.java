package org.example;

import org.example.model.Car;
import org.example.parking.Parking;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(2, 2, 10, 2);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Parking System Menu ---");
            System.out.println("1. Register a car");
            System.out.println("2. Unregister a car");
            System.out.println("3. List parked cars");
            System.out.println("4. Advance time by 1 hour");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter registration: ");
                    String reg = scanner.nextLine();
                    System.out.print("Enter car type (PASSENGER/DELIVERY): ");
                    String typeStr = scanner.nextLine();
                    Car.CarType type = Car.CarType.valueOf(typeStr.toUpperCase());

                    Car car = new Car(reg, type);
                    car.setTimeOfArrival(parking.getLocalTime());

                    parking.registerCarEntry(car);
                }
                case 2 -> {
                    System.out.print("Enter registration to remove: ");
                    String reg = scanner.nextLine();

                    Car car = findCarByReg(parking, reg);
                    if (car != null) {
                        parking.unregisterCar(car);
                    } else {
                        System.out.println("Car not found.");
                    }
                }
                case 3 -> parking.listCars();
                case 4 -> {
                    parking.setLocalTime(parking.getLocalTime().plusHours(1));
                    parking.timeForward();
                    System.out.println("Time advanced by 1 hour.");
                }
                case 5 -> {
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static Car findCarByReg(Parking parking, String reg) {
        for (Car car : parking.getPassengerCarSpots()) {
            if (car.getRegistration().equalsIgnoreCase(reg)) return car;
        }
        for (Car car : parking.getDeliveryCarSpots()) {
            if (car.getRegistration().equalsIgnoreCase(reg)) return car;
        }
        return null;
    }
}
