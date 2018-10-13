package de.hft.wiest_wolf;

import java.util.Random;

public class ShellSort
{
    public static long startTime;
    public static long stopTime;

    public static void main(String[] args)
    {
        // TODO code application logic here
    }

    public static long erikSort(int[] input)
    {
        return stopTime - startTime;
    }

    public static long lukasSort(int[] input)
    {
        return stopTime - startTime;
    }

    public static int[] bubbleSort(int[] input)
    {
        throw new UnsupportedOperationException();
    }

    public static int[] generate(int size)
    {
        Random random = new Random();
        int[] result = new int[size];
        for (int i=0; i < size; i++)
            result[i] = random.nextInt();

        return result;
    }

    public static int getMax(int[] input)
    {
        int result = input[0];
        for (int i: input)
        {
            if (i > result)
                result = i;
        }

        return result;
    }

    public static int getMin(int[] input)
    {
        int result = input[0];
        for (int i: input)
        {
            if (i < result)
                result = i;
        }

        return result;
    }

}
