package epgPlanner.synopsis;

import static epgPlanner.synopsis.SynopsisFormattingUtils.getDurationInMinutes;
import static epgPlanner.synopsis.SynopsisFormattingUtils.getFormattedDateTime;
import epgPlanner.Recording;
import epgPlanner.Recording.Rating;
import epgPlanner.Recording.Status;
import epgPlanner.ScheduledRecording;

public class ScheduledRecordingSynopsisFormattingHelper {
	private final ScheduledRecording srcRecording;

	public ScheduledRecordingSynopsisFormattingHelper(ScheduledRecording recording) {
		this.srcRecording = recording;
	}

	public String getDescription() {
		StringBuilder desc = new StringBuilder(this.srcRecording.getProgrammeTitle());
		if (this.srcRecording.getRating() != null && this.srcRecording.getRating() != Rating.NONE) {
			desc.append(" ").append(getRatingString(this.srcRecording));
		}
		desc.append("\n");
		desc.append(this.srcRecording.getSummary());
		desc.append(getSeriesLink(this.srcRecording));

		return desc.toString();
	}

	public String getStatusSummary() {
		StringBuilder status = new StringBuilder(this.srcRecording.getStatus().defaultText());

		if (this.srcRecording.getStatus() == Status.RECORDED) {
			status.append(getFormattedDateTime(this.srcRecording.getDateOfRecording())).append(" (");
			status.append(getDurationInMinutes(this.srcRecording.getDurationInSeconds())).append("mins)");
		}

		return status.toString();
	}

	private String getSeriesLink(ScheduledRecording recording) {
		if (!recording.isSeriesLinkable()) {
			return "";
		} else {
			return " [S]";
		}
	}

	private String getRatingString(Recording recording) {
		return recording.getRating() == null ? "no rating" : recording.getRating().defaultLabel();
	}

}
