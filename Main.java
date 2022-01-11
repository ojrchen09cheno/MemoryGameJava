package memoryGame;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		System.out.println("Welcome to a Java Memory Game");
		System.out.println("Please input the size of the game you want to play.");
		System.out.println("Make sure it is a positive even integer.");
		System.out.println("After selecting the size, you will have 10 seconds to memorize the number pairs: ");

		//Input scanner for game
		Scanner input = new Scanner(System.in);
		int size = 0;
		while(true) {
			if(input.hasNextInt()) {
				size = input.nextInt();
				if(size != 0 && size%2 == 0 )
					break;
				else
					System.out.println("Invalid input. Please type a positive even number.");
			}
			else {
				System.out.println("Invalid input. Please type a positive even number.");
				input.next();
			}

		}
		// counter used to keep track of how many pairs have been found
		int counter = 0;
		// done is the amount of pairs the game has
		int done = size*size / 2;

		//Generate the symbol pairs for the arrangement. This creates a list of numbers from 0 to half size of the arrangement
		List<Integer> ints = IntStream.rangeClosed(1, size*size/2).boxed().collect(Collectors.toList());

		//Duplicate previous list to generate pairs
		ints.addAll(ints);

		//Shuffle the list
		Collections.shuffle(ints);

		//Generate the 2d array from the list of pairs
		List<Card> cards = new ArrayList<>();
		for(int i = 0; i < ints.size(); i++) {
			cards.add(new Card(ints.get(i)));
		}
		List<List<Card>> matrix = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			matrix.add(cards.subList(i * size, (i + 1) * size));
		}

		//Print card arrangement and change starting boolean to false, so card numbers are not printed anymore
		System.out.println();
		System.out.printf("     ");
		for(int j = 1; j < size+1; j++) {
			System.out.printf("%3s", j);
			System.out.printf("   ");
		}
		System.out.println();
		System.out.println();
		for(int i = 0; i <matrix.size(); i++) {
			System.out.printf("%-5s", i+1);
			matrix.get(i).get(0).print();
			matrix.get(i).get(0).setStarting(false);
			for(int j = 1; j < matrix.get(i).size(); j++) {
				System.out.print(" - ");
				matrix.get(i).get(j).print();
				matrix.get(i).get(j).setStarting(false);
			}
			System.out.println();
		}

		//Sleep for 10 seconds to let the player memorize
		TimeUnit.SECONDS.sleep(10);

		//Loop until game completion
		while(counter != done) {

			//Done on Eclipse IDE. Line breaks used to simulate clear console
			for(int i = 0; i < 50; i++) {
				System.out.println();
			}

			//Print game arrangement
			System.out.printf("     ");
			for(int j = 1; j < size+1; j++) {
				System.out.printf("%3s", j);
				System.out.printf("   ");
			}
			System.out.println();
			System.out.println();
			for(int i = 0; i <matrix.size(); i++) {
				System.out.printf("%-5s", i+1);
				matrix.get(i).get(0).print();
				for(int j = 1; j < matrix.get(i).size(); j++) {
					System.out.print(" - ");
					matrix.get(i).get(j).print();
				}
				System.out.println();
			}

			//First card selection
			System.out.println();
			int y1;
			int x1;
			Card first;
			int y2;
			int x2;
			Card second;
			while(true) {
				System.out.println("Type the column of the first card you want to pick: ");
				while(true) {
					if(input.hasNextInt()) {
						y1 = input.nextInt()-1;
						if(y1 < size && y1 >= 0)
							break;
						else
							System.out.println("Invalid input. Please type a valid column.");
					}
					else {
						System.out.println("Invalid input. Please type a valid column.");
						input.next();
					}

				}
				System.out.println("Type the row of the first card you want to pick: ");
				while(true) {
					if(input.hasNextInt()) {
						x1 = input.nextInt()-1;
						if(x1 < size && x1 >= 0)
							break;
						else
							System.out.println("Invalid input. Please type a valid row.");
					}
					else {
						System.out.println("Invalid input. Please type a valid row.");
						input.next();
					}

				}
				if(!matrix.get(y1).get(x1).isFound()) {
					first = matrix.get(y1).get(x1);
					first.setChosen(true);		
					break;
				}
				else
					System.out.println("This card has already been solved. Please pick another one.");
			}


			//Print card arrangement with selected card
			System.out.println();
			System.out.printf("     ");
			for(int j = 1; j < size+1; j++) {
				System.out.printf("%3s", j);
				System.out.printf("   ");
			}
			System.out.println();
			System.out.println();
			for(int i = 0; i <matrix.size(); i++) {
				System.out.printf("%-5s", i+1);
				matrix.get(i).get(0).print();
				for(int j = 1; j < matrix.get(i).size(); j++) {
					System.out.print(" - ");
					matrix.get(i).get(j).print();
				}
				System.out.println();
			}

			//Second card selection
			System.out.println();
			while(true) {
				System.out.println("Type the column of the second card you want to pick: ");
				while(true) {
					if(input.hasNextInt()) {
						y2 = input.nextInt()-1;
						if(y2 < size && y2 >= 0)
							break;
						else
							System.out.println("Invalid input. Please type a valid column.");
					}
					else {
						System.out.println("Invalid input. Please type a valid column.");
						input.next();
					}

				}
				System.out.println("Type the row of the second card you want to pick: ");
				while(true) {
					if(input.hasNextInt()) {
						x2 = input.nextInt()-1;
						if(x2 < size && x2 >= 0)
							break;
						else
							System.out.println("Invalid input. Please type a valid row");
					}
					else {
						System.out.println("Invalid input. Please type a valid row.");
						input.next();
					}

				}
				if(x1 == x2 && y1 == y2) 
					System.out.println("You can't choose the same card.");
				else if(!matrix.get(y2).get(x2).isFound()) {
					second = matrix.get(y2).get(x2);
					second.setChosen(true);		
					break;
				}
				else
					System.out.println("This card has already been solved. Please pick another one.");
			}

			//Check if pair or not pair
			System.out.println();
			if(first.getNumber() == second.getNumber()) {
				first.setFound(true);
				second.setFound(true);
				System.out.println("Found a pair!");
				counter++;
			}
			else {
				first.setChosen(false);
				second.setChosen(false);
				System.out.println("Not a pair");
			}

			TimeUnit.SECONDS.sleep(2);

		}
		System.out.println("Congratulations! You Finished!");

	}
}
