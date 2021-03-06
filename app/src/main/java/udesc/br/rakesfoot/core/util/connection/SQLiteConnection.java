package udesc.br.rakesfoot.core.util.connection;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by felic on 30/10/2016.
 */
public class SQLiteConnection implements Connection<SQLiteDatabase> {

    protected static final String DATABASE_NAME = "Rakesfoot.db";

    private static SQLiteConnection instance = null;

    private int version;

    private SQLiteDatabase database;

    private Context context;

    private SQLiteConnection(Context context) {
        this.context  = context;
        open();
        defineVersion();
    }

    protected void open() {
        this.database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_APPEND, null);
    }

    public void defineVersion() {
        version = Connection.INITIAL_VERSION;
        if (databaseExist(this.context) && tableExists("manager")) {
            version = Connection.CURRENT_VERSION;
        }
        this.versionHandler(version);
    }

    public boolean tableExists(String tableName) {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='%s'";

        Cursor cursor = database.rawQuery(String.format(sql, tableName), new String[0]);

        return cursor.moveToFirst();
    }

    public int getVersion() {
        return version;
    }

    public SQLiteDatabase getConnection() {
        return database;
    }

    public void beginTransaction() {
//        database.beginTransaction();
    }

    public void commit() {
//        database.setTransactionSuccessful();
    }

    public void endTransaction() {
//        database.endTransaction();
    }

    public void close() {
        database.close();
    }

    public Context getContext() {
        return context;
    }

    public void versionHandler(int version) {
        if(version == INITIAL_VERSION) {
            onCreate();
            return;
        }

        if(version != CURRENT_VERSION) {
            onUpgrade();
        }
    }

    public void onCreate() {

    }

    public void onUpgrade() {

    }

    public static SQLiteConnection getInstance(Context context) {
        if(instance == null || context != instance.getContext()) {
            instance = new SQLiteConnection(context);
        }

        return instance;
    }

    public static boolean databaseExist(Context context) {
        return context.getDatabasePath(DATABASE_NAME).exists();
    }

    public static boolean deleteDataBase(Context context) {
        getInstance(context).close();

        boolean ok = context.deleteDatabase(DATABASE_NAME);
        getInstance(context).open();
        getInstance(context).defineVersion();

        return ok;
    }

}