package owowo;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DataInput {
    public static ArrayList<Double> consoleInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter amount of Data:");
        int n = sc.nextInt();
        ArrayList<Double> rowList = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            try {
                rowList.add(sc.nextDouble());
            }catch (InputMismatchException e){
                System.out.println("Error: Entering numbers only!");
                i--;
                sc.next();
            }
        }

        sc.close();
        return rowList;
    }

    public static ArrayList<Double> randomInput() {
        final int MIN = 10;
        final int MAX = 99;
        final int SIZE = 25;
        ArrayList<Double> rowList = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            rowList.add(new Random().nextDouble());
        }
        return rowList;
    }

    public static ArrayList<Double> file(){
        System.out.println("Enter path file:");
        String path = new Scanner(System.in).nextLine();

        ArrayList<Double> rowList = null;
        try(FileReader reader = new FileReader(path)){
            Scanner sc = new Scanner(reader);
            rowList = new ArrayList<>();
            rowList.ensureCapacity(1000);

            while (sc.hasNext()) {
                if (sc.hasNextDouble()) {
                    rowList.add(sc.nextDouble());
                } else {
                    sc.next();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        rowList.trimToSize();

        return rowList;
    }
}

