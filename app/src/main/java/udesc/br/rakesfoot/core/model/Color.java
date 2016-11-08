package udesc.br.rakesfoot.core.model;

import udesc.br.rakesfoot.core.util.IntRandomUtils;

/**
 * Created by felic on 29/10/2016.
 */
public enum Color {

     BLACK ("#000000", "Black", android.graphics.Color.BLACK)
    ,BLUE  ("#0000FF", "Blue", android.graphics.Color.BLUE)
    ,GREEN ("#008000", "Green", android.graphics.Color.GREEN)
    ,BROWN ("#8B4513", "Brown", android.graphics.Color.MAGENTA)
    ,PURPLE("#800080", "Purple", 0xFF800080)
    ,PINK  ("#FFC0CB", "Pink", 0xFFFFC0CB)
    ,RED   ("#FF0000", "Red", android.graphics.Color.RED)
    ,ORANGE("#FFA500", "Orange", 0xFFFFA500)
    ,YELLOW("#FFFF00", "Yellow", android.graphics.Color.YELLOW)
    ,WHITE ("#FFFFFF", "White", android.graphics.Color.WHITE);


    private String hexadecimal,
                   description;

    private int color;

    Color(String hexadecimal, String description, int color) {
        this.hexadecimal = hexadecimal;
        this.description = description;
        this.color = color;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public String getDescription() {
        return description;
    }

    public int getColor() {
        return color;
    }

    public static Color getRandomColor() {
        Color[] colors = Color.values();

        return colors[IntRandomUtils.getNextIntFromZeroToInterval(colors.length)];
    }

}