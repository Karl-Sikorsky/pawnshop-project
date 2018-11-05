package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import com.abwebmobile.karl.zslombard.RoomEntities.SettingsState;

/**
 * Created by Karl on 09.03.2018.
 */

interface CallbackSettingsState<T> {
    void onLoad(SettingsState settingsState);
}
