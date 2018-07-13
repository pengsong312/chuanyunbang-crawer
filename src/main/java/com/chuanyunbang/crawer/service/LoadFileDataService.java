package com.chuanyunbang.crawer.service;

/**
 * @author Luffy
 * @date 2018/7/12
 * @description 数据加载
 */
public interface LoadFileDataService {

    /**
     * 加载数据并分析
     *
     * @param filePath 文件路径
     * @param fileName 文件名
     */
    void loadData(String filePath, String fileName);
}
