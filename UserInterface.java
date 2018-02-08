
package calculator;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import calculator.BusinessLogic;

/**
 * <p> Title: UserInterface Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface for the calculator. The class works with String
 * objects and passes work to other classes to deal with all other aspects of the computation.</p>
 * 
 * * @author Shivam Singhal
 * 
 * <p> Copyright: Lynn Robert Carter © 2017 </p>
 * 
 
 * 
 * @version 4.00	2018-02-09 The JavaFX-based GUI for the implementation of a calculator
 * 
 */

public class UserInterface {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */
	private final double BUTTON_WIDTH = 60;
	private final double BUTTON_OFFSET = BUTTON_WIDTH / 2;

	// These are the application values required by the user interface
	private Label label_IntegerCalculator = new Label("Double Calculator");
	private Label label_Operand1 = new Label("First operand");
	private TextField text_Operand1 = new TextField();
	private Label label_Operand2 = new Label("Second operand");
	private TextField text_Operand2 = new TextField();
	private Label label_Result = new Label("Result");
	private TextField text_Result = new TextField();
	private Button button_Add = new Button("+");
	private Button button_Sub = new Button("-");
	private Button button_Mpy = new Button("\u00D7");				// The multiply symbol: \u00D7
	private Button button_Div = new Button("\u00F7");				// The divide symbol: \u00F7
	private Button button_sqrt = new Button("\u221A");				// The divide symbol: \u221A
	private Label label_errOperand1 = new Label("");
	private Label label_errOperand2 = new Label("");
	private Label label_errResult = new Label("");
	// If the multiplication and/or division symbols do not display properly, replace the 
	// quoted strings used in the new Button constructor call with the <backslash>u00xx values
	// shown on the same line. This is the Unicode representation of those characters and will
	// work regardless of the underlying hardware being used.
	
	private double buttonSpace;		// This is the white space between the operator buttons.
	
	/* This is the link to the business logic */
	public BusinessLogic perform = new BusinessLogic();
	
	

	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method initializes all of the elements of the graphical user interface. These assignments
	 * determine the location, size, font, color, and change and event handlers for each GUI object.
	 */
	public UserInterface(Pane theRoot) {
				
		// There are five gaps. Compute the button space accordingly.
		buttonSpace = Calculator.WINDOW_WIDTH / 6;
		
		// Label theScene with the name of the calculator, centered at the top of the pane
		setupLabelUI(label_IntegerCalculator, "Arial", 24, Calculator.WINDOW_WIDTH, Pos.CENTER, 0, 10);
		
		// Label the first operand just above it, left aligned
		setupLabelUI(label_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 40);
		
		// Establish the first text input operand field and when anything changes in operand 1,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand1, "Arial", 18, Calculator.WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 70, true);
		text_Operand1.textProperty().addListener((observable, oldValue, newValue) -> {setOperand1(); });
		// Move focus to the second operand when the user presses the enter (return) key
		text_Operand1.setOnAction((event) -> { text_Operand2.requestFocus(); });
		
		// Establish an error message for the first operand just above it with, left aligned
		setupLabelUI(label_errOperand1, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 45);
		label_errOperand1.setTextFill(Color.RED);
		
		// Label the second operand just above it, left aligned
		setupLabelUI(label_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 135);
		
		// Establish the second text input operand field and when anything changes in operand 2,
		// process both fields to ensure that we are ready to perform as soon as possible.
		setupTextUI(text_Operand2, "Arial", 18, Calculator.WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 160, true);
		text_Operand2.textProperty().addListener((observable, oldValue, newValue) -> {setOperand2(); });
		// Move the focus to the result when the user presses the enter (return) key
		text_Operand2.setOnAction((event) -> { text_Result.requestFocus(); });
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errOperand2, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 135);
		label_errOperand2.setTextFill(Color.RED);
		
		// Label the result just above the result output field, left aligned
		setupLabelUI(label_Result, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 10, 220);
		
		// Establish the result output field.  It is not editable, so the text can be selected and copied, 
		// but it cannot be altered by the user.  The text is left aligned.
		setupTextUI(text_Result, "Arial", 18, Calculator.WINDOW_WIDTH-20, Pos.BASELINE_LEFT, 10, 250, false);
		
		// Establish an error message for the second operand just above it with, left aligned
		setupLabelUI(label_errResult, "Arial", 18, Calculator.WINDOW_WIDTH-10, Pos.BASELINE_LEFT, 400, 195);
		label_errResult.setTextFill(Color.RED);
		
		// Establish the ADD "+" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Add, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 1 * buttonSpace-BUTTON_OFFSET, 300);
		button_Add.setOnAction((event) -> { addOperands(); });
		
		// Establish the SUB "-" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Sub, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 2 * buttonSpace-BUTTON_OFFSET, 300);
		button_Sub.setOnAction((event) -> { subOperands(); });
		
		// Establish the MPY "x" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Mpy, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 3 * buttonSpace-BUTTON_OFFSET, 300);
		button_Mpy.setOnAction((event) -> { mpyOperands(); });
		
		// Establish the DIV "/" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Div, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 4 * buttonSpace-BUTTON_OFFSET, 300);
		button_Div.setOnAction((event) -> { divOperands(); });
		
		// Establish the sqrt "" button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_sqrt, "Symbol", 32, BUTTON_WIDTH, Pos.BASELINE_LEFT, 5 * buttonSpace-BUTTON_OFFSET, 300);
		button_sqrt.setOnAction((event) -> { sqrtOperands(); });
		// Place all of the just-initialized GUI elements into the pane
		theRoot.getChildren().addAll(label_IntegerCalculator, label_Operand1, text_Operand1, label_errOperand1, 
				label_Operand2, text_Operand2, label_errOperand2, label_Result, text_Result, label_errResult, 
				button_Add, button_Sub, button_Mpy, button_Div, button_sqrt);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method to set the value of the first operand given a text value. The method uses the
	 * business logic class to perform the work of checking the string to see it is a valid value and if 
	 * so, saving that value internally for future computations. If there is an error when trying to convert
	 * the string into a value, the called business logic method returns false and actions are taken to
	 * display the error message appropriately.
	 */
	private void setOperand1() {
		text_Result.setText("");								// Any change of an operand probably invalidates
		label_Result.setText("Result");						// the result, so we clear the old result.
		label_errResult.setText("");
		if (perform.setOperand1(text_Operand1.getText())) {	// Set the operand and see if there was an error
			label_errOperand1.setText("");					// If no error, clear this operands error
			if (text_Operand2.getText().length() == 0)		// If the other operand is empty, clear its error
				label_errOperand2.setText("");				// as well.
		}
		else 												// If there's a problem with the operand, display
			label_errOperand1.setText(perform.getOperand1ErrorMessage());	// the error message that was generated
	}
	
	
	/**********
	 * Private local method to set the value of the second operand given a text value. The logic is exactly the
	 * same as used for the first operand, above.
	 */
	private void setOperand2() {
		text_Result.setText("");								// See setOperand1's comments. The logic is the same!
		label_Result.setText("Result");				
		label_errResult.setText("");
		if (perform.setOperand2(text_Operand2.getText())) {
			label_errOperand2.setText("");
			if (text_Operand1.getText().length() == 0)
				label_errOperand1.setText("");
		}
		else
			label_errOperand2.setText(perform.getOperand1ErrorMessage());
	}
	
	
	/**********
	 * This method is called when an binary operation button has been pressed. It assesses if there are issues 
	 * with either of the binary operands or they are not defined. If not return false (there are no issues)
	 * 
	 * @return	True if there are any issues that should keep the calculator from doing its work.
	 */
	private boolean binaryOperandIssues() {
		String errorMessage1 = perform.getOperand1ErrorMessage();	// Fetch the error messages, if there are any
		String errorMessage2 = perform.getOperand2ErrorMessage();
		if (errorMessage1.length() > 0) {						// Check the first.  If the string is not empty
			label_errOperand1.setText(errorMessage1);			// there's an error message, so display it.
			if (errorMessage2.length() > 0) {					// Check the second and display it if there is
				label_errOperand2.setText(errorMessage2);		// and error with the second as well.
				return true;										// Return true when both operands have errors
			}
			else {
				return true;										// Return true when only the first has an error
			}
		}
		else if (errorMessage2.length() > 0) {					// No error with the first, so check the second
			label_errOperand2.setText(errorMessage2);			// operand. If non-empty string, display the error
			return true;											// message and return true... the second has an error
		}														// Signal there are issues
		
		// If the code reaches here, neither the first nor the second has an error condition. The following code
		// check to see if the operands are defined.
		if (!perform.getOperand1Defined()) {						// Check to see if the first operand is defined
			label_errOperand1.setText("No value found");			// If not, this is an issue for a binary operator
			if (!perform.getOperand2Defined()) {					// Now check the second operand. It is is also
				label_errOperand2.setText("No value found");		// not defined, then two messages should be displayed
				return true;										// Signal there are issues
			}
			return true;
		} else if (!perform.getOperand2Defined()) {				// If the first is defined, check the second. Both
			label_errOperand2.setText("No value found");			// operands must be defined for a binary operator.
			return true;											// Signal there are issues
		}
		
		return false;											// Signal there are no issues with the operands
	}

	/*******************************************************************************************************
	 * This portion of the class defines the actions that take place when the various calculator
	 * buttons (add, subtract, multiply, and divide) are pressed.
	 */

	/**********
	 * This is the add routine
	 * 
	 */
	private void addOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.addition();						// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Addition");								// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}

	/**********
	 * This is the subtract routine
	 * 
	 */
	private void subOperands(){
		// Check to see if both operands are defined and valid
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the subtraction and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.subtraction();						// Call the business logic sub method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Subtraction");								// change the title of the field to "Subtraction"
		}
		else {														// Some error occurred while doing the Subtraction.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}								
	}

	/**********
	 * This is the multiply routine
	 * 
	 */
	private void mpyOperands(){
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;													// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the Multiplication and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.multiplication();						// Call the business logic mpy method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Multiplication");								// change the title of the field to "Multiplication"
		}
		else {														// Some error occurred while doing the Multiplication.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}



	/**********
	 * This is the divide routine.  If the divisor is zero, the divisor is declared to be invalid.
	 * 
	 */
	private void divOperands(){
		if (binaryOperandIssues()) 									// If there are issues with the operands, return
			return;												// without doing the computation
		
		// If the operands are defined and valid, request the business logic method to do the division and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.division();						// Call the business logic add method
		label_errResult.setText("");										// Reset any result error messages from before
		if ((theAnswer.equals("Infinity"))) {								// Check the returned String to see if it is okay

			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Division");							// Reset the result label if there is an error.
			label_errResult.setText("Division by Zero Not Possible");	// Display the error message.
									// change the title of the field to "Division"
			}
		if(theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Division");	// Some error occurred while doing the division .
			
		}
		else {														// Some error occurred while doing the Multiplication.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}

}
	/**********
	 * This is the Square root routine
	 * 
	 */
	private void sqrtOperands(){
		// Check to see if both operands are defined and valid
		
		// If the operands are defined and valid, request the business logic method to do the addition and return the
		// result as a String. If there is a problem with the actual computation, an empty string is returned
		String theAnswer = perform.squareroot();						// Call the business logic add method
		label_errResult.setText("");									// Reset any result error messages from before
		if (theAnswer.length() > 0) {								// Check the returned String to see if it is okay
			text_Result.setText(theAnswer);							// If okay, display it in the result field and
			label_Result.setText("Square Root ");								// change the title of the field to "Sum"
		}
		else {														// Some error occurred while doing the addition.
			text_Result.setText("");									// Do not display a result if there is an error.				
			label_Result.setText("Result");							// Reset the result label if there is an error.
			label_errResult.setText(perform.getResultErrorMessage());	// Display the error message.
		}
	}
}
