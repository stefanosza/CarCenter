/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefz.models;

import java.util.ArrayList;

/**
 *
 * @author stefz
 */
public class CarController {

    private static ArrayList<Car> carList = new ArrayList<>();
    
    public static void addCar(Car car){
        
        carList.add(car);
    }
    public static void showList(){
        for (Car car : carList){
            System.out.println(car);
        }
    }
    
    public CarController() {
    }

    public static ArrayList<Car> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<Car> carList) {
        this.carList = carList;
    }
        
}
