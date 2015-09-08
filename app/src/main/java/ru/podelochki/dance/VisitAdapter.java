package ru.podelochki.dance;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy.Sivolovskiy on 9/3/2015.
 */
public class VisitAdapter extends BaseAdapter {


    private final List<VisitItem> mItems=new ArrayList<>();

    private final Context mContext;

    public VisitAdapter(Context context) {

        mContext = context;

    }

    public void add(VisitItem item)
    {
        mItems.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public Object getItem(int pos) {

        return mItems.get(pos);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final VisitItem visitItem=(VisitItem) getItem(position);
        RelativeLayout itemLayout=(RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.visit_item, null);

        final TextView dateView=(TextView) itemLayout.findViewById(R.id.textView7);
        dateView.setText(visitItem.getDate());
        dateView.setBackgroundColor(visitItem.getColor());

        dateView.setHeight(visitItem.getCellHeight());

        return itemLayout;

    }

}
