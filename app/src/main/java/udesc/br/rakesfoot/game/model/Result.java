package udesc.br.rakesfoot.game.model;

import android.support.annotation.Nullable;

/**
 * Created by Info Home on 10/11/2016.
 */

public enum Result {

     NONE(0)   // Match not played yet
    ,DRAW(1)   // The match ended in a draw
    ,HOST(2)   // The host won the match
    ,GUEST(3); // The guest won the match

    private int id;

    Result(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public static Result getResultById(int id) {
        for(Result result : Result.values()) {
            if(result.getId() == id) {
                return result;
            }
        }

        return null;
    }
}
