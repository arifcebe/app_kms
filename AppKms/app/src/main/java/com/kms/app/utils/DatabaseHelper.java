package com.kms.app.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by arifcebe on 24/04/16.
 */
public class DatabaseHelper extends SQLiteAssetHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "db_kms";
    private static DatabaseHelper instance;
    private static SQLiteDatabase db;

    // TABLE NAME
    private static final String TABLE_LAKI = "kms_laki";
    private static final String TABLE_PEREMPUAN = "kms_prp";

    // KOLOM TABLE
    private static final String COL_ID = "id";
    private static final String COL_BULAN = "bulan";
    private static final String COL_BERAT_MIN = "berat_min";
    private static final String COL_BERAT_MAX = "berat_max";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHelper(context);
            db = instance.getWritableDatabase();
        }
        return instance;
    }

    public String getStatusGizi(String bulan,float berat,String jk){
        Cursor cursor = null;
        String namaTable = "";
        String statusGizi = null;
        float beratMin,beratMax;
        if(jk.equals("laki")){
            namaTable = TABLE_LAKI;
        }else{
            namaTable = TABLE_PEREMPUAN;
        }
        cursor = db.rawQuery("SELECT * FROM "+namaTable+" WHERE "
                +COL_BULAN+" = '"+bulan+"'",null);

        if(cursor.moveToFirst()){
            beratMin = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_BERAT_MIN));
            beratMax = cursor.getFloat(cursor.getColumnIndexOrThrow(COL_BERAT_MAX));

            if(berat < beratMin){
                statusGizi = "Kurang Gizi";
            }else if(berat > beratMin && berat < beratMax){
                statusGizi = "Gizi Baik";
            }else if(berat > beratMax){
                statusGizi = "Gizi Berlebih";
            }
        }

        return statusGizi;
    }
}
