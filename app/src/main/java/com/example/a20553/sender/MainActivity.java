package com.example.a20553.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    final String TAG1 = "Main Activity";

    @BindView(R.id.textView) TextView text;
    @BindView(R.id.button) Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.d(TAG1, "onCreate");
    }

    @OnClick (R.id.button)
    public void goToSettings (){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG1, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG1, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG1, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG1, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG1, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG1, "onDestroy");
    }
}
