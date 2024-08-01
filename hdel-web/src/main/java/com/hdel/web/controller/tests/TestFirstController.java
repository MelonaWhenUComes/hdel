package com.hdel.web.controller.tests;

import java.io.*;
import java.util.*;


public class TestFirstController {
    public static void main(String args[]) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String s1[] = br.readLine().split(" ");
//        int N = Integer.parseInt(s1[0]);
//        int score = Integer.parseInt(s1[1]);
//        int P = Integer.parseInt(s1[2]);
        int res = 1;

        int N = 10;
        int score = 1;
        int P = 10;
//        int parm[] = new int[N];
        //String s2[] = br.readLine().split(" ")
        //String s2[] = {"100", "90","80"};
//        for (int i = 0; i < N; i++) {
//            parm[i] = Integer.parseInt(s2[i]);
//        }
        int parm[] = {10, 9, 8, 7, 6, 5, 4, 3,3,0};
        //int parm[] = {100, 90, 80};
        //3 90 10
        //100 90 80
        //2

        if (P == N && score <= parm[P - 1]) {
            res = -1;
        } else {
            for (int i = 0; i < N; i++) {
                if (score < parm[i]) res++;
            }
        }
        System.out.println(res);
    }




        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

/** 1. **/
//        String input = "R G G R G G R";
//        int res = rgb(input);
//        System.out.println(res);

/** 2. **/
//        int iInput[] = {1, 2, 3, 4, 5};
//        int res[] = bSort(iInput);
//        for(int i = 0; i < res.length; i++) {
//            System.out.println(res[i]);
//        }

/** 3. **/
//        List<PointXY> list = new ArrayList<>();
//        PointXY xy = new PointXY(10, 30);
//        list.add(xy);
//
//        xy = new PointXY(10, 2);
//        list.add(xy);
//
//        xy = new PointXY(10, 50);
//        list.add(xy);
//
//        xy = new PointXY(10, 50);
//        list.add(xy);
//
//        Collections.sort(list, new YComparator().reversed());
//
//        for(PointXY xy1 : list) {
//            System.out.println(xy1.y);
//        }

        /****4 . ***/
//        int iInput[] = {500, 30, 3, 4, 1,2, 66, 12, 100};
//        int temp = iInput[0];
//        for(int i =0 ; i < iInput.length; i++) {
//            if(temp < iInput[i]) {
//                temp = iInput[i];
//            }
//        }
//        System.out.println(temp);





    public static int[] bSort(int[] iInput) {
        int size = iInput.length;
        int res[] = new int[size];
        int temp = 0;
        for(int i = 0 ; i < size -1; i++) {
            for(int j = i + 1; j < size; j++ ) {
                if(iInput[i] < iInput[j]) {
                    temp = iInput[i];
                    iInput[i] = iInput[j];
                    iInput[j] = temp;
                }
            }
        }
        return iInput;
    }


    public static int rgb(String sInput) {
        int res = 0;

        String sParm[] = sInput.split(" ");

        int cnt = sParm.length;
        int rCur = 0;
        int gCur = 0;
        int bCur = 0;
        for(int i = 0 ; i < cnt; ){
            //종료 시킴
            if(i == cnt-1) {
                res++;
                break;
            }
            for(int j = i + 1 ; j < cnt ; j++) {
                if("R".equals(sParm[j])) {
                    rCur = j;
                    break;
                }
            }
            for(int j = i + 1 ; j < cnt ; j++) {
                if ("G".equals(sParm[j])) {
                    gCur = j;
                    break;
                }
            }
            for(int j = i + 1 ; j < cnt ; j++) {
                if ("B".equals(sParm[j])) {
                    bCur = j;
                    break;
                }
            }

            if(rCur >= gCur && rCur >= bCur ) {
                i = rCur;
                res ++;
            } else if(bCur >= gCur && bCur >= rCur ) {
                i = bCur;
                res ++;
            } else if(gCur >= bCur && gCur >= bCur ) {
                i = rCur;
                res ++;
            }
        }
        return res;
    }




















    public static int[] bubbleSort(int[] param) {
        for(int i = 0 ; i < param.length ; i++) {
            for (int j = 0; j < param.length -1 ; j++) {
                if(param[j] < param[j+1]) {
                    int temp = param[j];
                    param[j] = param[j+1];
                    param[j+1] = temp;
                }
            }
        }
        return param;
    }
}





class PointXY {
    int x = 0;
    int y = 0;

    public PointXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class YComparator implements Comparator<PointXY> {
    @Override
    public int compare (PointXY y1, PointXY y2) {
        if(y1.y > y2.y) return 1;
        else if(y1.y < y2.y) return -1;
        else return 0;
    }
}


class ClsTemp {
    int start;
    int end ;

    public ClsTemp (int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class ClsComparator implements Comparator<ClsTemp>{
    @Override
    public int compare (ClsTemp c1, ClsTemp c2) {
        if(c1.start > c2.start) return 1;
        else if(c1.start < c2.start) return -1;
        else return 0;
    }

}





