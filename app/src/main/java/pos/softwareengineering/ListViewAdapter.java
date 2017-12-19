package pos.softwareengineering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{
    ArrayList<Foodlist> foodlist = new ArrayList<Foodlist>();
    int startDate;
    int endDate;
    ArrayList<String> check = new ArrayList<String>();

    @Override
    public int getCount() {
        return foodlist.size();
    }

    @Override
    public Object getItem(int i) {
        return foodlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Foodlist food = foodlist.get(i);
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.checksales_listview, viewGroup, false);
        }
        TextView menu = (TextView) view.findViewById(R.id.menu);
        TextView number = (TextView) view.findViewById(R.id.number);
        if (!check.contains(food.getMenu())){
            int count = 0;
            for (int index = 0; index < foodlist.size(); index++){
                int date = foodlist.get(index).getDate();
                if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > startDate && date < endDate){
                    int s = foodlist.get(index).getNumber();
                    count += s;
                }
            }
            menu.setText(food.getMenu());
            check.add(food.getMenu());
            number.setText(String.valueOf(count));
        }
        return view;
    }
    public void addItem(ArrayList<Foodlist> food, String start, String end){
        startDate = Integer.parseInt(start);
        endDate = Integer.parseInt(end);
        foodlist = food;
    }
}
