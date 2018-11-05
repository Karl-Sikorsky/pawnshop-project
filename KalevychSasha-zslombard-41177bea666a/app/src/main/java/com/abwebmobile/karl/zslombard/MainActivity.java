package com.abwebmobile.karl.zslombard;

import android.app.NotificationManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.abwebmobile.karl.zslombard.ChatClasses.ChatFragment;
import com.abwebmobile.karl.zslombard.RoomEntities.City;
import com.abwebmobile.karl.zslombard.allFragments.AboutFragment;
import com.abwebmobile.karl.zslombard.allFragments.AdressesFragment;
import com.abwebmobile.karl.zslombard.allFragments.ChatBotFragment;
import com.abwebmobile.karl.zslombard.allFragments.ConditionsHostFragment;
import com.abwebmobile.karl.zslombard.allFragments.FragmentCalculator;
import com.abwebmobile.karl.zslombard.allFragments.LoginFragment;
import com.abwebmobile.karl.zslombard.allFragments.NewsFragment;
import com.abwebmobile.karl.zslombard.allFragments.OffersFragment;
import com.abwebmobile.karl.zslombard.allFragments.QReaderFragment;
import com.abwebmobile.karl.zslombard.allFragments.SellPlatformHostFragment;
import com.abwebmobile.karl.zslombard.notification.NotificationFragment;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.InfoViewModel;
import com.google.firebase.iid.FirebaseInstanceId;


import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.abwebmobile.karl.zslombard.Constants.ABOUT_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.ADRESSES_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.CHAT_BOT_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.CHAT_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.CONDITIONS_HOST_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.FRAGMENT_CALCULATOR;
import static com.abwebmobile.karl.zslombard.Constants.FRAGMENT_SHOP_CATEGORY;
import static com.abwebmobile.karl.zslombard.Constants.LOGIN_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.NEWS_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.NOTIFICATION_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.OFFERS_FRAGMENT;
import static com.abwebmobile.karl.zslombard.Constants.QREADER_FRAGMENT;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HostView {
    ConditionsHostFragment fragmentConditions;
    AboutFragment fragmentAbout;
    LoginFragment fragmentLogin;
    AdressesFragment fragmentAdresses;
    FragmentCalculator fragmentCalculator;
    NewsFragment fragmentNews;
    SellPlatformHostFragment fragmentSell;
    OffersFragment offersFragment;
    NotificationFragment fragmentNotification;
    FragmentManager fm;
    FragmentTransaction ft;
    QReaderFragment fragmentReader;
    ChatBotFragment fragmentChatBot;
    ChatFragment fragmentChat;
    private int NOTIFICATION = 546;
    private NotificationManager mNM;
    InfoViewModel mViewModel;
    String chatTime;

    int currentFragmentCode = NEWS_FRAGMENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mNM == null)
            mNM = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        try {
            Log.d("hurt",FirebaseInstanceId.getInstance().getToken());
            mNM.cancel(NOTIFICATION);
            //Toast.makeText(getApplicationContext(), "Зміни прийнято", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            //sorry for this
        }
        mViewModel = ViewModelProviders.of(this).get(InfoViewModel.class);

        mViewModel.getAllCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> items) {
                if (items.size() == 0) {
                    mViewModel.addDefaultCities();
                    mViewModel.addDefaultDepartments();
                    Log.d("haveNULL", "have null and add default data");
                }
            }});


                Log.d("lifecycleObserver","Activity onCreate");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
Log.d("landscape", "activity onCreate called");

         fm = getSupportFragmentManager();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            Log.d("landscape", "activity instanceState called");
            currentFragmentCode = savedInstanceState.getInt("current fragment");
           chatTime = savedInstanceState.getString("chatTime");
        } else {
            chatTime =  DateFormat.format("dd-MM-yyyy (HH:mm:ss)", new Date().getTime()).toString();
            fragmentConditions = new ConditionsHostFragment();
            fragmentAbout = new AboutFragment();
            fragmentLogin = new LoginFragment();

            fragmentAdresses = new AdressesFragment();

            fragmentCalculator = new FragmentCalculator();
            fragmentNews = new NewsFragment();
            fragmentSell = new SellPlatformHostFragment();
            offersFragment = new OffersFragment();
            fragmentReader = new QReaderFragment();
            fragmentChatBot = new ChatBotFragment();
            fragmentNotification = new NotificationFragment();
            fragmentChat = new ChatFragment();
            fragmentChat.setChatDate(chatTime);



        }

        requestFragment(currentFragmentCode, "");






    }
    private static long back_pressed;
    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), R.string.tap_twice_to_exit, Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.nav_ru) {
             Resources res = getApplicationContext().getResources();
             DisplayMetrics dm = res.getDisplayMetrics();
             android.content.res.Configuration conf = res.getConfiguration();
             conf.locale = new Locale("RU".toLowerCase());
             res.updateConfiguration(conf, dm);
             recreate();
             return true;
         }
        if (id == R.id.nav_ua) {
            Resources res = getApplicationContext().getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            android.content.res.Configuration conf = res.getConfiguration();
            conf.locale = new Locale("UK".toLowerCase());
            res.updateConfiguration(conf, dm);
            recreate();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            // Handle the camera action

           requestFragment(ABOUT_FRAGMENT, "");
            currentFragmentCode = ABOUT_FRAGMENT;
        } else if (id == R.id.nav_account) {


            requestFragment(LOGIN_FRAGMENT, "");
            currentFragmentCode = LOGIN_FRAGMENT;
        } else if (id == R.id.nav_adress) {

           requestFragment(ADRESSES_FRAGMENT, "");
            currentFragmentCode = ADRESSES_FRAGMENT;
        } else if (id == R.id.nav_calculator) {

             requestFragment(FRAGMENT_CALCULATOR, "");
            currentFragmentCode = FRAGMENT_CALCULATOR;
        } else if (id == R.id.nav_conditions) {


            requestFragment(CONDITIONS_HOST_FRAGMENT, "");

            currentFragmentCode = CONDITIONS_HOST_FRAGMENT;
        }
        else if (id == R.id.nav_news) {

           requestFragment(NEWS_FRAGMENT, "");
            currentFragmentCode = NEWS_FRAGMENT;
        }else if (id == R.id.nav_chat){


           requestFragment(CHAT_FRAGMENT, "");
           currentFragmentCode = CHAT_FRAGMENT;

        }else if (id == R.id.nav_settings){
            requestFragment(NOTIFICATION_FRAGMENT, "");
            currentFragmentCode = NOTIFICATION_FRAGMENT;
            Log.d("settingsFr", "setted in menu");
        }else if (id == R.id.nav_shop){
            requestFragment(FRAGMENT_SHOP_CATEGORY, "");
            currentFragmentCode = FRAGMENT_SHOP_CATEGORY;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void requestFragment(int fragmentCode, String value) {
        Log.d("lifecycleObserver","Activity requestFragment");
        currentFragmentCode = fragmentCode;
        Log.d("landscape", "activity request fragment called");
        if (fragmentCode ==ABOUT_FRAGMENT){
            if(fragmentAbout==null) {
                Log.d("shit", "ok is was null");
                fragmentAbout = (AboutFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_ABOUT");

            }
            if (fragmentAbout==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentAbout = new AboutFragment();

            }

            ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, fragmentAbout, "FRGAMENT_ABOUT");
            ft.commit();


        }

        if (fragmentCode ==CONDITIONS_HOST_FRAGMENT){
            if(fragmentConditions==null) {
                Log.d("shit", "ok is was null");
                fragmentConditions = (ConditionsHostFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_CONDITIONS");

            }
            if (fragmentConditions==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentConditions = new ConditionsHostFragment();

            }
            fragmentConditions.setHostView(this);
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragmentConditions, "FRGAMENT_CONDITIONS");
                ft.commit();
                fragmentConditions.setHostView(this);

        }
        if(fragmentCode==FRAGMENT_CALCULATOR){
            if(fragmentCalculator==null) {
                Log.d("shit", "ok is was null");
                fragmentCalculator = (FragmentCalculator) getSupportFragmentManager().findFragmentByTag("FRAGMENT_CALCULATOR");

            }
            if (fragmentCalculator==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentCalculator = new FragmentCalculator();
            }
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragmentCalculator, "FRAGMENT_CALCULATOR");
                ft.commit();

        }
        if(fragmentCode==QREADER_FRAGMENT){



                Log.d("shit", "SHIT SHIT SHIT");
                fragmentReader = new QReaderFragment();

                fragmentReader.setHostView(this);
                if (!value.trim().equals(""))
                    fragmentReader.setCameraChosed(Integer.parseInt(value));
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragmentReader, "QREADER_FRAGMENT");
                ft.commit();

        }
        if(fragmentCode==LOGIN_FRAGMENT){
            if(fragmentLogin==null) {
                Log.d("shit", "ok is was null");
                fragmentLogin = (LoginFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_LOGIN");

            }
            if (fragmentLogin==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentLogin = new LoginFragment();

            }
                Log.d("qrlog", "fragment requested value = " + value);
                fragmentLogin.setHostView(this);
                if (!value.trim().equals("")) {
                    Log.d("qrlog", "value valid = " + value);
                    fragmentLogin.setOfferID(value);
                }
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragmentLogin, "FRAGMENT_LOGIN");
                ft.commit();

        }
        if (fragmentCode==OFFERS_FRAGMENT){
            if(offersFragment==null) {
                Log.d("shit", "ok is was null");
                offersFragment = (OffersFragment) getSupportFragmentManager().findFragmentByTag("OFFERS_FRAGMENT");
            }
            if (offersFragment==null){
                Log.d("shit", "SHIT SHIT SHIT");
                offersFragment = new OffersFragment();
            }
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, offersFragment, "OFFERS_FRAGMENT");
                ft.commit();

        }
        if (fragmentCode==NEWS_FRAGMENT){
            if(fragmentNews==null) {
                Log.d("shit", "ok is was null");
                fragmentNews = (NewsFragment) getSupportFragmentManager().findFragmentByTag("NEWS_FRAGMENT");

            }
            if (fragmentNews==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentNews = new NewsFragment();
            }
                fragmentNews.setHostView(this);
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragmentNews, "NEWS_FRAGMENT");
                ft.commit();

        }
        if (fragmentCode==ADRESSES_FRAGMENT){
            if(fragmentAdresses==null) {
                Log.d("shit", "ok is was null");
                fragmentAdresses = (AdressesFragment) getSupportFragmentManager().findFragmentByTag("ADRESSES_FRAGMENT");

            }
            if (fragmentAdresses==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentAdresses = new AdressesFragment();
            }
                fragmentAdresses.setHostView(this);
                ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragmentAdresses, "ADRESSES_FRAGMENT");
                ft.commit();


        }
        if (fragmentCode ==CHAT_FRAGMENT){
            if(fragmentChat==null) {
                Log.d("shit", "ok is was null");
                fragmentChat = (ChatFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_CHAT");

            }
            if (fragmentChat==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentChat = new ChatFragment();
                fragmentChat.setChatDate(chatTime);

            }

            ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, fragmentChat, "FRGAMENT_CHAT");
            ft.commit();


        }

        if (fragmentCode ==NOTIFICATION_FRAGMENT){
            if(fragmentNotification==null) {
                Log.d("shit", "ok is was null");
                fragmentNotification = (NotificationFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_NOTIFICATION");
                Log.d("settingsFr", "op 1");
            }
            if (fragmentNotification==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentNotification = new NotificationFragment();
                Log.d("settingsFr", "op 2");
            }
            fragmentNotification.setHostView(this);
            ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, fragmentNotification, "FRGAMENT_NOTIFICATION");
            ft.commit();
            Log.d("settingsFr", "end all commiteds");


        }

        if (fragmentCode ==FRAGMENT_SHOP_CATEGORY){
            if(fragmentSell==null) {
                Log.d("shit", "ok is was null");
                fragmentSell = (SellPlatformHostFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENT_SHOP_CATEGORY");

            }
            if (fragmentSell==null){
                Log.d("shit", "SHIT SHIT SHIT");
                fragmentSell = new SellPlatformHostFragment();


            }

            ft = fm.beginTransaction();
            ft.replace(R.id.fragment_container, fragmentSell, "FRGAMENT_SHOP_CATEGORY");
            ft.commit();


        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("lifecycleObserver","Activity onSaveInstanceState");
        outState.putInt("current fragment", currentFragmentCode);
        outState.putString("chatTime", chatTime);
        Log.d("landscape", "activity savw state called");
        super.onSaveInstanceState(outState);

    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifecycleObserver","Activity onResume");
        // ... your own onResume implementation

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifecycleObserver","Activity onPause");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifecycleObserver","Activity onDestroy");

    }


}
