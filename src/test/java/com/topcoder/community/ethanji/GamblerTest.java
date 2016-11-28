package com.topcoder.community.ethanji;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Unit test for AustrianLottoTest.
 * 
 * @author xingyuji
 * 
 */
public class GamblerTest {

	/**
	 * Check the constrain: picks contains between 1 and 50 elements, inclusive.
	 */
	@Test
	public void picksElementsCapacityValidation() {
		Gambler gambler = new Gambler();
		String[] picks = gambler.generatePicks();
		assertTrue(picks.length <= 50 && picks.length > 0);
	}

	/**
	 * Check the constrain: each element of picks contains exactly 6 distinct
	 * integers between 1 and 45, inclusive, each separated by exactly one
	 * single space, e.g. "3 12 13 17 32 45" (quotes for clarity). Integers have
	 * no leading zeros.
	 */
	@Test
	public void pickingNumbersValidation() {
		Gambler gambler = new Gambler();
		String[] picks = gambler.generatePicks();
		for (String singlePick : picks) {
			String[] pickedNumbers = singlePick.split(" ");
			boolean[] bool = new boolean[45];
			// picks contains exactly 6 distinct integers
			assertEquals(pickedNumbers.length, 6);
			for (String singleNumber : pickedNumbers) {
				// distinct integers
				assertFalse(bool[Integer.parseInt(singleNumber)]);
				bool[Integer.parseInt(singleNumber)] = true;
				// integers between 1 and 45 and no leading zeros
				Pattern pattern = Pattern.compile("[1-9]|[1-3][0-9]|4[0-5]");  
				assertTrue(pattern.matcher(singleNumber).matches());
			}
		}
	}
	
}
