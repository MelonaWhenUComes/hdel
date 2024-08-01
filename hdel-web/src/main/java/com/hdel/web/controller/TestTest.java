package com.hdel.web.controller;

import java.util.*;
import java.io.*;
public class TestTest {
    public static void main (String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        List<Cls1> list = new ArrayList<>();
        for(int i = 0 ; i < cnt; i++) {
            String temp[] = br.readLine().split(" ");
            Cls1 cls1 = new Cls1(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        Collections.sort(list, new Cls1Comparator().reversed());


    }
}
class Cls1 {
    int start = 0;
    int end = 0;
    public Cls1 (int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Cls1Comparator implements Comparator<Cls1> {
    @Override
    public int compare(Cls1 c1, Cls1 c2) {
        if(c1.start > c1.start) return 1;
        else if (c1.start < c1.start) return -1;
        else return 0;
    }
}
