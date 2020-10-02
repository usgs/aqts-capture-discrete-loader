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
	void recognizedDTACodesShouldBeUnmodified() {
		dga.setDateTimeAccuracyCode(DateTimeAccuracy.HOUR.getCode());
		dga.setDateTimeAccuracyText(DateTimeAccuracy.HOUR.getText());

		rules.apply(dga);

		assertEquals(DateTimeAccuracy.HOUR.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.HOUR.getText(), dga.getDateTimeAccuracyText());
	}

	@Test
	void unrecognizedDTACodesShouldBeConvertedToMinute() {
		dga.setDateTimeAccuracyCode("XYZ");

		rules.apply(dga);

		assertEquals(DateTimeAccuracy.MINUTE.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.MINUTE.getText(), dga.getDateTimeAccuracyText());
	}

	@Test
	void emptyDTACodesShouldDefaultToMinute() {
		dga.setDateTimeAccuracyCode("");
		dga.setDateTimeAccuracyText("");

		rules.apply(dga);

		assertEquals(DateTimeAccuracy.MINUTE.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.MINUTE.getText(), dga.getDateTimeAccuracyText());
	}

	@Test
	void nullDTACodesShouldDefaultToMinute() {
		rules.apply(dga);
		assertEquals(DateTimeAccuracy.MINUTE.getCode(), dga.getDateTimeAccuracyCode());
		assertEquals(DateTimeAccuracy.MINUTE.getText(), dga.getDateTimeAccuracyText());
	}
}
