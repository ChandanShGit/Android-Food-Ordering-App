package com.canteen.chandan.mcafeteria;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.canteen.chandan.mcafeteria.Fragments.FavoriteFragment;
import com.canteen.chandan.mcafeteria.Fragments.NotificationFragment;
import com.canteen.chandan.mcafeteria.Fragments.TabbedMainFragment;
import com.canteen.chandan.mcafeteria.Fragments.WalletFragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment homeFragment;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FavoriteFragment favoriteFragment;
    private WalletFragment walletFragment;
    private NotificationFragment notificationFragment;
    private BottomNavigationView mbottomNav;
    private Toolbar toolbar;
    private ProgressDialog mProg;
    private NavigationView navView;
    private PreConfig preConfig;
    private CircleImageView profile_header;
    private TextView header_name;
    private TextView header_college;
    private TextView header_cardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preConfig=new PreConfig(this);
        initialise();
        startFragment();

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!preConfig.readLoginStatus()){
            Intent intent=new Intent(MainActivity.this,LaunchActivity.class);
            startActivity(intent);
            finish();
        }

        header_name.setText(preConfig.readName());
        header_cardId.setText(preConfig.readCardId());

    }


    private void initialise() {
        mbottomNav = findViewById(R.id.main_navigation);
        // mainFrame = findViewById(R.id.main_frame);
        toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProg=new ProgressDialog(this);
        //Drawer Toggle button and its Listener
        drawerLayout = findViewById(R.id.mDL);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navView=findViewById(R.id.main_slidenav);
        navView.setNavigationItemSelectedListener(this);
        View headerView =navView.getHeaderView(0);
        header_name=headerView.findViewById(R.id.user_name_header);
        header_cardId=headerView.findViewById(R.id.card_id_header);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }



    private void startFragment() {
        favoriteFragment = new FavoriteFragment();
        homeFragment=new TabbedMainFragment();
        walletFragment = new WalletFragment();
        notificationFragment = new NotificationFragment();
        doTransaction(homeFragment);

        mbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_rmenu:
                        homeFragment=new TabbedMainFragment();
                        doTransaction(homeFragment);
                        return true;
                    case R.id.bottom_notification:
                        doTransaction(notificationFragment);
                        return true;
                    case R.id.bottom_fav:
                        doTransaction(favoriteFragment);
                        return true;
                    case R.id.bottom_orders:
                        doTransaction(walletFragment);
                        return true;
                    default:
                        return false;

                }

            }
        });



    }


    private void doTransaction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();

    }


    //Handle Slide Navigation Item Listener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.drawer_logout) {
            mProg.setTitle("Logout Request.");
            mProg.setMessage("Processing Your Request.");
            mProg.show();
            preConfig.writeLoginStatus(false);
            mProg.dismiss();
            this.onStart();
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
