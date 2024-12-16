/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.stefz.UI;

import com.stefz.Session.Settings;
import com.stefz.models.Car;
import com.stefz.database.DBUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author stefz
 */
public class Carlist extends javax.swing.JPanel {
    DBUtil db = new DBUtil();
    /**
     * Creates new form Carlist
     */
    public Carlist() {
        initComponents();
        
        try {
            loadCarDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carlist.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        carlistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    private void hideButtons() {
        btnCart.setVisible(false);
        btnDelete.setVisible(false);
        btnRefresh.setVisible(false);
    }

    public void buttonVisibility(int logedUser) {

        hideButtons();

        if (logedUser == 1) {
            btnDelete.setVisible(true);
            btnRefresh.setVisible(true);
        }
        if (logedUser == 2) {
            btnCart.setVisible(true);
        }
    }

    private void loadCarDatabase() throws ClassNotFoundException {

        String sql = "SELECT ID, BRAND, MODEL, HORSEPOWER, CC, YEAR, TRANSMISSION, TYPE, PRICE, RENTPRICE FROM CARS";

        try (Connection conn =db.creareLocalConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            DefaultTableModel model = (DefaultTableModel) carlistTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getInt("ID"),
                    rs.getString("BRAND"),
                    rs.getString("MODEL"),
                    rs.getInt("HORSEPOWER"),
                    rs.getInt("CC"),
                    rs.getInt("YEAR"),
                    rs.getString("TRANSMISSION"),
                    rs.getString("TYPE"),
                    rs.getInt("PRICE"),
                    rs.getInt("RENTPRICE")

                };
                model.addRow(row); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Carlist.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deleteSelectedRow() {

        int selectedRow = carlistTable.getSelectedRow(); 

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a line to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) carlistTable.getModel();

        int id = (int) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the ID record: " + id + "?", "Confirmation of Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        
        String sql = "DELETE FROM CARS WHERE ID = ?";

        try (Connection conn =db.creareLocalConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); 
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {
                model.removeRow(selectedRow); 
                JOptionPane.showMessageDialog(this, "The record was successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No record found for deletion.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Carlist.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error while deleting the record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addToCart() {

        int selectedRow = carlistTable.getSelectedRow(); 

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a car to add to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) carlistTable.getModel();

        String brand = (String) model.getValueAt(selectedRow, 1); 
        String modelName = (String) model.getValueAt(selectedRow, 2); 
        Integer price = (int) model.getValueAt(selectedRow, 8);
        Integer rentPrice = (int) model.getValueAt(selectedRow, 9);

        String sql = "INSERT INTO TEMP (BRAND, MODEL, PRICE, RENTPRICE) VALUES (?, ?, ?, ?)";

        try (Connection conn =db.creareLocalConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, brand);
            pstmt.setString(2, modelName);
            pstmt.setDouble(3, price);
            pstmt.setDouble(4, rentPrice);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "The car was successfully added to the cart.", "Succcess", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failure to add to cart.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Carlist.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error when adding to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void reloadTable() {
        DefaultTableModel model = (DefaultTableModel) carlistTable.getModel();
        carlistTable.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        carlistTable = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnCart = new javax.swing.JButton();

        carlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Brand", "Model", "Horsepower", "CC", "Year", "Transmission", "Type", "Price", "Rent Price / Day"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(carlistTable);

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(157, 159, 182));
        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnCart.setBackground(new java.awt.Color(0, 153, 0));
        btnCart.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCart.setForeground(new java.awt.Color(255, 255, 255));
        btnCart.setText("ADD TO CART");
        btnCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCart, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCart, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteSelectedRow();
        try {
            loadCarDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            // TODO add your handling code here:
            loadCarDatabase();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Carlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        // TODO add your handling code here:
        addToCart();

    }//GEN-LAST:event_btnCartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JTable carlistTable;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
