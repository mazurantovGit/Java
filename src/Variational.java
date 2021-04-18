package owowo;

import java.util.ArrayList;
import java.util.Calendar;

public class Variational implements Analysis {
    private ArrayList<Double> xRowList;

    Variational(ArrayList<Double> list){
        xRowList = list;
    }

    public double getAvgSample() {
        return Calculations.averageSample(xRowList);
    }

    public double getDispSample() {
        return Calculations.dispersionSample(xRowList);
    }

    public double getStandartDeviation() {
        return Calculations.standartDeviation(xRowList);
    }

    public double getVar() {
        return (getStandartDeviation() / getAvgSample()) * 100;
    }


    @Override
    public void info() {
       String out = "_____________________________________  \n";
        out += "X = " + xRowList + "\n";
        out += "Indicators of variations \n";
        out += "Arithmetic mean X: " + getAvgSample() + "\n";
        out += "Dispersion X:" + getDispSample() + "\n";
        out += "Mean square deviation X: " + getStandartDeviation() + "\n";
        out += "Variation coefficient X: " + getVar() + "\n";

        System.out.println(out);
    }
}
