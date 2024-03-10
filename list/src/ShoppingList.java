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
    public ShoppingList(String name) throws FileNotFoundException {     //TODO optimization required
        ArrayList<Product> temp  =  new ArrayList<>();
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
        scanner.close();
        categories.getLast().setProducts(temp);
        this.list = categories;
    }

    public ArrayList<Category> getList() {
        return list;
    }

    public void setList(ArrayList<Category> list) {
        this.list = list;
    }

    public void display(){  // metoda non-static odnosi sie do listy konkretnego obiektu a nie jak w statycznym
        for (Category category : list) {
            System.out.println(category.getName());
        }
    }

    public void display(String text){       // metoda non-static wyświetlająca produkty w obiekcie
        for (Category category : list) {
            if (category.getName().equals(text) || text.equals("all")) {
                System.out.println(category.getName());
                for (Product product : category.getProducts()) {
                    System.out.println(product.getName());
                }
            }
        }
    }

    public void add(String category, String product){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(0, product));
        Category category1 = new Category(0, category, products);

        for (Category category2 : list) {
            if(category2.getName().equals(category)){         //jesli mamy taka kategorie w liscie zakupow
                category2.getProducts().add(new Product(0, product));      //ti dodajemy produkt
                return;
            }
        }

        list.add(category1);
    }
    public void removeAll(){
        list.clear();
    }
     public void removeCategory(String categoryRemove){
         for (Category category : list) {
             if(category.getName().equals(categoryRemove)){
                 list.remove(category);
             }
         }
     }
     public  void  removeByCategoryAndProduct(String categoryRemove, String product){
         for (Category category : list) {
             if (category.getName().equals(categoryRemove)){
                 for (Product product1 : category.getProducts()) {
                      if (product1.getName().equals(product)){
                         category.getProducts().remove(product1);
                      }
                 }
             }
         }
     }
    public void save(String name) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        for(Category category : list){
            fileWriter.write(category.getName());
            for (Product product : category.getProducts()) {
                fileWriter.write("*" + product.getName());
            }
        }
        fileWriter.close();
    }
}




