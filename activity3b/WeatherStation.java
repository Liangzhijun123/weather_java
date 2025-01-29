import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Runnable {
    private final KelvinTempSensor tempSensor; // Temperature sensor.
    private final long PERIOD = 1000; // 1 sec = 1000 ms.

    private final Barometer barometer;
    private List<TemperatureDisplay> observers;

    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object it will use and initializes the observer list.
     */
    public WeatherStation() {
        tempSensor = new KelvinTempSensor();
        barometer = new Barometer();
        observers = new ArrayList<>();
    }

    public synchronized void addObserver(TemperatureDisplay display) {
        observers.add(display);
    }

    public synchronized void removeObserver(TemperatureDisplay observer) {
        observers.remove(observer);
    }

    public double getTemperatureFahrenheit() {
        return (tempSensor.reading() / 100.0 - 273.15) * 9 / 5 + 32;
    }

    public double getPressureInInches() {
        return barometer.pressure();
    }

    public double getPressureInMillibars() {
        return barometer.pressure() * 33.864;
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
        double fahrenheit;
        double pressureInches;
        double pressureMillibars;

        final int KTOC = -27315; // Convert raw Kelvin reading to Celsius.

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.err.println(e);
            }

            reading = tempSensor.reading();
            celsius = (reading + KTOC) / 100.0;
            kelvin = reading / 100.0;
            fahrenheit = getTemperatureFahrenheit();
            pressureInches = getPressureInInches();
            pressureMillibars = getPressureInMillibars();

            // Notify all observers with the current temperature.
            notifyObservers(celsius, kelvin, fahrenheit, pressureInches, pressureMillibars);
        }
    }

    private synchronized void notifyObservers(double celsius, double kelvin, double fahrenheit,
            double pressureInches, double pressureMillibars) {
        for (TemperatureDisplay observer : observers) {
            observer.updateWeather(celsius, kelvin, fahrenheit, pressureInches, pressureMillibars);
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
