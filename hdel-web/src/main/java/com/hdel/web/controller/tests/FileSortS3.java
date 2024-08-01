package com.hdel.web.controller.tests;
import java.util.*;
import java.io.IOException;

public class FileSortS3 {
// 1. 파일을 확장자 별로 정리해서 몇 개씩 있는지
// 2. 확장자들을 사전 순으로 정렬

// 입력
// 첫째 줄에 바탕화면에 있는 파일의 개수 N$N$이 주어진다. (1≤N≤50 000$1 \leq N \leq 50\ 000$)
// 둘째 줄부터 N$N$개 줄에 바탕화면에 있는 파일의 이름이 주어진다. 파일의 이름은 알파벳 소문자와 점(.)으로만 구성되어 있다.
// 점은 정확히 한 번 등장하며, 파일 이름의 첫 글자 또는 마지막 글자로 오지 않는다.
// 각 파일의 이름의 길이는 최소 3$3$, 최대 100$100$이다.

// 출력
// 확장자의 이름과 그 확장자 파일의 개수를 한 줄에 하나씩 출력한다. 확장자가 여러 개 있는 경우 확장자 이름의 사전순으로 출력한다.

// 입력 1 복사
// 8
// sbrus.txt
// spc.spc
// acm.icpc
// korea.icpc
// sample.txt
// hello.world
// sogang.spc
// example.txt

// 출력 1 복사
// icpc 2
// spc 2
// txt 3
// world 1

    public static void main (String args[]) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        //int cnt = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();
        String ss[] = {"sbrus.txt", "spc.spc", "acm.icpc", "korea.icpc", "sample.txt","hello.world", "sogang.spc", "example.txt"};
        // sbrus.txt
        // spc.spc
        // acm.icpc
        // korea.icpc
        // sample.txt
        // hello.world
        // sogang.spc
        // example.txt
        for(int i = 0 ; i < ss.length ; i++) {
            //String s1[] = br.readLine().split(".");
            String s1[] = ss[i].split("\\.");
            //String s1[] = "sbrus.txt".split("\\.");
            if(map.containsKey(s1[1])) {
                map.put(s1[1], map.get(s1[1]) + 1);
            } else {
                map.put(s1[1], 1);
            }
        }

        List<Ext> list = new ArrayList<>();
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            list.add(new Ext(key, map.get(key)));
        }

        Collections.sort(list, new Sort1());

        for(Ext e : list) {
            System.out.println(e.extNm + " " + e.cnt);
        }
    }
}

class Ext {
    String extNm = "";
    int cnt = 0;

    public Ext(String extNm, int cnt) {
        this.extNm = extNm;
        this.cnt = cnt;
    }
}

class Sort1 implements Comparator<Ext> {
    @Override
    public int compare(Ext e1, Ext e2) {
        return (e1.extNm).compareTo(e2.extNm); // 오름차순
    }
}
