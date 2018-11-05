package com.abwebmobile.karl.zslombard.LoginResponseClasses;

import org.simpleframework.xml.Element;

/**
 * Created by Karl on 15.02.2018.
 */


public class Head
{   @Element (name = "FIO", required = false)
    private String FIO;
    @Element (name = "UUID_Individual", required = false)
    private String UUID_Individual;

    public String getFIO ()
    {
        return FIO;
    }

    public void setFIO (String FIO)
    {
        this.FIO = FIO;
    }

    public String getUUID_Individual ()
    {
        return UUID_Individual;
    }

    public void setUUID_Individual (String UUID_Individual)
    {
        this.UUID_Individual = UUID_Individual;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FIO = "+FIO+", UUID_Individual = "+UUID_Individual+"]";
    }
}

