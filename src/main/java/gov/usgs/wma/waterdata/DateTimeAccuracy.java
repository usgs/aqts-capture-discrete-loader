package gov.usgs.wma.waterdata;

import java.util.Locale;

/**
 * The accuracy of the date/time of aa measurement.
 * Not all possible accuracies are represented, only the ones that we might parse from text.
 */
public enum DateTimeAccuracy {

	UNKNOWN(null, null, false),
	YEAR("Y", "year", true),
	MONTH("M", "month", true),
	DAY("D", "day", true),
	HOUR("h", "hour", true),
	MINUTE("m", "minute", true),
	SECOND("s", "second", true);

	public static final String COMMENT_TEMPLATE = "Water level date represents the %s";

	private String code;
	private String text;
	private boolean real;

	/* Exact string to match in a comment if this accuracy is present */
	private String matchString;

	DateTimeAccuracy(String code, String text, boolean real) {
		this.code = code;
		this.text = text;
		this.real = real;
		this.matchString = String.format(Locale.US, COMMENT_TEMPLATE, text);
	}

	/**
	 * The single character code for the accuracy.
	 * @return e.g. 'Y' (year), 'M' (month), etc,
	 */
	public String getCode() {
		return code;
	}

	/**
	 * The human readable name for the accuracy code.
	 * @return e.g. 'year', 'month', etc,
	 */
	public String getText() {
		return text;
	}

	/**
	 * True if this value represents an actual accuracy value, false if it does not.
	 * This allows an enum instance to represent the "I don't know the accuracy" state.
	 * @return
	 */
	public boolean isReal() {
		return real;
	}

	/**
	 * Parses a source string (expected to be comment text) looking for a DT accuracy embedded in the string.
	 * The template used to match an accuracy in a string is COMMENT_TEMPLATE, replaced with the DT text.
	 *
	 * @param sourceText The text to search for a DT acc in.
	 * @return A DateTimeAccuracy instance, which is never null.
	 * If the sourceText is null or no matching accuracy text is found in it, the UNKNOWN instance is returned.
	 */
	public static DateTimeAccuracy parse(final String sourceText) {

		if (sourceText != null) {
			for(DateTimeAccuracy acc : DateTimeAccuracy.values()) {
				if (acc.isReal() && sourceText.contains(acc.matchString)) {
					return acc;
				}
			}
		}

		return UNKNOWN;
	}
}
