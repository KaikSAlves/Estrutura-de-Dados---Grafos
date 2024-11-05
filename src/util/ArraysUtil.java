package util;

import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysUtil {

	public static String convertArrayToString(Integer[] array) {
		return Arrays.toString(array).replace("[", "").replace("]", "").replace(" ", "");
	}

	public static int[] convertStringToArray(String s) {
		return Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();
	}
}
