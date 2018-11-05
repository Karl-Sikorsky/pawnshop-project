package com.abwebmobile.karl.zslombard.LoginResponseClasses;

import org.simpleframework.xml.Element;

/**
 * Created by Karl on 15.02.2018.
 */

public class Row
{ @Element (name = "MinimumAmountPayment", required = false)
    public String MinimumAmountPayment;
    @Element (name = "LastOperationDate", required = false)
    public String LastOperationDate;
    @Element (name = "МaximumСredit", required = false)
    public String MaximumCredit;
    @Element(name = "DaysOverdue", required = false)
    public String DaysOverdue;
    @Element(name = "ContractNumber", required = false)
    public String ContractNumber;
    @Element(name = "Surcharge", required = false)
    public String Surcharge;
    @Element(name = "DaysTheEnd", required = false)
    public String DaysTheEnd;
    @Element (name = "MaximumAmountPayment", required = false)
    public String MaximumAmountPayment;
    @Element(name = "ExpirationDate", required = false)
    public String ExpirationDate;
    @Element(name = "Dodor", required = false)
    public String Dodor;
    @Element(name = "description", required = false)
    public String description;
    @Element(name = "InterestRatePerDay", required = false)
    public String InterestRatePerDay;
    @Element(name = "AccruedInterest", required = false)
    public String AccruedInterest;
    @Element(name = "AgreementDate", required = false)
    public String AgreementDate;
    @Element(name = "СreditBalance", required = false)
    public String CreditBalance;

    @Override
    public String toString()
    {
        return "ClassPojo ]" ;
    }
}
