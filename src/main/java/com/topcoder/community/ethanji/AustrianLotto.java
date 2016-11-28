package com.topcoder.community.ethanji;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * An instance of this class is used to generate a draw numbers or Figure out a
 * breakdown of the gambler's success rate.
 * 
 * @author xingyuji
 *
 */
public class AustrianLotto {
	private static Random random = new Random();
	private int ballRange = 45;

	/**
	 * Generate 6 distinct integers between 1 and 45 as draw
	 * 
	 * @return A string as draw contains six numbers each separated by a single
	 *         space
	 */
	public String draw() {
		StringBuilder drawing = new StringBuilder();
		boolean[] bool = new boolean[ballRange];
		int drawnNumber = 0;
		for (int j = 0; j < 6; j++) {
			if (j > 0) {
				drawing.append(" ");
			}
			do {
				drawnNumber = random.nextInt(ballRange - 1) + 1;
			} while (bool[drawnNumber]);
			bool[drawnNumber] = true;
			drawing.append(drawnNumber);
		}
		return drawing.toString();
	}

	/**
	 * Figure out a breakdown of the gambler's success rate
	 * 
	 * @param drawing
	 *            the drawing string contains six distinct numbers
	 * @param picks
	 *            a string array contains up to 50 elements, each elements
	 *            contains six distinct numbers
	 * @return A integer array represent occurrences of matches. The 0th element
	 *         is the number of picks in which there are no matches between the
	 *         gambler's numbers and the drawn numbers, and the 1st to 6th
	 *         elements are the number of picks in which there are 1 to 6
	 *         matches, respectively.
	 */
	public int[] evaluate(String drawing, String[] picks) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] drawingNumbers = drawing.split(" ");
		for (String singleDrawingNumber : drawingNumbers) {
			String regex = "\\s" + singleDrawingNumber + "\\s|^"
					+ singleDrawingNumber + "\\s|\\s" + singleDrawingNumber
					+ "$";
			Pattern pattern = Pattern.compile(regex);
			for (String singlePick : picks) {
				map.putIfAbsent(singlePick, 0);
				if (pattern.matcher(singlePick).find()) {
					map.put(singlePick, map.get(singlePick) + 1);
				}
			}
		}
		Map<Integer, Integer> countsMap = new HashMap<Integer, Integer>();
		for (Integer countMatches : map.values()) {
			countsMap.putIfAbsent(countMatches, 0);
			countsMap.put(countMatches, countsMap.get(countMatches) + 1);
		}
		int[] signature = new int[7];
		for (int i = 0; i < 7; i++) {
			signature[i] = countsMap.get(i) == null ? 0 : countsMap.get(i);
		}
		System.out.println("Drawing: " + drawing);
		System.out.println("Picks: " + Arrays.toString(picks));
		System.out.println("Returns: " + Arrays.toString(signature));
		return signature;
	}

	public static void main(String[] args) {
		AustrianLotto lotto = new AustrianLotto();
		Gambler gambler = new Gambler();
		lotto.evaluate(lotto.draw(), gambler.generatePicks());
	}
}
