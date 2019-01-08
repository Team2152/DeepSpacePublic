package frc.robot.utilities;

public class Util {
	public static byte[] concatBytes(byte[]... tss) {
		int size = 0;
		for(byte[] ts : tss) 
			size += ts.length;

		byte[] result = new byte[size];

		int index = 0;
		for(int i = 0; i < tss.length; i++) {
			for(int j = 0; j < tss[i].length; j++) {
				result[index] = tss[i][j];
				index++;
			}
		}
		return result;
	}
}