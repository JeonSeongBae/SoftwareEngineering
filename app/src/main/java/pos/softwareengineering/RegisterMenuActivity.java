package pos.softwareengineering;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = new Item(category.getText().toString(), menu.getText().toString(), price.getText().toString());
                databaseReference.child("item").child(menu.getText().toString()).setValue(item);
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
