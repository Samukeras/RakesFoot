package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;

/**
 * Created by Ricardo on 03/11/2016.
 */

public class SeederMatch extends EntitySeeder {

    public SeederMatch(Connection connection) {
        super(connection);
    }

    @Override
    public Persistible getDao() {
        return null;
    }

}
