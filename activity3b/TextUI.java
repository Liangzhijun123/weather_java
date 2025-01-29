public class TextUI implements TemperatureDisplay {
    @Override
    public void updateWeather(double celsius, double kelvin, double fahrenheit, double pressureInches,
            double pressureMillibars) {
        System.out.printf(
                "Current Weather:\n" +
                        "Celsius: %.2f°C\n" +
                        "Kelvin: %.2fK\n" +
                        "Fahrenheit: %.2f°F\n" +
                        "Pressure (in): %.2f\n" +
                        "Pressure (mbar): %.2f\n",
                celsius, kelvin, fahrenheit, pressureInches, pressureMillibars);
    }
}
