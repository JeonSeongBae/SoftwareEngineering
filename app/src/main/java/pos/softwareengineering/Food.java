package pos.softwareengineering;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Food {

    public ArrayList<Foodlist> foodlist = new ArrayList<Foodlist>();

    public Food(){
    }

    public void setFoodlist(ArrayList<Foodlist> foodlist) {
        this.foodlist = foodlist;
    }

    public ArrayList<Foodlist> getFoodlist() {
        return foodlist;
    }
}
