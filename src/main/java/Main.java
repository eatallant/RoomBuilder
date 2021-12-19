

public class Main {
    public static void main(String[] args) {

        //Menu.run();
        Furniture sofa = new Furniture("Sofa", 5, 1, '$');

        Room livingRoom = new Room(20, 10);

        livingRoom.printLayout();
        livingRoom.placeFurniture(sofa);
        livingRoom.printLayout();
        livingRoom.printTopLeftCoordinate(sofa);
        livingRoom.moveFurniture(sofa);


    }
}
