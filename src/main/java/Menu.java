import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private int currentMenu;
    private int[] menuArr;

    public Menu() {
        currentMenu = 0;
        menuArr = new int[]{0, 1, 2, 3, 4, 5};
    }

    public static void run() {
        Menu menuInstance = new Menu();
        menuInstance.displayCurrentMenu();
    }

    public void displayCurrentMenu() {
        displayMenu(currentMenu);
    }

    public void displayMenu(int menu) {

        switch (menu) {
            case 0:
                mainMenu();
                break;
            case 1:
                createRoomMenu();
                break;
            case 2:
                manageRoomsMenu();
                break;
            case 3:
                createFurnitureMenu();
                break;
            case 4:
                manageFurnitureMenu();
                break;
            case 5:
                quit();
                break;
            default:
                throw new IllegalArgumentException("Invalid menu");
        }
    }


    // Menu 0
    public void mainMenu() {
        currentMenu = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println( "***********************\n" +
                            "WELCOME TO ROOM BUILDER\n" +
                            "***********************\n\n" +
                            "1. Create room\n" +
                            "2. Manage rooms\n" +
                            "3. Create furniture\n" +
                            "4. Manage furniture\n" +
                            "5. Quit");
        System.out.print("Make a selection: ");

        int choice = scanner.nextInt();

        while(Arrays.binarySearch(menuArr, choice) < 1) {
            System.out.print("Choose a number from the menu: ");
            choice = scanner.nextInt();
        }

        currentMenu = choice;
        displayCurrentMenu();
    }

    // Menu 1
    public void createRoomMenu() {
        // needs implemented
    }

    // Menu 2
    public void manageRoomsMenu() {
        // needs implemented
    }

    // Menu 3
    public void createFurnitureMenu() {
        // needs implemented
    }

    // Menu 4
    public void manageFurnitureMenu() {
        // needs implemented
    }

    // Menu 5
    public void quit() {
        System.exit(0);
    }
}
