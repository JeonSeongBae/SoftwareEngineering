package pos.softwareengineering;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Context mContext;
    EditText ID;
    EditText password;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mContext = this;
        ID = (EditText) findViewById(R.id.ID);
        password = (EditText) findViewById(R.id.pass);
        loginbtn = (Button) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("UserID").child(ID.getText().toString()).setValue(password.getText().toString());
                login();
            }
        });
    }

    private void login() {
        databaseReference.child("UserID").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> a = (HashMap<String, String>) dataSnapshot.getValue();
                if (a.get(ID.getText().toString()).equals(password.getText().toString())){
                    Intent intent = getIntent();
                    String type = intent.getStringExtra("type");
                    switch (type){
                        case "checksalebtn":{
                            Intent intent1 = new Intent(mContext, CheckSalesActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                        }
                        case "detailbtn":{
                            Intent intent1 = new Intent(mContext, DetailViewActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                        }
                        case "saleinfobtn":{
                            Intent intent1 = new Intent(mContext, SaleInfoActivity.class);
                            startActivity(intent1);
                            finish();
                            break;
                        }
                        default:{
                            finish();
                        }
                    }
                } else{
                    Toast.makeText(mContext, "ID 또는 Password가 틀렸습니다.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
