package per.iys.crm.settings.service;

import per.iys.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User queryUserByLoginActAndLoginPwd(Map<String, Object> map);

    List<User> queryAllUsers();
}
