package talha.com.bd.patha_shathi.activities;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.adapters.HarassAdapter;

public class HarassmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HarassAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList <String> harassmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haressment);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        harassmentList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        harassmentList.add("Ethnic Harassment");
        harassmentList.add("Ethnic Harassment");
        harassmentList.add("Ethnic Harassment");
        harassmentList.add("Ethnic Harassment");
        harassmentList.add("Ethnic Harassment");
        harassmentList.add("Ethnic Harassment");
        harassmentList.add("Ethnic Harassment");
        adapter = new HarassAdapter(harassmentList,this);
        recyclerView.setAdapter(adapter);
        }
    }
