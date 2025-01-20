public class TextUI implements TemperatureDisplay {

    @Override
    public void updateTemperatures(double celsius, double kelvin) {
        System.out.printf("Current Temperature: %.2f°C and %.2fK%n", celsius, kelvin);
    }
}
