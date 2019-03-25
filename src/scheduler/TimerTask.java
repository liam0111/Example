package scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * 使用JDK自带类Timer和TimerTask实现定时任务
 * @Author: Liam
 * @Date: 2019/3/25 19:04
 */
public class TimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 2);
        cal.set(Calendar.SECOND, 0);
        if ((cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) < 0) {
            cal.add(Calendar.HOUR_OF_DAY, 1);
        }
        Task task = new TimerTask().new Task();
        timer.schedule(task, cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis(), 1000*60);
    }

    private class Task extends java.util.TimerTask {
        @Override
        public void run() {
            System.out.println("This is a TimerTask!" + new Date());
        }
    }

}
