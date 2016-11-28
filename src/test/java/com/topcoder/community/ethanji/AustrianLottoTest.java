package com.topcoder.community.ethanji;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * Unit test for AustrianLottoTest.
 * 
 * @author xingyuji
 *
 */
public class AustrianLottoTest {

	/**
	 * Check the constrain: drawing contains exactly 6 distinct integers between
	 * 1 and 45, inclusive, each separated by exactly one single space, e.g.
	 * "3 12 13 17 32 45" (quotes for clarity). Integers have no leading zeros.
	 */
	@Test
	public void drawingNumbersValidation() {
		AustrianLotto lotto = new AustrianLotto();
		String drawing = lotto.draw();
		String[] drawnNumbers = drawing.split(" ");
		// drawing contains exactly 6 integers
		assertEquals(6, drawnNumbers.length);
		boolean[] bool = new boolean[45];
		// integers between 1 and 45 and no leading zeros
		for (String singleNumber : drawnNumbers) {
			// distinct integers
			assertFalse(bool[Integer.parseInt(singleNumber)]);
			bool[Integer.parseInt(singleNumber)] = true;
			Pattern pattern = Pattern.compile("[1-9]|[1-3][0-9]|4[0-5]");
			assertTrue(pattern.matcher(singleNumber).matches());
		}
	}

	/**
	 * evaluate validation.
	 */
	@Test
	public void evaluateValidation() {
		AustrianLotto lotto = new AustrianLotto();
		String[] picks = { "40 18 44 3 30 12", "24 32 13 7 40 15",
				"2 6 4 29 31 15", "33 14 36 20 8 6", "7 23 39 29 15 8",
				"20 5 40 17 16 1", "20 17 8 41 30 9" };
		String draw = "17 20 8 9 30 41";
		int[] returns = lotto.evaluate(draw, picks);
		int[] expected = {2, 2, 2, 0, 0, 0, 1};
		assertArrayEquals(expected, returns);
	}
}
