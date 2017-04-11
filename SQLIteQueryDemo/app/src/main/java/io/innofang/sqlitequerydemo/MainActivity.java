package io.innofang.sqlitequerydemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import io.innofang.sqlitequerydemo.utils.Constant;
import io.innofang.sqlitequerydemo.utils.DbManager;
import io.innofang.sqlitequerydemo.utils.MySQLiteHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button mCreateAndInsertButton,
            mQueryButton,
            mApiQeryButton;
    private MySQLiteHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initView();
        initEvent();
    }

    private void init() {
        mHelper = DbManager.getInstance(this);
    }

    private void initView() {
        mCreateAndInsertButton = (Button) findViewById(R.id.create_and_insert_button);
        mQueryButton = (Button) findViewById(R.id.query_button);
        mApiQeryButton = (Button) findViewById(R.id.api_query_button);
    }

    private void initEvent() {
        mCreateAndInsertButton.setOnClickListener(this);
        mQueryButton.setOnClickListener(this);
        mApiQeryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase db;
        switch (v.getId()) {
            case R.id.create_and_insert_button:
                db = mHelper.getWritableDatabase();
                for (int i = 1; i <= 30; i++) {
                    String sql = "insert into " + Constant.TABLE_NAME
                            + " values(" + i + ", 'person" + i + "', 20)";
                    db.execSQL(sql);
                }
                db.close();
                break;
            case R.id.query_button:
                db = mHelper.getWritableDatabase();
                String selectSql = "select * from " + Constant.TABLE_NAME;
                Cursor cursor = DbManager.selectDataBySql(db, selectSql, null);
                List<Person> list = DbManager.cursorToList(cursor);
                for (Person person : list) {
                    Log.i(TAG, person.toString());
                }
                db.close();
                break;
            case R.id.api_query_button:
                db = mHelper.getWritableDatabase();
                /**
                 * query(String table, String[] columns, String selection,
                 String[] selectionArgs, String groupBy, String having,
                 String orderBy)
                 String table :  表示查询的表名
                 String[] columns 表示查询表中的字段名称 null 查询所有
                 String selection 表示查询条件 where字句
                 String[] selectionArgs 表示查询条件占位符取值
                 String groupBy 表示分组条件 group by 子句
                 String having 表示筛选条件 having 字句
                 String orderBy 表示排序条件 order by 子句 desc 降序
                 */
                cursor = db.query(Constant.TABLE_NAME, // String table
                        null,                          // String[] columns
                        Constant._ID + ">?",           // String selection
                        new String[]{"10"},            // String[] selectionArgs
                        null,                          // String groupBy
                        null,                          // String having
                        Constant._ID + " desc");       // String orderBy
                list = DbManager.cursorToList(cursor);
                for (Person person : list) {
                    Log.i(TAG, person.toString());
                }
                db.close();
                break;
        }
    }
}
