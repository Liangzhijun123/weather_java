import javax.swing.*;
import java.awt.*;

public class SwingUI extends JFrame implements TemperatureDisplay {
    private JLabel celsiusField;
    private JLabel kelvinField;

    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    public SwingUI() {
        super("Weather Station");

        setLayout(new GridLayout(1, 2));  // 1 row, 2 columns

        JPanel panel;

        // Set up Kelvin display
        panel = new JPanel(new GridLayout(2, 1));  // 2 rows, 1 column
        add(panel);
        createLabel(" Kelvin ", panel);
        kelvinField = createLabel("", panel);

        // Set up Celsius display
        panel = new JPanel(new GridLayout(2, 1));  // 2 rows, 1 column
        add(panel);
        createLabel(" Celsius ", panel);
        celsiusField = createLabel("", panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);  // Explicit window size
        setVisible(true);
    }

    private JLabel createLabel(String title, JPanel panel) {
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(labelFont);
        panel.add(label);
        return label;
    }

    @Override
    public void updateTemperatures(double celsius, double kelvin) {
        celsiusField.setText(String.format("%6.2f", celsius));
        kelvinField.setText(String.format("%6.2f", kelvin));
    }
}
