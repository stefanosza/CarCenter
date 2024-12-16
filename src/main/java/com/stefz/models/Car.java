/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefz.models;

/**
 *
 * @author stefz
 */
public class Car {
    
    private Integer id;
    private String brand;
    private String model;
    private Integer horsepower;
    private Integer cc;
    private String transmission;
    private Integer year;
    private String type;
    private Integer price;
    private Integer rentprice;

    public Car(String brand, String model, Integer horsepower, Integer cc, String transmission, Integer year, String type, Integer price, Integer rentprice) {
        this.brand = brand;
        this.model = model;
        this.horsepower = horsepower;
        this.cc = cc;
        this.transmission = transmission;
        this.year = year;
        this.type = type;
        this.price = price;
        this.rentprice = rentprice;
    }

    public Car(Integer id, String brand, String model, Integer horsepower, Integer cc, String transmission, Integer year, String type, Integer price, Integer rentprice) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.horsepower = horsepower;
        this.cc = cc;
        this.transmission = transmission;
        this.year = year;
        this.type = type;
        this.price = price;
        this.rentprice = rentprice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public Car(String brand, String model) {
       new Car(brand,model,1,1,null,1,null,1,1);
    }
    

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getCc() {
        return cc;
    }

    public void setCc(Integer cc) {
        this.cc = cc;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRentprice() {
        return rentprice;
    }

    public void setRentprice(Integer rentprice) {
        this.rentprice = rentprice;
    }

    @Override
    public String toString() {
        return "Car{" + "brand=" + brand + ", model=" + model + ", horsepower=" + horsepower + ", cc=" + cc + ", transmission=" + transmission + ", year=" + year + ", type=" + type + ", price=" + price + ", rentprice=" + rentprice + '}';
    }
    
 

}




