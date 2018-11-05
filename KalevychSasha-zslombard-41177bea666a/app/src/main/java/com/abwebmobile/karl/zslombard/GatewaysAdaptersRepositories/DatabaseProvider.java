package com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.abwebmobile.karl.zslombard.AppDatabase;
import com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses.Timestamp;
import com.abwebmobile.karl.zslombard.DatabaseDao;
import com.abwebmobile.karl.zslombard.RoomEntities.Category;
import com.abwebmobile.karl.zslombard.RoomEntities.City;
import com.abwebmobile.karl.zslombard.RoomEntities.Department;
import com.abwebmobile.karl.zslombard.RoomEntities.NewsItem;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;
import com.abwebmobile.karl.zslombard.RoomEntities.SettingsState;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.Callbacker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karl on 08.02.2018.
 */

public class DatabaseProvider {
    private static DatabaseProvider instance = new DatabaseProvider();
    private Context context;
    DatabaseDao databaseDao;
    AppDatabase db;


    private DatabaseProvider() {

    }

    public static DatabaseProvider getInstance() {

        return instance;
    }

    public void createDatabaseReference(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database")
                .fallbackToDestructiveMigration()
                .build();
        databaseDao = db.databaseDao();

    }

    public List<Category> getAllCategories() {
        return databaseDao.getAllCategories();
    }

    public void addDefaultCategories() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Category category = new Category();
                category.category_name = "Ювелірні вироби";
                databaseDao.insert(category);
                category.category_name = "Побутова техніка";
                databaseDao.insert(category);
                category.category_name = "Інше";
                databaseDao.insert(category);
            }
        }).start();
    }
    public void addDefaultNews(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseDao.nukeTableNews();
                NewsItem item = new NewsItem();

                databaseDao.insert(item);

            }
        }).start();
    }
    public List<NewsItem> getAllNews() {
        return databaseDao.getAllNewsItems();
    }

    public List<City> getAllCities() {
        return databaseDao.getAllCityItems();
    }

    public List<Department> getDepartmentsByCityName(String s) {
        return databaseDao.getDepartmentsByCityName(s);
    }
ArrayList<Offer> offersToPay;
    public void addOfferToPay(Offer offerAddToPay) {
        if (offersToPay==null)offersToPay = new ArrayList<>();
        offersToPay.add(offerAddToPay);
    }

    public ArrayList<Offer> getAllOffersToPay() {
        if (offersToPay==null)return new ArrayList<>();
        return offersToPay;

    }

    public void removeOfferToPay(Offer offer) {
        if (offersToPay==null)offersToPay = new ArrayList<>();
        for (int i=0;i<offersToPay.size(); i++){
            Log.d("offersPay", "now count of offers =  "+ offersToPay.size());
            if (offersToPay.get(i).contractNumber == offer.contractNumber){offersToPay.remove(i);
            Log.d("offersPay", "removed");}
           Log.d("offersPay", "now count of offers =  "+ offersToPay.size());
        }
    }

    public Float getTotalPaySum() {
        return totalPaySum;
    }

    Float totalPaySum = 0f;
    public void changePaySum(Float aFloat){
        totalPaySum+=aFloat;
    };
String currentUserLogin = "";
String currentUserPassword = "";
String currentUserContractNumber = "";

    public static void setInstance(DatabaseProvider instance) {
        DatabaseProvider.instance = instance;
    }

    public String getCurrentUserLogin() {
        return currentUserLogin;
    }

    public void setCurrentUserLogin(String currentUserLogin) {
        this.currentUserLogin = currentUserLogin;
    }

    public String getCurrentUserPassword() {
        return currentUserPassword;
    }

    public void setCurrentUserPassword(String currentUserPassword) {
        this.currentUserPassword = currentUserPassword;
    }

    public String getCurrentUserContractNumber() {
        return currentUserContractNumber;
    }

    public void setCurrentUserContractNumber(String currentUserContractNumber) {
        this.currentUserContractNumber = currentUserContractNumber;
    }

    public List<Offer> getLoginedUserOffers() {
        return loginedUserOffers;
    }

    List<Offer> loginedUserOffers;
    public void addLoginedUserOffers(List<Offer> items) {
        loginedUserOffers = new ArrayList<>();
        loginedUserOffers.addAll(items);
    }

    public void addDefaultCities() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseDao.nukeTableCities();
                Log.d("cities", "nuke table");
                City city = new City();


                city.city_name = "Чернівці";
                city.lat = 48.291411;
                city.lng = 25.945933;
                databaseDao.insert(city);




                city.city_name = "Львів";
                city.lat = 49.837982;
                city.lng = 24.020130;
                databaseDao.insert(city);

                city.city_name = "Ужгород";
                city.lat = 48.619814;
                city.lng = 22.297488;
                databaseDao.insert(city);

                city.city_name = "Мукачево";
                city.lat = 48.439075;
                city.lng = 22.719925;
                databaseDao.insert(city);
                Log.d("cities", "and all");

                city.city_name = "Київ";
                city.lat = 50.452440;
                city.lng = 30.563558;
                databaseDao.insert(city);
                Log.d("cities", "add 1");



                city.city_name = "Тернопіль";
                city.lat = 49.550784;
                city.lng = 25.600757;
                databaseDao.insert(city);


                city.city_name = "Одеса";
                city.lat = 46.454210;
                city.lng = 30.718645;
                databaseDao.insert(city);
                Log.d("cities", "add 1");


                city.city_name = "Біла Церква";
                city.lat = 49.803842;
                city.lng = 30.119580;
                databaseDao.insert(city);

                Log.d("cities", "add 1");

                city.city_name = "Житомир";
                city.lat = 48.291411;
                city.lng = 30.563558;
                databaseDao.insert(city);

                Log.d("cities", "add 1");
                city.city_name = "Кривий Ріг";
                city.lat = 47.907940;
                city.lng = 33.407682;
                databaseDao.insert(city);
                Log.d("cities", "add 1");


                city.city_name = "Рівне";
                city.lat = 50.618345;
                city.lng = 26.240976;
                databaseDao.insert(city);






                city.city_name = "Харків";
                city.lat = 49.990975;
                city.lng = 36.255604;
                databaseDao.insert(city);

                city.city_name = "Черкаси";
                city.lat = 49.420840;
                city.lng = 32.054721;
                databaseDao.insert(city);



                city.city_name = "Чернігів";
                city.lat = 51.501423;
                 city.lng = 31.287790;
                databaseDao.insert(city);


            }
        }).start();
    }

    public void addDefaultDepartments() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseDao.nukeTableDepartments();
        Department department = new Department();
        department.city_name="Київ";
        department.department_adress = "вул. Олеся Гончара, 1а";
        department.department_name = "вул. Олеся Гончара, 1а";
        databaseDao.insert(department);

        department.city_name="Київ";
        department.department_adress = "вул. Голосіївська, 18";
        department.department_name = "вул. Голосіївська, 18";
        databaseDao.insert(department);

        department.city_name="Київ";
        department.department_adress = "проспект Перемоги, 33";
        department.department_name = "проспект Перемоги, 33";
        databaseDao.insert(department);

        department.city_name="Харків";
        department.department_adress = "вул. Перемоги, 13";
        department.department_name = "вул. Перемоги, 13";
        databaseDao.insert(department);

        department.city_name="Харків";
        department.department_adress = "вул. Незалежності, 43";
        department.department_name = "вул. Незалежності, 43";
        databaseDao.insert(department);

        department.city_name="Харків";
        department.department_adress = "вул. Інженерна, 2г";
        department.department_name = "вул. Інженерна, 2г";
        databaseDao.insert(department);

        department.city_name="Одеса";
        department.department_adress = "вул. Портова, 1";
        department.department_name = "вул. Портова, 1";
        databaseDao.insert(department);

        department.city_name="Львів";
        department.department_adress = "вул. Брамна, 2";
        department.department_name = "вул. Брамна, 2";
        databaseDao.insert(department);

        department.city_name="Львів";
        department.department_adress = "вул. Брамна, 4";
        department.department_name = "вул. Брамна, 4";
        databaseDao.insert(department);

        department.city_name="Чернігів";
        department.department_adress = "вул. Чернігівська, 33в";
        department.department_name = "вул. Чернігівська, 33в";
        databaseDao.insert(department);

        department.city_name="Черкаси";
        department.department_adress = "вул. Першотравнева, 5";
        department.department_name = "вул Першотравнева, 5";
        databaseDao.insert(department);


        department.city_name="Черкаси";
        department.department_adress = "бульвар Світлий, 3в";
        department.department_name = "бульвар Світлий, 3в";
        databaseDao.insert(department);

        department.city_name="Чернівці";
        department.department_adress = "вул. Козацька, 1а";
        department.department_name = "вул. Козацька, 1а";
        databaseDao.insert(department);

        department.city_name="Житомир";
        department.department_adress = "вул. Теліги, 13";
        department.department_name = "вул. Теліги, 13";
        databaseDao.insert(department);


            }
        }).start();
    }

    public Timestamp getCurrentLocalTimestamp() {
        return databaseDao.getCurrentLocalTimestamp();
    }

    public void updateLocalTimestamp(final Timestamp timestampLoaded) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseDao.nukeTableTimestamp();
                databaseDao.insert(timestampLoaded);
                Log.d("update", "setted timestamp for "+timestampLoaded);
            }
        }).start();

    }

    public void addDefaultTimestamp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                databaseDao.nukeTableTimestamp();
                Timestamp timestamp = new Timestamp();
                timestamp.lastUpdate = System.currentTimeMillis();
                databaseDao.insert(timestamp);
                Log.d("update", "setted timestamp for"+timestamp.lastUpdate);
            }
        }).start();
    }

    public void removeAllOffersToPay() {
        offersToPay = new ArrayList<>();
    }

    public SettingsState getCurrentSettingsState() {

                SettingsState tempState = databaseDao.getCurrentSettingsState();
                if(tempState!=null) {
                    Log.d("SHIT SHIT", "get sales =  " + tempState.checkBoxSalesState);
                    Log.d("SHIT SHIT", "get rem =  " + tempState.checkBoxReminderState);
                    Log.d("SHIT SHIT", "get other =  " + tempState.checkBoxOtherState);
                }else{
                    Log.d("SHIT SHIT", "temp state is null");
                }
        return tempState;
    }

    public void setCurrentSettingState(final MutableLiveData<SettingsState> currentSettingState, final Callbacker callbacker) {
        new Thread((new Runnable() {
            @Override
            public void run() {
                Log.d("SHIT SHIT","setting in databaseProvider and value is "+currentSettingState.getValue());
                Log.d("SHIT SHIT","put sales =  "+currentSettingState.getValue().checkBoxSalesState);
                Log.d("SHIT SHIT","put rem =  "+currentSettingState.getValue().checkBoxReminderState);
                Log.d("SHIT SHIT","put other =  "+currentSettingState.getValue().checkBoxOtherState);
                databaseDao.nukeTableSettingsState();
                databaseDao.insert(currentSettingState.getValue());
                Log.d("settingFr", "ahaa added");
                callbacker.onCallback("refresh data");
            }
        })).start();

    }
}
