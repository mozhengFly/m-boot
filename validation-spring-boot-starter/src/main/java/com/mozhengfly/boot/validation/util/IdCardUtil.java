package com.mozhengfly.boot.validation.util;

import com.mozhengfly.boot.validation.constants.ProvinceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 身份证号判断工具类
 * @author mozhengfly
 * @version 1.0.0
 */
@Slf4j
public final class IdCardUtil {

    private IdCardUtil() {}

    /**
     * 权重
     */
    private static final int[] powerArray = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    /**
     * 校验位
     */
    private static final String[] verifyCodeArray = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

    /**
     * 判断身份证号是否合法，只校验18位的身份证
     * @param idCard 身份证号
     * @return  true 是身份证号 false 不是身份证号
     */
    public static boolean isIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
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
        return checkLastCharacter(idCard);
    }

    /**
     * 得到省份code
     * @param idCard 身份证号
     * @return  省份code
     */
    private static String getProvinceCode(String idCard) {
        return idCard.substring(0, 2);
    }

    /**
     * 得到出生日期
     * @param idCard 身份证号
     * @return  出生日期
     */
    private static String getBirthday(String idCard) {
        return idCard.substring(6, 14);
    }

    /**
     * 正则校验
     * @param idCard    身份证号
     * @return  true 正则校验通过 false 正则校验不通过
     */
    private static boolean checkIdCard(String idCard) {
        String reg = "^\\d{17}[0-9Xx]$";
        return idCard.matches(reg);
    }

    /**
     * 校验省份
     * @param provinceId    省份ID
     * @return  true 省份ID正确 false 省份ID错误
     */
    private static boolean checkProvince(String provinceId) {
        return ProvinceEnum.isProvince(provinceId);
    }

    /**
     * 校验出生日期
     * @param birthday  出生日期
     * @return  true 出生日期正确 false 出生日期错误
     */
    private static boolean checkBirthday(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            // 出生年月日不正确
            return tmpDate.equals(birthday);
        } catch (ParseException e) {
            log.debug("非正确的出生日期...", e);
            return false;
        }
    }

    /**
     * 校验最后一位
     * @param idCard    身份证号
     * @return  true 最后一位正确 false 最后一位错误
     */
    private static boolean checkLastCharacter(String idCard) {
        char[] cardArray = idCard.toCharArray();
        int sum = 0;
        for (int i = 0; i < powerArray.length; i++) {
            sum += Integer.parseInt(String.valueOf(cardArray[i])) * powerArray[i];
        }
        return verifyCodeArray[sum % 11].toLowerCase().equals(idCard.substring(17, 18).toLowerCase());
    }
}
