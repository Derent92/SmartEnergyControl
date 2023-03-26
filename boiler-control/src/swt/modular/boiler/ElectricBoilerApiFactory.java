package swt.modular.boiler;

public class ElectricBoilerApiFactory {
  public static ElectricBoilerApi createElectricBoilerApi(){
    return new SimpleElectricBoilerApi();
  }
}
