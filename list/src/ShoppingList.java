import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingList {

    private ArrayList<Category> list;

    public ShoppingList() {
        this.list = new ArrayList<>();
    }
    public ShoppingList(String name) throws FileNotFoundException {
        File file = new File(name);
        Scanner scanner = new Scanner(file);
        this.list = new ArrayList<>();

        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            if (data.contains("*")){
                Product product = new Product(data.substring(1));
                this.list.get(this.list.size() - 1).getProducts().add(product);
            } else {
                Category category = new Category(data, new ArrayList<>());
                this.list.add(category);
            }
        }

        scanner.close();
    }

    public ArrayList<Category> getList() {
        return this.list;
    }

    public void setList(ArrayList<Category> list) {
        this.list = list;
    }

    public void display() {
        for (Category category : list) {
            System.out.println(category.getName());
            for (Product product : category.getProducts()) {
                System.out.println("-"+product.getName());
            }
        }
    }

    public void display(String text){
        for (Category category : list) {
            if (category.getName().equals(text) || text.equals("all")) {
                System.out.println(category.getName());
                for (Product product : category.getProducts()) {
                    System.out.println("-" + product.getName());
                }
            }
        }
    }

    public void add(String categoryName, String productName){
        for (Category category : list) {
            if(category.getName().equals(categoryName)) {
                category.getProducts().add(new Product(productName));
                return;
            }
        }

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(productName));
        Category category = new Category(categoryName, products);
        list.add(category);
    }
    public void removeAll(){
        list.clear();
    }
     public void removeCategory(String categoryName){
         list.removeIf(category -> category.getName().equals(categoryName));    // lambda expression alt + enter TODO
     }
     public void removeProduct(String categoryName, String productName){
         for (Category category : list) {
             if (category.getName().equals(categoryName)){
                 category.getProducts().removeIf(product -> product.getName().equals(productName));
             }
         }
     }
    public void save(String name) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        for(Category category : list){
            fileWriter.write(category.getName() + "\n");
            for (Product product : category.getProducts()) {
                fileWriter.write("*" + product.getName() + "\n");
            }
        }
        fileWriter.close();
    }
}




