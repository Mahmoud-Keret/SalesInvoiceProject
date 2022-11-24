package Model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class InvoicesTable extends AbstractTableModel {
    private final ArrayList<Invoice> invoices;
    private final String[] columns={"No.","Date","Customer","Total"};

    public InvoicesTable(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice invoice = invoices.get(rowIndex);
        switch (columnIndex) {
            case 0 : return invoice.getInvoiceNum();
            case 1 : return invoice.getDate();
            case 2 : return invoice.getCustomerName();
            case 3 : return invoice.getInvoiceTotal();
            default:  return "";

        }
    }
}
