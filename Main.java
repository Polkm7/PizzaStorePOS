import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JPanel panelContainer;
    private JPanel customerSearch;
    private JPanel takeoutOption;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deliveryButton;
    private JButton takeoutButton;
    private JPanel createCustomer;
    private JPasswordField nameField;
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
    private JList list1;
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
    JList receiptList;
    double basePrice;
    double toppingPrice;
    double drinkprice;
    double pizzaCost;
    String pizzaSize = "";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gui");
        frame.setContentPane(new Main().panelContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


    public Main() {
        receiptModel = new DefaultListModel();
        receiptList.setModel(receiptModel);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cl.show(panelContainer, "createCard");
            }
        });
        takeoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelContainer, "Menu");
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                cl.show(panelContainer, "takeoutOption");
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
//                    Todo: get the receiptModel and receiptList to display an added element.
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