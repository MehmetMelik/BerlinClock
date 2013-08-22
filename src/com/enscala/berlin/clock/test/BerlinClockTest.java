/**
 * 
 */
package com.enscala.berlin.clock.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.enscala.berlin.clock.domain.BerlinClock;
import com.enscala.berlin.clock.exception.BerlinClockPatternException;

/**
 * @author Melik
 *
 */
public class BerlinClockTest {
	
	private BerlinClock bc;
	
	@Before
	public void init() {
		try {
			bc = new BerlinClock("23:59:00");
		} catch (BerlinClockPatternException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=BerlinClockPatternException.class)
	public void initByInvalidClockPattern() throws BerlinClockPatternException {
		new BerlinClock("000:59:00");
	}
	
	@Test
	public void testGetSeconds() {
		assertEquals(new Integer(0), bc.getSeconds());
	}
	
	@Test
	public void testGetClockArray() {
		assertEquals(3, bc.getClockArray().length);
	}
	
	@Test
	public void testGetMinutes() {
		assertEquals(new Integer(59), bc.getMinutes());
	}
	
	@Test
	public void testGetHours() {
		assertEquals(new Integer(23), bc.getHours());
	}
	
	@Test
	public void testReadSeconds() {
		assertEquals("Y", bc.readSeconds());
	}
	
	@Test
	public void testReadMinutes() {
		assertEquals("YYRYYRYYRYY\nYYYY", bc.readMinutes());
	}
	
	@Test
	public void testReadHours() {
		assertEquals("RRRR\nRRRO", bc.readHours());
	}
	
	@Test
	public void testReadClock() throws BerlinClockPatternException {
		assertEquals("Y\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY", bc.readClock());
		
		assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", new BerlinClock("00:00:00").readClock());
		assertEquals("O\nRROO\nRRRO\nYYROOOOOOOO\nYYOO", new BerlinClock("13:17:01").readClock());
		assertEquals("O\nRRRR\nRRRO\nYYRYYRYYRYY\nYYYY", new BerlinClock("23:59:59").readClock());
		assertEquals("Y\nRRRR\nRRRR\nOOOOOOOOOOO\nOOOO", new BerlinClock("24:00:00").readClock());
	}
	

}
