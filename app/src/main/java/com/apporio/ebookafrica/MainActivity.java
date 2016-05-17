package com.apporio.ebookafrica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apporio.apporiologin.AppOrioLoginScreen;
import com.apporio.apporiologin.ApporioSignUpActivity;
import com.apporio.apporiologin.LoginEvent;
import com.apporio.ebookafrica.categoryfragment.FragmentCategory;
import com.apporio.ebookafrica.constants.SessionManager;
import com.apporio.ebookafrica.constants.UrlsEbookAfrics;
import com.apporio.ebookafrica.fragmentyourbooks.FragmentYourBooksMain;
import com.apporio.ebookafrica.homefragment.FragmentHome;
import com.apporio.ebookafrica.logger.Logger;
import com.apporio.ebookafrica.pojo.LoginSuccess;
import com.apporio.ebookafrica.pojo.LoginUnSuccess;
import com.apporio.ebookafrica.pojo.ResponseChecker;
import com.apporio.ebookafrica.pojo.Search;
import com.apporio.ebookafrica.specificbook.SpecificBookActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction ft ;



    private MaterialSearchView searchView;
    BottomNavigationView bottomNavigationView;

    public static MainActivity mainActivity  ;
    private static RequestQueue queue ;
    private static StringRequest sr;

    ArrayList<String> search_name  = new ArrayList<>();
    ArrayList<String> search_id  = new ArrayList<>();
    SessionManager sm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = com.apporio.apporiologin.VolleySingleton.getInstance(MainActivity.this).getRequestQueue();
        sm = new SessionManager(MainActivity.this);
        EventBus.getDefault().register(this);

        mainActivity = this ;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSubmitOnClick(true);


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, "query   " + query, Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, SpecificBookActivity.class);
                int l = search_name.indexOf(query);
                in.putExtra("product_id" , "" + search_id.get(l));
                in.putExtra("product_name" , "" +query);
                startActivity(in);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                searchExecution(newText);
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });













///////////////////////////////////////// Bottom Navigation

        if (bottomNavigationView != null){
            bottomNavigationView.isWithText(true);
            bottomNavigationView.disableViewPagerSlide();
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(getResources().getColor(R.color.icons_8_muted_green_1));
        }
        bottomNavigationView.disableShadow();
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Home", ContextCompat.getColor(this, R.color.icons_8_muted_green_1), R.drawable.ic_home);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Category",  ContextCompat.getColor(this,R.color.icons_8_muted_green_1), R.drawable.book_category_icon);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Offline",  ContextCompat.getColor(this,R.color.icons_8_muted_green_1), R.drawable.ic_books_library);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("Profile",  ContextCompat.getColor(this,R.color.icons_8_muted_green_1), R.drawable.ic_profile);


        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                        setfragmentinContainer(new FragmentHome(), "" + R.string.fragment_home, 1);
                        break;
                    case 1:
                        setfragmentinContainer(new FragmentCategory(), "" + R.string.fragment_category , 1);
                        break;
                    case 2:
                        if(sm.isLoggedIn()){
                            setfragmentinContainer(new FragmentYourBooksMain(), "" + R.string.fragment_your_book , 1);
                        }else {
                            Intent in = new Intent(MainActivity.this, AppOrioLoginScreen.class);
                            in.putExtra("apporio_login_url", UrlsEbookAfrics.Login);
                            in.putExtra("apporio_sign_url", UrlsEbookAfrics.SighUp);
                            startActivity(in);
                        }
                        break;
                    case 3:

                        break;

                }
            }
        });






        setfragmentinContainer(new FragmentHome(), "" + R.string.fragment_home, 1);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
//            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            if (matches != null && matches.size() > 0) {
//                String searchWrd = matches.get(0);
//                if (!TextUtils.isEmpty(searchWrd)) {
//                    searchView.setQuery(searchWrd, false);
//                    Toast.makeText(MainActivity.this     , ""+searchWrd  ,Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }







    public void searchExecution (String s){
        if(sr != null){
            queue.cancelAll(sr);
        }

        String url = UrlsEbookAfrics.search +s;
        url=url.replace(" ","%20");
        Logger.d("Executing Search API   " + url);


        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                ResponseChecker rcheck = new ResponseChecker();
                rcheck = gson.fromJson(response, ResponseChecker.class);
                if(rcheck.getStatus().equals("success")){
                    Search search = new Search();
                    search = gson.fromJson(response, Search.class);

                    search_id.clear();
                    search_name.clear();

                    for(int i  = 0 ; i<search.getProductSearches().size() ;i++){
                        search_id.add(""+search.getProductSearches().get(i).getProductId());
                        search_name.add(""+search.getProductSearches().get(i).getName());
                    }

                    String[] productarray = new String[search_name.size()];
                    searchView.setSuggestions(search_name.toArray(productarray));

                }else {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Logger.d(""+error);
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);

    }





    private void    setfragmentinContainer(Fragment fragment , String fragment_name , int colour ) {
        if(colour == 1){
        }else if(colour == 2){
        }else if(colour == 3 ){
        }

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_container
                , fragment);
        ft.commit();


    }






    public void onEvent(LoginEvent Value){
        if(Value.LoginDeterminer() == 0 ){
            Toast.makeText(MainActivity.this, "" + Value.LoginEvent(), Toast.LENGTH_SHORT).show();
        }else{
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            ResponseChecker rcheck = new ResponseChecker();
            rcheck = gson.fromJson(Value.LoginEvent(), ResponseChecker.class);
            if(rcheck.getStatus().equals("success")){
                LoginSuccess l_success = new LoginSuccess();
                l_success = gson.fromJson(Value.LoginEvent(), LoginSuccess.class);
                if(ApporioSignUpActivity.Activity_Is_Open){
                    Toast.makeText(MainActivity.this, "Welcome" +l_success.getCustomer().getFirstname()+" "+l_success.getCustomer().getLastname(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Welcome" +l_success.getCustomer().getFirstname()+" "+l_success.getCustomer().getLastname(), Toast.LENGTH_SHORT).show();
                }
                sm.createLoginSession("" + l_success.getCustomer().getCustomerId(),
                        "" + l_success.getCustomer().getFirstname(),
                        "" + l_success.getCustomer().getLastname(),
                        "" + l_success.getCustomer().getEmail(),
                        "" + l_success.getCustomer().getTelephone(),
                        "" + l_success.getCustomer().getFax(),
                        "" + l_success.getCustomer().getNewsletter(),
                        "" + l_success.getCustomer().getWishlist(),
                        "" + l_success.getCustomer().getCart(),
                        "" + l_success.getCustomer().getTotal());

                if(ApporioSignUpActivity.Activity_Is_Open){
                    ApporioSignUpActivity.activity.finish();
                    AppOrioLoginScreen.activity.finish();
                }else {
                    AppOrioLoginScreen.activity.finish();
                }
                AppOrioLoginScreen.activity.finish();

            }else if (rcheck.getStatus().equals("failed")){
                LoginUnSuccess l_unsuccess = new LoginUnSuccess();
                l_unsuccess = gson.fromJson(Value.LoginEvent(), LoginUnSuccess.class);
                Toast.makeText(MainActivity.this, "" +l_unsuccess.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }







}
