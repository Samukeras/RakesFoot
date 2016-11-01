package udesc.br.rakesfoot.game.model.dao;

import android.content.Context;

import udesc.br.rakesfoot.core.model.dao.DAOGeneric;
import udesc.br.rakesfoot.game.model.Player;

/**
 * Created by felic on 01/11/2016.
 */
public class DaoPlayer extends DAOGeneric<Player> {

    public DaoPlayer(Context context, int version) {
        super(context, version);
    }
}
