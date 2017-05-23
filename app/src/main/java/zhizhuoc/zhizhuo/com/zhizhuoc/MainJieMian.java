package zhizhuoc.zhizhuo.com.zhizhuoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 52829 on 2017/5/24.
 */

public class MainJieMian extends AppCompatActivity {
    private MainUi mainUi;
    private LeftMenu leftMenu;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainUi=new MainUi(this);
        setContentView(mainUi);
        leftMenu =new LeftMenu();
    }
}
