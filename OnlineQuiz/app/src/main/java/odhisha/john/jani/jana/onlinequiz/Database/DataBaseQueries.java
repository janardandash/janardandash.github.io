package odhisha.john.jani.jana.onlinequiz.Database;

/**
 * Created by janardan.d on 18-12-2017.
 */

public final class DataBaseQueries {
    public static String HHT_SCORE_Table_Create = "CREATE TABLE [HHT_SCORE] (\r\n"
            + "	[id] INTEGER PRIMARY KEY AUTOINCREMENT, \r\n"
            + "	[CategoryName] nvarchar(150),\r\n"
            + "	[Score] INTEGER \r\n"
            + ")";
    public static String HHT_SCORE_Table_Drop = "DROP TABLE IF EXISTS HHT_SCORE";


}
