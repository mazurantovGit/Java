package owowo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calculations {
    public static double averageSample(ArrayList<Double> list){
        return list.stream().mapToDouble(Double::doubleValue).sum() / list.size();
    }

    public static double dispersionSample(ArrayList<Double> list) {
        return list.stream().map(d -> Math.pow(d, 2)).mapToDouble(Double::doubleValue).sum() / list.size();
    }

    public static double standartDeviation(ArrayList<Double> list) {
        return Math.sqrt(dispersionSample(list));
    }
}
