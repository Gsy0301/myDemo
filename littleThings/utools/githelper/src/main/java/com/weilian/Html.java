package com.weilian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * <p>The following is a class description.</p>
 *
 * @author Guo S.Y.
 * @version V1.0
 * @since 2022/7/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Html {
    private String name;
    private String zhName;
    private List<String> code;
}
