package pos.softwareengineering;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
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
    private StorageReference mStorageRef;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_menu);
        image = (ImageView) findViewById(R.id.img);
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
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
                intent.setType("image/*"); //이미지만 보이게
                //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEmpty()) {
                    if (category.getText().toString().equals("main")) {
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
                    } else if (category.getText().toString().equals("sub")) {
                        databaseReference.child("item").child("sub").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Food temp = dataSnapshot.getValue(Food.class);
                                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                                Foodlist inputFood = new Foodlist();
                                inputFood.setPrice(Integer.parseInt(price.getText().toString()));
                                inputFood.setMenu(menu.getText().toString());
                                foodlist.add(inputFood);
                                temp.setFoodlist(foodlist);
                                databaseReference.child("item").child("sub").setValue(temp);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    } else {
                        databaseReference.child("item").child("drink").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Food temp = dataSnapshot.getValue(Food.class);
                                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                                Foodlist inputFood = new Foodlist();
                                inputFood.setPrice(Integer.parseInt(price.getText().toString()));
                                inputFood.setMenu(menu.getText().toString());
                                foodlist.add(inputFood);
                                temp.setFoodlist(foodlist);
                                databaseReference.child("item").child("drink").setValue(temp);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    mStorageRef = FirebaseStorage.getInstance().getReference();
                    mStorageRef = mStorageRef.child(menu.getText().toString());
                    image.setDrawingCacheEnabled(true);
                    image.buildDrawingCache();
                    Drawable temp1 = image.getDrawable();
                    Bitmap Bitmap1 = ((BitmapDrawable)temp1).getBitmap();

                    ByteArrayOutputStream baos;
                    UploadTask uploadTask;
                    baos = new ByteArrayOutputStream();
                    Bitmap1.compress(Bitmap.CompressFormat.JPEG, 100,baos);
                    byte[] photodata = baos.toByteArray();
                    uploadTask = mStorageRef.putBytes(photodata);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getContext(),"실패",Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(getContext(),"성공",Toast.LENGTH_LONG).show();
                        }
                    });

                    Toast.makeText(mContext, "상품등록이 완료되었습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            private boolean checkEmpty() {
                if (category.getText().toString().isEmpty() || menu.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
                    Toast.makeText(mContext, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                //이미지가 한계이상(?) 크면 불러 오지 못하므로 사이즈를 줄여 준다.
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                image.setImageBitmap(scaled);

            } else {
                Toast.makeText(this, "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }


}
