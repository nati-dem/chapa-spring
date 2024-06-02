package io.github.nati_dem.chapa.util;

public class ChapaUtil {

	public static boolean isNotNullAndEmpty(String val) {
		return val != null && !val.isBlank() && !val.isEmpty();
	}
	
    public static boolean isSuccessResponse(int statucCode) {
        return statucCode >= 200 && statucCode <= 299;
    }
	
}
