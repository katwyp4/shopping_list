import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingList {
    public static void main(String[] args) {
    }

    private static void info(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Available actions:");
        System.out.println("1. Add product");
        System.out.println("2. Show list");
        System.out.println("3. Show list by category");
        System.out.println("4. Remove list");
        System.out.println("5. Remove list by category");
        System.out.println("6. Remove product");
        System.out.println("7. Save list");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private ArrayList<Category> load(String name) throws FileNotFoundException {
        ArrayList<Product> temp =  new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        File file = new File(name);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            if (data.contains("*")){
                Product product = new Product(0, data.substring(1));
                temp.add(product);
            }
            else {
                if(!categories.isEmpty()){
                    categories.getLast().setProducts(temp);
                    temp.clear();
                }
                Category category = new Category(0, data, temp);
                categories.add(category);
            }
        }
        categories.getLast().setProducts(temp);
        return categories;
    }
}

