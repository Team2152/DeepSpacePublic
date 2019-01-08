package frc.robot.utilities;

public class ByteUtil {
	public static int[] getUnsignedBytesFromLong(byte[] bytes) {
    	int[] out = new int[bytes.length];
    	for(int i = 0; i < bytes.length; i++) {
    		if (bytes[i] < 0) { 
    			int rollover = bytes[i] + 129;
    			out[i] = 127 + rollover;
    		} else {
    			out[i] = bytes[i];
    		}
    	}
    	return out;
    }

	/**
	 * Converts a long to a byte array (little endian byte order)
	 * @param l
	 * @return A byte array in little endian byte order
	 */
	public static byte[] toByteArrayLE(long l) {
    	byte[] bytes = new byte[8];
    	for(byte i = 0; i < 8; i++) {
    		bytes[i] = (byte) (l & 0x0ffL);
    		l >>= 8;
    	}
    	return bytes;
    }

	/**
	 * Converts a long to a byte array (big endian byte order)
	 * @param l
	 * @return A byte array in big endian byte order
	 */
	public static byte[] toByteArrayBE(long l) {
    	byte[] bytes = new byte[8];
    	for(byte i = 0; i < 8; i++) {
    		bytes[i] = (byte) ((l & 0xff00000000000000L) >> 8*7);
    		l <<= 8;
    	}
    	return bytes;
    }

	/**
	 * Converts an integer to a byte array (little endian byte order)
	 * @param n
	 * @return A byte array in little endian byte order
	 */
	public static byte[] toByteArrayLE(int n) {
    	byte[] bytes = new byte[4];
    	for(byte i = 0; i < 4; i++) {
    		bytes[i] = (byte) (n & 0x0ffL);
    		n >>= 8;
    	}
    	return bytes;
    }

	/**
	 * Converts an integer to a byte array (big endian byte order)
	 * @param n
	 * @return A byte array in big endian byte order
	 */
	public static byte[] toByteArrayBE(int n) {
    	byte[] bytes = new byte[4];
    	for(byte i = 0; i < 4; i++) {
    		bytes[i] = (byte) ((n & 0xff000000L) >> 8*3);
    		n <<= 8;
    	}
    	return bytes;
    }
}