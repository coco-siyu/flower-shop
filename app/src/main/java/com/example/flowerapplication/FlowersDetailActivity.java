package com.example.flowerapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowersDetailActivity extends BaseActivity implements View.OnClickListener {

    private MyDatabaseHelper dbHelper, commentHelper;
    private TextView mTvName, mTvKind, mTvAddress, mTvPrice, mTvNum, mTvTotal, mTvBuy;
    private TextView mTvCommentUser, mTvCommentName, mTvCommentContent, mTvCommentSend;
    private ImageView mIvBack, mIvDetail, mIvSub, mIvAdd, mIvShare;
    private String mStrName, mStrNum, mStrTotal;
    private String mStrCommentUser, mStrCommentName, mStrCommentContent;
    private String mSqlName, mSqlKind, mSqlAddress, mSqlPrice;
//    private String mSqlCommentUser, mSqlCommentName, mSqlCommentContent;
    private String user_name;
    private byte[] detailPic;
    private int mIntTotal, mIntPrice, mIntNum;

    private ListView mLvComment;
    private EditText mEdComment;
    private SQLiteDatabase  db1;
    private ProgressBar wait;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers_detail);

        mStrName = getIntent().getStringExtra("name");
        user_name = getIntent().getStringExtra("user_name");

        mEdComment = (EditText) findViewById(R.id.et_comment);
        wait = (ProgressBar) findViewById(R.id.loading_process_dialog_progressBar);

        mTvName = (TextView) findViewById(R.id.tv_detail_name);
        mTvKind = (TextView) findViewById(R.id.tv_detail_kind);
        mTvAddress = (TextView) findViewById(R.id.tv_detail_address);
        mTvPrice = (TextView) findViewById(R.id.tv_detail_price);
        mTvNum = (TextView) findViewById(R.id.tv_detail_num);
        mTvTotal = (TextView) findViewById(R.id.tv_total_num);
        mTvBuy = (TextView) findViewById(R.id.tv_buy);
        mTvCommentSend = (TextView) findViewById(R.id.tv_comment_send);

        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvShare = (ImageView) findViewById(R.id.iv_share);
        mIvSub = (ImageView) findViewById(R.id.iv_num_sub);
        mIvAdd = (ImageView) findViewById(R.id.iv_add);

        mIvBack.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
        mIvSub.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mTvBuy.setOnClickListener(this);
        mTvCommentSend.setOnClickListener(this);

        dbHelper = new MyDatabaseHelper(this, "flowersStore.db", null, 1);

        mLvComment = (ListView) findViewById(R.id.lv_comment_list);

        String selectQuery = "SELECT*FROM flowers where name like '%" + mStrName + "%'";
        db1 = dbHelper.getReadableDatabase();
        Cursor cursor = db1.rawQuery(selectQuery, null);
        CursorWindowUtil.cw(cursor);
        if (cursor.moveToFirst()) {
            do {
                mSqlName = cursor.getString(cursor.getColumnIndex("name"));
                mSqlKind = cursor.getString(cursor.getColumnIndex("kind"));
                mSqlAddress = cursor.getString(cursor.getColumnIndex("address"));
                mSqlPrice = cursor.getString(cursor.getColumnIndex("price"));
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (mStrName.equals(mSqlName)) {
            mTvName.setText(mSqlName);
            mTvKind.setText(mSqlKind);
            mTvAddress.setText(mSqlAddress);
            mTvPrice.setText("$" + mSqlPrice);

            mStrNum = mTvNum.getText().toString();
            mStrTotal = mTvTotal.getText().toString();


            mIntNum = Integer.parseInt(mTvNum.getText().toString());
            mIntPrice = Integer.parseInt(mSqlPrice);
            mIntTotal = mIntNum * mIntPrice;
            mTvTotal.setText("$" + mIntTotal);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_num_sub:
                int numSub = Integer.parseInt(mTvNum.getText().toString());
                if (numSub <= 1) {
                    Toast.makeText(this, "No less than 1", Toast.LENGTH_SHORT).show();
                } else {
                    mIntNum = numSub - 1;
                    String mSub = String.valueOf(mIntNum);
                    mTvNum.setText(mSub);
                    mIntTotal = mIntNum * mIntPrice;
                    mTvTotal.setText("$" + mIntTotal);
                    mStrNum = mTvNum.getText().toString();
                    mStrTotal = mTvTotal.getText().toString();
                }
                break;
            case R.id.iv_add:
                int numAdd = Integer.parseInt(mTvNum.getText().toString());
                mIntNum = numAdd + 1;
                String mAdd = String.valueOf(mIntNum);
                mTvNum.setText(mAdd);
                mIntTotal = mIntNum * mIntPrice;
                mTvTotal.setText("$" + mIntTotal);
                mStrNum = mTvNum.getText().toString();
                mStrTotal = mTvTotal.getText().toString();
                break;


        }
    }



}