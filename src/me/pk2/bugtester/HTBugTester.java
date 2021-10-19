package me.pk2.bugtester;

import me.pk2.pk2objects.coords.Coord;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HTBugTester {
    public static void newLog(String name) { System.out.print(name + " test... "); }
    public static void pushLog(boolean status, String error) { System.out.println(status?"OK!":"TEST FAILED!"+" ["+error+"]"); }
    public static void pushLog(boolean status) { pushLog(status, "NO_ERROR_INFO"); }

    public static void main(String[] args) throws java.io.IOException {
        newLog("NoClassModify");
        try {
            Class<?> c = Class.forName("me.pk2.bugtester.HTBugTester");
            boolean found = false;
            for(Method method : c.getMethods())
                if(method.getName().contentEquals("main"))
                    found = true;
            if(!found) throw new Exception();
            pushLog(true);
        } catch (Exception exception) { pushLog(false, exception.getMessage() + " ," + exception.getStackTrace()[0].getLineNumber()); }

        newLog("UnsafeClassLoader");
        try {
            byte[] bytes = {-54, -2, -70, -66, 0, 0, 0, 52, 0, 16, 10, 0, 3, 0, 13, 7, 0, 14, 7, 0, 15, 1, 0, 6, 60, 105, 110, 105, 116, 62, 1, 0, 3, 40, 41, 86, 1, 0, 4, 67, 111, 100, 101, 1, 0, 15, 76, 105, 110, 101, 78, 117, 109, 98, 101, 114, 84, 97, 98, 108, 101, 1, 0, 18, 76, 111, 99, 97, 108, 86, 97, 114, 105, 97, 98, 108, 101, 84, 97, 98, 108, 101, 1, 0, 4, 116, 104, 105, 115, 1, 0, 31, 76, 109, 101, 47, 112, 107, 50, 47, 112, 107, 50, 111, 98, 106, 101, 99, 116, 115, 47, 99, 111, 111, 114, 100, 47, 67, 111, 111, 114, 100, 59, 1, 0, 10, 83, 111, 117, 114, 99, 101, 70, 105, 108, 101, 1, 0, 10, 67, 111, 111, 114, 100, 46, 106, 97, 118, 97, 12, 0, 4, 0, 5, 1, 0, 29, 109, 101, 47, 112, 107, 50, 47, 112, 107, 50, 111, 98, 106, 101, 99, 116, 115, 47, 99, 111, 111, 114, 100, 47, 67, 111, 111, 114, 100, 1, 0, 16, 106, 97, 118, 97, 47, 108, 97, 110, 103, 47, 79, 98, 106, 101, 99, 116, 0, 33, 0, 2, 0, 3, 0, 0, 0, 0, 0, 1, 0, 1, 0, 4, 0, 5, 0, 1, 0, 6, 0, 0, 0, 47, 0, 1, 0, 1, 0, 0, 0, 5, 42, -73, 0, 1, -79, 0, 0, 0, 2, 0, 7, 0, 0, 0, 6, 0, 1, 0, 0, 0, 3, 0, 8, 0, 0, 0, 12, 0, 1, 0, 0, 0, 5, 0, 9, 0, 10, 0, 0, 0, 1, 0, 11, 0, 0, 0, 2, 0, 12};
            Field theUnsafe = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
            if (!theUnsafe.isAccessible()) theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe)theUnsafe.get(null);
            Class<?> c = unsafe.defineAnonymousClass(Coord.class, bytes, null);
            c.newInstance();
            pushLog(true);
        } catch (Exception exception) { pushLog(false, exception.getMessage() + " ," + exception.getStackTrace()[0].getLineNumber()); }

        newLog("APIConnection");
        try {
            PostRequest.read(PostRequest.genGetCon("https://aurahardware.eu/api/HTPEAuth.txt"));
            pushLog(true);
        } catch (Exception exception) { pushLog(false, exception.getMessage()+ " ," + exception.getStackTrace()[0].getLineNumber());}

        System.out.println("All tests done, send a ss to PK2_Stimpy.");
        System.in.read();
    }
}