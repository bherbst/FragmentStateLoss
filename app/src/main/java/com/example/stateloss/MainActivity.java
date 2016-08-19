package com.example.stateloss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean allowStateLoss;
    private boolean changeOnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new BlueFragment())
                    .commit();
        }

        Button disallowStateLossButton = (Button) findViewById(R.id.disallow_state_loss);
        Button allowStateLossButton = (Button) findViewById(R.id.allow_state_loss);
        Button noStateLossButton = (Button) findViewById(R.id.no_state_loss);

        disallowStateLossButton.setOnClickListener(view -> {
            allowStateLoss = false;
            changeOnStop = true;
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });

        allowStateLossButton.setOnClickListener(view -> {
            allowStateLoss = true;
            changeOnStop = true;
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });

        noStateLossButton.setOnClickListener(view -> {
            changeOnStop = false;

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new RedFragment());

            if (allowStateLoss) {
                transaction.commitAllowingStateLoss();
            } else {
                transaction.commit();
            }

            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (changeOnStop) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new RedFragment());

            if (allowStateLoss) {
                transaction.commitAllowingStateLoss();
            } else {
                transaction.commit();
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
