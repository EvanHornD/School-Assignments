package CL_3;

public class WordleLetter {
	private char letter;
	private String color;

	public WordleLetter(char letterIn){
		this.letter = letterIn;
	}

	public void setColor(String colorIn){
		if(colorIn.equals("green")||colorIn.equals("yellow")||colorIn.equals("red")){
			this.color = colorIn;
		}
	}

	//gets the character that this letter has assigned to it, I replaced the methods that get the color of the letter with this.
	public char getChar(){
		return this.letter;
	}

	public String toString() {
		/*	Determine the special characters to add at the beginning of the String
			to change the text color to the right color. */
		String colorCode;
		if(color.equals("green")) {
			colorCode = "\u001B[32m";
		} else if(color.equals("yellow")) {
			colorCode = "\u001B[33m";
		} else {
			colorCode = "\u001B[31m";
		}
	
		/*	These are the special character to add 
			to the end of the String 
			to signify the end of the color change. */
		String resetCode = "\u001B[0m";

		/*  Surround the letter with space characters and with 
			the above color changing special characters. */ 
		return String.format(
			"%s %s %s",
			colorCode, letter, resetCode);
	}
}
