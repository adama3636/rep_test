package com.example.adama.findmypharmacie.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adama.findmypharmacie.models.DatabaseHelperPharmacie;
import com.example.adama.findmypharmacie.models.Pharmacie;
import com.example.adama.findmypharmacie.utils.GPSTracker;
import com.example.adama.findmypharmacie.R;


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


    GPSTracker gps;
    public double latitude;
    public double longitude;

    TextView tvName;
    TextView tvAddress;
    TextView tvTelephone;
    TextView tvLongitude;
    TextView tvLatitude;
    TextView tvAccuracy;
    Button btnSubmit;

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

       // checkLocation();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        this.tvName = (TextView) view.findViewById(R.id.name);
        this.tvAddress = (TextView) view.findViewById(R.id.neighborhood);
        this.tvTelephone = (TextView) view.findViewById(R.id.phone);
        this.tvLongitude = (TextView) view.findViewById(R.id.logitude);
        this.tvLatitude = (TextView) view.findViewById(R.id.latitude);
        this.tvAccuracy = (TextView) view.findViewById(R.id.accuracy);
        this.btnSubmit = (Button) view.findViewById(R.id.submit);
        this.btnSubmit.setOnClickListener(this);

        Button btnRefresh = (Button)  view.findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(this);
        btnRefresh.performClick();

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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.refresh){

            GPSTracker gps = new GPSTracker(getContext());
            double latitude = 0;
            double longitude = 0;
            double accuracy = 0;

            if(gps.canGetLocation()){
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                accuracy = gps.getAccuracy();
                Toast.makeText(getContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude+ "\nAcc: " + accuracy, Toast.LENGTH_LONG).show();
                //btnSubmit.setEnabled(true);

            }else{
                gps.showSettingsAlert();
            }
            if((latitude!=0) && (longitude != 0)){

                this.tvLatitude.setText(latitude + "");
                this.tvLongitude.setText(longitude + "");
                this.tvAccuracy.setText(accuracy + "");
                this.btnSubmit.setEnabled(true);
                this.btnSubmit.setBackgroundColor(Color.parseColor("#81C784"));
            }
        }else if(v.getId() == R.id.submit){
            Toast.makeText(getContext(),"Pharmacie insere avec succes", Toast.LENGTH_LONG).show();
            Boolean pass = true;
            //if(tvName.getText().length() < 1){tvName.setError("Obligatoire!"); pass = false;}
            //if(tvAddress.getText().length() < 1){tvAddress.setError("Obligatoire!"); pass = false;}
            //if(tvTelephone.getText().length() < 1){tvTelephone.setError("Obligatoire!"); pass = false;}

            if(pass){
                // Get values of Componants
                String strName = tvName.getText().toString();
                String strAddress = tvAddress.getText().toString();
                String strTelephone = tvTelephone.getText().toString();
                String strEmei = tvTelephone.getText().toString();
                String strLatitude = tvLatitude.getText().toString();
                String strLongitude = tvLongitude.getText().toString();
                String strAccuracy = tvAccuracy.getText().toString();
                DatabaseHelperPharmacie dbCon = new DatabaseHelperPharmacie(getContext());

                Pharmacie pharmacie = new Pharmacie(strName,strTelephone, strAddress, strEmei, strLatitude, strLongitude,strAccuracy);
                dbCon.insertPharmacie(pharmacie);
                Toast.makeText(getContext(),"Pharmacie insere avec succes", Toast.LENGTH_LONG).show();
                //updateList();
               // Intent i = new Intent(FormContact.this, MainActivity.class);
//                Fragment listPharmacie = new ListPharmacie();
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.list_pharmacie, listPharmacie, null);
//                fragmentTransaction.commit();

                // i.putExtra("Name",strName);
//                i.putExtra("Surname",strSurname);
//                i.putExtra("Sexe", sex);
//                i.putExtra("Login", strLogin);
//                i.putExtra("Password", strPassword);
//
                //startActivity(i);
            }
        }

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
