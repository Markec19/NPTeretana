/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.component.table;

import domain.Clanarina;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Model tabela za prikaz clanarina
 * 
 * @author Luka
 */
public class ClanarinaTableModel extends AbstractTableModel{
    
    /**
     * Nazivi kolona u tabeli za prikaz clanarina
     */
    private final String[] columnNames = new String[]{"ID", "Cena", "Korisničko ime", "Teretana", "Adresa", "Grad", "Datum od", "Datum do"};
    
    /**
     * Lista clanarina
     */
    private final List<Clanarina> list;    
    
    public ClanarinaTableModel(List<Clanarina> list) {
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
        Clanarina c = list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return c.getId();
            case 1:
                return c.getCena();
            case 2:
                return c.getNalog().getKorisnickoIme();
            case 3:
                return c.getTeretana().getNaziv();
            case 4:
                return c.getTeretana().getAdresa();
            case 5:
                return c.getTeretana().getGrad().getNaziv();
            case 6:
                return c.getDatumOd();
            case 7:
                return c.getDatumDo();
            default:
                return "n/a";
            
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    
    
    public void dodajClanarinu(Clanarina c){
        list.add(c);
        fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }
    
    public Clanarina vratiClanarnu(int row){
        return list.get(row);
    }
    
}
