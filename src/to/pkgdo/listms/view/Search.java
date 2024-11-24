/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package to.pkgdo.listms.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import to.pkgdo.listms.database.DBConnection;

/**
 *
 * @author USER
 */
public class Search extends javax.swing.JInternalFrame {
private String selectedCriteria = "ID"; // Default sorting criteria
private String selectedOrder = "ASC";   // Default sorting order
    /**
     * Creates new form AddTask
     */
    public Search() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
        tableLoard();
        
    }
    
    private void loadTableData(String filter) {
    DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
    model.setRowCount(0);

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        con = DBConnection.getConnection();
        String sql = "SELECT * FROM task";

        if (filter.equals("Completed")) {
            sql += " WHERE currentStates = 'Complete'";
        } else if (filter.equals("Incomplete")) {
            sql += " WHERE currentStates = 'Incomplete'";
        }

        if (radioAscending.isSelected()) {
            sql += " ORDER BY " + selectedCriteria + " ASC";
        } else if (radioDescending.isSelected()) {
            sql += " ORDER BY " + selectedCriteria + " DESC";
        }
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            // Populate data into the table model
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("startDate"),
                rs.getDate("dueDate"),
                rs.getString("currentStates")
            });
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    } 
    }

    
    private void playnotificationMusic() {
    try {
        // Load the audio file
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Music/Outlook notification sound.wav"));

        // Create a clip to play the audio
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        // Play the audio once
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
    
    
private void loadTableDataWithSearch(String searchTerm) {
    DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
    model.setRowCount(0);

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        con = DBConnection.getConnection();
        String sql = "SELECT * FROM task WHERE title LIKE ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, "%" + searchTerm + "%"); // Search with a wildcard for partial matches
        rs = pst.executeQuery();

        while (rs.next()) {
            // Populate data into the table model
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("startDate"),
                rs.getDate("dueDate"),
                rs.getString("currentStates")
            });
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    
    }
}
    
    private DefaultTableModel resultSetToTableModel(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();

    // Get column names
    int columnCount = metaData.getColumnCount();
    String[] columnNames = new String[columnCount];
    for (int i = 1; i <= columnCount; i++) {
        columnNames[i - 1] = metaData.getColumnName(i);
    }

    // Get row data
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    while (rs.next()) {
        Object[] rowData = new Object[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            rowData[i - 1] = rs.getObject(i);
        }
        tableModel.addRow(rowData);
    }

    return tableModel;
}
    
    
    
    private void loadTableDataByDateRange() {
    DefaultTableModel model = (DefaultTableModel) tblItem.getModel();
    model.setRowCount(0);

    Date fromDate = dateChooserFrom.getDate();
    Date toDate = dateChooserTo.getDate();

    if (fromDate == null || toDate == null) {
        return; // Wait for both dates to be selected
    }

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        con = DBConnection.getConnection();
        String sql = "SELECT * FROM task WHERE startDate >= ? AND dueDate <= ?";
        pst = con.prepareStatement(sql);
        pst.setDate(1, new java.sql.Date(fromDate.getTime()));
        pst.setDate(2, new java.sql.Date(toDate.getTime()));
        rs = pst.executeQuery();

        while (rs.next()) {
            // Populate data into the table model
            model.addRow(new Object[]{
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("startDate"),
                rs.getDate("dueDate"),
                rs.getString("currentStates")
            });
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    
    }
}
    
    
    
    
     private void tableLoard(){
       
        try{
        String sql = "SELECT id, title, description, startDate, dueDate, currentStates FROM task";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        DefaultTableModel tableModel = resultSetToTableModel(rs);
        tblItem.setModel(tableModel);
        
        // Close the resources
        rs.close();
        pst.close();
       
    } catch (SQLException e) {
        playnotificationMusic();
        JOptionPane.showMessageDialog(this, "Error occurred while loading data: " + e.getMessage());
    }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        label8 = new java.awt.Label();
        jLabel4 = new javax.swing.JLabel();
        rSMaterialButtonRectangle10 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle11 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new rojerusan.RSTableMetro();
        label25 = new java.awt.Label();
        radioDescending = new javax.swing.JRadioButton();
        radioAscending = new javax.swing.JRadioButton();
        cmbitem = new javax.swing.JComboBox<>();
        label26 = new java.awt.Label();
        label27 = new java.awt.Label();
        txtPassword1 = new rojerusan.RSMetroTextPlaceHolder();
        dateChooserFrom = new com.toedter.calendar.JDateChooser();
        dateChooserTo = new com.toedter.calendar.JDateChooser();
        label28 = new java.awt.Label();
        label29 = new java.awt.Label();

        panel1.setBackground(new java.awt.Color(0, 0, 51));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label8.setBackground(new java.awt.Color(0, 0, 51));
        label8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(0, 255, 255));
        label8.setText("Search Task");
        panel1.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/compliance.png"))); // NOI18N
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 80, 60));

        rSMaterialButtonRectangle10.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle10.setForeground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonRectangle10.setText("Search");
        rSMaterialButtonRectangle10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle10ActionPerformed(evt);
            }
        });
        panel1.add(rSMaterialButtonRectangle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, 130, 40));

        rSMaterialButtonRectangle11.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle11.setForeground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonRectangle11.setText("Clear");
        rSMaterialButtonRectangle11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle11ActionPerformed(evt);
            }
        });
        panel1.add(rSMaterialButtonRectangle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 130, 40));

        tblItem.setBackground(new java.awt.Color(0, 0, 51));
        tblItem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 1, true));
        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Discription", "Start Date", "Due Date", "Status"
            }
        ));
        tblItem.setColorBackgoundHead(new java.awt.Color(0, 0, 102));
        tblItem.setColorFilasBackgound1(new java.awt.Color(0, 0, 0));
        tblItem.setColorFilasBackgound2(new java.awt.Color(51, 51, 51));
        tblItem.setColorFilasForeground1(new java.awt.Color(0, 255, 255));
        tblItem.setColorFilasForeground2(new java.awt.Color(0, 255, 255));
        tblItem.setColorForegroundHead(new java.awt.Color(0, 255, 255));
        tblItem.setColorSelBackgound(new java.awt.Color(0, 51, 102));
        tblItem.setColorSelForeground(new java.awt.Color(0, 255, 255));
        tblItem.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblItem.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblItem.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tblItem.setRowHeight(15);
        tblItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblItemMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblItem);

        panel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 760, 180));

        label25.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label25.setForeground(new java.awt.Color(0, 255, 255));
        label25.setText("Search By Title");
        panel1.add(label25, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, -1, 30));

        radioDescending.setForeground(new java.awt.Color(0, 255, 255));
        radioDescending.setText("Decending");
        radioDescending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDescendingActionPerformed(evt);
            }
        });
        panel1.add(radioDescending, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 210, -1, -1));

        radioAscending.setForeground(new java.awt.Color(0, 255, 255));
        radioAscending.setText("Ascending");
        radioAscending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAscendingActionPerformed(evt);
            }
        });
        panel1.add(radioAscending, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 180, -1, -1));

        cmbitem.setBackground(new java.awt.Color(0, 0, 102));
        cmbitem.setForeground(new java.awt.Color(0, 255, 255));
        cmbitem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Complete", "Incomplete " }));
        cmbitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbitemActionPerformed(evt);
            }
        });
        panel1.add(cmbitem, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 180, 30));

        label26.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label26.setForeground(new java.awt.Color(0, 255, 255));
        label26.setText("Sort");
        panel1.add(label26, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, -1, 30));

        label27.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label27.setForeground(new java.awt.Color(0, 255, 255));
        label27.setText("To");
        panel1.add(label27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, -1, 30));

        txtPassword1.setBackground(new java.awt.Color(102, 102, 102));
        txtPassword1.setForeground(new java.awt.Color(0, 255, 255));
        txtPassword1.setBorderColor(new java.awt.Color(0, 255, 255));
        txtPassword1.setBotonColor(new java.awt.Color(0, 255, 255));
        txtPassword1.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtPassword1.setPhColor(new java.awt.Color(0, 255, 255));
        txtPassword1.setPlaceholder("Tittle");
        txtPassword1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassword1KeyReleased(evt);
            }
        });
        panel1.add(txtPassword1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 410, 30));

        dateChooserFrom.setBackground(new java.awt.Color(153, 153, 153));
        dateChooserFrom.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 255)));
        dateChooserFrom.setForeground(new java.awt.Color(0, 255, 255));
        dateChooserFrom.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooserFromPropertyChange(evt);
            }
        });
        panel1.add(dateChooserFrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 290, 40));

        dateChooserTo.setBackground(new java.awt.Color(153, 153, 153));
        dateChooserTo.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 255)));
        dateChooserTo.setForeground(new java.awt.Color(0, 255, 255));
        dateChooserTo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooserToPropertyChange(evt);
            }
        });
        panel1.add(dateChooserTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 290, 40));

        label28.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label28.setForeground(new java.awt.Color(0, 255, 255));
        label28.setText("Search By Date");
        panel1.add(label28, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, -1, 30));

        label29.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label29.setForeground(new java.awt.Color(0, 255, 255));
        label29.setText("From");
        panel1.add(label29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonRectangle10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle10ActionPerformed
        /*  playnotificationMusic();
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to update the user information?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                getData();
                String sql = "update signup set full_name=?,email=?,username=?,password=? where username='"+searchusername+"' or email='"+searchMail+"'";
                con = DBConnection.getConnection();
                pst = con.prepareStatement(sql);

                pst.setString(1, fname);
                pst.setString(2, email);
                pst.setString(3, uname);
                pst.setString(4, password);

                pst.execute();
                playnotificationMusic();
                JOptionPane.showMessageDialog(this, "<html><body><h2>Successfully Updated!</h2>"
                    + "<p>The user information has been updated successfully.</p></body></html>", "Update Successful", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }*/
    }//GEN-LAST:event_rSMaterialButtonRectangle10ActionPerformed

    private void rSMaterialButtonRectangle11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle11ActionPerformed

         int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the fields?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            txtPassword1.setText("");
           tableLoard();
            
            playnotificationMusic();
            JOptionPane.showMessageDialog(this, "<html><body><h2>Fields Cleared!</h2>"
                + "<p>All fields have been cleared successfully.</p></body></html>", "Clear Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle11ActionPerformed

    private void tblItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseClicked
        // clearSelectedRow();
    }//GEN-LAST:event_tblItemMouseClicked

    private void radioDescendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDescendingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioDescendingActionPerformed

    private void radioAscendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAscendingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioAscendingActionPerformed

    private void cmbitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbitemActionPerformed
       String selectedFilter = cmbitem.getSelectedItem().toString();
    // Set the appropriate column for sorting based on the selected filter
    if (selectedFilter.equals("All")) {
        selectedCriteria = "id"; // Default to ID
    } else {
        selectedCriteria = "currentStates"; // Sort by status for Completed or Incomplete
    }
    loadTableData(selectedFilter);
    }//GEN-LAST:event_cmbitemActionPerformed

    private void txtPassword1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassword1KeyReleased
 String searchTerm = txtPassword1.getText();
    loadTableDataWithSearch(searchTerm);        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassword1KeyReleased

    private void dateChooserFromPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserFromPropertyChange
         if ("date".equals(evt.getPropertyName())) {
        loadTableDataByDateRange();
    }
    }//GEN-LAST:event_dateChooserFromPropertyChange

    private void dateChooserToPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserToPropertyChange
 if ("date".equals(evt.getPropertyName())) {
        loadTableDataByDateRange();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_dateChooserToPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbitem;
    private com.toedter.calendar.JDateChooser dateChooserFrom;
    private com.toedter.calendar.JDateChooser dateChooserTo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label label25;
    private java.awt.Label label26;
    private java.awt.Label label27;
    private java.awt.Label label28;
    private java.awt.Label label29;
    private java.awt.Label label8;
    private java.awt.Panel panel1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle10;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle11;
    private javax.swing.JRadioButton radioAscending;
    private javax.swing.JRadioButton radioDescending;
    private rojerusan.RSTableMetro tblItem;
    private rojerusan.RSMetroTextPlaceHolder txtPassword1;
    // End of variables declaration//GEN-END:variables
}
