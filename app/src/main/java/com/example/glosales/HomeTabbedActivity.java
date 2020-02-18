package com.example.glosales;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.glosales.Adapters.HomeTabsAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeTabbedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tabbed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Stock"));
        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Expenses"));
        tabLayout.addTab(tabLayout.newTab().setText("MY PRODUCTS"));


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        HomeTabsAdapter tabsAdapter = new HomeTabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
 /*   public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menus,menu);
        return true;
    }*/
   /* public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.Profile:
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.helpline:
                Toast.makeText(this, "Feature coming soon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:

                break;
             default:
                 return super.onOptionsItemSelected(item);


        }


        return true;

    }*/
}
