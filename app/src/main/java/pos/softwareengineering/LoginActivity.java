package pos.softwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        switch (type){
            case "checksalebtn":{
                Intent intent1 = new Intent(this, CheckSalesActivity.class);
                startActivity(intent1);
                finish();
                break;
            }
            case "detailbtn":{
                Intent intent1 = new Intent(this, DetailViewActivity.class);
                startActivity(intent1);
                finish();
                break;
            }
            case "saleinfobtn":{
                Intent intent1 = new Intent(this, SaleInfoActivity.class);
                startActivity(intent1);
                finish();
                break;
            }
            default:{
                finish();
            }
        }
    }
}
