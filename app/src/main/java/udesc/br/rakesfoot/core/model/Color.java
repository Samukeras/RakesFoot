package udesc.br.rakesfoot.core.model;

import udesc.br.rakesfoot.core.util.IntRandomUtils;

/**
 * Created by felic on 29/10/2016.
 */
public enum Color {

     BLACK ("#000000", "Black" )
    ,BLUE  ("#0000FF", "Blue"  )
    ,GREEN ("#008000", "Green" )
    ,BROWN ("#8B4513", "Brown" )
    ,PURPLE("#800080", "Purple")
    ,PINK  ("#FFC0CB", "Pink"  )
    ,RED   ("#FF0000", "Red"   )
    ,ORANGE("#FFA500", "Orange")
    ,YELLOW("#FFFF00", "Yellow")
    ,WHITE ("#FFFFFF", "White" );


    private String hexadecimal,
                   description;

    Color(String hexadecimal, String description) {
        this.hexadecimal = hexadecimal;
        this.description = description;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public String getDescription() {
        return description;
    }


    public static Color getRandomColor() {
        Color[] colors = Color.values();

        return colors[IntRandomUtils.getNextIntFromZeroToInterval(colors.length)];
    }

}