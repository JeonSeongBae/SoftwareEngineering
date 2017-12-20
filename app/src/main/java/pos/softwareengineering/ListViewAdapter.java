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

        menu.setText(food.getMenu());
        number.setText(String.valueOf(food.getNumber()));

        return view;
    }
    public void addItem(ArrayList<Foodlist> food){
        foodlist = food;
    }
}
