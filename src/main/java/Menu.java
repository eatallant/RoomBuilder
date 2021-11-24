import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private int currentMenu;
    private int[] menuArr;
    private Room activeRoom;


    public Menu() {
        currentMenu = 0;
        menuArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
    }

    public static void run() {
        Menu menuInstance = new Menu();
        Inventory inventory = new Inventory(); // create furniture inventory
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
            case 6:
                editRoomMenu();
                break;
            case 7:
                changeActiveRoomMenu();
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
        Scanner scanner = new Scanner(System.in);

        System.out.println( "************\n" +
                            "MANAGE ROOMS\n" +
                            "************\n\n" +
                            "Current Room: " + activeRoom + "\n" +
                            "1. Edit room\n" + // menu 6
                            "2. Change active room\n" + // menu 7
                            "3. Main menu"); // menu 0
        System.out.print("Make a selection: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                choice = 6;
                break;
            case 2:
                choice = 7;
                break;
            case 3:
                choice = 0;
                break;
        }

        displayMenu(choice);

        while(Arrays.binarySearch(menuArr, choice) < 1) {
            System.out.print("Choose a number from the menu: ");
            choice = scanner.nextInt();
        }

        currentMenu = choice;
        displayCurrentMenu();
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

    // Menu 6
    public void editRoomMenu() {
        // needs implemented
    }

    // Menu 7
    public void changeActiveRoomMenu() {

    }
}
