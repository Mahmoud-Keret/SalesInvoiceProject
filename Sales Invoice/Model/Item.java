package Model;

public class Item {
//    private int invoiceNum;
    private final String item;
    private final double price;
    private final int amount ;
    private final Invoice invoice;

    public Item( String item, double price, int amount, Invoice invoice) {

        this.item = item;
        this.price = price;
        this.amount = amount;
        this.invoice = invoice;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public Invoice getInvoice() {
        return invoice;
    }


    public double getTotal(){
        return price*amount;
    }

    public String saveCSV(){
        return invoice.getInvoiceNum() + "," + item + "," + price + "," + amount ;
    }

}
