import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private int currentMenu;
    private int[] menuArr;
    private Room activeRoom;


    public Menu() {
        currentMenu = 0;
        menuArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
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
                manageRoomMenu();
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
                placeFurnitureInRoomMenu();
                break;
            case 7:
                moveFurnitureMenu();
                break;
            case 8:
                removeFurnitureFromRoomMenu();
                break;
            case 9:
                displayRoom();
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
                            "2. Manage room\n" +
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
        Scanner scanner = new Scanner(System.in);

        System.out.println( "************\n" +
                "Create Room\n" +
                "************\n\n" +
                "Width (x axis): ");
        int width = scanner.nextInt();
        System.out.println("Length (y axis): ");
        int length = scanner.nextInt();

        activeRoom = new Room(width, length);
        System.out.println("Room has been created");

        activeRoom.printLayout();

        currentMenu = 0;
        displayCurrentMenu();
    }

    // Menu 2
    public void manageRoomMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println( "************\n" +
                            "MANAGE ROOM\n" +
                            "************\n\n" +
                            "1. Place Furniture\n" + // menu 6
                            "2. Move Furniture\n" + // menu 7
                            "3. Remove Furniture\n" + // menu 8
                            "4. Display Room\n" +
                            "5. Main menu"); // menu 0
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
                choice = 8;
                break;
            case 4:
                choice = 9;
                break;
            case 5:
                choice = 0;
                break;
        }

        displayMenu(choice);

        while(Arrays.binarySearch(menuArr, choice) < 1) {
            System.out.print("Choose a number from the menu: ");
            choice = scanner.nextInt();
        }

        System.out.println();
        currentMenu = choice;
        displayCurrentMenu();
    }

    // Menu 3
    public void createFurnitureMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println( "************\n" +
                            "Create Furniture\n" +
                            "************\n\n" +
                            "Name the piece: ");
        String name = scanner.nextLine();
        System.out.println("Enter a single character to represent the " + name + ": ");
        char identifier = scanner.next().charAt(0);
        System.out.println("Enter width (x axis): ");
        int width = scanner.nextInt();
        System.out.println("Enter length (y axis): ");
        int length = scanner.nextInt();

        Furniture furniture = new Furniture(name, width, length, identifier);

        Inventory.add(furniture);

        System.out.println("Here is your new piece: ");
        furniture.printFurniture();

        currentMenu = 0;
        displayCurrentMenu();
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
    public void placeFurnitureInRoomMenu() {
        System.out.println("Choose a piece of furniture to place");
        Furniture furniture = Inventory.pickFurniture();

        activeRoom.placeFurniture(furniture);
        activeRoom.printLayout();

        currentMenu = 2;
        displayCurrentMenu();
    }

    // Menu 7
    public void moveFurnitureMenu() {
        System.out.println("Choose a piece of furniture in the room to move");
        Furniture furniture = Inventory.pickFurnitureInRoom();

        activeRoom.moveFurniture(furniture);

        currentMenu = 2;
        displayCurrentMenu();
    }

    // Menu 8
    public void removeFurnitureFromRoomMenu() {
        System.out.println("Choose a piece of furniture in the room to remove");
        Furniture furniture = Inventory.pickFurnitureInRoom();

        activeRoom.removeFurniture(furniture);

        currentMenu = 2;
        displayCurrentMenu();
    }

    // Menu 9
    public void displayRoom() {
        activeRoom.printLayout();

        currentMenu = 2;
        displayCurrentMenu();
    }

}
