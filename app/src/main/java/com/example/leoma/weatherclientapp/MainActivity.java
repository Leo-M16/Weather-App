package com.example.leoma.weatherclientapp;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.leoma.weatherclientapp.Fragments.FourDaysFragment;
import com.example.leoma.weatherclientapp.Fragments.LocationFragment;
import com.example.leoma.weatherclientapp.Fragments.NowFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener, NowFragment.OnFragmentInteractionListener,
        FourDaysFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {

           onFragmentInteraction(Uri.parse(""));

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        LocationFragment LctnFragment=new LocationFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, LctnFragment).addToBackStack(null).commit();


    }

    @Override
    public void onNowFragmentInteraction(String city) {
        NowFragment NwFragment=new NowFragment();
        NwFragment.setCity(city);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, NwFragment).addToBackStack(null).commit();

    }

    @Override
    public void onFourDaysFragmentInteraction(String city) {
        FourDaysFragment FDaysFragments=new FourDaysFragment();
        FDaysFragments.setCity(city);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FDaysFragments).addToBackStack(null).commit();

    }


}

