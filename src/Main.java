public class Main {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        toyStore.add("Lego", 10, 4);
        toyStore.add("Huggy Wuggy", 30, 10);
        toyStore.add("Fidget spinner", 45, 15);
        toyStore.add("PopIt", 60, 25);

        toyStore.countReport();

        for (int i = 0; i < 5; i++) {
            toyStore.putToyToPrizeToysQueue();
            toyStore.getPrizeToyAndWriteToFile("prizes.txt");
        }

    }
}