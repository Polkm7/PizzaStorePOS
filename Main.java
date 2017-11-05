import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
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
    private JPanel Payment;
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
    double basePrice;
    double toppingPrice;
    double drinkprice;
    double pizzaCost;
    String pizzaSize = "";
    public InsertApp insertApp;
    String customerID;
    boolean isDrink;
    int numberOfToppings;



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
                } catch (SQLException h){
                    System.out.println(h.getMessage());
                }
                insertApp.setCurrentCustomerID(phone);
                customerID = phone;
                System.out.println("Create customer, id: " + customerID);
                cl.show(panelContainer, "takeoutCard");
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
                cl.show(panelContainer, "receiptCard");
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

                    receiptList.setModel(receiptModel);
                    receiptModel.addElement(pizzaSize + " " + toppingPrice + " topping pizza: " + pizzaCost);
                    total = total+pizzaCost+drinkprice; //adds current pizza to total price
                    totaltextField.setText(Double.toString(total));
                    numberOfToppings = (int) toppingPrice;
                    try {
                        insertApp.addCart(customerID, insertApp.getLastID(), pizzaSize, numberOfToppings, isDrink, total);
                    } catch (SQLException g){
                        System.out.println(g.getMessage());
                    }

                }

                //cbg = new CheckboxGroup();


                //reset button
//                if (e.getSource() == resetButton){
//
//                }


                /*if (e.getSource() == add1Button) {
                    pizzaCost = basePrice + toppingPrice + drinkprice;
                    //JOptionPane.showMessageDialog(null, "total price is:" + totalprice);
                    //System.exit(0);

                    //JOptionPane.showMessageDialog(null,"Total is " + total + ". Pizza cost is " + pizzaCost + " .");
//
                    receiptModel.addElement(pizzaSize + " " + toppingPrice + " topping pizza: " + pizzaCost); //adds pizza to list model but for some reason doest work right now
                    total = total+pizzaCost; //adds current pizza to total price
                    totalField1.setText(Double.toString(total));


                }*/


            }

            ;


        };
    }

    ;
}