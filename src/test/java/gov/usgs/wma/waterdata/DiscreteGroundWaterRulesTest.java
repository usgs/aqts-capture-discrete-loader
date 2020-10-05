package gov.usgs.wma.waterdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscreteGroundWaterRulesTest {

	DiscreteGroundWater dga;
	DiscreteGroundWaterRules rules = new DiscreteGroundWaterRules();

	@BeforeEach
	void setUp() {
		dga = new DiscreteGroundWater();
	}

	@Test
	void recognizedFieldVisitCommentsShouldParseToCorrectDateTimeAccuracyCode() {

		dga.setFieldVisitComments(DateTimeAccuracy.HOUR.getMatchString());

		rules.apply(dga);

		assertEquals(DateTimeAccuracy.HOUR.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.HOUR.getText(), dga.getDateTimeAccuracyText());
	}

	@Test
	void unrecognizedFieldVisitCommentsShouldParseToCorrectDateTimeAccuracyCode() {
		dga.setFieldVisitComments("XYZ");

		rules.apply(dga);

		assertEquals(DateTimeAccuracy.MINUTE.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.MINUTE.getText(), dga.getDateTimeAccuracyText());
	}

	@Test
	void emptyFieldVisitCommentsShouldParseToMINUTEDateTimeAccuracyCode() {
		dga.setFieldVisitComments("");

		rules.apply(dga);

		assertEquals(DateTimeAccuracy.MINUTE.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.MINUTE.getText(), dga.getDateTimeAccuracyText());
	}

	@Test
	void nullFieldVisitCommentsShouldParseToMINUTEDateTimeAccuracyCode() {

		dga.setFieldVisitComments(null);

		rules.apply(dga);
		assertEquals(DateTimeAccuracy.MINUTE.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.MINUTE.getText(), dga.getDateTimeAccuracyText());
	}
}
