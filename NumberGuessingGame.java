import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    static final int MAX_ATTEMPTS = 7;
    static final int LOWER_BOUND = 1;
    static final int UPPER_BOUND = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int roundsPlayed = 0;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Challenge!");
        System.out.println("Try to guess the number I'm thinking of between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
        System.out.println("You only get " + MAX_ATTEMPTS + " attempts per round. Let's go!\n");

        while (playAgain) {
            int secretNumber = random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
            int attemptsLeft = MAX_ATTEMPTS;
            boolean guessedCorrectly = false;

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (" + attemptsLeft + " left): ");
                int guess;

                // Handle invalid input
                try {
                    guess = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("That doesn't look like a number. Try again.");
                    continue;
                }

                if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                    System.out.println("Please guess a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
                    continue;
                }

                if (guess == secretNumber) {
                    System.out.println("Nice one! You guessed it right.");
                    guessedCorrectly = true;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low. Try something higher.");
                } else {
                    System.out.println("Too high. Try something lower.");
                }

                attemptsLeft--;
            }

            roundsPlayed++;

            if (guessedCorrectly) {
                int roundScore = attemptsLeft * 10;
                totalScore += roundScore;
                System.out.println("You scored " + roundScore + " points this round!");
            } else {
                System.out.println("Out of attempts! The number was: " + secretNumber);
            }

            System.out.print("\n Want to play another round? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
            System.out.println();
        }

        System.out.println(" Game Over. You played " + roundsPlayed + " round(s).");
        System.out.println(" Final Score: " + totalScore + " points.");
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
