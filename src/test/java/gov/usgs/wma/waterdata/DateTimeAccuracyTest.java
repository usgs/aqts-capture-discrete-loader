package gov.usgs.wma.waterdata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeAccuracyTest {

	String[] DAYS_VALID = {
		"Water level date represents the day",
		"Water level date represents the day.Ignore these: year, month, hour, second, minute",
		"Water level date represents the dayyearmonthdayminutesecond", /* Should we require a space or delimiter? */
		"XXXXWater level date represents the dayXXXX", /*Currently this is OK - should it be? */
		"\"Water level date represents the day\"",
		"\"?*$#@!^&()\\{};:'/<>,.~`\n\t\rWater level date represents the day?*$#@!^&()\\{};:'/<>,.~`\n\t\r\"",
		/* Real examples */
		"Water level date represents the day, Source: Reporting Agency, Remarks: time stamp removed. entered as 00:52",
	};

	String[] DAYS_INVALID = {
		"Water level date represents X day",
		"Water level date represents the 'day",
		"Water level date represents the DAY",
		/* Real examples */
		"Source: Reporting Agency, GWSI reading qualifier:, Recent Pumping, Remarks: )Pump off since 6:15 am today.", /* Contains 'day' */
		"Source: Reporting Agency, GWSI reading qualifier:, Active Pumping, Remarks: Bailed day before?" /* Contains 'day' */
	};

	String[] YEARS_VALID = {
		"Water level date represents the year",
		/* Real examples */
		"Water level date represents the year, Source: Driller's Log, Remarks: changed date from 1900 to 1959 based on paper record. CAF 11/1/2018",
	};

	String[] YEARS_INVALID = {
		"Water level date represents the YEAR",
		/* Real examples */
		"Source: Driller's Log, GWSI reading qualifier:, Other, Remarks: WL estimate during drilling. Date is only accurate to the year."
	};

	String[] MONTHS_VALID = {
		"Water level date represents the month",
		/* Real examples */
		"Water level date represents the month, Source: Driller's Log, Remarks: NOT SURE OF THIS WL - ~72 FT DOWN THE DRILL LOG - SATURATED WITH WATER."
	};

	String[] MONTHS_INVALID = {
		"Water level date represents the MONTH",
		/* Real examples */
		"Source: Reporting Agency, Remarks: new transducer installed earlier this month by Loughlin Water Consultants"
	};

	String[] HOURS_VALID = {
		"Water level date represents the hour",
		"XXXWater level date represents the hourXXX",
		/* Real examples */
		"Water level date represents the hour, Source: Unknown",
	};

	String[] HOURS_INVALID = {
		"Water level date represents the HOUR",
		/* Real examples */
		"Source: Geologist, Remarks: City of Annapolis Lower Patapsco wells off for about 24 hours", /* contains 'hour' */
	};

	/* Not trying to be complete here - just verifying basics */
	void basicEnumValues() {

		//Check the codes
		assertEquals(DateTimeAccuracy.YEAR.getCode(), "Y");
		assertEquals(DateTimeAccuracy.MONTH.getCode(), "M");
		assertEquals(DateTimeAccuracy.DAY.getCode(), "D");
		assertEquals(DateTimeAccuracy.HOUR.getCode(), "h");
		assertEquals(DateTimeAccuracy.MINUTE.getCode(), "m");
		assertEquals(DateTimeAccuracy.SECOND.getCode(), "s");

		//a few sanity checks
		assertEquals(DateTimeAccuracy.DAY.isReal(), true);
		assertEquals(DateTimeAccuracy.DAY.getText(), "day");
		assertEquals(DateTimeAccuracy.UNKNOWN.isReal(), false);
		assertNull(DateTimeAccuracy.UNKNOWN.getText());
		assertNull(DateTimeAccuracy.UNKNOWN.getCode());
	}

	@Test
	void nullAndEmptyParsingDoesNotCauseErrors() {
		assertEquals(DateTimeAccuracy.UNKNOWN, DateTimeAccuracy.parse(null));
		assertEquals(DateTimeAccuracy.UNKNOWN, DateTimeAccuracy.parse("     "));
		assertEquals(DateTimeAccuracy.UNKNOWN, DateTimeAccuracy.parse("\n\r"));
	}

	@Test
	void parseDays() {
		for (String s : DAYS_VALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.DAY, dta);
		}

		for (String s : DAYS_INVALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.UNKNOWN, dta);
		}
	}

	@Test
	void parseYears() {
		for (String s : YEARS_VALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.YEAR, dta);
		}

		for (String s : YEARS_INVALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.UNKNOWN, dta);
		}
	}

	@Test
	void parseMonths() {
		for (String s : MONTHS_VALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.MONTH, dta);
		}

		for (String s : MONTHS_INVALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.UNKNOWN, dta);
		}
	}

	@Test
	void parseHours() {
		for (String s : HOURS_VALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.HOUR, dta);
		}

		for (String s : HOURS_INVALID) {
			DateTimeAccuracy dta = DateTimeAccuracy.parse(s);
			assertEquals(DateTimeAccuracy.UNKNOWN, dta);
		}
	}

	/* We don't expect to see minutes or seconds in comments, but it should work to parse them */
	@Test
	void parseMinuteAndSecond() {
		assertEquals(DateTimeAccuracy.MINUTE, DateTimeAccuracy.parse("Water level date represents the minute"));
		assertEquals(DateTimeAccuracy.SECOND, DateTimeAccuracy.parse("Water level date represents the second"));
	}
}
