package talha.com.bd.patha_shathi.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import talha.com.bd.patha_shathi.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private String[] notificationList;

    public NotificationAdapter(Context context, String[] notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.notification_view,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        //viewHolder.imageView.setImageResource(R.drawable.notification_icon);
        viewHolder.textView.setText(notificationList[i]);
       if (i%3==0){
           viewHolder.cardView.setBackgroundResource(R.color.back);
       }

    }

    @Override
    public int getItemCount() {
        return notificationList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //ImageView imageView;
        TextView textView;
        CardView cardView;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            //imageView=itemView.findViewById(R.id.notificationImageId);
            textView=itemView.findViewById(R.id.notificationTextId);
            cardView=itemView.findViewById(R.id.cardViewId);

        }
    }
}
