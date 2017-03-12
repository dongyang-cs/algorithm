import org.junit.Test;

/**
 * Created by Administrator on 2016/11/6.
 */


public class Main {
    @Test
    public static void main(String[] args) {
        try {
            String s = "1234567abcdefghijklmn";
            byte[] b = s.getBytes("UTF-8");
            for(byte tm:b){
                println(tm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void println(Object o) {
        System.out.println(o.toString());
    }
}
