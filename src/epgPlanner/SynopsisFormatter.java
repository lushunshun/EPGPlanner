package epgPlanner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import epgPlanner.Recording.Status;

@Deprecated
public class SynopsisFormatter {

	public String firstPageOfSynopsis;
	public String secondPageOfSynopsis;

	public SynopsisFormatter(Recording recording) {
		if (recording instanceof ScheduledRecording) {
			// Format synopsis for scheduled recording
			ScheduledRecording scheduledRecording = (ScheduledRecording) recording;
			firstPageOfSynopsis = (scheduledRecording.getProgrammeTitle()) + " "
					+ (getRatingString(scheduledRecording)) + ("\n") + (scheduledRecording.getSummary())
					+ (getSeriesLink(scheduledRecording));

			secondPageOfSynopsis = getStatusForRecording(scheduledRecording);

			if (scheduledRecording.getStatus().equals(Status.RECORDED)) {
				secondPageOfSynopsis = secondPageOfSynopsis + (getFormattedRecordingTime(scheduledRecording)) + (" (")
						+ (getDurationInMinutes(scheduledRecording)) + ("mins)");
			}
		} else if (recording instanceof ManualRecording) {
			// Format synopsis for manual recording
			ManualRecording manualRecording = (ManualRecording) recording;
			firstPageOfSynopsis = (manualRecording.getProgrammeTitle()) + ("\n")
					+ (ManualRecording.NO_SYNOPSIS_AVAILABLE);

			if (manualRecording.getStatus().equals(Status.RECORDED)) {
				// format second page of recorded manual recording
				secondPageOfSynopsis = getStatusForRecording(manualRecording)
						+ getFormattedRecordingTime(manualRecording) + " (" + getDurationInMinutes(manualRecording)
						+ "mins)";
			} else {
				// format second page of unrecorded manual recording
				secondPageOfSynopsis = getStatusForRecording(manualRecording) + "\n"
						+ getStartDateOfManualRecording(manualRecording) + " "
						+ getTimeOfManualRecording(manualRecording.getStartTime()) + " - "
						+ getTimeOfManualRecording(manualRecording.getEndTime()) + ", "
						+ getRecurrence(manualRecording);
			}
		}
	}

	public String getFirstPageOfSynopsis() {
		return firstPageOfSynopsis;
	}

	public String getSecondPageOfSynopsis() {
		return secondPageOfSynopsis;
	}

	private String getRecurrence(ManualRecording recording) {
		return recording.getRecurrence() == null ? "None" : recording.getRecurrence().defaultText();
	}

	private String getStatusForRecording(Recording recording) {
		String result = "";
		Status status = recording.getStatus();
		switch (status) {
		case BOOKED:
			result = "To be recorded";
			break;
		case RECORDING:
			result = "Recording";
			break;
		case RECORDED:
			result = "Recorded: ";
			break;
		default:
			result = "default";
			break;
		}
		return result;
	}

	private String getTimeOfManualRecording(Calendar time) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("h:mma");

		return timeFormat.format(time.getTime());
	}

	private String getStartDateOfManualRecording(ManualRecording recording) {
		SimpleDateFormat dayMonthFormat = new SimpleDateFormat("dd/MM");

		return dayMonthFormat.format(recording.getStartTime().getTime());
	}

	private int getDurationInMinutes(Recording recording) {
		return recording.getDurationInSeconds() / 60;
	}

	private String getRatingString(Recording recording) {
		return recording.getRating() == null ? "no rating" : recording.getRating().defaultLabel();
	}

	private String getSeriesLink(ScheduledRecording recording) {
		if (!recording.isSeriesLinkable()) {
			return "";
		} else {
			return " [S]";
		}
	}

	private String getFormattedRecordingTime(Recording recording) {
		SimpleDateFormat recordingTimeDateFormat = new SimpleDateFormat("h:mma EEE dd/MM/yy");

		return recordingTimeDateFormat.format(recording.getDateOfRecording().getTime());
	}

}
