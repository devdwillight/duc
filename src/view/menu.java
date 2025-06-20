package view;


import utils.InputvalidationException;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class menu<T> {
    protected String title;
    protected ArrayList<T> mChon;
    protected Validation validation = new Validation();

    public menu(){}

    public menu(String td, String[] mc){
        title=td;
        mChon= new ArrayList<>();
        for(String s:mc) mChon.add((T) s);
    }
    //-------------------------------------------
    public void display(){
        System.out.println(title);
        System.out.println("--------------------------------");
        for(int i=0; i<mChon.size();i++)
            System.out.println((i+1)+"."+mChon.get(i));
        System.out.println("--------------------------------");
    }
    //-------------------------------------------
    public int getSelected(){
        display();
        Scanner sc= new Scanner(System.in);
        int select = validation.inputInt("Enter selection", 1,6  );
        return select;
    }
    public int getSelected1(){
        display();
        Scanner sc= new Scanner(System.in);
        int select = validation.inputInt("Enter selection", 1,4  );
        return select;
    }
    //-------------------------------------------
    public abstract void execute(int ch) throws InputvalidationException;
    //-------------------------------------------
    public void run() throws InputvalidationException {
        while(true){
            int n=getSelected();
            if(n<=mChon.size())execute(n);
            else break;
        }
    }
    public void run1() throws InputvalidationException {
        while(true){
            int n=getSelected1();
            if(n<=mChon.size())execute(n);
            else break;
        }
    }
//-------------------------------------------
}


