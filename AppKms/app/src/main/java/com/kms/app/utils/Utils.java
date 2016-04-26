package com.kms.app.utils;

import android.util.Log;

import com.kms.app.BuildConfig;

import java.util.Calendar;

/**
 * Created by arifcebe on 28/02/16.
 */
public class Utils {

    public static String[] arrTgl(){
        String[] tgl = new String[32];

        tgl[0] = "Tanggal";
        for(int i = 1; i < tgl.length;i++){
            tgl[i] = String.valueOf(i);
        }

        return tgl;
    }

    public static String[] arrTahun(){
        String[] tahun = new String[7];
        Calendar calendar = Calendar.getInstance();
        int year = (calendar.get(Calendar.YEAR) + 1) - 6;

        tahun[0] = "Tahun";
        for(int i = 1; i < tahun.length;i++){
            tahun[i] = String.valueOf(year);
            year++;
        }

        return tahun;
    }

    public static void TRACE(String tag,String msg){
        if(BuildConfig.DEBUG){
            Log.d(tag,msg);
        }
    }


}
