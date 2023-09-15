public class Menu {
    private int restaurantId;
    private String menuCategory;
    private String itemName;
    private double price;

    public Menu(int restaurantId, String menuCategory, String itemName, double price) {
        this.restaurantId = restaurantId;
        this.menuCategory = menuCategory;
        this.itemName = itemName;
        this.price = price;
    }

    // Getter methods for fields
    public int getRestaurantId(){
        return restaurantId;
    }

    public String getMenuCategory(){
        return menuCategory;
    }

    public String getItemName(){
        return itemName;
    }

    public double getPrice(){
        return price;
    }

    public String toString() {
        return restaurantId + "," + menuCategory + "," + itemName + "," + String.format("%.2f", price);
    }
}
