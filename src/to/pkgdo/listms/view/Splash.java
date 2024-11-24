/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package to.pkgdo.listms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Timer;
import javax.swing.UIManager;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

/**
 *
 * @author USER
 */
public class Splash extends javax.swing.JFrame {

    /**
     * Creates new form Splash
     */
    public Splash() {
        initComponents();
        setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),40,40));
        
        
        
        Timer timer = new Timer(1000, new ActionListener() {
    boolean isVisible = true;

    @Override
    public void actionPerformed(ActionEvent e) {
        // Toggle the visibility of the label
        //lblshehanz.setVisible(isVisible);
        isVisible = !isVisible;
    }
});

// Start the Timer
timer.start();
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblLoading = new javax.swing.JLabel();
        lblLoadingvalue = new javax.swing.JLabel();
        circleProgressBar1 = new to.pkgdo.listms.database.CircleProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Blue Minimal Lion Technology Free Logo.gif"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 490, 390));

        jLabel3.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MANAGER");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Franklin Gothic Demi", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TASK");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, -1, -1));

        lblLoading.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblLoading.setForeground(new java.awt.Color(0, 255, 255));
        lblLoading.setText("Loading....");
        jPanel2.add(lblLoading, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 210, -1));

        lblLoadingvalue.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        lblLoadingvalue.setForeground(new java.awt.Color(0, 255, 255));
        lblLoadingvalue.setText("0%");
        jPanel2.add(lblLoadingvalue, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 400, -1, -1));

        circleProgressBar1.setForeground(new java.awt.Color(0, 255, 255));
        circleProgressBar1.setValue(50);
        jPanel2.add(circleProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 240, 230));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 960, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 470));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

            
       Splash sp = new Splash();
       sp.setVisible(true);
       Login l = new  Login();
               
       
       try{
           
           for(int i = 0; i<=100;i++){
               Thread.sleep(100);
               sp.lblLoadingvalue.setText(i+"%");
               
               if(i==10){
                   sp.lblLoading.setText("Turning on Modules...");
        /*lbl1.setVisible(false);
        lbl2.setVisible(false);
        lbl3.setVisible(false);
        lbl4.setVisible(false);*/
               }
               if(i==20){
                   sp.lblLoading.setText("Loading on Modules...");
                   
               }
               if(i==50){
                   sp.lblLoading.setText("Connecting to Database...");
                   
               }
               if(i==70){
                   sp.lblLoading.setText("Connection Successful !");
                   
               }
               if(i==80){
                   sp.lblLoading.setText("Launching Application...");
                   
               }
             
               sp.circleProgressBar1.setValue(i);
               
           }
           
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
       
       sp.setVisible(false);
       l.setVisible(true);
       
       sp.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private to.pkgdo.listms.database.CircleProgressBar circleProgressBar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblLoading;
    private javax.swing.JLabel lblLoadingvalue;
    // End of variables declaration//GEN-END:variables
}