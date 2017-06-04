package observer_2;

import java.util.ArrayList;

/**
 * 气象局数据类
 * 
 * 被观察者
 * 
 * @author liyang
 *
 */

public class WeatherData implements Subject{
	private ArrayList<Observer> mObservers;
	private float temperature;
	private float humidity;
	private float pressure;
	
	public WeatherData() {
		mObservers = new ArrayList<>();
	}

	@Override
	public void registerObserver(Observer oberver) {
		mObservers.add(oberver);
	}

	@Override
	public void removeObserver(Observer observer) {
		int i = mObservers.indexOf(observer);
		if (i >= 0) {
			mObservers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer oberver : mObservers) {
			oberver.update(temperature, humidity, pressure);
		}
	}
	
	public void measurementsChanged() {
		notifyObservers();
	}
	
	public void setMeasurements(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		measurementsChanged();
	}
}
