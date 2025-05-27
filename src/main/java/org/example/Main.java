package org.example;

import org.example.feeadder.FeeAdder;
import org.example.feeadder.FeeAdderImpl;
import org.example.historymanager.HistoryManager;
import org.example.historymanager.HistoryManagerImpl;
import org.example.menu.Menu;
import org.example.model.Car;
import org.example.parking.Parking;
import org.example.parking.parkingdao.ParkingDao;
import org.example.parking.parkingdao.ParkingDaoImpl;
import org.example.timesimulator.TimeSimulator;

import java.util.List;
import java.util.Scanner;

import static org.example.timesimulator.TimeSimulator.localTime;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.beginParking();
    }
}
