package talha.com.bd.patha_shathi.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.adapters.NotificationAdapter;
import talha.com.bd.patha_shathi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    private String[] notificationList;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        notificationList=getResources().getStringArray(R.array.notification_list);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        NotificationAdapter adapter=new NotificationAdapter(getActivity(),notificationList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
