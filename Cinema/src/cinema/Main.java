package cinema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        int choice = 1;

        Cinema c = new Cinema();
        StatisticBehavior s = new StatisticBehavior(c);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of rows: \n> ");
        int n = scan.nextInt();
        System.out.print("Enter the number of seats in each row: \n> ");
        int m = scan.nextInt();
        c.setSeats(n,m);
        System.out.println();
        while(choice != 0) {
            System.out.print("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n> ");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    c.printSeats();
                    System.out.println();
                    break;
                case 2:
                    c.toBook(c);
                    break;
                case 3:
                    s.printStatistics();
                    break;
            }
        }
    }
}
