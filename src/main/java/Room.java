import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Room {
    private final char[][] layout;
    private String doorLocation;
    private int[] doorCoordinates;
    private Map<Furniture, Integer[]> map; // store data and locations of all furniture (Not implemented yet)

    public Room(int x, int y) {
        if(x < 1 || y < 1) {
            throw new IllegalArgumentException("Room cannot be less than 1x1");
        }
        layout = new char[y + 2][x + 2]; // add 2 to each to make room for walls
        map = new HashMap<>();
        buildNewLayout();
        buildNewDoor();

    }

    private void buildNewLayout() {

        // in column major order. n/s wall = |, e/w wall = -, empty space = ., corner = +
        int rowLength = layout[0].length;
        int columnLength = layout.length;
        for(int i = 0; i < columnLength; i++) {
            for(int j = 0; j < rowLength; j++) {
                if((i == 0 || i == columnLength - 1) && (j == 0 || j == rowLength - 1)) // corner
                        layout[i][j] = '|';
                else if((i == 0 || i == columnLength - 1) && (j != 0 && j != rowLength - 1)) // n/s wall
                    layout[i][j] = '-';
                else if(j == 0 || j == rowLength - 1) // e/w wall
                    layout[i][j] = '|';
                else
                    layout[i][j] = '.';
            }
        }
    }

    private void buildNewDoor() {
        int side = selectWall();

        switch(side) {
            case 1:
                layout[0][1] = '*';
                doorLocation = "north";
                doorCoordinates = new int[]{0,1};
                break;
            case 2:
                layout[1][xAxisLength() - 1] = '*';
                doorLocation = "east";
                doorCoordinates = new int[]{1, xAxisLength() - 1};
                break;
            case 3:
                layout[yAxisLength() - 1][xAxisLength() - 2] = '*';
                doorLocation = "south";
                doorCoordinates = new int[]{yAxisLength() - 1, xAxisLength() - 2};
                break;
            case 4:
                layout[yAxisLength() - 2][0] = '*';
                doorLocation = "west";
                doorCoordinates = new int[]{yAxisLength() - 2, 0};
                break;
        }
    }

    private int selectWall() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a side for the door:\n" +
                "1. North\n" +
                "2. East\n" +
                "3. South\n" +
                "4. West");

        int side = scanner.nextInt();

        while(side < 1 || side > 4) {
            System.out.print("Invalid choice. Enter a number from the menu: ");
            side = scanner.nextInt();
        }

        return side;
    }

    public void moveDoor() {
        if(Objects.equals(doorLocation, "north") || Objects.equals(doorLocation, "south")) {
            layout[doorCoordinates[0]][doorCoordinates[1]] = '-';
        } else {
            layout[doorCoordinates[0]][doorCoordinates[1]] = '|';
        }
        buildNewDoor();
    }

    public void placeFurniture(Furniture furniture) {
        if(furniture.isInRoom()) {
            System.out.println("Item has already been placed in the room");
            return;
        } else if(furniture.getWidth() > xAxisLength() - 2 || furniture.getLength() > yAxisLength() - 2) {
            System.out.println("Item is too large");
            return;
        }

        switch(doorLocation) {
            case "north":
                placeFurnitureNorth(furniture);
                break;
            case "east":
                placeFurnitureEast(furniture);
                break;
            case "south":
                placeFurnitureSouth(furniture);
                break;
            case "west":
                placeFurnitureWest(furniture);
                break;
            default:
                throw new IllegalArgumentException("Invalid door location");

        }

        furniture.placedInRoom();
    }


    // There might be a better algorithm that uses the door location to determine placement.
    private void placeFurnitureNorth(Furniture furniture) {

        // Check area around door for other furniture
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                if(layout[doorCoordinates[1] + i][doorCoordinates[0] + 1 + j] != '.') {
                    System.out.println("Something is in the way! Make sure the door has enough room around it to fit this item");
                    return;
                }
            }
        }

        // Replace '.' char with furniture identifier
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                layout[doorCoordinates[1] + i][doorCoordinates[0] + 1 + j] = furniture.getIdentifier();
            }
        }

    }

    private void placeFurnitureEast(Furniture furniture) {
        // Check area around door for other furniture
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                if(layout[doorCoordinates[0] + i][doorCoordinates[1] - 1 - j] != '.') {
                    System.out.println("Something is in the way! Make sure the door has enough room around it to fit this item");
                    return;
                }
            }
        }

        // Replace '.' char with furniture identifier
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                layout[doorCoordinates[0] + i][doorCoordinates[1] - 1 - j] = furniture.getIdentifier();
            }
        }
    }

    private void placeFurnitureSouth(Furniture furniture) {
        // Check area around door for other furniture
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                if(layout[doorCoordinates[0] - 1 - i][doorCoordinates[1] - j] != '.') {
                    System.out.println("Something is in the way! Make sure the door has enough room around it to fit this item");
                    return;
                }
            }
        }

        // Replace '.' char with furniture identifier
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                layout[doorCoordinates[0] - 1 - i][doorCoordinates[1] - j] = furniture.getIdentifier();
            }
        }
    }

    private void placeFurnitureWest(Furniture furniture) {
        // Check area around door for other furniture
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                if(layout[doorCoordinates[0] + i][doorCoordinates[1] + 1 + j] != '.') {
                    System.out.println("Something is in the way! Make sure the door has enough room around it to fit this item");
                    return;
                }
            }
        }

        // Replace '.' char with furniture identifier
        for(int i = 0; i < furniture.getLength(); i++) {
            for(int j = 0; j < furniture.getWidth(); j++) {
                layout[doorCoordinates[0] + i][doorCoordinates[1] + 1 + j] = furniture.getIdentifier();
            }
        }
    }
    public void moveFurniture(Furniture furniture) {
        Scanner scanner = new Scanner(System.in);
        char choice = 'a';
        System.out.println("Move furniture with WASD key followed by Enter. Enter Q when finished.");

        while(choice != 'q') {
            choice = scanner.next().charAt(0);
            choice = Character.toLowerCase(choice); // lowercase the input
            switch(choice) {
                case 'w':
                    moveFurnitureUpDown(furniture, -1); // move up
                    break;
                case 's':
                    moveFurnitureUpDown(furniture, 1); // move down
                    break;
                case 'a':
                    moveFurnitureLeftRight(furniture, -1); // move left
                    break;
                case 'd':
                    moveFurnitureLeftRight(furniture, 1); // move right
                    break;
            }
        }

    }
    private void moveFurnitureUpDown(Furniture furniture, int distance) {
        // needs implemented. furniture location map needed
    }

    private void moveFurnitureLeftRight(Furniture furniture, int distance) {
        // needs implemented. furniture location map needed
    }


    public void buildWindow(Window window) {
        int wall = selectWall();
        int size = selectWindowSize(wall);

        switch(wall) {
            case 1: // north wall
                // needs implemented
            case 2: // east wall
                // needs implemented
            case 3: // south wall
                // needs implemented
            case 4: // west wall
                // needs implemented
            default:
                throw new IllegalArgumentException("Invalid window location");

        }
    }

    private int selectWindowSize(int wall) {
        Scanner scanner = new Scanner(System.in);

        // get size of window
        int sizeLimit;
        int sizeChosen = 0;

        if(wall == 1 || wall == 3)  // north/south wall
            sizeLimit = xAxisLength() - 2;
        else                        // east/west wall
            sizeLimit = yAxisLength() - 2;

        System.out.println("Choose a size for the window. must be less than " + sizeLimit);
        System.out.print("Enter size: ");

        try {
            while(sizeChosen < 1 || sizeChosen > sizeLimit ) {
                sizeChosen = scanner.nextInt();
            }

        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        return sizeChosen;

    }

    private void moveWindow() {
        // needs implemented
    }

    public void printLayout() {
        System.out.println();
        for (char[] x : layout)
        {
            for (char y : x)
            {
                System.out.print(y);
            }
            System.out.println();
        }
        System.out.println();
    }

    // return dimensions of room
    public int[] getDimensions() {
        return new int[] {layout.length - 2, layout[0].length - 2};
    }

    public void printDimensions() {
        System.out.println("Height: " + (layout.length - 2) + ", Width: " + (layout[0].length - 2));
    }

    private int xAxisLength() {
        return layout[0].length;
    }

    private int yAxisLength() {
        return layout.length;
    }



}
