package com.example.a20553.sender;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.record_add) Button add;
    @BindView(R.id.record_cancel) Button cancel;
    @BindView(R.id.record_name) TextView name;
    @BindView(R.id.record_email) TextView email;
    @BindView(R.id.record_phone) TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.record_add)
    public void onAdd() {
        Record record = new Record();
        name.setText(record.name);
        email.setText(record.email);
        phone.setText(record.phone);
    }

    @OnClick(R.id.record_cancel)
    public void onCancel() {
        finish();
    }
}
