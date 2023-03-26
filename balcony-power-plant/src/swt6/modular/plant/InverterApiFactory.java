package swt6.modular.plant;

public class InverterApiFactory {
  public static InverterApi createInverterApi(){
    return new SimpleInverter();
  }
}
