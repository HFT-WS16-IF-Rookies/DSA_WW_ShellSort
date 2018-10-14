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

    public static int[] lukasSort(int[] input)
    {
        int[] tmp = new int[input.length];

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

            tmp[i] = min;
            tmp[toIndex-1] = max;
            input[indexMin] = input[fromIndex];
            input[indexMax] = input[toIndex-1];

            fromIndex++;
            toIndex--;
        }
        return tmp;
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
