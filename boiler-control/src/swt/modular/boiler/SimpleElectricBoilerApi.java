package swt.modular.boiler;

public class SimpleElectricBoilerApi implements ElectricBoilerApi {

  private final double minPower = 0.0;
  private final double maxPower = 100.0;

  @Override
  public void turnOn() {
    System.out.println("Boiler turned on!");
  }

  @Override
  public double getBoilerTemperature() {
    return (Math.random() * (maxPower - minPower)) + minPower;
  }
}
