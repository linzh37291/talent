package com.lin.application.service

import com.lin.domain.model.UserInfoDO
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author linzihao
 */
@Service
class UserServiceImpl extends IUserService {

  override def addUserInfo(userInfoDomain: UserInfoDO): Mono[UserInfoDO] = {
    userInfoDomain.save
  }

  override def login(userInfo: UserInfoDO): Mono[UserInfoDO] = ???
}
