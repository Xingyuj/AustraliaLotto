package com.topcoder.community.ethanji;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An instance of this class is used to generate picks.
 * @author xingyuji
 *
 */
public class Gambler {
	private static Random random = new Random();

	/**
	 * Generate picks followed the constrains: picks contains between 1 and 50
	 * elements, inclusive. Each element of picks contains exactly 6 distinct
	 * integers between 1 and 45, inclusive, each separated by exactly one
	 * single space, e.g. "3 12 13 17 32 45". Integers have no leading zeros.
	 * Each element of picks contains between 11 and 17 characters, inclusive,
	 * and no leading or trailing spaces.
	 * 
	 * @return A string array as picks contains up to 50 elements, each elements
	 *         contains six distinct numbers
	 */
	public String[] generatePicks() {
		int ballRange = 45;
		// picks contains between 1 and 50 elements, inclusive.
		List<String> picks = new ArrayList<String>();
		for (int i = 0; i < random.nextInt(49) + 1; i++) {
			// element of picks contains exactly 6 distinct integers between 1
			// and 45
			StringBuilder singlePick = new StringBuilder();
			boolean[] bool = new boolean[ballRange];
			int pickedNumber = 0;
			for (int j = 0; j < 6; j++) {
				if (j > 0) {
					singlePick.append(" ");
				}
				do {
					pickedNumber = random.nextInt(ballRange - 1) + 1;
				} while (bool[pickedNumber]);
				bool[pickedNumber] = true;
				singlePick.append(pickedNumber);
			}
			picks.add(singlePick.toString());
		}
		return picks.toArray(new String[picks.size()]);
	}

}
