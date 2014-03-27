package epgPlanner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ManualRecording extends Recording {

    final static String NO_SYNOPSIS_AVAILABLE = "Programme synopsis is not available";

    public interface Recurrence {
        int ONCE = 0;
        int DAILY = 1;
        int WEEKLY = 2;
    }

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("h:mma dd/MM");

	private final int recurrence;
	private final Calendar startTime;
	private final Calendar endTime;

	public ManualRecording(Calendar startTime, Calendar endTime, int rating, Status status,
			Calendar dateOfRecording, int recurrence) {

		super(dateFormat.format(startTime.getTime()), NO_SYNOPSIS_AVAILABLE, calculateDurationInSeconds(startTime,
				endTime), rating, status, dateOfRecording);
		this.startTime = startTime;
		this.endTime = endTime;
		this.recurrence = recurrence;
	}

	private static int calculateDurationInSeconds(Calendar startTime, Calendar endTime) {
		return (int) ((endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000);
	}

	int getRecurrence() {
		return recurrence;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

}
