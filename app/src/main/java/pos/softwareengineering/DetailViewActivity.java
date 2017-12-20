package pos.softwareengineering;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class DetailViewActivity extends AppCompatActivity {
    Button registbtn;
    ListView mainList;
    ListView subList;
    ListView drinkList;
    EditText updatePrice;
    detailListViewAdapter mainAdapter;
    detailListViewAdapter subAdapter;
    detailListViewAdapter drinkAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Context mContext;
    public class detailListViewAdapter extends BaseAdapter {

        ArrayList<Foodlist> foodlist = new ArrayList<Foodlist>();
        Button remove;
        Button modify;
        String upPrice;

        @Override
        public int getCount() {
            return foodlist.size();
        }

        @Override
        public Object getItem(int i) {
            return foodlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final int index = i;
            final Foodlist food = foodlist.get(i);

            if (view == null){
                LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.detail_listview, viewGroup, false);
            }
            TextView menu = (TextView) view.findViewById(R.id.menu);
            TextView number = (TextView) view.findViewById(R.id.price);
            final ImageView image = (ImageView)view.findViewById(R.id.image);

            remove = (Button) view.findViewById(R.id.remove);
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference.child("item").child(foodlist.get(index).getCategory()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Food temp = dataSnapshot.getValue(Food.class);
                            ArrayList<Foodlist> dbfoodlist = temp.getFoodlist();
                            dbfoodlist.remove(index);
                            temp.setFoodlist(dbfoodlist);
                            String a = dataSnapshot.getKey();
                            databaseReference.child("item").child(a).setValue(temp);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    foodlist.remove(index);
                    notifyDataSetChanged();
                }
            });

            modify = (Button) view.findViewById(R.id.modify);
            modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Foodlist tempfood = foodlist.get(index);
                    tempfood.setPrice(Integer.parseInt(updatePrice.getText().toString()));
                    foodlist.set(index, tempfood);
                    updateDB();
                    upPrice = updatePrice.getText().toString();
                    updatePrice.setText("");
                    notifyDataSetChanged();
                }

                private void updateDB() {
                    databaseReference.child("item").child(foodlist.get(index).getCategory()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Food temp = dataSnapshot.getValue(Food.class);
                            ArrayList<Foodlist> dbfoodlist = temp.getFoodlist();
                            Foodlist dbfood = dbfoodlist.get(index);
                            dbfood.setPrice(Integer.parseInt(upPrice));
                            dbfoodlist.set(index,dbfood);
                            temp.setFoodlist(dbfoodlist);
                            String a = dataSnapshot.getKey();
                            databaseReference.child("item").child(a).setValue(temp);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            });

            menu.setText(food.getMenu());
            number.setText(String.valueOf(food.getPrice()));
            final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference().child(menu.getText().toString());
            mStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(getApplicationContext()).using(new FirebaseImageLoader()).load(mStorageRef).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(image);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

            return view;
        }
        public void addItem(ArrayList<Foodlist> food){
            foodlist = food;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        updatePrice = (EditText) findViewById(R.id.updatePrice);
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
        databaseReference.child("item").child("sub").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food temp = dataSnapshot.getValue(Food.class);
                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                subAdapter.addItem(foodlist);
                subAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("item").child("drink").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Food temp = dataSnapshot.getValue(Food.class);
                ArrayList<Foodlist> foodlist = temp.getFoodlist();
                drinkAdapter.addItem(foodlist);
                drinkAdapter.notifyDataSetChanged();
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