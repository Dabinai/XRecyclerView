package sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dabin on 2017/10/21.
 */

public class MySQL extends SQLiteOpenHelper{
    public MySQL(Context context) {
        super(context, "db_qingqiu", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table jizhu(_id integer primary key autoincrement,name varchar(20),pass varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
