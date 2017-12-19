package pos.softwareengineering;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button paybtn,managerbtn;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerbtn = (Button)findViewById(R.id.managerbtn);
        paybtn = (Button)findViewById(R.id.paybtn);
        mContext = this;
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
                Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                startActivityForResult(intent,101);
            }
        });
    }
    public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
        public ArrayList<Item> list;

        public ExpandableListViewAdapter(ArrayList<Item> list) {
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
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = null;

            if(groupPosition == 0) { // 이름
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setName(editText.getText().toString());
//                        Log.d("third",estimation.getNumber());
                    }
                });
            }else if(groupPosition == 1) { // 공간유형
                view = LayoutInflater.from(mContext).inflate(R.layout.normal_child_item, null);
                final Button button = (Button) view.findViewById(R.id.normal_child_item);

                button.setText(list.get(groupPosition).getChildList().get(childPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    //                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View v) {
                        info.setPurpose(button.getText().toString());
                        button.setBackgroundColor(R.color.selected);
//                        Log.d("third",estimation.getNumber());
                    }
                });
            }else if(groupPosition == 2) { // 소개
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setIntroduce(editText.getText().toString());
//                        Log.d("third",estimation.getNumber());
                    }
                });
            } else if(groupPosition == 3) { // 주소
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setAddress(editText.getText().toString());
//                        Log.d("fourth",estimation.getDate());
                    }
                });
            }else if(groupPosition == 4) { // 이용가능시간
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setTime(editText.getText().toString());
//                        Log.d("fourth",estimation.getDate());
                    }
                });
            }else if(groupPosition == 5) { // 시간당가격
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setPrice(editText.getText().toString());
//                        Log.d("five",estimation.getDate());
                    }
                });
            }else if(groupPosition == 6) { // 내부시설
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setFacilities(editText.getText().toString());
//                        Log.d("five",estimation.getDate());
                    }
                });
            }else if(groupPosition == 7) { // 주변시설
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setAround(editText.getText().toString());
//                        Log.d("five",estimation.getDate());
                    }
                });
            }else if(groupPosition == 8) { // 주의사항
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setNotice(editText.getText().toString());
//                        Log.d("five",estimation.getDate());
                    }
                });
            }else if(groupPosition == 9) { // 기타
                view = LayoutInflater.from(mContext).inflate(R.layout.number_child_item, null);
                final EditText editText = (EditText) view.findViewById(R.id.number_child_item);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);

                final Button button = (Button) view.findViewById(R.id.number_child_bt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        info.setEtc(editText.getText().toString());
//                        Log.d("five",estimation.getDate());
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
