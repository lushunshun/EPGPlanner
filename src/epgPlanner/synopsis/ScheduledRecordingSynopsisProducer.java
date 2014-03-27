package epgPlanner.synopsis;

import epgPlanner.ScheduledRecording;

public class ScheduledRecordingSynopsisProducer extends RevertableSynopsisProducer<String> {
	private final ScheduledRecording srcRecording;
	private ScheduledRecordingSynopsisFormattingHelper formattingHelper;

	public ScheduledRecordingSynopsisProducer(ScheduledRecording srcRecording) {
		super();
		this.srcRecording = srcRecording;
		this.initSynopses();
	}

	private void initSynopses() {
		this.synopses.add(this.getFormattingHelper().getDescription());
		this.synopses.add(this.getFormattingHelper().getStatusSummary());
	}

	public void setFormattingHelper(ScheduledRecordingSynopsisFormattingHelper formattingHelper) {
		this.formattingHelper = formattingHelper;
	}

	private ScheduledRecordingSynopsisFormattingHelper getFormattingHelper() {
		if (this.formattingHelper == null) {
			this.formattingHelper = new ScheduledRecordingSynopsisFormattingHelper(this.srcRecording);
		}

		return this.formattingHelper;
	}
}
