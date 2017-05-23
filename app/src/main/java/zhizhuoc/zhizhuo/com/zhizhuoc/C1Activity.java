package zhizhuoc.zhizhuo.com.zhizhuoc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 52829 on 2017/5/23.
 */

public class C1Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ArrayList<String> meunLists;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle  mDrawerToggle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c1);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList=(ListView)findViewById(R.id.left_drawer);
        meunLists= new ArrayList<String>();
        for(int i=1;i<5;i++)
            meunLists.add("执着"+i);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,meunLists);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        mDrawerToggle= new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(C1Activity.this, "抽屉打开", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Toast.makeText(C1Activity.this, "抽屉关闭", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment contentFragment=new ContemnFragment();
            Bundle args=new Bundle();
            args.putString("text",meunLists.get(position));
            contentFragment.setArguments(args);

            FragmentManager fm=getFragmentManager();
            fm.beginTransaction().replace(R.id.content_frame,contentFragment).commit();
            mDrawerLayout.closeDrawer(mDrawerList);
    }
}
