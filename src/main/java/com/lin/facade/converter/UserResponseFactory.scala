package com.lin.facade.converter

import com.lin.business.domain.UserInfoDO
import com.lin.facade.model.response.UserResponse
import com.lin.infrastructure.utils.BeanCopyUtil
import org.springframework.stereotype.Component

/**
 *
 * Scala的@BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Component
class UserResponseFactory extends IResponseFactory[UserInfoDO, UserResponse] {

  override def toDTOs(domains: List[UserInfoDO]): List[UserResponse] = {
    domains.map(toDTO(_))
  }

  override def toDTO(domain: UserInfoDO): UserResponse = {
    var userResponse = new UserResponse
    BeanCopyUtil.copyProperties(domain, userResponse)
    userResponse
  }
}
