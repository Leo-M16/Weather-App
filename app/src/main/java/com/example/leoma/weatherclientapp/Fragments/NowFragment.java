package com.example.leoma.weatherclientapp.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leoma.weatherclientapp.ClimateData;
import com.example.leoma.weatherclientapp.MainActivity;
import com.example.leoma.weatherclientapp.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
/*This is trial for github*/
public class NowFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String city;
    private Button fourdaysbutton;
    private Button detailedbutton;
    private Button changeLocationButton;
    private ImageView TodayImageMain;
    private ImageView TodayImage;
    private ImageView TonightImage;
    private ImageView TomorrowImage;
    private TextView TodayPrecipitationMain;
    private TextView TodayTextViewMain;
    private TextView TodayHighTextViewMain;
    private TextView TodayLowTextViewMain;
    private TextView TodayHighMain;
    private TextView TodayLowMain;
    private TextView TodayPrecipitation;
    private TextView TodayTextView;
    private TextView TodayHighTextView;
    private TextView TodayHigh;
    private TextView TonightPrecipitation;
    private TextView TonightTextView;
    private TextView TonightLow;
    private TextView TomorrowPrecipitation;
    private TextView TomorrowTextView;
    private TextView TomorrowHigh;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private String tomorrow="tomorrow";
    final long ONE_MEGABYTE = 1024 * 1024;








    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private OnFragmentInteractionListener mListener;

    public NowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowFragment newInstance(String param1, String param2) {
        NowFragment fragment = new NowFragment();
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



        if((city==null)||city.equals("---")){
            city="London";
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child(city);
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                FirebaseStorage storage = FirebaseStorage.getInstance();
                ClimateData Cdata = dataSnapshot.getValue(ClimateData.class);

                if(Cdata.getDate().equals("today")){
               TodayPrecipitationMain.setText(Cdata.getPrecipitation());
               TodayHighMain.setText(Cdata.getTempHigh());
               TodayLowMain.setText(Cdata.getTempLow());

               TodayPrecipitation.setText(Cdata.getPrecipitation());
               TodayHigh.setText(Cdata.getTempHigh());
               TodayHighTextView.setText(city);

               if(Cdata.getPrecipitation().equals("Sunny")){
                   TonightPrecipitation.setText("Clear Skies");
               }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                   TonightPrecipitation.setText("Partly Cloudy");
               }else{
                   TonightPrecipitation.setText(Cdata.getPrecipitation());
                   }
               TonightLow.setText(Cdata.getTempLow());
               TonightTextView.setText(city);


               if(Cdata.getPrecipitation().equals("Sunny")) {
                   String db=storage.getReference().toString();
                   Log.e("The reference issssssss",db);

                   StorageReference storageRef = storage.getReference().child("sunny.jpg");
                   storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                           TodayImageMain.setImageBitmap(circularBitmap);
                           TodayImage.setImageBitmap(circularBitmap);


                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });
                   StorageReference storageRef2 = storage.getReference().child("clearskies.jpg");
                   storageRef2.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bmp2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap2 = ImageConverter.getRoundedCornerBitmap(bmp2, 100);

                           TonightImage.setImageBitmap(circularBitmap2);

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });

               }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                   String db=storage.getReference().toString();
                   Log.e("The reference issssssss",db);

                   StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                   storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                           TodayImageMain.setImageBitmap(circularBitmap);
                           TodayImage.setImageBitmap(circularBitmap);
                           TonightImage.setImageBitmap(circularBitmap);

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });




               }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                   String db=storage.getReference().toString();
                   Log.e("The reference issssssss",db);

                   StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                   storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                           TodayImageMain.setImageBitmap(circularBitmap);
                           TodayImage.setImageBitmap(circularBitmap);

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });
                   StorageReference storageRef2 = storage.getReference().child("partlycloudy.jpg");
                   storageRef2.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {

                           Bitmap bmp2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap2 = ImageConverter.getRoundedCornerBitmap(bmp2, 100);

                           TonightImage.setImageBitmap(circularBitmap2);

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });


               }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                   String db=storage.getReference().toString();
                   Log.e("The reference issssssss",db);

                   StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                   storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                           TodayImageMain.setImageBitmap(circularBitmap);
                           TodayImage.setImageBitmap(circularBitmap);

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });


               }else if(Cdata.getPrecipitation().equals("Rainy")){
                   String db=storage.getReference().toString();
                   Log.e("The reference issssssss",db);

                   StorageReference storageRef = storage.getReference().child("rainy.jpg");
                   storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                       @Override
                       public void onSuccess(byte[] bytes) {
                           Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                           Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                           TodayImageMain.setImageBitmap(circularBitmap);
                           TodayImage.setImageBitmap(circularBitmap);
                           TonightImage.setImageBitmap(circularBitmap);

                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception exception) {
                           Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                       }
                   });


               }





                }
           else if(Cdata.getDate().equals("tomorrow")){
               TomorrowPrecipitation.setText(Cdata.getPrecipitation());
               TomorrowHigh.setText(Cdata.getTempHigh());
               TomorrowTextView.setText(city);

                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TomorrowImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rainy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }



                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseStorage storage = FirebaseStorage.getInstance();


                ClimateData Cdata = dataSnapshot.getValue(ClimateData.class);
                Log.e("The date isssssssssssss", Cdata.getDate());
//                TodayPrecipitationMain.setText(Cdata.getPrecipitation());


                if(Cdata.getDate().equals("today")){
                    TodayPrecipitationMain.setText(Cdata.getPrecipitation());
                    TodayHighMain.setText(Cdata.getTempHigh());
                    TodayLowMain.setText(Cdata.getTempLow());

                    TodayPrecipitation.setText(Cdata.getPrecipitation());
                    TodayHigh.setText(Cdata.getTempHigh());
                    TodayHighTextView.setText(city);

                    if(Cdata.getPrecipitation().equals("Sunny")){
                        TonightPrecipitation.setText("Clear Skies");
                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        TonightPrecipitation.setText("Partly Cloudy");
                    }else{
                        TonightPrecipitation.setText(Cdata.getPrecipitation());
                    }
                    TonightLow.setText(Cdata.getTempLow());
                    TonightTextView.setText(city);


                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TodayImageMain.setImageBitmap(circularBitmap);
                                TodayImage.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                        StorageReference storageRef2 = storage.getReference().child("clearskies.jpg");
                        storageRef2.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap2 = ImageConverter.getRoundedCornerBitmap(bmp2, 100);

                                TonightImage.setImageBitmap(circularBitmap2);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TodayImageMain.setImageBitmap(circularBitmap);
                                TodayImage.setImageBitmap(circularBitmap);
                                TonightImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });




                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TodayImageMain.setImageBitmap(circularBitmap);
                                TodayImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                        StorageReference storageRef2 = storage.getReference().child("partlycloudy.jpg");
                        storageRef2.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {

                                Bitmap bmp2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap2 = ImageConverter.getRoundedCornerBitmap(bmp2, 100);

                                TonightImage.setImageBitmap(circularBitmap2);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageMain.setImageBitmap(circularBitmap);
                                TodayImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rainy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TodayImageMain.setImageBitmap(circularBitmap);
                                TodayImage.setImageBitmap(circularBitmap);
                                TonightImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }





                }
                else if(Cdata.getDate().equals("tomorrow")){
                    TomorrowPrecipitation.setText(Cdata.getPrecipitation());
                    TomorrowHigh.setText(Cdata.getTempHigh());
                    TomorrowTextView.setText(city);

                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TomorrowImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rainy")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);

                                TomorrowImage.setImageBitmap(circularBitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }



                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mMessagesDatabaseReference.addChildEventListener(mChildEventListener);

        OnBackPressedCallback callback = new OnBackPressedCallback(true)
                // default to enabled
        {
            @Override
            public void handleOnBackPressed() {

                /*android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);*/

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentInteraction(Uri.parse(""));

            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this,callback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_now, container, false);
        // Inflate the layout for this fragment
        changeLocationButton=(Button) view.findViewById(R.id.changelocationbutton);
        TodayImageMain= view.findViewById(R.id.TodayImageViewMain1);
        TodayImage=view.findViewById(R.id.TodayImageView1);
        TonightImage=view.findViewById(R.id.TonightImageView1);
        TomorrowImage=view.findViewById(R.id.TomorrowImageView1);
        TodayPrecipitationMain=view.findViewById(R.id.TodayPrecipitationMain1);
        TodayTextViewMain=view.findViewById(R.id.TodayTextViewMain1);
        TodayHighTextViewMain=view.findViewById(R.id.TodayHighTextViewMain1);
        TodayLowTextViewMain=view.findViewById(R.id.TodayLowTextViewMain1);
        TodayHighMain=view.findViewById(R.id.TodayHighMain1);
        TodayLowMain=view.findViewById(R.id.TodayLowMain1);
        TodayPrecipitation=view.findViewById(R.id.TodayPrecipitation1);
        TodayTextView=view.findViewById(R.id.TodayTextView1);
        TodayHighTextView=view.findViewById(R.id.TodayHighTextView1);
        TodayHigh=view.findViewById(R.id.TodayHigh1);
        TonightPrecipitation=view.findViewById(R.id.TonightPrecipitation1);
        TonightTextView=view.findViewById(R.id.TonightLowTextView1);
        TonightLow=view.findViewById(R.id.TonightLow);
        TomorrowPrecipitation=view.findViewById(R.id.TomorrowPrecipitation1);
        TomorrowTextView=view.findViewById(R.id.textView21);
        TomorrowHigh=view.findViewById(R.id.TomorrowHigh1);

        fourdaysbutton=(Button) view.findViewById(R.id.fourdaysviewbutton);
        TodayTextViewMain.setText(" Now"+"\n"+city);

        fourdaysbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onFourDaysFragmentInteraction(city);
            }
        });

        changeLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onFragmentInteraction(Uri.parse(""));
            }
        });





        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String city) {
        if (mListener != null) {
            mListener.onNowFragmentInteraction(city);
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
        Log.d("Heyyyyyyy","It's meeeeeeeeeeee");
        Log.e("Heyyyyyyyyy","It's Meeeee");
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
        void onNowFragmentInteraction(String city);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("City",city);
        Log.d("onSaveInstanceState","Saved instanceeeeee");
        Log.e("onSaveInstanceState","Saved instanceeee");

    }

}


