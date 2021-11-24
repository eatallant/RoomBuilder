import java.util.Arrays;
import java.util.Scanner;

public class Furniture {
    private String name;
    private char[][] size;
    private final char identifier;
    private boolean isInRoom;

    public Furniture(String name, int x, int y, char identifier) {
        this.name = name;
        this.size = new char[y][x];
        this.identifier = identifier;
        isInRoom = false;
        buildNewFurniture();
    }

    private void buildNewFurniture() {
        for (int i = 0; i < size.length; i++) {
            for(int j = 0; j < size[0].length; j++) {
                size[i][j] = identifier;
            }
        }
    }

    public void furnitureDesigner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Current representation of piece " + identifier + ": ");
        printFurniture();
    }

    public void printFurniture() {
        for (char[] x : size)
        {
            for (char y : x)
            {
                System.out.print(y);
            }
            System.out.println();
        }
    }

    public String getName() {
        return name;
    }

    public char getIdentifier() {
        return identifier;
    }

    public int getWidth() {
        return size[0].length;
    }

    public int getLength() {
        return size.length;
    }

    public boolean isInRoom() {
        return isInRoom;
    }

    public void placedInRoom() {
        isInRoom = true;
    }

    public void removedFromRoom() {
        isInRoom = false;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "name='" + name + '\'' +
                ", size=" + Arrays.toString(size) +
                ", identifier=" + identifier +
                ", isInRoom=" + isInRoom +
                '}';
    }
}
