import swt6.modular.plant.SimpleInverter;
import swt6.modular.plant.InverterApi;

module swt.modular.plant {
  exports swt6.modular.plant;
  provides InverterApi with SimpleInverter;
}