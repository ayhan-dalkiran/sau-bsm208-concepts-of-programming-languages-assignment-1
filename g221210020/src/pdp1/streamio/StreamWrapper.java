/*
 * @author Ayhan Dalkıran G221210020
 * @since 27/04/2025
 * <p>
 * Stream nesnesinin üzerine kurulu tüm IO sınıflarının kullandığı arayüz.
 * </p>
 */
package pdp1.streamio;

public interface StreamWrapper extends AutoCloseable {
	default public void close(boolean closeStream) throws Exception {
		if (closeStream)
			this.close();
	}
}
