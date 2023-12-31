/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.form;

import domain.Nalog;

/**
 * Forma koja korisniku dozvoljava da pristupi drugim formama
 * 
 * @author Luka
 */
public class FrmMain extends javax.swing.JFrame {

    Nalog n;
    
    /**
     * Creates new form FrmMain
     */
    public FrmMain(Nalog n) {
        this.n = n;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        meniNalozi = new javax.swing.JMenuItem();
        meniTeretana = new javax.swing.JMenuItem();
        meniOprema = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Novi prozor");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        meniNalozi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        meniNalozi.setText("Nalozi");
        meniNalozi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meniNaloziActionPerformed(evt);
            }
        });
        jMenu1.add(meniNalozi);

        meniTeretana.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        meniTeretana.setText("Teretana");
        meniTeretana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meniTeretanaActionPerformed(evt);
            }
        });
        jMenu1.add(meniTeretana);

        meniOprema.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        meniOprema.setText("Oprema");
        meniOprema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meniOpremaActionPerformed(evt);
            }
        });
        jMenu1.add(meniOprema);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meniNaloziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meniNaloziActionPerformed
        new FrmNalog(n).setVisible(true);
    }//GEN-LAST:event_meniNaloziActionPerformed

    private void meniTeretanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meniTeretanaActionPerformed
        new FrmPregledTeretana().setVisible(true);
    }//GEN-LAST:event_meniTeretanaActionPerformed

    private void meniOpremaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meniOpremaActionPerformed
        new FrmOprema().setVisible(true);
    }//GEN-LAST:event_meniOpremaActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem meniNalozi;
    private javax.swing.JMenuItem meniOprema;
    private javax.swing.JMenuItem meniTeretana;
    // End of variables declaration//GEN-END:variables
    
}
