package nl.arthurvlug.binairo.board;

public enum Field {
	EMPTY('.'), ONE('1'), ZERO('0');
	
	private char value;

	Field(char value) {
		this.value = value;
	}
	
	public char toChar() {
		return value;
	}

	public Field invert() {
		if(this == EMPTY) {
			throw new RuntimeException("Can't invert an empty value");
		}
		
		if(this == ONE) {
			return ZERO;
		} else {
			return ONE;
		}
	}
}
