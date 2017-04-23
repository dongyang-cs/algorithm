package Utils;

/**
 * Created by Administrator on 2017/4/15.
 */
public class ArrayUtil {
    private static final int defaultLength = 10;

    public static Integer[] getArray(Integer length) {
        if (length == null) {
            length = defaultLength;
        }
        Integer[] array = new Integer[length];
        for (int i = 0; i < length; i++) {
            array[i] = (int) (Math.random() * length);
        }
        return array;
    }
}
