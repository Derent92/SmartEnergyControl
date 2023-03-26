import swt6.modular.ac.AirConditionApi;
import swt6.modular.beans.TimerProvider;
import swt6.modular.plant.InverterApi;

module swt.modular.client {
  requires swt.modular.beans;
  uses TimerProvider;

  requires swt.modular.plant;
  uses InverterApi;

  requires swt.modular.ac;
  uses AirConditionApi;
}