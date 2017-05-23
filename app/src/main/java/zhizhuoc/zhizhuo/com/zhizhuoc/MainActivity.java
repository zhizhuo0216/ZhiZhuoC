package zhizhuoc.zhizhuo.com.zhizhuoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnC1,btnC2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnC1=(Button)findViewById(R.id.btnC1);
        btnC1.setOnClickListener(new BtnC1());
        btnC2=(Button)findViewById(R.id.btnC2);
        btnC2.setOnClickListener(new BtnC2());
    }
    public class BtnC1 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this, C1Activity.class);
            startActivity(intent);
        }
    }
    public class BtnC2 implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(MainActivity.this, MainJieMian.class);
            startActivity(intent);
        }
    }
}