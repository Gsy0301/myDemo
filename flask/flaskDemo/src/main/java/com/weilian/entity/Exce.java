package com.weilian.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * <p> description </p>
 *
 * @author Guo S.Y.
 * @version V1.0
 * @since 2022/5/25-10:21
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exce {
    private Double cx;
    private Double cy;
    private Double width;
    private Double height;
    private String type;
}
