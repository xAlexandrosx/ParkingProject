package org.example.feeadder;

import org.example.model.Car;

public interface FeeAdder {
    void checkParking();
    void addFee(Car car);
}
