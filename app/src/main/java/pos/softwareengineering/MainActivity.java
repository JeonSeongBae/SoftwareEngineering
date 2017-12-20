package pos.softwareengineering;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ExpandableListViewAdapter adapter;
    ArrayList<String> mainList,subList,drinkList;
    ArrayList<Integer> mainprice,subprice,drinkprice;
    Button paybtn,managerbtn;
    ListView table1,table2,table3,table4,selecttable;
    Context mContext;
    String tablenum;
    TextView tb1,tb2,tb3,tb4;
    ExpandableListView expandableListView;
    tableListAdapter adapter1,adapter2,adapter3,adapter4,selectadapter;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView)findViewById(R.id.expanded_menu);
        managerbtn = (Button)findViewById(R.id.managerbtn);
        paybtn = (Button)findViewById(R.id.paybtn);
        table1 = (ListView) findViewById(R.id.table1);
        table2 = (ListView) findViewById(R.id.table2);
        table3 = (ListView) findViewById(R.id.table3);
        table4 = (ListView) findViewById(R.id.table4);
        adapter1 = new tableListAdapter();
        adapter2 = new tableListAdapter();
        adapter3 = new tableListAdapter();
        adapter4 = new tableListAdapter();
        mainprice = new ArrayList<>();
        subprice = new ArrayList<>();
        drinkprice = new ArrayList<>();
        table1.setAdapter(adapter1);
        table2.setAdapter(adapter2);
        table3.setAdapter(adapter3);
        table4.setAdapter(adapter4);
        selecttable = null;
        mContext = this;
        tb1 = (TextView)findViewById(R.id.tb1);
        tb2 = (TextView)findViewById(R.id.tb2);
        tb3 = (TextView)findViewById(R.id.tb3);
        tb4 = (TextView)findViewById(R.id.tb4);
        managerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ManagerActivity.class);
                startActivity(intent);

            }
        });
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selecttable == null) {
                    Toast.makeText(mContext, "테이블을 선택해주세요.", Toast.LENGTH_LONG).show();

                } else {
                    Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                    ArrayList<Integer> intarray = new ArrayList<>();
                    ArrayList<String> stringarray = new ArrayList<>();
                    ArrayList<Integer> pricearray = new ArrayList<>();
                    for (int i = 0; i < selectadapter.getCount(); i++) {
                        intarray.add(((ListItem) selectadapter.getItem(i)).getCount());
                    }
                    for (int i = 0; i < selectadapter.getCount(); i++) {
                        stringarray.add(((ListItem) selectadapter.getItem(i)).getMenu());
                    }
                    for (int i = 0; i < selectadapter.getCount(); i++) {
                        pricearray.add(((ListItem) selectadapter.getItem(i)).getPrice());
                    }
                    intent.putExtra("pricearray", pricearray);
                    intent.putExtra("intarray", intarray);
                    intent.putExtra("stringarray", stringarray);
                    intent.putExtra("tablenumber", tablenum);
                    startActivity(intent);
                }
            }
        });

        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"table1",Toast.LENGTH_SHORT).show();
                selecttable = table1;
                selectadapter = adapter1;
                tablenum = "#1";
            }
        });
        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"table2",Toast.LENGTH_SHORT).show();

                selecttable = table2;
                selectadapter = adapter2;
                tablenum = "#2";

            }
        });
        tb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"table3",Toast.LENGTH_SHORT).show();
                tablenum = "#3";
                selecttable = table3;
                selectadapter = adapter3;
            }
        });
        tb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"table4",Toast.LENGTH_SHORT).show();
                tablenum = "#4";
                selecttable = table4;
                selectadapter = adapter4;
            }
        });
        ArrayList<ExpandableItem> groupList = new ArrayList<>();
        mainList = new ArrayList<>();
        subList = new ArrayList<>();
        drinkList = new ArrayList<>();

        databaseReference.child("item").child("main").child("foodlist").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Foodlist main = dataSnapshot.getValue(Foodlist.class);
                mainList.add(main.getMenu());
                mainprice.add(main.getPrice());
                adapter.notifyDataSetChanged();
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
                Foodlist sub = dataSnapshot.getValue(Foodlist.class);
                subList.add(sub.getMenu());
                subprice.add(sub.getPrice());
                adapter.notifyDataSetChanged();
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
                Foodlist drink = dataSnapshot.getValue(Foodlist.class);
                drinkList.add(drink.getMenu());
                drinkprice.add(drink.getPrice());
                adapter.notifyDataSetChanged();
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
        groupList.add(new ExpandableItem("주 메 뉴", mainList));
        groupList.add(new ExpandableItem("부 메 뉴", subList));
        groupList.add(new ExpandableItem("음 료", drinkList));
        adapter = new ExpandableListViewAdapter(groupList);
        expandableListView.setAdapter(adapter);

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

        @SuppressLint("ResourceAsColor")
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.group_item, null);
            view.setBackgroundColor(R.color.green);
            TextView title = (TextView) view.findViewById(R.id.group_title);
            title.setText(list.get(groupPosition).getTitle());

            return view;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = null;
            int i = 0;
            if(groupPosition == 0) { // 주메뉴
                view = LayoutInflater.from(mContext).inflate(R.layout.normal_child_item, null);
                final Button button = (Button) view.findViewById(R.id.normal_child_item);
                final TextView text = (TextView)view.findViewById(R.id.child_item);
                final EditText num = (EditText)view.findViewById(R.id.ordernum);
                text.setText(list.get(groupPosition).getChildList().get(childPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    //                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        if (selecttable == null) {
                            Toast.makeText(mContext, "테이블을 선택해주세요.", Toast.LENGTH_LONG).show();

                        } else {
                            //button.setBackgroundColor(R.color.selected);
                            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(mContext);
                            alert_confirm.setMessage("메뉴를 추가하시겠습니까?").setCancelable(false).setPositiveButton("추가", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "추가되었습니다.", Toast.LENGTH_LONG).show();
                                    selectadapter.addItem(new ListItem(text.getText().toString(),Integer.parseInt(num.getText().toString()),mainprice.get(childPosition)));
                                    selectadapter.notifyDataSetChanged();
                                }

                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "취소되었습니다.", Toast.LENGTH_LONG).show();
                                }
                            });
                            AlertDialog alert = alert_confirm.create();
                            alert.show();
//                        Log.d("third",estimation.getNumber());
                        }
                    }
                });
            }else if(groupPosition == 1) { // 부메뉴
                view = LayoutInflater.from(mContext).inflate(R.layout.normal_child_item, null);
                final Button button = (Button) view.findViewById(R.id.normal_child_item);
                final TextView text = (TextView)view.findViewById(R.id.child_item);
                final EditText num = (EditText)view.findViewById(R.id.ordernum);
                text.setText(list.get(groupPosition).getChildList().get(childPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    //                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        if (selecttable == null) {
                            Toast.makeText(mContext, "테이블을 선택해주세요.", Toast.LENGTH_LONG).show();

                        } else {
                            //button.setBackgroundColor(R.color.selected);
                            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(mContext);
                            alert_confirm.setMessage("메뉴를 추가하시겠습니까?").setCancelable(false).setPositiveButton("추가", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "추가되었습니다.", Toast.LENGTH_LONG).show();
                                    selectadapter.addItem(new ListItem(text.getText().toString(),Integer.parseInt(num.getText().toString()),subprice.get(childPosition)));
                                    selectadapter.notifyDataSetChanged();
                                }

                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "취소되었습니다.", Toast.LENGTH_LONG).show();
                                }
                            });
                            AlertDialog alert = alert_confirm.create();
                            alert.show();
//                        Log.d("third",estimation.getNumber());
                        }
                    }
                });
            }else if(groupPosition == 2) { // 음료
                view = LayoutInflater.from(mContext).inflate(R.layout.normal_child_item, null);
                final Button button = (Button) view.findViewById(R.id.normal_child_item);
                final TextView text = (TextView)view.findViewById(R.id.child_item);
                final EditText num = (EditText)view.findViewById(R.id.ordernum);
                text.setText(list.get(groupPosition).getChildList().get(childPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    //                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        if (selecttable == null) {
                            Toast.makeText(mContext, "테이블을 선택해주세요.", Toast.LENGTH_LONG).show();

                        } else {
                            //button.setBackgroundColor(R.color.selected);
                            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(mContext);
                            alert_confirm.setMessage("메뉴를 추가하시겠습니까?").setCancelable(false).setPositiveButton("추가", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "추가되었습니다.", Toast.LENGTH_LONG).show();

                                    selectadapter.addItem(new ListItem(text.getText().toString(),Integer.parseInt(num.getText().toString()),drinkprice.get(childPosition)));
                                    selectadapter.notifyDataSetChanged();
                                }

                            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "취소되었습니다.", Toast.LENGTH_LONG).show();
                                }
                            });
                            AlertDialog alert = alert_confirm.create();
                            alert.show();
//                        Log.d("third",estimation.getNumber());
                        }
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
