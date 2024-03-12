package managers;

public class Validator {
    public static boolean isArrayConsistsOfOnlyNull(Object[] array) {
        for (Object element : array) {
            if (element != null) {
                return false;
            }
        }
        return true;
    }
    public static <T extends Number> boolean isCorrectNumber(String string, Class<T> type) {
        try {
            T number = type.getConstructor(String.class).newInstance(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
