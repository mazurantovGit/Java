import java.util.ArrayList;

public class CovData {
    private ArrayList<Double> xRowList = null;
    private ArrayList<Double> yRowList = null;
    private ArrayList<Double> yFromXList = null;
    private ArrayList<Double> list1 = null;
    private ArrayList<Double> list2 = null;


    public CovData(ArrayList<Double> xList) {
        this.xRowList = xList;

    }

    public CovData(ArrayList<Double> xList, ArrayList<Double> yList){
        this.xRowList = xList;
        this.yRowList = yList;
        this.yFromXList = new ArrayList<>(xRowList.size());
        this.list1 = new ArrayList<>(xRowList.size());
        this.list2 = new ArrayList<>(xRowList.size());
        addYFromXList();
    }

    public ArrayList<Double> getXList() {

        return xRowList;
    }

    public ArrayList<Double> getYList() {

        return yRowList;
    }

    public double getAvgSample(ArrayList<Double> list) {
        double avg = 0;
        for (Double pair : list) {
            avg += pair;
        }

        return avg / list.size();
    }

    public double getAvgSample(ArrayList<Double> list1, ArrayList<Double> list2) {
        double avg = 0;
        for(int i = 0; i < list1.size(); i++){
            avg += list1.get(i) * list2.get(i);
        }

        return avg / list1.size();
    }

    public double getDispSample(ArrayList<Double> list){
        double disp = 0;
        for(Double pair: list){
            disp += Math.pow(pair, 2);
        }
        disp /= list.size();

        if(yFromXList == null){
            return disp;
        }
        disp -= Math.pow(getAvgSample(list), 2);

        return disp;
    }

    public double getStandartDeviation(ArrayList<Double> list){

        return Math.sqrt(getDispSample(list));
    }

    public double getCorB(){

        return getCov() / getDispSample(xRowList);
    }

    public double getCorA(){
        double corA = 0;
        corA = getAvgSample(yRowList) - (getCorB() * getAvgSample(xRowList));

        return corA;
    }

    public double getCov(){
        double cov = getAvgSample(xRowList,yRowList);
        cov -= (getAvgSample(xRowList) * getAvgSample(yRowList));

        return cov;
    }

    public double getR(){
        return getCov() / (getStandartDeviation(xRowList) * getStandartDeviation(yRowList));
    }

    private void addYFromXList(){
        double tmp;
        for(int i = 0; i < xRowList.size(); i++){
            tmp = getR();
            tmp *= ((xRowList.get(i) - getAvgSample(xRowList)) / getStandartDeviation(xRowList)) * getStandartDeviation(yRowList);
            tmp += getAvgSample(yRowList);
            yFromXList.add(tmp);
        }

        for(int i = 0; i < yRowList.size(); i++){
            list1.add(Math.pow((yRowList.get(i) - getAvgSample(yRowList)), 2));
            list2.add(Math.pow((getAvgSample(yRowList) - yFromXList.get(i)), 2));
        }

    }

    private double getSumList(ArrayList<Double> list){
        double sum = 0;
        for (Double aDouble : list) {
            sum += aDouble;
        }

        return sum;
    }

    public double getDetermination(){
        return getSumList(list2) / getSumList(list1);
    }

    public double getVar(ArrayList<Double> list){
        double var = getStandartDeviation(list) / getAvgSample(list);

        return var * 100;
    }


    @Override
    public String toString() {
        String out = null;

        if(yRowList == null){
            out = "_____________________________________  \n";
            out += "X = " + xRowList + "\n";
            out += "Indicators of variations \n";
            out += "Arithmetic mean X: " + getAvgSample(xRowList) + "\n";
            out += "Dispersion X:" + getDispSample(xRowList) + "\n";
            out += "Mean square deviation X: " + getStandartDeviation(xRowList) + "\n";
            out += "Variation coefficient X: " + getVar(xRowList) + "\n";

            return out;
        }

        out = "_____________________________________  \n";
        out += "X = " + xRowList + "\n";
        out += "Y = " + yRowList + "\n";
        out += "Correlation and regression \n";
        out += "Regression equation parameters \n";
        out += "Sample mean: \n";
        out += "  X = " + getAvgSample(xRowList) + "\n";
        out += "  Y = " + getAvgSample(yRowList) + "\n";
        out += "  XY = " + getAvgSample(xRowList, yRowList) + "\n";
        out += "Sample variances: \n";
        out += "  D(x) = " + getDispSample(xRowList) + "\n";
        out += "  D(y) = " + getDispSample(xRowList) + "\n";
        out += "Standard deviation: \n";
        out += "  S(x) = " + getStandartDeviation(xRowList) + "\n";
        out += "  S(y) = " + getStandartDeviation(yRowList) + "\n";
        out += "Correlation coefficient: \n";
        out += "  b = " + getCorB() + "\n";
        out += "  a = " + getCorA() + "\n";
        out += "Covariance: \n";
        out += "  cov(x,y) = " + getCov() + "\n";
        out += "The linear correlation coefficient: \n";
        out += "  r = " + getR() + "\n";
        out += "Determination index : \n";
        out += "  R^2 = " + getDetermination();

        return out;

    }


}