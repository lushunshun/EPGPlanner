package epgPlanner;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Test;

import epgPlanner.Recording;
import epgPlanner.SynopsisFormatter;
import epgPlanner.Recording.Rating;
import epgPlanner.Recording.Status;
import epgPlanner.ScheduledRecording;

public class TestSynopsisStringForRecordedMovies {

	@Test
	public void testSynopsisForRecordedMovieRatedUniversal() throws Exception {
		testSynopsisForRecordedMovie(Rating.UNIVERSAL, "U");
	}

	@Test
	public void testSynopsisForRecordedMovieRatedParentalGuidance() throws Exception {
		testSynopsisForRecordedMovie(Rating.PARENTAL_GUIDANCE, "PG");
	}

	@Test
	public void testSynopsisForRecordedMovieRatedOverTwelve() throws Exception {
		testSynopsisForRecordedMovie(Rating.OVER_12, "12");
	}

	@Test
	public void testSynopsisForRecordedMovieRatedOverFifteen() throws Exception {
		testSynopsisForRecordedMovie(Rating.OVER_15, "15");
	}

	@Test
	public void testSynopsisForRecordedMovieRatedAdult() throws Exception {
		testSynopsisForRecordedMovie(Rating.ADULT, "18");
	}

	private void testSynopsisForRecordedMovie(int rating, String expectedRating) {

		String title = "Prometheus";
		String summary = "A team of scientists visiting an alien world become locked in a battle to save the human race";
		int durationInSeconds = 7200;
		Status status = Status.RECORDED;
		GregorianCalendar dateOfRecording = new GregorianCalendar(2012, 9, 17, 18, 0);
		boolean isSeriesLinked = false;

		String expectedFirstPageOfScheduledRecordingSynopsis = title + " " + expectedRating + "\n" + summary;
		String expectedSecondPageOfScheduledRecordingSynopsis = "Recorded: 6:00PM Wed 17/10/12 (120mins)";

		Recording scheduledRecording = new ScheduledRecording(title, summary, durationInSeconds, rating, status,
				isSeriesLinked, dateOfRecording);

		SynopsisFormatter classUnderTest = new SynopsisFormatter(scheduledRecording);

		Assert.assertEquals(expectedFirstPageOfScheduledRecordingSynopsis, classUnderTest.getFirstPageOfSynopsis());
		Assert.assertEquals(expectedSecondPageOfScheduledRecordingSynopsis, classUnderTest.getSecondPageOfSynopsis());
	}

}
