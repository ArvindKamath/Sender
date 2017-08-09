package com.example.a20553.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";

    @BindView(R.id.loggerText) TextView text;
    @BindView(R.id.AddFlow) Button button;
    @BindView(R.id.sendLocation) Button sendLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupActivityView();
    }

    @OnClick (R.id.AddFlow)
    public void addFlow (){
        Intent intent = new Intent(this, AddFlowActivity.class);
        startActivity(intent);
    }

    @OnClick (R.id.sendLocation)
    public void sendLocation() {
        Intent intent = new Intent(this, GpsActivity.class);
        startActivity(intent);
    }

    public void setupActivityView () {
        FlowDb flowDb = new FlowDb(this.getApplicationContext());
        int numberOfFlows = flowDb.getNumberOfFlows();

        text.setText("Number of Activities: " + numberOfFlows);
    }

    private void setupButton(int num) {
        for (int i = 0; i < num; i++) {
            Button myButton = new Button(this);
            myButton.setText("Button :" + i);
            myButton.setId(i);
            final int id_ = myButton.getId();

            LinearLayout layout = (LinearLayout) findViewById(R.id.myDynamicLayout);
            layout.addView(myButton);

            myButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    text.setText("Touched this button");
                }
            });
        }
    }
}