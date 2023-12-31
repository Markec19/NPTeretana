/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.form;

import domain.Clanarina;
import domain.Nalog;
import domain.Teretana;
import controller.Controller;
import domain.Grad;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.form.component.table.ClanarinaTableModel;
import view.form.component.table.TeretanaTableModel;
import view.form.util.FormMode;

/**
 * Forma za prikaz, dodavanje i izmenu clanarine
 * 
 * @author Luka
 */
public class FrmClanarina extends javax.swing.JFrame {
    
    List<Teretana> teretane;
    Clanarina c;
    Teretana t;
    Nalog n;
    FrmNalog nalogForma;
    
    /**
     * Creates new form FrmClanarina
     */
    public FrmClanarina(Nalog n, FrmNalog form, FormMode fm) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        inicijalizujFormu(fm);
        
        teretane = new ArrayList<>();
        c = new Clanarina();
        t = new Teretana();
        this.n = n;
        nalogForma = form;        
    }
    
    public FrmClanarina(Clanarina c, FrmNalog form, FormMode fm){
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.c = c;
        inicijalizujClanarinu(c);
        inicijalizujFormu(fm);
        nalogForma = form;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        btnPretrazi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTeretane = new javax.swing.JTable();
        btnDodaj = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jcbDuzina = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTGrad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTAdresa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTNaziv = new javax.swing.JTextField();
        btnPotvrdi = new javax.swing.JButton();
        btnProduzi = new javax.swing.JButton();
        jcbGrad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Grad:");

        btnPretrazi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPretrazi.setText("Pretraži");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        tblTeretane.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblTeretane);

        btnDodaj.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDodaj.setText("Dodaj");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Dužina članarine:");

        jcbDuzina.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbDuzina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1 mesec", "3 meseca", "6 meseci" }));
        jcbDuzina.setSelectedIndex(-1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Grad:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Adresa:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Naziv:");

        btnPotvrdi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPotvrdi.setText("Potvrdi");
        btnPotvrdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPotvrdiActionPerformed(evt);
            }
        });

        btnProduzi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProduzi.setText("Produži");
        btnProduzi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProduziActionPerformed(evt);
            }
        });

        jcbGrad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbGrad.setSelectedIndex(-1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(27, 27, 27)
                                .addComponent(txtTNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcbDuzina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnProduzi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPotvrdi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(jcbGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(btnPretrazi))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnPretrazi)
                    .addComponent(jcbGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPotvrdi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProduzi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTNaziv, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTGrad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jcbDuzina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 98, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        try {
            Grad grad = ((Grad)jcbGrad.getSelectedItem());
            teretane = nadjiTeretane(grad);
            
            if(teretane.size() > 0){
                JOptionPane.showMessageDialog(this, "Sistem je našao teretane po zadatim vrednostima");
                azurirajTabelu(teretane);
                tblTeretane.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(this, "Sistem ne može da nađe teretane po zadatim vrednostima");
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da nađe teretane po zadatim vrednostima");
            Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void btnPotvrdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPotvrdiActionPerformed
        if(tblTeretane.getSelectedRow() != -1){
            try {
                t = teretane.get(tblTeretane.getSelectedRow());
                ucitajTeretanu(t);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne može da učita teretanu");
                Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Izaberite teretanu");
        }
    }//GEN-LAST:event_btnPotvrdiActionPerformed

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        if(jcbDuzina.getSelectedIndex() != -1){
            int mesec = odrediMesec();
            BigDecimal cena = odrediCenu();
            try {
                Clanarina c = new Clanarina();
                //c = kreirajClanarinu();
                c.setCena(cena);
                c.setNalog(n);
                c.setTeretana(t);
                
                LocalDate datumOd = LocalDate.now();
                c.setDatumOd(datumOd);
                
                LocalDate datumDo = datumOd.plusMonths(mesec);
                c.setDatumDo(datumDo);
                 
                if(proveriPostojanjeClanarine(c)){
                    JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti članarinu");
                    JOptionPane.showMessageDialog(this, "Korisnik već ima članarinu u ovoj teretani");
                }else{
                    JOptionPane.showMessageDialog(this, "Sistem je zapamtio članarinu");
                    zapamtiClanarinu(c);
                }
                
                azurirajNalogFormu(n);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti članarinu");
                Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti članarinu");
            JOptionPane.showMessageDialog(this, "Odaberite dužinu trajanja članarine!");
        }
    }//GEN-LAST:event_btnDodajActionPerformed

    private void btnProduziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProduziActionPerformed
        if(jcbDuzina.getSelectedIndex() != -1){
            int mesec = odrediMesec();
            BigDecimal cena = odrediCenu();
            
            if(istekla(c)){
                LocalDate datumOd = LocalDate.now();
                LocalDate datumDo = datumOd.plusMonths(mesec);

                c.setDatumOd(datumOd);
                c.setDatumDo(datumDo);
            }else{
                 LocalDate datum = c.getDatumDo().plusMonths(mesec);
                 c.setDatumDo(datum);
             }
            try {
                c.setCena(cena);
                sacuvajClanarinu(c);
                //azurirajNalogFormu(c.getNalog());
                
                JOptionPane.showMessageDialog(this, "Sistem je sačuvao članarinu");
                JOptionPane.showMessageDialog(this, "Članarina je produžena do " + c.getDatumDo());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne može da sačuva članarinu");
                Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Sistem ne može da sačuva članarinu");
            JOptionPane.showMessageDialog(this, "Odaberite dužinu trajanja članarine!");
        }             
        
    }//GEN-LAST:event_btnProduziActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JButton btnPotvrdi;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JButton btnProduzi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbDuzina;
    private javax.swing.JComboBox<String> jcbGrad;
    private javax.swing.JTable tblTeretane;
    private javax.swing.JTextField txtTAdresa;
    private javax.swing.JTextField txtTGrad;
    private javax.swing.JTextField txtTNaziv;
    // End of variables declaration//GEN-END:variables

    /**
     * Vraca listu teretana u zadatom gradu
     * @param grad
     * @return lista teretana
     * @throws Exception
     */
    private List<Teretana> nadjiTeretane(Grad grad) throws Exception {
        return Controller.getInstance().nadjiTeretane(grad);
    }
    
    /**
     * Vraca broj meseci za koje clanarina kupljena ili produzena
     * 
     * @return broj meseci
     */
    private int odrediMesec() {
        int slucaj = jcbDuzina.getSelectedIndex();
        switch(slucaj){
            case 0: return 1;
            case 1: return 3;
            case 2: return 6;
        }
        return -1;
    }

    /**
     * Odredjuje cenu clanarine na osnovu njene duzine
     * 
     * @return cena clanarine
     */
    private BigDecimal odrediCenu() {
        int slucaj = jcbDuzina.getSelectedIndex();
        int cena = 0;
        switch(slucaj){
            case 0: cena = 2500; break;
            case 1: cena = 4500; break;
            case 2: cena = 6500; break;
        }
        return BigDecimal.valueOf(cena);        
    }

    /**
     * Azurira tabelu clanarina u FrmNalog, koje se odnose na clanarine koje je odredjeni nalog kupio
     * 
     * @throws Exception 
     */
    private void azurirajNalogFormu(Nalog n) throws Exception {
        List<Clanarina> list = Controller.getInstance().vratiSveClanarine(n);
        nalogForma.getTblClanarina().setModel(new ClanarinaTableModel(list));
    }

    /**
     * Proverava da li je clanarina istekla
     * 
     * @param c clanarina koja se proverava
     * @return da li je clanarina istekla
     */
    private boolean istekla(Clanarina c) {
        return c.getDatumDo().isBefore(LocalDate.now());        
    }
    
    /**
     * Vraca listu svih gradova
     * 
     * @return lista svih gradova
     * @throws Exception 
     */
    private List<Grad> vratiListuGradova() throws Exception{
        return Controller.getInstance().vratiSveGradove();
    }

    /**
     * Inicijalizuje formu na osnovu rezima forme
     * 
     * @param fm rezim forme
     */
    private void inicijalizujFormu(FormMode fm) {
        List<Teretana> list = new ArrayList<>();
        
        txtTAdresa.setEditable(false);
        txtTGrad.setEditable(false);
        txtTNaziv.setEditable(false);
        tblTeretane.setEnabled(false);
        
        List<Grad> gradovi;
        try {
            gradovi = vratiListuGradova();
            jcbGrad.setModel(new DefaultComboBoxModel(gradovi.toArray()));
        } catch (Exception ex) {
            Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Sistem ne može da kreira članarinu");
        }
        
        if(fm == FormMode.FORM_EDIT){
            jcbGrad.setEnabled(false);
            btnPretrazi.setEnabled(false);
            btnPotvrdi.setEnabled(false);
            btnDodaj.setVisible(false);
        }
        if(fm == FormMode.FORM_ADD){
            btnProduzi.setVisible(false);
            try {
                list = vratiTeretane();
            } catch (Exception ex) {
                Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        tblTeretane.setModel(new TeretanaTableModel(list));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Vraca listu svih teretana
     * 
     * @return lista svih teretana
     * @throws Exception 
     */
    private List<Teretana> vratiTeretane() throws Exception{
        return Controller.getInstance().vratiSveTeretane();
    }
    
    /**
     * Vraca listu svih clanarina
     * 
     * @return lista svih clanarina
     * @throws Exception 
     */
    private List<Clanarina> vratiListuClanarina() throws Exception{
        return Controller.getInstance().vratiSveClanarine();
    }

    /**
     * Azurira tabelu teretana
     * 
     * @param teretane lista teretana
     */
    private void azurirajTabelu(List<Teretana> teretane) {
        TeretanaTableModel model = new TeretanaTableModel(teretane);
        tblTeretane.setModel(model);
    }

    /**
     * Ucitava teretanu iz baze podataka
     * 
     * @param teretana
     * @throws Exception 
     */
    private void ucitajTeretanu(Teretana teretana) throws Exception {
        t = Controller.getInstance().ucitajTeretanu(teretana);
        
        if(t.getId() != 0){
            txtTNaziv.setText(t.getNaziv());
            txtTGrad.setText(t.getGrad().getNaziv());
            txtTAdresa.setText(t.getAdresa());
            JOptionPane.showMessageDialog(this, "Sistem je učitao teretanu");
        }else{
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita teretanu");
            t = new Teretana();
        }
    }

    /**
     * Proverava da li nalog vec ima uplacenu clanarinu u teretani
     * 
     * @param clanarina
     * @return da li clanarina postoji
     * @throws Exception 
     */
    private boolean proveriPostojanjeClanarine(Clanarina clanarina) throws Exception {
        List<Clanarina> clanarine = vratiListuClanarina();
        for (int i = 0; i < clanarine.size(); i++)
            if(clanarina.getNalog().getId() == clanarine.get(i).getNalog().getId() && 
                    clanarina.getTeretana().getId() == clanarine.get(i).getTeretana().getId())
                return true;
        return false;
    }

    /**
     * Cuva clanarinu u bazi
     * 
     * @param c clanarina
     * @throws Exception 
     */
    private void zapamtiClanarinu(Clanarina c) throws Exception {
        Controller.getInstance().zapamtiClanarinu(c);
    }

    /**
     * Popunjava tekstualna polja i combobox u formi na osnovu ucitane clanarine
     * 
     * @param clanarina 
     */
    private void inicijalizujClanarinu(Clanarina clanarina) {
        try {
            c = ucitajClanarinu(clanarina);
            if(c.getId() != 0){
                txtTAdresa.setText(c.getTeretana().getAdresa());
                txtTGrad.setText(c.getTeretana().getGrad().getNaziv());
                txtTNaziv.setText(c.getTeretana().getNaziv());
                
                JOptionPane.showMessageDialog(this, "Sistem je učitao članarinu");
            }else{
                JOptionPane.showMessageDialog(this, "Sistem ne može da učita članarinu"); 
                c = new Clanarina();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne može da učita članarinu");
            Logger.getLogger(FrmClanarina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cuva izmenu clanarine u bazi
     * 
     * @param c clanarina
     * @throws Exception 
     */
    private void sacuvajClanarinu(Clanarina c) throws Exception {
        Controller.getInstance().sacuvajClanarinu(c);
    }

    /**
     * Ucitava clanarinu iz baze
     * @param c clanarina
     * @return
     * @throws Exception 
     */
    private Clanarina ucitajClanarinu(Clanarina c) throws Exception {
        return Controller.getInstance().ucitajClanarinu(c);
    }

    
    
}
