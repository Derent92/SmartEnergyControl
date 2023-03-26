package swt6.modular.client;

import swt6.modular.ac.AirConditionApi;
import swt6.modular.ac.AirConditionFactory;
import swt6.modular.beans.Timer;
import swt6.modular.beans.TimerProvider;
import swt6.modular.plant.InverterApi;
import swt6.modular.plant.InverterApiFactory;

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

  private static InverterApi getInverterApi(){
    return InverterApiFactory.createInverterApi();
  }

  public static void main(String[] args) throws Exception {
    var timer = getTimerWithHighestPrecision(5000, 1000);
    var ac = getAirConditionApi();
    var powerPlant = getInverterApi();

    LinkedList<Double> temperatures = new LinkedList<Double>();
    LinkedList<Double> powerAvgs = new LinkedList<Double>();

    timer.addTimerListener(e -> { temperatures.addFirst(ac.getRoomTemperature());
                                  powerAvgs.addFirst(powerPlant.getActualCurrent());
    });

    timer.start();

    while (timer.isRunning()) {
      if (temperatures.size() > 10) {
        temperatures.removeLast();
        if(powerAvgs.size() > 10){
          powerAvgs.removeLast();
        }

        if (getMedium(temperatures) < 22) {
          ac.turnOff();
        } else if( getMedium(temperatures) > 24 && getMedium(powerAvgs) > 0.1 ){
          ac.turnOn();
        }
      }
    }
    timer.stop();
  }

  private static double getMedium(LinkedList<Double> impList) {
    double sum = 0;
    for ( int i = 0; i < impList.size(); i++) {
      sum += impList.get(i);
    }
    return sum / impList.size();
  }
}
