package com.apporio.ebookafrica.lalit;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.ebookafrica.R;
import com.skytree.epub.Highlight;
import com.skytree.epub.Highlights;
import com.skytree.epub.NavPoint;
import com.skytree.epub.NavPoints;
import com.skytree.epub.PageInformation;
import com.skytree.epub.PageMovedListener;
import com.skytree.epub.PageTransition;
import com.skytree.epub.PagingInformation;
import com.skytree.epub.PagingListener;
import com.skytree.epub.Parallel;
import com.skytree.epub.ReflowableControl;
import com.skytree.epub.SearchResult;
import com.skytree.epub.Setting;
import com.skytree.epub.SkyProvider;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Stack;




/**
 * Created by admin on 4/1/2016.
 */

@SuppressWarnings("ALL")
public class BookViewActivity extends Activity {

    ReflowableControl rv;
    LinearLayout ePubView;
    Button debugButton1;
    Button markButton;

    int textsize;
    ImageButton rotationButton;
    ImageButton listButton;
    ImageButton fontButton;
    ImageButton searchButton;
    ImageButton restoreButton; // 임시
    Rect bookmarkRect;
    Rect bookmarkedRect;

    boolean isRotationLocked;

    TextView titleLabel;
    TextView authorLabel;
    TextView pageIndexLabel;
    TextView secondaryIndexLabel;

    SeekBar seekBar;
    SeekBar.OnSeekBarChangeListener seekListener;

    TextView seekLabel;


    Button highlightMenuButton;
    Button noteMenuButton;
    Rect boxFrame;


    ImageButton colorButtonInHighlightBox;
    ImageButton trashButtonInHighlightBox;
    ImageButton noteButtonInHighlightBox;
    ImageButton shareButtonInHighlightBox;

    EditText noteEditor;
    int noteBoxWidth;
    int noteBoxHeight;


    EditText searchEditor;
    ScrollView searchScrollView;
    LinearLayout searchResultView;

    SeekBar brightBar;
    Button increaseButton;
    Button decreaseButton;
    String fontNames[] = {"Book Fonts","Sans Serif","Serif","Monospace"};
    LinearLayout fontListView;


    Button contentListButton;
    Button bookmarkListButton;
    Button highlightListButton;
    ScrollView listScrollView;
    LinearLayout listView;
    Button listTopButton;

    ImageButton playAndPauseButton;
    ImageButton stopButton;
    ImageButton prevButton;
    ImageButton nextButton;

    Stack<Double> positionStack = new Stack<Double>();

    ArrayList<SearchResult> searchResults = new ArrayList<SearchResult>();

    boolean isBoxesShown;



    Button outsideButton;

    String fileName;
    String author;
    String title;

    ProgressBar progressBar;
    View pagingView;


    Highlight currentHighlight;

    boolean isControlsShown = true;

    int bookCode;

    Parallel currentParallel;
    boolean autoStartPlayingWhenNewPagesLoaded = true;
    boolean autoMoveChapterWhenParallesFinished = true;
    boolean isAutoPlaying = true;
    boolean isPageTurnedByMediaOverlay = false;

    boolean isDoublePagedForLandscape;
    boolean isGlobalPagination=true;

    boolean isRTL =false;
    boolean isVerticalWriting = false;

    final private String TAG = "EPub";
    Highlights highlights;
    ArrayList <PagingInformation> pagings = new ArrayList<PagingInformation>();
    int temp = 20;

    Bitmap pagesStack,pagesCenter;
    BitmapDrawable pgsDrawable,pgcDrawable;

    boolean isFullScreenForNexus = true;

    ArrayList<Theme> themes = new ArrayList<Theme>();
    int themeIndex = -1;

    ArrayList<String> chapter_titles=new ArrayList<String>();
    ArrayList<Integer> chapter_index=new ArrayList<Integer>();
    int pi;
    float pageno=0;
    double pagePositionInBook = -1;

    NavPoint targetNavPoint = null;


    public int getDensityDPI() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int density = metrics.densityDpi;
        return density;
    }

    public void reportMetrics() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Log.e("densityDPI",""+metrics.densityDpi);
        Log.e("density" ,""+ metrics.density);
        Log.e("real width  pixels" ,""+ metrics.widthPixels);
        Log.e("real height pixels","" + metrics.heightPixels);
        Log.e("inch for width ","" + metrics.widthPixels / metrics.densityDpi);
        Log.e("inch for height","" + metrics.heightPixels / metrics.densityDpi);
    }

    public void reportFiles(String path) {
        Log.e("EPub", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.e("EPub", "Size: " + file.length);
        for (int i=0; i < file.length; i++)
        {
            Log.e("EPub", "FileName:" + file[i].getName());
        }
    }

    public boolean isHighDensityPhone() {	// if HIGH density (not XHIGH) phone like Galaxy S2, retuns true;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int p0 = metrics.heightPixels;
        int p1 = metrics.widthPixels;
        int max = Math.max(p0, p1);
        if (metrics.densityDpi==240 && max==800) {
            return true;
        }else {
            return false;
        }
    }


    public int getPS(float dip) {
        float density = this.getDensityDPI();
        float factor = (float)density/240.f;
        int px = (int)(dip*factor);
        return px;
    }

    public int getPSFromDP(float dps) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, metrics);
        return (int)pixels;
    }

    public int getPXFromLeft(float dip) {
        int ps = this.getPS(dip);
        return ps;
    }

    public int getPXFromRight(float dip) {
        int ps = this.getPS(dip);
        int ms = this.getWidth()-ps;
        return ms;
    }
    public int getPYFromTop(float dip) {
        int ps = this.getPS(dip);
        return ps;
    }

    public int getPYFromBottom(float dip) {
        int ps = this.getPS(dip);
        int ms = this.getHeight()-ps;
        return ms;
    }

    public int pxl(float dp) {
        return this.getPXFromLeft(dp);
    }

    public int pxr(float dp) {
        return this.getPXFromRight(dp);
    }

    public int pyt(float dp) {
        return this.getPYFromTop(dp);
    }

    public int pyb(float dp) {
        return this.getPYFromBottom(dp);
    }

    public int ps(float dp) {
        return this.getPS(dp);
    }

    public int pw(float sdp) {
        int ps = this.getPS(sdp*2);
        int ms = this.getWidth()-ps;
        return ms;
    }

    public int cx(float dp) {
        int ps = this.getPS(dp);
        int ms = this.getWidth()/2-ps/2;
        return ms;
    }

    // in double paged and landscape mode,get the center of view(its width is dpWidth) on left page
    public int lcx(float dpWidth) {
        int ps = this.getPS(dpWidth);
        int ms = this.getWidth()/4-ps/2;
        return ms;
    }
    // in double paged and landscape mode,get the center of view(its width is dpWidth) on right page
    public int rcx(float dpWidth) {
        int ps = this.getPS(dpWidth);
        int ms = this.getWidth()/2+this.getWidth()/4-ps/2;
        return ms;
    }

    public float getDIP(float px) {
        float densityDPI = this.getDensityDPI();
        float dip = px/(densityDPI/240);
        return dip;
    }


    public int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f; // value component
        int darker = Color.HSVToColor(hsv);
        return darker;
    }

    public Bitmap getBackgroundForLandscape() {
        Bitmap backgroundForLandscape=null;
        Theme theme = getCurrentTheme();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        return backgroundForLandscape;
    }





    public int getMaxSize() {
        int width = this.getRawWidth();
        int height= this.getRawHeight();
        return Math.max(width,height);
    }
    public Theme getCurrentTheme() {
        Theme theme = themes.get(1);
        return theme;
    }

    public void setThemeIndex(int index) {
        themeIndex = index;
    }

    public int getOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    public int getWidth() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        return width;
    }

    public boolean isPortrait() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation== Configuration.ORIENTATION_PORTRAIT) return true;
        else return false;
    }


    public int getHeight() {
        if (Build.VERSION.SDK_INT>=19) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int height = this.getRawHeight();
            height+=ps(50);
            if (Build.DEVICE.contains("maguro") && this.isPortrait()) {
                height-=ps(65);
            }

            return height;
        }else {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int height = metrics.heightPixels;
            height+=ps(50);
            return height;
        }
    }

    public int getRawHeight() {
        int width = 0, height = 0;
        final DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        Method mGetRawH = null, mGetRawW = null;

        try {
            // For JellyBeans and onward
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                display.getRealMetrics(metrics);
                width = metrics.widthPixels;
                height = metrics.heightPixels;
            } else {
                mGetRawH = Display.class.getMethod("getRawHeight");
                mGetRawW = Display.class.getMethod("getRawWidth");
                try {
                    width = (Integer) mGetRawW.invoke(display);
                    height = (Integer) mGetRawH.invoke(display);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    return 0;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return 0;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            return height;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return 0;
        }
    }

    public int getRawWidth() {
        int width = 0, height = 0;
        final DisplayMetrics metrics = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        Method mGetRawH = null, mGetRawW = null;

        try {
            // For JellyBeans and onward
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                display.getRealMetrics(metrics);

                width = metrics.widthPixels;
                height = metrics.heightPixels;
            } else {
                mGetRawH = Display.class.getMethod("getRawHeight");
                mGetRawW = Display.class.getMethod("getRawWidth");

                try {
                    width = (Integer) mGetRawW.invoke(display);
                    height = (Integer) mGetRawH.invoke(display);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return 0;
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return 0;
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return 0;
                }
            }
            return width;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return 0;
        }
    }



    public void makeLayout() {
        highlights = new Highlights();
        String fileName = new String();
        fileName = "Alice.epub";
        textsize=18;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;

        ePubView = new LinearLayout(this);

        LayoutParams LLParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        ePubView.setLayoutParams(LLParams);
        ePubView.setOrientation(LinearLayout.VERTICAL);

        themes.add(new Theme("white",Color.BLACK, 0xffffff,Color.argb(240, 94,61,35),Color.LTGRAY,Color.argb(240, 94,61,35),Color.argb(120, 160, 124, 95),Color.DKGRAY,0x22222222,"Phone-Portrait-White.png","Phone-Landscape-White.png","Phone-Landscape-Double-White.png", R.drawable.bookmark2x));
        themes.add(new Theme("brown", Color.BLACK, 0xece3c7, Color.argb(240, 94, 61, 35), Color.argb(255, 255, 255, 255), Color.argb(240, 94, 61, 35), Color.argb(120, 160, 124, 95), Color.DKGRAY, 0x22222222, "Phone-Portrait-Brown.png", "Phone-Landscape-Brown.png", "Phone-Landscape-Double-Brown.png", R.drawable.bookmark2x));
        themes.add(new Theme("black", Color.LTGRAY, 0x323230, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, Color.LTGRAY, 0x77777777, "Phone-Portrait-Black.png", "Phone-Landscape-Black.png", "Phone-Landscape-Double-Black.png", R.drawable.bookmarkgray2x));

        View child = getLayoutInflater().inflate(R.layout.ttttttt, null);
        ePubView.addView(child);
        final ImageView option=(ImageView)child.findViewById(R.id.options);
        ImageView chapters=(ImageView)child.findViewById(R.id.chapters);

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPopupButtonClick(option);

            }
        });


        chapters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showbookchapters();
            }
        });



        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);


        rv = new ReflowableControl(this);

        if (this.getOSVersion()>=11) {
            rv = new ReflowableControl(this);						// in case that device supports transparent webkit, the background image under the content can be shown. in some devices, content may be overlapped.
        }else {
            rv = new ReflowableControl(this,getCurrentTheme().backgroundColor);			// in case that device can not support transparent webkit, the background color will be set in one color.
        }

        String baseDirectory = getFilesDir().getAbsolutePath() + "/books";
        rv.setBaseDirectory(baseDirectory);
        rv.setBookName(fileName);
        rv.setLicenseKey("1221-1132-1334-1243");

        rv.setGlobalPaging(true);
        rv.setDoublePagedForLandscape(true);
        rv.setFont("TimesRoman", textsize);
        rv.setLineSpacing(135); // the value is supposed to be percent(%).
        rv.setHorizontalGapRatio(0.30);
        rv.setVerticalGapRatio(0.22);

        rv.setPageMovedListener(new PageMovedDelegate());

        rv.setPagingListener(new PagingDelegate());

        rv.setMaxSizeForBackground(1024);
        if (this.isDoublePagedForLandscape) {
            rv.setBackgroundForLandscape(this.getBackgroundForLandscape(), 	new Rect(0,0,2004,1506),	new Rect(32	,0,2004-32,1506)); 			// Android Rect - left,top,right,bottom
        }else {
            rv.setBackgroundForLandscape(this.getBackgroundForLandscape(), 	new Rect(0,0,2004,1506),	new Rect(0	,0,2004-32,1506)); 			// Android Rect - left,top,right,bottom
        }
        Log.e("chapter index", "" + rv.getChapterIndex());

        Log.e("number of pages", "" + rv.getNumberOfPagesInBook());



        rv.setPageTransition(PageTransition.Curl);

        rv.setLayoutParams(params);

        SkyProvider skyProvider = new SkyProvider();
        rv.setContentProvider(skyProvider);
        rv.setBackgroundColor(getCurrentTheme().backgroundColor);
        rv.setForegroundColor(getCurrentTheme().foregroundColor);

        rv.setFingerTractionForSlide(true);
        rv.setSendingEventsToIFrameEnabled(false);
        rv.setGlobalOffset(true);
        if (this.getMaxSize()>1280) {
            rv.setCurlQuality(0.5f);
        }
        //  params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        //  params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.width = LayoutParams.WRAP_CONTENT; // 400;
        params.height = LayoutParams.WRAP_CONTENT; // 600;
        rv.setStartPositionInBook(pagePositionInBook);

        rv.useDOMForHighlight(false);
        rv.setNavigationAreaWidthRatio(0.4f); // both left and right side.
        ePubView.addView(rv);

        LayoutParams debugButton1Param = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT); // width,height
        debugButton1 = new Button(this);
        debugButton1.setText("Exit");
        debugButton1Param.leftMargin = (int) (20 * density);
        debugButton1Param.topMargin = (int) (5 * density);
        debugButton1Param.width = (int) (70 * density);
        debugButton1Param.height = (int) (35 * density);
        debugButton1.setLayoutParams(debugButton1Param);
        debugButton1.setId(8081);
        debugButton1.setOnClickListener(listener);
        ePubView.addView(debugButton1);

        if(debugButton1.getParent()!=null)
            ((ViewGroup)debugButton1.getParent()).removeView(debugButton1); // <- fix
        ePubView.addView(debugButton1);

        LayoutParams markButtonParam = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT); // width,height
        markButton = new Button(this);
        markButton.setText("Highlight");
        markButtonParam.leftMargin = (int) (240 * density);
        markButtonParam.topMargin = (int) (5 * density);
        markButtonParam.width = (int) (70 * density);
        markButtonParam.height = (int) (35 * density);
        markButton.setLayoutParams(markButtonParam);
        markButton.setId(8083);
        markButton.setOnClickListener(listener);
        markButton.setVisibility(View.INVISIBLE);

        ePubView.addView(markButton);

        setContentView(ePubView);


    }

    int op = 0;
    int targetPageIndexInBook = 0;
    public void moveSeekBox(PageInformation pi) {
        int position = seekBar.getProgress();
        targetPageIndexInBook = position;
        if (Math.abs(op-position)<10) {
            return;
        }
        if (pi==null) return;
        String chapterTitle = null;
        if (!rv.isRTL()) {
            chapterTitle = pi.chapterTitle;
            if (pi.chapterTitle==null || pi.chapterTitle.isEmpty()) {
                chapterTitle = "Chapter "+pi.chapterIndex;
            }
        }else {
            int targetIndex = rv.getNumberOfChapters()-pi.chapterIndex-1;
            chapterTitle = rv.getChapterTitle(targetIndex);
            if (pi.chapterTitle==null || pi.chapterTitle.isEmpty()) {
                chapterTitle = "Chapter "+targetIndex;
            }
        }
        if (rv.isGlobalPagination()) {
//			seekLabel.setText(String.format("%s %d",chapterTitle,position+1));
            //  seekLabel.setText(chapterTitle);
        }else {
            // seekLabel.setText(chapterTitle);
        }

        op = position;
    }




    public void showbookchapters(){

        final Dialog dialog = new Dialog(BookViewActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window=dialog.getWindow();
        dialog.setCancelable(false);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_chapters);
        displayNavPoints();
        ListView chapterslist=(ListView)dialog.findViewById(R.id.chapters_list);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chapter_titles);
        chapterslist.setAdapter(itemsAdapter);

        chapterslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int index = view.getId();

                NavPoints nps = rv.getNavPoints();
                targetNavPoint = nps.getNavPoint(position);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        rv.showPages();
                        rv.gotoPageByNavPoint(targetNavPoint);
                    }
                }, 200);

                    chapter_titles.clear();

                  // makeLayout();

                    dialog.dismiss();

                }
            });

        dialog.show();
    }



    public void onCreate(Bundle savedInstanceState) {

        debug("onCreate");
        super.onCreate(savedInstanceState);
        this.makeLayout();
    }


    public void onPopupButtonClick(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                String t = (String) item.getTitle();
                if (t.equals("text size 15")) {
                    rv.changeFontSize(15);

                }
                if (t.equals("text size 18")) {
                    rv.changeFontSize(18);
                }
                if (t.equals("text size 20")) {
                    rv.changeFontSize(20);
                }

                if (t.equals("text size 23")) {
                    rv.changeFontSize(23);

                }

                if (t.equals("text size 25")) {
                    rv.changeFontSize(25);

                }
                return true;
            }
        });

        popup.show();
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View arg) {
            if (arg.getId() == 8080) {
                displayNavPoints();
            } else if (arg.getId() == 8081) {
                finish();
            } else if (arg.getId() == 8082) {
                rv.debug2("");
            } else if (arg.getId() == 8083) {
                hideButton();
                mark();
            }
        }
    };

    private void displayNavPoints() {
        NavPoints nps = rv.getNavPoints();

        for (int i=0; i<nps.getSize(); i++) {
            NavPoint np = nps.getNavPoint(i);
            // debug("" + i + ":" + np.text);


            chapter_titles.add(i, np.text);

        }

        Log.e("chapter no","chapter title"+chapter_titles);

        // modify one NavPoint object at will
//        NavPoint onp = nps.getNavPoint(1);
//        onp.text = "preface - it is modified";

//        for (int i=0; i<nps.getSize(); i++) {
//            NavPoint np = nps.getNavPoint(i);
//            debug(""+i+":"+np.text);
//            Log.e("dfiojdf" + i + "", "kdml" + np.text);
//
//        }
    }

    private void mark() {
        rv.markSelection(0x66FFFF00, "");

    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }


    class PagingDelegate implements PagingListener {
        public void onPagingStarted(int bookCode) {
            // hideBoxes();
            disableControlBeforePagination();
        }

        public void onPaged(PagingInformation pagingInformation) {
            int ci = pagingInformation.chapterIndex;
            int cn = rv.getNumberOfChapters();
            int value = (int)((float)ci*100/(float)cn);
            //changePagingView(value);

            Log.e("lkmjlkdledde",""+value);

            Log.e("chapter index" + ci, "no.of chapters" + cn);

        }

        public void onPagingFinished(int bookCode) {
            enableControlAfterPagination();
        }

        @Override
        public int getNumberOfPagesForPagingInformation(PagingInformation pagingInformation) {
            return 0;
        }


    }


    private String getPageText() {
        String text = "";
        int si = rv.getStartIndexInPage();
        int ei = rv.getEndIndexInPage();

        int max = Math.max(si, ei);
        int min = Math.min(si, ei);

        for (int i=min; i<=max; i++) {

            String name = rv.getNodeNameByUniqueIndex(i);
            if (name.equalsIgnoreCase("sky")) {
                String nt = rv.getNodeTextByUniqueIndex(i);
                text =nt+"\r\n";
                Log.e("texttttttt",""+text);

            }
        }
        return text;
    }








    public void disableControlBeforePagination() {


        int pi = rv.getPageIndexInChapter();
        int tn = rv.getNumberOfPagesInChapter();
        setIndexLabelsText(-1,-1); // do not display.

        Log.e("page index in chapter"+pi,"no. of pages in chapter"+tn);

    }

    public void enableControlAfterPagination() {

        int pi = rv.getPageIndexInBook();
        int tn = rv.getNumberOfPagesInBook();
        setIndexLabelsText(pi,tn);

        Log.e("After pagination page index"+pi,"no. of pages "+tn);



    }



    class PageMovedDelegate implements PageMovedListener {

        public void onPageMoved(PageInformation pi) {
            double ppb = pi.pagePositionInBook;
            double pageDelta = ((1.0f/pi.numberOfChaptersInBook)/pi.numberOfPagesInChapter);
            if (rv.isRTL()) {
                ppb +=pageDelta;
            }

            int progress = (int)((double)999.0f * (ppb));
            int pib = pi.pageIndexInBook;

            if (rv.isGlobalPagination()) {
                if (!rv.isPaging()) {

                    setIndexLabelsText(pi.pageIndexInBook,pi.numberOfPagesInBook);
                }else {
                    setIndexLabelsText(-1,-1); // do not display
                }
            }else {

                setIndexLabelsText(pi.pageIndex, pi.numberOfPagesInChapter);
            }

            pagePositionInBook = (float)pi.pagePositionInBook;

            isPageTurnedByMediaOverlay = false;
            if (pi.pageDescription!=null) {
                debug(pi.pageDescription);
            }
            positionStack.push(new Double(pi.pagePositionInBook));
            if (positionStack.size()<2) {

            }else {

            }

            Log.e("EPub","number of chapter "+pi.numberOfChaptersInBook);
            Log.e("chapter index ",""+pi.chapterIndex);
            Log.e(",ds,dxxd,ddkidjj",""+pi.numberOfPagesInBook);
            Log.e("chapter title",""+pi.chapterTitle);

        }

        @Override
        public void onChapterLoaded(int chapterIndex) {
            // TODO Auto-generated method stub

            if (rv.isMediaOverlayAvailable()) {

                if (autoStartPlayingWhenNewPagesLoaded) {
                    if (isAutoPlaying) rv.playFirstParallelInPage();
                }
            }else {

            }


        }

        @Override
        public void onFailedToMove(boolean b) {

        }
    }



    public void setIndexLabelsText(int pageIndex, int pageCount) {
        if (pageIndex==-1 || pageCount==-1 || pageCount==0) {
//            pageIndexLabel.setText("");
//            secondaryIndexLabel.setText("");
            return;
        }

        pi = 0;
        int si = 0;
        int pc;
        if (rv.isDoublePaged()) {
            pc = pageCount*2;
            pi = pageIndex*2+1;
            si = pageIndex*2+2;
        }else {
            pc = pageCount;
            pi = pageIndex+1;
            si = pageIndex+2;
            if (rv.isRTL()) {
                pi = pc-pi+1;
                si = pc-si+1;
            }
        }
        String pt = String.format("%3d/%3d",pi,pc);
        String st = String.format("%3d/%3d",si,pc);
        Log.e("text sizeee",""+pt);
        Log.e("second text sizeee",""+st);

    }








    private void moveButton(int x, int y) {
        LayoutParams markButtonParam = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT); // width,height
        markButtonParam.leftMargin = x;
        markButtonParam.topMargin = y;
        markButton.setLayoutParams(markButtonParam);
    }

    private void showButton() {
        markButton.setVisibility(View.VISIBLE);
    }

    private void hideButton() {
        markButton.setVisibility(View.INVISIBLE);
        markButton.setVisibility(View.GONE);
    }



    public void debug(String msg) {
        if (Setting.isDebug()) {
            Log.e(Setting.getTag(), msg);
        }
    }


}


class Theme {
    public String name;
    public int foregroundColor;
    public int backgroundColor;
    public int controlColor;
    public int controlHighlightColor;
    public String portraitName = "";
    public String landscapeName = "";
    public String doublePagedName = "";
    public int seekBarColor;
    public int seekThumbColor;
    public int selectorColor;
    public int selectionColor;
    public int bookmarkId;

    Theme(String name,int foregroundColor,int backgroundColor,int controlColor,int controlHighlightColor,int seekBarColor,int seekThumbColor,int selectorColor,int selectionColor,String portraitName,String landscapeName,String doublePagedName,int bookmarkId) {
        this.name = name;
        this.foregroundColor = foregroundColor;
        this.backgroundColor=backgroundColor;
        this.portraitName = portraitName;
        this.landscapeName = landscapeName;
        this.doublePagedName = doublePagedName;
        this.controlColor = controlColor;
        this.controlHighlightColor = controlHighlightColor;
        this.seekBarColor = seekBarColor;
        this.seekThumbColor = seekThumbColor;
        this.selectorColor = selectorColor;
        this.selectionColor = selectionColor;
        this.bookmarkId = bookmarkId;
    }




}
