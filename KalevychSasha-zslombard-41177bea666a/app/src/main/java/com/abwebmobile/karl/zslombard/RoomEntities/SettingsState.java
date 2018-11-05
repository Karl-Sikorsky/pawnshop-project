package com.abwebmobile.karl.zslombard.RoomEntities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Karl on 09.03.2018.
 */
@Entity
public class SettingsState {
   @PrimaryKey(autoGenerate = true)
   public int id;
   public int languageCode;
   public boolean checkBoxSalesState;
   public boolean checkBoxReminderState;
   public boolean checkBoxOtherState;
}
