import javax.swing.*;
import java.awt.*;

public class SwingUI extends JFrame implements TemperatureDisplay {
    private JLabel celsiusField; // put current celsius reading here
    private JLabel kelvinField; // put current kelvin reading here

    /*
     * A Font object contains information on the font to be used to
     * render text.
     */
    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    /*
     * Create and populate the SwingUI JFrame with panels and labels to
     * show the temperatures.
     */
    public SwingUI() {
        super("Weather Station");

        /*
         * WeatherStation frame is a grid of 1 row by an indefinite
         * number of columns.
         */
        this.setLayout(new GridLayout(1, 0));

        /*
         * There are two panels, one each for Kelvin and Celsius, added to the
         * frame. Each Panel is a 2 row by 1 column grid, with the temperature
         * name in the first row and the temperature itself in the second row.
         */
        JPanel panel;

        /*
         * Set up Kelvin display.
         */
        panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(" Kelvin ", panel);
        kelvinField = createLabel("", panel);

        /*
         * Set up Celsius display.
         */
        panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(" Celsius ", panel);
        celsiusField = createLabel("", panel);

        /*
         * Set up the frame's default close operation pack its elements,
         * and make the frame visible.
         */
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
        celsiusField.setText(String.format("%6.2f", celsius));
        kelvinField.setText(String.format("%6.2f", kelvin));
    }
}
