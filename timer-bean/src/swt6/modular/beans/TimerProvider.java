package swt6.modular.beans;

public interface TimerProvider {
  Timer createTimer(int interval, int numTicks);
  double getResolution();
}
