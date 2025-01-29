import javax.swing.*;
import java.awt.*;

public class SwingUI extends JFrame implements TemperatureDisplay {
    private JLabel celsiusField;
    private JLabel kelvinField;
    private JLabel fahrenheitField;
    private JLabel pressureInchesField;
    private JLabel pressureMillibarsField;
    private WeatherStation weatherStation;

    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, 24);

    /*
     * Create and populate the SwingUI JFrame with panels and labels to
     * show the temperatures.
     */
    public SwingUI(WeatherStation weatherStation) {
        super("Weather Station");

        this.weatherStation = weatherStation;

        weatherStation.addObserver(this);

        this.setLayout(new GridLayout(1, 0));

        JPanel panel;

        // Set up Celsius display.
        panel = new JPanel(new GridLayout(1, 0));
        this.add(panel);

        celsiusField = createLabel("Celsius", panel);
        kelvinField = createLabel("Kelvin", panel);
        fahrenheitField = createLabel(" Fahrenheit: ", panel);
        pressureInchesField = createLabel(" Pressure (in): ", panel);
        pressureMillibarsField = createLabel(" Pressure (mbar): ", panel);

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
    public void updateWeather(double celsius, double kelvin, double fahrenheit,
            double pressureInches, double pressureMillibars) {
        celsiusField.setText(String.format(" Celsius: %.2f°C", celsius));
        kelvinField.setText(String.format(" Kelvin: %.2fK", kelvin));
        fahrenheitField.setText(String.format(" Fahrenheit: %.2f°F", fahrenheit));
        pressureInchesField.setText(String.format(" Pressure (in): %.2f", pressureInches));
        pressureMillibarsField.setText(String.format(" Pressure (mbar): %.2f", pressureMillibars));
    }
}
