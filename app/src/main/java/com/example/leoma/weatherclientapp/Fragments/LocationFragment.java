package com.example.leoma.weatherclientapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.leoma.weatherclientapp.MainActivity;
import com.example.leoma.weatherclientapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Spinner locationspinner;
    private Button Submitbtn;
    private String city;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public LocationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LocationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LocationFragment newInstance(String param1, String param2) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true)
                 // default to enabled
         {
            @Override
            public void handleOnBackPressed() {

                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);

    }

/*Add some commentsss*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= (View) inflater.inflate(R.layout.fragment_location, container, false);
        locationspinner=(Spinner) view.findViewById(R.id.locationspinner);
        Submitbtn=(Button) view.findViewById(R.id.Submitbtn);
       locationspinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());



        // Inflate the layout for this fragment
        Submitbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {



                city= locationspinner.getSelectedItem().toString();
                Log.e("the city issssss","city"+city);
                if(city.equals("---")||city.equals(null)){

                    Toast.makeText(getActivity(), "Please select a location",Toast.LENGTH_LONG).show();
                    MainActivity mainActivity = (MainActivity) getActivity();
                   mainActivity.onFragmentInteraction(Uri.parse(""));
                    mainActivity.onFragmentInteraction(Uri.parse(""));


                }else if(city!=null) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.onNowFragmentInteraction(city);

                }

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
//        Toast.makeText(parent.getContext(),
//                "The Selected city is : " + parent.getItemAtPosition(pos).toString(),
//                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



}