import java.io.FileNotFoundException;
import java.io.IOException;

public class Shop implements IShop{
    public static void main(String[] args) {
       try{
            ShoppingList data = new ShoppingList("products.txt");
            ShoppingList myList = new ShoppingList();

            while(true){
                IShop.info();
                IShop.decide(IShop.intercept(), data, myList);
            }

       } catch (FileNotFoundException e){
           System.out.println("Error: ShoppingList() " + e.getMessage());
       } catch (IOException en){
           System.out.println("Error: save() " + en.getMessage());
       } catch (Exception ef){
           System.out.println("Error: unforeseen problem or exiting the program intentionally " + ef.getMessage());
       }
    }
}
