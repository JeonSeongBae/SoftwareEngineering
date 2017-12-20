package pos.softwareengineering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailViewActivity extends AppCompatActivity {
    Button registbtn;
    ListView mainList;
    ListView subList;
    ListView drinkList;
    detailListViewAdapter mainAdapter;
    detailListViewAdapter subAdapter;
    detailListViewAdapter drinkAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mContext = this;

        mainList = (ListView)findViewById(R.id.mainList);
        mainAdapter = new detailListViewAdapter();

        subList=(ListView)findViewById(R.id.subList);
        subAdapter = new detailListViewAdapter();

        drinkList=(ListView)findViewById(R.id.drinkList);
        drinkAdapter = new detailListViewAdapter();
        mainList.setAdapter(mainAdapter);
        subList.setAdapter(subAdapter);
        drinkList.setAdapter(drinkAdapter);

        databaseReference.child("item").child("main").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food temp = dataSnapshot.getValue(Food.class);
                ArrayList<Foodlist> foodlist = temp.getFoodlist();


                mainAdapter.addItem(foodlist);
                mainAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        registbtn = (Button)findViewById(R.id.registbtn);
        registbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
