package com.abwebmobile.karl.zslombard;

/**
 * Created by Karl on 08.02.2018.
 */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.abwebmobile.karl.zslombard.ConsultationsAndNewsRequestsClasses.Timestamp;
import com.abwebmobile.karl.zslombard.RoomEntities.AboutItem;
import com.abwebmobile.karl.zslombard.RoomEntities.Category;
import com.abwebmobile.karl.zslombard.RoomEntities.City;
import com.abwebmobile.karl.zslombard.RoomEntities.Department;
import com.abwebmobile.karl.zslombard.RoomEntities.MarketUser;
import com.abwebmobile.karl.zslombard.RoomEntities.NewsItem;
import com.abwebmobile.karl.zslombard.RoomEntities.SellItem;
import com.abwebmobile.karl.zslombard.RoomEntities.SettingsState;
import com.abwebmobile.karl.zslombard.RoomEntities.ZsUser;

/**
 * Created by Karl on 28.01.2018.
 */
@Database(entities = {SettingsState.class, AboutItem.class, Category.class, City.class, Timestamp.class, Department.class, MarketUser.class, ZsUser.class, NewsItem.class, SellItem.class}, version = 16)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}