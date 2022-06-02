package per.iys.crm.settings.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import per.iys.crm.settings.domain.User;
import per.iys.crm.settings.mapper.UserMapper;
import per.iys.crm.settings.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User queryUserByLoginActAndLoginPwd(Map<String, Object> map) {
        String loginPwd = (String) map.get("loginPwd");
        loginPwd = DigestUtils.md5DigestAsHex(loginPwd.getBytes(StandardCharsets.UTF_8));
        map.put("loginPwd", loginPwd);
        return userMapper.selectUserByLoginActAndLoginPwd(map);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectAllUsers();
    }
}
