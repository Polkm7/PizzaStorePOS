import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private JPanel panelContainer;
    private JPanel customerSearch;
    private JPanel takeoutOption;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deliveryButton;
    private JButton takeoutButton;
    private JPanel createCustomer;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JButton createButton;
    private JLabel createCustomerLabel;
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel zipLabel;
    private JLabel phoneLabel;
    private JList customerList;
    private JButton nextButton;
    private JButton createCustomerButton;
    private JPanel Menu;
    private JPanel Receipt;
    private JPanel creditPanel;
    private JCheckBox largeCheckBox;
    private JCheckBox sausageCheckBox;
    private JCheckBox mediumCheckBox;
    private JCheckBox pepperoniCheckBox;
    private JCheckBox chickenCheckBox;
    private JCheckBox smallCheckBox;
    private JCheckBox olivesCheckBox;
    private JCheckBox peppersCheckBox;
    private JButton addToCartButton;
    private JButton nextButton1;
    private JCheckBox waterCheckBox;
    private JCheckBox cokeCheckBox;
    private JButton resetButton;
    private JLabel sizeLabel;
    private JLabel toppingLabel;
    private JTextField totaltextField;

    private JButton add1Button;
    private JTextField totalField1;

    private JTextField cardNumberField;
    private JTextField nameField2;
    private JTextField xdateField;
    private JButton cancelButton;
    private JLabel cardNumberLabel;
    private JLabel nameLabel2;
    private JLabel xdateLabel;
    private JButton payButton;
    private JLabel receiptLabel;
    private JPanel PanelContainer;
    private CardLayout cl = (CardLayout) panelContainer.getLayout();
    private double total = 0.00;
    DefaultListModel receiptModel;
    DefaultListModel customerModel;
    JList receiptList;
    private JTabbedPane PaymentScreen;
    private JTextField cashAmountField;
    private JLabel cashAmountLabel;
    private JButton cashSubmitButton;
    private JButton cancelButton2;
    private JPanel checkPanel;
    private JTextField checkNumberField;
    private JTextField checkAmountField;
    private JPanel cashPanel;
    private JButton CheckSubmitButton;
    private JButton cancelButton3;
    private JLabel checkNumberLabel;
    private JLabel checkAmountLabel;
    private JCheckBox pepperCheckBox;
    private JCheckBox pretzelCheckBox;
    private JCheckBox cheeseCheckBox;
    private JButton newOrderButton;
    private JButton finishButton;
    double basePrice;
    double toppingPrice;
    double drinkprice;
    double pizzaCost;
    String pizzaSize = "";
    public InsertApp insertApp;
    String customerID;
    boolean isDrink;
    int numberOfToppings;
    ButtonGroup sizeGroup;
    ButtonGroup crustGroup;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Gui");
        frame.setContentPane(new Main().panelContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


    public Main() {
        System.out.println("Starting Customer ID: " + customerID);

        insertApp = new InsertApp();
        try {
            insertApp.getOrderID();
        } catch (SQLException i ){
            System.out.println(i.getMessage());
        }
        customerModel = new DefaultListModel();
        customerList.setModel(customerModel);
        receiptModel = new DefaultListModel();
        receiptList.setModel(receiptModel);
        //insertApp.getOrderID();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerModel.removeAllElements();
                try {

                    customerModel.addElement(insertApp.search(searchField.getText()));
                    customerList.setSelectedIndex(0);
                    //insertApp.search("7064328825");
                }catch (SQLException f) {
                    System.out.println(f.getMessage());
                }

            }
        });
        takeoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Menu");
            }
        });
        createButton.addActionListener(new ActionListener() {
            //adds customer info to database

            @Override
                public void actionPerformed(ActionEvent e) {
                if (phoneField.getText().length() == 0) {
                    phoneField.requestFocusInWindow();
                } else
                {

                    //String name = nameField.get
                    //insertApp.insert("Adam", "2245586332");

                    String name = nameField.getText();
                    String phone = phoneField.getText();
                    String address = addressField.getText();
                    String city = cityField.getText();
                    String state = stateField.getText();
                    String zip = zipField.getText();
                    try {
                        insertApp.insert(name, phone, address, city, state, zip);
                    } catch (SQLException h) {
                        System.out.println(h.getMessage());
                    }
                    insertApp.setCurrentCustomerID(phone);
                    customerID = phone;
                    System.out.println("Create customer, id: " + customerID);
                    cl.show(panelContainer, "takeoutCard");
                }
            }
        });
        createCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "createCard");
            }

        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerID  = insertApp.getCurrentCustomerID();
                System.out.println("Customer id when hitting next: " + customerID);
                if(customerID != null && !customerID.isEmpty()){
                    cl.show(panelContainer, "takeoutCard");
                } else{
                    JOptionPane.showMessageDialog(null, "Not valid customer: " + insertApp.getCurrentCustomerID());
                }

            }
        });
        deliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cl.show(panelContainer, "Menu");

            }
        });


        largeCheckBox.addActionListener(actionListener);
        mediumCheckBox.addActionListener(actionListener);
        smallCheckBox.addActionListener(actionListener);
        sausageCheckBox.addActionListener(actionListener);
        pepperoniCheckBox.addActionListener(actionListener);
        chickenCheckBox.addActionListener(actionListener);
        peppersCheckBox.addActionListener(actionListener);
        olivesCheckBox.addActionListener(actionListener);
        addToCartButton.addActionListener(actionListener);
    //    add1Button.addActionListener(actionListener);


        nextButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "paymentCard");
            }
        });
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertApp.paymentType("Credit", insertApp.getLastID());
                } catch (SQLException i) {
                    System.out.println(i.getMessage());
                }
                cl.show(panelContainer, "receiptCard");
            }
        });
        CheckSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertApp.paymentType("Check", insertApp.getLastID());
                } catch (SQLException i) {
                    System.out.println(i.getMessage());
                }
                cl.show(panelContainer, "receiptCard");
            }
        });
        cashSubmitButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertApp.paymentType("Cash", insertApp.getLastID());
                } catch (SQLException i) {
                    System.out.println(i.getMessage());
                }
                cl.show(panelContainer, "receiptCard");
            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
      

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(e.getSource() == resetButton){

                  largeCheckBox.setSelected(false);
                  mediumCheckBox.setSelected(false);
                  smallCheckBox.setSelected(false);
                  pepperoniCheckBox.setSelected(false);
                  chickenCheckBox.setSelected(false);
                  peppersCheckBox.setSelected(false);
                  sausageCheckBox.setSelected(false);
                  olivesCheckBox.setSelected(false);
                  peppersCheckBox.setSelected(false);
                  pretzelCheckBox.setSelected(false);
                  cheeseCheckBox.setSelected(false);
                  waterCheckBox.setSelected(false);
                  cokeCheckBox.setSelected(false);
                  totaltextField.setText(null);
                  sizeGroup.clearSelection();
                  crustGroup.clearSelection();

                  ((DefaultListModel)receiptList.getModel()).clear();

              }
            }
        });
        newOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "searchCard");
                largeCheckBox.setSelected(false);
                mediumCheckBox.setSelected(false);
                smallCheckBox.setSelected(false);
                pepperoniCheckBox.setSelected(false);
                chickenCheckBox.setSelected(false);
                peppersCheckBox.setSelected(false);
                sausageCheckBox.setSelected(false);
                olivesCheckBox.setSelected(false);
                peppersCheckBox.setSelected(false);
                pretzelCheckBox.setSelected(false);
                cheeseCheckBox.setSelected(false);
                waterCheckBox.setSelected(false);
                cokeCheckBox.setSelected(false);
                totaltextField.setText(null);
                searchField.setText(null);
                ((DefaultListModel)receiptList.getModel()).clear();
                ((DefaultListModel)customerList.getModel()).clear();


            }
        });
    }

    ActionListener actionListener;

    {
        actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                /* pizza base */
                basePrice = 0;
                if (largeCheckBox.isSelected()) {
                    basePrice = 16;
                    pizzaSize = "Large";
                } else if (mediumCheckBox.isSelected()) {
                    basePrice = 14;
                    pizzaSize = "Medium";

                } else if (smallCheckBox.isSelected()) {
                    basePrice = 10;
                    pizzaSize = "Small";

                }
                /*pizza topping*/
                JCheckBox[] toppings;
                toppings = new JCheckBox[]{
                        pepperoniCheckBox,
                        chickenCheckBox,
                        peppersCheckBox,
                        sausageCheckBox,
                        olivesCheckBox,

                };
                toppingPrice = 0;
                if (waterCheckBox.isSelected() || cokeCheckBox.isSelected()){
                    isDrink = true;
                    drinkprice++;
                } else {
                    isDrink = false;

                }
                for (JCheckBox topping : toppings) {
                    if (topping.isSelected()) {
                        toppingPrice += 1;

                    }
                }

                if (e.getSource() == addToCartButton) {
                    pizzaCost = basePrice + toppingPrice;
                    numberOfToppings = (int) toppingPrice;
                    receiptList.setModel(receiptModel);

                    receiptModel.addElement("Customer ID:" + customerID + "  " +  "Pizza size: "+ pizzaSize  + "  " + "Number of toppings:"  + numberOfToppings  + " " + " Total cost:" + pizzaCost);
                    total = total+pizzaCost+drinkprice; //adds current pizza to total price
                    totaltextField.setText(Double.toString(total));

                    try {
                        insertApp.addCart(customerID, insertApp.getLastID(), pizzaSize, numberOfToppings, isDrink, total);
                    } catch (SQLException g){
                        System.out.println(g.getMessage());
                    }

                }



            }

            ;


        };
    }

    ;
}