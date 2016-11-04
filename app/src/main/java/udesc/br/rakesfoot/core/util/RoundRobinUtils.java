package udesc.br.rakesfoot.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ricardo on 03/11/2016.
 */

public class RoundRobinUtils {



    public static List<Map<Integer, Integer>> getMatches(int teams){
        List<Map<Integer, Integer>> list = new ArrayList<>();
        for (int i = 1; i < teams; i++) {
            list.add(getMatches(teams, i));
        }
        return list;
    }

    public static Map<Integer, Integer> getMatches(int teams, int round){
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 1; i <= teams / 2; i++) {
            int team2 = teams - ((round + i) - 2);
            int team1 = i;

            if (round > 1 && i != 1) {
                team1 -= round - 1;
                if (team1 < 2) {
                    team1--;
                }
            }

            if (team2 < 2) {
                team2--;
            }

            map.put(turnAround(team1, teams), turnAround(team2, teams));
        }

        return map;
    }

    private static int turnAround(int index, int max) {
        if (index <= 0) {
            return max + index;
        }
        return index;
    }

}
