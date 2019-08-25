package talha.com.bd.patha_shathi.models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingletone {

    private static MySingletone mySingletone;
    private Context context;
    private RequestQueue requestQueue;


    public MySingletone(Context context) {
        this.context = context;
        requestQueue = getRequestQueue(context);

    }

    public RequestQueue getRequestQueue (Context context){
        if (requestQueue ==null){
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }


    public static synchronized MySingletone getInstance (Context context){
        if (mySingletone == null){
            mySingletone = new MySingletone(context);
        }
        return mySingletone;
    }

    public <T> void addToRequestQueue (Request<T> request){
        requestQueue.add(request);
    }

}
