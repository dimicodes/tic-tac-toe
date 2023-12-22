package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] ch = {'_', '_', '_', '_', '_', '_', '_', '_', '_'};
        printBoard(ch);
        usersMove(scanner, ch);
    }

    static void usersMove(Scanner scanner, char[] ch) {
        boolean gameCompleted = false;
        boolean xTurn = true;
        while (!gameCompleted) {
            System.out.println("Enter the coordinates: ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length == 2 && isNumeric(parts[0]) && isNumeric(parts[1])) {
                int coordinateOne = Integer.parseInt(parts[0]);
                int coordinateTwo = Integer.parseInt(parts[1]);

                if ((coordinateOne >= 1 && coordinateOne <= 3) && (coordinateTwo >= 1 && coordinateTwo <= 3)) {
                    int index = (coordinateOne - 1) * 3 + (coordinateTwo - 1);
                    if (ch[index] == '_') {
                        ch[index] = xTurn ? 'X' : 'O';
                        printBoard(ch);
                        String result = checkWinner(ch);
                        if (!result.equals("Game not finished")) {
                            System.out.println(result);
                            gameCompleted = true;
                        }
                        xTurn = !xTurn;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }





    static String checkWinner(char[] userInputLine) {
        int countX = 0;
        int countO = 0;

        // Count X's and O's
        for (char c : userInputLine) {
            if (c == 'X') {
                countX++;
            } else if (c == 'O') {
                countO++;
            }
        }

        // Check the difference in counts
        if (Math.abs(countX - countO) > 1) {
            return "Impossible";
        }

        boolean xWins = isWinner(userInputLine, 'X');
        boolean oWins = isWinner(userInputLine, 'O');

        // If both X and O win, it's impossible
        if (xWins && oWins) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (countX + countO == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    static boolean isWinner(char[] board, char player) {
        for (int i = 0; i < 3; i++) {
            // Check rows and columns
            if ((board[i * 3] == player && board[i * 3 + 1] == player && board[i * 3 + 2] == player) ||
                    (board[i] == player && board[i + 3] == player && board[i + 6] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0] == player && board[4] == player && board[8] == player) ||
                (board[2] == player && board[4] == player && board[6] == player)) {
            return true;
        }

        return false;
    }

    static void printBoard(char[] ch) {
        System.out.println("---------");
        System.out.println("| " + ch[0] + " " + ch[1] + " " + ch[2] + " |");
        System.out.println("| " + ch[3] + " " + ch[4] + " " + ch[5] + " |");
        System.out.println("| " + ch[6] + " " + ch[7] + " " + ch[8] + " |");
        System.out.println("---------");
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    }

