/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.component.table;

import domain.Teretana;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabela za prikaz teretana
 * 
 * @author Luka
 */
public class TeretanaTableModel extends AbstractTableModel{
    
    /**
     * Nazivi kolona u tabeli za prikaz teretane
     */
    private final String[] columnNames = new String[]{"ID", "Naziv", "Adresa", "Grad", "Prosecna ocena"};
    
    /**
     * Lista teretana
     */
    private final List<Teretana> list;
        
    public TeretanaTableModel(List<Teretana> list) {
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
        Teretana t = list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return t.getId();
            case 1:
                return t.getNaziv();
            case 2:
                return t.getAdresa();
            case 3:
                return t.getGrad().getNaziv();
            case 4:
                return t.getProsecnaOcena();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
                
    public void dodajTeretanu(Teretana t){
        list.add(t);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
    
    public Teretana vratiClanarinu(int row){
        return list.get(row);
    }
    
}
