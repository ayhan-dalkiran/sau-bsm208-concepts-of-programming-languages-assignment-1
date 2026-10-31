/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Soyut simulasyon nesne okuyucu sınıfı.
 * </p>
 */
package pdp1.streamio.reader;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class SimulationObjectFileReader extends FileReader {
	protected final Scanner scanner;

	public SimulationObjectFileReader(String pathName) throws FileNotFoundException, SecurityException {
		super(pathName);
		this.scanner = new Scanner(this.stream);
		this.setDelimiterPattern(ATTRIBUTE_DIVIDER_PATTERN);
	}
	
	protected static final int PATTERN_FLAGS = Pattern.UNICODE_CHARACTER_CLASS;
	protected static final Pattern ATTRIBUTE_DIVIDER_PATTERN = Pattern.compile("\\s*(?:#|\\v+)\\s*", PATTERN_FLAGS);
	protected static final Pattern DATETIME_PATTERN = Pattern.compile("(?<day>\\d{1,2})\\.(?<month>\\d{1,2})\\.(?<year>\\d+)", PATTERN_FLAGS);
	
	public void setDelimiterPattern(Pattern pattern) {
		this.scanner.useDelimiter(pattern);
	}
	
	public boolean hasNext() {
		return this.scanner.hasNext();
	}
	
	public String readString() {
		return this.scanner.next();
	}
	
	public int readInt() {
		return this.scanner.nextInt();
	}
	
	public LocalDate readLocalDate() {
		var value = this.readString();
		var matcher = DATETIME_PATTERN.matcher(value);
		matcher.find();
		
		int day = Integer.parseInt(matcher.group("day"));
		int month = Integer.parseInt(matcher.group("month"));
		int year = Integer.parseInt(matcher.group("year"));
		
		return LocalDate.of(year, month, day);
	}

}
