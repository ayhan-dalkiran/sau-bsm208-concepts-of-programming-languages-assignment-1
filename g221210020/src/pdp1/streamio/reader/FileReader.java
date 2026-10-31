/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Dosya okuyucu sınıf.
 * </p>
 */
package pdp1.streamio.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileReader extends Reader {
	public FileReader(String pathName) throws FileNotFoundException, SecurityException {
		super(new FileInputStream(pathName));
	}
}
