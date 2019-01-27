package com.iogogogo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

/**
 * Created by tao.zeng on 2019/1/27.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Metric {

    public String name;
    public long timestamp;
    public Map<String, Object> fields;
    public Map<String, String> tags;
}
