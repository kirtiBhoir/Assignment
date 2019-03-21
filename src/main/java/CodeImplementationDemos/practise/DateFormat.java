package CodeImplementationDemos.practise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
public class DateFormat {
	private static final String[] formats = { "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ",
			"yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
			"yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", "MM/dd/yyyy'T'HH:mm:ss.SSSZ",
			"MM/dd/yyyy'T'HH:mm:ss.SSS", "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss", "yyyy:MM:dd HH:mm:ss",
			"yyyyMMdd", "dd/mm/yyyy",};

	/*
	 * @param args
	 */
	public static void main(String[] args) {
		String inputString = "04/03/2019";
		parse(inputString);
	}

	public static void parse(String d) {
		if (d != null) {
			for (String dateFormat : formats) {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				try {
					sdf.parse(d);
					System.out.println("Date Format of given date is :--" + dateFormat);
				} catch (ParseException e) {

				}
			}
		}
	}
}
