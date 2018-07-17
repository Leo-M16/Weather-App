package project.msc.leo.weatherappadmin.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import project.msc.leo.weatherappadmin.ClimateData;
import project.msc.leo.weatherappadmin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModifyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModifyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String city;
    private String date;
    private Spinner precipitationSpinner;
    private Button btnSubmit;
    private EditText HighTemp;
    private EditText LowTemp;
    private HashMap<String,String> Coventry=new HashMap<String,String>();
    private HashMap<String,String> London=new HashMap<String,String>();
    private HashMap<String,String> Stoke=new HashMap<String, String>();

    /* This is how to declare HashMap */
    HashMap<Integer, String> hmap = new HashMap<Integer, String>();

    /*Adding elements to HashMap*/



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }








    private OnFragmentInteractionListener mListener;

    public ModifyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModifyFragment newInstance(String param1, String param2) {
        ModifyFragment fragment = new ModifyFragment();
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
        View view=inflater.inflate(R.layout.fragment_modify, container, false);
        precipitationSpinner=view.findViewById(R.id.precipitationspinner2);
        HighTemp=view.findViewById(R.id.High2);
        LowTemp=view.findViewById(R.id.Low2);
        btnSubmit=view.findViewById(R.id.btnSubmit2);
        // Initializing the keys
        Coventry.put("today","/Coventry/-LGXO60n1VEoX7WjfniR");
        London.put("today","London/-LGHEDjkx1KksriJzRwI");
        London.put("tomorrow","/London/-LGHE0xkpPfQCJhJkYjA");
        London.put("After Two Days","/London/-LGHEUj2Ria0h3BZhuHK");
        London.put("After Three Days","/London/-LGHE_HIEMmO0gAWnfBw");
        Stoke.put("today","/Stoke/-LHc48sevnQBQw2bKU0D");
        Stoke.put("tomorrow","/Stoke/-LHc5jsiLlbwOv-0IW4e");
        Stoke.put("After Two Days","/Stoke/-LHc60LP6hcqHov54kti");
        Stoke.put("After Three Days","/Stoke/-LHc67JI4Sb-HWiJuwZ4");


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClimateData CD= new ClimateData(date,HighTemp.getText().toString(),LowTemp.getText().toString(),precipitationSpinner.getSelectedItem().toString());


                Map<String, Object> postValues = CD.toMap();
                Map<String, Object> childUpdates = new HashMap<>();


                if(city=="Coventry"){
                    childUpdates.put(Coventry.get("today").toString(),postValues);
                }

                else if(city=="London"){
                    if(date=="today") {
                        childUpdates.put(London.get("today"), postValues);
                    }else if(date=="tomorrow"){
                        childUpdates.put(London.get("tomorrow"), postValues);
                    }else if(date=="After Two Days") {
                        childUpdates.put(London.get("After Two Days"), postValues);
                    }else if (date=="After Three Days"){
                        childUpdates.put(London.get("After Three Days"), postValues);
                        }

                }else if(city=="Stoke"){
                    if(date=="today") {
                        childUpdates.put(Stoke.get("today"), postValues);
                    }else if(date=="tomorrow"){
                        childUpdates.put(Stoke.get("tomorrow"), postValues);
                    }else if(date=="After Two Days") {
                        childUpdates.put(Stoke.get("After Two Days"), postValues);
                    }else if (date=="After Three Days"){
                        childUpdates.put(Stoke.get("After Three Days"), postValues);
                    }

                }


//                childUpdates.put("/Coventry/"+"-LGXO60n1VEoX7WjfniR", postValues);

                new ModifyAsyncTask(getActivity(),childUpdates).execute("");


            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String city,String date) {
        if (mListener != null) {
            mListener.onModifyFragmentInteraction(city,date);
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
        void onModifyFragmentInteraction(String city,String date);
    }
}


class ModifyAsyncTask extends AsyncTask<String, String,String> {

    private FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mMessagesDatabaseReference;
    Map<String, Object> childUpdates = new HashMap<>();
    private Activity context;


    public ModifyAsyncTask(Activity context,Map<String,Object> CU){
        this.childUpdates=CU;
        this.context=context;


    }


    @Override
    protected String doInBackground(String... strings) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference();
        mMessagesDatabaseReference.updateChildren(childUpdates);

        return null;
    }
}