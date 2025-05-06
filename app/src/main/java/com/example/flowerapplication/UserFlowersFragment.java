package com.example.flowerapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.flowerapplication.FlowersDetailActivity;

import java.util.ArrayList;
import java.util.List;


public class UserFlowersFragment extends Fragment {

    private MyDatabaseHelper dbHelper;
    private ListView mLvAdFlowers;
    private UserFlowersAdapter adapter;
    private List<FlowersData> mListItems;
    private String name, price, user_name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_flowers, container, false);
    }

    @SuppressLint("Range")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new MyDatabaseHelper(getActivity(), "flowersStore.db", null, 1);
        dbHelper.getWritableDatabase();

        Bundle bundle = getArguments();
        assert bundle != null;
        user_name = (String) bundle.getString("mine_user");

        mListItems = new ArrayList<>();
        adapter = new UserFlowersAdapter(mListItems, getActivity());

        mLvAdFlowers = (ListView) getActivity().findViewById(R.id.lv_user_flowers);

        String selectQuery = "SELECT*FROM flowers ";
        SQLiteDatabase db1 = dbHelper.getReadableDatabase();
        Cursor cursor = db1.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FlowersData mFlowersData = new FlowersData();
                name = cursor.getString(cursor.getColumnIndex("name"));
                price = cursor.getString(cursor.getColumnIndex("price"));

                mFlowersData.name = name;
                mFlowersData.price = price;
                mListItems.add(mFlowersData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mLvAdFlowers.setAdapter(adapter);

        mLvAdFlowers.setOnItemClickListener((parent, view, position, id) -> {
            FlowersData flowersData = mListItems.get(position);
            Log.i("log", flowersData.getName());
            Intent intent = new Intent(getActivity(), FlowersDetailActivity.class);
            intent.putExtra("name", flowersData.getName());
            intent.putExtra("user_name", user_name);
            startActivity(intent);
        });
    }

}
