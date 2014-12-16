package glazer.acm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PenneyGame {
	private Map<String, Integer> map;

	public PenneyGame(){
		map = new HashMap<String, Integer>();
	
	}
	public void clearMap(){
		map.put("TTT", 0);
		map.put("TTH", 0);
		map.put("THT", 0);
		map.put("THH", 0);
		map.put("HTT", 0);
		map.put("HTH", 0);
		map.put("HHT", 0);
		map.put("HHH", 0);
	}
	public String playRound(String toss,int roundNum){
	String t=toss;
	String key;
	String report;
	int round=roundNum;

	for (int k = 0; k < 3; k++) {

		for (int j = k; j + 3 <= 40; j += 3) {
			key = t.substring(j, j + 3);
			map.put(key, map.get(key) + 1);
		}
	}
	report = String.valueOf(round);
	report += " " + map.get("TTT");
	report += " " + map.get("TTH");
	report += " " + map.get("THT");
	report += " " + map.get("THH");
	report += " " + map.get("HTT");
	report += " " + map.get("HTH");
	report += " " + map.get("HHT");
	report += " " + map.get("HHH");
	return report;
	}
	public String playGame(){
		Scanner keyboard=new Scanner (System.in);
		StringBuilder report=new StringBuilder();
		int numRounds=keyboard.nextInt();
		int sets=numRounds;
		String toss;
		
		for (int i = 0; i < sets; i++) {
			clearMap();
			int roundNum = keyboard.nextInt();
			keyboard.nextLine();
			toss = keyboard.nextLine();
		
			report.append(playRound(toss,roundNum));
			if(i<sets-1){
				report.append("\n");
			}

		}
		keyboard.close();
		return report.toString();
	}
	public static void main(String args[]) {
		PenneyGame game=new PenneyGame();
		System.out.println(game.playGame());

	}
}
