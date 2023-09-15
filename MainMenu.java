import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        RestaurantDatabaseSystem system = new RestaurantDatabaseSystem();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Main Menu:");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");

            choice = Integer.parseInt(scanner.nextLine()); // Use nextLine() after nextInt()
            switch (choice) {
                case 1:
                    system.searchRestaurants();
                    break;
                case 2:
                    system.searchFoodItems();
                    break;
                case 3:
                    system.addRestaurant();
                    break;
                case 4:
                    system.addFoodItem();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}