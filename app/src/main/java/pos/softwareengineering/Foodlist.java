package pos.softwareengineering;

public class Foodlist {
    public String menu;
    public int number;
    public int date;
    public int price;
    public String category;

    public Foodlist() {
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMenu() {
        return menu;
    }

    public int getNumber() {
        return number;
    }
}
