package com.iogogogo.persistent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tao.zeng on 2019/1/27.
 */
public interface BaseMapper<PK extends Serializable, M extends Serializable> {

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
    M findById(PK id);

    /**
     * 保存数据
     *
     * @param m
     * @return
     */
    int save(M m);

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
    int delete(PK id);
}
