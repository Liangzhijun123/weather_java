public class WeatherApp {

    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        SwingUI ui = new SwingUI(station);

        station = new WeatherStation();

        Thread thread = new Thread(station);
        thread.start();
    }
}
