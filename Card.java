package memoryGame;

public class Card {
	
	/**
	 * Attributes used to run the game.
	 * number is the symbol used to check for pairs
	 * found, chosen, and starting boolean used when a pair is found, chosen or game is starting
	 * this affects how the game is printed
	 */
	
	private int number;
	private boolean found = false;
	private boolean chosen = false;
	
	//starting boolean is true to print the card arrangement once showing the cards
	private boolean starting = true;
	
	//Constructor, getters, and setters
	public Card(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	public boolean isStarting() {
		return starting;
	}

	public void setStarting(boolean starting) {
		this.starting = starting;
	}
	
	//Print card depending on boolean attributes
	//Print if chosen or starting
	//Print nothing if found
	//Else print X
	public void print() {
		if(starting)
			System.out.printf("%3s", number);
		else if(found)
			System.out.printf("   ");
		else if(chosen)
			System.out.printf("%3s", number);
		else
			System.out.printf(" X ");
	}
	

}
