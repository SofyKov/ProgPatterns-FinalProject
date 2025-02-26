/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.prog2.labs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class logInLibrarian extends javax.swing.JFrame {

    ResourceBundle messages;
    LibraryController controller = LibraryController.getInstance();

    /**
     * Creates new form logInLibrarian
     */
    public logInLibrarian() {
        initComponents();
        messages = ResourceBundle.getBundle("messages", Locale.getDefault());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane2 = new javax.swing.JOptionPane();
        jLabel1 = new javax.swing.JLabel();
        libID_lbl = new javax.swing.JLabel();
        libPsswd_lbl = new javax.swing.JLabel();
        libIdTextBox = new javax.swing.JTextField();
        libPasswordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        langToggleButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Librarian - Log In");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setText("Log In");

        libID_lbl.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        libID_lbl.setText("Librarian ID:");

        libPsswd_lbl.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        libPsswd_lbl.setText("Password:");

        submitButton.setBackground(java.awt.Color.cyan);
        submitButton.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                submitButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                submitButtonMouseExited(evt);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        back_btn.setText("Back");
        back_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                back_btnMousePressed(evt);
            }
        });
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(204, 0, 0));
        errorLabel.setText("Invalid ID or password.");
        errorLabel.setVisible(false);

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seePsswd.png"))); // NOI18N
        jToggleButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/hidePsswd.png"))); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        langToggleButton.setText("FR");
        langToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                langToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(libPsswd_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(libPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(libID_lbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(libIdTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel1)))
                .addGap(0, 5, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back_btn)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(submitButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(langToggleButton)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(libID_lbl)
                    .addComponent(libIdTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(libPsswd_lbl)
                    .addComponent(libPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(back_btn)
                            .addComponent(langToggleButton))
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseEntered
        // TODO add your handling code here:
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.white);

    }//GEN-LAST:event_submitButtonMouseEntered

    private void submitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseExited
        // TODO add your handling code here:
        submitButton.setBackground(Color.cyan);
        submitButton.setForeground(Color.BLACK);
    }//GEN-LAST:event_submitButtonMouseExited

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

        //get id and password input
        String id = libIdTextBox.getText();
        String password = new String(libPasswordField.getPassword());

        //verify login 
        boolean loginVerified = controller.verifyLogin(id, password);

        //if verified, show LibraryView
        if (loginVerified) {

            //make librarian object through controller
            Librarian librarian = (Librarian) controller.createEntity("Librarian", id);
            controller.setLibrarian(librarian);

            System.out.println("LogIn library " + controller.getLibrarian());

            viewLibrarian libView = new viewLibrarian();
            libView.show();

            this.hide();

        } else {
            //show error message
            errorLabel.setVisible(true);

            //Hide  label after 3 seconds
            Timer timer = new Timer(3000, e -> errorLabel.setVisible(false));
            timer.setRepeats(false);
            timer.start();

        }

    }//GEN-LAST:event_submitButtonActionPerformed

    private void back_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back_btnMousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_back_btnMousePressed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        // TODO add your handling code here:
        LibraryGUIView lGUIv = new LibraryGUIView();
        lGUIv.show();
        this.dispose();
    }//GEN-LAST:event_back_btnActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        if (jToggleButton2.isSelected()) {
            libPasswordField.setEchoChar((char) 0);
        } else {
            libPasswordField.setEchoChar('*');
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void langToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_langToggleButtonActionPerformed
        // TODO add your handling code here:

        if (langToggleButton.isSelected()) {
            //french
            messages = ResourceBundle.getBundle("messages", Locale.FRENCH);
            updateTexts();

        } else {
            //english
            messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);
            updateTexts();
        }
    }//GEN-LAST:event_langToggleButtonActionPerformed

    public void showDialog(String title, String message) {
        // Create a new JFrame to act as the small screen
        JFrame dialogFrame = new JFrame(title);
        dialogFrame.setSize(250, 100);
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setLocationRelativeTo(this); // Position relative to the main JFrame

        // Create content for the dialog
        JLabel label = new JLabel(message);
        dialogFrame.add(label, BorderLayout.CENTER);

        JButton closeButton = new JButton("Ok");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogFrame.dispose(); // Close the dialog frame
            }
        });
        dialogFrame.add(closeButton, BorderLayout.SOUTH);

        dialogFrame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(logInLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(logInLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(logInLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(logInLibrarian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new logInLibrarian().setVisible(true);
            }
        });
    }

    private void updateTexts() {
        jLabel1.setText(messages.getString("jLabel1"));
        libID_lbl.setText(messages.getString("libID_lbl"));
        libPsswd_lbl.setText(messages.getString("libPsswd_lbl"));
        submitButton.setText(messages.getString("submitButton"));
        errorLabel.setText(messages.getString("errorLabel"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_btn;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JOptionPane jOptionPane2;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton langToggleButton;
    private javax.swing.JLabel libID_lbl;
    private javax.swing.JTextField libIdTextBox;
    private javax.swing.JPasswordField libPasswordField;
    private javax.swing.JLabel libPsswd_lbl;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
