package pos.softwareengineering;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckSalesActivity extends AppCompatActivity {
    Context mContext;
    EditText startDate;
    EditText endDate;
    Button search;
    Button cancle;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView mainList;
    ListView subList;
    ListView drinkList;
    ListViewAdapter mainAdapter;
    ListViewAdapter subAdapter;
    ListViewAdapter drinkAdapter;
    TextView allPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sales);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mContext = this;
        startDate = (EditText) findViewById(R.id.startDate);
        endDate = (EditText) findViewById(R.id.endDate);
        search = (Button) findViewById(R.id.search);
        cancle = (Button) findViewById(R.id.cancel);
        allPrice = (TextView) findViewById(R.id.allPrice);

        mainList = (ListView)findViewById(R.id.mainList);
        mainAdapter = new ListViewAdapter();

        subList=(ListView)findViewById(R.id.subList);
        subAdapter = new ListViewAdapter();

        drinkList=(ListView)findViewById(R.id.drinkList);
        drinkAdapter = new ListViewAdapter();

        mainList.setAdapter(mainAdapter);
        subList.setAdapter(subAdapter);
        drinkList.setAdapter(drinkAdapter);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        databaseReference.child("food").child("main").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food temp = dataSnapshot.getValue(Food.class);
                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                ArrayList<String> check = new ArrayList<String>();
                ArrayList<Foodlist> foodview = new ArrayList<>();
                for (int i=0; i<foodlist.size();i++){
                    Foodlist food = foodlist.get(i);
                    if (!check.contains(food.getMenu())){
                        int count = 0;
                        for (int index = 0; index < foodlist.size(); index++){
                            int date = foodlist.get(index).getDate();
                            if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > 000000 && date < 999999){
                                int s = foodlist.get(index).getNumber();
                                count += s;
                            }
                        }
                        check.add(food.getMenu());
                        food.setNumber(count);
                        foodview.add(food);
                    }
                }
                int tempprice = Integer.parseInt(allPrice.getText().toString());
                for (int i =0;i<foodview.size();i++){
                    int sumprice = foodview.get(i).getPrice();
                    int sumnumber = foodview.get(i).getNumber();
                    tempprice += sumprice*sumnumber;
                }
                allPrice.setText(String.valueOf(tempprice));
                mainAdapter.addItem(foodview);
                mainAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("food").child("sub").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("SNAPSHOT==", dataSnapshot.toString());
                Food temp = dataSnapshot.getValue(Food.class);
                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                ArrayList<String> check = new ArrayList<String>();
                ArrayList<Foodlist> foodview = new ArrayList<>();
                for (int i=0; i<foodlist.size();i++){
                    Foodlist food = foodlist.get(i);
                    if (!check.contains(food.getMenu())){
                        int count = 0;
                        for (int index = 0; index < foodlist.size(); index++){
                            int date = foodlist.get(index).getDate();
                            if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > 000000 && date < 999999){
                                int s = foodlist.get(index).getNumber();
                                count += s;
                            }
                        }
                        check.add(food.getMenu());
                        food.setNumber(count);
                        foodview.add(food);
                    }
                }
                int tempprice = Integer.parseInt(allPrice.getText().toString());
                for (int i =0;i<foodview.size();i++){
                    int sumprice = foodview.get(i).getPrice();
                    int sumnumber = foodview.get(i).getNumber();
                    tempprice += sumprice*sumnumber;
                }
                allPrice.setText(String.valueOf(tempprice));
                subAdapter.addItem(foodview);
                subAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("food").child("drink").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("SNAPSHOT==", dataSnapshot.toString());
                Food temp = dataSnapshot.getValue(Food.class);
                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                ArrayList<String> check = new ArrayList<String>();
                ArrayList<Foodlist> foodview = new ArrayList<>();
                for (int i=0; i<foodlist.size();i++){
                    Foodlist food = foodlist.get(i);
                    if (!check.contains(food.getMenu())){
                        int count = 0;
                        for (int index = 0; index < foodlist.size(); index++){
                            int date = foodlist.get(index).getDate();
                            if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > 000000 && date < 999999){
                                int s = foodlist.get(index).getNumber();
                                count += s;
                            }
                        }
                        check.add(food.getMenu());
                        food.setNumber(count);
                        foodview.add(food);
                    }
                }
                int tempprice = Integer.parseInt(allPrice.getText().toString());
                for (int i =0;i<foodview.size();i++){
                    int sumprice = foodview.get(i).getPrice();
                    int sumnumber = foodview.get(i).getNumber();
                    tempprice += sumprice*sumnumber;
                }
                allPrice.setText(String.valueOf(tempprice));
                drinkAdapter.addItem(foodview);
                drinkAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allPrice.setText("0");
                if(startDate.getText().toString().equals("000000")&&endDate.getText().toString().equals("000000")){
                    finish();
                }
                databaseReference.child("food").child("main").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("SNAPSHOT==", dataSnapshot.toString());
                        Food temp = dataSnapshot.getValue(Food.class);
                        ArrayList<Foodlist> foodlist = temp.getFoodlist();
                        ArrayList<String> check = new ArrayList<String>();
                        ArrayList<Foodlist> foodview = new ArrayList<>();
                        for (int i=0; i<foodlist.size();i++){
                            Foodlist food = foodlist.get(i);
                            if (!check.contains(food.getMenu())){
                                int count = 0;
                                for (int index = 0; index < foodlist.size(); index++){
                                    int date = foodlist.get(index).getDate();
                                    if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > Integer.parseInt(startDate.getText().toString()) && date < Integer.parseInt(endDate.getText().toString())){
                                        int s = foodlist.get(index).getNumber();
                                        count += s;
                                    }
                                }
                                check.add(food.getMenu());
                                food.setNumber(count);
                                if (count != 0){
                                    foodview.add(food);
                                }
                            }
                        }
                        int tempprice = Integer.parseInt(allPrice.getText().toString());
                        for (int i =0;i<foodview.size();i++){
                            int sumprice = foodview.get(i).getPrice();
                            int sumnumber = foodview.get(i).getNumber();
                            tempprice += sumprice*sumnumber;
                        }
                        allPrice.setText(String.valueOf(tempprice));
                        mainAdapter.addItem(foodview);
                        mainAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                databaseReference.child("food").child("sub").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("SNAPSHOT==", dataSnapshot.toString());
                        Food temp = dataSnapshot.getValue(Food.class);
                        ArrayList<Foodlist> foodlist = temp.getFoodlist();
                        ArrayList<String> check = new ArrayList<String>();
                        ArrayList<Foodlist> foodview = new ArrayList<>();
                        for (int i=0; i<foodlist.size();i++){
                            Foodlist food = foodlist.get(i);
                            if (!check.contains(food.getMenu())){
                                int count = 0;
                                for (int index = 0; index < foodlist.size(); index++){
                                    int date = foodlist.get(index).getDate();
                                    if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > Integer.parseInt(startDate.getText().toString()) && date < Integer.parseInt(endDate.getText().toString())){
                                        int s = foodlist.get(index).getNumber();
                                        count += s;
                                    }
                                }
                                check.add(food.getMenu());
                                food.setNumber(count);
                                if (count != 0){
                                    foodview.add(food);
                                }
                            }
                        }
                        int tempprice = Integer.parseInt(allPrice.getText().toString());
                        for (int i =0;i<foodview.size();i++){
                            int sumprice = foodview.get(i).getPrice();
                            int sumnumber = foodview.get(i).getNumber();
                            tempprice += sumprice*sumnumber;
                        }
                        allPrice.setText(String.valueOf(tempprice));
                        subAdapter.addItem(foodview);
                        subAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                databaseReference.child("food").child("drink").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("SNAPSHOT==", dataSnapshot.toString());
                        Food temp = dataSnapshot.getValue(Food.class);
                        ArrayList<Foodlist> foodlist = temp.getFoodlist();
                        ArrayList<String> check = new ArrayList<String>();
                        ArrayList<Foodlist> foodview = new ArrayList<>();
                        for (int i=0; i<foodlist.size();i++){
                            Foodlist food = foodlist.get(i);
                            if (!check.contains(food.getMenu())){
                                int count = 0;
                                for (int index = 0; index < foodlist.size(); index++){
                                    int date = foodlist.get(index).getDate();
                                    if (foodlist.get(index).getMenu().equals(food.getMenu()) && date > Integer.parseInt(startDate.getText().toString()) && date < Integer.parseInt(endDate.getText().toString())){
                                        int s = foodlist.get(index).getNumber();
                                        count += s;
                                    }
                                }
                                check.add(food.getMenu());
                                food.setNumber(count);
                                if (count != 0){
                                    foodview.add(food);
                                }
                            }
                        }
                        int tempprice = Integer.parseInt(allPrice.getText().toString());
                        for (int i =0;i<foodview.size();i++){
                            int sumprice = foodview.get(i).getPrice();
                            int sumnumber = foodview.get(i).getNumber();
                            tempprice += sumprice*sumnumber;
                        }
                        allPrice.setText(String.valueOf(tempprice));
                        drinkAdapter.addItem(foodview);
                        drinkAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
