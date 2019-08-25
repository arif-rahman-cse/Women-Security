package talha.com.bd.patha_shathi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.R;

public class HarassAdapter extends RecyclerView.Adapter<HarassAdapter.MyViewHolder>{

    private ArrayList<String> harassmentList;
    Context context;

    public HarassAdapter(ArrayList<String> harassmentList, Context context) {
        this.harassmentList = harassmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.harassment,viewGroup,false);
        MyViewHolder viewholder = new MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String title =harassmentList.get(i);
        myViewHolder.textView.setText(title);
    }

    @Override
    public int getItemCount() {
        return harassmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView =itemView.findViewById(R.id.text_view);
        }

    }
}
