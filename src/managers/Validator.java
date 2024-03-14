package managers;

/**
 * Class that consist of some static methods that check users' input.
 */
public class Validator {
    /**
     * @param array of some object
     * @return true if array is null
     */
    public static boolean isArrayConsistsOfOnlyNull(Object[] array) {
        for (Object element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param string parameter of Console
     * @param <T> type of class
     * @return true if type and parameter match
     */
    public static <T extends Number> boolean isCorrectNumber(String string, Class<T> type) {
        try {
            T number = type.getConstructor(String.class).newInstance(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
