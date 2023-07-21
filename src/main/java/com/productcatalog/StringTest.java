package com.productcatalog;

public class StringTest {

    public static void main(String str[]){
        System.out.println(checkS1inS2("abc", "abcd"));
        System.out.println(checkS1inS2("abc 1", "1abc d"));
        System.out.println(checkS1inS2("a", "1abc d  zero"));
        System.out.println(checkS1inS2("abx", "1abc d"));
        System.out.println(checkS1inS2("aaaaaaaaaa", "a"));
    }
    public static boolean checkS1inS2(String a,String b) {

        if(a==null || b==null)
            return  false;
        char[] first = a.toCharArray();
        char[] second = b.toCharArray();
        boolean isExists=true;
        for(int i=0;i<first.length;i++){
            isExists =  b.contains(String.valueOf(first[i]));
             if (!isExists)
                 break;
        }
        return isExists;
    }
}
