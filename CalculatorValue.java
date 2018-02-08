package calculator;

import java.util.Scanner;


/**
 * <p> Title: CalculatorValue Class. </p>
 * 
 * <p> Description: A component of a JavaFX demonstration application that performs computations </p>
 * 

 * 
 * @author Shivam Singhal
 * 
 *  * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 * @version 4.00	2018-02-09 Double integer implementation of the CalculatorValue class 
 * 
 */
public class CalculatorValue {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	// These are the major values that define a calculator value
	double measuredValue = 0;
	String errorMessage = "";
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/*****
	 * This is the default constructor
	 */
	public CalculatorValue() {
	}

	/*****
	 * This constructor creates a calculator value based on a long integer. For future calculators, it
	 * is best to avoid using this constructor.
	 */
	public CalculatorValue(double v) {
		measuredValue = v;
	}

	/*****
	 * This copy constructor creates a duplicate of an already existing calculator value
	 */
	public CalculatorValue(CalculatorValue v) {
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}

	/*****
	 * This constructor creates a calculator value from a string... Due to the nature
	 * of the input, there is a high probability that the input has errors, so the 
	 * routine returns the value with the error message value set to empty or the string 
	 * of an error message.
	 */
	public CalculatorValue(String s) {
		measuredValue = 0;
		if (s.length() == 0) {								// If there is nothing there,
			errorMessage = "***Error*** Input is empty";		// signal an error	
			return;												
		}
		// If the first character is a plus sign, ignore it.
		int start = 0;										// Start at character position zero
		boolean negative = false;							// Assume the value is not negative
		if (s.charAt(start) == '+')							// See if the first character is '+'
			 start++;										// If so, skip it and ignore it
		
		
		
		// If the first character is a minus sign, skip over it, but remember it
		else if (s.charAt(start) == '-'){					// See if the first character is '-'
			start++;											// if so, skip it
			negative = true;									// but do not ignore it
		}

		// See if the user-entered string can be converted into an integer value
		Scanner tempScanner = new Scanner(s.substring(start));// Create scanner for the digits
		if (!tempScanner.hasNextLong()) {					// See if the next token is a valid
			errorMessage = "***Error*** Invalid value"; 		// integer value.  If not, signal there
			tempScanner.close();								// return a zero
			return;												
		}
		
		
		// Convert the user-entered string to a integer value and see if something else is following it
		measuredValue = tempScanner.nextLong();				// Convert the value and check to see
		if (tempScanner.hasNext()) {							// that there is nothing else is 
			errorMessage = "***Error*** Excess data"; 		// following the value.  If so, it
			tempScanner.close();								// is an error.  Therefore we must
			measuredValue = 0;								// return a zero value.
			return;													
		}
		tempScanner.close();
		errorMessage = "";
		if (negative)										// Return the proper value based
			measuredValue = -measuredValue;					// on the state of the flag that
	}

	/**********************************************************************************************

	Getters and Setters
	
	**********************************************************************************************/
	
	/*****
	 * This is the start of the getters and setters
	 * 
	 * Get the error message
	 */
	String divMessage = "Can't divide";
	public String getDivMessage() {
		return divMessage;
	}
	public void setDivMessage(String k) {
		divMessage = k;
		
	}
	public String getErrorMessage(){
		return errorMessage;
	}
	
	/*****
	 * Set the current value of a calculator value to a specific long integer
	 */
	public void setValue(long v){
		measuredValue = v;
	}
	
	/*****
	 * Set the current value of a calculator error message to a specific string
	 */
	public void setErrorMessage(String m){
		errorMessage = m;
	}
	
	/*****
	 * Set the current value of a calculator value to the value of another (copy)
	 */
	public void setValue(CalculatorValue v){
		measuredValue = v.measuredValue;
		errorMessage = v.errorMessage;
	}
	
	/**********************************************************************************************

	The toString() Method
	
	**********************************************************************************************/
	
	/*****
	 * This is the default toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String toString() {
		return measuredValue + "";
	}
	
	/*****
	 * This is the debug toString method
	 * 
	 * When more complex calculator values are creating this routine will need to be updated
	 */
	public String debugToString() {
		return "measuredValue = " + measuredValue + "\nerrorMessage = " + errorMessage + "\n";
	}

	
	/**********************************************************************************************

	The computation methods
	
	**********************************************************************************************/
	

	/*******************************************************************************************************
	 * The following methods implement computation on the calculator values.  These routines assume that the
	 * caller has verified that things are okay for the operation to take place.  These methods understand
	 * the technical details of the values and their reputations, hiding those details from the business 
	 * logic and user interface modules.
	 * 
	 * Since this is addition and we do not yet support units, we don't recognize any errors.
	 */
	public void add(CalculatorValue v) {
		measuredValue += v.measuredValue;
		errorMessage = "";
	}

	/*****
	 * The following methods now implemented.  The code here is now
	 * properly compile and run.
	 * 
	 * @param v
	 */
	public void sub(CalculatorValue v) {
		measuredValue -= v.measuredValue;
		errorMessage = "";
	}

	public void mpy(CalculatorValue v) {
		measuredValue *= v.measuredValue;
		errorMessage = "";
	}

	public void div(CalculatorValue v) {
		measuredValue /= v.measuredValue;
		errorMessage = "Division by Zero not Possible";
	}
	
	public void sqrt(CalculatorValue v) {

		measuredValue= Math.pow( measuredValue, 0.5);
		errorMessage = "";
	}
}
