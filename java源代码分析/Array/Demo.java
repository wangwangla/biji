import java.util.ArrayDeque;
import java.util.Arrays;

class Demo{
    public static void main(String[] args) {
        Arrays a =Arrays.asList("","");
        ArrayDeque ar = new ArrayDeque<>();
    }
    /**
     * 
     * @param arrayLength
     * @param fromIndex
     * @param toIndex
     */
    private static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > arrayLength) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }
    
}