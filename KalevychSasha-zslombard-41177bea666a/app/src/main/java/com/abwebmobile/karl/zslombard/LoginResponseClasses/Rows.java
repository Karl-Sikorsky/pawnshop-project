package com.abwebmobile.karl.zslombard.LoginResponseClasses;


import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created by Karl on 15.02.2018.
 */
public class Rows
{    @ElementList(entry = "Row", inline = true, required = false)
    public List<Row> rows;



    @Override
    public String toString()
    {
        return "ok have rows";
    }
}