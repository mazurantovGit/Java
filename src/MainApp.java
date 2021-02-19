import java.io.*;
import java.util.*;

public class MainApp {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        ArrayList<Double> xRow = null;
        ArrayList<Double> yRow = null;

        do{
            System.out.println("1 - Filling the console window");
            System.out.println("2 - Random filling");
            System.out.println("3 - Filling from file");
            System.out.println("0 - Exit");

            int input = sc.nextInt();
            int n;
            switch (input) {
                case 1 -> {
                    System.out.println("Amount of elements: ");
                    n = sc.nextInt();
                    System.out.println("First row: ");
                    xRow = consoleInput(n);
                    System.out.println("Second row: ");
                    yRow = consoleInput(n);
                }
                case 2 -> {
                    System.out.println("Amount of elements: ");
                    n = sc.nextInt();
                    xRow = randomInput(n);
                    yRow = randomInput(n);
                }
                case 3 -> {
                    sc.nextLine();
                    System.out.println("Path to a file: ");
                    System.out.println("X1:");
                    String pathY = sc.nextLine();
                    xRow = fileInput(pathY);
                    System.out.println("X2:");
                    pathY = sc.nextLine();
                    yRow = fileInput(pathY);
                }
                case 0 -> flag = false;
                default -> System.out.println("incorrect input");
            }

            if (flag) {
                do {
                    System.out.println("_______________________________________");
                    System.out.println("1 - Analysis of variance x1, x2");
                    System.out.println("2 - Сorrelation linear analysis x and y");
                    System.out.println("0 - New row of numbers");
                    input = sc.nextInt();
                    if (input == 1) {
                        CovData varX = new CovData(xRow);
                        CovData varY = new CovData(yRow);
                        System.out.println(varX);
                        System.out.println(varY);


                    } else if (input == 2) {
                        CovData covXY = new CovData(xRow, yRow);
                        System.out.println(covXY);

                    } else if (input == 0) {
                        break;
                    } else System.out.println("incorrect input");

                } while (true);
            }

        }while(flag);

    }



    public static ArrayList<Double> consoleInput(int n){
        ArrayList<Double> rowList = new ArrayList<>(n);
        Scanner sc = new Scanner(System.in);
        Double num;
        for(int i = 0; i < n; i++){
            try {
                rowList.add(sc.nextDouble());
            }catch (InputMismatchException e){
                System.out.println("Error: Entering numbers only!");
                i--;
                sc.next();
            }
        }

        return rowList;
    }

    public static ArrayList<Double> randomInput(int n) {
        final int MIN = 10;
        final int MAX = 99;

        ArrayList<Double> rowList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            rowList.add((double) (MIN + (int) (Math.random() * ((MAX - MIN) + 1))));
        }

        return rowList;
    }

    public static ArrayList<Double> fileInput(String path){
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Double> rowList = new ArrayList<>();
        rowList.ensureCapacity(1000);

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                rowList.add(scanner.nextDouble());
            } else {
                scanner.next();
            }
        }
        rowList.trimToSize();

        return rowList;
    }


}
