/*
 * Class for a simple computer-based weather station that reports the current
 * temperature (in Celsius and Kelvin) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * Updated by: Zhi Jun Liang
 * Date: January 14, 2025
 */

 public class WeatherStation implements Runnable {

    private final KelvinTempSensor sensor; // Temperature sensor.
    private final long PERIOD = 1000;      // 1 sec = 1000 ms.

    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object it will use.
     */
    public WeatherStation() {
        sensor = new KelvinTempSensor();
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from
     * its sensor, and reports this as a formatted output string.
     */
    public void run() {
        int reading;            // Actual sensor reading.
        double celsius;         // Sensor reading transformed to Celsius.
        double kelvin;          // Sensor reading in Kelvin.

        final int KTOC = -27315;   // Convert raw Kelvin reading to Celsius.

        while (true) {
            try {
                Thread.sleep(PERIOD);
            } catch (Exception e) {
                // Ignore exceptions
            }

            reading = sensor.reading();
            celsius = (reading + KTOC) / 100.0;
            kelvin = reading / 100.0;

            /*
             * Print both Celsius and Kelvin temperatures.
             */
            System.out.printf("Reading is %6.2f degrees C and %6.2f degrees K%n", celsius, kelvin);
        }
    }

    /*
     * Initial main method.
     *      Create the WeatherStation (Runnable).
     *      Embed the WeatherStation in a Thread.
     *      Start the Thread.
     */
    public static void main(String[] args) {
        WeatherStation ws = new WeatherStation();
        Thread thread = new Thread(ws);
        thread.start();
    }
}
