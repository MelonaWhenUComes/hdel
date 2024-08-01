package com.hdel.web.controller.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Word {
    public static void main (String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //7 4
        //apple
        //ant
        //sand
        //apple
        //append
        //sand
        //sand
        //String s1[] = br.readLine().split(" ");
        //String s1[] = {"7", "4"};
        String s1[] = {"12", "5"};
        String s2[] = //{"apple", "ant", "sand", "apple", "append", "sand", "sand"};
                {
                        "appearance",
                        "append",
                        "attendance",
                        "swim",
                        "swift",
                        "swift",
                        "swift",
                        "mouse",
                        "wallet",
                        "mouse",
                        "ice",
                        "age"};
        int cnt = Integer.parseInt(s1[0]);
        int len = Integer.parseInt(s1[1]);
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < cnt ; i++) {
            //String s2 = br.readLine();

            if(s2[i].length() >= len) {
                if(map.containsKey(s2[i])) {
                    map.put(s2[i],map.get(s2[i])+1);
                }
                else {
                    map.put(s2[i], 1);
                }
            }
        }
        List<Word1> list = new ArrayList<>();
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            list.add(new Word1(key, map.get(key)));
        }

        Collections.sort(list, new Sort12());

        for(Word1 w : list) {

            //sb.append(w.word).append(" : ").append(w.size).append("\n");
            sb.append(w.word).append("\n");
        }
        System.out.println(sb);
    }
}

class Sort12 implements Comparator<Word1> {
    @Override
    public int compare(Word1 w1, Word1 w2) {
        if(w1.word.length() == w2.word.length() && w1.size == w2.size) {
            return w1.word.compareTo(w2.word);
        }
        else if(w1.size == w2.size) return w2.word.length() - w1.word.length();
        return w2.size - w1.size;
    }
}

class Word1 {
    String word = "";
    int size = 0;

    public Word1 (String word, int size) {
        this.word = word;
        this.size = size;
    }
}