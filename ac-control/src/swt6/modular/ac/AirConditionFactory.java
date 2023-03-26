package swt6.modular.ac;

public class AirConditionFactory {
  public static AirConditionApi createAirConditionApi(){
    return new SimpleAirConditionApi();
  }
}
