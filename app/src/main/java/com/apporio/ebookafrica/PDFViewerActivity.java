package com.apporio.ebookafrica;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.apporio.ebookafrica.epubsamir.FileaName;
import com.joanzapata.pdfview.PDFView;

import java.io.File;

public class PDFViewerActivity extends Activity {

    PDFView  pdfview ;
    TextView book_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);
        pdfview = (PDFView) findViewById(R.id.pdfview);
        book_name = (TextView) findViewById(R.id.book_name);

        book_name.setText("" + FileaName.FileNAME.replace("_" , " "));

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        pdfview.fromFile(new File(FileaName.FilePath))
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }





}
