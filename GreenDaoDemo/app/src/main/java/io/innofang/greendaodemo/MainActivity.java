package io.innofang.greendaodemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

import io.innofang.greendaodemo.dao.DaoSession;
import io.innofang.greendaodemo.dao.User;
import io.innofang.greendaodemo.dao.UserDao;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mAgeEditText;
    private Button mAddButton;
    private ListView mListView;

    private List<User> data = new ArrayList<>();
    private Query<User> mUserQuery;
    private UserDao mUserDao;
    private ArrayAdapter<User> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mAgeEditText = (EditText) findViewById(R.id.age_edit_text);
        mAddButton = (Button) findViewById(R.id.add_button);
        mListView = (ListView) findViewById(R.id.list_view);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        mUserDao = daoSession.getUserDao();
        mUserQuery = mUserDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();

        data.addAll(mUserQuery.list());
        mAdapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_2, data) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                User user = getItem(position);
                View view = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
                ((TextView) view.findViewById(android.R.id.text1)).setText(user.getName());
                ((TextView) view.findViewById(android.R.id.text2)).setText(user.getAge() + "");
                return view;
            }
        };
        mListView.setAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                User user = data.get(position);
                data.remove(user);
                mUserDao.delete(user);
                mAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }


    public void onClick(View view) {
        String name = mNameEditText.getText().toString();
        String age = mAgeEditText.getText().toString();
        User user = new User(null, name, Integer.parseInt(age));
        data.add(user);
        mUserDao.insert(user);
        mAdapter.notifyDataSetChanged();
    }
}
