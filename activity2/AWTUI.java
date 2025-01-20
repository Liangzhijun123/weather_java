/*
 * Initial Author
 *      Michael J. Lutz
 *
 * Other Contributers
 * Zhi Jun Liang
 *
 * Acknowledgements
 * None
 */
 
/*
 * AWT UI class used for displaying the information from the
 * associated weather station object.
 * This is an extension of JFrame, the outermost container in
 * a AWT application.
 */

 import java.awt.*;
import java.awt.event.*;

public class AWTUI extends Frame implements TemperatureDisplay {
    private Label celsiusField;
    private Label kelvinField;

    private static Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    public AWTUI() {
        super("Weather Station");

        setLayout(new GridLayout(1, 0));

        Panel panel = new Panel(new GridLayout(2, 1));
        add(panel);
        setLabel(" Kelvin ", panel);
        kelvinField = setLabel("", panel);

        panel = new Panel(new GridLayout(2, 1));
        add(panel);
        setLabel(" Celsius ", panel);
        celsiusField = setLabel("", panel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
    }

    private Label setLabel(String title, Panel panel) {
        Label label = new Label(title);
        label.setAlignment(Label.CENTER);
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

 