package com.yfc.androidu;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringHelper {

    /**
     * 获得输入的字符串
     *
     * @param editText
     * @return
     */
    public static String getEditText(EditText editText) {
        if (editText == null) return "";
        return editText.getText().toString().trim();
    }

    /**
     * 输入是否为空
     *
     * @param editText
     * @return
     */
    public static boolean isEdit(EditText editText) {
        if (editText == null) return true;
        return TextUtils.isEmpty(getEditText(editText));
    }

    /**
     * 判断是否包含数字和字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    /**
     * 手机号判断
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 字符串转字符数组
     *
     * @param lists
     * @return
     */
    public static List<String> getStringList(String lists) {
        List<String> stringList = new ArrayList<>();
        String[] strings = lists.split(",");
        for (int i = 0; i < strings.length; i++) {
            boolean add = stringList.add(strings[i]);
        }
        return stringList;
    }
}
