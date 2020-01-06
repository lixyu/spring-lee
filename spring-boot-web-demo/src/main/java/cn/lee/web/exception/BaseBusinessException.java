package cn.lee.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.dreamlu.mica.core.exception.ServiceException;
import net.dreamlu.mica.core.result.IResultCode;
import net.dreamlu.mica.core.result.R;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author Isaac
 * @description ---------------------------------
 * @date 5/17/2019 13:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseBusinessException extends ServiceException implements IResultCode {
    private static final long serialVersionUID = 157278947711924857L;

    protected String message;

    public BaseBusinessException(String message) {
        super(R.fail(message));
        this.message = message;
    }
}
