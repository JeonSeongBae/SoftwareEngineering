package pos.softwareengineering;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterMenuActivity extends AppCompatActivity {
    Context mContext;
    ImageView image;
    EditText category;
    EditText menu;
    EditText price;
    Button cancel;
    Button register;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);

        itemList = new ArrayList<Item>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mContext = this;
        category = (EditText) findViewById(R.id.category);
        menu = (EditText) findViewById(R.id.menu);
        price = (EditText) findViewById(R.id.price);
        cancel = (Button) findViewById(R.id.cancel);
        register = (Button) findViewById(R.id.register);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Food food = new Food();
        ArrayList<Foodlist> foodlist =new ArrayList<>();
        Foodlist a = new Foodlist();

        a.setMenu("name");
        a.setPrice(3000);
        foodlist.add(a);
        food.setFoodlist(foodlist);
        foodlist =new ArrayList<>();
        databaseReference.child("item").child("sub").setValue(food);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.child("item").child("main").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            Food temp = dataSnapshot.getValue(Food.class);
                            ArrayList<Foodlist> foodlist = temp.getFoodlist();
                            Foodlist inputFood = new Foodlist();
                            inputFood.setPrice(Integer.parseInt(price.getText().toString()));
                            inputFood.setMenu(menu.getText().toString());
                            foodlist.add(inputFood);
                            temp.setFoodlist(foodlist);
                            databaseReference.child("item").child("main").setValue(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(mContext,"상품등록이 완료되었습니다.",Toast.LENGTH_LONG).show();
                finish();
            }

            private boolean checkEmpty() {
                if (category.getText().toString().isEmpty()||menu.getText().toString().isEmpty()||price.getText().toString().isEmpty()){
                    Toast.makeText(mContext,"입력되지 않은 정보가 있습니다.",Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        });
    }
}
