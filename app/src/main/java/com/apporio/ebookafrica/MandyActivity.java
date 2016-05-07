package com.apporio.ebookafrica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MandyActivity extends AppCompatActivity {


    ImageView  imafge  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandy);

        imafge = (ImageView) findViewById(R.id.imafge);




        imafge.setImageResource(R.drawable.mandy);



//        Glide.with(MandyActivity.this)
//                .load("http://media.merchantcircle.com/43551521/0x550-2_full.jpeg")
//                .centerCrop()
//                .thumbnail(0.1f)
//                .placeholder(R.drawable.background)
//                .error(R.drawable.cover_one)
//                .into(imafge);
//
//
//

    }


}
