package pos.softwareengineering;

public class Item {
    private String category = " ";
    private String menu = " ";
    private String price = " ";

    public Item(String category, String menu, String price) {
        this.category = category;
        this.menu = menu;
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public String getMenu() {
        return menu;
    }

    public String getPrice() {
        return price;
    }
}
