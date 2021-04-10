package cinema;

import com.sun.jdi.request.WatchpointRequest;

import java.util.Scanner;

public class Cinema {
    private int rows, columns;
    private char[][] seats;
    private int currentIncome;

    public Cinema() {
        this.currentIncome = 0;
        this.rows = 0;
        this.columns = 0;
        this.seats = new char[rows][columns];
    }

    public void printSeats() {
        System.out.println(" Cinema: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void setSeats(int n, int m) {
        if (n <= 9 && m <= 9) {
            seats = new char[n + 1][m + 1];
            rows = n + 1;
            columns = m + 1;
            char[] nums = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
            int counterR = 0;
            int counterC = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i == 0 && j == 0) seats[i][j] = ' ';
                    else if (i == 0) seats[i][j] = nums[counterR++];
                    else if (j == 0 && i != 0) seats[i][j] = nums[counterC++];
                    else seats[i][j] = 'S';
                }
            }
        } else System.out.println("Numbers of rows and seats cannot be greater than 9!");
    }
    public int getRows() { return rows - 1; }
    public int getSeatsAmount() { return columns - 1; }
    public int getTotalNumberOfSeats() { return (rows-1)*(columns-1);}
    public void toBook(Cinema hall) throws SeatIsTakenException, WrongInputException {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter a row number: \n> ");
            int n1 = scan.nextInt();
            System.out.print("Enter a seat number in that row: \n> ");
            int m1 = scan.nextInt();
            if(!isCorrectInput(n1, m1)) { System.out.println("\nWrong input!\n"); }
            else if(isAlreadyTaken(n1, m1))
                System.out.println("\nThat ticket has already been purchased!\n");
            else if (isCorrectInput(n1, m1)) {
                int[] positions = null;
                positions = hall.setSeatState(n1, m1);
                n1 = positions[0];
                m1 = positions[1];
                int seat = (((getSeatsAmount() * n1) - (columns - m1)) + 1);
                int seats = (rows - 1) * (columns - 1);
                int cost = (seats < 60) ? 10 : (seat < (getRows() / 2) * getSeatsAmount()) ? 10 : 8;
                System.out.println("\nTicket price: $" + cost + "\n");
                currentIncome += cost;

                break;
            }
        }

    }
    public void setCurrentIncome(int currentIncome) { this.currentIncome = currentIncome; }
    public int[] setSeatState(int n, int m) throws SeatIsTakenException, WrongInputException {
        int positions[] = new int[2];
        if(n <= rows && m <= columns) {
            if(seats[n][m] != 'B') {
                seats[n][m] = 'B';
                positions[0] = n;
                positions[1] = m;
            }
            else throw new SeatIsTakenException("That ticket has already been purchased!\n");
        }
        else throw new WrongInputException("Wrong input!\n");
        return positions;
    }
    public boolean isCorrectInput(int n, int m) { return (n <= rows-1 && m <= columns-1); }
    public boolean isAlreadyTaken(int n, int m) { return (seats[n][m] == 'B');}
    public int getCurrentIncome() { return currentIncome; }
    public char getSeatStatus(int n, int m) { return seats[n][m];}
}
