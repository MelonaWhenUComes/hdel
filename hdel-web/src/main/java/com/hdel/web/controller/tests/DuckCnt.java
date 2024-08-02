package com.hdel.web.controller.tests;

import java.io.IOException;
import java.io.*;
import java.util.*;
public class DuckCnt {

    public static void main (String args[]) throws IOException {
// quqacukqauackck
// 2

// kcauq
// -1

// quackquackquackquackquackquackquackquackquackquack
// 1

// qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk
// 10

// quqaquuacakcqckkuaquckqauckack
// 3

// quackqauckquack
// -1

// 힌트
// 	예제 5의 경우에 다음과 같이 오리 3마리가 울었다고 할 수 있다.
// 	  녹음: quqaquuacakcqckkuaquckqauckack
// 	 1: ____q_u__a___ck_______________
// 	 2: __q__u_ac_k_q___ua__ckq_u__ack
// 	 3: qu_a_______c___k__qu___a_ck___
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String s1[] = br.readLine().split("");
        String s1[] = "quackqauckquack".split("");
        //quqaquuacakcqckkuaquckqauckack
        //XXqXquuaXaX
        int cnt = s1.length;
        String quack[] = {"q", "u", "a", "c", "k"};
        String comp[] = {"", "", "", "", ""};

        int pt = 0;
        int res = 0;
        boolean changed;
        for(int i = 0 ; i < cnt; i++ ) {
            changed = false;
            for(int j = i; j < cnt; j++) {
                if(quack[pt % 5].equals(s1[j])) {
                    if(!s1[j].equals("X")) {
                        changed = true;
                    }
                    pt++;

                    s1[j] = "X"; // X 로 바꿔줌

                }
            }
            if(changed) res++;
            //마지막에 ++ 함
//            for(int j = 0; j < cnt; j++) {
//                System.out.print(s1[j]);
//            }
//            System.out.println();

        }

        if(pt % 5 > 0) {
            System.out.println(-1);
            return;
        }

        for(int i = 0 ; i < cnt ; i++) {
            if(!s1[i].equals("X")) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(res);
    }
}

// 오리의 울음 소리는 "quack"이다. 올바른 오리의 울음 소리는 울음 소리를 한 번 또는 그 이상 연속해서 내는 것이다.
// 예를 들어, "quack", "quackquackquackquack", "quackquack"는 올바른 오리의 울음 소리이다.
// 영선이의 방에는 오리가 있는데, 문제를 너무 열심히 풀다가 몇 마리의 오리가 있는지 까먹었다.

// 갑자기 영선이의 방에 있는 오리가 울기 시작했고, 이 울음소리는 섞이기 시작했다.
// 영선이는 일단 울음소리를 녹음했고, 나중에 들어보면서 총 몇 마리의 오리가 있는지 구해보려고 한다.
// 녹음한 소리는 문자열로 나타낼 수 있는데, 한 문자는 한 오리가 낸 소리이다.
// 오리의 울음 소리는 연속될 필요는 없지만, 순서는 "quack"이어야 한다.
// "quqacukqauackck"과 같은 경우는 두 오리가 울었다고 볼 수 있다.
// 영선이가 녹음한 소리가 주어졌을 때, 영선이 방에 있을 수 있는 오리의 최소 개수를 구하는 프로그램을 작성하시오.

// 입력
// 첫째 줄에 영선이가 녹음한 소리가 주어진다. 소리의 길이는 5보다 크거나 같고, 2500보다 작거나 같은 자연수이고, 'q','u','a','c','k'로만 이루어져 있다.

// 출력
// 영선이 방에 있을 수 있는 오리의 최소 수를 구하는 프로그램을 작성하시오. 녹음한 소리가 올바르지 않은 경우에는 -1을 출력한다.

// 예제 입력 1 복사
// quqacukqauackck
// 예제 출력 1 복사
// 2

// 예제 입력 2 복사
// kcauq
// 예제 출력 2 복사
// -1

// 예제 입력 3 복사
// quackquackquackquackquackquackquackquackquackquack
// 예제 출력 3 복사
// 1

// 예제 입력 4 복사
// qqqqqqqqqquuuuuuuuuuaaaaaaaaaacccccccccckkkkkkkkkk
// 예제 출력 4 복사
// 10

// 예제 입력 5 복사
// quqaquuacakcqckkuaquckqauckack
// 예제 출력 5 복사
// 3

// 예제 입력 6 복사
// quackqauckquack
// 예제 출력 6 복사
// -1

// 힌트
// 	예제 5의 경우에 다음과 같이 오리 3마리가 울었다고 할 수 있다.
// 	  녹음: quqaquuacakcqckkuaquckqauckack
// 	 1: ____q_u__a___ck_______________
// 	 2: __q__u_ac_k_q___ua__ckq_u__ack
// 	 3: qu_a_______c___k__qu___a_ck___

// //0부터 찾음 qu a c k