package com.abwebmobile.karl.zslombard.useCasesAndIteractors;

import com.abwebmobile.karl.zslombard.RoomEntities.Department;

import java.util.List;

/**
 * Created by Karl on 13.02.2018.
 */

interface CallbackDepartments<T> {
    void onLoad(List<Department> items);
}
