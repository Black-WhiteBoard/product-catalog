package com.productcatalog;

import java.util.*;
import java.util.stream.Collectors;

public class StringUtilTest {

    public static void main(String a[]) {
        List<String> data= new ArrayList<>();
         data.add("one");
        data.add("two");
        data.add("one");
        data.add("4");
        data.add("4");
        data.add("4");

        //System.out.println(Collections.frequency(data,"one"));
        System.out.println(findStringDuplicate(data));;
    }

    public static  Map findStringDuplicate(List<String> list) {
        return list.stream().filter(word-> Collections.frequency(list,word)>1).collect(Collectors.groupingBy(s->s,Collectors.counting()));
    }
}
