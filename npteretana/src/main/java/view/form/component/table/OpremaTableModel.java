/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.component.table;

import domain.Oprema;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabela za prikaz opreme
 * @author Luka
 */
public class OpremaTableModel extends AbstractTableModel{
    
    /**
     * Nazivi kolona u tabeli za prikaz opreme
     */
    private final String[] columnNames = new String[]{"ID", "Stanje opreme", "Vresta opreme", "Teretana", "Adresa", "Grad"};
    
    /**
     * Lista oprema
     */
    private final List<Oprema> list;  

    public OpremaTableModel(List<Oprema> list) {
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
        Oprema o = list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return o.getId();
            case 1:
                return o.getStanjeOpreme();
            case 2:
                return o.getVrsta().getVrsta();
            case 3:
                return o.getTeretana().getNaziv();
            case 4:
                return o.getTeretana().getAdresa();
            case 5:
                return o.getTeretana().getGrad().getNaziv();
            default:
                return "n/a";
            
        }
    }
        
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }   
        
    public void dodajTrenera(Oprema o){
        list.add(o);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
    
    public Oprema vratiTrenera(int row){
        return list.get(row);
    }
}
