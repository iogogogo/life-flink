package com.iogogogo.persistent;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by tao.zeng on 2019/1/27.
 */
public interface BaseMapper<M extends Serializable> {

    /**
     * 查询所有数据
     *
     * @return
     */
    List<M> findAll();

    /**
     * 根据 id 查询数据
     *
     * @param id
     * @return
     */
    M findById(Serializable id);

    /**
     * 保存数据
     *
     * @param m
     * @return
     */
    int save(M m);

    /**
     * 批量保存
     *
     * @param data
     * @param batchSize
     * @return
     */
    int batchSave(Collection<M> data, int batchSize);

    /**
     * 修改数据
     *
     * @param m
     * @return
     */
    int update(M m);

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    int delete(Serializable id);
}
