package util;

public class CutString {
	public static final String cutTittle(String st) {
		String result = "";
		String cut[] = st.split(" ");
		for (String item : cut) {
			item = item.trim();
			result = result + item + " ";
			if (result.length() >= 300) {
				break;
			}
		}
		result = result + "......";
		return result;
	}
}
