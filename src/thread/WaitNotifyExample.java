package thread;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 利用线程和Object的wait/notify实现阻塞队列
 *
 * @Author: Liam
 * @Date: 2019/3/20 19:30
 */
public class WaitNotifyExample {
    //1、需要一个承装元素的集合
    private final LinkedList<Object> list = new LinkedList<>();
    //2、需要一个计数器
    private final AtomicInteger count = new AtomicInteger(0);
    //3、需要指定上限和下限
    private final int maxSize = 5;
    private final int minSize = 0;
    //4、初始化锁对象
    private final Object lock = new Object();

    public void put(Object obj) {
        synchronized (lock) {
            //达到最大无法添加，进入等待
            while (count.get() == maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj); //加入元素
            count.getAndIncrement(); //计数器增加
            System.out.println(" 元素 " + obj + " 被添加 ");
            lock.notify(); //通知另外一个阻塞的线程方法
        }
    }

    public Object get() {
        Object temp;
        synchronized (lock) {
            //达到最小，没有元素无法消费，进入等待
            while (count.get() == minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count.getAndDecrement();
            temp = list.removeFirst();
            System.out.println(" 元素 " + temp + " 被消费 ");
            lock.notify();
        }
        return temp;
    }

    private int size() {
        return count.get();
    }

    public static void main(String[] args) throws Exception {
        final WaitNotifyExample blockingQueue = new WaitNotifyExample();
        initBlockingQueue(blockingQueue);

        Thread t1 = new Thread(() -> {
            blockingQueue.put("h");
            blockingQueue.put("i");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
                blockingQueue.get();
                Thread.sleep(2000);
                blockingQueue.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }

    private static void initBlockingQueue(WaitNotifyExample blockingQueue) {
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");
        blockingQueue.put("e");
        System.out.println("当前元素个数：" + blockingQueue.size());
    }
}
