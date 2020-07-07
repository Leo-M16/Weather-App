package com.example.leoma.weatherclientapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
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
 * {@link FourDaysFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FourDaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourDaysFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private String city;
    private Button detailedview;

    private ImageView TodayImageView;
    private TextView TodayPrecipitation;
    private TextView TodayHigh;
    private TextView TodayLow;

    private ImageView TomorrowImageView;
    private TextView TomorrowPrecipitation;
    private TextView TomorrowHigh;
    private TextView TomorrowLow;

    private ImageView AfterTwoDaysIV;
    private TextView AfterTwoDaysPrec;
    private TextView AfterTwoDaysHigh;
    private TextView AfterTwoDaysLow;

    private ImageView AfterThreeDaysIV;
    private TextView AfterThreeDaysPrec;
    private TextView AfterThreeDaysHigh;
    private TextView AfterThreeDaysLow;

    private TextView cd1;
    private TextView cd2;
    private TextView cd3;
    private TextView cd4;
    final long ONE_MEGABYTE = 1024 * 1024;





    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private OnFragmentInteractionListener mListener;

    public FourDaysFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FourDaysFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourDaysFragment newInstance(String param1, String param2) {
        FourDaysFragment fragment = new FourDaysFragment();
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

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMessagesDatabaseReference = mFirebaseDatabase.getReference().child(city);

        mChildEventListener = new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ClimateData Cdata = dataSnapshot.getValue(ClimateData.class);
                FirebaseStorage storage = FirebaseStorage.getInstance();


                if(Cdata.getDate().equals("today")){
                    TodayPrecipitation.setText(Cdata.getPrecipitation());
                    TodayHigh.setText(Cdata.getTempHigh());
                    TodayLow.setText(Cdata.getTempLow());




                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

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
                                TodayImageView.setImageBitmap(circularBitmap);

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
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rainy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }








                }else if(Cdata.getDate().equals("tomorrow")){
                    TomorrowPrecipitation.setText(Cdata.getPrecipitation());
                    TomorrowHigh.setText(Cdata.getTempHigh());
                    TomorrowLow.setText(Cdata.getTempLow());





                        if(Cdata.getPrecipitation().equals("Sunny")) {
                            String db=storage.getReference().toString();

                            StorageReference storageRef = storage.getReference().child("sunny.jpg");
                            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                    TomorrowImageView.setImageBitmap(circularBitmap);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                        }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                            String db=storage.getReference().toString();

                            StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                    TomorrowImageView.setImageBitmap(circularBitmap);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });




                        }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                            String db=storage.getReference().toString();

                            StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                    TomorrowImageView.setImageBitmap(circularBitmap);


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
                                    TomorrowImageView.setImageBitmap(circularBitmap);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                        }else if(Cdata.getPrecipitation().equals("Rainy")){
                            String db=storage.getReference().toString();

                            StorageReference storageRef = storage.getReference().child("rainy.jpg");
                            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                    TomorrowImageView.setImageBitmap(circularBitmap);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                        }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                            String db=storage.getReference().toString();

                            StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                @Override
                                public void onSuccess(byte[] bytes) {
                                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                    Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                    TomorrowImageView.setImageBitmap(circularBitmap);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                        }


                }else if(Cdata.getDate().equals("After Two Days")){
                    AfterTwoDaysPrec.setText(Cdata.getPrecipitation());
                    AfterTwoDaysHigh.setText(Cdata.getTempHigh());
                    AfterTwoDaysLow.setText(Cdata.getTempLow());

                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });




                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rain")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers"))
                        {
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }






                }else if(Cdata.getDate().equals("After Three Days")){
                    AfterThreeDaysPrec.setText(Cdata.getPrecipitation());
                    AfterThreeDaysHigh.setText(Cdata.getTempHigh());
                    AfterThreeDaysLow.setText(Cdata.getTempLow());

                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                                }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });




                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                                }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rain")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                                }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }

                }









                if(Integer.parseInt(Cdata.getTempHigh().toString())>=21){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("Heatwave");
                    builder.setMessage("Very High Temperature");
                    builder.setIcon(R.drawable.sun);

                    builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        alertTextView.setVisibility(View.VISIBLE);
                        }
                    });

                    builder.show();




                }

                Log.d("onChild added Button", (String) TodayHigh.getText());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ClimateData Cdata = dataSnapshot.getValue(ClimateData.class);
                FirebaseStorage storage = FirebaseStorage.getInstance();


                if(Cdata.getDate().equals("today")){
                    TodayPrecipitation.setText(Cdata.getPrecipitation());
                    TodayHigh.setText(Cdata.getTempHigh());
                    TodayLow.setText(Cdata.getTempLow());




                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

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
                                TodayImageView.setImageBitmap(circularBitmap);

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
                                TodayImageView.setImageBitmap(circularBitmap);

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
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rain")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TodayImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }



                }else if(Cdata.getDate().equals("tomorrow")){
                    TomorrowPrecipitation.setText(Cdata.getPrecipitation());
                    TomorrowHigh.setText(Cdata.getTempHigh());
                    TomorrowLow.setText(Cdata.getTempLow());





                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TomorrowImageView.setImageBitmap(circularBitmap);

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
                                TomorrowImageView.setImageBitmap(circularBitmap);


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
                                TomorrowImageView.setImageBitmap(circularBitmap);


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
                                TomorrowImageView.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rain")){
                        String db=storage.getReference().toString();
                        Log.e("The reference issssssss",db);

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TomorrowImageView.setImageBitmap(circularBitmap);


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                TomorrowImageView.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }


                }else if(Cdata.getDate().equals("After Two Days")){
                    AfterTwoDaysPrec.setText(Cdata.getPrecipitation());
                    AfterTwoDaysHigh.setText(Cdata.getTempHigh());
                    AfterTwoDaysLow.setText(Cdata.getTempLow());

                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });




                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rain")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterTwoDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }






                }else if(Cdata.getDate().equals("After Three Days")){
                    AfterThreeDaysPrec.setText(Cdata.getPrecipitation());
                    AfterThreeDaysHigh.setText(Cdata.getTempHigh());
                    AfterThreeDaysLow.setText(Cdata.getTempLow());

                    if(Cdata.getPrecipitation().equals("Sunny")) {
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Partly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });




                    }else if(Cdata.getPrecipitation().equals("Partly Sunny")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("partlysunny.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("mostlycloudy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Rain")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("rainy.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }else if(Cdata.getPrecipitation().equals("Mostly Cloudy With Showers")){
                        String db=storage.getReference().toString();

                        StorageReference storageRef = storage.getReference().child("sunnycloudyshowers.jpg");
                        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                            @Override
                            public void onSuccess(byte[] bytes) {
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bmp, 100);
                                AfterThreeDaysIV.setImageBitmap(circularBitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getActivity(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });


                    }


                }

                if(Integer.parseInt(Cdata.getTempHigh().toString())>=21){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("Heatwave");
                    builder.setMessage("Very High Temperature");
                    builder.setIcon(R.drawable.sun);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                        alertTextView.setVisibility(View.VISIBLE);
                        }
                    });

                    builder.show();




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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_four_days, container, false);
        detailedview=(Button) view.findViewById(R.id.detailedbutton2);
        detailedview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.onNowFragmentInteraction(city);

            }
        });

        TodayImageView=(ImageView)view.findViewById(R.id.TodayimageView2);
        TodayPrecipitation=(TextView)view.findViewById(R.id.TodayPrecipitation2);
        TodayHigh=(TextView)view.findViewById(R.id.TodayHigh2);
        TodayLow=(TextView)view.findViewById(R.id.TodayLow2);

        TomorrowImageView=(ImageView)view.findViewById(R.id.TomorrowImageView2);
        TomorrowPrecipitation=(TextView)view.findViewById(R.id.TomorrowPrecipitation2);
        TomorrowHigh=(TextView)view.findViewById(R.id.TomorrowHigh2);
        TomorrowLow=(TextView)view.findViewById(R.id.TomorrowLow2);

        AfterTwoDaysIV=(ImageView)view.findViewById(R.id.AfterTwoDaysIV);
        AfterTwoDaysPrec=(TextView)view.findViewById(R.id.AfterTwoDaysPrec);
        AfterTwoDaysHigh=(TextView)view.findViewById(R.id.AfterTwoDaysHigh);
        AfterTwoDaysLow=(TextView)view.findViewById(R.id.AfterTwoDaysLow);

        AfterThreeDaysIV=(ImageView)view.findViewById(R.id.AfterThreeDaysIV);
        AfterThreeDaysPrec=(TextView)view.findViewById(R.id.AfterThreeDaysPrec);
        AfterThreeDaysHigh=(TextView)view.findViewById(R.id.AfterThreeDaysHigh);
        AfterThreeDaysLow=(TextView)view.findViewById(R.id.AfterThreeDaysLow);

        cd1=(TextView) view.findViewById(R.id.cd1);
        cd2=(TextView) view.findViewById(R.id.cd2);
        cd3=(TextView) view.findViewById(R.id.cd3);
        cd4=(TextView) view.findViewById(R.id.cd4);

        cd1.setText(city+"\n"+" Today");
        cd2.setText(" "+city+"\n"+"Tomorrow");
        cd3.setText("    "+city+"\n"+"After Two Days");
        cd4.setText("      "+city+"\n"+"After Three Days");




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String city) {
        if (mListener != null) {
            mListener.onFourDaysFragmentInteraction(city);
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("City",city);
        Log.d("Heyyyyyyy","It's meeeeeeeeeeee");
        Log.e("Heyyyyyyyyy","It's Meeeee");

    }

    public void onResume() {

        if(TodayHigh.equals("TextView")) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.onFourDaysFragmentInteraction(city);
        }


        super.onResume();
        Log.d("onResume","It's meeeeeeeeeeee");
        Log.e("onResume","It's Meeeee");
        Log.d("onResume", (String) TodayHigh.getText());

    }

    public void onPause() {

        super.onPause();
        Log.e("onPause", (String) TodayHigh.getText());

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
        void onFourDaysFragmentInteraction(String city);
    }
}
