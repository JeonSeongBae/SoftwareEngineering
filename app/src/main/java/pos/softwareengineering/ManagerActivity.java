package pos.softwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    Button saleinfobtn, backbtn,detailbtn,checksalebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        saleinfobtn = (Button)findViewById(R.id.saleinfobtn);
        backbtn = (Button)findViewById(R.id.backbtn);
        detailbtn =  (Button)findViewById(R.id.detailbtn);
        checksalebtn = (Button)findViewById(R.id.checksalebtn);

        saleinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this,LoginActivity.class);
                intent.putExtra("type","saleinfobtn");
                startActivity(intent);
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        detailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this,LoginActivity.class);
                intent.putExtra("type","detailbtn");
                startActivity(intent);
            }
        });
        checksalebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerActivity.this,LoginActivity.class);
                intent.putExtra("type","checksalebtn");
                startActivity(intent);
            }
        });
    }
}
