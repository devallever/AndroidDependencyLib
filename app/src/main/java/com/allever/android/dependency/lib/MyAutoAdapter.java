package com.allever.android.dependency.lib;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.allever.lib.ui.widget.AutoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAutoAdapter extends AutoAdapter {

    private List<String> mList = new ArrayList<>();

    private Context mContext;

    public MyAutoAdapter(Context context){
        mContext = context;
    }

    public void setData(ArrayList<String> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position) {
        View view = View.inflate(mContext, R.layout.item_tag, null);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(mList.get(position));
        return view;
    }
}
