package com.ncd.greendaotest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ncd.greendaotest.Utils.UserUtils;
import com.ncd.greendaotest.entity.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;

    private TextView content_tv;
    private EditText id_et, name_et;
    private Button insert_btn, delete_btn, query_btn;

    private StringBuilder mStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initUI();
        mStringBuilder = new StringBuilder();
    }

    private void initUI() {
        content_tv = (TextView) findViewById(R.id.content_tv);
        id_et = (EditText) findViewById(R.id.id_et);
        name_et = (EditText) findViewById(R.id.name_et);
        insert_btn = (Button) findViewById(R.id.insert_btn);
        delete_btn = (Button) findViewById(R.id.delete_btn);
        query_btn = (Button) findViewById(R.id.query_btn);

        insert_btn.setOnClickListener(this);
        delete_btn.setOnClickListener(this);
        query_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert_btn:
                insert();
                break;
            case R.id.delete_btn:
                delete();
                break;
            case R.id.query_btn:
                query();
                break;
        }
    }

    private void insert() {
        String name = name_et.getText().toString();
        String id = id_et.getText().toString();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(mContext,"name is null",Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User();
        if(TextUtils.isEmpty(id)){
            Toast.makeText(mContext,"id is null",Toast.LENGTH_SHORT).show();
            //return;
        }else{
            user.setId(Long.valueOf(id));
        }
        user.setName(name);
        UserUtils.getInstance().insert(user);
        mStringBuilder.append("insert(): name="+name+"    id="+id+"\n\n");
        updateContent();
    }

    private void delete() {
        String id = id_et.getText().toString();
        if(TextUtils.isEmpty(id)){
            Toast.makeText(mContext,"id is null",Toast.LENGTH_SHORT).show();
            return;
        }
        UserUtils.getInstance().deleteByUserId(id);
        mStringBuilder.append("delete(): id="+id+"\n\n");
        updateContent();
    }

    private void query() {
        List<User> list = UserUtils.getInstance().query();
        if(null != list) {
            mStringBuilder.append("query(): "+list.toString()+"\n\n");
            updateContent();
        }
    }

    private void updateContent() {
        content_tv.setText(mStringBuilder);
    }
}
