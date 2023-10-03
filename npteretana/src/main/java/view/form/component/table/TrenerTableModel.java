/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.component.table;

import domain.Trener;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabela za prikaz trenera
 * 
 * @author Luka
 */
public class TrenerTableModel extends AbstractTableModel{
    
    /**
     * Nazivi kolona u tabeli za prikaz trenera
     */
    private final String[] columnNames = new String[]{"ID", "Ime", "Prezime", "Teretana", "Adresa", "Grad"};
    
    /**
     * Lista trenera
     */
    private final List<Trener> list;   

    public TrenerTableModel(List<Trener> list) {
        this.list = list;
    }   
    
    
    @Override
    public int getRowCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trener t = list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return t.getId();
            case 1:
                return t.getIme();
            case 2:
                return t.getPrezime();
            case 3:
                return t.getTeretana().getNaziv();
            case 4:
                return t.getTeretana().getAdresa();
            case 5:
                return t.getTeretana().getGrad().getNaziv();
            default:
                return "n/a";
            
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    public void dodajTrenera(Trener t){
        list.add(t);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
    
    public Trener vratiTrenera(int row){
        return list.get(row);
    }
    
}
