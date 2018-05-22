package yee;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yee on 2018/5/15.
 */
public class Caculate {


    /**
     * 罗马字符
     */
    static Map<Character, Integer> roman = new HashMap<Character, Integer>();

    /**
     * 元素
     */
    static Map<String, BigDecimal> element = new HashMap<String, BigDecimal>();
    static final Pattern checker = Pattern.compile("((L{2})|(D{2})|(V{2})|(I{4})|(M{4})|(X{4})|(C{4}))");
    static final Pattern needCombine = Pattern.compile("((L|V|D)|(CD|CM)|(XL|XC)|(IV|IX)|(I{1,3})|(M{1,3})|(X{1,3})|(C{1,3}))");
    static final Pattern elements = Pattern.compile("((Silver|Gold|Iron))");
    static {
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
    }

    /**
     *
     * 初始化元素值
     *
     * @param prefix
     * @param suffix
     */
    static void initEliments(String prefix, String suffix) {
        Matcher matcher = elements.matcher(prefix);
        if(matcher.find()) {
            // 元素值
            String el = matcher.group();
            // 罗马转换值
            String result = caculateRoman(Convert.convetChar(prefix.replace(el, "").trim()));
            //总价格
            String totalPrice = suffix.replace("Credits","").trim();
            // 计算元素单价
            BigDecimal price = new BigDecimal(totalPrice).divide(new BigDecimal(result));
            // 初始化元素值
            element.put(el, price);
        }
    }

    /**
     * 计算罗马数字转换
     *
     * @param input 罗马字符
     * @return
     */
    public static String caculateRoman(String input) {
        if (input == null || input == "") {
            return "I have no idea what you are talking about";
        }

        int romanResult = 0;
        // 1.不允许有连续重复或连续重复三次以上
        Matcher m = checker.matcher(input);
        if (m.find()) {
            return "Error input char '" + m.group() + "'";
        } else {
            Matcher matcher = needCombine.matcher(input);
            while (matcher.find()) {
                String matched = matcher.group(1);
                int size = matched.length();
                char[] array = matched.toCharArray();

                // 单个字符
                if (size == 1) {
                    Integer single = roman.get(array[0]);
                    if (single != null) {
                        romanResult += single;
                    }
                } else { // 多个字符
                    int mResult = 0;
                    int before = -1, that = 0;
                    for (int i = 0; i < size; i++) {
                        that = roman.get(array[i]);
                        if (before != -1 && before < that) {
                            mResult = that - before;
                        } else {
                            mResult += that;
                        }
                        if (before == -1) {
                            before = that;
                        }
                    }
                    romanResult += mResult;
                }
            }
            return String.valueOf(romanResult);
        }
    }

    /**
     *
     * 计算元素
     *
     * @param input 元素
     * @return
     */
    public static String caculateElement(String input) {
        if (input == null || input == "") {
            return "I have no idea what you are talking about";
        }
        Matcher el = elements.matcher(input);
        if (el.find()) {
            if (element.containsKey(el.group())) {
                return element.get(el.group()).toString();
            }
        }else {
            return "I have no idea what you are talking about";
        }
        return null;
    }



}
