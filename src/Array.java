import java.util.List;
import java.util.ArrayList;

public class Array {
    private List<int[]> intervals;
    private int[] data;
    private int size;
    private int dimension;

    public int getSize() {
        return size;
    }

    public int getDimension() {
        return dimension;
    }

    private int calculateSize(List<int[]> intervals) {
        int size = 1;
        for (int[] interval : intervals) {
            size *= interval[1] - interval[0] + 1;
        }
        data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        return size;
    }

    public Array(ArrayList<int[]> intervals, int dimension) {
        size = calculateSize(intervals);
        this.intervals = new ArrayList<>(intervals);
        this.dimension = dimension;
    }

    public int directAccess(int[] indices) {
        int index = indices[dimension - 1] - intervals.get(dimension - 1)[0];
        int product = 1;
        for (int i = dimension - 2; i >= 0; i--) {
            index += (indices[i] - intervals.get(i)[0]) * product;
            product *= intervals.get(i + 1)[1] - intervals.get(i + 1)[0] + 1;
        }
        return data[index];
    }

    public int accessAyleaf(int[] indices) {
        int index = indices[0] - intervals.get(0)[0];
        for (int i = 1; i < dimension; i++) {
            int product = 1;
            for (int j = 0; j < i; j++) {
                product *= intervals.get(j + 1)[1] - intervals.get(j + 1)[0] + 1;
            }
            index += (indices[i] - intervals.get(i)[0]) * product;
        }
        return data[index];
    }

    public void getDefiningVectors(int index, int[] rowVector, int[] colVector) {
        int product = 1;
        for (int i = dimension - 1; i >= 0; i--) {
            rowVector[i] = (index / product) % (intervals.get(i)[1] - intervals.get(i)[0] + 1) + intervals.get(i)[0] - 1;
            product *= intervals.get(i)[1] - intervals.get(i)[0] + 1;
        }
        product = 1;
        for (int i = 0; i < dimension; i++) {
            colVector[i] = (index / product) % (intervals.get(i)[1] - intervals.get(i)[0] + 1) + intervals.get(i)[0];
            product *= intervals.get(i)[1] - intervals.get(i)[0] + 1;
        }
    }

}
