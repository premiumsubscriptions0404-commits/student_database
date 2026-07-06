package com.example.student_database.Controller;
class Counter{
    private int count=0;
    public synchronized   void increment(){
        count++;
    }

    public int getCounter() {
        return count;
    }
}


public class MyThread extends Thread{
    private  Counter counter;

    public MyThread(Counter counter) {
        this.counter=counter;
    }


    public void run(){
        for(int i=0;i<50000;i++){
        counter.increment();
    }}
    public static void main(String[] args) throws InterruptedException {
        Counter counter=new Counter();
        MyThread t=new MyThread(counter);
        MyThread t1=new MyThread(counter);
        t.start();
        t1.start();
        t.join();
        t1.join();
        System.out.println(counter.getCounter());

    }
}
