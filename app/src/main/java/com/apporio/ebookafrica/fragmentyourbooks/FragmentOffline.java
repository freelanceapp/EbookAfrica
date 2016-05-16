package com.apporio.ebookafrica.fragmentyourbooks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.database.PurchasedProductManager;
import com.apporio.ebookafrica.epubsamir.FileaName;
import com.apporio.ebookafrica.epubsamir.MainActivityEPUBSamir;
import com.apporio.ebookafrica.logger.Logger;

import java.io.File;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class FragmentOffline extends Fragment {


    ListView list ;
    String[] theNamesOfFiles ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_offline, container, false);


        list = (ListView) v.findViewById(R.id.list);


        File[] filelist = getDataFolder(getActivity()).listFiles();
        theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            theNamesOfFiles[i] = filelist[i].getName();
        }
        list.setAdapter(new AdapterOfflineBookList(getActivity(), theNamesOfFiles));



        for(int i = 0 ; i< theNamesOfFiles.length ; i++){

            Logger.d("path name " +getDataFolder(getActivity())+"/"+theNamesOfFiles[i]);
        }



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FileaName.FilePath = ""+getDataFolder(getActivity())+"/"+theNamesOfFiles[position];
                FileaName.FileNAME = ""+theNamesOfFiles[position];
                startActivity(new Intent(getActivity() , MainActivityEPUBSamir.class));
            }
        });






        return v;
    }





    public static FragmentOffline newInstance(String text) {
        FragmentOffline f = new FragmentOffline();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
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
