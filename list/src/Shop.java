import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Shop implements IShop{
    public static void main(String[] args) {
       try{
            ShoppingList data = new ShoppingList("products.txt");
            ShoppingList myList = new ShoppingList();
            String decision = "";

            while(true){
                IShop.info();
                decision = IShop.intercept();
                IShop.decide(decision, data, myList);
            }

       } catch (FileNotFoundException e){
           System.out.println("Error: load() " + e.getMessage());
       } catch (IOException en){
           System.out.println("Error: save() " + en.getMessage());
       } catch (Exception ef){
           System.out.println("Error: main() " + ef.getMessage());
       }
    }
}
