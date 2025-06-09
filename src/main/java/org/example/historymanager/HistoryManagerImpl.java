package org.example.historymanager;

import org.example.model.Car;
import java.util.ArrayList;
import java.util.List;

import static org.example.timesimulator.TimeSimulator.getFormattedLocalTime;

public class HistoryManagerImpl implements HistoryManager {
    private List<String> history = new ArrayList<>();
    private List<Car> carsParked = new ArrayList<>();
    private int totalBonusFees = 0;

    public void getHistory() {
        for (String entry : history) {
            System.out.println(entry);
        }
    }

    public void addToHistory(Car car, String action) {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormattedLocalTime())
                .append(": ")
                .append(car.getRegistration())
                .append(" ");

        if (action.equalsIgnoreCase("enter")) {
            sb.append("registered for parking.");
            carsParked.add(car);
        } else if (action.equalsIgnoreCase("exit")) {
            sb.append("unregistered from parking.");
            if (car.getBonusFee() > 0) {
                sb.append(" Overtime Fee: " + car.getBonusFee());
                totalBonusFees += car.getBonusFee();
            }
        }
        history.add(sb.toString());
    }

    public String generateEndOfDayReport() {
        StringBuilder report = new StringBuilder();
        report.append("--- End of Day Report ---\n");
        for (String entry : history) {
            report.append(entry).append("\n");
        }

        report.append("\nTotal Cars Parked Today: ").append(carsParked.size()).append("\n");
        report.append("Total Bonus Fees Collected: ").append(totalBonusFees).append("\n");

        return report.toString();
    }
}
