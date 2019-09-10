package utils;

import java.awt.Color;
import java.awt.Polygon;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import models.PlayShape;


public class BuildShapes {
	public static PlayShape[] buildPlayShapes(String path) {
		File file = new File(path);
		System.out.println("build palyshape!");
//		PlayShape[] temp = new PlayShape[7];
		ArrayList<PlayShape> temp = new ArrayList<PlayShape>();
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch(Exception e) {
			System.out.println("I/O failure");
		}
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.length() < 1) {
				break;
			}
//			temp[i++] = buildPlayShape(line);
			temp.add(buildPlayShape(line));
		}
		scan.close();
		PlayShape[] res;
		if (temp.size() == 7) {
			res = new PlayShape[7];
		}
		else {
			res = new PlayShape[8];
		}
		int i = 0;
		for (PlayShape p: temp) {
			res[i++] = p;
		}
		return res;
	}
	
	public static PlayShape buildPlayShape(String line) {
		int r, g, b;
		String[] arr = line.split(" ");
		r = Integer.parseInt(arr[0]);
		g = Integer.parseInt(arr[1]);
		b = Integer.parseInt(arr[2]);
		Polygon poly = new Polygon();
		for(int i = 3; i < arr.length; i += 2) {
			poly.addPoint(Integer.parseInt(arr[i]), Integer.parseInt(arr[i+1]));
		}
		if (r == 0 && g == 0 && b == 0) {
			return new PlayShape(poly);
		}
		return new PlayShape(poly, new Color(r, g, b));
	}

}
