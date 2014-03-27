package epgPlanner;

import java.util.Calendar;

public class ScheduledRecording extends Recording {

	private final boolean isSeriesLinkable;

	public ScheduledRecording(String programmeTitle, String summary, int durationInSeconds, int rating,
			Status status, boolean isSeriesLinkable, Calendar dateOfRecording) {

		super(programmeTitle, summary, durationInSeconds, rating, status, dateOfRecording);
		this.isSeriesLinkable = isSeriesLinkable;
	}

    public boolean isSeriesLinkable() {
		return isSeriesLinkable;
	}
}
