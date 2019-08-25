package talha.com.bd.patha_shathi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.models.AvailablePothSathi;

public class AvailableSatheAdapter2 extends RecyclerView.Adapter<AvailableSatheAdapter2.MyHolder> {

    private Context context;
    private List<AvailablePothSathi> availablePothSathiList;

    public AvailableSatheAdapter2(Context context, List<AvailablePothSathi> availablePothSathiList) {
        this.context = context;
        this.availablePothSathiList = availablePothSathiList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.availble_view_two,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        AvailablePothSathi sathi=availablePothSathiList.get(i);
        myHolder.circleImageView.setImageDrawable(context.getResources().getDrawable(sathi.getImage()));
        myHolder.nameText.setText(sathi.getName());

        myHolder.distanceText.setText("Distance : "+sathi.getDistance()+" km");


    }

    @Override
    public int getItemCount() {
        return availablePothSathiList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView nameText,distanceText;
        Button callForHelpButton;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.circleImageView);
            nameText=itemView.findViewById(R.id.nameTextId);
            distanceText=itemView.findViewById(R.id.distanceId);

        }
    }
}

