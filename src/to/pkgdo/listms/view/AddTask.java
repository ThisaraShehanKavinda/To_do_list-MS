/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package to.pkgdo.listms.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import to.pkgdo.listms.database.DBConnection;


/**
 *
 * @author USER
 */
public class AddTask extends javax.swing.JInternalFrame {


    /**
     * Creates new form AddTask
     */
    
    
    
    int id;
    String title;
    String description;
    String startDate;
    String dueDate;
    String status;
    
    public AddTask() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
        
      autoID();
        
        
    }
    private void autoID() {
    try {
        String sql = "SELECT id FROM task ORDER BY id DESC LIMIT 1";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
         ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            String rnno = rs.getString("id");
            int oo = rnno.length();
            String txt = rnno.substring(0, 3);
            String num = rnno.substring(3, oo);
            int n = Integer.parseInt(num);
            n++;
            String snum = Integer.toString(n);
            String ftxt = txt + snum;
            txtID.setText(ftxt);
        } else {
            txtID.setText("1000");
        }
        
        rs.close();
        pst.close();
        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(rootPane, ex.getMessage());
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

private void getData() {
    try {
        id = Integer.parseInt(txtID.getText());
    } catch (NumberFormatException e) {
        // Handle parsing error for id
        e.printStackTrace(); // You might want to log or display an error message
    }
    
    title = txtTitle.getText();
    description = txtDescription.getText(); // Assuming you have a JTextfield called txtDescription

    // Assuming you have two JDateChoosers named startDateChooser and dueDateChooser
    Date startDateValue = dateChooserstartDate.getDate();
    Date dueDateValue = dateChooserDueDate.getDate();
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define your desired date format
    startDate = dateFormat.format(startDateValue);
    dueDate = dateFormat.format(dueDateValue);
}


   
   

    // ... existing code ...


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        label8 = new java.awt.Label();
        jLabel4 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        label23 = new java.awt.Label();
        label24 = new java.awt.Label();
        txtTitle = new rojerusan.RSMetroTextPlaceHolder();
        btnAdd = new rojerusan.RSMaterialButtonRectangle();
        btnClear = new rojerusan.RSMaterialButtonRectangle();
        label25 = new java.awt.Label();
        label26 = new java.awt.Label();
        label27 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        dateChooserDueDate = new com.toedter.calendar.JDateChooser();
        dateChooserstartDate = new com.toedter.calendar.JDateChooser();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, -1, 536));

        panel1.setBackground(new java.awt.Color(0, 0, 51));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label8.setBackground(new java.awt.Color(0, 0, 51));
        label8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(0, 255, 255));
        label8.setText("Add Task");
        panel1.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clipboard (1).png"))); // NOI18N
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 60, 60));

        txtID.setBackground(new java.awt.Color(102, 102, 102));
        txtID.setForeground(new java.awt.Color(0, 255, 255));
        txtID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 1, true));
        txtID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDKeyReleased(evt);
            }
        });
        panel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 120, 30));

        label23.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label23.setForeground(new java.awt.Color(0, 255, 255));
        label23.setText("ID  :");
        panel1.add(label23, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, -1, 30));

        label24.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label24.setForeground(new java.awt.Color(0, 255, 255));
        label24.setText("Due Date :");
        panel1.add(label24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, 30));
        label24.getAccessibleContext().setAccessibleName("Title :");

        txtTitle.setBackground(new java.awt.Color(102, 102, 102));
        txtTitle.setForeground(new java.awt.Color(0, 255, 255));
        txtTitle.setBorderColor(new java.awt.Color(0, 255, 255));
        txtTitle.setBotonColor(new java.awt.Color(0, 255, 255));
        txtTitle.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtTitle.setPhColor(new java.awt.Color(0, 255, 255));
        txtTitle.setPlaceholder("Tittle");
        panel1.add(txtTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 550, 40));

        btnAdd.setBackground(new java.awt.Color(102, 102, 102));
        btnAdd.setForeground(new java.awt.Color(0, 255, 255));
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        panel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 130, 40));

        btnClear.setBackground(new java.awt.Color(102, 102, 102));
        btnClear.setForeground(new java.awt.Color(0, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        panel1.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 450, 130, 40));

        label25.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label25.setForeground(new java.awt.Color(0, 255, 255));
        label25.setText("Title  :");
        panel1.add(label25, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, 30));

        label26.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label26.setForeground(new java.awt.Color(0, 255, 255));
        label26.setText("Description :");
        panel1.add(label26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, 30));

        label27.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label27.setForeground(new java.awt.Color(0, 255, 255));
        label27.setText("Start Date :");
        panel1.add(label27, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, 30));

        jScrollPane1.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 255)));
        jScrollPane1.setForeground(new java.awt.Color(0, 255, 255));

        txtDescription.setBackground(new java.awt.Color(102, 102, 102));
        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        panel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 550, 110));

        dateChooserDueDate.setBackground(new java.awt.Color(153, 153, 153));
        dateChooserDueDate.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 255)));
        dateChooserDueDate.setForeground(new java.awt.Color(0, 255, 255));
        panel1.add(dateChooserDueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 550, 40));

        dateChooserstartDate.setBackground(new java.awt.Color(153, 153, 153));
        dateChooserstartDate.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 255)));
        dateChooserstartDate.setForeground(new java.awt.Color(0, 255, 255));
        panel1.add(dateChooserstartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 550, 40));

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed

        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the fields?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            txtDescription.setText("");
            txtID.setText("");
            txtTitle.setText("");

            playnotificationMusic();
            JOptionPane.showMessageDialog(this, "<html><body><h2>Fields Cleared!</h2>"
                + "<p>All fields have been cleared successfully.</p></body></html>", "Clear Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        getData();

        try {
            // Validate fields
            if (txtID.getText().isEmpty() || txtTitle.getText().isEmpty() || txtDescription.getText().isEmpty()) {
                playnotificationMusic();
                JOptionPane.showMessageDialog(this, "<html><body><h2>Error!</h2>"
                    + "<p>Please fill in all required fields.</p></body></html>", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtID.getText());
            String title = txtTitle.getText();
            String description = txtDescription.getText();

            // Perform the database insertion
            String sql = "INSERT INTO task (id, title, description, startDate, dueDate, currentStates) VALUES (?, ?, ?, ?, ?, ?)";
            Connection con = null;
            PreparedStatement pst = null;

            try {
                con = DBConnection.getConnection();
                pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                pst.setString(2, title);
                pst.setString(3, description);
                pst.setString(4, startDate); // Assuming startDate is already populated
                pst.setString(5, dueDate);   // Assuming dueDate is already populated
                pst.setString(6, "Incomplete");

                pst.executeUpdate();
                playnotificationMusic();

                JOptionPane.showMessageDialog(this, "<html><body><h2>Successfully Added!</h2>"
                    + "<p>The Task has been added successfully.</p></body></html>", "Success", JOptionPane.INFORMATION_MESSAGE);

                txtDescription.setText("");
                txtID.setText("");
                txtTitle.setText("");
                autoID();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } finally {
                try {
                    if (pst != null) pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (NumberFormatException e) {
            playnotificationMusic();
            JOptionPane.showMessageDialog(this, "<html><body><h2>Error!</h2>"
                + "<p>Please enter valid numeric values for ID.</p></body></html>", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_btnAddActionPerformed

    private void txtIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDKeyReleased
        /* itemLoard();
        txtEmailsearch.setText("");*/
    }//GEN-LAST:event_txtIDKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnAdd;
    private rojerusan.RSMaterialButtonRectangle btnClear;
    private com.toedter.calendar.JDateChooser dateChooserDueDate;
    private com.toedter.calendar.JDateChooser dateChooserstartDate;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label23;
    private java.awt.Label label24;
    private java.awt.Label label25;
    private java.awt.Label label26;
    private java.awt.Label label27;
    private java.awt.Label label8;
    private java.awt.Panel panel1;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtID;
    private rojerusan.RSMetroTextPlaceHolder txtTitle;
    // End of variables declaration//GEN-END:variables
}