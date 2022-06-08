package reqrestests.utils;

import java.util.Random;

public class Generator {

    public static int getRandomIntegerFromRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
