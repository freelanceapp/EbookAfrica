package com.apporio.ebookafrica.order;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.CustomVolleyRequestQueue;
import com.apporio.ebookafrica.pojo.purchasedProductPojo.PurchasedBooksOrder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by spinnosolutions on 5/18/16.
 */
public class AdapterPurchasedBooks extends BaseAdapter{
    Context con ;
    LayoutInflater inflate;
    ImageLoader mImageLoader;
    List<PurchasedBooksOrder>  data ;
    ArrayList<String> booknames = new ArrayList<>();
    String[] theNamesOfFiles ;

    public AdapterPurchasedBooks(Context con ,  List<PurchasedBooksOrder>  data ){

        this.con = con ;
        this.data =data ;
        mImageLoader = CustomVolleyRequestQueue.getInstance(con).getImageLoader();


        File[] filelist = getDataFolder(con).listFiles();
        theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            if(!filelist[i].getName().equals("sample_book.epub.epub")){
                booknames.add(""+filelist[i].getName().replace(".epub" , ""));
            }
        }
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View MyView ;
        inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView = inflate.inflate(R.layout.purchased_book_item, parent, false);
        TextView bookname = (TextView) MyView.findViewById(R.id.bookname);
        TextView available_offline = (TextView) MyView.findViewById(R.id.available_offline);
        Button download  = (Button) MyView.findViewById(R.id.download);
        bookname.setText("" + data.get(position).getPurchasedBooksProducts().getName());


        if(booknames.contains(""+data.get(position).getPurchasedBooksProducts().getName())){
            download.setVisibility(View.GONE);
            available_offline.setVisibility(View.VISIBLE);
        }else{
            download.setVisibility(View.VISIBLE);
            available_offline.setVisibility(View.GONE);
        }

//        mImageLoader.get("" + data.get(position).getPurchasedBooksOrders().get(position).getPurchasedBooksProducts().get, ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
//        imagebook.setImageUrl(""+data.get(position).getImage(), mImageLoader);

        return MyView;
    }





    public File getDataFolder(Context context) {
        File dataDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dataDir = new File(Environment.getExternalStorageDirectory(), "ebbok_data");
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
