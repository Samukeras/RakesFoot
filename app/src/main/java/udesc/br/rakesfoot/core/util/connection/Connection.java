package udesc.br.rakesfoot.core.util.connection;

/**
 * Created by felic on 30/10/2016.
 */
public interface Connection<ConnectionType> {

    public ConnectionType getConnection();

    public void beginTransaction();

    public void endTransaction();

    public void close();

    public void versionHandler(int version);

    public void onCreate();

    public void onUpgrade();

}
