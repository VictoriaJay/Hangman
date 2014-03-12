import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HangmanAgain {
	
	
	public static void HangPersonGame() throws IOException{
		
		//Gets words from text file
		String[] wordlist = getWords();
						
		//Picks a word, gets an array of the letters		
		String[] choice = pickWord(wordlist);
		
		//Makes a displayable list of empty spaces
		String[] displayed = new String[choice.length];
		for (int i = 0; i < choice.length ;i++){
			displayed[i] = "_";
		}
		
		//Preamble to the game
		int numberOfLives = 6;
		ArrayList<String> guesses  = new ArrayList<String>();
		
		Scanner input = new Scanner(System.in);
		System.out.println("A Pokemon has been chosen!");
		System.out.println(buildWord(displayed));
		System.out.println("You have 6 lives!");
		boolean gameOn = true;
		
		//Main game loop
		while (gameOn == true){

			//Takes an input
			System.out.println("Guess a letter:");
			String guess = input.nextLine();
			
			//Adds the new guess to the list of guesses
			guesses.add(guess);
			
			//Used for logic later, but needed to be reset each time round
			boolean InWord = false;
			boolean winner = false;
			
			//Checks input against each letter
			for (int index=0; index < choice.length ; index++){
				
				if (choice[index].equals(guess)){
					displayed[index] = guess;
					InWord = true;
				}
				
			}
			
			//Displays the list of displayed characters
			System.out.println(buildWord(displayed));
			System.out.println(" ");
			
			
			if (InWord == false){
				
				//Incorrect guess
				
				
				numberOfLives -= 1;
				displayGallows(numberOfLives);
				
				//If you run out of lives:
				if (numberOfLives == 0){
					System.out.println("Incorrect!");
					System.out.println("You have " + Integer.toString(numberOfLives) + " lives left!");
					System.out.println("You have guessed the following letters: " + guesses);
					System.out.println("The Pokemon was " + buildWord(choice) + "!");
					gameOn = false;
					break;
				}
				
				System.out.println("Incorrect!");
				System.out.println("You have " + Integer.toString(numberOfLives) + " lives left!");
				System.out.println("You have guessed the following letters: " + guesses);
				
			} 
			
			else{
				
				//Correct guess
				
				
				System.out.println("Correct!");
				System.out.println("You have guessed the following letters: " + guesses);
				
				for (int index=0; index < choice.length ; index++){
					
					if(displayed[index].equals("_")){
						winner = false;
						break;
					}
					
					else{
						winner = true;
					}
					
				}
			
				}
			
			if (winner == true){
				System.out.println("A winner is you!");
				System.out.println("The Pokemon was " + buildWord(choice) + "!");				
				gameOn = false;
			}

		}

	}

	public static void main(String[] args) throws IOException{
		
		HangPersonGame();
		
	}
	
	public static String[] getWords() throws IOException{
		
		//Makes a thing to read text file
		BufferedReader reader = new BufferedReader(new FileReader("M:/Hangman/Words/plaintextpokemon.txt"));
		
		ArrayList<String> words = new ArrayList<String>();
		
		String pokemon = "";
		while (pokemon != null){
			
			pokemon = reader.readLine();
			words.add(pokemon);

		}
		
		//Takes out null added at the end of this loop above...
		words.remove(words.size()-1);
		
		//Converts back to String array
		String[] finalList = new String[words.size()];
		words.toArray(finalList);
	
		reader.close();
		return finalList;
	}
	
	public static String[] pickWord(String[] list){

		
		
		//Picks a word from a given list at random
		int wordcount = list.length;
		String chosenPokemon = list[(int )(Math.random()*wordcount)];
		
		String[] letters = chosenPokemon.split("(?!^)");
		letters = Arrays.copyOfRange(letters, 0, letters.length -1);
		return letters;
		
	}
		
	public static void displayGallows(int lives){

		
		
		switch(lives){
			
		case 5:
			System.out.println(' ');
			System.out.println(' ');
			System.out.println(' ');
			System.out.println(' ');
			System.out.println(' ');
			System.out.println("------");
			break;
		
		case 4:
			System.out.println("      ");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("-----+");
			break;
			
		case 3:
			System.out.println("-----+");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("-----+");
			break;
			
		case 2:
			System.out.println("-----+");
			System.out.println("    \\|");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("-----+");
			break;
			
		case 1:
			System.out.println("-----+");
			System.out.println("|   \\|");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("     |");
			System.out.println("-----+");
			break;

		case 0:
			System.out.println("-----+");
			System.out.println("|   \\|");
			System.out.println("o    |");
			System.out.println("+    |");
			System.out.println("^    |");
			System.out.println("-----+");
			break;
				
		}
		
		
		
	}

	public static String buildWord(String[] letters){
		
		String word = "";
		
		int index = letters.length;

		for (int i = 0; i<index;i++){
			if (letters[i] == "_"){
				word =  word + " ";
			}
			word = word + letters[i];	
		}
		
		return word;
	}
}
