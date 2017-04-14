package love.util;

import org.apache.commons.lang.ArrayUtils;

/**
 * User: lovemooner
 * Date: 17-3-27
 * Time: 下午4:59
 */
public class ArrayUtil extends ArrayUtils {

	public static boolean isNotEmpty(Object[] array) {
		return !isEmpty(array);
	}

	public static <T> T getFirst(T[] list) {
		if (isEmpty(list))
			return null;
		return list[0];
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		if (ArrayUtils.isEmpty(array))
			return true;
		// 元素有length无元素的
		for (Object o : array) {
			if (o != null)
				return false;
		}
		return true;
	}
}
