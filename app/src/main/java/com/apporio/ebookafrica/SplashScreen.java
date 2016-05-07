package com.apporio.ebookafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {




    ImageView banner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


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
}
