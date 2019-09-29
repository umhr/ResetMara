package com.example.resetmara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_tap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _x = ((EditText)findViewById(R.id.editText_x)).getText().toString();
                String _y = ((EditText)findViewById(R.id.editText_y)).getText().toString();

                new AsyncInputConnect().execute("screen 0 " + _x + " " + _y, "screen 1 " + _x + " " + _y);
            }
        });

        findViewById(R.id.button_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncInputConnect().execute("exit");
            }
        });
    }

}
