package udesc.br.rakesfoot.game.model;

import android.support.annotation.Nullable;

/**
 * Created by Ricardo on 06/11/2016.
 */
public enum ChampionshipType {
    DIVISION_1(1),
    DIVISION_2(2);

    private final int value;

    ChampionshipType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Nullable
    public static ChampionshipType getChampionshipType(int value) {
        for(ChampionshipType type : values()) {
            if(type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
