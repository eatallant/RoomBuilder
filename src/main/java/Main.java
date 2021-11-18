public class Main {
    public static void main(String[] args) {

        //Menu.run(); // Menu object menuInstance created automatically
        Room testRoom = new Room(20,10);

        Furniture sofa = new Furniture(4,1,'S');
        testRoom.placeFurniture(sofa);
        testRoom.printLayout();

        Furniture tv = new Furniture(2,1,'T');
        testRoom.placeFurniture(tv);
        testRoom.printLayout();
    }
}
