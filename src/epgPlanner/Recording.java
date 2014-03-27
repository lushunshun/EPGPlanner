package epgPlanner;

import java.util.Calendar;

public abstract class Recording {

	public interface Rating {
		int NONE = 0;
        int UNIVERSAL = 1;
        int PARENTAL_GUIDANCE = 2;
        int OVER_12 = 3;
        int OVER_15 = 4;
        int ADULT = 5;
	}

	public enum Status {
		BOOKED, RECORDING, RECORDED
	}

	private final String programmeTitle;
	private final String summary;
	protected final int durationInSeconds;
	private final int rating;
	private final Status status;
	private final Calendar dateOfRecording;

	public Recording(String programmeTitle, String summary, int durationInSeconds, int rating, Status status,
			Calendar dateOfRecording) {
		this.programmeTitle = programmeTitle;
		this.summary = summary;
		this.durationInSeconds = durationInSeconds;
		this.rating = rating;
		this.status = status;
		this.dateOfRecording = dateOfRecording;
	}

	public String getProgrammeTitle() {
		return programmeTitle;
	}

	public String getSummary() {
		return summary;
	}

	public int getDurationInSeconds() {
		return durationInSeconds;
	}

    public int getRating() {
		return rating;
	}

	public Status getStatus() {
		return status;
	}

	public Calendar getDateOfRecording() {
		return dateOfRecording;
	}

}
