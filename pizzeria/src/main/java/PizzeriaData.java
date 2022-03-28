import java.util.Arrays;

public class PizzeriaData {
    private final int storage;
    private final int delivery;
    private final int[] backersExp;

    PizzeriaData(int storage, int delivery, int[] backersExp){
        this.storage = storage;
        this.delivery = delivery;
        this.backersExp = backersExp;
    }

    public int getStorage(){
        return storage;
    }
    public int getDelivery(){
        return delivery;
    }
    public int[] getBackersExp(){
        return backersExp;
    }

    public void writeAllData(){
        System.out.print("backers experience: ");
        System.out.println(Arrays.toString(backersExp));
        System.out.println("storage value: " + storage);
        System.out.println("delivery value: " + delivery);
    }
}
