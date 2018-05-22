package yee;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yee on 2018/5/16.
 */
public class Convert {

    static Map<String, String> elementConvert = new HashMap<String, String>();
    private StringBuilder patterns = new StringBuilder();
    static Pattern  pattern = null;


    /**
     *
     * 前缀
     *
     * @param input
     * @return
     */
    public String subPrefix(String input) {
        input = input.replace("?"," ");
        String[] arr = input.split(" is ");
        input = arr[0];
        return input;
    }

    /**
     *
     * 后缀
     *
     * @param input
     * @return
     */
    public String subSuffix(String input) {
        input = input.replace("?"," ");
        String[] arr = input.split(" is ");
        input = arr[1];
        return input;
    }

    /**
     *
     * 判断输入是初始化值还是计算
     *
     * @param input
     * @return
     */
    public String judgment(String input) {
        if (!input.contains("is")) {
            return "I have no idea what you are talking about";
        }
        String prefix = subPrefix(input);
        String suffix = subSuffix(input);
        if (suffix.trim().length() == 1) {
            initconvertData(prefix, suffix);
            return "";
        } else if (suffix.endsWith("Credits")) {
            Caculate.initEliments(prefix,suffix);
            return "";
        }else {
            return print(input, prefix, suffix);
        }
    }

    /**
     *
     * 初始化
     *
     * @param prefix
     * @param suffix
     */
    public void initconvertData(String prefix, String suffix) {
        Map<Character, Integer> romans = Caculate.roman;
        // 判断是否需要初始化数据
        String roman = suffix.trim();
        String newPattern = prefix.trim();
        if (romans.containsKey(roman.toCharArray()[0])) {
            elementConvert.put(newPattern, roman);
            patterns.append("|" + newPattern);
        }
        pattern = Pattern.compile("(" + patterns.toString().substring(1,patterns.length()) + ")");
    }

    /**
     *
     * 计算并打印
     *
     * @param input
     * @param prefix
     * @param suffix
     * @return
     */
    public String print(String input,String prefix, String suffix) {
        Pattern elements = Caculate.elements;
        if (pattern == null) return "erro!";
        if (!elements.matcher(convetChar(input)).find() && !pattern.matcher(input).find()) {
            return "I have no idea what you are talking about";
        }
        if(prefix.toLowerCase().contains("how much")) {
            return suffix + "is " + Caculate.caculateRoman(convetChar(suffix));
        }
        else if (prefix.toLowerCase().contains("how many")) {
            Matcher matcher = elements.matcher(convetChar(suffix));

            String el = "";
            while (matcher.find()) {
                el = matcher.group();
            }
            String roman = suffix.replace(el, "");

            String romanRet = Caculate.caculateRoman(convetChar(roman));
            String elRet = Caculate.caculateElement(el);
            return suffix + "is " + new BigDecimal(elRet).multiply(new BigDecimal(romanRet)) + " Credits";
        }else {
            return "I have no idea what you are talking about";
        }
    }


    /**
     *
     * 优化字符
     *
     * @param input
     * @return
     */
    public static String convetChar(String input) {
        String element = "";
        if (pattern == null) throw new RuntimeException("erro!");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            while (matcher.find()) {
                element = matcher.group();
                if (elementConvert.containsKey(element)) {
                    input = input.replace(element, elementConvert.get(element));
                }
            }
            return input.replace(" ","");
        }else {
            throw new RuntimeException(input + " is not exist");
        }
    }
}
