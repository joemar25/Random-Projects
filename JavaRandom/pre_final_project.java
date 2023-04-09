import java.util.Random;
import java.util.Scanner;

class BingoCard {
    private static final int CARD_SIZE = 5;
    private static final int MAX_NUMBER = 75;
    private final int[][] card;

    public BingoCard() {
        this.card = generateCard();
    }

    private int[][] generateCard() {
        int[][] card = new int[CARD_SIZE][CARD_SIZE];
        boolean[] usedNumbers = new boolean[MAX_NUMBER];

        Random random = new Random();
        for (int i = 0; i < CARD_SIZE; i++) {
            for (int j = 0; j < CARD_SIZE; j++) {
                if (j == CARD_SIZE / 2 && i == CARD_SIZE / 2) {
                    card[i][j] = -1; // Set the center square to -1
                } else {
                    int num;
                    do {
                        num = random.nextInt(MAX_NUMBER) + 1;
                    } while (usedNumbers[num - 1]);
                    usedNumbers[num - 1] = true;
                    card[i][j] = num;
                }
            }
        }
        return card;
    }

    public int[][] getCard() {
        return this.card;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int[] row : card) {
            for (int num : row) {
                String str = (num == -1) ? "FREE" : Integer.toString(num);
                res.append(String.format("%-4s", str));
            }
            res.append("\n");
        }
        return res.toString();
    }
}

class BingoGame {
    private int num_players;
    private BingoCard[] players;
    private boolean[] called_numbers;
    private int turns;

    public BingoGame(int num_players) {
        this.num_players = num_players;
        this.players = new BingoCard[num_players];
        for (int i = 0; i < num_players; i++) {
            this.players[i] = new BingoCard();
        }
        this.called_numbers = new boolean[75];
        this.turns = 0;
    }

    public boolean checkBingo(int[][] card) {
        for (int i = 0; i < 5; i++) {
            boolean rowBingo = true;
            boolean colBingo = true;
            for (int j = 0; j < 5; j++) {
                if (card[i][j] != -1) {
                    rowBingo = false;
                }
                if (card[j][i] != -1) {
                    colBingo = false;
                }
            }
            if (rowBingo || colBingo) {
                return true;
            }
        }
        boolean diagonal1Bingo = true;
        boolean diagonal2Bingo = true;
        for (int i = 0; i < 5; i++) {
            if (card[i][i] != -1) {
                diagonal1Bingo = false;
            }
            if (card[i][4 - i] != -1) {
                diagonal2Bingo = false;
            }
        }
        if (diagonal1Bingo || diagonal2Bingo) {
            return true;
        }
        return false;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to start the game...");
        scanner.nextLine();
        int currentPlayer = 0; // Initialize to player 1
        while (true) {
            this.turns += 1;
            int called_number = new Random().nextInt(75) + 1;
            while (this.called_numbers[called_number - 1]) {
                called_number = new Random().nextInt(75) + 1;
            }
            this.called_numbers[called_number - 1] = true;
            System.out.println("\nTurn: " + this.turns);
            System.out.println("Player " + (currentPlayer + 1) + "'s turn");
            System.out.println("Called number: " + called_number);
            for (int i = 0; i < this.num_players; i++) {
                int[][] card = this.players[i].getCard();
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (card[j][k] == called_number) {
                            card[j][k] = -1;
                            if (this.checkBingo(card)) {
                                System.out.println("Player " + (i + 1) + " wins!");
                                return;
                            }
                        }
                    }
                }
            }
            scanner.nextLine();
            currentPlayer = (currentPlayer + 1) % this.num_players; // Update current player
        }
    }
}

public class pre_final_project {

    public static void main(String[] args) {
        System.out.println("Welcome to Bingo!");
        try (Scanner scanner = new Scanner(System.in)) {
            boolean playAgain = true;
            while (playAgain) {
                int num_players = 5;
                BingoGame game = new BingoGame(num_players);
                game.play();
                scanner.nextLine(); // Consume the newline character left by nextInt()
                System.out.print("Do you want to play again? (y/n): ");
                String input = scanner.nextLine();
                playAgain = input.equals("y");
            }
        }
        System.out.println("Thanks for playing!");
    }

}