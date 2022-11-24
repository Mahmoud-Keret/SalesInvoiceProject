package Model;

import java.util.ArrayList;

public class Invoice {
    private final int invoiceNum;
    private final String date;
    private final String customerName;
    private ArrayList<Item> items;

    public Invoice(int invoiceNum, String date, String customerName) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.customerName = customerName;
    }

    public int getInvoiceNum() {
        if (items==null){
            items=new ArrayList<>();
        }
        return invoiceNum;
    }

    public String getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public double getInvoiceTotal () {
        double total = 0.0;
        for (Item item : items) {
            total += item.getTotal();
        }

        return total;
    }
    public String saveCSV () {
        return invoiceNum + "," + date + "," + customerName ;
    }
}
