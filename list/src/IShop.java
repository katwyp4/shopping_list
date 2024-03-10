import java.io.IOException;
import java.util.Scanner;

public interface IShop {
    static void info(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Available actions:");
        System.out.println("1. Add product");
        System.out.println("2. Show list");
        System.out.println("3. Show category");
        System.out.println("4. Remove list");
        System.out.println("5. Remove category");
        System.out.println("6. Remove product");
        System.out.println("7. Save list");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    static String intercept(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your choice: ");
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    static void decide(String decision, ShoppingList data, ShoppingList myList) throws IOException {
        switch (decision) {
            case "1" -> add(data, myList);
            case "2" -> display(myList);
            case "3" -> displayByCategory(myList);
            case "4" -> remove(myList);
            case "5" -> removeByCategory(myList);
            case "6" -> removeByCategoryAndProducts(myList);
            case "7" -> saves(myList);
            default -> System.out.println("Invalid choice. Please enter a number between 1 and 7.");
        }
    }

    static void add(ShoppingList data, ShoppingList myList){
        data.display();
        String category = intercept();

        data.display(category);
        String product = intercept();

        myList.add(category, product);
    }

    static void display(ShoppingList myList){
        myList.display();
    }

    static void displayByCategory(ShoppingList myList){
        String category = intercept();
        myList.display(category);
    }

    static void remove(ShoppingList myList){
        myList.removeAll();
    }

    static void  removeByCategory(ShoppingList myList){
        String category = intercept();
        myList.removeCategory(category);

    }
    static  void removeByCategoryAndProducts(ShoppingList myList){
        String category = intercept();
        String product = intercept();
        myList.removeProduct(category,product);
    }
    static void saves(ShoppingList myList) throws IOException {
         System.out.println("Input name");
         String name = intercept();
         myList.save(name);
    }

}

