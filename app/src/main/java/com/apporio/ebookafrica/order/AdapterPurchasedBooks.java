package com.apporio.ebookafrica.order;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View MyView ;
        inflate = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView = inflate.inflate(R.layout.purchased_book_item, parent, false);
        TextView bookname = (TextView) MyView.findViewById(R.id.bookname);
        TextView available_offline = (TextView) MyView.findViewById(R.id.available_offline);
        Button download  = (Button) MyView.findViewById(R.id.download);
        bookname.setText("" + data.get(position).getPurchasedBooksProducts().getName()  +  "  "+data.get(position).getPurchasedBooksProducts().getFileType());
        NetworkImageView imagebook  = (NetworkImageView) MyView.findViewById(R.id.imagebook);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent in = new Intent(con, ConfirmOrder.class);
        in.putExtra("name_key", data.get(position).getPurchasedBooksProducts().getName());
        in.putExtra("product_id", data.get(position).getPurchasedBooksProducts().getProductId());
        in.putExtra("isbn", data.get(position).getPurchasedBooksProducts().getIsbn());
        in.putExtra("image_key", "http://www.e-bookafrica.com/image/"+data.get(position).getPurchasedBooksProducts().getImage());
        in.putExtra("pages_txt", data.get(position).getPurchasedBooksProducts().getPages());
        in.putExtra("hours_txt", data.get(position).getPurchasedBooksProducts().getHours());
        in.putExtra("price", data.get(position).getPurchasedBooksProducts().getPrice());
        in.putExtra("author", "Author Name Not NAPI");
        in.putExtra("manufacturer", data.get(position).getPurchasedBooksProducts().getModel());
        in.putExtra("payment_id", data.get(position).getPurchasedBooksProducts().getOrderProductId());
        in.putExtra("voucher_id" ,"");
                in.putExtra("book_url" ,""+data.get(position).getPurchasedBooksProducts().getDownloadLink());
                in.putExtra("file_type" ,data.get(position).getPurchasedBooksProducts().getFileType());
        con.startActivity(in);

            }
        });











        if(booknames.contains(""+data.get(position).getPurchasedBooksProducts().getName())){
            download.setVisibility(View.GONE);
            available_offline.setVisibility(View.VISIBLE);
        }else{
            download.setVisibility(View.VISIBLE);
            available_offline.setVisibility(View.GONE);
        }

        mImageLoader.get("http://www.e-bookafrica.com/image/" + data.get(position).getPurchasedBooksProducts().getImage(), ImageLoader.getImageListener(imagebook, R.color.icons_8_muted_green_1, R.color.icons_8_muted_yellow));
        imagebook.setImageUrl("http://www.e-bookafrica.com/image/" + data.get(position).getPurchasedBooksProducts().getImage(), mImageLoader);

        return MyView;
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
