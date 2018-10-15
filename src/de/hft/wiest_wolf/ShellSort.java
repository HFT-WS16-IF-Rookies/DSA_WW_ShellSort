package de.hft.wiest_wolf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ShellSort
{
    public static long startTime;
    public static long stopTime;

    enum Algo
    {
        ARRAYSSORT("Arrays.sort")
        {
            @Override
            public void sort(int[] values)
            {
                Arrays.sort(values);
            }
        },
        LUKASSORT("Lukas-Sort")
        {
            @Override
            public void sort(int[] values)
            {
                wwSort(values);
            }
        },
        BUBBLESORT("Bubblesort")
        {
            @Override
            public void sort(int[] values)
            {
                bubbleSort(values);
            }
        };

        String name;

        Algo(String name)
        {
            this.name = name;
        }

        public abstract void sort(int[] values);

        @Override
        public String toString()
        {
            return name;
        }
    }

    public static void main(String[] args)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
//        DecimalFormat tf = new DecimalFormat("0000.0000 seconds");
        String resultFile = "DSA_WW_SortBench_" + df.format(new Date());
        StringBuilder buf = new StringBuilder();

        final int TESTSIZE = 10000;
        int[] generated = generate(TESTSIZE);

//        for (int i: generated)
//            System.out.println(i);
//
//        generated = new int[] {5,8,9,3,4,6,2,8,0,10,20,3,5,4,6, 17};
//        System.out.println("\n\nsorted:\n");
//        generated = lukasSort(generated);
//        for (int i: generated)
//            System.out.println(i);
//        System.exit(0);

        benchmark(TESTSIZE);
        System.exit(0);
    }

    public static void benchmark(int size)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String resultFile = "DSA_WW_SortBench_" + df.format(new Date());
        long[] runs;
        int[] generated = generate(size);
        int[] range;

        for (Algo alg: Algo.values())
        {
            System.out.println("algorithm:\t" + alg);
            System.out.println("array-size:\t" + size);
            System.out.println("needed Time:");

            runs = new long[10];
            long avg = 0;
            for (int i=0; i < runs.length; i += 2)
            {
                range = Arrays.copyOf(generated, generated.length);
                runs[i] = System.nanoTime();
                alg.sort(range);
                runs[i+1] = System.nanoTime();
                avg += runs[i+1];
                avg -= runs[i];
                System.out.println("run " + (i/2) + ":\t\t" + ((runs[i+1] - runs[i]) / 1000000000d) + "\tseconds");
            }
            System.out.println("\n\n");
            avg /= (runs.length / 2);

            try
            {
                File file = new File(resultFile + "_" + alg);
                if (!file.exists())
                {
                    file.createNewFile();
                }
                PrintWriter pw = new PrintWriter(new FileOutputStream(file, true), true);
                pw.println(size + "\t" + (avg / 1000000000d));
                pw.close();
            }
            catch (IOException e)
            {
                System.err.println("Couldn't save runtime for algorithm: " + alg + " for size: " + size);
                e.printStackTrace();
            }
        }
    }

    public static int[] wwSort(int[] input)
    {
        int indexMin = 0;
        int min;
        int indexMax = input.length-1;
        int max;
        int fromIndex = 0;
        int toIndex = input.length;
        int loops = input.length % 2 == 0 ? input.length / 2 : input.length / 2 +1;
        int innerLoops = loops;

        for (int i=0; i < loops; i++, innerLoops--)
        {

            min = input[fromIndex];
            max = input[toIndex-1];

            for (int j=0, innerFromIndex=fromIndex, innerToIndex=toIndex-1; j < innerLoops; j++, innerFromIndex++, innerToIndex--)
            {
                if(input[innerFromIndex] < min)
                {
                    min = input[innerFromIndex];
                    indexMin = innerFromIndex;
                }
                else if (input[innerFromIndex] > max)
                {
                    max = input[innerFromIndex];
                    indexMax = innerFromIndex;
                }

                if (input[innerToIndex] > max)
                {
                    max = input[innerToIndex];
                    indexMax = innerToIndex;
                }
                else if(input[innerToIndex] < min)
                {
                    min = input[innerToIndex];
                    indexMin = innerToIndex;
                }
            }

            input[indexMin] = input[fromIndex];
            input[indexMax] = input[toIndex-1];
            input[fromIndex] = min;
            input[toIndex-1] = max;

            fromIndex++;
            toIndex--;
        }
        return input;
    }

    public static void bubbleSort(int[] input)
    {
        boolean changed = true;
        int dest = input.length -1;
        while (changed)
        {
            changed = false;
            for (int i=0; i < dest; i++)
            {
                if (input[i] > input[i+1])
                {
                    int tmp     = input[i];
                    input[i]    = input[i+1];
                    input[i+1]  = tmp;
                    changed = true;
                }
            }
            dest--;
        }
    }

    public static int[] generate(int size)
    {
        Random random = new Random();
        int[] result = new int[size];
        for (int i=0; i < size; i++)
            result[i] = random.nextInt();

        return result;
    }
}
