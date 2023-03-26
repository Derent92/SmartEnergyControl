import swt6.modular.ac.AirConditionApi;
import swt6.modular.ac.SimpleAirConditionApi;

module swt.modular.ac {
  exports swt6.modular.ac;
  provides AirConditionApi with SimpleAirConditionApi;
}