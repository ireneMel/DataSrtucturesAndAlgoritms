package homework.lesson3DataStructures;

import java.util.Iterator;

public class Tester {
    public static void main(String[] args) throws Exception{
        System.out.println("***Deque***");
        Deque<Integer> dq = new Deque<>();
        Iterator<Integer> iterator = dq.iterator();

        dq.addLast(2);
        dq.addLast(5);
        dq.addLast(1);


        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("size: "+dq.size()+"\n--------------------");

        dq.addFirst(11);
        dq.addFirst(9);
        dq.addFirst(13);


        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("size: "+dq.size()+"\n--------------------");

        System.out.println("removed (first): "+dq.removeFirst());
        System.out.println("removed (last): "+dq.removeLast());
        System.out.println("removed (last): "+dq.removeLast());

        for(Integer in: dq) {
            System.out.println(in);
        }

        System.out.println("size: "+dq.size()+"\n--------------------");

        System.out.println("***RandomizedQueueArray***");
        RandomizedQueueArray<Integer> rq = new RandomizedQueueArray<>();
        Iterator<Integer> rqIter = rq.iterator();

        rq.enqueue(0);
//        rq.enqueue(1);
//        rq.enqueue(4);
//        rq.enqueue(15);
//        rq.enqueue(2);

        while (rqIter.hasNext()) {
            System.out.println(rqIter.next());
        }
        System.out.println("size: "+rq.size()+"\n--------------------");

//        System.out.println("deleted: "+rq.dequeue());
//        System.out.println("deleted: "+rq.dequeue());
//        System.out.println("deleted: "+rq.dequeue());
        while (rqIter.hasNext()) {
            System.out.println(rqIter.next());
        }

        System.out.println("size: "+rq.size()+"\n--------------------");

        System.out.println("sample: "+rq.sample());
        System.out.println("sample: "+rq.sample());

        System.out.println("\n***ArrayQueue***");
        ArrayQueue<Integer> aq = new ArrayQueue<>();

        aq.enqueue(10);
        aq.enqueue(100);
        aq.enqueue(1);
        aq.enqueue(1000);

        for (Integer i:aq) {
            System.out.println(i);
        }
        System.out.println("size: "+aq.size() +" \n---------------------- ");
        aq.dequeue();
        aq.dequeue();
        for (Integer i:aq) {
            System.out.println(i);
        }
        System.out.println("size: "+aq.size() +" \n---------------------- ");

        System.out.println("***ArrayStack***");
        ArrayStack<Integer> as = new ArrayStack<>();
        as.push(33);
        as.push(3);
        as.push(34);
        as.push(1);
        for (Integer i:as) {
            System.out.println(i);
        }
        System.out.println("size: "+as.size() +" \n---------------------- ");
        as.pop();
        as.pop();
        for (Integer i:as) {
            System.out.println(i);
        }
        System.out.println("size: "+as.size() +" \n---------------------- ");
        System.out.println("***Bag***");

        Bag<Integer> b = new Bag<>();
        b.add(2);
        b.add(21);
        b.add(1);
        b.add(122);

        for (Integer i:b) {
            System.out.println(i);
        }
        System.out.println("size: "+b.size() +" \n---------------------- ");

        System.out.println("***LinkedQueue***");

        LinkedQueue<Integer> lq = new LinkedQueue<>();
        lq.enqueue(44);
        lq.enqueue(4);
        lq.enqueue(414);
        lq.enqueue(404);

        for (Integer i:lq) {
            System.out.println(i);
        }
        System.out.println("size: "+lq.size() +" \n---------------------- ");
        lq.dequeue();
        lq.dequeue();
        for (Integer i:lq) {
            System.out.println(i);
        }
        System.out.println("size: "+lq.size() +" \n---------------------- ");

        System.out.println("***LinkedStack***");
        LinkedStack<Integer> ls = new LinkedStack<>();
        ls.push(1);
        ls.push(8);
        ls.push(19);
        ls.push(191);

        for (Integer i:ls) {
            System.out.println(i);
        }
        System.out.println("size: "+ls.size() +" \n---------------------- ");
        ls.pop();
        ls.pop();
        for (Integer i:ls) {
            System.out.println(i);
        }
        System.out.println("size: "+ls.size() +" \n---------------------- ");

    }
}
