package swt6.modular.beans;

public interface Timer {
  void start();

  void stop();

  boolean isRunning();

  int getInterval();

  int getNumTicks();

  void addTimerListener(TimerListener listener);

  void removeTimerListener(TimerListener listener);
}
