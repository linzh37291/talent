package com.lin.application.service

import com.lin.domain.model.UserInfoDO
import com.lin.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author linzihao
 */
@Service
class UserServiceImpl extends IUserService {

  @Autowired
  var userRepository: UserRepository = _ //SpringUtil.getBean(classOf[UserRepository])

  override def addUserInfo(userInfoDO: UserInfoDO): Mono[UserInfoDO] = {
    userRepository.save(userInfoDO)
  }

  override def login(userInfoDO: UserInfoDO): Mono[UserInfoDO] = ???

  override def register(userInfoDO: UserInfoDO): Mono[UserInfoDO] = {
    userRepository.save(userInfoDO)
  }
}
