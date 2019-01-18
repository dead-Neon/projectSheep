package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void positiveToPositive() {
		assertEquals(8, Main.pwr(2,3));
	}
	@Test
	void negativeToOddPositive() {
		assertEquals(-8, Main.pwr(-2,3));
	}
	@Test
	void negativeToEvenPositive() {
		assertEquals(4, Main.pwr(-2,2));
	}
	@Test
	void positiveToPowerZero() {
		assertEquals(1, Main.pwr(2,0));
	}
	@Test
	void zeroToPositive() {
		assertEquals(0, Main.pwr(0,4));
	}

}
