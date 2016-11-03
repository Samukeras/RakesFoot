package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Stadium;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoStadium;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederStadium extends EntitySeeder {

    public SeederStadium(Connection connection) {
        super(connection);
    }

    @Override
    public Persistible getDao() {
        return new SqliteDaoStadium(connection.getContext(), Connection.INITIAL_VERSION);
    }

    @Override
    public void seed(Connection connection) {
        Persistible persistible = getDao();
        persistible.onCreate();

        Stadium s1 = new Stadium();
        s1.setName("Estádio 1");
        s1.setId(1);
        s1.setMaxCapacity(20000);
        persistible.insert(s1);

        Stadium s2 = new Stadium();
        s2.setName("Estádio 2");
        s2.setId(2);
        s2.setMaxCapacity(20000);
        persistible.insert(s2);

        Stadium s3 = new Stadium();
        s3.setName("Estádio 3");
        s3.setId(3);
        s3.setMaxCapacity(20000);
        persistible.insert(s3);

        Stadium s4 = new Stadium();
        s4.setName("Estádio 4");
        s4.setId(4);
        s4.setMaxCapacity(20000);
        persistible.insert(s4);

        Stadium s5 = new Stadium();
        s5.setName("Estádio 5");
        s5.setId(5);
        s5.setMaxCapacity(20000);
        persistible.insert(s5);

        Stadium s6 = new Stadium();
        s6.setName("Estádio 6");
        s6.setId(6);
        s6.setMaxCapacity(20000);
        persistible.insert(s6);

        Stadium s7 = new Stadium();
        s7.setName("Estádio 7");
        s7.setId(7);
        s7.setMaxCapacity(20000);
        persistible.insert(s7);

        Stadium s8 = new Stadium();
        s8.setName("Estádio 8");
        s8.setId(8);
        s8.setMaxCapacity(20000);
        persistible.insert(s8);

        Stadium s9 = new Stadium();
        s9.setName("Estádio 9");
        s9.setId(9);
        s9.setMaxCapacity(20000);
        persistible.insert(s9);

        Stadium s10 = new Stadium();
        s10.setName("Estádio 10");
        s10.setId(10);
        s10.setMaxCapacity(20000);
        persistible.insert(s10);

        Stadium s11 = new Stadium();
        s11.setName("Estádio 11");
        s11.setId(11);
        s11.setMaxCapacity(20000);
        persistible.insert(s11);

        Stadium s12 = new Stadium();
        s12.setName("Estádio 12");
        s12.setId(12);
        s12.setMaxCapacity(20000);
        persistible.insert(s12);

        Stadium s13 = new Stadium();
        s13.setName("Estádio 13");
        s13.setId(13);
        s13.setMaxCapacity(20000);
        persistible.insert(s13);

        Stadium s14 = new Stadium();
        s14.setName("Estádio 14");
        s14.setId(14);
        s14.setMaxCapacity(20000);
        persistible.insert(s14);

        Stadium s15 = new Stadium();
        s15.setName("Estádio 15");
        s15.setId(15);
        s15.setMaxCapacity(20000);
        persistible.insert(s15);

        Stadium s16 = new Stadium();
        s16.setName("Estádio 16");
        s16.setId(16);
        s16.setMaxCapacity(20000);
        persistible.insert(s16);

        Stadium s17 = new Stadium();
        s17.setName("Estádio 17");
        s17.setId(17);
        s17.setMaxCapacity(20000);
        persistible.insert(s17);

        Stadium s18 = new Stadium();
        s18.setName("Estádio 18");
        s18.setId(18);
        s18.setMaxCapacity(20000);
        persistible.insert(s18);

        Stadium s19 = new Stadium();
        s19.setName("Estádio 19");
        s19.setId(19);
        s19.setMaxCapacity(20000);
        persistible.insert(s19);

        Stadium s20 = new Stadium();
        s20.setName("Estádio 20");
        s20.setId(20);
        s20.setMaxCapacity(20000);
        persistible.insert(s20);
    }
}
