import java.util.Scanner;

public class Furniture {
    private char[][] size;
    private int[][] shape;
    private char identifier;

    public Furniture(int x, int y, char identifier) {
        this.size = new char[y][x];
        this.identifier = identifier;
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


    public char getIdentifier() {
        return identifier;
    }
}
