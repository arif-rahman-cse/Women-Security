package talha.com.bd.patha_shathi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.models.Pathasathis;

public class PathasathisAdepter extends RecyclerView.Adapter<PathasathisAdepter.pathasathiViewHolder>{

    private Context context;
    private List<Pathasathis> pathasathis;
    private Position pos;
    private boolean isTrusted;

    public PathasathisAdepter(Context context, List<Pathasathis> pathasathis,Position pos) {
        this.context = context;
        this.pathasathis = pathasathis;
        this.pos = pos;
    }

    public interface Position{
        void getPosition(ImageView image);
    }
    @NonNull
    @Override
    public pathasathiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pathasathi_list,parent,false);
        pathasathiViewHolder holder = new pathasathiViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final pathasathiViewHolder holder, final int position) {


        Pathasathis pathasathi = pathasathis.get(position);
        holder.nameTV.setText(pathasathi.getName());
        holder.monthTV.setText(pathasathi.getMonth());
        holder.pictureIV.setImageDrawable(context.getResources().getDrawable(pathasathi.getPicture()));
        holder.heartIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //isTrusted= pos.getPosition(position,0);
                pos.getPosition(holder.heartIV);

               /* if (isTrusted){
                    holder.heartIV.setImageResource(R.drawable.trusted_red);
                }*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return pathasathis.size();
    }

    public class pathasathiViewHolder extends RecyclerView.ViewHolder {

     TextView nameTV,monthTV;
     ImageView pictureIV,tickIV,heartIV;

     public pathasathiViewHolder(@NonNull View itemView) {
         super(itemView);

         nameTV = itemView.findViewById(R.id.name_id);
         monthTV = itemView.findViewById(R.id.month_id);
         pictureIV = itemView.findViewById(R.id.imageView_id);
         tickIV = itemView.findViewById(R.id.image2_id);
         heartIV = itemView.findViewById(R.id.heart_id);

     }
 }
}
