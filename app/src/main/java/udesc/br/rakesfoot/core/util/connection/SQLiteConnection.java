package udesc.br.rakesfoot.core.util.connection;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by felic on 30/10/2016.
 */
public class SQLiteConnection implements Connection<SQLiteDatabase> {

    protected static final String DATABASE_NAME   = "Rakesfoot.db";
    public    static final int    INITIAL_VERSION = 0,
                                  CURRENT_VERSION = 1;

    private static SQLiteConnection instance = null;

    private SQLiteDatabase database;
    private Context        context;


    private SQLiteConnection(Context context, int version) {
        this.context  = context;
        this.database = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        this.versionHandler(version);
    }

    public SQLiteDatabase getConnection() {
        return database;
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void endTransaction() {
        database.endTransaction();
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

    public static SQLiteConnection getInstance(Context context, int version) {
        if(instance == null || context != instance.getContext()) {
            instance = new SQLiteConnection(context, version);
        }

        return instance;
    }

}