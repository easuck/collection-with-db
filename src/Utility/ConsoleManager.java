package Utility;

import java.util.Scanner;

public class ConsoleManager {

    private Scanner scanner = new Scanner(System.in);

    public String readString(){
        if (!ifScannerHasNext()){
            exit();
        }
        return scanner.nextLine();
    }

    public int readInt(){
        if(!ifScannerHasNext()){
            exit();
        }
        return scanner.nextInt();
    }

    public float readFloat(){
        if(!ifScannerHasNext()){
            exit();
        }
        return scanner.nextFloat();
    }

    public double readDouble(){
        if(!ifScannerHasNext()){
            exit();
        }
        return scanner.nextDouble();
    }

    public long readLong(){
        if(!ifScannerHasNext()){
            exit();
        }
        return scanner.nextLong();
    }

    public boolean ifScannerHasNext(){
        return scanner.hasNextLine();
    }

    public void print(Object out){
        System.out.print(out);
    }

    public void println(Object out){
        System.out.println(out);
    }

    public void exit(){
        System.exit(0);
    }
}
