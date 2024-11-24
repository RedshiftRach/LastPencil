package lastpencil;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var random = new Random();
        int pencils;
        String[] players = {"John", "Jack"};
        String currentPlayer;

        // Get number of pencils
        while (true) {
            System.out.println("How many pencils would you like to use:");
            var input = scanner.nextLine();
            try {
                pencils = Integer.parseInt(input);
                if (pencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        // Get the first player
        while (true) {
            System.out.println("Who will be the first (John, Jack):");
            currentPlayer = scanner.nextLine();
            if (!currentPlayer.equals(players[0]) && !currentPlayer.equals(players[1])) {
                System.out.println("Choose between 'John' and 'Jack'");
            } else {
                break;
            }
        }

        // Game loop
        while (pencils > 0) {
            // Print the pencils
            System.out.println("|".repeat(pencils));

            if (currentPlayer.equals("Jack")) {
                int botMove = 1;  // Default move for bot
                if (pencils % 4 == 0) {
                    botMove = 3;
                } else if (pencils % 4 == 3) {
                    botMove = 2;
                } else if (pencils % 4 == 2) {
                } else {
                    // Losing position - choose a random move
                    botMove = random.nextInt(3) + 1;
                }

                System.out.println("Jack's turn:");
                System.out.println(botMove);
                pencils -= botMove;
            } else {
                System.out.println("John's turn!");
                int playerMove;

                while (true) {
                    var input = scanner.nextLine();
                    try {
                        playerMove = Integer.parseInt(input);
                        if (playerMove < 1 || playerMove > 3) {
                            System.out.println("Possible values: '1', '2' or '3'");
                        } else if (playerMove > pencils) {
                            System.out.println("Too many pencils were taken");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    }
                }

                pencils -= playerMove;
            }

            // Check if the game is over
            if (pencils == 0) {
                
                currentPlayer = currentPlayer.equals(players[0]) ? players[1] : players[0];
                System.out.println(currentPlayer + " won!");
                break;
            }

            // Switch player
            currentPlayer = currentPlayer.equals(players[0]) ? players[1] : players[0];
        }
    }
}