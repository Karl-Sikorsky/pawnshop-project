package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.abwebmobile.karl.zslombard.AdressItem;
import com.abwebmobile.karl.zslombard.CallbackCategories;
import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotMessage;
import com.abwebmobile.karl.zslombard.ChatBotClasses.ChatBotReply;
import com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses.Timestamp;
import com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses.TimestampChecker;
import com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses.TimestampService;
import com.abwebmobile.karl.zslombard.GatewaysAdaptersRepositories.DatabaseProvider;
import com.abwebmobile.karl.zslombard.LoginResponseClasses.RetrofitService;
import com.abwebmobile.karl.zslombard.R;
import com.abwebmobile.karl.zslombard.RoomEntities.Category;
import com.abwebmobile.karl.zslombard.RoomEntities.City;
import com.abwebmobile.karl.zslombard.RoomEntities.Department;
import com.abwebmobile.karl.zslombard.RoomEntities.NewsItem;
import com.abwebmobile.karl.zslombard.RoomEntities.Offer;
import com.abwebmobile.karl.zslombard.RoomEntities.SellItem;
import com.abwebmobile.karl.zslombard.RoomEntities.SettingsState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Karl on 04.02.2018.
 */

public class InfoViewModel extends AndroidViewModel implements TimestampChecker {

    Context context;

    MutableLiveData<List<AdressItem>> allAdresses;
    private LiveData<List<Offer>> offersByCurrentUser;


    public InfoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();


    }

    MutableLiveData<List<AdressItem>> adressItems;

    public MutableLiveData<List<AdressItem>> getAllAdressItems() {

        if (adressItems == null) {
            adressItems = new MutableLiveData<>();


            ArrayList<AdressItem> adresses = new ArrayList<>();
            adresses.add(new AdressItem("пр.Гагаріна буд 17", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 18", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 19", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 12", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 2", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 7", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 37", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adresses.add(new AdressItem("пр.Гагаріна буд 0", "Найменування підрозділу: ВІДОКРЕМЛЕНИЙ ПІДРОЗДІЛ №42 ПОВНОГО ТОВАРИСТВА «ЛОМБАРД «ЗОЛОТА СКРИНЯ» ТОВ «КРЕДИТНЕ ТОВАРИСТВО №1» І КОМПАНІЯ» \n\n Адреса: 50027, Дніпропетровська область., м. Кривий Ріг, проспект Гагаріна, буд. 17, прим. 105 \n\n Протокол про створення: №13 від 30.07.2010р \n\n Перелік фінансових послуг: Надання коштів у позику, в тому числі на умовах фінансового кредиту. \n\n Керівник підрозділу: Шафрай Юрій Олександрович \n"));
            adressItems.setValue(adresses);
        }
        return adressItems;
    }

    MutableLiveData<List<SellItem>> allSellItems;

    public LiveData<List<SellItem>> getAllSellItems() {
        if (allSellItems == null) {
            allSellItems = new MutableLiveData<>();
            ArrayList<SellItem> sells = new ArrayList<>();
            sells.add(new SellItem());
            sells.add(new SellItem());
            sells.add(new SellItem());
            sells.add(new SellItem());
            sells.add(new SellItem());
            sells.add(new SellItem());
            sells.add(new SellItem());
            sells.add(new SellItem());
            allSellItems.setValue(sells);
        }
        return allSellItems;
    }

    public void addDefaultCategories() {

        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().addDefaultCategories();


    }


    MutableLiveData<List<Category>> sellCategories;

    public LiveData<List<Category>> getSellCategories() {
        if (sellCategories == null) {
            sellCategories = new MutableLiveData<>();
            loadCategories(new CallbackCategories<List<Category>>() {
                @Override
                public void onLoad(List<Category> itemsLoaded) {
                    sellCategories.postValue(itemsLoaded);
                }


            });
           /* String[] categories = new String[]{"Ювелірні вироби","Побутова техніка","Інше"};
            sellCategories.setValue(categories);*/
        }
        return sellCategories;
    }

    private void loadCategories(final CallbackCategories<List<Category>> callbackCategories) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseProvider.getInstance().createDatabaseReference(context);
                List<Category> categories = DatabaseProvider.getInstance().getAllCategories();


                callbackCategories.onLoad(categories);
            }
        }).start();
    }

    MutableLiveData<List<NewsItem>> news;

    public LiveData<List<NewsItem>> getNews() {
        if (news == null) {
            news = new MutableLiveData<>();
            loadNews(new CallbackNews<List<NewsItem>>() {
                @Override
                public void onLoad(List<NewsItem> itemsLoaded) {
                    news.postValue(itemsLoaded);
                }


            });

        }
        return news;
    }

    private void loadNews(final CallbackNews<List<NewsItem>> callbackNews) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseProvider.getInstance().createDatabaseReference(context);
                List<NewsItem> newsList = DatabaseProvider.getInstance().getAllNews();


                callbackNews.onLoad(newsList);
            }
        }).start();
    }

    MutableLiveData<List<City>> allCities;

    public LiveData<List<City>> getAllCities() {
        if (allCities == null) {
            allCities = new MutableLiveData<>();
            loadCities(new CallbackCities<List<City>>() {
                @Override
                public void onLoad(List<City> itemsLoaded) {
                    allCities.postValue(itemsLoaded);
                }


            });

        }
        return allCities;
    }

    private void loadCities(final CallbackCities<List<City>> callbackCities) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseProvider.getInstance().createDatabaseReference(context);
                List<City> cityList = DatabaseProvider.getInstance().getAllCities();


                callbackCities.onLoad(cityList);
            }
        }).start();
    }

    MutableLiveData<List<Department>> departments;

    public LiveData<List<Department>> getAdressItemsByCity(String s) {

        departments = new MutableLiveData<>();
        loadDepartments(s, new CallbackDepartments<List<Department>>() {
            @Override
            public void onLoad(List<Department> itemsLoaded) {
                departments.postValue(itemsLoaded);
            }


        });


        return departments;
    }

    private void loadDepartments(final String s, final CallbackDepartments<List<Department>> callbackDepartments) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseProvider.getInstance().createDatabaseReference(context);
                List<Department> departmentList = DatabaseProvider.getInstance().getDepartmentsByCityName(s);


                callbackDepartments.onLoad(departmentList);
            }
        }).start();
    }

    public LiveData<List<Offer>> getOffersByCurrentUser(Callbacker callbacker) {

        RetrofitService retrofitService = new RetrofitService();
        return retrofitService.getOffers(DatabaseProvider.getInstance().getCurrentUserContractNumber(), DatabaseProvider.getInstance().getCurrentUserLogin(), DatabaseProvider.getInstance().getCurrentUserPassword(), callbacker);


    }

    public void setCurrentUserLogin(String currentUserLogin) {
        DatabaseProvider.getInstance().setCurrentUserLogin(currentUserLogin);
    }

    public void setCurrentUserOfferNumber(String currentUserOfferNumber) {
        DatabaseProvider.getInstance().setCurrentUserContractNumber((currentUserOfferNumber));
    }

    public void setCurrentUserPassword(String s) {

        DatabaseProvider.getInstance().setCurrentUserPassword(s);
    }

    public void addLoginedUserOffers(List<Offer> items) {
        DatabaseProvider.getInstance().addLoginedUserOffers(items);
    }

    public Collection<? extends Offer> getLoginedUserOffers() {
        return DatabaseProvider.getInstance().getLoginedUserOffers();
    }


    public void addDefaultNews() {
        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().addDefaultNews();
    }

    public void addDefaultCities() {
        Log.d("cities", "try to add cities");
        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().addDefaultCities();
    }

    public void addDefaultDepartments() {
        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().addDefaultDepartments();
    }

    public void checkTimeStamp() {
        Log.d("update", "started checking timestamp");
        TimestampService timestampService = new TimestampService(this);
        timestampService.callForLastUpdate();
        Log.d("timestamp", "ok started get");
    }

    MutableLiveData<Timestamp> timestamp;

    @Override
    public void onNeedUpdate(long lastUpdateFromServer) {
        Log.d("update", "on need update called in ViewModel");
       currentLocalTimestampUpdateIsNeeded(lastUpdateFromServer);


    }

    @Override
    public void onBadResponse() {


    }





    public void currentLocalTimestampUpdateIsNeeded(final long serverTimestamp) {


            loadLocalCurrentTimeStamp(new CallbackTimestamp<Timestamp>() {
                @Override
                public void onLoad(Timestamp timestampLoaded) {
                    Log.d("update", "loaded timestamp with DB");
                    if (timestampLoaded == null) {
                        addDefaultTimestamp();
                        currentLocalTimestampUpdateIsNeeded(serverTimestamp);
                    } else {
                        Log.d("update", "is need to update? local = " + timestampLoaded.lastUpdate + " and server timestamp is " + serverTimestamp);

                        if (timestampLoaded.lastUpdate < serverTimestamp) {
                            Log.d("update", "and its need to update because local = " + timestampLoaded.lastUpdate + " and server timestamp is " + serverTimestamp);
                            timestampLoaded.lastUpdate = serverTimestamp;
                            DatabaseProvider.getInstance().updateLocalTimestamp(timestampLoaded);
                            UpdateNewsAndContacts();
                        }
                        ;
                    }
                }

            });



    }
    public void loadLocalCurrentTimeStamp(final CallbackTimestamp<Timestamp> callbackTimestamp) {

        new Thread(new Runnable() {
            @Override
            public void run () {
                DatabaseProvider.getInstance().createDatabaseReference(context);
                Timestamp timestampLoaded = DatabaseProvider.getInstance().getCurrentLocalTimestamp();


                callbackTimestamp.onLoad(timestampLoaded);
            }
        }).

                start();

    }
    private void UpdateNewsAndContacts() {
        //TODO: create service to get news/contacts
        Log.d("update", "OK NEED UPDATE");
    }

    public void addDefaultTimestamp() {
        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().addDefaultTimestamp();
    }
MutableLiveData<List<ChatBotMessage>> messagesList;
    public LiveData<List<ChatBotMessage>> getMessageItems() {
        if (messagesList==null) {
            messagesList = new MutableLiveData<>();

            ArrayList<ChatBotMessage> msgs = new ArrayList<ChatBotMessage>();
            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_intro), 10000));
            msgs.add(new ChatBotMessage(context.getString(R.string.сhat_bot_info), 10001));
            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_department), 10002));
            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_operator), 10003));
            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_see_all), 10004));
            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_intro), 10005));

            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_current_sales), 10011));

            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_price), 10012));

            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_sales), 10013));

            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_loyality_programm), 10014));

            msgs.add(new ChatBotMessage(context.getString(R.string.chat_bot_docs), 10015));


            messagesList.postValue(msgs);

        }


        return messagesList;
    }
    MutableLiveData<List<ChatBotReply>> repliesList;
    public LiveData<List<ChatBotReply>> getRepliesItems() {
        if (repliesList==null) {
            Log.d("chatbot", "called getReplies in viewModel");
            repliesList = new MutableLiveData<>();
            ArrayList<ChatBotReply> prls = new ArrayList<ChatBotReply>();
            ChatBotReply rep = new ChatBotReply(context.getString(R.string.chat_bot_button_all),4, 10004, new ArrayList<Integer>(Arrays.asList(1,2,3,4,5)));
            prls.add(rep);
            rep = new ChatBotReply(context.getString(R.string.chat_bot_button_info),1, 10001, new ArrayList<Integer>(Arrays.asList(11,12,13,14,15)));
            prls.add(rep);
             rep = new ChatBotReply(context.getString(R.string.chat_bot_button_search_dept),2, 10002, new ArrayList<Integer>(Arrays.asList(21,22,23,24,25)));
            prls.add(rep);
            rep = new ChatBotReply(context.getString(R.string.chat_bot_button_call_operator),3, 10003, new ArrayList<Integer>(Arrays.asList(31,32,33,34,35)));
            prls.add(rep);


            prls.add(new ChatBotReply(context.getString(R.string.chat_bot_button_current_sales), 11, 10011, new ArrayList<Integer>(Arrays.asList(111,112,113,114,115) )));
            prls.add(new ChatBotReply(context.getString(R.string.chat_bot_button_price), 12, 10012, new ArrayList<Integer>(Arrays.asList(121,122,123,124,125) )));
            prls.add(new ChatBotReply(context.getString(R.string.chat_bot_button_sales), 13, 10013, new ArrayList<Integer>(Arrays.asList(131,132,133,134,135) )));
            prls.add(new ChatBotReply(context.getString(R.string.chat_bot_button_loyality), 14, 10014, new ArrayList<Integer>(Arrays.asList(141,142,143,144,145) )));
            prls.add(new ChatBotReply(context.getString(R.string.chat_bot_button_docs), 15, 10015, new ArrayList<Integer>(Arrays.asList(151,152,153,154,155) )));




            repliesList.postValue(prls);
        }

        return repliesList;
    }
MutableLiveData<SettingsState> currentSettingsState = new MutableLiveData<>() ;
    public MutableLiveData<SettingsState> getCurrentSettingsState() {
        if (currentSettingsState.getValue() != null) {return  currentSettingsState;}
        else {
            loadCurrentSettingsState(new CallbackSettingsState<SettingsState>() {
                @Override
                public void onLoad(SettingsState settingsStateloaded) {

                    currentSettingsState.postValue(settingsStateloaded);
                }
            });

            return currentSettingsState;
        }
    }

    private void loadCurrentSettingsState(final CallbackSettingsState<SettingsState> callbackSettingsState) {
        new Thread(new Runnable() {
            @Override
            public void run () {
                DatabaseProvider.getInstance().createDatabaseReference(context);
                SettingsState settingsState = DatabaseProvider.getInstance().getCurrentSettingsState();
                callbackSettingsState.onLoad(settingsState);
            }
            }).start();
    }

    public void saveSettingsState(MutableLiveData<SettingsState> mCurrentSettingsState, Callbacker callbacker) {
        currentSettingsState = mCurrentSettingsState;
        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().setCurrentSettingState(mCurrentSettingsState, callbacker);
    }

    public void addDeafultSettingsState(Callbacker callbacker) {
        MutableLiveData<SettingsState> defaultSettingState = new MutableLiveData<>();
        SettingsState defaultSettings = new SettingsState();
        defaultSettings.checkBoxSalesState = true;
        defaultSettings.checkBoxReminderState = true;
        defaultSettings.checkBoxOtherState = true;
        Log.d("SHIT SHIT","addDefaulthave been called, all setted");
        defaultSettingState.setValue(defaultSettings);
        Log.d("SHIT SHIT","in infoviewmodel value = "+defaultSettingState.getValue());
        DatabaseProvider.getInstance().createDatabaseReference(context);
        DatabaseProvider.getInstance().setCurrentSettingState(defaultSettingState, callbacker);
    }
}