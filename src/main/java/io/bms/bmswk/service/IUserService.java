package io.bms.bmswk.service;

import io.bms.bmswk.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.bms.bmswk.model.support.R;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 996worker
 * @since 2023-02-23
 */
public interface IUserService extends IService<User> {
    public R registerUser();
}
