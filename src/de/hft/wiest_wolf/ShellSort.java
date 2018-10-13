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

        return input;
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
        return input[getIndexOfMax(input)];
    }

    public static int getMin(int[] input)
    {
        return input[getIndexOfMin(input)];
    }

    public static int getIndexOfMax(int[] input)
    {
        int result = input[0];
        int index = 0;
        for (int i=0; i < input.length; i++)
        {
            if (input[i] > result)
            {
                result = input[i];
                index = i;
            }
        }

        return index;
    }

    public static int getIndexOfMin(int[] input)
    {
        int result = input[0];
        int index = 0;
        for (int i=0; i < input.length; i++)
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
