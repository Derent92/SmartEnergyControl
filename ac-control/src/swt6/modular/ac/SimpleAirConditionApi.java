package swt6.modular.ac;

public class SimpleAirConditionApi implements AirConditionApi{
  private boolean AirConditionRunning;

  private final double minPower = 19.0;
  private final double maxPower = 30.8;

  @Override
  public void turnOn() {
    if(AirConditionRunning == false) {
      AirConditionRunning = true;
      System.out.println("Air Condition turned on");
    }
  }

  @Override
  public void turnOff() {
    if(AirConditionRunning == true) {
      AirConditionRunning = false;
      System.out.println("Air Condition turned off");
    }
  }

  @Override
  public double getRoomTemperature() {
    return (Math.random() * (maxPower - minPower)) + minPower;
  }
}
