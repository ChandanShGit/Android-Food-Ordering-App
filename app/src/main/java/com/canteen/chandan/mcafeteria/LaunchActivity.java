package com.canteen.chandan.mcafeteria;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.canteen.chandan.mcafeteria.Fragments.LoginFragment;
import com.canteen.chandan.mcafeteria.Fragments.RegisterFragment;


public class LaunchActivity extends AppCompatActivity implements LoginFragment.LoginInterface{

    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        doTransaction(new LoginFragment());
    }

    @Override
    public void regClicked() {
        doTransaction(new RegisterFragment());
    }

    private void doTransaction(Fragment frag){
        ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.launch_fl,frag).commit();
    }

}
