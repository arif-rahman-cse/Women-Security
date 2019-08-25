package talha.com.bd.patha_shathi.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.activities.AvailablePsActivity;
import talha.com.bd.patha_shathi.activities.HarassmentActivity;
import talha.com.bd.patha_shathi.activities.MapsActivity;
import talha.com.bd.patha_shathi.activities.MapsCurrentActivity;
import talha.com.bd.patha_shathi.activities.UserImageCaptureActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView camera, myPathasathi, anyOneThere, help,currentLocation;


    public HomeFragment() {
        // Required empty public constructor
    }

    OpenMypathaSathi openMypathaSathi;

    public interface OpenMypathaSathi{
        public void openMypathaSathiFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        myPathasathi = view.findViewById(R.id.myPathasathi);
        anyOneThere = view.findViewById(R.id.anyOneThere);
        help = view.findViewById(R.id.help);
        currentLocation=view.findViewById(R.id.current_location_id);




        myPathasathi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMypathaSathi.openMypathaSathiFragment();
            }
        });

        anyOneThere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapsActivity.class));
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HarassmentActivity.class));
            }
        });

        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MapsCurrentActivity.class));
            }
        });
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        openMypathaSathi = (OpenMypathaSathi) activity;
    }

}
