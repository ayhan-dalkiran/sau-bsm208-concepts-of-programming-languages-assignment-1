/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Genel yazıcı sınıfı.
 * </p>
 */
package pdp1.streamio.writer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import pdp1.streamio.StreamWrapper;

public class Writer implements StreamWrapper {
	protected final OutputStream stream;
	protected static final Charset charset = Charset.forName("UTF-8");
	
	public Writer(OutputStream stream) {
		this.stream = stream;
	}
	
	public void write(String data) throws IOException {
		this.stream.write(data.getBytes(charset));
	}

	@Override
	public void close() throws IOException {
		if (this.stream != System.out)
			this.stream.close();
	}
}
