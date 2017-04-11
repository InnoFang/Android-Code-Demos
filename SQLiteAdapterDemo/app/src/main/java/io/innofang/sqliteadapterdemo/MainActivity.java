package io.innofang.sqliteadapterdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.innofang.sqliteadapterdemo.adapter.DbAdapter;
import io.innofang.sqliteadapterdemo.bean.Person;
import io.innofang.sqliteadapterdemo.database.DatabaseSchema;
import io.innofang.sqliteadapterdemo.database.DbManager;
import io.innofang.sqliteadapterdemo.database.MySQLiteHelper;

import static io.innofang.sqliteadapterdemo.database.DatabaseSchema.Table.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    private Button mCreateAndInsertButton,
            mQueryAndShowButton;
    private RecyclerView mShowDbRecyclerView;
    private MySQLiteHelper mHelper;
    private List<Person> mPersonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initEvent();
    }

    private void init() {
        mHelper = DbManager.getInstance(this);
        mPersonList = new ArrayList<>();
        mCreateAndInsertButton = (Button) findViewById(R.id.create_and_insert_button);
        mQueryAndShowButton = (Button) findViewById(R.id.query_and_show_button);
        mShowDbRecyclerView = (RecyclerView) findViewById(R.id.show_db_recycler_view);

        mShowDbRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initEvent() {
        mCreateAndInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = mHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from "
                        + TABLE_NAME, null);
                // 数据库显示开启事务
                db.beginTransaction();
                for (int i = 1; i <= 30; i++) {
                    String sql = "insert into " + DatabaseSchema.Table.TABLE_NAME
                            + " values(" + i + ", 'person" + i + "', 20)";
                    db.execSQL(sql);
                }
                // 提交当前事务
                db.setTransactionSuccessful();
                // 关闭事务
                db.close();

                /*if (cursor.getInt(0) == 0) {
                    for (int i = 1; i <= 30; i++) {
                        String sql = "insert into " + DatabaseSchema.Table.TABLE_NAME
                                + " values(" + i
                                + ", 'person" + i + "'"
                                + ", 20)";
                        db.execSQL(sql);
                    }
                } else {
                    showToast("You have created a Database, do not create again!");
                    Log.i(TAG, "You have created a Database, not create again!");
                }*/
                cursor.close();
                db.close();

            }
        });

        mQueryAndShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.db_title_layout).setVisibility(View.VISIBLE);

                Log.i(TAG, "onClick: visible");

                SQLiteDatabase db = mHelper.getWritableDatabase();
                Cursor cursor = db.rawQuery("select * from "
                        + TABLE_NAME, null);
                mPersonList = DbManager.cursorToList(cursor);
                for (Person person : mPersonList) {
                    Log.i(TAG, person.toString());
                }
                DbAdapter adapter = new DbAdapter(MainActivity.this, mPersonList);
                mShowDbRecyclerView.setAdapter(adapter);
                cursor.close();
                db.close();
            }
        });
    }

    private static Toast sToast;
    private long time = 0;

    public void showToast(String content) {
        long temp = System.currentTimeMillis();
        if (time == 0) {
            time = temp;
        }
        if (sToast == null) {
            sToast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
        } else {
            sToast.setText(content);
        }
        if (temp - time < 2000) {
            sToast.setDuration(Toast.LENGTH_LONG);
            time = 0;
        }
        sToast.show();
    }
}
