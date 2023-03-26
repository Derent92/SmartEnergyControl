package swt6.modular.client;

import swt6.modular.ac.AirConditionApi;
import swt6.modular.ac.AirConditionFactory;
import swt6.modular.beans.Timer;
import swt6.modular.beans.TimerProvider;

import java.util.LinkedList;
import java.util.ServiceLoader;

public class HomeControlClient {

  private static Timer getTimerWithHighestPrecision(int tickInterval, int nrOfTicks) {
    var timerProviders = ServiceLoader.load(TimerProvider.class);

    double minResolution = Double.MAX_VALUE;
    TimerProvider bestProvider = null;
    for (TimerProvider provider : timerProviders) {
      if (provider.getResolution() < minResolution) {
        bestProvider   = provider;
        minResolution = bestProvider.getResolution();
      }
    }

    return bestProvider == null ? null : bestProvider.createTimer(tickInterval, nrOfTicks);
  }

  private static AirConditionApi getAirConditionApi(){
    return AirConditionFactory.createAirConditionApi();
  }

  public static void main(String[] args) throws Exception {
    var timer = getTimerWithHighestPrecision(5000, 1000);
    var ac = getAirConditionApi();

    LinkedList<Double> temperatures = new LinkedList<Double>();

    timer.addTimerListener(e -> temperatures.addFirst(ac.getRoomTemperature()));

    timer.start();

    while (timer.isRunning()) {
      if (temperatures.size() > 10) {
        temperatures.removeLast();

        if (getMediumTemperature(temperatures) > 22) {
          ac.turnOn();
        } else {
          ac.turnOff();
        }
      }
    }
    timer.stop();
  }

  private static double getMediumTemperature(LinkedList<Double> temperatures) {
    double temperatureSum = 0;
    for ( int i = 0; i < temperatures.size(); i++) {
      temperatureSum += temperatures.get(i);
    }
    return temperatureSum / temperatures.size();
  }
}
