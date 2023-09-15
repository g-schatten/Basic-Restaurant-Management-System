import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private double rating;
    private String priceRange;
    private String zipCode;
    private List<String> categories;

    public Restaurant(int id, String name, double rating, String priceRange, String zipCode, List<String> categories) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.priceRange = priceRange;
        this.zipCode = zipCode;
        this.categories = categories;
    }

    // Getter methods for fields
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getRating(){
        return rating;
    }

    public String getPriceRange(){
        return priceRange;
    }

    public String getZipCode(){
        return zipCode;
    }

    public List<String> getCategories(){
        return categories;
    }

    public String toString() {
        return id + "," + name + "," + rating + "," + priceRange + "," + zipCode + "," + String.join(",", categories);
    }
}
