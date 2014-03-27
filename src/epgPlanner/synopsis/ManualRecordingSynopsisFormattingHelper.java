package epgPlanner.synopsis;

import static epgPlanner.synopsis.SynopsisFormattingUtils.getDateText;
import static epgPlanner.synopsis.SynopsisFormattingUtils.getDurationInMinutes;
import static epgPlanner.synopsis.SynopsisFormattingUtils.getFormattedDateTime;
import static epgPlanner.synopsis.SynopsisFormattingUtils.getTimeText;
import epgPlanner.ManualRecording;
import epgPlanner.Recording.Status;

public class ManualRecordingSynopsisFormattingHelper {
	private final ManualRecording srcRecording;

	public ManualRecordingSynopsisFormattingHelper(ManualRecording recording) {
		this.srcRecording = recording;
	}

	public String getDescription() {
		return this.srcRecording.getProgrammeTitle() + ("\n") + srcRecording.getSummary();
	}

	public String getStatusSummary() {
		StringBuilder status = new StringBuilder(this.srcRecording.getStatus().defaultText());

		if (this.srcRecording.getStatus() == Status.RECORDED) {
			// format second page of recorded manual recording
			status.append(getFormattedDateTime(this.srcRecording.getDateOfRecording())).append(" (");
			status.append(getDurationInMinutes(this.srcRecording.getDurationInSeconds())).append("mins)");
		} else {
			// format second page of unrecorded manual recording
			status.append("\n").append(getDateText(this.srcRecording.getStartTime()));
			status.append(" ").append(getTimeText(this.srcRecording.getStartTime()));
			status.append(" - ").append(getTimeText(this.srcRecording.getEndTime()));
			status.append(", ").append(getRecurrence(this.srcRecording));
		}

		return status.toString();
	}

	private String getRecurrence(ManualRecording recording) {
		return recording.getRecurrence() == null ? "None" : recording.getRecurrence().defaultText();
	}
}
