package talha.com.bd.patha_shathi.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.adapters.PathasathisAdepter;
import talha.com.bd.patha_shathi.models.Pathasathis;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class MyPathasathis extends AppCompatActivity implements PathasathisAdepter.Position {


    private RecyclerView recyclerView;
    private List<Pathasathis> pathasathis;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView image, trustImage;
    private TextView trusted;
    private boolean isTrusted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pathasathis);

        recyclerView  = findViewById(R.id.recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pathasathis = new ArrayList<>();
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));
        pathasathis.add(new Pathasathis("Ifty","3 month",R.drawable.usermel));

//        PathasathisAdepter adepter = new PathasathisAdepter(this,pathasathis,MyPathasathis);
        PathasathisAdepter adepter = new PathasathisAdepter(this,pathasathis,this);
        recyclerView.setAdapter(adepter);
    }

    @Override
    public void getPosition(ImageView imageView) {

        image=imageView;
        bottomSheetDialog = new BottomSheetDialog(MyPathasathis.this);
        View parent = getLayoutInflater().inflate(R.layout.bottom_sheet,null,false);

        trustImage  = parent.findViewById(R.id.trust_id);
        trusted  = parent.findViewById(R.id.trusted);
        bottomSheetDialog.setContentView(parent);
        bottomSheetBehavior = BottomSheetBehavior.from((View)parent.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150,getResources().getDisplayMetrics()));
        bottomSheetDialog.show();


    }

    public void makeTrusted(View view) {

        if (!isTrusted){
            image.setImageResource(R.drawable.trusted_red);
            trustImage.setImageResource(R.drawable.trusted_red);
            trusted.setText("Remove from trusted");
            isTrusted =true;
        }
        else {
            image.setImageResource(R.drawable.trusted_gray);
            trustImage.setImageResource(R.drawable.trusted_gray);
            trusted.setText("Make Trusted Pathasathi");
            isTrusted =false;
        }


    }
}
