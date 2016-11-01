package udesc.br.rakesfoot.core.util.connection;

import android.content.Context;

/**
 * Created by felic on 30/10/2016.
 */
public interface Connection<ConnectionType> {

    public static final int INITIAL_VERSION = 0,
                            CURRENT_VERSION = 1;


    public ConnectionType getConnection();

    public void beginTransaction();

    public void endTransaction();

    public void close();

    public void versionHandler(int version);

    public void onCreate();

    public void onUpgrade();

    public Context getContext();
}