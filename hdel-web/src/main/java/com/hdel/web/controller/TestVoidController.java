package com.hdel.web.controller;

import java.io.*;
import java.util.*;

public class TestVoidController {
    public static void main(String[] args) throws IOException {
        List<Cls> tempList = new ArrayList<>();
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

       for(int i = 0 ; i < cnt; i++) {
           String sTemp[] = br.readLine().split(" ");
           Cls cls = new Cls(Integer.parseInt(sTemp[0]), Integer.parseInt(sTemp[1]));

           tempList.add(cls);
       }*/

        Cls cls = new Cls(3 , 4);
        tempList.add(cls);

        cls = new Cls(5 , 8);
        tempList.add(cls);

        cls = new Cls(8 , 1);
        tempList.add(cls);

        cls = new Cls(2 , 9);
        tempList.add(cls);

        Collections.sort(tempList, new ClsStartComparator().reversed());

        for (Cls c1 : tempList) {
            System.out.println(c1.start + " " + c1.end);
        }

        int xxx = 0;
        xxx ++;

        Iterator<Cls> it = tempList.iterator();

        while(it.hasNext()) {
            xxx ++;
            if(xxx == 3) it.remove();
        }
        for (Cls c1 : tempList) {
            System.out.println(c1.start + " " + c1.end);
        }

    }
}

class Cls {
    int start = 0;
    int end = 0;
    public Cls(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class ClsStartComparator implements Comparator<Cls> {
    @Override
    public int compare (Cls c1, Cls c2) {
        if(c1.start < c2.start) return 1;
        else if(c1.start > c2.start) return -1;
        else return 0;
    }
}