import java.util.*;

public class GenericsExample {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<String>();
//        List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
//        objs.add(1); // Put an Integer into a list of Strings
//        String s = strs.get(0);

    }

    abstract class Vehcle {

    }

    class Sedan extends Vehcle {

    }

    class Truck extends Vehcle {

    }
}
