package com.example.onzzz.gps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.location.Address;
import android.location.Geocoder;
import android.widget.TextView;
import android.content.Intent;
import android.provider.MediaStore;
import android.net.Uri;
import android.graphics.drawable.Drawable;

import java.io.File;

public class MainActivity extends ActionBarActivity {

    private Button btnCapImage;
    private ImageView imageView;
    private static final int CAM_REQUEST = 1;
    private Button btnShowLocation;
    private TextView myAddress;
    private double latitude;
    private double longitude;

    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.show_location);
        btnCapImage = (Button) findViewById(R.id.cap_image);
        myAddress = (TextView)findViewById(R.id.address);
        imageView = (ImageView) findViewById(R.id.image_view);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);

                if (gps.canGetLocation()) {
                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    getMyLocationAddress();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        btnCapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
    }

    private File getFile(){
        File folder = new File("sdcard/camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        File image_file = new File(folder, "cam_image.jpg");

        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camera_app/cam_image.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }

    public void getMyLocationAddress() {

        Geocoder geocoder= new Geocoder(this, Locale.ENGLISH);

        try {

            //Place your latitude and longitude
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if(addresses != null) {

                Address fetchedAddress = addresses.get(0);
                StringBuilder strAddress = new StringBuilder();

                for(int i=0; i<fetchedAddress.getMaxAddressLineIndex(); i++) {
                    strAddress.append(fetchedAddress.getAddressLine(i)).append("\n");
                }

                myAddress.setText("I am at: " + modifyLocationName(latitude, longitude, strAddress.toString()));
            }

            else
                myAddress.setText("No location found..!");

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Could not get address..!", Toast.LENGTH_LONG).show();
        }
    }

    public String modifyLocationName(double lag, double log, String addr){

        //Modifying Barbecue Area String
        if (lag >= 22.497959 && lag <= 22.499228 && log >= 114.176793 && log <= 114.178166)
            return "Hok Tau Reservoir Barbecue Area";
        if (lag >= 22.485277 && lag <= 22.489747 && log >= 114.241337 && log <= 114.244502)
            return "Bride's Pool Road Barbecue Area-Chung Pui Section";
        if (lag >= 22.501853 && lag <= 22.502795 && log >= 114.237218 && log <= 114.239964)
            return "Bride's Pool Road Barbecue Area-Bride's Pool Section";
        if (lag >= 22.513816 && lag <= 22.515095 && log >= 114.231716 && log <= 114.233095)
            return "Bride's Pool Road Barbecue Area-Wu Kau Tang & Kong Ha Au Section";
        if (lag >= 22.491544 && lag <= 22.495519 && log >= 114.240299 && log <= 114.242412)
            return "Bride's Pool Road Barbecue Area-Chung Mei Section";
        if (lag >= 22.471048 && lag <= 22.471722 && log >= 114.232444 && log <= 114.233163)
            return "Tai Mei Tuk Barbecue Area";
        if (lag >= 22.404617 && lag <= 22.405539 && log >= 114.104709 && log <= 114.106050)
            return "Twisk and Rotary Barbecue Area";
        if (lag >= 22.407310 && lag <= 22.409393 && log >= 114.032667 && log <= 114.035156)
            return "Tai Tong Barbecue Area";
        if (lag >= 22.405950 && lag <= 22.408181 && log >= 113.987451 && log <= 113.989908)
            return "Fu Tei Barbecue Area";
        if (lag >= 22.383347 && lag <= 22.384160 && log >= 114.142677 && log <= 114.143825)
            return "Shing Mun Barbecue Area";
        if (lag >= 22.428781 && lag <= 22.431002 && log >= 114.254881 && log <= 114.259945)
            return "Nai Chung Barbecue Area";
        if (lag >= 22.290981 && lag <= 22.291666 && log >= 114.304124 && log <= 114.305325)
            return "Tai Hang Tun Barbecue Area";
        if (lag >= 22.440688 && lag <= 22.441739 && log >= 114.325665 && log <= 114.327811)
            return "Hau Tong Kai Barbecue Area";
        if ((lag >= 22.456812 && lag <= 22.459588 && log >= 114.323730 && log <= 114.329245) || (lag >= 22.444714 && lag <= 22.448066 && log >= 114.323666 && log <= 114.329223))
            return "Hoi Ha Barbecue Area";
        if (lag >= 22.391994 && lag <= 22.392956 && log >= 114.276522 && log <= 114.277375)
            return "Tai Mong Tsai Barbecue Area";
        if (lag >= 22.436403 && lag <= 22.437345 && log >= 114.333514 && log <= 114.335295)
            return "Tai Tan Barbecue Area";
        if (lag >= 22.397069 && lag <= 22.399594 && log >= 114.318228 && log <= 114.319617)
            return "Pak Tam Chung Barbecue Area";
        if (lag >= 22.404422 && lag <= 22.406009 && log >= 114.278104 && log <= 114.280314)
            return "Sai Sha Road Barbecue Area";
        if ((lag >= 22.398111 && lag <= 22.398869 && log >= 114.325425 && log <= 114.326841) || (lag >= 22.395775 && lag <= 22.396330 && log >= 114.328408 && log <= 114.329459))
            return "Tsak Yue Wu Barbecue Area";
        if (lag >= 22.434741 && lag <= 22.435137 && log >= 114.336232 && log <= 114.336688)
            return "Wong Shek Barbecue Area";
        if (lag >= 22.252121 && lag <= 22.253501 && log >= 113.985213 && log <= 113.986543)
            return "Nam Shan and Pak Fu Tin Barbecue Area";
        if (lag >= 22.239649 && lag <= 22.242370 && log >= 113.944713 && log <= 113.950163)
            return "Cheung Sha Barbecue Area";
        if (lag >= 22.235994 && lag <= 22.238656 && log >= 113.962973 && log <= 113.969303)
            return "San Shek Wan Barbecue Area";
        if (lag >= 22.226573 && lag <= 22.227020 && log >= 113.933537 && log <= 113.934133)
            return "Tong Fuk Barbecue Area";


        //Modifying Beach String
        if (lag >= 22.242801 && lag <= 22.245835 && log >= 114.185915 && log <= 114.188399)
            return "Deep Water Bay Beach";
        if (lag >= 22.234683 && lag <= 22.238189 && log >= 114.194017 && log <= 114.198126)
            return "Repulse Bay Beach";
        if (lag >= 22.229303 && lag <= 22.230234 && log >= 114.197818 && log <= 114.198381)
            return "Middle Bay Beach";
        if (lag >= 22.216951 && lag <= 22.218525 && log >= 114.201883 && log <= 114.202307)
            return "Chung Hom Kok Beach";
        if (addr.equals("Wong Ma Kok Path"))
            return "St. Stephen's Beach";
        if (addr.contains("Stanley Link Rd") || addr.contains("Stanley Beach Rd"))
            if (addr.contains("7 Stanley Beach Rd"))
                return "Hairpin Beach";
            else
                return "Stanley Main Beach";
        if (addr.contains("Turtle Cove"))
            return "Turtle Cove Beach";
        if (lag >= 22.227520 && lag <= 22.229785 && log >= 114.249612 && log <= 114.252627)
            return "Shek O Beach";
        if (addr.contains("Big Wave Bay"))
            return "Big Wave Bay Beach";
        if (lag >= 22.218339 && lag <= 22.219059 && log >= 114.118926 && log <= 114.120122)
            return "Hung Shing Yeh Beach";
        if (lag >= 22.204380 && lag <= 22.204599 && log >= 114.122548 && log <= 114.123130)
            return "Lo So Shing Beach";
        if (lag >= 22.206855 && lag <= 22.207781 && log >= 114.033362 && log <= 114.035346)
            return "Kwun Yam Beach";
        if (lag >= 22.207923 && lag <= 22.211171 && log >= 114.029462 && log <= 114.032460)
            return "Cheung Chau Tung Wan Beach";
        if (lag >= 22.267465 && lag <= 22.272638 && log >= 113.998234 && log <= 114.003567)
            return "Silver Mine Bay Beach";
        if (lag >= 22.237110 && lag <= 22.240784 && log >= 113.972870 && log <= 113.981571)
            return "Pui O Beach";
        if (lag >= 22.233044 && lag <= 22.234454 && log >= 113.952569 && log <= 113.957623)
            return "Lower Cheung Sha Beach";
        if (lag >= 22.229235 && lag <= 22.234379 && log >= 113.939915 && log <= 113.952446)
            return "Upper Cheung Sha Beach";
        if (lag >= 22.226556 && lag <= 22.228890 && log >= 113.933912 && log <= 113.937377)
            return "Tong Fuk Beach";
        if (lag >= 22.357170 && lag <= 22.359621 && log >= 114.266898 && log <= 114.271962)
            return "Trio Beach";
        if (lag >= 22.367854 && lag <= 22.368489 && log >= 114.288713 && log <= 114.289402)
            return "Kiu Tsui Beach";
        if (lag >= 22.355805 && lag <= 22.357050 && log >= 114.295536 && log <= 114.296263)
            return "Hap Mun Bay Beach";
        if (lag >= 22.322229 && lag <= 22.323187 && log >= 114.271785 && log <= 114.272949)
            return "Silverstrand Beach";
        if (lag >= 22.290744 && lag <= 22.291414 && log >= 114.290667 && log <= 114.291675)
            return "Clear Water Bay First Beach";
        if (lag >= 22.287420 && lag <= 22.288621 && log >= 114.287334 && log <= 114.287978)
            return "Clear Water Bay Second Beach";
        if (lag >= 22.364584 && lag <= 22.365285 && log >= 114.055624 && log <= 114.056767)
            return "Anglers' Beach";
        if (lag >= 22.363861 && lag <= 22.364064 && log >= 114.069124 && log <= 114.069437)
            return "Gemini Beaches";
        if (lag >= 22.364472 && lag <= 22.365026 && log >= 114.070471 && log <= 114.071587)
            return "Hoi Mei Wan Beach";
        if (lag >= 22.367347 && lag <= 22.367732 && log >= 114.074483 && log <= 114.075478)
            return "Casam Beach";
        if (lag >= 22.367484 && lag <= 22.368107 && log >= 114.075119 && log <= 114.076761)
            return "Lido Beach";
        if (lag >= 22.368634 && lag <= 22.369225 && log >= 114.079051 && log <= 114.080196)
            return "Ting Kau Beach";
        if (lag >= 22.367292 && lag <= 22.367516 && log >= 114.085858 && log <= 114.087075)
            return "Approach Beach";
        if (lag >= 22.348991 && lag <= 22.351497 && log >= 114.061504 && log <= 114.061911)
            return "Ma Wan Tung Wan Beach";
        if (lag >= 22.371456 && lag <= 22.373458 && log >= 113.954155 && log <= 113.959504)
            return "Butterfly Beach";
        if (lag >= 22.378297 && lag <= 22.379814 && log >= 113.978941 && log <= 113.980953)
            return "Castle Peak Beach";
        if (lag >= 22.375851 && lag <= 22.376865 && log >= 113.980945 && log <= 113.982592)
            return "Kadoorie Beach";
        if (lag >= 22.373896 && lag <= 22.376020 && log >= 113.982593 && log <= 113.985939)
            return "Cafeteria Old Beach";
        if (lag >= 22.370652 && lag <= 22.374522 && log >= 113.985940 && log <= 113.988740)
            return "Cafeteria New Beach";
        if (lag >= 22.369285 && lag <= 22.371138 && log >= 113.988741 && log <= 113.989571)
            return "Golden Beach";
        return addr;
    }
}
