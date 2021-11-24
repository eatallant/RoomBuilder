import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Furniture> furnitureList;

    public Inventory() {
        furnitureList = new ArrayList<>();
    }

    private void add(Furniture furniture) {
        furnitureList.add(furniture);
    }

    private void remove(Furniture furniture) {
        furnitureList.remove(furniture);
    }


    public void printAllFurniture() {
        for(Furniture furniture : furnitureList) {
            System.out.print(furniture.getName() + ", ");
        }
        System.out.println();
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }
}
