package pos.softwareengineering;

/**
 * Created by dldlw on 2017-12-20.
 */

public class ListItem {
    private String menu;
    private int count;
    private int price;


    public ListItem(String menu, int count,int price) {
        this.menu = menu;
        this.count = count;
        this.price = price;
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

    public String getMenu() {
        return menu;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
