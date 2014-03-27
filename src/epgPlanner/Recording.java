package epgPlanner;

import java.util.Calendar;

public abstract class Recording {

	// public interface Rating {
	// int NONE = 0;
	// int UNIVERSAL = 1;
	// int PARENTAL_GUIDANCE = 2;
	// int OVER_12 = 3;
	// int OVER_15 = 4;
	// int ADULT = 5;
	// }

	public enum Rating {
		NONE(0, ""), UNIVERSAL(1, "U"), PARENTAL_GUIDANCE(2, "PG"), OVER_12(3, "12"), OVER_15(4, "15"), ADULT(5, "18");

		private int value;
		private String defaultLabel;

		private Rating(int value, String defaultLabel) {
			this.value = value;
			this.defaultLabel = defaultLabel;
		}

		public int getValue() {
			return this.value;
		}

		public String defaultLabel() {
			return this.defaultLabel;
		}
	}

	public enum Status {
		BOOKED("To be recorded"), RECORDING("Recording"), RECORDED("Recorded: ");

		private String defaultText;

		private Status(String defaultText) {
			this.defaultText = defaultText;
		}

		public String defaultText() {
			return this.defaultText;
		}
	}

	private final String programmeTitle;
	private final String summary;
	protected final int durationInSeconds;
	private final Rating rating;
	private final Status status;
	private final Calendar dateOfRecording;

	public Recording(String programmeTitle, String summary, int durationInSeconds, Rating rating, Status status,
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

	public Rating getRating() {
		return rating;
	}

	public Status getStatus() {
		return status;
	}

	public Calendar getDateOfRecording() {
		return dateOfRecording;
	}

}
