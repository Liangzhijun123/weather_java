import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Runnable {
    private final KelvinTempSensor sensor; // Temperature sensor.
    private final long PERIOD = 1000; // 1 sec = 1000 ms.
    private List<TemperatureDisplay> observers; // List to hold observers (displays).

    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object it will use and initializes the observer list.
     */
    public WeatherStation() {
        sensor = new KelvinTempSensor();
        observers = new ArrayList<>();
    }

    public synchronized void addObserver(TemperatureDisplay observer) {
        observers.add(observer);
    }

    public synchronized void removeObserver(TemperatureDisplay observer) {
        observers.remove(observer);
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from
     * its sensor, and reports this as a formatted output string.
     */
    public void run() {
        int reading; // Actual sensor reading.
        double celsius; // Sensor reading transformed to Celsius.
        double kelvin; // Sensor reading in Kelvin.

        final int KTOC = -27315; // Convert raw Kelvin reading to Celsius.

        while (true) {
            try {
                Thread.sleep(PERIOD);
            } catch (Exception e) {
                // Ignore exceptions
            }

            reading = sensor.reading();
            celsius = (reading + KTOC) / 100.0;
            kelvin = reading / 100.0;

            // Notify all observers with the current temperature.
            notifyObservers(celsius, kelvin);
        }
    }

    private synchronized void notifyObservers(double celsius, double kelvin) {
        for (TemperatureDisplay observer : observers) {
            observer.updateTemperatures(celsius, kelvin);
        }
    }

    /*
     * Initial main method.
     * Create the WeatherStation (Runnable).
     * Embed the WeatherStation in a Thread.
     * Start the Thread.
     */
    public static void main(String[] args) {
        System.out.println("Choose UI option: 1) AWT 2) Swing 3) Command Line");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice = scanner.nextInt();

        TemperatureDisplay display = null;
        switch (choice) {
            case 1:
                display = new AWTUI();
                break;
            case 2:
                display = new SwingUI(new WeatherStation());
                break;
            case 3:
                display = new TextUI();
                break;
            default:
                System.out.println("Invalid choice! Exiting.");
                System.exit(1);
        }

        WeatherStation ws = new WeatherStation();
        ws.addObserver(display);
        Thread thread = new Thread(ws);
        thread.start();
    }

}
