package pos.softwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SaleInfoActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    int discountnum;
    EditText discountname;
    EditText discountrate;
    Button regist,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_info);
        regist = (Button)findViewById(R.id.discountregist);
        cancel = (Button)findViewById(R.id.canclebutton);
        discountnum = 0;
        discountname = (EditText)findViewById(R.id.discountname);
        discountrate = (EditText)findViewById(R.id.discountrate);

        databaseReference.child("discount").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                discountnum++;
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discount disco = new discount(discountname.getText().toString(),discountrate.getText().toString());
                databaseReference.child("discount").child(discountnum+"").setValue(disco);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}
