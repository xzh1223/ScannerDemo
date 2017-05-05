package com.xzh.scannerdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhenghangxia on 17-5-5.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mContent;
    private Button mBtnScanner;
    private ImageView mImageView;
    private Button mBtnGenerate;
    private EditText mInputET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    private void initUI() {

        mInputET = (EditText) findViewById(R.id.input);

        mContent = (TextView) findViewById(R.id.content);

        mImageView = (ImageView) findViewById(R.id.imageView);

        mBtnGenerate = (Button) findViewById(R.id.btn_generate);
        mBtnScanner = (Button) findViewById(R.id.btn_scanner);

        mBtnGenerate.setOnClickListener(this);
        mBtnScanner.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_scanner:
                startActivityForResult(new Intent(this, ScannerActivity.class), 1);
                break;

            case R.id.btn_generate:
                String contentString = mInputET.getText().toString().trim();
                if (contentString == null) {
                    Toast.makeText(MainActivity.this,"请先输入内容...",Toast.LENGTH_SHORT).show();
                }else {
                    Bitmap bitmap = Util.encodeAsBitmap(contentString);
                    mImageView.setImageBitmap(bitmap);
                    mContent.setText("successful");
                }
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            mContent.setText(data.getStringExtra("text")); // 显示识别到的文字
        }
    }
}
