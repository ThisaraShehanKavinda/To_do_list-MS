/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package to.pkgdo.listms.view;

import com.toedter.calendar.JDayChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
//import javax.swing.JPanel;
import to.pkgdo.listms.database.CustomCalendar;
import to.pkgdo.listms.database.DBConnection;
/**
 *
 * @author USER
 */


public class Dashboard extends javax.swing.JInternalFrame {
  private CustomCalendar customCalendar; 
  //private JPanel panelCalender;

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        BasicInternalFrameUI bi = (BasicInternalFrameUI) this.getUI();
        bi.setNorthPane(null);


        tableLoard();
        displayIncompleteCount();
        displaycompleteCount();
       // displayTodayIncompleteTasks();
        tableLoadTodayDueTasks();
       // showDueTasksMessage();
        
       // myPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Example layout

       
    }
    
    

    
    
    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
   private void displaycompleteCount() {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        con = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM task WHERE currentStates = 'Complete'";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        int completeCount = 0;
        if (rs.next()) {
            completeCount = rs.getInt(1);
        }
        
       lblcompleteCount.setText(Integer.toString(completeCount));

    } catch (SQLException ex) {
        ex.printStackTrace();
    } 
}
   
   
   
   
  private void displayTodayIncompleteTasks() {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        con = DBConnection.getConnection();
        String sql = "SELECT * FROM task WHERE currentStates = 'Incomplete' AND dueDate = ?";
        pst = con.prepareStatement(sql);
        pst.setDate(1, new java.sql.Date(System.currentTimeMillis())); // Today's date
        rs = pst.executeQuery();

        // Clear existing components from the panel
        myPanel.removeAll();

        int yOffset = 10; // Initial y-offset for positioning labels
        int labelHeight = 30; // Height of each label
        int panelWidth = 450; // Width of the panel

        while (rs.next()) {
            // Create and configure UI components for each task
            JLabel taskLabel = new JLabel("Task: " + rs.getString("title"));
            taskLabel.setBounds(10, yOffset, panelWidth - 20, labelHeight);

            JLabel dueDateLabel = new JLabel("Due Date: " + rs.getDate("dueDate"));
            dueDateLabel.setBounds(10, yOffset + labelHeight, panelWidth - 20, labelHeight);

            // Add the labels to the panel
            myPanel.add(taskLabel);
            myPanel.add(dueDateLabel);

            // Increment y-offset for the next set of labels
            yOffset += labelHeight * 2 + 10; // Adding 10 for spacing
        }

        // Repaint the panel to reflect the changes
        myPanel.revalidate();
        myPanel.repaint();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}



    
    
    
    
    private void displayIncompleteCount() {
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
        con = DBConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM task WHERE currentStates = 'Incomplete'";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        int incompleteCount = 0;
        if (rs.next()) {
            incompleteCount = rs.getInt(1);
        }
        
        lblIncompleteCount.setText(Integer.toString(incompleteCount));

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
    
    private void tableLoadTodayDueTasks() {
    try {
        String sql = "SELECT id, title, description, currentStates FROM task WHERE dueDate = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setDate(1, new java.sql.Date(System.currentTimeMillis())); // Today's date
        ResultSet rs = pst.executeQuery();
        DefaultTableModel tableModel = resultSetToTableModel(rs);
        tbltoday.setModel(tableModel);

        // Close the resources
        rs.close();
        pst.close();

    } catch (SQLException e) {
        playnotificationMusic();
        JOptionPane.showMessageDialog(this, "Error occurred while loading data: " + e.getMessage());
    }
}

    private void tableLoard(){
       
        try{
        String sql = "SELECT id, title, description, currentStates FROM task";
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




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        label12 = new java.awt.Label();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbltoday = new rojerusan.RSTableMetro();
        panelCalender1 = new javax.swing.JPanel();
        dateChooserPanel1 = new org.jfree.ui.DateChooserPanel();
        panel1 = new java.awt.Panel();
        jPanel5 = new javax.swing.JPanel();
        label9 = new java.awt.Label();
        lblcompleteCount = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        label8 = new java.awt.Label();
        lblIncompleteCount = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        myPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new rojerusan.RSTableMetro();

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label12.setBackground(new java.awt.Color(0, 0, 51));
        label12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label12.setForeground(new java.awt.Color(0, 255, 255));
        label12.setText("Due Date is TODAY !!!");
        jPanel2.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fire.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 15, -1, -1));

        tbltoday.setBackground(new java.awt.Color(0, 0, 51));
        tbltoday.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 1, true));
        tbltoday.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Description", "Start Date", "Due Date", "Status"
            }
        ));
        tbltoday.setColorBackgoundHead(new java.awt.Color(0, 0, 102));
        tbltoday.setColorFilasBackgound1(new java.awt.Color(0, 0, 0));
        tbltoday.setColorFilasBackgound2(new java.awt.Color(51, 51, 51));
        tbltoday.setColorFilasForeground1(new java.awt.Color(0, 255, 255));
        tbltoday.setColorFilasForeground2(new java.awt.Color(0, 255, 255));
        tbltoday.setColorForegroundHead(new java.awt.Color(0, 255, 255));
        tbltoday.setColorSelBackgound(new java.awt.Color(0, 51, 102));
        tbltoday.setColorSelForeground(new java.awt.Color(0, 255, 255));
        tbltoday.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbltoday.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbltoday.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbltoday.setRowHeight(15);
        tbltoday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbltodayMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbltoday);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 400, 90));

        panelCalender1.setBackground(new java.awt.Color(0, 0, 51));

        dateChooserPanel1.setBackground(new java.awt.Color(0, 0, 102));
        dateChooserPanel1.setForeground(new java.awt.Color(0, 255, 255));
        dateChooserPanel1.setChosenOtherButtonColor(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout panelCalender1Layout = new javax.swing.GroupLayout(panelCalender1);
        panelCalender1.setLayout(panelCalender1Layout);
        panelCalender1Layout.setHorizontalGroup(
            panelCalender1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCalender1Layout.createSequentialGroup()
                .addComponent(dateChooserPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        panelCalender1Layout.setVerticalGroup(
            panelCalender1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dateChooserPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel1.setBackground(new java.awt.Color(0, 0, 51));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 0, 0, new java.awt.Color(0, 255, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label9.setBackground(new java.awt.Color(0, 0, 102));
        label9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label9.setForeground(new java.awt.Color(0, 255, 255));
        label9.setText("No Of Task Done:");
        jPanel5.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 16, 250, -1));

        lblcompleteCount.setBackground(new java.awt.Color(0, 0, 102));
        lblcompleteCount.setFont(new java.awt.Font("Elephant", 1, 36)); // NOI18N
        lblcompleteCount.setForeground(new java.awt.Color(0, 255, 255));
        lblcompleteCount.setText("10");
        jPanel5.add(lblcompleteCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/task-list (1).png"))); // NOI18N
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 93, 60));

        panel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 270, 170));

        jPanel6.setBackground(new java.awt.Color(0, 0, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(6, 0, 0, 0, new java.awt.Color(0, 255, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label8.setBackground(new java.awt.Color(0, 0, 102));
        label8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(0, 255, 255));
        label8.setText("No Of Task Remain:");
        jPanel6.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblIncompleteCount.setBackground(new java.awt.Color(0, 0, 102));
        lblIncompleteCount.setFont(new java.awt.Font("Elephant", 1, 36)); // NOI18N
        lblIncompleteCount.setForeground(new java.awt.Color(0, 255, 255));
        lblIncompleteCount.setText("10");
        jPanel6.add(lblIncompleteCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/task-list (1).png"))); // NOI18N
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 93, 93));

        panel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 170));

        myPanel.setBackground(new java.awt.Color(0, 0, 51));
        myPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblItem.setBackground(new java.awt.Color(0, 0, 51));
        tblItem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 1, true));
        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Discription", "Status"
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

        myPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 410, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelCalender1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(myPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelCalender1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseClicked
       // clearSelectedRow();
    }//GEN-LAST:event_tblItemMouseClicked

    private void tbltodayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltodayMouseClicked
        

    }//GEN-LAST:event_tbltodayMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jfree.ui.DateChooserPanel dateChooserPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label12;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.Label lblIncompleteCount;
    private java.awt.Label lblcompleteCount;
    private javax.swing.JPanel myPanel;
    private java.awt.Panel panel1;
    private javax.swing.JPanel panelCalender1;
    private rojerusan.RSTableMetro tblItem;
    private rojerusan.RSTableMetro tbltoday;
    // End of variables declaration//GEN-END:variables
}
