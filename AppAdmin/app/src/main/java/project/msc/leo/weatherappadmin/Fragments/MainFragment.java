package project.msc.leo.weatherappadmin.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import project.msc.leo.weatherappadmin.MainActivity;
import project.msc.leo.weatherappadmin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button Stoke;
    private Button London;
    private Button Coventry;
    private String city;



    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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

                /*android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);*/

                System.exit(1);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        Stoke= view.findViewById(R.id.StokeButton);
        London= view.findViewById(R.id.LondonButton);
        Coventry= view.findViewById(R.id.CoventryButton);

        Stoke.setOnClickListener(this);
        London.setOnClickListener(this);
        Coventry.setOnClickListener(this);


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

        FragmentManager.OnBackStackChangedListener callback = new FragmentManager.OnBackStackChangedListener(
                 // default to enabled
        ) {
            @Override
            public void onBackStackChanged() {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

                Toast.makeText(getActivity(),"hello world", Toast.LENGTH_LONG).show();



            }





        };
        };



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void onClick(View view) {
     Button button=(Button) view;
     if (button==Stoke){
         city="Stoke";

     }else if(button==London){
         city="London";
     }else if(button==Coventry){
         city="Coventry";
     }

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.onDateFragmentInteraction(city);

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
