package per.iys.crm.settings.service;

import per.iys.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 登陆
     * @param map
     * @return
     */
    User queryUserByLoginActAndLoginPwd(Map<String, Object> map);

    /**
     * 查询所有用户
     * @return
     */
    List<User> queryAllUsers();
}
