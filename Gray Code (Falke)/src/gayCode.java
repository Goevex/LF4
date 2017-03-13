import java.util.InputMismatchException;
import java.util.Scanner;

public class gayCode {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int count = 0;
		int[] countArray;

		while (true) {
			System.out.println("Welche Bitlänge soll der Grey Code haben?");
			try {
				count = input.nextInt();
				if (count <= 0)
					throw new InputMismatchException();
				else
					break;
			} catch (InputMismatchException e) {
				System.out.println("Gib eine Zahl größer 0 ein!");
				input.next();
			}
		}

		countArray = new int[(int) Math.pow(count, 2)];
		for (int i = 0; i < countArray.length; i++) {
			countArray[i] = i;
		}

		System.out.println("Ausgangs-Array:");
		String[] binaryCountArray = intArrayToBinaryStringArray(countArray);
		printStringArray(binaryCountArray);
		System.out.println("");
		System.out.println("Gray-Code:");
		String[] grayArray = binaryStringArrayToGrayArray(binaryCountArray);
		printStringArray(grayArray);

		input.close();
	}

	private static String[] intArrayToBinaryStringArray(int[] intArray) {
		String[] stringArray = new String[intArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.format(
					"%"
							+ Integer.toBinaryString(
									intArray[intArray.length - 1]).length()
							+ "s", Integer.toBinaryString(intArray[i]))
					.replace(' ', '0');
		}
		return stringArray;
	}

	private static void printStringArray(String[] stringArray) {
		for (int i = 0; i < stringArray.length; i++) {
			System.out.printf("%s%"+ String.valueOf(stringArray.length-1).length() +"d%s", "[", i, "] ");
			System.out.println(stringArray[i]);
		}
	}

	/*
	 * Am folgenden Beispiel zeige ich, wie man den Gray Code von einer beliebigen Bitfolge generiert: 
	 * -Man habe die Bitfolge 0100 und möchte dessen Gray Code 
	 * -Man übernehme das erste Bit ganz so wie es ist:   0 
	 * -Für die restlichen drei Bits wende man ein XOR (Exclusive OR) auf alle nebeneinanderliegenden Bits an. Von links nach rechts. 
	 * 											0 XOR 1 = 1 
	 * 											1 XOR 0 = 1 
	 * 											0 XOR 0 = 0 
	 * -Zum Schluss füge man die Bits zusammen. Der Gray Code zu 0100 lautet also 0110
	 */
	private static String[] binaryStringArrayToGrayArray(String[] stringArray) {
		String[] returnArray = new String[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			returnArray[i] = stringArray[i].substring(0, 1); // Erstes Bit wird
																// übernommen
			for (int j = 1; j < stringArray[i].length(); j++) {
				boolean firstBool, secondBool, resultBool;
				String resultPseudoChar = "";
				firstBool = stringArray[i].charAt(j - 1) == '1';
				secondBool = stringArray[i].charAt(j) == '1';
				resultBool = firstBool ^ secondBool; // XOR der nebeneinanderliegenden Bits
				if (resultBool)
					resultPseudoChar = "1";
				else
					resultPseudoChar = "0";
				returnArray[i] = returnArray[i] + resultPseudoChar; // hinzufügen des Ergebnisses zur Gray-Bitfolge
			}
		}
		return returnArray;
	}
}
