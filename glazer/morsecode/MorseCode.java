package glazer.morsecode;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {

	public MorseCode() {

	}

	public String toMorseCode(String plainText) {
		if (plainText.length() > 0) {
			StringBuilder translated = new StringBuilder();
			String sentence = plainText.toUpperCase();
			String[] words = sentence.split(" ");
			StringBuilder builder = new StringBuilder();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("A", ".-");
			map.put("B", "-...");
			map.put("C", "-.-.");
			map.put("D", "-..");
			map.put("E", ".");
			map.put("F", "..-.");
			map.put("G", "--.");
			map.put("H", "....");
			map.put("I", "..");
			map.put("J", ".---");
			map.put("K", "-.-");
			map.put("L", ".-..");
			map.put("M", "--");
			map.put("N", "-.");
			map.put("O", "---");
			map.put("P", ".--.");
			map.put("Q", "--.-");
			map.put("R", ".-.");
			map.put("S", "...");
			map.put("T", "-");
			map.put("U", "..-");
			map.put("V", "...-");
			map.put("W", ".--");
			map.put("X", "-..-");
			map.put("Y", "-.--");
			map.put("Z", "--..");
			map.put(".", ".-.-.-");
			map.put(",", "--..--");
			map.put("?", "..--..");
			map.put("=", "-...-");
			map.put("0", "-----");
			map.put("1", ".----");
			map.put("2", "..---");
			map.put("3", "...--");
			map.put("4", "....-");
			map.put("5", ".....");
			map.put("6", "-....");
			map.put("7", "--...");
			map.put("8", "---..");
			map.put("9", "----.");
			String letter;
			for (int i = 0; i < words.length; i++) {
				for (int j = 0; j < words[i].length(); j++) {
					builder.setLength(0);
					letter = Character.toString(words[i].charAt(j));
					builder.append(map.get(letter));

					builder.append(" ");
					translated.append(builder);
					if ((j == (words[i].length() - 1))) {
						translated.append("/ ");
						if(i==words.length-1){
							translated.setLength(translated.length()-3);
;						}
					}
				}
				
				
			}

			return translated.toString();
		} else {
			return "";
		}
	}

	public String toPlainText(String morse) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(".-", "A");
		map.put("-...", "B");
		map.put("-.-.", "C");
		map.put("-..", "D");
		map.put(".", "E");
		map.put("..-.", "F");
		map.put("--.", "G");
		map.put("....", "H");
		map.put("..", "I");
		map.put(".---", "J");
		map.put("-.-", "K");
		map.put(".-..", "L");
		map.put("--", "M");
		map.put("-.", "N");
		map.put("---", "O");
		map.put(".--.", "P");
		map.put("--.-", "Q");
		map.put(".-.", "R");
		map.put("...", "S");
		map.put("-", "T");
		map.put("..-", "U");
		map.put("...-", "V");
		map.put(".--", "W");
		map.put("-..-", "X");
		map.put("-.--", "Y");
		map.put("--..", "Z");
		map.put("-----", "0");
		map.put(".----", "1");
		map.put("..---", "2");
		map.put("...--", "3");
		map.put("....-", "4");
		map.put(".....", "5");
		map.put("-....", "6");
		map.put("--...", "7");
		map.put("---..", "8");
		map.put("----.", "9");
		map.put(".-.-.-", ".");
		map.put("--..--", ",");
		map.put("..--..", "?");
		map.put("-...-", "=");
		map.put("/", " ");
		map.put("", "");
		StringBuilder translated = new StringBuilder();
		if (morse.length() > 0) {

			String[] words = morse.split(" ");
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < words.length; i++) {
				builder.setLength(0);
				String letter = words[i];
				if (letter.length() > 0) {
					builder.append(map.get(letter));
					translated.append(builder);
				}

			}

			return translated.toString();
		} else {
			return "";
		}
	}

}
