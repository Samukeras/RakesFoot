package udesc.br.rakesfoot.game.rules;

import android.location.SettingInjectorService;
import android.text.method.HideReturnsTransformationMethod;

import java.util.HashMap;
import java.util.Map;

import udesc.br.rakesfoot.game.model.EventType;

/**
 * Created by Ricardo on 03/11/2016.
 */

public class Event {

    public final static double GOAL_CHANCE        = 0.5;
    public final static double ASSISTANCE_CHANCE  = 0.2;
    public final static double INJURY_CHANCE      = 0.02;
    public final static double YELLOW_CARD_CHANCE = 0.04;
    public final static double RED_CARD_CHANCE    = 0.01;

    public static Map<EventType, Double> getEventsForSimulator() {
        Map<EventType, Double> events = new HashMap<>();

        events.put(EventType.ASSISTANCE, ASSISTANCE_CHANCE);
        events.put(EventType.INJURY, INJURY_CHANCE);
        events.put(EventType.YELLOW_CARD, YELLOW_CARD_CHANCE);
        events.put(EventType.RED_CARD, RED_CARD_CHANCE);

        return events;
    }

}
