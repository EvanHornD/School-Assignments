package CL_3;

public class WordleLetter {
	private char letter;
	private String color;

	public WordleLetter(char letterIn){
		this.letter = letterIn;
		this.color = null; // color is not set initially
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}

	public boolean isColorSet() {
		return this.color != null;
	}

	public boolean isGreen() {
		return "green".equals(this.color);
	}

	public String toString() {
		String colorCode;
		if("green".equals(color)) {
			colorCode = "\u001B[32m";
		} else if("yellow".equals(color)) {
			colorCode = "\u001B[33m";
		} else {
			colorCode = "\u001B[31m";
		}
	
		String resetCode = "\u001B[0m";
		return String.format("%s %s %s", colorCode, letter, resetCode);
	}
}
