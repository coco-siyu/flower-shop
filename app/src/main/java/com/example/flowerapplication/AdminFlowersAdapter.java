package com.example.flowerapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;



public class AdminFlowersAdapter extends BaseAdapter {

    private final List<FlowersData> mFlowersData;
    private final LayoutInflater inflater;

    public AdminFlowersAdapter(List<FlowersData> mFlowersData, Context context) {
        this.mFlowersData = mFlowersData;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mFlowersData == null ? 0 : mFlowersData.size();
    }

    @Override
    public Object getItem(int position) {
        return mFlowersData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.add_flowers, null);
        FlowersData mFlowersData = (FlowersData) getItem(position);

        TextView tv_name = (TextView) view.findViewById(R.id.tv_ad_name);
        TextView tv_kind = (TextView) view.findViewById(R.id.tv_ad_kind);
        TextView tv_price = (TextView) view.findViewById(R.id.tv_ad_price);
        TextView tv_address = (TextView) view.findViewById(R.id.tv_ad_address);


        tv_name.setText("Name：" + mFlowersData.getName());
        tv_kind.setText("Type：" + mFlowersData.getKind());
        tv_price.setText("Price：$" + mFlowersData.getPrice() );
        tv_address.setText("Origin：" + mFlowersData.getAddress());

        return view;
    }
}
