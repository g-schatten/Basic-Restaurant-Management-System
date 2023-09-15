import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class RestaurantDatabaseSystem {
    private List<Restaurant> restaurants;
    private List<Menu> menuItems;
    private Scanner scanner;

    public RestaurantDatabaseSystem() {
        restaurants = new ArrayList<>();
        menuItems = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadInitialData();
    }

    public void searchRestaurants() {
        System.out.println("Restaurant Searching Options:");
        System.out.println("1) By Name");
        System.out.println("2) By Score");
        System.out.println("3) By Category");
        System.out.println("4) By Price");
        System.out.println("5) By Zip Code");
        System.out.println("6) Different Category Wise List of Restaurants");
        System.out.println("7) Back to Main Menu");
        
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                // Search by name
                System.out.print("Enter Restaurant Name: ");
                String searchName = scanner.nextLine();
                searchRestaurantsByName(searchName);
                break;
            case 2:
                // Search by score
                System.out.print("Enter Minimum Score: ");
                double minScore = scanner.nextDouble();
                System.out.print("Enter Maximum Score: ");
                double maxScore = scanner.nextDouble();
                searchRestaurantsByScore(minScore, maxScore);
                break;
            case 3:
                // Search by category
                System.out.print("Enter Category: ");
                String searchCategory = scanner.nextLine();
                searchRestaurantsByCategory(searchCategory);
                break;
            case 4:
                // Search by price
                System.out.print("Enter Price Range ($, $$, $$$): ");
                String priceRange = scanner.nextLine();
                searchRestaurantsByPrice(priceRange);
                break;
            case 5:
                // Search by zip code
                System.out.print("Enter Zip Code: ");
                String zipCode = scanner.nextLine();
                searchRestaurantsByZip(zipCode);
                break;
            case 6:
                // Display category-wise restaurant names
                displayCategoryWiseRestaurants();
                break;
            case 7:
                // Go back to the main menu
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
        }
    }

    public void searchFoodItems() {
        System.out.println("Food Item Searching Options:");
        System.out.println("1) By Name");
        System.out.println("2) By Name in a Given Restaurant");
        System.out.println("3) By Category");
        System.out.println("4) By Category in a Given Restaurant");
        System.out.println("5) By Price Range");
        System.out.println("6) By Price Range in a Given Restaurant");
        System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
        System.out.println("8) List of Restaurants and Total Food Item on the Menu");
        System.out.println("9) Back to Main Menu");
        
        String input = scanner.nextLine();
        int choice = Integer.parseInt(input);
        switch (choice) {
            case 1:
                // Search food item by name
                System.out.print("Enter Food Item Name: ");
                String searchFoodName = scanner.nextLine();
                searchFoodItemsByName(searchFoodName);
                break;
            case 2:
                // Search food item by name in a given restaurant
                System.out.print("Enter Restaurant Name: ");
                String restaurantName = scanner.nextLine();
                System.out.print("Enter Food Item Name: ");
                String searchFoodNameInRestaurant = scanner.nextLine();
                searchFoodItemsByNameInRestaurant(restaurantName, searchFoodNameInRestaurant);
                break;
            case 3:
                // Search food item by category
                System.out.print("Enter Food Item Category: ");
                String searchFoodCategory = scanner.nextLine();
                searchFoodItemsByCategory(searchFoodCategory);
                break;
            case 4:
                // Search food item by category in a given restaurant
                System.out.print("Enter Restaurant Name: ");
                String restaurantNameForCategory = scanner.nextLine();
                System.out.print("Enter Food Item Category: ");
                String searchFoodCategoryInRestaurant = scanner.nextLine();
                searchFoodItemsByCategoryInRestaurant(restaurantNameForCategory, searchFoodCategoryInRestaurant);
                break;
            case 5:
                // Search food item by price range
                System.out.print("Enter Minimum Price: ");
                double minPrice = scanner.nextDouble();
                System.out.print("Enter Maximum Price: ");
                double maxPrice = scanner.nextDouble();
                searchFoodItemsByPriceRange(minPrice, maxPrice);
                break;
            case 6:
                // Search food item by price range in a given restaurant
                System.out.print("Enter Restaurant Name: ");
                String restaurantNameForPriceRange = scanner.nextLine();
                System.out.print("Enter Minimum Price: ");
                double minPriceInRestaurant = scanner.nextDouble();
                System.out.print("Enter Maximum Price: ");
                double maxPriceInRestaurant = scanner.nextDouble();
                searchFoodItemsByPriceRangeInRestaurant(restaurantNameForPriceRange, minPriceInRestaurant, maxPriceInRestaurant);
                break;
            case 7:
                // Display costliest food items in a given restaurant
                System.out.print("Enter Restaurant Name: ");
                String costliestRestaurantName = scanner.nextLine();
                displayCostliestFoodItems(costliestRestaurantName);
                break;
            case 8:
                // Display total food items on the menu for every restaurant
                displayTotalFoodItemsForEachRestaurant();
                break;
            case 9:
                // Go back to the main menu
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
        }
    }


    public void addRestaurant() {
        System.out.println("Adding a new restaurant:");

        // Take input for restaurant information in a single line
        System.out.print("Enter Restaurant Information (id,name,rating,price,zipCode,categories): ");
        //scanner.nextLine();
        String input = scanner.nextLine().trim();
        String[] restaurantInfo = input.split(",", 6);

        if (restaurantInfo.length < 6) {
            System.out.println("Invalid input format. Please provide all required values.");
            return;
        }

        int id = Integer.parseInt(restaurantInfo[0]);
        String name = restaurantInfo[1];
        double rating = Double.parseDouble(restaurantInfo[2]);
        String priceRange = restaurantInfo[3];
        String zipCode = restaurantInfo[4];
        List<String> categories = Arrays.asList(restaurantInfo[5].split("\\s*,\\s*"));

        Restaurant newRestaurant = new Restaurant(id, name, rating, priceRange, zipCode, categories);
        restaurants.add(newRestaurant);

        // Save the new restaurant to the 'restaurant.txt' file
        saveRestaurantsToFile();

        System.out.println("Restaurant added successfully!");
    }


    public void addFoodItem() {
        System.out.println("Adding a new food item to the menu:");
    
        // Take input for food item information in a single line
        System.out.print("Enter Food Item Information (restaurantId,category,name,price): ");
        //scanner.nextLine(); // Consume newline
        String input = scanner.nextLine().trim();
        String[] foodItemInfo = input.split(",");

        if (foodItemInfo.length < 4) {
            System.out.println("Invalid input format. Please provide all required values.");
            return;
        }
    
        // Extract individual values from the split array
        int restaurantId = Integer.parseInt(foodItemInfo[0]);
        String menuCategory = foodItemInfo[1];
        String itemName = foodItemInfo[2];
        double price = Double.parseDouble(foodItemInfo[3]);
    
        // Find the restaurant with the given id
        Restaurant restaurant = null;
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurantId) {
                restaurant = r;
                break;
            }
        }
    
        if (restaurant == null) {
            System.out.println("Restaurant not found. Food item cannot be added.");
            return;
        }
    
        // Create a new Menu object and add it to the menuItems list
        Menu newFoodItem = new Menu(restaurantId, menuCategory, itemName, price);
        menuItems.add(newFoodItem);

        // Save the new food item to the 'menu.txt' file
        saveMenuItemsToFile();
    
        System.out.println("Food item added to the menu successfully!");
    }

    private void saveRestaurantsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("restaurant.txt"))) {
            for (Restaurant restaurant : restaurants) {
                writer.println(restaurant.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving restaurant data to file.");
        }
    }

    private void saveMenuItemsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("menu.txt"))) {
            for (Menu menuItem : menuItems) {
                writer.println(menuItem.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving menu item data to file.");
        }
    }

    public void searchRestaurantsByName(String name) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No restaurant found with the given name.");
        }
    }
    
    public void searchRestaurantsByScore(double minScore, double maxScore) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRating() >= minScore && restaurant.getRating() <= maxScore) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No restaurant found within the given score range.");
        }
    }

    public void searchRestaurantsByCategory(String category) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            for (String cat : restaurant.getCategories()) {
                if (cat.toLowerCase().contains(category.toLowerCase())) {
                    System.out.println(restaurant);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No restaurant found with the given category.");
        }
    }
    
    
    public void searchRestaurantsByPrice(String priceRange) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getPriceRange().equalsIgnoreCase(priceRange)) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No restaurant found with the given price range.");
        }
    }
    
    public void searchRestaurantsByZip(String zipCode) {
        boolean found = false;
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getZipCode().equals(zipCode)) {
                System.out.println(restaurant);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No restaurant found with the given zip code.");
        }
    }
    
    public void displayCategoryWiseRestaurants() {
        List<String> categories = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            for (String category : restaurant.getCategories()) {
                if (!categories.contains(category)) {
                    categories.add(category);
                }
            }
        }
    
        for (String category : categories) {
            System.out.print(category + ": ");
            List<String> restaurantNames = new ArrayList<>();
            for (Restaurant restaurant : restaurants) {
                if (Arrays.asList(restaurant.getCategories()).contains(category)) {
                    restaurantNames.add(restaurant.getName());
                }
            }
            System.out.println(String.join(", ", restaurantNames));
        }
    }
    
    public void loadInitialData() {
        // Implement loading data from files 'restaurant.txt' and 'menu.txt'
        try {
            // Load restaurant data from 'restaurant.txt'
            Scanner restaurantScanner = new Scanner(new File("restaurant.txt"));
            while (restaurantScanner.hasNextLine()) {
                String restaurantLine = restaurantScanner.nextLine();
                String[] restaurantInfo = restaurantLine.split(",");
                int id = Integer.parseInt(restaurantInfo[0]);
                String name = restaurantInfo[1];
                double rating = Double.parseDouble(restaurantInfo[2]);
                String priceRange = restaurantInfo[3];
                String zipCode = restaurantInfo[4];
                List<String> categories = new ArrayList<>();
                for (int i = 5; i < restaurantInfo.length; i++) {
                    categories.add(restaurantInfo[i]);
                }
                Restaurant restaurant = new Restaurant(id, name, rating, priceRange, zipCode, categories);
                restaurants.add(restaurant);
            }
            restaurantScanner.close();

            // Load menu data from 'menu.txt'
            Scanner menuScanner = new Scanner(new File("menu.txt"));
            while (menuScanner.hasNextLine()) {
                String menuLine = menuScanner.nextLine();
                String[] menuInfo = menuLine.split(",");
                int restaurantId = Integer.parseInt(menuInfo[0]);
                String menuCategory = menuInfo[1];
                String itemName = menuInfo[2];
                double price = Double.parseDouble(menuInfo[3]);
                Menu menuItem = new Menu(restaurantId, menuCategory, itemName, price);
                menuItems.add(menuItem);
            }
            menuScanner.close();

            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Data files not found.");
        }
    }

    public void searchFoodItemsByName(String name) {
        List<Menu> foundFoodItems = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getItemName().toLowerCase().contains(name.toLowerCase())) {
                foundFoodItems.add(menuItem);
            }
        }
        displayFoodItems(foundFoodItems);
    }

    public void searchFoodItemsByNameInRestaurant(String restaurantName, String foodName) {
        List<Menu> foundFoodItems = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getRestaurantId() == getRestaurantIdByName(restaurantName) &&
                menuItem.getItemName().toLowerCase().contains(foodName.toLowerCase())) {
                foundFoodItems.add(menuItem);
            }
        }
        displayFoodItems(foundFoodItems);
    }

    public void searchFoodItemsByCategory(String category) {
        List<Menu> foundFoodItems = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getMenuCategory().toLowerCase().contains(category.toLowerCase())) {
                foundFoodItems.add(menuItem);
            }
        }
        displayFoodItems(foundFoodItems);
    }

    public void searchFoodItemsByCategoryInRestaurant(String restaurantName, String category) {
        List<Menu> foundFoodItems = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getRestaurantId() == getRestaurantIdByName(restaurantName) &&
                menuItem.getMenuCategory().toLowerCase().contains(category.toLowerCase())) {
                foundFoodItems.add(menuItem);
            }
        }
        displayFoodItems(foundFoodItems);
    }

    public void searchFoodItemsByPriceRange(double minPrice, double maxPrice) {
        List<Menu> foundFoodItems = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getPrice() >= minPrice && menuItem.getPrice() <= maxPrice) {
                foundFoodItems.add(menuItem);
            }
        }
        displayFoodItems(foundFoodItems);
    }

    public void searchFoodItemsByPriceRangeInRestaurant(String restaurantName, double minPrice, double maxPrice) {
        List<Menu> foundFoodItems = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getRestaurantId() == getRestaurantIdByName(restaurantName) && menuItem.getPrice() >= minPrice && menuItem.getPrice() <= maxPrice) {
                foundFoodItems.add(menuItem);
            }
        }
        displayFoodItems(foundFoodItems);
    }

    public void displayCostliestFoodItems(String restaurantName) {
        List<Menu> restaurantMenu = getRestaurantMenuByName(restaurantName);
        if (restaurantMenu.isEmpty()) {
            System.out.println("Restaurant not found.");
            return;
        }

        double maxPrice = Double.MIN_VALUE;
        List<Menu> costliestItems = new ArrayList<>();

        for (Menu menuItem : restaurantMenu) {
            if (menuItem.getPrice() > maxPrice) {
                costliestItems.clear();
                costliestItems.add(menuItem);
                maxPrice = menuItem.getPrice();
            } else if (menuItem.getPrice() == maxPrice) {
                costliestItems.add(menuItem);
            }
        }

        System.out.println("Costliest Food Item(s) in " + restaurantName + ":");
        displayFoodItems(costliestItems);
    }

    public void displayTotalFoodItemsForEachRestaurant() {
        for (Restaurant restaurant : restaurants) {
            List<Menu> restaurantMenu = getRestaurantMenuById(restaurant.getId());
            System.out.println(restaurant.getName() + ": " + restaurantMenu.size());
        }
    }

    // Helper methods

    public List<Menu> getRestaurantMenuById(int restaurantId) {
        List<Menu> restaurantMenu = new ArrayList<>();
        for (Menu menuItem : menuItems) {
            if (menuItem.getRestaurantId() == restaurantId) {
                restaurantMenu.add(menuItem);
            }
        }
        return restaurantMenu;
    }

    public List<Menu> getRestaurantMenuByName(String restaurantName) {
        return getRestaurantMenuById(getRestaurantIdByName(restaurantName));
    }

    public int getRestaurantIdByName(String restaurantName) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equalsIgnoreCase(restaurantName)) {
                return restaurant.getId();
            }
        }
        return -1; // Return -1 if restaurant not found
    }

    public String getRestaurantNameById(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                return restaurant.getName();
            }
        }
        return "Restaurant not found";
    }

    public void displayFoodItems(List<Menu> foodItems) {
        if (foodItems.isEmpty()) {
            System.out.println("No food items found.");
            return;
        }
    
        System.out.println("Found Food Items:");
        for (Menu foodItem : foodItems) {
            System.out.println("Restaurant: " + getRestaurantNameById(foodItem.getRestaurantId()));
            System.out.println("Category: " + foodItem.getMenuCategory());
            System.out.println("Name: " + foodItem.getItemName());
            System.out.println("Price: $" + foodItem.getPrice());
            System.out.println("---------------------");
        }
    }
    
}
