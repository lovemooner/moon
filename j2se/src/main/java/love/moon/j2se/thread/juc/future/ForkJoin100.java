package love.moon.j2se.thread.juc.future;

import java.util.concurrent.*;


/**
 * 求最大数
 */
public class ForkJoin100 {


    public static void main(String... args) throws ExecutionException, InterruptedException, TimeoutException {
        int[] array = {100, 400, 200, 90, 80, 300, 600, 10, 20, -10, 30, 2000, 1000};
        SumTask task = new SumTask(array, 0, array.length - 1);

        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(task);
        System.out.println("Result:" + future.get(1, TimeUnit.SECONDS));

    }

    private static class SumTask extends RecursiveTask<Integer> {
        private int[] numbers;
        private int from;
        private int to;

        public SumTask(int[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        static final int THRESHOLD = 6;

        /**
         * 任务拆分  拆分的好坏决定了效率的高低
         */
        @Override
        protected Integer compute() {
            if (to - from < THRESHOLD) {
                int total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            } else {
                //任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                taskLeft.fork();
                taskRight.fork();
                return taskLeft.join() + taskRight.join();
            }
        }
    }


}
