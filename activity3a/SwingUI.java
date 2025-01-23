import javax.swing.*;
import java.awt.*;

public class SwingUI extends JFrame implements TemperatureDisplay {
    private JLabel celsiusField;
    private JLabel kelvinField;
    private WeatherStation weatherStation;

    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    /*
     * Create and populate the SwingUI JFrame with panels and labels to
     * show the temperatures.
     */
    public SwingUI(WeatherStation weatherStation) {
        super("Weather Station");

        this.weatherStation = weatherStation;

        // Register this SwingUI as an observer
        weatherStation.addObserver(this);

        this.setLayout(new GridLayout(1, 0));

        JPanel panel;

        // Set up Kelvin display.
        panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(" Kelvin ", panel);
        kelvinField = createLabel("", panel);

        // Set up Celsius display.
        panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(" Celsius ", panel);
        celsiusField = createLabel("", panel);

        // Set up the frame's default close operation, pack its elements,
        // and make the frame visible.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /*
     * Set the label holding the Kelvin temperature.
     */
    public void setKelvinJLabel(double temperature) {
        kelvinField.setText(String.format("%6.2f", temperature));
    }

    /*
     * Set the label holding the Celsius temperature.
     */
    public void setCelsiusJLabel(double temperature) {
        celsiusField.setText(String.format("%6.2f", temperature));
    }

    /*
     * Create a Label with the initial value <title>, place it in
     * the specified <panel>, and return a reference to the Label
     * in case the caller wants to remember it.
     */
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
        // Update the labels when new temperature data arrives
        celsiusField.setText(String.format("%6.2f", celsius));
        kelvinField.setText(String.format("%6.2f", kelvin));
    }
}
