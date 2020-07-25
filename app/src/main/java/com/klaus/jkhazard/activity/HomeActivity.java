package com.klaus.jkhazard.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.klaus.jkhazard.R;
import com.klaus.jkhazard.common.UIListener;
import com.klaus.jkhazard.fragment.HomeFragment;
import com.klaus.jkhazard.fragment.JoinRoomFragment;
import com.klaus.jkhazard.fragment.SetupRoomFragment;
import com.klaus.jkhazard.fragment.WaitRoomFragment;
import com.klaus.jkhazard.model.ApiMock;
import com.klaus.jkhazard.model.Player;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements UIListener, HomeFragment.InitialSetupHomeListener, WaitRoomFragment.PlayersListener {

    Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ApiMock.getInstance();

        initFragment();

    }

    private void initFragment() {
        FragmentManager fm = getSupportFragmentManager();
        mCurrentFragment = fm.findFragmentById(R.id.home_content_panel);

        if (mCurrentFragment == null) {
            mCurrentFragment = HomeFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.home_content_panel, mCurrentFragment)
                    .commit();
        }
    }

    public void loadFragment(Fragment fragment) {
        mCurrentFragment = fragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.home_content_panel, mCurrentFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.home_content_panel);
    }

    @Override
    public void onDoneClicked() {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

    @Override
    public void onNextClicked() {
        if (mCurrentFragment instanceof SetupRoomFragment) {
            loadFragment(WaitRoomFragment.newInstance(true));
        } else if (mCurrentFragment instanceof JoinRoomFragment) {
            loadFragment(WaitRoomFragment.newInstance(false));
        }
    }

    @Override
    public void goToCreateRoom() {
        loadFragment(SetupRoomFragment.newInstance());
    }

    @Override
    public void goToJoinRoom() {
        loadFragment(JoinRoomFragment.newInstance());
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return ApiMock.getInstance().getPlayerList();
    }
}
