package de.hft.wiest_wolf;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class ShellSort
{
    public static long startTime;
    public static long stopTime;

    public static void main(String[] args)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
//        DecimalFormat tf = new DecimalFormat("0000.0000 seconds");
        File resultFile = new File("DSA_WW_SortBench_" + df.format(new Date()));
        StringBuilder buf = new StringBuilder();

        final int TESTSIZE = 200000;
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

        int[] range;

        buf.append("array size: \t ");
        buf.append(TESTSIZE);
        buf.append("\n\n");

        buf.append("algorithm: \t Arrays.sort\n");
        buf.append("needed Time:\n");
        for (int i=0; i < 5; i++)
        {
            range = Arrays.copyOf(generated, generated.length);
            startTime = System.nanoTime();
            Arrays.sort(range);
            stopTime = System.nanoTime();
            buf.append("run ");
            buf.append(i+1);
            buf.append(": \t\t ");
            buf.append(String.format("%f seconds", ((double) (stopTime - startTime) / 1000000000d)));
            buf.append("\n");
        }
        buf.append("\n\n\n");
        System.out.println(buf);

        buf = new StringBuilder();
        buf.append("algorithm: \t lukasSort\n");
        buf.append("needed Time:\n");
        for (int i=0; i < 5; i++)
        {
            range = Arrays.copyOf(generated, generated.length);
            startTime = System.nanoTime();
            lukasSort(range);
            stopTime = System.nanoTime();
            buf.append("run ");
            buf.append(i+1);
            buf.append(": \t\t ");
            buf.append(String.format("%f seconds", ((double) (stopTime - startTime) / 1000000000d)));
            buf.append("\n");
        }
        buf.append("\n\n\n");
        System.out.println(buf);

        buf = new StringBuilder();
        buf.append("algorithm: \t bubblesort\n");
        buf.append("needed Time:\n");
        for (int i=0; i < 5; i++)
        {
            range = Arrays.copyOf(generated, generated.length);
            startTime = System.nanoTime();
            bubbleSort(range);
            stopTime = System.nanoTime();
            buf.append("run ");
            buf.append(i+1);
            buf.append(": \t\t ");
            buf.append(String.format("%f seconds", ((double) (stopTime - startTime) / 1000000000d)));
            buf.append("\n");
        }
        buf.append("\n\n\n");
        System.out.println(buf);
    }

    public static long erikSort(int[] input)
    {
        return stopTime - startTime;
    }

    public static int[] lukasSort(int[] input)
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

    public static int getMax(int[] input, int fromIndex, int toIndex)
    {
        return input[getIndexOfMax(input, fromIndex, toIndex)];
    }

    public static int getMin(int[] input, int fromIndex, int toIndex)
    {
        return input[getIndexOfMin(input, fromIndex, toIndex)];
    }

    public static int getIndexOfMax(int[] input, int fromIndex, int toIndex)
    {
        int result = input[0];
        int index = 0;
        for (int i=fromIndex; i < toIndex; i++)
        {
            if (input[i] > result)
            {
                result = input[i];
                index = i;
            }
        }

        return index;
    }

    public static int getIndexOfMin(int[] input, int fromIndex, int toIndex)
    {
        int result = input[0];
        int index = 0;
        for (int i=fromIndex; i < toIndex; i++)
        {
            if (input[i] < result)
            {
                result = input[i];
                index = i;
            }
        }

        return index;
    }

}
