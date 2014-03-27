package epgPlanner;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import epgPlanner.Recording.Rating;
import epgPlanner.Recording.Status;

public class TestSynopsisStringForScheduledRecordings {

    private static final String TITLE = "News at One";
    private static final String SUMMARY = "The latest national and international news stories";

    @Test
	public void testSynopsisForProgrammeWhichHasBeenRecorded() throws Exception {
        Status status = Status.RECORDED;

		Recording scheduledRecording = createScheduledRecording(status);

		String expectedSecondPageOfScheduledRecordingSynopsis = "Recorded: " + "1:00PM Tue 16/10/12 (24mins)";

		verifyExpectedSynopsis(createFirstPageOfSynopsisForScheduledRecording(),
				expectedSecondPageOfScheduledRecordingSynopsis, scheduledRecording);
	}

	@Test
	public void testSynopsisForProgrammeWhichIsCurrentlyRecording() throws Exception {
		Status status = Status.RECORDING;

		Recording scheduledRecording = createScheduledRecording(status);

		verifyExpectedSynopsis(createFirstPageOfSynopsisForScheduledRecording(), "Recording",
				scheduledRecording);
	}

	@Test
	public void testSynopsisForProgrammeToBeRecorded() throws Exception {
		Status status = Status.BOOKED;

		Recording scheduledRecording = createScheduledRecording(status);

		String expectedSecondPageOfScheduledRecordingSynopsis = "To be recorded";

		verifyExpectedSynopsis(createFirstPageOfSynopsisForScheduledRecording(),
				expectedSecondPageOfScheduledRecordingSynopsis, scheduledRecording);

	}

	private void verifyExpectedSynopsis(String expectedFirstPageOfScheduledRecordingSynopsis,
			String expectedSecondPageOfScheduledRecordingSynopsis, Recording recording) {

		SynopsisFormatter classUnderTest = new SynopsisFormatter(recording);

		Assert.assertEquals(expectedFirstPageOfScheduledRecordingSynopsis, classUnderTest.getFirstPageOfSynopsis());
		Assert.assertEquals(expectedSecondPageOfScheduledRecordingSynopsis, classUnderTest.getSecondPageOfSynopsis());
	}

	private String createFirstPageOfSynopsisForScheduledRecording() {
		return TITLE + "\n" + SUMMARY + " [S]";
	}

	private Recording createScheduledRecording(Status status) {
		int rating = Rating.NONE;
		int durationInSeconds = 1440;
		GregorianCalendar dateOfRecording = new GregorianCalendar(2012, 9, 16, 13, 0);
		boolean isSeriesLinked = true;

        return new ScheduledRecording(TITLE, SUMMARY, durationInSeconds, rating, status,
                isSeriesLinked, dateOfRecording);
	}
}
