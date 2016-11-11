package udesc.br.rakesfoot.core.seeder;

import udesc.br.rakesfoot.core.persistence.Persistible;
import udesc.br.rakesfoot.game.model.Budget;
import udesc.br.rakesfoot.game.model.Team;
import udesc.br.rakesfoot.game.model.dao.sqlite.SqliteDaoBudget;

import static udesc.br.rakesfoot.game.rules.Budget.START_CASH;

public class SeederBudget extends EntitySeeder<Budget, Team> {

    @Override
    public Persistible getDao() {
        return new SqliteDaoBudget(getConnection().getContext());
    }

    @Override
    public void seed(Team parent) {
        Budget budget = new Budget();
        budget.setTeam(parent);
        budget.setStartCash(START_CASH);
        budget.setStartCash(START_CASH);

        getDao().insert(budget);
        handle(budget);
    }

    @Override
    public void crop(Team parent) {

    }

}