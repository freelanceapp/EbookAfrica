package com.apporio.ebookafrica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.apporio.ebookafrica.logger.Logger;

import java.io.File;

public class SplashScreen extends AppCompatActivity {




    ImageView banner ;

    SharedPreferences pref_directory;
    public SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Logger.d("Script Sending in Confirm Order API" + "{\"language_id\":1,\"coupon\":\"\",\"voucher\": \"+\"\\\"\"+VOUCHERID +\"\\\"\"+\",\\\"customer_id\\\":\\\"\"+ sm.getUserDetails().get(SessionManager.CUSTOMER_ID) +\"\\\",\\\"products\\\":[{\\\"product_id\\\":\"+product_idd+\",\"+ \"\\\"quantity\\\":1}],\\\"language_id\\\":1,\\\"payment_address\\\":{\\\"address_id\\\":14,\\\"payment_firstname\\\":\\\"\"+sm.getUserDetails().get(SessionManager.FIRST_NAME)+\"\\\",\\\"payment_lastname\\\":\\\"\"+sm.getUserDetails().get(SessionManager.LAST_NAME)+\"\\\",\\\"payment_company\\\":\\\"EbookAfica\\\",\\\"payment_address_1\\\":\\\"Gurgaon\\\",\\\"payment_address_2\\\":\\\"\\\",\\\"payment_city\\\":\\\"Gurgaon\\\",\\\"payment_postcode\\\":\\\"122001\\\",\\\"payment_country\\\":\\\"India\\\",\\\"payment_country_id\\\":\\\"99\\\",\\\"payment_zone\\\":\\\"Haryana\\\",\\\"payment_zone_id\\\":\\\"1486\\\",\\\"payment_telephone\\\":\\\"\"+sm.getUserDetails().get(SessionManager.TELEPHONE)+\"\\\",\\\"payment_email\\\":\\\"\"+ sm.getUserDetails().get(SessionManager.EMAIL)+\"\"},\"payment_method\":{\"title\":\"paypal\",\"code\": \"cod\",\"terms\": \"\",\"sort_order\": \"5\"},\"shipping_method\":{\"title\":\"Flat Rate Shipping\",\"code\": \"flat\",\"cost\": \"5.00\",\"tax_class_id\": \"9\",\"sort_order\": \"5\"}}");


        pref_directory = SplashScreen.this.getSharedPreferences("directory_prefrence", PRIVATE_MODE);
        editor = pref_directory.edit();


        if(pref_directory.getBoolean("directory_removed_first_time",false)==false){
            Logger.d("Result for deleting directory "+deleteDir(""+deleteNon_EmptyDir(new File("" + getDataFolder(SplashScreen.this)))));
            editor.putBoolean("directory_removed_first_time", true);
            editor.commit();
        }






        banner = (ImageView) findViewById(R.id.banner);
        banner.setImageResource(R.drawable.top_books_banner);




        Thread spth = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(3000);
                    }

                } catch (InterruptedException e) {
                    // TODO: handle exception
                }

                Intent ints =new Intent (SplashScreen.this,MainActivity.class);
                startActivity(ints);
                finish();
            }
        };

        spth.start();

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public boolean deleteDir(String dir_path) {

        File del = new File(""+dir_path);
         Logger.d("Deleting this directory " +del);
        return del.delete();
    }



    public static boolean deleteNon_EmptyDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteNon_EmptyDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }





    public File getDataFolder(Context context) {
        File dataDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dataDir = new File(Environment.getExternalStorageDirectory(), "ebook_data");
            if(!dataDir.isDirectory()) {
                dataDir.mkdirs();
            }
        }

        if(!dataDir.isDirectory()) {
            dataDir = context.getFilesDir();
        }

        return dataDir;
    }





}
