import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Room {
    private char[][] layout;
    private String doorLocation;
    private Map<Furniture, Integer[]> map; // store data and locations of all furniture

    public Room(int x, int y) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("input cannot be less than zero");
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
                        layout[i][j] = '+';
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a side for the door:\n" +
                            "1. North\n" +
                            "2. East\n" +
                            "3. South\n" +
                            "4. West");

        int side = scanner.nextInt();

        switch(side) {
            case 1:
                layout[0][xAxisLength() / 2] = '*';
                layout[0][xAxisLength() / 2 - 1] = '*';
                doorLocation = "north";
                break;
            case 2:
                layout[yAxisLength() / 2][xAxisLength() - 1] = '*';
                layout[yAxisLength() / 2 - 1][xAxisLength() - 1] = '*';
                doorLocation = "east";
                break;
            case 3:
                layout[yAxisLength() - 1][xAxisLength() / 2] = '*';
                layout[yAxisLength() - 1][xAxisLength() / 2] = '*';
                doorLocation = "south";
                break;
            case 4:
                layout[yAxisLength() / 2][0] = '*';
                layout[yAxisLength() / 2 - 1][0] = '*';
                doorLocation = "west";
                break;
        }
    }

    public void placeFurniture(Furniture furniture) {
        if(furniture.getWidth() > xAxisLength() - 2 || furniture.getLength() > yAxisLength() - 2) {
            System.out.println("Item is too large");
            return;
        }

    }

    public void printLayout() {
        for (char[] x : layout)
        {
            for (char y : x)
            {
                System.out.print(y);
            }
            System.out.println();
        }
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
