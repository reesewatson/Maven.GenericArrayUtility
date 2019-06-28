package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility <T>{

    private T[] inputArray;

    public ArrayUtility(T[] inputArray) {
        this.inputArray = inputArray;
    }

    public T[] removeValue(T valueToRemove) {
        Stream<T> stream = Stream.of(inputArray);
        Object[] objects = stream.filter(t -> !t.equals(valueToRemove)).toArray();
        T[] answer = (T[]) Array.newInstance(inputArray.getClass().getComponentType(), objects.length);
        System.arraycopy(objects, 0, answer, 0, objects.length);
        return answer;
    }

    //Helper method.
    public T[] merge(T[] arrayToMerge) {
        return (T[]) Stream.concat(Stream.of(inputArray), Stream.of(arrayToMerge)).toArray();
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        merge(arrayToMerge);
        return getNumberOfOccurrences(valueToEvaluate);
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T[] mergedArray = merge(arrayToMerge);
        int max = 0;
        for(int i = 1; i <  mergedArray.length; i++){
            if (countDuplicatesInMerge(arrayToMerge, mergedArray[i]) >
                    (countDuplicatesInMerge(arrayToMerge, mergedArray[max]))){
                max = i;
            }
        }
        return mergedArray[max];
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        List list = Arrays.asList(inputArray);
        Integer count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(valueToEvaluate)){
                count++;
            }
        } return count;
    }
}
