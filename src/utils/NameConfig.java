package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The class NameConfig
 * Define names of shapesets and corresponding puzzles.
 * shapeset names include: ["traditional", "nontraditional"]
 * puzzle names for "traditional" shapeset: ["fox", "bird", "rabbit", "cristal", "fish", "duck", "dontknow", "turtle","house", "dog"]
 * puzzle names for "nontraditonal" shapeset: ["house", "snake", "windmill", "human", "tree", "diamond", "fish1", "horse","table", "fish2"]
 * @author weixiong
 */

public class NameConfig {

	/**
	 * Create the the name of config
	 */
	private static NameConfig configInstance = null;
	
	public static NameConfig getInstance() {
		if (configInstance == null) {
			configInstance = new NameConfig();
		}
		return configInstance;
	}
	
	public final static ArrayList<String> shapesetNames;
	static {
		shapesetNames = new ArrayList<>();
		shapesetNames.add("traditional");
		shapesetNames.add("nontraditional");
	}
	
	private final static Map<String, ArrayList<String>> puzzleNames = new HashMap<>();
	private final static ArrayList<String> traditional = new ArrayList<String>();
	private final static ArrayList<String> nontraditional = new ArrayList<String>();
	
	private NameConfig() {
		getPuzzles(shapesetNames.get(0), traditional);
		getPuzzles(shapesetNames.get(1), nontraditional);
	}

	private void getPuzzles(String dir, ArrayList<String> puzzles) {
		File folder = new File(dir);
		String[] folders = folder.list();
		for (String f : folders) {
			if (!f.contains(".") && !f.contains("test")) {
				puzzles.add(f);
			}
		}
		puzzleNames.put(dir, puzzles);
	}

	
	/**
	 * Gets the puzzles.
	 *
	 * @param shapesetName the shapeset name
	 * @return A list of puzzle names for the shapeset
	 */
	public ArrayList<String> getPuzzles(String shapesetName) {
		return puzzleNames.get(shapesetName);
	}

}

