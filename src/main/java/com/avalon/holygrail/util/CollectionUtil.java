package com.avalon.holygrail.util;

import java.util.List;

/**
 * 集合工具
 * Created by 白超 on 2018/2/27.
 */
public class CollectionUtil {

    public interface ListHandlerA<T> {

        void accept(List<T> records) throws Exception;
    }

    public interface ListHandlerB<T> {

        boolean apply(List<T> records) throws Exception;
    }

    /**
     * 批处理
     *
     * @param records 数据集合
     * @param start   开始下标
     * @param length  处理长度,可以为负值,负值表示从后向前
     * @param handler 回调函数
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> void batchProcess(List<T> records, int start, int length, ListHandlerB<T> handler) throws Exception {
        if (records == null || records.size() == 0) {
            return;
        }
        if (start < 0) {
            return;
        }
        if (length == 0) {
            return;
        }
        List<T> list = records.getClass().newInstance();
        int i;
        int end = start + length;
        if (length > 0) {
            end--;
            for (i = start; i <= end; i++) {
                if (i >= records.size()) {
                    break;
                }
                list.add(records.get(i));
            }
        } else {
            ++end;
            for (i = start; i >= end; i--) {
                if (i < 0) {
                    break;
                }
                list.add(records.get(i));
            }
        }
        if (handler.apply(list) && i < records.size()) {
            CollectionUtil.batchProcess(records, i, length, handler);
        }
    }

    /**
     * 批处理
     * 默认从第一条数据开始
     *
     * @param records 数据集合
     * @param size    长度,默认取绝对值
     * @param handler 回调函数
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> void batchProcess(List<T> records, int size, ListHandlerB<T> handler) throws Exception {
        CollectionUtil.batchProcess(records, 0, Math.abs(size), handler);
    }

    /**
     * 批处理
     *
     * @param records 数据集合
     * @param start   开始下标
     * @param length  处理长度,可以为负值,负值表示从后向前
     * @param handler 回调函数
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> void batchProcess(List<T> records, int start, int length, ListHandlerA<T> handler) throws Exception {
        CollectionUtil.batchProcess(records, start, length, row -> {
            handler.accept(row);
            return true;
        });
    }

    /**
     * 批处理
     *
     * @param records 数据集合
     * @param size    长度,默认取绝对值
     * @param handler 回调函数
     * @param <T>
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> void batchProcess(List<T> records, int size, ListHandlerA<T> handler) throws Exception {
        CollectionUtil.batchProcess(records, 0, Math.abs(size), handler);
    }

}