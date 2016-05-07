package com.apporio.ebookafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.apporio.apporiologin.LoginEvent;

import de.greenrobot.event.EventBus;

public class SplashScreen extends AppCompatActivity {


    private EventBus bus = EventBus.getDefault();

    ImageView banner ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        bus.register(this);


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




//        if(new AppOrioLoginPrefrences(SplashScreen.this).getLoginStatus() == 0){
//            Intent in  = new Intent(SplashScreen.this , AppOrioLoginScreen.class);
//            in.putExtra("apporio_login_url" ," http://176.32.230.48/kalthegeek.com/kalthegeek.com/ebook/api/api.php?func=login");
//            in.putExtra("apporio_sign_url" , "http://176.32.230.48/kalthegeek.com/kalthegeek.com/ebook/api/api.php?func=registration");
//            startActivity(in);
//        }else {
//            startActivity(new Intent(SplashScreen.this  , MainActivity.class));
//        }
    }




    public void onEvent(LoginEvent Value){
        Toast.makeText(SplashScreen.this , "Response is " +Value.LoginEvent() ,Toast.LENGTH_SHORT).show();

    }







    @Override
    protected void onDestroy() {
        bus.unregister(this);
        super.onDestroy();
    }
}
