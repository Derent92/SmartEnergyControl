import swt.modular.boiler.ElectricBoilerApi;
import swt.modular.boiler.SimpleElectricBoilerApi;

module swt.modular.boiler {
  exports swt.modular.boiler;
  provides ElectricBoilerApi with SimpleElectricBoilerApi;
}