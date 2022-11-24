package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ItemsTable extends AbstractTableModel {
    private final ArrayList<Item> items ;
    String [] columns={"No.","Item Name","Price","Amount","Total"};

    public ItemsTable(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
//        return 5;
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = items.get(rowIndex);
        switch (columnIndex){
            case 0 : return item.getInvoice().getInvoiceNum();
            case 1 : return item.getItem();
            case 2 : return item.getPrice();
            case 3 : return item.getAmount();
            case 4 : return item.getTotal();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {

        return columns[column];
    }
}
