package epgPlanner.synopsis;

import epgPlanner.ManualRecording;

public class ManualRecordingSynopsisProducer extends RevertableSynopsisProducer<String> {
	private final ManualRecording srcRecording;
	private ManualRecordingSynopsisFormattingHelper formattingHelper;

	public ManualRecordingSynopsisProducer(ManualRecording srcRecording) {
		super();
		this.srcRecording = srcRecording;
		this.initSynopses();
	}

	private void initSynopses() {
		this.synopses.add(this.getFormattingHelper().getDescription());
		this.synopses.add(this.getFormattingHelper().getStatusSummary());
	}

	public void setFormattingHelper(ManualRecordingSynopsisFormattingHelper formattingHelper) {
		this.formattingHelper = formattingHelper;
	}

	private ManualRecordingSynopsisFormattingHelper getFormattingHelper() {
		if (this.formattingHelper == null) {
			this.formattingHelper = new ManualRecordingSynopsisFormattingHelper(this.srcRecording);
		}

		return this.formattingHelper;
	}
}
