package com.mozhengfly.boot.verification.util;

import com.mozhengfly.boot.verification.constants.ProvinceEnum;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description IdCardUtil
 * @Author mozhengfly
 * @Date 2019-07-01 21:54:49
 * @Version V1.0.0
 * @Copyright mozhengfly@163.com
 */
public class IdCardUtil {

    private static final int[] powerArray = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    private static final String[] verifyCodeArray = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

    /**
     * 判断身份证号是否合法
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return false;
        }
        // 正则匹配校验
        if (!checkIdCard(idCard)) {
            return false;
        }
        // 校验省份
        if (!checkProvince(getProvinceCode(idCard))) {
            return false;
        }
        // 校验出生日期
        if (!checkBirthday(getBirthday(idCard))) {
            return false;
        }
        // 校验最后一位
        if (!checkLastCharacter(idCard)) {
            return false;
        }
        return true;
    }

    /**
     * 得到省份code
     * @param idCard
     * @return
     */
    private static String getProvinceCode(String idCard) {
        return StringUtils.substring(idCard, 0, 2);
    }

    /**
     * 得到出生日期
     * @param idCard
     * @return
     */
    private static String getBirthday(String idCard) {
        return StringUtils.substring(idCard, 6, 14);
    }

    /**
     * 正则校验
     * @param idCard
     * @return
     */
    public static boolean checkIdCard(String idCard) {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        return idCard.matches(reg);
    }

    /**
     * 校验省份
     * @param provinceId
     * @return
     */
    private static boolean checkProvince(String provinceId) {
        return ProvinceEnum.isProvince(provinceId);
    }

    /**
     * 校验出生日期
     * @param birthday
     * @return
     */
    private static boolean checkBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            // 出生年月日不正确
            return tmpDate.equals(birthday);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 校验最后一位
     * @param idCard
     * @return
     */
    private static boolean checkLastCharacter(String idCard) {
        char[] cardArray = idCard.toCharArray();
        int sum = 0;
        for (int i = 0; i < powerArray.length; i++) {
            sum += Integer.parseInt(String.valueOf(cardArray[i])) * powerArray[i];
        }
        return StringUtils.equalsIgnoreCase(idCard.substring(17, 18), verifyCodeArray[sum % 11]);
    }
}
