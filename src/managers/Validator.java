package managers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * @param arr with Objects
     * @return boolean
     */
    public static boolean isEmptyArray(Object[] arr) {
        return arr.length == 0;
    }

    /**
     * @param obj
     * @return boolean
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }


    /**
     * Сhecks if the name is correct
     * @param str
     * @return boolean
     */
    public static boolean isValidName(String str) {
        return !isNull(str) && !str.isEmpty();
    }

    /**
     * @param inputString
     * @return
     */
    public static boolean isStringWithIntegers(String inputString) {
        Pattern pattern = Pattern.compile("^\\s*\\d+\\s*(?:\\s+\\d+\\s*)*$");
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }
}
