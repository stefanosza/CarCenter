/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stefz.database;

import com.stefz.models.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stefz
 */

public class DBUtil extends DBConnection {

    public DBUtil() {
    }

    public String checkRole(int ID) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ROLE FROM USERS WHERE ID=" + ID);
        String role = "None";
        while (resultSet.next()) {
            role = resultSet.getString("ROLE");
        }
        closeConnection();
        return role;
    }

    public int getUserIDByLoginCredentials(String name, String password) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID FROM USERS WHERE USERNAME = '" + name + "' AND PASSWORD = '" + password + "' ");
        int id = -1;
        while (resultSet.next()) {
            id = resultSet.getInt("ID");
        }
        closeConnection();
        return id;
    }
    
    public Connection creareLocalConnection(){
        Connection myConnection = null;
        try {
            myConnection = getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    return myConnection;
    }

    public ArrayList<Car> getAllCars() throws ClassNotFoundException, SQLException {
        
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT ID, BRAND, MODEL, HORSEPOWER, CC, YEAR, TRANSMISSION, TYPE, PRICE, RENTPRICE FROM CARS");

        ArrayList<Car> cars = new ArrayList<Car>();
        Car car;

        while (resultSet.next()) {

            car = new Car(
                    resultSet.getInt("ID"),
                    resultSet.getString("BRAND"),
                    resultSet.getString("MODEL"),
                    resultSet.getInt("HORSEPOWER"),
                    resultSet.getInt("CC"),
                    resultSet.getString("TRANSMISSION"),
                    resultSet.getInt("YEAR"),
                    resultSet.getString("TYPE"),
                    resultSet.getInt("PRICE"),
                    resultSet.getInt("RENTPRICE")
            );

            cars.add(car);
        }
        closeConnection();

        return cars;
    }

}
