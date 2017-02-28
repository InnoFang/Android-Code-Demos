package io.innofang.sqliteadapterdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.innofang.sqliteadapterdemo.R;
import io.innofang.sqliteadapterdemo.bean.Person;

/**
 * Author: Inno Fang
 * Time: 2017/1/16 14:56
 * Description:
 */

public class DbAdapter extends RecyclerView.Adapter<DbAdapter.DbHolder>{

    private List<Person> mPersonList;
    private Context mContext;
    public DbAdapter(Context context, List<Person> personList) {
        mContext = context;
        mPersonList = personList;
    }

    @Override
    public DbHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.db_item, parent, false);
        return new DbHolder(view);
    }

    @Override
    public void onBindViewHolder(DbHolder holder, int position) {
        Person person = mPersonList.get(position);
        holder.bindDbItem(person);
    }

    @Override
    public int getItemCount() {
        return mPersonList == null ? 0 : mPersonList.size();
    }

    public class DbHolder extends RecyclerView.ViewHolder{

        private TextView mId, mName, mAge;

        public DbHolder(View itemView) {
            super(itemView);
            mId = (TextView) itemView.findViewById(R.id.id_text_view);
            mName = (TextView) itemView.findViewById(R.id.name_text_view);
            mAge = (TextView) itemView.findViewById(R.id.age_text_view);
        }

        public void bindDbItem(Person person){
            mId.setText(String.valueOf(person.getId()));
            mName.setText(person.getName());
            mAge.setText(String.valueOf(person.getAge()));
        }
    }
}

