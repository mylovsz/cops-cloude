package com.cops.library.comparator;

import java.util.Comparator;

/**
 * TODO
 *
 * @Author: wangermao
 * @Date: 2020-05-13 15:18
 * @Version: V1.0
 */
public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
