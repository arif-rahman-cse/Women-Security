package talha.com.bd.patha_shathi.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.models.AvailablePothSathi;
import talha.com.bd.patha_shathi.adapters.AvailableSatheAdapter;
import talha.com.bd.patha_shathi.adapters.AvailableSatheAdapter2;

public class AvailablePsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button button;
    private ImageView mapImage;
    private List<AvailablePothSathi> availablePothSathiList;
    private AvailableSatheAdapter adapter;
    private AvailableSatheAdapter2 adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_ps);
        availablePothSathiList=new ArrayList<>();
        mapImage=findViewById(R.id.mapImageId);
        recyclerView=findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);

       recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        availablePothSathiList.add(new AvailablePothSathi("Lelin Mahmud","2.1",R.drawable.usermel));
        availablePothSathiList.add(new AvailablePothSathi("Likhon Mahmud","1.2",R.drawable.usermel));
        availablePothSathiList.add(new AvailablePothSathi("Kayes Mahmud","3.2",R.drawable.usermel));
        availablePothSathiList.add(new AvailablePothSathi("Sumon Mahmud",".5",R.drawable.usermel));

        adapter=new AvailableSatheAdapter(this,availablePothSathiList);
        adapter2=new AvailableSatheAdapter2(this,availablePothSathiList);
        if (availablePothSathiList.size()==1){
            recyclerView.setAdapter(adapter2);
            recyclerView.setAdapter(adapter2);

        }
        else {
            recyclerView.setAdapter(adapter);
            recyclerView.setAdapter(adapter);
        }


    }
}
