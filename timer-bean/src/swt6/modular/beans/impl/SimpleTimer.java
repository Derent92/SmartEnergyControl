package swt6.modular.beans.impl;

import swt6.modular.beans.Timer;
import swt6.modular.beans.TimerEvent;
import swt6.modular.beans.TimerListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleTimer implements Timer {

  private final int interval;
  private final int numTicks;
  private final List<TimerListener> listeners = new CopyOnWriteArrayList<>();
  private Thread tickerThread;

  public SimpleTimer(int interval, int numTicks){
    this.interval = interval;
    this.numTicks = numTicks;
  }

  @Override
  public synchronized void start(){
    if (isRunning()){
      return;
    }

    final int numTicks = this.getNumTicks();
    final int interval = this.getInterval();
    tickerThread = new Thread(() ->{
      // THREAD START
      int tickCount = 0;
      while (tickCount < numTicks && !Thread.currentThread().isInterrupted()){
        try {
          Thread.sleep(interval);
        } catch (InterruptedException e) {
          // restart interruption flag
          Thread.currentThread().interrupt();
        }
        fireEvent(new TimerEvent(this, tickCount, numTicks));
        tickCount++;
      }
      synchronized (this) {
        tickerThread = null;
      }
      // THREAD STOP
    });
    tickerThread.start();
  }

  private void fireEvent(TimerEvent event) {
    listeners.forEach(listener -> {
      listener.expired(event);
    });
  }

  @Override
  public synchronized void stop(){
    if (tickerThread != null) {
      tickerThread.interrupt();
      tickerThread = null;
    }
  }

  @Override
  public synchronized boolean isRunning(){return tickerThread != null;}


  @Override
  public int getInterval(){return interval;}

  @Override
  public int getNumTicks(){return numTicks;}


  @Override
  public void addTimerListener(TimerListener listener){
    listeners.add(listener);
  }

  @Override
  public void removeTimerListener(TimerListener listener){
    listeners.remove(listener);
  }




}
