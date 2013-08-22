/**
 * 
 */
package com.enscala.berlin.clock.domain;

import com.enscala.berlin.clock.exception.BerlinClockPatternException;

/**
 * @author Melik
 *
 */
public class BerlinClock {
	
	private final static String RED = "R";
	private final static String YELLOW = "Y";
	private final static String OFF = "O";
	private String clock;
	private String[] clockArray;
	private final String clockPattern = "([01]?[0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9]";
	
	/**
	 * 
	 * @param clock
	 * @throws BerlinClockPatternException
	 */
	public BerlinClock(String clock) throws BerlinClockPatternException {
		if (clock.matches(clockPattern)) {
			this.clock = clock;
			clockArray = this.getClockArray();
		}
		else
			throw new BerlinClockPatternException();
	}

	/**
	 * reads seconds
	 * @return
	 */
	public String readSeconds() {
		int sec = this.getSeconds(); 
		return (sec%2 == 0) ? YELLOW : OFF;
	}

	/**
	 * extracts hours seconds digital hour
	 * @return
	 */
	public Integer getSeconds() {
		
		return Integer.parseInt(clockArray[2]);
	}
	
	/**
	 * gets time digits to an array
	 * @return
	 */
	public String[] getClockArray() {
		
		return clock.split(":");
	}

	/**
	 * extracts minutes from digital hour
	 * @return
	 */
	public Integer getMinutes() {
		
		return Integer.parseInt(clockArray[1]);
	}

	/**
	 * extracts hours from digital hour
	 * @return
	 */
	public Integer getHours() {
		
		return Integer.parseInt(clockArray[0]);
	}
	
	/**
	 * reads minutes
	 * @return
	 */
	public String readMinutes() {
		int min = this.getMinutes();
		StringBuffer resCol1 = new StringBuffer();
		StringBuffer resCol2 = new StringBuffer();
		resCol1 = getRow(min, YELLOW, true, 11, RED);
		resCol2 = getRow(min, YELLOW, false, 4, YELLOW);
		resCol1.append("\n").append(resCol2).trimToSize();
		return resCol1.toString();
		
	}

	/**
	 * reads hours
	 * @return
	 */
	public String readHours() {
		int hours = this.getHours();
		StringBuffer resCol1 = new StringBuffer();
		StringBuffer resCol2 = new StringBuffer();
		resCol1 = getRow(hours, RED, true, 4, RED);
		resCol2 = getRow(hours, RED, false, 4, RED);
		resCol1.append("\n").append(resCol2).trimToSize();
		return resCol1.toString();
	}
	
	/**
	 * reads clock
	 * @return
	 */
	public String readClock() {
		return readSeconds()+"\n"
				+readHours()+"\n"
				+readMinutes();
	}
	/**
	 * converts time to "lamp" format.
	 * @param t	time
	 * @param color	firstColor 
	 * @param divisor	if true, divide by 5, if false, use remainder operator
	 * @param boundary	number of "lamps"
	 * @param dualColor	color of the second lamp, may have the same color with first color.
	 * @return
	 */
	public StringBuffer getRow(int t, String color, boolean divisor, int boundary, String dualColor) {
		StringBuffer resCol = new StringBuffer();
		int val;
		if (divisor) 
			val = t / 5;
		else
			val = t % 5;
		for(int i = 1; i<=boundary; i++) {
			if(i<=val) {
				if(i%3==0)
					resCol.append(dualColor);
				else
					resCol.append(color);
			}
			else
				resCol.append(OFF);
		}
		resCol.trimToSize();
		return resCol;
	}
}
