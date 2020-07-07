package project.msc.leo.weatherappadmin.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import project.msc.leo.weatherappadmin.ClimateData;
import project.msc.leo.weatherappadmin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DbUpdateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DbUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DbUpdateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private String city;
    private String date;
    private Spinner precipitationSpinner;
    private Button btnSubmit;
    private EditText HighTemp;
    private EditText LowTemp;

//    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json");




    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DbUpdateFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DbUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DbUpdateFragment newInstance(String param1, String param2) {
        DbUpdateFragment fragment = new DbUpdateFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_db_update, container, false);
        precipitationSpinner =(Spinner) view.findViewById(R.id.spinner);
        btnSubmit=(Button) view.findViewById(R.id.btnSubmit);
        HighTemp=(EditText) view.findViewById(R.id.HighTemp);
        LowTemp=(EditText) view.findViewById(R.id.LowTemp);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {





                new MyAsyncTask(getActivity(),city, date,HighTemp.getText().toString(),LowTemp.getText().toString(),precipitationSpinner.getSelectedItem().toString()).execute("");

                Toast.makeText(getActivity(),
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(precipitationSpinner.getSelectedItem())+
                                "\n City :"+city + "\n Date :"+date,
                        Toast.LENGTH_SHORT).show();


            }

        });


        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String city,String date) {
        if (mListener != null) {
            mListener.onDbUpdateFragmentInteraction(city,date);
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
        void onDbUpdateFragmentInteraction(String city,String date);
    }
}


class MyAsyncTask extends AsyncTask<String, String,String> {


    Activity mContex;
    private String city;
    private String date;
    private String HighTemp;
    private String LowTemp;
    private String precipitationSpinner;


    private FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;



    public MyAsyncTask(Activity contex, String city, String date,String HighTemp,String LowTemp,String precipitationSpinner) {
        this.mContex = contex;
        this.city = city;
        this.date=date;
        this.HighTemp=HighTemp;
        this.LowTemp=LowTemp;
        this.precipitationSpinner=precipitationSpinner;

        }


    @Override
    protected String doInBackground(String... strings) {


       mFirebaseDatabase = FirebaseDatabase.getInstance();
//
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child(city);
        ClimateData CD= new ClimateData(date,HighTemp,LowTemp,precipitationSpinner);



        mMessagesDatabaseReference.push().setValue(CD);





        return city;

    }

    public String cityJSON (String city){
        return "{'city':'"+city+"'}";
    }

}

