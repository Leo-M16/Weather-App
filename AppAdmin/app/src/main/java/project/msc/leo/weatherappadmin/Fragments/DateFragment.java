package project.msc.leo.weatherappadmin.Fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import project.msc.leo.weatherappadmin.MainActivity;
import project.msc.leo.weatherappadmin.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DateFragment.DateInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String City;
    private String Date;
    private TextView location;

    private DateInteractionListener mListener;

    public void setCity(String city) {
        City = city;
    }

    private Button Today;
    private Button Tomorrow;
    private Button Aftertwodays;
    private Button Afterthreedays;


    public DateFragment() {

        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateFragment newInstance(String param1, String param2) {
        DateFragment fragment = new DateFragment();
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
        View view= inflater.inflate(R.layout.fragment_date, container, false);
        // Inflate the layout for this fragment

        Today= view.findViewById(R.id.Today);
        Tomorrow= view.findViewById(R.id.Tomorrow);
        Aftertwodays=view.findViewById(R.id.AfterTwoDays);
        Afterthreedays=view.findViewById(R.id.AfterThreeDays);

        Today.setOnClickListener(this);
        Tomorrow.setOnClickListener(this);
        Aftertwodays.setOnClickListener(this);
        Afterthreedays.setOnClickListener(this);


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String city) {
        if (mListener != null) {
            mListener.onDateFragmentInteraction(city);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DateInteractionListener) {
            mListener = (DateInteractionListener) context;
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

    @Override
    public void onClick(View view) {
        Button button=(Button) view;

      if(button==Today){
          Date="today";
      }else if(button==Tomorrow){
          Date="tomorrow";
      }else if(button==Aftertwodays){
          Date="After Two Days";
      }else if(button==Afterthreedays){
          Date="After Three Days";
        }


        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.onFragmentInteraction(City,Date);


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
    public interface DateInteractionListener {
        // TODO: Update argument type and name
        void onDateFragmentInteraction(String city);
    }
}
