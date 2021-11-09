

public class Room {
    private char[][] layout;

    public Room(int x, int y) {
        if(x < 0 || y < 0) {
            throw new IllegalArgumentException("input cannot be less than zero");
        }
        layout = new char[y + 2][x + 2]; // add 2 to each to make room for walls
        buildNewLayout();
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

    public void placeFurniture(Furniture furniture, int x, int y) {

    }
}
