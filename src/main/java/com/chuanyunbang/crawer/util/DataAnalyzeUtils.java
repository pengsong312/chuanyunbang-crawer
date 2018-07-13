package com.chuanyunbang.crawer.util;

import com.chuanyunbang.crawer.constant.BaseConstants;
import com.chuanyunbang.crawer.entity.WeChatData;
import com.chuanyunbang.crawer.enums.AnalyzeTypeEnum;
import com.chuanyunbang.crawer.enums.IdentityEnum;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luffy
 * @date 2018/7/9
 * @description 数据分析
 */
public class DataAnalyzeUtils {

    /**
     * 移动电话号码正则
     */
    private static final String MOBILE_REGEX = "((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}";
    /**
     * 固定电话号码正则
     */
    private static final String TEL_REGEX = "(0\\\\d{2}-\\\\d{8}(-\\\\d{1,4})?)|(0\\\\d{3}-\\\\d{7,8}(-\\\\d{1,4})?)";


    /**
     * 分析数据行中的用户身份
     *
     * @param msg 用户单条数据msg
     */
    public static final WeChatData analyze(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return null;
        }

        int hitShipOwnerNums = analyzeIdentity(msg, AnalyzeTypeEnum.SHIP_OWNER_TYPE);
        int hitGoodsOwnerNums = analyzeIdentity(msg, AnalyzeTypeEnum.GOODS_OWNER_TYPE);
        IdentityEnum identity = hitShipOwnerNums > hitGoodsOwnerNums ?
                IdentityEnum.SHIP_OWNER :
                hitShipOwnerNums == hitGoodsOwnerNums ? IdentityEnum.OTHER_OWNER : IdentityEnum.GOODS_OWNER;
        // 获取移动电话
        Set<String> mobileNumbers = extractTelNumber(msg, MOBILE_REGEX);
        Set telNumbers = extractTelNumber(msg, TEL_REGEX);

        Set<String> phoneNumbers = Sets.newHashSet();
        if (!mobileNumbers.isEmpty()) {
            phoneNumbers.addAll(mobileNumbers);
        }
        if (!telNumbers.isEmpty()) {
            phoneNumbers.addAll(telNumbers);
        }

        WeChatData weChatData = WeChatData.builder().telNums(phoneNumbers).identityEnum(identity).msg(msg).build();
        return weChatData;
    }

    /**
     * 分析消息来源是货主还是船主
     */
    public static int analyzeIdentity(String msg, AnalyzeTypeEnum analyzeTypeEnum) {
        int hitNums = 0;
        if (StringUtils.isEmpty(msg)) {
            return hitNums;
        }
        // 判断msg中是否含有船主常用关键词
        if (analyzeTypeEnum.equals(AnalyzeTypeEnum.SHIP_OWNER_TYPE)) {
            String shipOwnerConfig = ConfigUtils.getString(BaseConstants.SHIP_OWNER_KEY);
            shipOwnerConfig = "[寻,空船,装空船,船舶]";
            List<String> keyword = new Gson().fromJson(shipOwnerConfig, new TypeToken<List>() {
            }.getType());
            hitNums = hitNums(msg, keyword);
        } else if (analyzeTypeEnum.equals(AnalyzeTypeEnum.GOODS_OWNER_TYPE)) {
            // 判断msg中是否含有货主常用关键词
            String goodsOwnerConfig = ConfigUtils.getString(BaseConstants.GOODS_OWNER_KEY);
            goodsOwnerConfig = "[需,急需,一条,装,要,须,吨,卸货,货]";
            List<String> keyword = new Gson().fromJson(goodsOwnerConfig, new TypeToken<List>() {
            }.getType());
            hitNums = hitNums(msg, keyword);
        }

        return hitNums;
    }

    public static final int hitNums(String msg, List<String> keyword) {
        int hitNums = 0;
        if (Objects.isNull(keyword) || keyword.isEmpty()) {
            return hitNums;
        }
        for (String s : keyword) {
            if (msg.contains(s)) {
                hitNums++;
            }
        }
        return hitNums;
    }

    /**
     * 抽取msg中的电话号码，可能是多个
     */
    public static Set<String> extractTelNumber(String msg, String regex) {
        // 将给定的正则表达式编译到模式中
        Pattern pattern = Pattern.compile(regex);
        // 创建匹配给定输入与此模式的匹配器。
        Matcher matcher = pattern.matcher(msg);
        Set<String> telNumbers = Sets.newHashSet();
        //查找字符串中是否有符合的子字符串
        while (matcher.find()) {
            //查找到符合的即输出
            telNumbers.add(matcher.group());
        }
        return telNumbers;
    }
}
