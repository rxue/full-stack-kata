package rx;

import java.time.ZonedDateTime;

public class StartTime {
	private final ZonedDateTime startTime;
	public StartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}
	public ZonedDateTime getStartTime() {
		return startTime;
	}
}
