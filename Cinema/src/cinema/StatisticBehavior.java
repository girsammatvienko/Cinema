package cinema;
public class StatisticBehavior {
    private Cinema cinema;

    public StatisticBehavior(Cinema cinema) {
        this.cinema = cinema;
    }
    public int getPurchasedTickets() {
        int totalPurchased = 0;
        for (int i = 0;i < cinema.getRows()+1;i++) {
            for (int j = 0;j < cinema.getSeatsAmount()+1;j++) {
                if(cinema.getSeatStatus(i,j) == 'B') totalPurchased++;
            }
        }
        return totalPurchased;
    }
    public double getPercentage() {
        return (double) ((getPurchasedTickets() / ((double)(cinema.getSeatsAmount())*cinema.getRows()) * 100.0));
    }
    public int getIncome() { return cinema.getCurrentIncome(); }
    public int getTotalIncome() {
        return ((cinema.getTotalNumberOfSeats() < 60)) ? (cinema.getTotalNumberOfSeats()*10)
                : (((cinema.getRows()/2) * cinema.getSeatsAmount()) * 10) +
                (((cinema.getRows()/2) + cinema.getRows()%2) * cinema.getSeatsAmount())*8;
    }
    public void printStatistics() {
        System.out.println("\nNumber of purchased tickets: " + getPurchasedTickets());
        System.out.printf("Percentage: %.2f", getPercentage());
        System.out.println("%");
        System.out.println("Current income: $" + getIncome());
        System.out.println("Total income: $" + getTotalIncome() + "\n");
    }
}
