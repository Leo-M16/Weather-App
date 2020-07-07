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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import project.msc.leo.weatherappadmin.MainActivity;
import project.msc.leo.weatherappadmin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeleteFragment.OnDeleteFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String city;
    private String date;
    private Button yes;
    private Button no;
    private OnDeleteFragmentInteractionListener mListener;
    private HashMap<String,String> Stoke=new HashMap<String, String>();
    private HashMap<String,String> Coventry=new HashMap<String, String>();
    private String childtobedeleted;



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



    public DeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteFragment newInstance(String param1, String param2) {
        DeleteFragment fragment = new DeleteFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete, container, false);
        yes=(Button) view.findViewById(R.id.yesbutton);
        no=(Button) view.findViewById(R.id.nobutton);
        Stoke.put("today","-LHKWIudjic1HM6BwyVY");
        Coventry.put("After Two Days","-LGXO60n1VEoX7WjfniR");



        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(city=="Stoke"){
                    if(date.equals("today")){
                        childtobedeleted=Stoke.get("today");
                    }
                }else if(city.equals("Coventry")){
                    if(date.equals("today")) {
                        childtobedeleted = Coventry.get("today");
                    }else if(date.equals("tomorrow")){
                        childtobedeleted=Coventry.get("tomorrow");
                    }else if(date.equals("After Two Days")){
                        childtobedeleted=Coventry.get("After Two Days");
                    }else if(date.equals("After Three Days")){
                        childtobedeleted=Coventry.get("After Three Days");
                    }
                }

                new DeleteAsyncTask(getActivity(),city,childtobedeleted).execute("");

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentInteraction(Uri.parse(""));


            }
        });


        return view ;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String city,String date) {
        if (mListener != null) {
            mListener.onDeleteFragmentInteraction(city,date);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDeleteFragmentInteractionListener) {
            mListener = (OnDeleteFragmentInteractionListener) context;
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
    public interface OnDeleteFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDeleteFragmentInteraction(String city,String date);
    }
}

 class DeleteAsyncTask extends AsyncTask<String, String,String>{

     private FirebaseDatabase mFirebaseDatabase;
     DatabaseReference mMessagesDatabaseReference;
     private Activity context;
     private String city;
     private String key;

public DeleteAsyncTask(Activity context,String city,String key){
    this.context=context;
    this.city=city;
    this.key=key;

}
    @Override
    protected String doInBackground(String... strings) {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child(city).child(key);
        mMessagesDatabaseReference.removeValue();


        return null;
    }
}
