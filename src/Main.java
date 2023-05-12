import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int DIMENSION = 4;
        ArrayList<int[]> intervals = new ArrayList<>();
        intervals.add(new int[] {1, 4});
        intervals.add(new int[] {-8, -6});
        intervals.add(new int[] {0, 2});
        intervals.add(new int[] {-2, 0});

        System.out.println("Intervals:");
        for (int[] interval : intervals) {
            System.out.println(interval[0] + "\t" + interval[1]);
        }

        Array array = new Array(intervals, DIMENSION);
        int[] indices = {5, -3, 5, -4};

        long startTime = System.nanoTime();
        System.out.println(array.directAccess(indices));
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println("Direct access execution time = " + duration + " nanoseconds");

        startTime = System.nanoTime();
        System.out.println(array.accessAyleaf(indices));
        endTime = System.nanoTime();

        duration = (endTime - startTime);
        System.out.println("Ayleaf access execution time = " + duration + " nanoseconds");

        int[] rowVector = new int[DIMENSION];
        int[] colVector = new int[DIMENSION];

        startTime = System.nanoTime();
        array.getDefiningVectors(1000, rowVector, colVector);
        endTime = System.nanoTime();

        long durationDefining = (endTime - startTime);
        System.out.println("Defining vectors method execution time = " + durationDefining + " nanoseconds");
    }
}
