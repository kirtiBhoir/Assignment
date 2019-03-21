package CodeImplementationDemos.coreJavaAssignment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeDemo {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("Time in UTC: " + sdf.format(calendar.getTime()));
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		System.out.println("Time in IST: " + sdf.format(calendar.getTime()));
	}

}
