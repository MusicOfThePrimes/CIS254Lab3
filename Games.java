/**
 * Games.java
 *
 * This is a simple Java program that allows the user to play two different games:
 * 1. A Number Guessing Game
 * 2. A Rock-Paper-Scissors Game
 *
 * The program presents a menu allowing the user to choose a game to play, and gives 
 * them the option to replay the games multiple times without restarting the program.
 *
 * <p><b>Features:</b></p>
 * <ul>
 *     <li>Number Guessing Game: The user has 5 tries to guess a randomly generated number between 1 and 100.</li>
 *     <li>Rock-Paper-Scissors: The user plays against the computer using number inputs (1 = rock, 2 = paper, 3 = scissors).</li>
 *     <li>Main menu with game selection and exit.</li>
 * </ul>
 *
 * <p>This class was developed collaboratively by:</p>
 * <ul>
 *     <li><b>Lab2 Partner</b>: Implemented the Number Guessing Game.</li>
 *     <li><b>Huilin Reid</b>: Implemented the Rock-Paper-Scissors Game.</li>
 * </ul>
 *
 * @author Lab2 Partner
 * @author Huilin Reid
 * @version 1.0
 * @since 10-03-2025
 */

import java.util.Random;
import java.util.Scanner;

public class Games {
    /**
     * Game one: Guessing Game
     * 
     * This method runs an interactive number guessing game.
     * <p>
     * The computer randomly selects a number between 1 and 100,
     * and the user is given 5 tries to guess the number. After each guess,
     * the user is informed whether the guess was too high or too low.
     * If the user guesses correctly, a success message is displayed.
     * If the user runs out of tries, the correct number is revealed.
     * <p>
     * After each game, the user is prompted to play again. The game continues
     * until the user chooses not to play again.
     *
     * @param input A Scanner object used to read user input from the console.
     */
    public static void guessingGame(Scanner input) {
        final int MIN = 1;
        final int MAX = 100;
        final int MAX_TRIES = 5;
        Random rand = new Random();

        String playAgain;

        do {
            int numberToGuess = rand.nextInt(MAX - MIN + 1) + MIN;
            int triesLeft = MAX_TRIES;
            boolean guessedCorrectly = false;

            System.out.println("\nI'm thinking of a number between " + MIN + " and " + MAX + ".");
            System.out.print("Guess what it is. You have " + triesLeft + " tries: ");

            while(triesLeft > 0) {
                int guess = input.nextInt();
                triesLeft--;

                if(guess == numberToGuess) {
                    System.out.println("You got it!\n");
                    guessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    if (triesLeft > 0) {
                        System.out.print("Nope! Too low. Try again (" + triesLeft + (triesLeft == 1 ? " try" : " tries") + " left): ");
                    } 
                } else {
                     if (triesLeft > 0) {
                        System.out.print("Nope! Too high. Try again (" + triesLeft + (triesLeft == 1 ? " try" : " tries") + " left): ");
                    }        
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Nope! You lost. The number was " + numberToGuess + ".\n");
            }

            System.out.print("Do you want to play again? (Y/N): ");
            playAgain = input.next();
            input.nextLine(); // clear newline

        } while (playAgain.equalsIgnoreCase("Y"));
    }

    /**
     * Game two: Rock-Paper-Scissors
     * 
     * This method runs an interactive Rock-Paper-Scissors game against the computer.
     * <p>
     * The user selects one of three options: rock (1), paper (2), or scissors (3),
     * and the computer randomly selects its own choice. The winner is determined
     * based on traditional Rock-Paper-Scissors rules.
     * <p>
     * If both the user and the computer make the same choice, the game is a tie.
     * After each round, the user is asked if they want to play again.
     * The game continues until the user chooses not to play again.
     *
     * @param input A Scanner object used to read user input from the console.
     */

    public static void rockPaperScissorsGame(Scanner input) {
        Random rand = new Random();
        String playAgain = "Y";

        do {
            System.out.print("Enter you choice: 1. rock, 2. paper, 3. scissors: ");
            int userChoice = input.nextInt();
            int computerChoice = rand.nextInt(3) + 1;
            input.nextLine(); // clear newline

            System.out.println("Computer chose: " + convertChoice(computerChoice));
            System.out.println("You chose: " + convertChoice(userChoice));

            if (userChoice == computerChoice) {
                System.out.println("It is a tie!\n");
            } else if (
                (userChoice == 1 && computerChoice == 3) ||
                (userChoice == 2 && computerChoice == 1) ||
                (userChoice == 3 && computerChoice == 2)
             ) {
                System.out.println("You win!\n");
            } else {
                System.out.println("Computer wins!\n");
            }

            System.out.print("Do you want to play again? (Y/N): ");
            playAgain = input.nextLine();

        } while (playAgain.equalsIgnoreCase("Y"));    
    }

    /**
     * Helper method:
     * Converts a numeric choice to its corresponding Rock-Paper-Scissors move.
     * <p>
     * This method maps the user's or computer's numeric input to a readable
     * string representing the chosen move:
     * <ul>
     *     <li>1 → "Paper"</li>
     *     <li>2 → "Scissors"</li>
     *     <li>3 → "Rock"</li>
     * </ul>
     * If the input does not match one of these values, "Invalid" is returned.
     *
     * @param choice an integer representing the player's or computer's choice
     * @return a String representation of the move ("Rock", "Paper", "Scissors"),
     *         or "Invalid" if the input is not 1, 2, or 3
     */
    public static String convertChoice(int choice) {
        switch(choice) {
            case 1: 
                return "Rock"; 
            case 2: 
                return "Paper"; 
            case 3: 
                return "Scissors"; 
            default: 
                return "Invalid";
        }
    }

    /**
     * This method displays the main menu for the game program and handles user navigation.
     * <p>
     * The user can choose to play the Number Guessing Game (1), the Rock-Paper-Scissors Game (2),
     * or exit (3) the program. The menu reappears after each game unless the user selects "Exit (3)."
     * <p>
     * Input is validated to ensure the user selects one of the available options (1–3).
     *
     * @param input A Scanner object used to read user input from the console.
     */
    public static void mainMenu(Scanner input) {
        int choice;
        do {
            System.out.println("\n======== Main Menu ========");
            System.out.println("1. Play Guessing Game");
            System.out.println("2. Play Rock-Paper-Scissors");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            while (!input.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-3): ");
                input.next();
            }

            choice = input.nextInt();
            input.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    guessingGame(input);
                    break;
                case 2:
                    rockPaperScissorsGame(input);
                    break;
                case 3:
                    System.out.println("Thanks for playing!\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.\n");
            }

        } while (choice != 3);
    }

    /** The entry point of the program.
     * <p>
     * This method initializes a Scanner for reading user input
     * and calls the mainMenu() method to start the interactive
     * game menu, allowing the user to choose between playing the
     * Number Guessing Game or the Rock-Paper-Scissors Game.
     * <p>
     * The program continues running until the user chooses to exit from the menu.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        mainMenu(input);
        input.close();
    }   
}
