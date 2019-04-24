package jsdemo.wzx.com.jsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button home_button1;
    private Button home_button2;
    private Button home_button3;
    private Button home_button4;
    private Button home_button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        home_button1 = (Button) findViewById(R.id.home_button1);
        home_button2 = (Button) findViewById(R.id.home_button2);
        home_button3 = (Button) findViewById(R.id.home_button3);
        home_button4 = (Button) findViewById(R.id.home_button4);

        home_button1.setOnClickListener(this);
        home_button2.setOnClickListener(this);
        home_button3.setOnClickListener(this);
        home_button4.setOnClickListener(this);
        home_button5 = (Button) findViewById(R.id.home_button5);
        home_button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_button1:
                startActivity(MainActivity.class);
                break;
            case R.id.home_button2:
                startActivity(Main2Activity.class);

                break;
            case R.id.home_button3:
                startActivity(Main3Activity.class);

                break;
            case R.id.home_button4:
                startActivity(Main4Activity.class);

                break;
            default:
            case R.id.home_button5:
                startActivity(Main5Activity.class);
                break;
        }
    }

    private void startActivity(Class mClass) {
        startActivity(new Intent(this, mClass));
    }
}
