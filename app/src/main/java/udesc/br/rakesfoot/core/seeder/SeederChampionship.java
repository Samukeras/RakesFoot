package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.model.Entity;
import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.core.util.connection.Connection;
import udesc.br.rakesfoot.game.model.Championship;
import udesc.br.rakesfoot.game.model.ChampionshipType;
import udesc.br.rakesfoot.game.model.Season;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoChampionship;

/**
 * Created by felic on 03/11/2016.
 */

public class SeederChampionship extends EntitySeeder<Championship, Season> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoChampionship(getConnection().getContext());
    }

    @Override
    public void seed(Season parent) {
        createChampionship(parent, ChampionshipType.DIVISION_1, "Brasileirão Série A");
        createChampionship(parent, ChampionshipType.DIVISION_2, "Brasileirão Série B");
    }

    private void createChampionship(Season season, ChampionshipType type, String description) {
        Championship champ = new Championship();
        champ.setName(description);
        champ.setType(type);
        champ.setRound(1);
        champ.setSeason(season);
        season.addCaChampionship(champ);

        getDao().insert(champ);

        handle(champ);
    }

    @Override
    public void crop(Season parent) {
        for (Championship championship : (Iterable<Championship>) getDao().getAll()) {
            parent.addCaChampionship(championship);
            championship.setSeason(parent);
            handle(championship);
        }
    }
}