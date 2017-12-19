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

        mainList = (ListView)findViewById(R.id.mainList);
        mainAdapter = new ListViewAdapter();

        subList=(ListView)findViewById(R.id.subList);
        subAdapter = new ListViewAdapter();

        drinkList=(ListView)findViewById(R.id.drinkList);
        drinkAdapter = new ListViewAdapter();

        mainList.setAdapter(mainAdapter);
        subList.setAdapter(subAdapter);
        drinkList.setAdapter(drinkAdapter);

        Food te = new Food();
        final ArrayList<Foodlist> a = new ArrayList<Foodlist>();
        Foodlist fl = new Foodlist();
        fl.setMenu("menuaa");
        fl.setNumber(4);
        fl.setDate(171230);
        a.add(fl);
        a.add(fl);
        a.add(fl);
        a.add(fl);
        a.add(fl);
        Foodlist fll = new Foodlist();
        fll.setMenu("891fd");
        fll.setNumber(23);
        fll.setDate(182030);
        a.add(fll);
        te.setFoodlist(a);
        databaseReference.child("food").child("main").setValue(te);

        databaseReference.child("food").child("main").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("SNAPSHOT==", dataSnapshot.toString());
                Food temp = dataSnapshot.getValue(Food.class);
                mainAdapter.addItem(temp.getFoodlist(),"000000","999999");
                ArrayList<Integer> aa = new ArrayList<Integer>();
                ArrayList<String> bb = new ArrayList<String>();

                for (int i=0;i<temp.getFoodlist().size();i++){
                    if (bb.contains(temp.getFoodlist().get(i).getMenu())){
                        aa.add(i);
                    }else{
                        bb.add(temp.getFoodlist().get(i).getMenu());
                    }
                }
                for (int i=0;i<bb.size();i++){
                    temp.getFoodlist().remove(bb.get(i));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("food").child("main").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("SNAPSHOT==", dataSnapshot.toString());
                        Food temp = dataSnapshot.getValue(Food.class);
                        mainAdapter.addItem(temp.getFoodlist(),startDate.getText().toString(), endDate.getText().toString());
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
