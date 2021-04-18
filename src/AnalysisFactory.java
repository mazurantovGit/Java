package owowo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalysisFactory {
    private final String CORRELATIONAL_ANALYSIS = "CORRELATIONAL";
    private final String VARIATIONAL_ANALYSIS = "VARIATIONAL";

    public Analysis getAnalysis(String analysisType){
        if(VARIATIONAL_ANALYSIS.equals(analysisType)){
            ArrayList<Double> listX = choise();
            return new Variational(listX);
        }else if(CORRELATIONAL_ANALYSIS.equals(analysisType)){
            ArrayList<Double> listX = choise();
            ArrayList<Double> listY = choise();
            return new Correlational(listX, listY);
        }

        return null;
    }

    private ArrayList<Double> choise(){
        info();
        Scanner sc = new Scanner(System.in);
        int choise = sc.nextInt();

        if(choise == 1){
            return DataInput.consoleInput();
        }
        if(choise == 2){
            return DataInput.randomInput();
        }

        return DataInput.consoleInput();

    }

    private void info(){
        System.out.println("1 - Filling the console window");
        System.out.println("2 - Random filling");
        System.out.println("3 - Filling from file");
    }
}
