package observer_2;

public class WeatherStation {
	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentCoditionDisplay currentCoditionDisplay = new CurrentCoditionDisplay(weatherData);
		
		weatherData.setMeasurements(123, 456, 789);
	}
}
