package org.example.historymanager;

import org.example.model.Car;

import java.util.List;

public interface HistoryManager {
    void addToHistory(Car car, String action);
    String generateEndOfDayReport();
    List<String> getHistory();
}
