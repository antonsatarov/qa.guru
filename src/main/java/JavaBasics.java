public class JavaBasics {

    public static void main(String[] args) {
        //data types
        char s = 's';

        byte b = 3;
        int i = 10;
        long l = 20L;

        float f = 22.2f;
        double d = 1.11;

        boolean boolTrue = true;
        boolean boolFalse = false;

        //arithmetic
        System.out.println("arithmetic:");
        System.out.println("i + l = " + (i + l));
        System.out.println("i - l = " + (i - l));
        System.out.println("i * l = " + (i * l));
        System.out.println("l / i = " + (l / i));
        System.out.println("l % b = " + (l % b));
        System.out.println("i++   = " +  (i++));
        System.out.println("i--   = " +  (i--));
        System.out.println("--i   = " +  (--i));
        System.out.println("++i   = " +  (++i));

        //comparison
        System.out.println("comparison:");
        System.out.println(s + " == " + s + " = " + (s == s));
        System.out.println(s + " != " + s + " = " + (s != s));
        System.out.println(l + " > " + i + " = " + (l > i));
        System.out.println(l + " < " + i + " = " + (l < i));
        System.out.println(b + " >= " + 3*b + " = " + (b >= 3*b));
        System.out.println(b + " <= " + 3*b + " = " + (b <= 3*b));

        //logical
        System.out.println("logical:");
        System.out.println(boolTrue + " && " + boolFalse + " = " + (boolTrue && boolFalse));
        System.out.println(boolTrue + " || " + boolFalse + " = " + (boolTrue || boolFalse));
        System.out.println("!(" + boolTrue + " && " + boolFalse + ") = " + !(boolTrue && boolFalse));

        //assignment
        System.out.println("assignment:");

        f += d;
        System.out.println("c += a = " + f);

        d -= f;
        System.out.println("c -= a = " + d);

        d *= b ;
        System.out.println("c *= a = " + d);

        //overflow
        System.out.println("overflow:");
        System.out.println("byte min = " + Byte.MIN_VALUE);
        // b = Byte.MIN_VALUE - 1;
        System.out.println("byte min = " + Byte.MAX_VALUE);
        // b = Byte.MAX_VALUE + 1;
    }

}
