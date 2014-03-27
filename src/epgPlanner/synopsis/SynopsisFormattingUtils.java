package epgPlanner.synopsis;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SynopsisFormattingUtils {
	public static String getTimeText(Calendar recordingTime) {
		SimpleDateFormat timeFormat = new SimpleDateFormat("h:mma");

		return timeFormat.format(recordingTime.getTime());
	}

	public static String getDateText(Calendar recordingDate) {
		SimpleDateFormat dayMonthFormat = new SimpleDateFormat("dd/MM");

		return dayMonthFormat.format(recordingDate.getTime());
	}

	public static int getDurationInMinutes(int seconds) {
		return seconds / 60;
	}

	public static String getFormattedDateTime(Calendar recordingDateTime) {
		SimpleDateFormat recordingTimeDateFormat = new SimpleDateFormat("h:mma EEE dd/MM/yy");

		return recordingTimeDateFormat.format(recordingDateTime.getTime());
	}
}
