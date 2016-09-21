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

import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.ebookafrica.PDFViewerActivity;
import com.apporio.ebookafrica.R;
import com.apporio.ebookafrica.constants.FragmentStatus;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.epubsamir.FileaName;
import com.apporio.ebookafrica.epubsamir.MainActivityEPUBSamir;
import com.apporio.ebookafrica.logger.Logger;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by spinnosolutions on 4/9/16.
 */
public class FragmentOffline extends Fragment {


    ListView list ;
    String[] theNamesOfFiles ;
    View loginlayout;
    SessionManager sm ;

    ArrayList<String> booknames = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_offline, container, false);
        FragmentStatus.GetOpenfragment = "FragmentOffline";
        sm = new SessionManager(getActivity());

        list = (ListView) v.findViewById(R.id.list);
        loginlayout = v.findViewById(R.id.loginlayout);


        File[] filelist = getDataFolder(getActivity()).listFiles();
        theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
            if(!filelist[i].getName().equals("sample_book.epub.epub")){
                booknames.add(""+filelist[i].getName());
            }
        }

        Logger.d("Total numbers of books  "+booknames.size());
        for(int i = 0 ; i < booknames.size() ; i++){
            Logger.d("name of books in offline fragment "+booknames.get(i));
        }



        if(sm.isLoggedIn()){
            loginlayout.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            list.setAdapter(new AdapterOfflineBookList(getActivity(), booknames));
        }else {
            loginlayout.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }







        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileaName.FilePath = "" + getDataFolder(getActivity()) + "/" + booknames.get(position).replace(" ", "_");
                FileaName.FileNAME = "" + booknames.get(position).replace(" ", "_");
                if(FileaName.FileNAME.contains(".pdf")){
                    startActivity(new Intent(getActivity(), PDFViewerActivity.class));
                }if(FileaName.FileNAME.contains(".epub")){
                    startActivity(new Intent(getActivity(), MainActivityEPUBSamir.class));
                }
            }
        });





        v.findViewById(R.id.loginlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AppOrioLoginScreen.class);
                in.putExtra("apporio_login_url", UrlsEbookAfrics.Login);
                in.putExtra("apporio_sign_url", UrlsEbookAfrics.SighUp);
                startActivity(in);
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
