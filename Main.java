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
    private JLabel createCustomerLabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel zipLabel;
    private JLabel stateLabel;
    private JLabel cityLabel;
    private JLabel addressLabel;
    private JButton nextButton;
    private JPanel PanelContainer;
    private CardLayout cl = (CardLayout)panelContainer.getLayout();

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
                cl.show(panelContainer, "Card2");
            }
        });
    }
}
