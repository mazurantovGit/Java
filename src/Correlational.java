package owowo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Correlational implements Analysis {
    private ArrayList<Double> xRowList;
    private ArrayList<Double> yRowList;

    Correlational(ArrayList<Double> xList, ArrayList<Double> yList){
        xRowList = xList;
        yRowList = yList;
    }

    public double getAvgSample() {
        int size = xRowList.size();
        double xyAvg = 0;
        for(int i = 0; i < size; i++){
            xyAvg += xRowList.get(i) * yRowList.get(i);
        }
        return xyAvg / size;

    }

    public double getCov(){
        return getAvgSample() - (Calculations.averageSample(xRowList) * Calculations.averageSample(yRowList));
    }

    public double getCorB(){

        return getCov() / Calculations.dispersionSample(xRowList);
    }

    public double getCorA(){
        return Calculations.averageSample(yRowList) - (getCorB() * Calculations.averageSample(xRowList));
    }

    public double getR(){
        return getCov() / (Calculations.standartDeviation(xRowList) * Calculations.standartDeviation(yRowList));
    }

    public double getDetermination(){
        ArrayList<Double> yFromXList = new ArrayList<>();
        ArrayList<Double> list1 = new ArrayList<>();
        ArrayList<Double> list2 = new ArrayList<>();
        double tmp;

        for(int i = 0; i < xRowList.size(); i++){
            tmp = getR();
            tmp *= ((xRowList.get(i) - Calculations.averageSample(xRowList)) / Calculations.standartDeviation(xRowList)) * Calculations.standartDeviation(yRowList);
            tmp += Calculations.averageSample(yRowList);
            yFromXList.add(tmp);
        }

        for(int i = 0; i < yRowList.size(); i++){
            list1.add(Math.pow((yRowList.get(i) - Calculations.averageSample(yRowList)), 2));
            list2.add(Math.pow((Calculations.averageSample(yRowList) - yFromXList.get(i)), 2));
        }

        return (list2.stream().mapToDouble(Double::doubleValue).sum()) / list1.stream().mapToDouble(Double::doubleValue).sum();
    }


    @Override
    public void info() {
        String out = "_____________________________________  \n";
        out += "X = " + xRowList + "\n";
        out += "Y = " + yRowList + "\n";
        out += "Correlation and regression \n";
        out += "Regression equation parameters \n";
        out += "Sample mean: \n";
        out += "  XY = " + getAvgSample() + "\n";
        out += "Correlation coefficient: \n";
        out += "  b = " + getCorB() + "\n";
        out += "  a = " + getCorA() + "\n";
        out += "Covariance: \n";
        out += "  cov(x,y) = " + getCov() + "\n";
        out += "The linear correlation coefficient: \n";
        out += "  r = " + getR() + "\n";
        out += "Determination index : \n";
        out += "  R^2 = " + getDetermination();

        System.out.println(out);
    }
}
