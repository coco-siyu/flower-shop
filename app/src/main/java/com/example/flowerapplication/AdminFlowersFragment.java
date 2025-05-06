package com.example.flowerapplication;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AdminFlowersFragment extends Fragment implements View.OnClickListener {

    private MyDatabaseHelper dbHelper;
    private EditText mEdName, mEdKind, mEdPrice, mEdAddress;
    private Button mBtAdd, mBtUpdate, mBtRetrieve, mBtDelete, mBtClear;
    private String mStrName, mStrKind, mStrPrice, mStrAddress;
    private ListView mLvAdFlowers;

    private AdminFlowersAdapter adapter;
    private List<FlowersData> mListItems;
    int amount = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_admin_add_flower, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dbHelper = new MyDatabaseHelper(getActivity(), "flowersStore.db", null, 1);
        dbHelper.getWritableDatabase();

        mListItems = new ArrayList<>();
        adapter = new AdminFlowersAdapter(mListItems, getActivity());

        mEdName = (EditText) getActivity().findViewById(R.id.et_ad_name);
        mEdKind = (EditText) getActivity().findViewById(R.id.et_ad_kind);
        mEdPrice = (EditText) getActivity().findViewById(R.id.et_ad_price);
        mEdAddress = (EditText) getActivity().findViewById(R.id.et_ad_address);

        mBtAdd = (Button) getActivity().findViewById(R.id.bt_ad_add);
        mBtUpdate = (Button) getActivity().findViewById(R.id.bt_ad_update);
        mBtRetrieve = (Button) getActivity().findViewById(R.id.bt_ad_retrieve);
        mBtDelete = (Button) getActivity().findViewById(R.id.bt_ad_delete);
        mBtClear = (Button) getActivity().findViewById(R.id.bt_ad_clear);

        mBtAdd.setOnClickListener(this);
        mBtUpdate.setOnClickListener(this);
        mBtRetrieve.setOnClickListener(this);
        mBtDelete.setOnClickListener(this);
        mBtClear.setOnClickListener(this);

        mLvAdFlowers = (ListView) getActivity().findViewById(R.id.lv_ad_flowers_info);

    }

    @SuppressLint("Range")
    @Override
    public void onClick(View view) {
        mStrName = mEdName.getText().toString();
        mStrKind = mEdKind.getText().toString();
        mStrPrice = mEdPrice.getText().toString();
        mStrAddress = mEdAddress.getText().toString();
        switch (view.getId()) {


            case R.id.bt_ad_add:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                Cursor c = db.rawQuery("select * from flowers", null);
                amount = c.getCount();
                if (amount == 0) {
                    Log.i("add data", "flower table");

                    addItem("Lily", "Lily", "VA", "8");
                    addItem("Rose", "Rosaceae", "PA", "10");
                    addItem("Carnation", "Pink", "Japan", "5");
                }
                //</editor-fold>
                values.put("name", mStrName);
                values.put("kind", mStrKind);
                values.put("price", mStrPrice);
                values.put("address", mStrAddress);

                if (mStrName.equals("") || mStrKind.equals("") || mStrPrice.equals("") || mStrAddress.equals("")) {
                    Toast.makeText(getActivity(), "insert failed", Toast.LENGTH_SHORT).show();
                } else {
                    db.insert("flowers", null, values);
                    values.clear();
                    Toast.makeText(getActivity(), mStrName + "insert success", Toast.LENGTH_SHORT).show();
                    mEdName.setText("");
                    mEdKind.setText("");
                    mEdPrice.setText("");
                    mEdAddress.setText("");

                }

                break;

            case R.id.bt_ad_update:
                if (!TextUtils.isEmpty(mStrName)) {
                    db = dbHelper.getWritableDatabase();
                    values = new ContentValues();
                    mStrName = mEdName.getText().toString();
                    mStrKind = mEdKind.getText().toString();
                    mStrPrice = mEdPrice.getText().toString();
                    mStrAddress = mEdAddress.getText().toString();
                    values.put("kind", mStrKind);
                    values.put("price", mStrPrice);
                    values.put("address", mStrAddress);

                    db.update("flowers", values, "name=?", new String[]{mStrName});
                    Toast.makeText(getActivity(), "Update" + mStrName + "Success", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Enter the flower to update", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bt_ad_delete:
                if (!TextUtils.isEmpty(mStrName)) {
                    db = dbHelper.getWritableDatabase();
                    mStrName = mEdName.getText().toString();
                    db.delete("flowers", "name=?", new String[]{mStrName});
                    Toast.makeText(getActivity(), "Delete" + mStrName + "success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Enter flower to be delete", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bt_ad_retrieve:
                String name, kind, price, address;
                String key = mEdName.getText().toString();
                String selectQuery = "SELECT*FROM flowers where name like '%" + key + "%' ";
                SQLiteDatabase db1 = dbHelper.getReadableDatabase();
                Cursor cursor = db1.rawQuery(selectQuery, null);
                if (cursor.moveToFirst()) {
                    do {
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        kind = cursor.getString(cursor.getColumnIndex("kind"));
                        price = cursor.getString(cursor.getColumnIndex("price"));
                        address = cursor.getString(cursor.getColumnIndex("address"));

                        FlowersData mFlowersData = new FlowersData();
                        mFlowersData.name = name;
                        mFlowersData.kind = kind;
                        mFlowersData.price = price;
                        mFlowersData.address = address;

                        mListItems.add(mFlowersData);
                    } while (cursor.moveToNext());
                } else {
                    Toast.makeText(getActivity(), "No found of this flower", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
                mLvAdFlowers.setAdapter(adapter);
                break;

            case R.id.bt_ad_clear:
                if (adapter == null) {
                    Toast.makeText(getActivity(), "No info to be cleared", Toast.LENGTH_SHORT).show();
                } else {
                    mLvAdFlowers.setAdapter(adapter);
                    mListItems.clear();
                    adapter.notifyDataSetChanged();

                    mEdName.setText("");
                    mEdKind.setText("");
                    mEdPrice.setText("");
                    mEdAddress.setText("");

                }
                break;
            //</editor-fold>
        }
    }



    public void addItem(String name, String kind, String address, String price) {


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("kind", kind);
        values.put("price", price);
        values.put("address", address);
        db.insert("flowers", null, values);
        Log.i("flowers", "Added Success");
        values.clear();
    }
}
