package udesc.br.rakesfoot.game.model;

import android.support.annotation.Nullable;

/**
 * Created by Ricardo on 01/11/2016.
 */
public enum BudgetType {

    PLAYER_HIRING           (1, BudgetClass.EXPENSE),
    PLAYER_SALARY           (2, BudgetClass.EXPENSE),
    STADIUM_IMPROVEMENT     (3, BudgetClass.EXPENSE),
    TRAINING                (4, BudgetClass.EXPENSE),
    PHISICAL_REHABILITATION (5, BudgetClass.EXPENSE),

    TICKET                  (11, BudgetClass.INCOME),
    PRIZE_1ST               (12, BudgetClass.INCOME),
    PRIZE_2ND               (13, BudgetClass.INCOME),
    PRIZE_3RD               (14, BudgetClass.INCOME),
    STRIKER                 (15, BudgetClass.INCOME),
    PLAYER_SALE             (16, BudgetClass.INCOME);

    private final int id;

    private final BudgetClass budgetClass;

    BudgetType(int id, BudgetClass budgetClass) {
        this.id = id;
        this.budgetClass = budgetClass;
    }

    public int getId() {
        return id;
    }

    public BudgetClass getBudgetClass() {
        return budgetClass;
    }

    @Nullable
    public BudgetType getBudgetType(int id) {
        for (BudgetType type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }

        return null;
    }
}
