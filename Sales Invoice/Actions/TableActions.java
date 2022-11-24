package Actions;

import GUI.InvoiceGenerator;
import Model.Invoice;
import Model.ItemsTable;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableActions implements ListSelectionListener {
    private final InvoiceGenerator frame ;
    public TableActions (InvoiceGenerator frame) {
        this.frame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex=  frame.getInvoicesTable().getSelectedRow();
        if(selectedIndex!=-1 && frame.getInvoices()!=null) {
            Invoice currentInvoice = frame.getInvoices().get(selectedIndex);
            frame.getInvoiceNumResult().setText(String.valueOf(currentInvoice.getInvoiceNum()));
            frame.getInvoiceDateTX().setText(currentInvoice.getDate());
            frame.getCustomerNameTX().setText(currentInvoice.getCustomerName());
            frame.getInvoiceTotalResult().setText(String.valueOf(currentInvoice.getInvoiceTotal()));

            ItemsTable itemsTable = new ItemsTable(currentInvoice.getItems());
            frame.getInvoiceItems().setModel(itemsTable);
            itemsTable.fireTableDataChanged();
        }
    }

}
