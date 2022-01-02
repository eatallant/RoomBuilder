import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    private static List<Furniture> furnitureList;

    public Inventory() {
        furnitureList = new ArrayList<>();
    }

    public static void add(Furniture furniture) {
        furnitureList.add(furniture);
    }

    public static void remove(Furniture furniture) {
        furnitureList.remove(furniture);
    }

    public static Furniture pickFurniture() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        for(int i = 0; i < furnitureList.size(); i++) {
            System.out.println((i + 1) + ". " + furnitureList.get(i).getName());
        }

        while(choice < 1) {
            System.out.println("choice: ");
            choice = scanner.nextInt();
        }

        return furnitureList.get(choice - 1);
    }

    // will only display furniture option if it is placed in the room
    public static Furniture pickFurnitureInRoom() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        for(int i = 0; i < furnitureList.size(); i++) {
            if(furnitureList.get(i).isInRoom())
                System.out.println((i + 1) + ". " + furnitureList.get(i).getName());
        }

        while(choice < 0 || choice > furnitureList.size()) {
            System.out.println("Enter a number from the list (or 0 to exit): ");
            choice = scanner.nextInt();
        }
        // return null if invalid choice is made (handled in menu class, will typically kick user back to last menu)
        if(choice == 0)
            return null;

        return furnitureList.get(choice - 1);
    }

    public static void printAllFurniture() {
        for(Furniture furniture : furnitureList) {
            System.out.println(furniture.getName());

        }
        System.out.println();
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }
}
