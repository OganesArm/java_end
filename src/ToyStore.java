import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ToyStore {
    private final ArrayList<Toy> allToysList = new ArrayList<>();
    private final Queue<Toy> prizeToysQueue = new LinkedList<>();
    private int lastToyID = 0;

    public void add(String name, int frequency, int count) {
        for (int i = 0; i < count; i++) {
            Toy toy = new Toy(++lastToyID, name, frequency);
            allToysList.add(toy);
        }
    }

    public void countReport() {
        HashMap<String, Integer> toyCountMap = new HashMap<>();

        for (Toy toy : allToysList) {
            String toyName = toy.getName();
            int toyCount = toyCountMap.getOrDefault(toyName, 0);
            toyCountMap.put(toyName, toyCount + 1);
        }

        System.out.println("Перечень игрушек в магазине и их количество:");
        for (Map.Entry<String, Integer> entry : toyCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void putToyToPrizeToysQueue() {
        if (allToysList.isEmpty()) {
            System.out.println("В магазине закончились игрушки");
            return;
        }

        ArrayList<Toy> toysByFrequencyList = new ArrayList<>();
        for (Toy toy : allToysList) {
            int currentToyFrequency = toy.getFrequency();
            for (int i = 0; i < currentToyFrequency; i++) {
                toysByFrequencyList.add(toy);
            }
        }

        Collections.shuffle(toysByFrequencyList);
        Toy toy = toysByFrequencyList.get(0);
        prizeToysQueue.add(toy);
        allToysList.remove(toy);
    }

    public void getPrizeToyAndWriteToFile(String filePath) {
        if (prizeToysQueue.isEmpty()) {
            System.out.println("Призовых игрушек в наличии нет");
            return;
        }
        Toy prizeToy = prizeToysQueue.poll();
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(prizeToy.getName() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Не удалось записать призовую игрушку в файл");
            return;
        }
        System.out.println("Поздравляю! Вы выиграли " + prizeToy.getName());
        allToysList.remove(prizeToy);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nСписок игрушек:\n");
        for (Toy toy : this.allToysList) {
            sb.append("\t").append(toy.getId()).append(". ").append(toy).append("\n");
        }
        return sb.toString();
    }
}