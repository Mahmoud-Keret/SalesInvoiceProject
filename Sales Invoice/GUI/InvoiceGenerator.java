package GUI;

import Actions.Actions;
import Model.Invoice;
import Model.InvoicesTable;
import Actions.TableActions;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InvoiceGenerator extends JFrame {


    public InvoiceGenerator() {
        super("Sales Invoice Generator");
        initComponents();
    }

    private void initComponents() {

        JPanel invoicesTablePanel = new JPanel();
        JScrollPane jScrollPane2 = new JScrollPane();
        invoicesTable = new JTable();
        JPanel invoiceItemsPanel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        invoiceItems = new JTable();
        JLabel invoiceNumber = new JLabel();
        JLabel invoiceDate = new JLabel();
        JLabel customerName = new JLabel();
        JLabel invoiceTotal = new JLabel();
        invoiceDateTX = new JTextField();
        customerNameTX = new JTextField();
        invoiceNumResult = new JLabel();
        invoiceTotalResult = new JLabel();
        JButton createNewInvoice = new JButton();
        JButton deleteInvoice = new JButton();
        JButton createNewItem = new JButton();
        JButton deleteItem = new JButton();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenuItem loadFileMenuItem = new JMenuItem();
        JMenuItem saveItemMenuBar = new JMenuItem();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setName("MainInvoiceFrame");

        invoicesTablePanel.setBorder(BorderFactory.createTitledBorder("Invoices Table"));
        invoicesTablePanel.setPreferredSize(new Dimension(450, 600));

        invoicesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "No.", "Date", "Name", "Total"
                }
        ));
        invoicesTable.getSelectionModel().addListSelectionListener(tableActions);
        jScrollPane2.setViewportView(invoicesTable);

        GroupLayout InvoicesTablePanelLayout = new GroupLayout(invoicesTablePanel);
        invoicesTablePanel.setLayout(InvoicesTablePanelLayout);
        InvoicesTablePanelLayout.setHorizontalGroup(
                InvoicesTablePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(InvoicesTablePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2,GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                                .addContainerGap())
        );
        InvoicesTablePanelLayout.setVerticalGroup(
                InvoicesTablePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(InvoicesTablePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2,GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                                .addContainerGap())
        );

        invoiceItemsPanel.setBorder(BorderFactory.createTitledBorder("Invoice Items"));
        invoiceItemsPanel.setPreferredSize(new java.awt.Dimension(450, 300));

        invoiceItems.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String [] {
                        "No.", "Item Name", "Price", "Amount", "Total"
                }
        ));
        jScrollPane1.setViewportView(invoiceItems);

        GroupLayout InvoiceItemsPanelLayout = new GroupLayout(invoiceItemsPanel);
        invoiceItemsPanel.setLayout(InvoiceItemsPanelLayout);
        InvoiceItemsPanelLayout.setHorizontalGroup(
                InvoiceItemsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(InvoiceItemsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                                .addContainerGap())
        );
        InvoiceItemsPanelLayout.setVerticalGroup(
                InvoiceItemsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(InvoiceItemsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                .addContainerGap())
        );

        invoiceNumber.setText("Invoice Number");

        invoiceDate.setText("Invoice Date");

        customerName.setText("Customer Name");

        invoiceTotal.setText("Invoice Total");

        createNewInvoice.setText("Create New Invoice");
        createNewInvoice.setPreferredSize(new Dimension(150, 25));
        createNewInvoice.addActionListener(actions);
        createNewInvoice.setActionCommand("Create New Invoice");

        deleteInvoice.setText("Delete Invoice");
        deleteInvoice.setPreferredSize(new Dimension(150, 25));
        deleteInvoice.addActionListener(actions);
        deleteInvoice.setActionCommand("Delete Invoice");

        createNewItem.setText("Create New Item");
        createNewItem.setPreferredSize(new Dimension(120, 25));
        createNewItem.addActionListener(actions);
        createNewItem.setActionCommand("Create New Item");

        deleteItem.setText("Delete Item");
        deleteItem.setPreferredSize(new Dimension(120, 25));
        deleteItem.addActionListener(actions);
        deleteItem.setActionCommand("Delete Item");

        fileMenu.setText("File");

        loadFileMenuItem.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
        loadFileMenuItem.setText("Load File");
        loadFileMenuItem.addActionListener(actions);
        loadFileMenuItem.setActionCommand("Load File");
        fileMenu.add(loadFileMenuItem);


        saveItemMenuBar.setAccelerator(javax.swing.KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
        saveItemMenuBar.setText("Save File");
        saveItemMenuBar.addActionListener(actions);
        saveItemMenuBar.setActionCommand("Save File");
        fileMenu.add(saveItemMenuBar);

        jMenuBar1.add(fileMenu);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(invoicesTablePanel, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(invoiceItemsPanel, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(invoiceTotal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(invoiceTotalResult, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(invoiceNumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(invoiceDate, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                                        .addComponent(customerName, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(invoiceDateTX, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(customerNameTX,GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(invoiceNumResult, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(8, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(createNewInvoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86)
                                .addComponent(deleteInvoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createNewItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(deleteItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(invoicesTablePanel, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(invoiceNumber, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(invoiceNumResult, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(invoiceDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(invoiceDateTX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(customerName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(customerNameTX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(invoiceTotal, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(invoiceTotalResult, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(invoiceItemsPanel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(createNewInvoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteInvoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(createNewItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(71, Short.MAX_VALUE))
        );

        setBounds(100, 50, 1014, 707);
    }


    public static void main(String[] args) {

        new InvoiceGenerator().setVisible(true);
    }

    private JTable invoiceItems;
    private JTable invoicesTable;
    private JTextField customerNameTX;
    private JTextField invoiceDateTX;
    private JLabel invoiceNumResult;
    private JLabel invoiceTotalResult;
    private ArrayList<Invoice> invoices;
    private InvoicesTable invoicesTableModel;
    private final Actions actions=new Actions(this);
    private final TableActions tableActions = new TableActions(this);


    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public InvoicesTable getInvoicesTableModel() {
        return invoicesTableModel;
    }

    public void setInvoicesTableModel(InvoicesTable invoicesTableModel) {
        this.invoicesTableModel = invoicesTableModel;
    }

    public JTable getInvoiceItems() {
        return invoiceItems;
    }

    public JTable getInvoicesTable() {
        return invoicesTable;
    }

    public JTextField getCustomerNameTX() {
        return customerNameTX;
    }

    public JTextField getInvoiceDateTX() {
        return invoiceDateTX;
    }

    public JLabel getInvoiceNumResult() {
        return invoiceNumResult;
    }

    public JLabel getInvoiceTotalResult() {
        return invoiceTotalResult;
    }

    public int getNextInvoiceNum (){
        int num = 0 ;

        for (Invoice invoice:invoices){
            if(invoice.getInvoiceNum()>num){
                num=invoice.getInvoiceNum();
            }
        }
        return ++num;
    }
    public Actions getActions(){
        return actions;
    }
}
