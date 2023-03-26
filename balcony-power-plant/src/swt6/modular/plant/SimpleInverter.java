package swt6.modular.plant;

public class SimpleInverter implements InverterApi{

  private final double minPower = 0.000;
  private final double maxPower = 0.800;

  @Override
  public double getActualCurrent() {
    return (Math.random() * (maxPower - minPower)) + minPower;
  }
}
