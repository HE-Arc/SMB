package ch.hearc.smb.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageDate {
    private static SimpleDateFormat dfDataBase = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static SimpleDateFormat dfDisplay = new SimpleDateFormat("dd MMMM yyyy HH:mm");

    public static synchronized String dateToDB(Date dateIn) {
        return dfDataBase.format(dateIn);
    }

    public static synchronized Date dateFromDB(String dateIn) {
        try {
            return dfDataBase.parse(dateIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(); //In case there is a problem
    }

    public static synchronized String dateToDisplay(Date dateIn) {
        return dfDisplay.format(dateIn);
    }

    public static synchronized Date dateFromDisplay(String dateIn) {
        try {
            return dfDisplay.parse(dateIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(); //In case there is a problem
    }

    public static synchronized String dateFromDBToDisplay(String dateIn) {
        return dateToDisplay(dateFromDB(dateIn));
    }
}
