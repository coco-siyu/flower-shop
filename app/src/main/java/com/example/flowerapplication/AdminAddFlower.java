package com.example.flowerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class AdminAddFlower extends AppCompatActivity {
    private Bitmap bitmap, loadbitmap;
    private Bitmap loadBitmap = null;
    private MyDatabaseHelper dbHelper;
    private EditText mEdName, mEdKind, mEdPrice, mEdAddress;
    private Button mBtAdd, mBtUpdate, mBtRetrieve, mBtDelete, mBtClear;
    private String mStrName, mStrKind, mStrPrice, mStrAddress;
    private ListView mLvAdFlowers;
    private TextView mTvSelect;
    private AdminFlowersAdapter adapter;
    private ImageView imageView;
    private List<FlowersData> mListItems;
    int amount = 0;
    byte[] bytePic;
    private Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_flower);
    }
}