package epgPlanner;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import epgPlanner.Recording.Rating;
import epgPlanner.Recording.Status;
import epgPlanner.synopsis.ManualRecordingSynopsisProducer;

public class TestSynopsisStringForManualRecordings {

	private final GregorianCalendar startTime = new GregorianCalendar(2012, 9, 17, 16, 15);
	private final GregorianCalendar endTime = new GregorianCalendar(2012, 9, 17, 17, 30);
	private final String expectedFirstPageOfSynopsis = "4:15PM 17/10\n" + ManualRecording.NO_SYNOPSIS_AVAILABLE;

	@Test
	public void TestSynopsisForManualRecordingToBeRecordedOnce() throws Exception {

		String expectedSecondPageOfSynopsis = "To be recorded\n17/10 4:15PM - 5:30PM, Once";

		ManualRecording.Recurrence recurrence = ManualRecording.Recurrence.ONCE;
		createAndVerifyManualRecordingWithSpecifiedRecurrence(expectedSecondPageOfSynopsis, recurrence);
	}

	@Test
	public void TestSynopsisForManualRecordingToBeRecordedDaily() throws Exception {
		String expectedSecondPageOfSynopsis = "To be recorded\n17/10 4:15PM - 5:30PM, Daily";

		ManualRecording.Recurrence recurrence = ManualRecording.Recurrence.DAILY;
		createAndVerifyManualRecordingWithSpecifiedRecurrence(expectedSecondPageOfSynopsis, recurrence);
	}

	@Test
	public void TestSynopsisForManualRecordingToBeRecordedWeekly() throws Exception {
		String expectedSecondPageOfSynopsis = "To be recorded\n17/10 4:15PM - 5:30PM, Weekly";

		ManualRecording.Recurrence recurrence = ManualRecording.Recurrence.WEEKLY;
		createAndVerifyManualRecordingWithSpecifiedRecurrence(expectedSecondPageOfSynopsis, recurrence);
	}

	@Test
	public void TestSynopsisForManualRecordingThatIsCurrentlyRecording() throws Exception {
		String expectedSecondPageOfSynopsis = "Recording\n17/10 4:15PM - 5:30PM, Once";

		ManualRecording manualRecording = new ManualRecording(startTime, endTime, Rating.NONE, Status.RECORDING,
				startTime, ManualRecording.Recurrence.ONCE);

		// SynopsisFormatter classUnderTest = new
		// SynopsisFormatter(manualRecording);
		//
		// Assert.assertEquals(expectedFirstPageOfSynopsis,
		// classUnderTest.getFirstPageOfSynopsis());
		// Assert.assertEquals(expectedSecondPageOfSynopsis,
		// classUnderTest.getSecondPageOfSynopsis());

		ManualRecordingSynopsisProducer classUnderTest = new ManualRecordingSynopsisProducer(manualRecording);

		Assert.assertEquals(expectedFirstPageOfSynopsis, classUnderTest.getSynopsisFor(0));
		Assert.assertEquals(expectedSecondPageOfSynopsis, classUnderTest.getSynopsisFor(1));
	}

	@Test
	public void TestSynopsisForManualRecordingThatHasBeenRecorded() throws Exception {
		String expectedSecondPageOfSynopsis = "Recorded: 4:15PM Wed 17/10/12 (75mins)";

		ManualRecording manualRecording = new ManualRecording(startTime, endTime, Rating.NONE, Status.RECORDED,
				startTime, ManualRecording.Recurrence.ONCE);

		ManualRecordingSynopsisProducer classUnderTest = new ManualRecordingSynopsisProducer(manualRecording);

		Assert.assertEquals(expectedFirstPageOfSynopsis, classUnderTest.getSynopsisFor(0));
		Assert.assertEquals(expectedSecondPageOfSynopsis, classUnderTest.getSynopsisFor(1));
	}

	private void createAndVerifyManualRecordingWithSpecifiedRecurrence(String expectedSecondPageOfSynopsis,
			ManualRecording.Recurrence recurrence) throws Exception {
		ManualRecording manualRecording = new ManualRecording(startTime, endTime, Rating.NONE, Status.BOOKED,
				new GregorianCalendar(), recurrence);

		ManualRecordingSynopsisProducer classUnderTest = new ManualRecordingSynopsisProducer(manualRecording);

		Assert.assertEquals(expectedFirstPageOfSynopsis, classUnderTest.getSynopsisFor(0));
		Assert.assertEquals(expectedSecondPageOfSynopsis, classUnderTest.getSynopsisFor(1));
	}
}
