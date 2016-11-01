package udesc.br.rakesfoot.core.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by felic on 30/10/2016.
 */
public class NameUtils {

    private static final String FILE_FIRST_NAMES = "firstnames.txt",
                                FILE_LAST_NAMES  = "lastnames.txt";

    private static List<String> firstNames = null,
                                lastNames  = null;


    public static List<String> getFirstNames(Context context) {
        if(firstNames == null) {
            AssetManager assetManager = context.getResources().getAssets();
            try {
                InputStream       inputStream       = assetManager.open(FILE_FIRST_NAMES);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader    bufferedReader    = new BufferedReader(inputStreamReader);
                String line;
                firstNames = new LinkedList<String>();
                while((line = bufferedReader.readLine()) != null) {
                    firstNames.add(line);
                }
                inputStream.close();
            } catch (IOException e) {
                return null;
            }
        }

        return firstNames;
    }

    public static List<String> getLastNames(Context context) {
        if(lastNames == null) {
            AssetManager assetManager = context.getResources().getAssets();
            try {
                InputStream       inputStream       = assetManager.open(FILE_FIRST_NAMES);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader    bufferedReader    = new BufferedReader(inputStreamReader);
                String line;
                lastNames = new LinkedList<>();
                while((line = bufferedReader.readLine()) != null) {
                    lastNames.add(line);
                }
                inputStream.close();
            } catch (IOException e) {
                return null;
            }
        }

        return lastNames;
    }

    public static String generateRandomFirstName(Context context) {
        List<String> names = getFirstNames(context);

        return names.get(IntRandomUtils.getNextIntFromZeroToInterval(names.size()));
    }

    public static String generateRandomLastName(Context context) {
        List<String> names = getLastNames(context);

        return names.get(IntRandomUtils.getNextIntFromZeroToInterval(names.size()));
    }

}