package gameOfLife;

import java.util.*;
import java.util.function.ToIntFunction;

public class GameUtils {

    private static Scanner scanner = new Scanner(System.in);

    public static int getTimeFromUser(){
        return scanner.nextInt();
    }

    public static Set<Cell> getCellsFromUser(){

        Set<Cell> livedCells = new HashSet<>();

        try{
            while(true){
                String x = scanner.next();
                if(x.equals("start")) break;
                int y = scanner.nextInt();
                livedCells.add(Cell.live(Integer.valueOf(x),y));
                System.out.println("komorka dodana "+x+","+y);
            }
        }catch(InputMismatchException exception){
            System.out.println("Wprowadzono błędne dane.");
        }

        return livedCells;
    }

    public static int moda(ArrayList<Integer> list){
        int moda=list.get(1),count,maxcount=0;

        for(int elementi : list){
            count=0;
            for(int elementj : list){
                //if(elementj!=0) System.out.println(elementj);

                if(elementi==elementj){
                    count++;
                }
                if(maxcount<count){
                    moda=elementj;
                    maxcount=count;
                }
            }
        }
        return moda;
    }

    public static float srednia(ArrayList<Integer> list){
        float srednia=suma(list);
        int dzielenie=0;

        for(int element : list){
            if(element!=0) dzielenie++;
        }
        return srednia/dzielenie;
    }

    public static int suma(ArrayList<Integer> list){
        int suma=0;
        for(int element : list){
            suma+=element;
        }
        return suma;
    }

    public static float mediana(ArrayList<Integer> list){
        list.sort((int1,int2) -> int1-int2);
        System.out.println(list.size());
        for(int element : list){
            System.out.print(element+ " ");
        }
        if(list.size()%2 != 0){
            float sr = list.get(list.size()/2-1)+list.get(list.size()/2);
            return sr/2;
        }
        else{
            return (float)list.get(list.size()/2);
        }
    }
}
