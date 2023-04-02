package rx;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SessionScoped
@Stateful
public class TaskListener {
	private ZonedDateTime startedAt;
	public void onStartAlways(@Observes StartTime startTime) {
		startedAt = startTime.getStartTime();
	}
	public void measure(@Observes(notifyObserver=Reception.IF_EXISTS) TimeMeasurement startTime) {
		ChronoUnit unit = ChronoUnit.MICROS;
		long length = ChronoUnit.MICROS.between(ZonedDateTime.now(), startedAt);
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Measured Time Length is " + length + " micro-seconds since " + startedAt , 
				"Measure Time Length");
		context.addMessage(null, m);
		System.out.println("If Exists, STARTED" + unit.between(ZonedDateTime.now(), startedAt));
	}
}
