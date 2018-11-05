package com.abwebmobile.karl.zslombard;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

import java.util.List;

/**
 * Created by Karl on 08.02.2018.
 */


@Dao
public interface DatabaseDao {


    @Query("SELECT * FROM settingsstate LIMIT 1")
    SettingsState getCurrentSettingsState();

    @Insert
    void insert(SettingsState item);

    @Update
    void update(SettingsState item);

    @Delete
    void delete(SettingsState item);

    @Query("DELETE FROM settingsstate")
    void nukeTableSettingsState();



    @Query("SELECT * FROM timestamp LIMIT 1")
    Timestamp getCurrentLocalTimestamp();


    @Insert
    void insert(Timestamp item);

    @Update
    void update(Timestamp item);

    @Delete
    void delete(Timestamp item);

    @Query("DELETE FROM timestamp")
    void nukeTableTimestamp();

    @Query("SELECT * FROM aboutitem")
    List<AboutItem> getAllAboutItems();

    @Query("SELECT * FROM aboutitem WHERE item_id = :id")
    AboutItem getAboutItemById(long id);

    @Insert
    void insert(AboutItem item);

    @Update
    void update(AboutItem item);

    @Delete
    void delete(AboutItem item);


    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM category WHERE category_id = :id")
    Category getCategoryById(long id);

    @Insert
    void insert(Category item);

    @Update
    void update(Category item);

    @Delete
    void delete(Category item);


    @Query("SELECT * FROM city")
    List<City> getAllCityItems();

    @Query("SELECT * FROM city WHERE city_id = :id")
    City getCityItemById(long id);

    @Insert
    void insert(City itm);

    @Update
    void update(City item);

    @Delete
    void delete(City item);
    @Query("DELETE FROM city")
    void nukeTableCities();




    @Query("SELECT * FROM department")
    List<Department> getAllDepartments();

    @Query("SELECT * FROM department WHERE department_id = :id")
    Department getDepartmentById(long id);

    @Query("SELECT * FROM department WHERE city_name = :s")
    List<Department> getDepartmentsByCityName(String s);

    @Insert
    void insert(Department item);

    @Update
    void update(Department item);

    @Delete
    void delete(Department item);

    @Query("DELETE FROM department")
    void nukeTableDepartments();






    @Query("SELECT * FROM marketuser")
    List<MarketUser> getAllMarketUsers();

    @Query("SELECT * FROM marketuser WHERE id = :id")
    MarketUser getMarketUserById(long id);

    @Insert
    void insert(MarketUser item);

    @Update
    void update(MarketUser item);

    @Delete
    void delete(MarketUser item);




    @Query("SELECT * FROM newsitem")
    List<NewsItem> getAllNewsItems();

    @Query("SELECT * FROM newsitem WHERE news_id = :id")
    NewsItem getNewsItemById(long id);

    @Insert
    void insert(NewsItem item);

    @Update
    void update(NewsItem item);

    @Delete
    void delete(NewsItem item);

    @Query("DELETE FROM newsitem")
    void nukeTableNews();




    @Query("SELECT * FROM sellitem")
    List<SellItem> getAllSellItems();

    @Query("SELECT * FROM sellitem WHERE id = :id")
    SellItem getSellItemById(long id);

    @Insert
    void insert(SellItem item);

    @Update
    void update(SellItem item);

    @Delete
    void delete(SellItem item);





    @Query("SELECT * FROM zsuser")
    List<ZsUser> getAllZSUsers();

    @Query("SELECT * FROM zsuser WHERE id = :id")
    ZsUser getZSUserById(long id);

    @Insert
    void insert(ZsUser itm);

    @Update
    void update(ZsUser item);

    @Delete
    void delete(ZsUser item);



}
