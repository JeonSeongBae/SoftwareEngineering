package pos.softwareengineering;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentActivity extends AppCompatActivity {
    ListView listView;
    EditText givenpay;
    int drinknum,mainnum,subnum;
    TextView tablen;
    TextView pay,givepay,exchange;
    Context mContext;
    ArrayList<Integer> discountrate;
    int allpay;
    ExpandableListView expandableListView;
    Button cancle,payment;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    ArrayList<Integer> intarray, pricearray;
    ArrayList<String> stringarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        expandableListView = (ExpandableListView)findViewById(R.id.expanded_menu);
        mContext = this;
        drinknum =0;
        subnum = 0;
        mainnum = 0;
        discountrate = new ArrayList<Integer>();
        givepay = (TextView)findViewById(R.id.givepay);
        cancle = (Button)findViewById(R.id.canclebtn);
        payment = (Button)findViewById(R.id.paymentbtn);
        listView = (ListView)findViewById(R.id.paytable);
        tablen = (TextView)findViewById(R.id.tablenum);
        pay = (TextView)findViewById(R.id.pay);
        allpay = 0;
        givenpay = (EditText)findViewById(R.id.givenpay);
        exchange = (TextView)findViewById(R.id.exchange);
        databaseReference.child("food").child("main").child("foodlist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mainnum++;
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
        databaseReference.child("food").child("sub").child("foodlist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                subnum++;
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
        databaseReference.child("food").child("drink").child("foodlist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                drinknum++;
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
        Intent intent = getIntent();
        String tablenum =intent.getStringExtra("tablenumber");
        tablen.setText(tablenum);
        intarray = intent.getIntegerArrayListExtra("intarray");
        stringarray = intent.getStringArrayListExtra("stringarray");
        pricearray = intent.getIntegerArrayListExtra("pricearray");

        givenpay.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!(givenpay.getText().toString().equals(""))){
                    exchange.setText("거스름돈 : " + (Integer.parseInt(givenpay.getText().toString()) - Integer.parseInt(pay.getText().toString())) );
                }
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"결제가 완료되었습니다!",Toast.LENGTH_SHORT).show();

                databaseReference.child("item").child("main").child("foodlist").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Foodlist food = dataSnapshot.getValue(Foodlist.class);
                        for(int i=0;i<stringarray.size();i++){
                            if(food.getMenu().equals(stringarray.get(i))){
                                long now = System.currentTimeMillis();
                                Date date = new Date(now);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                String getTime = sdf.format(date);
                                int time = Integer.parseInt(getTime)-20000000;
                                food.setDate(time);
                                food.setNumber(intarray.get(i));
                                databaseReference.child("food").child("main").child("foodlist").child(mainnum+"").setValue(food);
                                mainnum++;
                            }
                        }
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
                databaseReference.child("item").child("sub").child("foodlist").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Foodlist food = dataSnapshot.getValue(Foodlist.class);
                        for(int i=0;i<stringarray.size();i++){
                            if(food.getMenu().equals(stringarray.get(i))){
                                long now = System.currentTimeMillis();
                                Date date = new Date(now);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                String getTime = sdf.format(date);
                                int time = Integer.parseInt(getTime)-20000000;
                                food.setDate(time);
                                food.setNumber(intarray.get(i));
                                databaseReference.child("food").child("sub").child("foodlist").child(subnum+"").setValue(food);
                                subnum++;
                            }
                        }

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
                databaseReference.child("item").child("drink").child("foodlist").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Foodlist food = dataSnapshot.getValue(Foodlist.class);
                        for(int i=0;i<stringarray.size();i++){
                            if(food.getMenu().equals(stringarray.get(i))){
                                long now = System.currentTimeMillis();
                                Date date = new Date(now);
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                                String getTime = sdf.format(date);
                                int time = Integer.parseInt(getTime)-20000000;
                                food.setDate(time);
                                food.setNumber(intarray.get(i));
                                databaseReference.child("food").child("drink").child("foodlist").child(drinknum+"").setValue(food);
                                drinknum++;
                            }
                        }
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



            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tableListAdapter adapter = new tableListAdapter();

        listView.setAdapter(adapter);
        for(int i = 0; i<intarray.size(); i++){
            adapter.addItem(new ListItem(stringarray.get(i),intarray.get(i),pricearray.get(i)));
            allpay += intarray.get(i) * pricearray.get(i);
        }
        pay.setText(allpay+"");
        ArrayList<ExpandableItem> groupList = new ArrayList<>();
        ArrayList<String> payList = new ArrayList<>();
        payList.add("현금계산");
        payList.add("카드계산");
        payList.add("포인트계산");
        final ArrayList<String> discountList = new ArrayList<>();
        discountList.add("할인없음");
        discountrate.add(0);

        databaseReference.child("discount").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                discount discou = dataSnapshot.getValue(discount.class);
                discountList.add(discou.getName());
                discountrate.add(Integer.parseInt(discou.getNumber()));
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


        groupList.add(new ExpandableItem("결제수단", payList));
        groupList.add(new ExpandableItem("할인적용", discountList));
        ExpandableListViewAdapter adapter2 = new ExpandableListViewAdapter(groupList);
        expandableListView.setAdapter(adapter2);
        adapter.notifyDataSetChanged();
    }
    public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
        public ArrayList<ExpandableItem> list;

        public ExpandableListViewAdapter(ArrayList<ExpandableItem> list) {
            this.list = list;
        }

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return list.get(groupPosition).getChildList().size() == 0 ? 1 : list.get(groupPosition).getChildList().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return list.get(groupPosition).getTitle();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return list.get(groupPosition).getChildList().get(childPosition).toString();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.group_item, null);

            TextView title = (TextView) view.findViewById(R.id.group_title);
            title.setText(list.get(groupPosition).getTitle());

            return view;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = null;

            if(groupPosition == 0) { // 결제수단
                view = LayoutInflater.from(mContext).inflate(R.layout.child_item, null);
                final Button button = (Button) view.findViewById(R.id.child_item);

                button.setText(list.get(groupPosition).getChildList().get(childPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    //                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                    if(childPosition != 0){
                        givepay.setVisibility(View.INVISIBLE);
                        givenpay.setVisibility(View.INVISIBLE);
                        exchange.setVisibility(View.INVISIBLE);
                    }else{
                        givepay.setVisibility(View.VISIBLE);
                        givenpay.setVisibility(View.VISIBLE);
                        exchange.setVisibility(View.VISIBLE);
                    }
//                      Log.d("third",estimation.getNumber());

                    }
                });
            }else if(groupPosition == 1) { // 할인적용
                view = LayoutInflater.from(mContext).inflate(R.layout.child_item, null);
                final Button button = (Button) view.findViewById(R.id.child_item);

                button.setText(list.get(groupPosition).getChildList().get(childPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    //                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    @Override
                    public void onClick(View v) {
                        pay.setText(allpay/100*(100-discountrate.get(childPosition))+"");
//                      Log.d("third",estimation.getNumber());

                    }
                });
            }
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}
