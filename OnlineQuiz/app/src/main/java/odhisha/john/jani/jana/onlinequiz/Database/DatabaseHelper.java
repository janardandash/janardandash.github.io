package odhisha.john.jani.jana.onlinequiz.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import odhisha.john.jani.jana.onlinequiz.Model.Scorelist;

/**
 * Created by janardan.d on 18-12-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper sInstance;

    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat
    private static String DB_NAME = "score.db";// Database name
    private static SQLiteDatabase mDataBase;
    private final Context mContext;

    private static final int DATABASE_VERSION = 1;
    private static final String CategoryName = "CategoryName";
    private static final String TABLE_NAME = "HHT_SCORE";

    private static final int Score = 0;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        mDataBase = getWritableDatabase();
    }

    public static DatabaseHelper getDbHelperSingletonInstance(Context mContext) {
        if (mDataBase == null) {
            synchronized (DatabaseHelper.class) {
                if (mDataBase == null) {
                    sInstance = new DatabaseHelper(mContext);

                    return sInstance;
                }
            }
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DataBaseQueries.HHT_SCORE_Table_Create);
            Log.d(TAG, "DEVICE_DETAILS_Created");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBaseQueries.HHT_SCORE_Table_Drop);
        Log.d(TAG, "HHT_SCORE_Droped");
        onCreate(db);

    }

    public Boolean insert(String tablename, HashMap<String, Object> params) {
        // mDataBase = this.getWritableDatabase();

        try {
            long result = 0;
            ContentValues values = new ContentValues();
            for (Map.Entry entry : params.entrySet()) {
                Object value = entry.getValue();
                values.put(entry.getKey().toString(), value.toString());
            }
            result = mDataBase.insert(tablename, null, values);
            if (result == -1)
                return false;
            else
                return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            return false;
        } finally {
            // close();
        }

    }

    public Cursor select(String query, String[] selectionArgs) {
        try {
            // mDataBase = this.getReadableDatabase();

            return mDataBase.rawQuery(query, selectionArgs);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }


    public Boolean update(String tablename, HashMap<String, Object> params,
                          String whereClause, String[] whereArgs) {
        // mDataBase = this.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            for (Map.Entry entry : params.entrySet()) {
                Object value = entry.getValue();
                values.put(entry.getKey().toString(), value.toString());

            }
            mDataBase.update(tablename, values, whereClause, whereArgs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
            return false;
        } finally {
            // close();
        }

    }


    /*public List<Scorelist> GetScorelist()
    {
        try
        {
            String sql = "SELECT [CategoryName],[Score] FROM [HHT_SCORE]"
                    + " ORDER BY Score DESC;";
            List<Scorelist> list = new ArrayList<Scorelist>();
            // mDataBase = this.getReadableDatabase();
            Cursor cursorObj = mDataBase.rawQuery(sql, null);
            if (cursorObj != null)
                if (cursorObj.moveToFirst())
                {
                    do
                    {
                        list.add(new Scorelist(
                                cursorObj.getInt(cursorObj.getColumnIndex("Score"))));
                    } while (cursorObj.moveToNext());
                }

            cursorObj.close();
            // mDataBase.close();
            return list;
        }
        catch (Exception e)
        {

            e.printStackTrace();
            return null;
        }
    }*/

    public ArrayList<String> Getlist()
    {
        try {
            String sql = "SELECT [Score] FROM [HHT_SCORE]"
                    + " ORDER BY Score DESC;";
            ArrayList<String> list = new ArrayList<String>();
            // mDataBase = this.getReadableDatabase();
            Cursor cursorObj = mDataBase.rawQuery(sql, null);
            if (cursorObj != null)
                if (cursorObj.moveToFirst()) {
                    do {
                        list.add(String.valueOf(
                                cursorObj.getInt(cursorObj.getColumnIndex("Score"))));
                    } while (cursorObj.moveToNext());
                }

            cursorObj.close();
            // mDataBase.close();
            return list;
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<String> GetlistCategory()
    {
        try {
            String sql = "SELECT [CategoryName] FROM [HHT_SCORE]"
                    + " ORDER BY Score DESC;";
            ArrayList<String> list = new ArrayList<String>();
            // mDataBase = this.getReadableDatabase();
            Cursor cursorObj = mDataBase.rawQuery(sql, null);
            if (cursorObj != null)
                if (cursorObj.moveToFirst()) {
                    do {
                        list.add(
                                cursorObj.getString(cursorObj.getColumnIndex("CategoryName")));
                    } while (cursorObj.moveToNext());
                }

            cursorObj.close();
            // mDataBase.close();
            return list;
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }
    }

}