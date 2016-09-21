package com.apporio.ebookafrica.order;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.apporio.apporiologin.VolleySingleton;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.constants.FileTypes;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.database.PurchasedProductManager;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.specificbook.SpecificBookActivity;
import com.daasuu.ahp.AnimateHorizontalProgressBar;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAlreadyPurchasedBooksActivity extends Activity {

    private static RequestQueue queue ;
    SessionManager sm  ;
    Button download   , openbook;


    TextView capturedownloadlink  , book_name  ,author_name ;
    NetworkImageView imagebook ;
    ImageLoader mImageLoader;
    String file_url , BookNAME;

    String BOOKIMAGE  , BOOKNAME = "" ,BOOKURL, BOOKID , ISBN  ,PAGES , HOURS ,PRICE , AUTHOR , MANUFACTURE , PAYMENTID , VOUCHERID , IMAGEBITMAP , FILETYPE;
    PurchasedProductManager psm ;

    AnimateHorizontalProgressBar animate_progress_bar ;
    TextView percentage_text ;
    Button cancel ;
    DownloadFileFromURL epubdowloadtas ;
    File cacheFile ;


    ImageView trialimage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_already_purchased_books);
        sm = new SessionManager(DownloadAlreadyPurchasedBooksActivity.this);
        psm = new PurchasedProductManager(DownloadAlreadyPurchasedBooksActivity.this);
        epubdowloadtas = new DownloadFileFromURL();



        mImageLoader = CustomVolleyRequestQueue.getInstance(DownloadAlreadyPurchasedBooksActivity.this).getImageLoader();

        capturedownloadlink = (TextView) findViewById(R.id.capturedownloadlink);
        openbook = (Button) findViewById(R.id.openbook);
        author_name = (TextView) findViewById(R.id.author_name);
        book_name = (TextView) findViewById(R.id.book_name);
        download = (Button) findViewById(R.id.download);
        imagebook = (NetworkImageView)findViewById(R.id.image_book);
        animate_progress_bar = (AnimateHorizontalProgressBar) findViewById(R.id.animate_progress_bar);
        percentage_text = (TextView) findViewById(R.id.percentage_text);
        cancel = (Button) findViewById(R.id.cancel);
        trialimage = (ImageView) findViewById(R.id.trialimage);
        queue = VolleySingleton.getInstance(DownloadAlreadyPurchasedBooksActivity.this).getRequestQueue();


        mImageLoader.get(getIntent().getExtras().getString("image_key"), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
        imagebook.setImageUrl(getIntent().getExtras().getString("image_key"), mImageLoader);

        book_name.setText("" + getIntent().getExtras().getString("name_key"));
        author_name.setText(""+getIntent().getExtras().getString("author"));


        BOOKNAME = getIntent().getExtras().getString("name_key");
        BOOKID = getIntent().getExtras().getString("product_id");
        ISBN = getIntent().getExtras().getString("isbn");
        BOOKIMAGE = getIntent().getExtras().getString("image_key");
        PAGES = getIntent().getExtras().getString("pages_txt");
        HOURS = getIntent().getExtras().getString("hours_txt");
        PRICE = getIntent().getExtras().getString("price");
        AUTHOR = getIntent().getExtras().getString("author");
        MANUFACTURE = getIntent().getExtras().getString("manufacturer");
        PAYMENTID = getIntent().getExtras().getString("payment_id");
        VOUCHERID = getIntent().getExtras().getString("voucher_id");
        BOOKURL = getIntent().getExtras().getString("book_url");
        FILETYPE = getIntent().getExtras().getString("file_type");




        BitmapDrawable drawable = (BitmapDrawable)imagebook.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        String encodedimage = encodeToBase64(bitmap, Bitmap.CompressFormat.JPEG, 100);
        IMAGEBITMAP = encodedimage ;
        Bitmap decodedimage  = decodeBase64(encodedimage);
        trialimage.setImageBitmap(decodedimage);







        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cacendownload();
            }
        });


        animate_progress_bar.setMax(100);
        epubdowloadtas.execute("" + BOOKURL);
    }












    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            animate_progress_bar.setProgress(0);
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                File cacheDir = getDataFolder(DownloadAlreadyPurchasedBooksActivity.this);
                String newbookname = BookNAME.replace(" ", "_");
                if(FILETYPE.equals(""+ FileTypes.EPUB_FILE_TYPE)){
                    cacheFile= new File(cacheDir, newbookname+".epub");
                }if(FILETYPE.equals(""+FileTypes.PDF_FILE_TYPE)){
                    cacheFile= new File(cacheDir, newbookname+".pdf");
                }

                Logger.d("file path " + cacheFile);
                FileOutputStream output = new FileOutputStream(cacheFile);
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));
                    // writing data to file
                    output.write(data, 0, count);
                }
                // flushing output
                output.flush();
                // closing streams
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
            return null;
        }





        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            percentage_text.setText(""+progress[0] + " %");
            animate_progress_bar.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String file_url) {
//            FileaName.FilePath = ""+getDataFolder(ConfirmOrder.this)+"/"+BookNAME.replace(" " , "_")+".epub";
//            FileaName.FileNAME = ""+BookNAME.replace(" " , "_"+".epub");
//            startActivity(new Intent(ConfirmOrder.this, MainActivityEPUBSamir.class));
            Toast.makeText(DownloadAlreadyPurchasedBooksActivity.this ,"File Downloaded Successfully  , now available in offline section " ,Toast.LENGTH_LONG).show();
            savaBookLocaly();
            finish();
            SpecificBookActivity.activity.finish();
        }
    }


    private void savaBookLocaly() {

        psm.addtoPurchasedProductTable(BOOKNAME, BOOKID, ISBN, BOOKIMAGE, PAGES, HOURS, PRICE, AUTHOR, MANUFACTURE , IMAGEBITMAP, FILETYPE);
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



    public void cacendownload(){
        if(epubdowloadtas != null){
            epubdowloadtas.cancel(true);
            File file_to_be_deleted  = new File(""+cacheFile );
            file_to_be_deleted.delete();
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cacendownload();

    }






    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }



}
