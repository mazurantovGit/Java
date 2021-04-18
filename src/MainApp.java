package owowo;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnalysisFactory analysisFactory = new AnalysisFactory();
        Scanner sc = new Scanner(System.in);
        Analysis analysis;
        int input;
        do {
            System.out.println("_______________________________________");
            System.out.println("1 - Analysis of variance");
            System.out.println("2 - Сorrelation linear analysis x and y");
            System.out.println("0 - Exit");
            input = sc.nextInt();
            if (input == 1) {
                analysis = analysisFactory.getAnalysis("VARIATIONAL");
                analysis.info();
            }
            if (input == 2) {
                analysis = analysisFactory.getAnalysis("CORRELATIONAL");
                analysis.info();
                }
            else if (input == 0) {
                break;
                }
           } while (true) ;


    }
}
