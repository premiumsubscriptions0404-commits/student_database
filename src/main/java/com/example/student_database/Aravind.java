package com.example.student_database;

import java.util.*;

//public class Aravind {
//    public static boolean canLeap(int leap , int[] game){
//        return canLeap1(leap , game, 0);
//    }
//public static boolean canLeap1(int leap , int[] game, int i){
//    if (i > game.length) {
//        return true;
//    }
//    if(i<0||game[i]==1) return false;
//    game[i]=1;
//    return canLeap1(leap, game, i+leap) || canLeap1(leap,game, i+1) || canLeap1(leap, game, i-1);
//
//}
//    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        int[] game=new int[20];
//        int n=sc.nextInt();
//        while(n-->0){
//            int a=sc.nextInt();
//            int leap=sc.nextInt();
//            for (int i=0;i<a;i++)
//            {
//                game[i]=sc.nextInt();
//            }
//            System.out.println((canLeap(leap,game))? "Yes":"no");
//        }
//    }
//}




public class Aravind {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> d = new ArrayDeque<>();
        HashSet<Integer> h = new HashSet<>();

        int n = sc.nextInt();
        int m = sc.nextInt();
        int maxvalue = 0;
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            d.addLast(num);
            h.add(num);

            if(d.size()>m){
                int r=d.removeFirst();
                if(!d.contains(r)){
                    h.remove(r);
                }}
                maxvalue=Math.max(maxvalue, h.size());

            }
            System.out.println(maxvalue);

    }
}