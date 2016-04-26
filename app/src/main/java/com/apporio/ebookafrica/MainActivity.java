package com.apporio.ebookafrica;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.apporio.ebookafrica.categoryfragment.FragmentCategory;
import com.apporio.ebookafrica.fragmentyourbooks.FragmentYourBooksMain;
import com.apporio.ebookafrica.homefragment.FragmentHome;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction ft ;



    private MaterialSearchView searchView;
    BottomNavigationView bottomNavigationView;

    public static MainActivity mainActivity  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = this ;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(false);
        searchView.setCursorDrawable(R.drawable.color_cursor_white);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
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
                ("Categories",  ContextCompat.getColor(this,R.color.icons_8_muted_green_1), R.drawable.book_category_icon);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Your Books",  ContextCompat.getColor(this,R.color.icons_8_muted_green_1), R.drawable.ic_books_library);
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
                        setfragmentinContainer(new FragmentYourBooksMain(), "" + R.string.fragment_your_book , 1);



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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
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



}
