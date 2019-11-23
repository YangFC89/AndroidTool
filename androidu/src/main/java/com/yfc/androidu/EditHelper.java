package com.yfc.androidu;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

/**
 * 输入帮助类
 */

public class EditHelper {

    /**
     * 获得输入的字符串
     *
     * @param editText 输入框控件
     * @return
     */
    public static String getEditText(EditText editText) {
        if (editText == null) return "";
        return editText.getText().toString().trim();
    }

    /**
     * 输入是否为空
     *
     * @param editText 输入框控件
     * @return
     */
    public static boolean isEdit(EditText editText) {
        if (editText == null) return true;
        return TextUtils.isEmpty(getEditText(editText));
    }

    /**
     * 设置部分字体颜色
     *
     * @param str   字符串
     * @param color 设置颜色
     * @param start 起始位置
     * @param end   结束位置
     * @return
     */
    public static SpannableStringBuilder setTextViewSpanColor(String str, int color, int start, int end) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

}
