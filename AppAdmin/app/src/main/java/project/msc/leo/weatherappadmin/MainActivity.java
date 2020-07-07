package project.msc.leo.weatherappadmin;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import project.msc.leo.weatherappadmin.Fragments.ActivityOptionsFragment;
import project.msc.leo.weatherappadmin.Fragments.DateFragment;
import project.msc.leo.weatherappadmin.Fragments.DbUpdateFragment;
import project.msc.leo.weatherappadmin.Fragments.DeleteFragment;
import project.msc.leo.weatherappadmin.Fragments.MainFragment;
import project.msc.leo.weatherappadmin.Fragments.ModifyFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, DateFragment.DateInteractionListener,
        ActivityOptionsFragment.OnFragmentInteractionListener, DbUpdateFragment.OnFragmentInteractionListener , DeleteFragment.OnDeleteFragmentInteractionListener, ModifyFragment.OnFragmentInteractionListener
{
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
        MainFragment firstFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment).addToBackStack(null).commit();

    }

    @Override
    public void onDateFragmentInteraction(String city){
        DateFragment dateFragment=new DateFragment();
        dateFragment.setCity(city);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dateFragment).addToBackStack(null).commit();


    }

    @Override
    public void onFragmentInteraction(String city, String date) {
        ActivityOptionsFragment AOFragment=new ActivityOptionsFragment();
        AOFragment.setCity(city);
        AOFragment.setDate(date);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AOFragment).addToBackStack(null).commit();


    }
/*Add some comment*/
    @Override
    public void onDbUpdateFragmentInteraction(String city, String date) {
        DbUpdateFragment DbUFragment= new DbUpdateFragment();
        DbUFragment.setCity(city);
        DbUFragment.setDate(date);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DbUFragment).addToBackStack(null).commit();

    }

    @Override
    public void onDeleteFragmentInteraction(String city, String date) {
        DeleteFragment DltFragment=new DeleteFragment();
        DltFragment.setCity(city);
        DltFragment.setDate(date);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DltFragment).addToBackStack(null).commit();

    }

    @Override
    public void onModifyFragmentInteraction(String city, String date) {
        ModifyFragment MdfyFragment=new ModifyFragment();
        MdfyFragment.setCity(city);
        MdfyFragment.setDate(date);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MdfyFragment).addToBackStack(null).commit();

    }
}



