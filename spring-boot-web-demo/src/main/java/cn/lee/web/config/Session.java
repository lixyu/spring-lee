package cn.lee.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Lee
 * @date : 2020-10-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private Long custId;

    private Long accountId;
}
