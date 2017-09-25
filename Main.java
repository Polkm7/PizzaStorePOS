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
    private JButton addButton;
    private JButton nextButton1;
    private JLabel sizeLabel;
    private JLabel toppingLabel;
    private JTextField totaltextField;
    private JPanel Drink;
    private JCheckBox waterCheckBox;
    private JCheckBox cokeCheckBox;
    private JButton add1Button;
    private JButton nextButton2;
    private JTextField totalField1;
    private JCheckBox dietCokeCheckBox;
    private JCheckBox spriteCheckBox;
    private JPanel PanelContainer;
    private CardLayout cl = (CardLayout) panelContainer.getLayout();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gui");
        frame.setContentPane(new Main().panelContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


    public Main() {

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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        addButton.addActionListener(actionListener);
        cokeCheckBox.addActionListener(actionListener);
        waterCheckBox.addActionListener(actionListener);
        dietCokeCheckBox.addActionListener(actionListener);
        spriteCheckBox.addActionListener(actionListener);
        add1Button.addActionListener(actionListener);


    }

    ActionListener actionListener;

    {
        actionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double price;
                double topprice;
                double drinkprice;
                double totalprice;

                /* pizza menu */
                price = 0;
                if (largeCheckBox.isSelected()) {
                    price = 16;
                } else if (mediumCheckBox.isSelected()) {
                    price = 14;
                } else if (smallCheckBox.isSelected()) {
                    price = 10;
                }

                JCheckBox[] toppings;
                toppings = new JCheckBox[]{
                        pepperoniCheckBox,
                        chickenCheckBox,
                        peppersCheckBox,
                        sausageCheckBox,
                        olivesCheckBox,

                };
                topprice = 0;
                for (JCheckBox topping : toppings) {
                    if (topping.isSelected()) {
                        topprice += 1;
                    }
                }
                if (e.getSource() == addButton) {
                    totalprice = price + topprice;
                    //JOptionPane.showMessageDialog(null, "total price is:" + totalprice);
                    //System.exit(0);
                    totaltextField.setText(Double.toString(totalprice));
                } else {
                    return;
                }
                /* Drink menu */
                drinkprice = 0;
                if (cokeCheckBox.isSelected()) {
                    drinkprice += 2;
                } else if (waterCheckBox.isSelected()) {
                    drinkprice += 2;
                } else if (dietCokeCheckBox.isSelected()) {
                    drinkprice += 2;
                } else if (spriteCheckBox.isSelected()) {
                    drinkprice += 2.5;
                }

                if (e.getSource() == add1Button) {
                    totalprice = price + topprice + drinkprice;
                    //JOptionPane.showMessageDialog(null, "total price is:" + totalprice);
                    //System.exit(0);
                    totalField1.setText(Double.toString(totalprice));
                }


            }

            ;


        };
    }

    ;
}