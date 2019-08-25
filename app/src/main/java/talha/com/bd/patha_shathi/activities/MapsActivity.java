package talha.com.bd.patha_shathi.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Build;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import talha.com.bd.patha_shathi.R;
import talha.com.bd.patha_shathi.adapters.AvailableSatheAdapter;
import talha.com.bd.patha_shathi.adapters.AvailableSatheAdapter2;
import talha.com.bd.patha_shathi.models.AvailablePothSathi;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMarkerDragListener{


    //recyclerView
    private RecyclerView recyclerView;
    private Button button;
    private ImageView mapImage;
    private List<AvailablePothSathi> availablePothSathiList;
    private AvailableSatheAdapter adapter;
    private AvailableSatheAdapter2 adapter2;

    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastlocaton;
    private Marker currentLocationMarker;
    public static final int REQUEST_LOCATION_CODE = 99;
    private Button searchbtn;
    int PROXIMITY_RADIUS = 10000;
    double latitude,longitude;
    double end_latitude,end_longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //for recyclerview
        availablePothSathiList=new ArrayList<>();
        //mapImage=findViewById(R.id.mapImageId);
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
        //for map
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_LOCATION_CODE:
                if(grantResults.length> 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    //permission granted
                    if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        if (client == null){
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
                return;
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        // Add a marker in Kalabagan and move the camera
        //LatLng kalabagan = new LatLng(23.750988, 90.382416);
        /*
        mMap.addMarker(new MarkerOptions().position(kalabagan).title("Marker in kalabagan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kalabagan));
        mMap.setMinZoomPreference(13);
*/

/*        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,*/
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.74,90.417437)));
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.56,90.40),5));
        }

        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.




    }


/*    public void onClick(View v){

        Object datatranfer[]=new Object[2];
        //GetNearbyPlacesData getNearbyPlacesData =new GetNearbyPlacesData();
        switch (v.getId()) {
            case R.id.button_search_id: {

                EditText tf_location = findViewById(R.id.TF_location_id);
                String location = tf_location.getText().toString();
                Log.d("locat", "location convert in a string");
                List<Address> addressList = null;
                MarkerOptions markerOptions = new MarkerOptions();
                if (!location.equals("")) {
                    Geocoder geocoder = new Geocoder(this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 5);
                        Log.d("adress list", " : "+addressList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < addressList.size(); i++) {

                        Address myAddress = addressList.get(i);
                        LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                        markerOptions.position(latLng);
                        markerOptions.title("MH Search Result");
                        mMap.addMarker(markerOptions);
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    }
                }
            }
            break;
            case R.id.btn_hospitals_id:
                mMap.clear();
                String hospital = "hospital";
                String url=getUrl(latitude,longitude,hospital);
                datatranfer [0]=mMap;
                datatranfer [1]=url;
                getNearbyPlacesData.execute(datatranfer);
                Toast.makeText(MapsActivity.this, "Showing nearby Hospital", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_restutarurant_id:
                mMap.clear();
                String restutarunt = "restutarunt";
                url=getUrl(latitude,longitude,restutarunt);
                datatranfer [0]=mMap;
                datatranfer [1]=url;
                getNearbyPlacesData.execute(datatranfer);
                Toast.makeText(MapsActivity.this, "Showing nearby restutarant", Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_schools_id:
                mMap.clear();
                String school = "school";
                url=getUrl(latitude,longitude,school);
                datatranfer [0]=mMap;
                datatranfer [1]=url;
                getNearbyPlacesData.execute(datatranfer);
                Toast.makeText(MapsActivity.this, "Showing nearby school", Toast.LENGTH_LONG).show();
                break;
            case R.id.for_distance_id:

        }

    }*/

    protected synchronized void buildGoogleApiClient()
    {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        client.connect();
    }

    private String getUrl(double latitude, double longitude,String nearbyPlace){

        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location"+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyCW5g_NWCuKm1BY5VjYRR4xi0wvOxlSQUU");
        Log.d("MapsActivity","url : "+googlePlaceUrl.toString());
        return googlePlaceUrl.toString();
    }

    public static Bitmap createCustomMarker(Context context, @DrawableRes int resource, String _name) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);

        CircleImageView markerImage = (CircleImageView) marker.findViewById(R.id.user_dp);
        markerImage.setImageResource(resource);
        TextView txt_name = (TextView)marker.findViewById(R.id.name);
        txt_name.setText(_name);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }


    @Override
    public void onLocationChanged(Location location) {


        lastlocaton =location;

        if(currentLocationMarker != null){
            currentLocationMarker.remove();

        }
        LatLng latLng =new LatLng(location.getLatitude(),location.getLongitude());
        LatLng latLng1 =new LatLng(23.758560, 90.382507);
        LatLng latLng2 =new LatLng(23.751300, 90.378534);
        LatLng latLng3 =new LatLng(23.751177, 90.390990);
        LatLng latLng4 =new LatLng(23.747531, 90.400173);
        LatLng latLng5 =new LatLng(23.744674, 90.384777);
        location.set(lastlocaton);
        Log.d("LatLng",": "+latLng+lastlocaton);

        MarkerOptions markerOptions =new MarkerOptions();
        MarkerOptions markerOptions1 =new MarkerOptions();
        MarkerOptions markerOptions2 =new MarkerOptions();
        MarkerOptions markerOptions3 =new MarkerOptions();
        MarkerOptions markerOptions4 =new MarkerOptions();
        MarkerOptions markerOptions5 =new MarkerOptions();
        markerOptions5.position(latLng5);
        markerOptions4.position(latLng4);
        markerOptions3.position(latLng3);
        markerOptions2.position(latLng2);
        markerOptions1.position(latLng1);
        markerOptions.position(latLng);
        markerOptions.title("Your Current Location mh");
        markerOptions1.title("User : 1 (Noor)Location");
        markerOptions2.title("User : 2 (Zahid)Location");
        markerOptions3.title("User : 3 (Sahed)Location");
        markerOptions4.title("User : 4 (Sumon)Location");
        markerOptions5.title("User : 5 (Ifty)Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        markerOptions4.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.sumon,"Sumon")));
        markerOptions3.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.sahed,"Sahed")));
        markerOptions2.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.zahid,"Zahid")));
        markerOptions1.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.noor,"Noor")));

        //markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable));
        markerOptions5.icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MapsActivity.this,R.drawable.ifty,"Ifty")));


        currentLocationMarker = mMap.addMarker(markerOptions);
        currentLocationMarker =mMap.addMarker(markerOptions1);
        currentLocationMarker =mMap.addMarker(markerOptions2);
        currentLocationMarker =mMap.addMarker(markerOptions3);
        currentLocationMarker =mMap.addMarker(markerOptions4);
        currentLocationMarker =mMap.addMarker(markerOptions5);
        mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(1500)
                .strokeWidth(0)
                .fillColor(0x66aaaFFF));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(2));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(23.7535483,90.382379),15));

        if(client != null){

            LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        }



    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest =new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, (com.google.android.gms.location.LocationListener) this);
        }



    }

    public boolean checkLocationPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }
            else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_CODE);
            }
            return false;
        }
        else
            return true;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.setDraggable(true);
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

        end_latitude=marker.getPosition().latitude;
        end_longtitude=marker.getPosition().longitude;

    }

}
