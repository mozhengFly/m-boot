package com.mozhengfly.boot.validation.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 省份枚举类
 *
 * @author mozhengfly
 * @version 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ProvinceEnum {

    /**
     * 北京
     **/
    BEI_JING("11"),
    /**
     * 天津
     **/
    TIAN_JIN("12"),
    /**
     * 河北
     **/
    HE_BEI("13"),
    /**
     * 山西
     **/
    SHAN_XI("14"),
    /**
     * 内蒙古
     **/
    NEI_MENG_GU("15"),
    /**
     * 辽宁
     **/
    LIAO_NING("21"),
    /**
     * 吉林
     **/
    JI_LIN("22"),
    /**
     * 黑龙江
     **/
    HEI_LONG_JIANG("23"),
    /**
     * 上海
     **/
    SHANG_HAI("31"),
    /**
     * 江苏
     **/
    JIANG_SU("32"),
    /**
     * 浙江
     **/
    ZHE_JIANG("33"),
    /**
     * 安徽
     **/
    AN_HUI("34"),
    /**
     * 福建
     **/
    FU_JIAN("35"),
    /**
     * 江西
     **/
    JIANG_XI("36"),
    /**
     * 山东
     **/
    SHAN_DONG("37"),
    /**
     * 河南
     **/
    HE_NAN("41"),
    /**
     * 湖北
     **/
    HU_BEI("42"),
    /**
     * 湖南
     **/
    HU_NAN("43"),
    /**
     * 广东
     **/
    GUANG_DONG("44"),
    /**
     * 广西
     **/
    GUANG_XI("45"),
    /**
     * 海南
     **/
    HAI_NAN("46"),
    /**
     * 重庆
     **/
    CHONG_QING("50"),
    /**
     * 四川
     **/
    SI_CHUAN("51"),
    /**
     * 贵州
     **/
    GUI_ZHOU("52"),
    /**
     * 云南
     **/
    YUN_NAN("53"),
    /**
     * 西藏
     **/
    XI_ZANG("54"),
    /**
     * 陕西
     **/
    SHAN_XI_S("61"),
    /**
     * 甘肃
     **/
    GAN_SU("62"),
    /**
     * 青海
     **/
    QING_HAI("63"),
    /**
     * 宁夏
     **/
    NING_XIA("64"),
    /**
     * 新疆
     **/
    XIN_JIANG("65"),
    /**
     * 台湾
     **/
    TAI_WAN("71"),
    /**
     * 香港
     **/
    XIANG_GANG("81"),
    /**
     * 澳门
     **/
    AO_MEN("82"),
    /**
     * 国外
     **/
    GUO_WAI("91");

    private String code;

    private static Set<String> set = Arrays.stream(ProvinceEnum.values()).map(ProvinceEnum::getCode).collect(Collectors.toSet());

    /**
     * 判断是否是省份
     *
     * @param code 省份代码
     * @return true 是省份 false 不是省份
     */
    public static boolean isProvince(String code) {
        return set.contains(code);
    }

}
