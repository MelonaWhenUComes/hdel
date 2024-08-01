package com.hdel.web.controller.tests;
import java.io.*;
import java.util.*;
public class CrossCountry {
    public static void main (String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 2
        // 15
        // 1 2 3 3 1 3 2 4 1 1 3 1 3 3 1
        // 18
        // 1 2 3 1 2 3 1 2 3 3 3 3 2 2 2 1 1 1
        //int cnt = Integer.parseInt(br.readLine());
        int cnt = 2;
        for(int i = 0 ; i < 1; i++) {
            //int member = Integer.parseInt(br.readLine());
            String st = "";
            int member = 0;
            if(i == 0 ) {
                member = 15;
                st = "1 2 3 3 1 3 2 4 1 1 3 1 3 3 1";
                //1 0 2 3 4 5 0 0 6 7 8 9 10 11 12
            }
            else {
                member = 18;
                st = "1 2 3 1 2 3 1 2 3 3 3 3 2 2 2 1 1 1";
            }
            String s1[] = st.split(" ");
            //String s1[] = br.readLine().split(" ");
            Map<String, Integer> map = new HashMap<>();
            for(int j = 0 ; j < member ; j++) {
                if(map.containsKey(s1[j])) {
                    map.put(s1[j], map.get(s1[j]) + 1);
                }
                else {
                    map.put(s1[j], 1);
                }
            }

            //6개 체크해서 팀과 점수를 리스트에 담음
            Iterator<String> iter = map.keySet().iterator();
            List<Score> list = new ArrayList<>();
            int temp = 1;
            for(int j = 0 ; j < member; j++) {
                iter = map.keySet().iterator();
                while(iter.hasNext()) {
                    String key = iter.next();
                    if(map.get(key) != 6 && key.equals(s1[j])) {
                        //list.add(new Score(key, 0));
                    } else if (map.get(key) == 6 && key.equals(s1[j])) {
                        list.add(new Score(key, temp++));
                    }
                    //System.out.println("key : " + key + " : " + temp);
                }
            }
            //팀으로 점수 합산하여 상위 4개 및 점수 합산시 동일한 점수의 경우 5개 합산
            //소팅한다 팀별 점수로 4개
            Collections.sort(list, new Comp());
            //상위 4개의 합계 구하기
            String sTemp = (list.get(0)).team;
            int icnt = 0;
            Map<String, Integer> resMap = new HashMap<>();
            for(Score s : list) {
                if(resMap.containsKey(s.team)) {
                    resMap.put(s.team, s.score + resMap.get(s.team));
                } else {
                    resMap.put(s.team, s.score);
                }

                System.out.println(s.team + " / " + s.score);
                // icnt += 1;
                // if(!sTemp.equals(s.team)) {
                //     s.score
                // }
            }
            int test1 = 1;
            // 팀별 4개의 합계 구함

        }
    }

}

class Score{
    String team = "";
    int score = 0;

    public Score (String team, int score) {
        this.team = team;
        this.score = score;
    }

}

class Comp implements Comparator<Score> {
    @Override
    public int compare (Score s1, Score s2) {

        if(s1.team.compareTo(s2.team) > 0) {
            return 1;
        } else if(s1.team.compareTo(s2.team) == 0) {
            return s1.score - s2.score;
        } else return -1;
    }
}
