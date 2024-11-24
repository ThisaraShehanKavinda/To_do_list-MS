/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package to.pkgdo.listms.view;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class AcoountSettings extends javax.swing.JInternalFrame {
 String searchMail;
        String searchusername;
        String fname ;
        String email;
        String uname ;
        String password;
        String path;
    
    
    Connection con;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form AcoountSettings
     */
    public AcoountSettings() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        
        BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
        bi.setNorthPane(null);
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
    
     private void itemLoard1() {
    try {
        con = DBConnection.getConnection();
        String sql = "SELECT full_name,email,username,password,image FROM signup WHERE email = ?";
         pst = con.prepareStatement(sql);
        pst.setString(1, txtEmailsearch.getText());
         rs = pst.executeQuery();

        if (rs.next()) {
            
            txtfname.setText(rs.getString("full_name"));
            txtemail.setText(rs.getString("email"));
            txtuname.setText(rs.getString("username"));
            txtPassword.setText(rs.getString("password"));

            byte[] imageData = rs.getBytes("image");
            ImageIcon imageIcon = new ImageIcon(imageData);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(lblphoto.getWidth(), lblphoto.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            lblphoto.setImagenDefault(scaledImageIcon);
        } else {
            // Handle case when no item is found
            txtemail.setText("");
            txtPassword.setText("");
            txtfname.setText("");
            txtuname.setText("");
            String imagePath = "C:\\Users\\USER\\Documents\\NetBeansProjects\\Cafe Management System\\src\\Images\\icons8-contacts-125 (1).png";
                    ImageIcon defaultIcon = new ImageIcon(imagePath);
                    lblphoto.setImagenDefault(defaultIcon);
        }

        // Close the resources
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
    
    
    
 private void itemLoard() {
    try {
        con = DBConnection.getConnection();
        String sql = "SELECT full_name,email,username,password,image FROM signup WHERE username = ?";
         pst = con.prepareStatement(sql);
        pst.setString(1, txtusernamesearch.getText());
         rs = pst.executeQuery();

        if (rs.next()) {
            
            txtfname.setText(rs.getString("full_name"));
            txtemail.setText(rs.getString("email"));
            txtuname.setText(rs.getString("username"));
            txtPassword.setText(rs.getString("password"));

            byte[] imageData = rs.getBytes("image");
            ImageIcon imageIcon = new ImageIcon(imageData);
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(lblphoto.getWidth(), lblphoto.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            lblphoto.setImagenDefault(scaledImageIcon);
        } else {
            // Handle case when no item is found
            txtemail.setText("");
            txtPassword.setText("");
            txtfname.setText("");
            txtuname.setText("");
            String imagePath = "C:\\Users\\USER\\Documents\\NetBeansProjects\\Cafe Management System\\src\\Images\\icons8-contacts-125 (1).png";
                    ImageIcon defaultIcon = new ImageIcon(imagePath);
                    lblphoto.setImagenDefault(defaultIcon);
        }

        // Close the resources
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e);
    }
}
    
    
     private void getData(){
         
         
         
         searchMail = txtEmailsearch.getText();
         searchusername = txtusernamesearch.getText();
        fname = txtfname.getText();
        email = txtemail.getText();
        uname = txtuname.getText();
        password = txtPassword.getText();
   
    }
    
    
    
    

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
        txtusernamesearch = new javax.swing.JTextField();
        label23 = new java.awt.Label();
        txtEmailsearch = new javax.swing.JTextField();
        label24 = new java.awt.Label();
        lblphoto = new rojerusan.RSFotoCircle();
        jPanel2 = new javax.swing.JPanel();
        txtuname = new rojerusan.RSMetroTextPlaceHolder();
        txtemail = new rojerusan.RSMetroTextPlaceHolder();
        txtfname = new rojerusan.RSMetroTextPlaceHolder();
        rSMaterialButtonRectangle9 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle10 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle11 = new rojerusan.RSMaterialButtonRectangle();
        txtPassword = new rojerusan.RSMetroTextPlaceHolder();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(0, 0, 51));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label8.setBackground(new java.awt.Color(0, 0, 51));
        label8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label8.setForeground(new java.awt.Color(0, 255, 255));
        label8.setText("Account Settings");
        panel1.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, -1, -1));
        panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 70, 80));

        txtusernamesearch.setBackground(new java.awt.Color(102, 102, 102));
        txtusernamesearch.setForeground(new java.awt.Color(0, 255, 255));
        txtusernamesearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 1, true));
        txtusernamesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtusernamesearchKeyReleased(evt);
            }
        });
        panel1.add(txtusernamesearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 220, 30));

        label23.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label23.setForeground(new java.awt.Color(0, 255, 255));
        label23.setText("User Name  :");
        panel1.add(label23, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

        txtEmailsearch.setBackground(new java.awt.Color(102, 102, 102));
        txtEmailsearch.setForeground(new java.awt.Color(0, 255, 255));
        txtEmailsearch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 255, 255), 1, true));
        txtEmailsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailsearchKeyReleased(evt);
            }
        });
        panel1.add(txtEmailsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 220, 30));

        label24.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        label24.setForeground(new java.awt.Color(0, 255, 255));
        label24.setText("Email  :");
        panel1.add(label24, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, -1, 30));

        lblphoto.setBackground(new java.awt.Color(102, 102, 102));
        lblphoto.setColorBorde(new java.awt.Color(102, 102, 102));
        lblphoto.setColorBordePopu(new java.awt.Color(0, 255, 255));
        lblphoto.setColorButtonOptions(new java.awt.Color(0, 255, 255));
        lblphoto.setColorButtonOptionsHover(new java.awt.Color(0, 255, 255));
        lblphoto.setColorFondo(new java.awt.Color(0, 255, 255));
        lblphoto.setColorTextButtonsPopu(new java.awt.Color(102, 102, 102));
        panel1.add(lblphoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 110, 120));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1020, 210));

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtuname.setBackground(new java.awt.Color(102, 102, 102));
        txtuname.setForeground(new java.awt.Color(0, 255, 255));
        txtuname.setBorderColor(new java.awt.Color(0, 255, 255));
        txtuname.setBotonColor(new java.awt.Color(0, 255, 255));
        txtuname.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtuname.setPhColor(new java.awt.Color(0, 255, 255));
        txtuname.setPlaceholder("User Name");
        txtuname.setSelectionColor(new java.awt.Color(0, 255, 255));
        jPanel2.add(txtuname, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 340, 40));

        txtemail.setBackground(new java.awt.Color(102, 102, 102));
        txtemail.setForeground(new java.awt.Color(0, 255, 255));
        txtemail.setBorderColor(new java.awt.Color(0, 255, 255));
        txtemail.setBotonColor(new java.awt.Color(0, 255, 255));
        txtemail.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtemail.setPhColor(new java.awt.Color(0, 255, 255));
        txtemail.setPlaceholder("Email");
        txtemail.setSelectionColor(new java.awt.Color(0, 255, 255));
        jPanel2.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 340, 40));

        txtfname.setBackground(new java.awt.Color(102, 102, 102));
        txtfname.setForeground(new java.awt.Color(0, 255, 255));
        txtfname.setBorderColor(new java.awt.Color(0, 255, 255));
        txtfname.setBotonColor(new java.awt.Color(0, 255, 255));
        txtfname.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtfname.setPhColor(new java.awt.Color(0, 255, 255));
        txtfname.setPlaceholder("Full Name");
        txtfname.setSelectionColor(new java.awt.Color(0, 255, 255));
        jPanel2.add(txtfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 340, 40));

        rSMaterialButtonRectangle9.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle9.setForeground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonRectangle9.setText("Delete");
        rSMaterialButtonRectangle9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle9ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, 130, 40));

        rSMaterialButtonRectangle10.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle10.setForeground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonRectangle10.setText("Update");
        rSMaterialButtonRectangle10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle10ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle10, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, 130, 40));

        rSMaterialButtonRectangle11.setBackground(new java.awt.Color(102, 102, 102));
        rSMaterialButtonRectangle11.setForeground(new java.awt.Color(0, 255, 255));
        rSMaterialButtonRectangle11.setText("Clear");
        rSMaterialButtonRectangle11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle11ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle11, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 130, 40));

        txtPassword.setBackground(new java.awt.Color(102, 102, 102));
        txtPassword.setForeground(new java.awt.Color(0, 255, 255));
        txtPassword.setBorderColor(new java.awt.Color(0, 255, 255));
        txtPassword.setBotonColor(new java.awt.Color(0, 255, 255));
        txtPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        txtPassword.setPhColor(new java.awt.Color(0, 255, 255));
        txtPassword.setPlaceholder("Password");
        txtPassword.setSelectionColor(new java.awt.Color(0, 255, 255));
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 340, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 1020, 270));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1060, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtusernamesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusernamesearchKeyReleased
       itemLoard();
        txtEmailsearch.setText("");
    }//GEN-LAST:event_txtusernamesearchKeyReleased

    private void txtEmailsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailsearchKeyReleased
        itemLoard1();
        txtusernamesearch.setText("");
    }//GEN-LAST:event_txtEmailsearchKeyReleased

    private void rSMaterialButtonRectangle9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle9ActionPerformed
        playnotificationMusic();
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                String sql = "DELETE FROM signup WHERE username=? OR email=?";
                con = DBConnection.getConnection();
                pst = con.prepareStatement(sql);
                pst.setString(1, txtusernamesearch.getText());
                pst.setString(2, txtEmailsearch.getText());
                pst.executeUpdate();
                playnotificationMusic();
                JOptionPane.showMessageDialog(this, "<html><body><h2>Successfully Deleted!</h2>"
                    + "<p>The record has been deleted successfully.</p></body></html>", "Delete Successful", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e);
            }
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle9ActionPerformed

    private void rSMaterialButtonRectangle10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle10ActionPerformed
        playnotificationMusic();
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
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle10ActionPerformed

    private void rSMaterialButtonRectangle11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle11ActionPerformed

        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear the fields?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            txtEmailsearch.setText("");
            txtusernamesearch.setText("");
            txtemail.setText("");
            txtPassword.setText("");
            txtfname.setText("");
            txtuname.setText("");
            String imagePath = "C:\\Users\\USER\\Documents\\NetBeansProjects\\Cafe Management System\\src\\Images\\icons8-contacts-125 (1).png";
            ImageIcon defaultIcon = new ImageIcon(imagePath);
            lblphoto.setImagenDefault(defaultIcon);
            playnotificationMusic();
            JOptionPane.showMessageDialog(this, "<html><body><h2>Fields Cleared!</h2>"
                + "<p>All fields have been cleared successfully.</p></body></html>", "Clear Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle11ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private java.awt.Label label23;
    private java.awt.Label label24;
    private java.awt.Label label8;
    private rojerusan.RSFotoCircle lblphoto;
    private java.awt.Panel panel1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle10;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle11;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle9;
    private javax.swing.JTextField txtEmailsearch;
    private rojerusan.RSMetroTextPlaceHolder txtPassword;
    private rojerusan.RSMetroTextPlaceHolder txtemail;
    private rojerusan.RSMetroTextPlaceHolder txtfname;
    private rojerusan.RSMetroTextPlaceHolder txtuname;
    private javax.swing.JTextField txtusernamesearch;
    // End of variables declaration//GEN-END:variables
}
