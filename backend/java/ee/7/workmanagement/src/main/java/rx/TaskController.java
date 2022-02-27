package rx;

import java.time.ZonedDateTime;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TaskController {
  @Inject	
  private Event<StartTime> startEvent;
  @Inject	
  private Event<TimeMeasurement> measureTimeEvent;
  public void start() {
	  startEvent.fire(new StartTime(ZonedDateTime.now()));
  }
  public void measure() {
	  measureTimeEvent.fire(new TimeMeasurement());
  }
}
