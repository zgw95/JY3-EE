package com.neuedu.demo;

public class Demo {

    public static void main(String args[]){
        int num = 10;
        System.out.println(test(num));
    }

    public static int test(int b){
        try
        {
        b += 10;
        return b;
        }
        catch(RuntimeException e)
        {
            return 0;
        }
        finally
        {
        b += 10;
        return b;
        }
}


}
