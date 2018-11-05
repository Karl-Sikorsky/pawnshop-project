package com.abwebmobile.karl.zslombard.LoginResponseClasses;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Karl on 15.02.2018.
 */

@Root(name = "Date", strict = false)
public class Date
{   @Element(name = "Rows", required = false)
    public Rows Rows;
    @Element (name = "Head", required = false)
    public Head Head;



    @Override
    public String toString()
    {
        return "ClassPojo [Rows =]";
    }
}

