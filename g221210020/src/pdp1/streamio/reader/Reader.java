/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Genel stream okuyucu sınıfı.
 * </p>
 */
package pdp1.streamio.reader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import pdp1.streamio.StreamWrapper;

public class Reader implements StreamWrapper {
	protected final InputStream stream;
	protected static final Charset charset = Charset.forName("UTF-8");
	
	public Reader(InputStream stream) {
		this.stream = stream;
	}
	
	public String read(int length) throws IOException {
		return new String(this.stream.readNBytes(length), charset);
	}

	@Override
	public void close() throws IOException {
		if (this.stream != System.in)
			this.stream.close();
	}
}
