package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private UseListsAndMaps() {
    }

    public static void testHeadInsertPeformance(int elementNumber, List<Integer> list) {
        
        long time = System.nanoTime();

        for (int i = 1; i <= elementNumber; i++) {
            list.add(0, i);
        }

        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println("Took " + time + "ns (" + millis + "ms) to insert " + elementNumber + " elements at the head");
    }

    public static void testReadPerfomance(int index, int times, List<Integer> list) {
        long time = System.nanoTime();

        for (int i = 1; i <= times; i++) {
            int elem = list.get(index);
        }

        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println("Took " + time + "ns (" + millis + "ms) to read at index " + index + " for " + times + " times");
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */

        final List<Integer> arrList = new ArrayList<>();

        for (int num = 1000; num < 2000; num++) {
            arrList.add(num);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */

        final List<Integer> linkList = new LinkedList<>(arrList);
        
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

        final int tmp = arrList.get(arrList.size() - 1);
        arrList.set(arrList.size() - 1, arrList.get(0));
        arrList.set(0, tmp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer num : arrList) {
            System.out.println(num);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */

        final int newElements = 100_000;
        System.out.println("ArrayList:");
        UseListsAndMaps.testHeadInsertPeformance(newElements, arrList);
        System.out.println("LinkedList:");
        UseListsAndMaps.testHeadInsertPeformance(newElements, linkList);


        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */

         final int times = 1000;
         final int index = arrList.size() / 2;
         System.out.println("ArrayList:");
         UseListsAndMaps.testReadPerfomance(index, times, arrList);
         System.out.println("LinkedList:");
         UseListsAndMaps.testReadPerfomance(index, times, linkList);
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */

         Map<String, Long> continents = new HashMap<>();
         continents.put("Africa", 1_110_635_000L);
         continents.put("Americas", 972_005_000L);
         continents.put("Antartica", 0L);
         continents.put("Asia", 4_298_723_000L);
         continents.put("Europe", 742_452_000L);
         continents.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */

        long population = 0;
        for (String cont : continents.keySet()) {
            population += continents.get(cont);
        }

        System.out.println("The world population count is " + population);
    }
}
