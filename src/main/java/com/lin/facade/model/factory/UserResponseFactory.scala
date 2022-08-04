package com.lin.facade.model.factory

import com.lin.domain.model.UserInfoDO
import com.lin.facade.model.response.UserResponse
import com.lin.infrastructure.utils.BeanUtils
import org.springframework.stereotype.Component
/**
 *
 * Scala的@BeanProperty可以取代Lombok
 * @author linzihao
 */
@Component
class UserResponseFactory extends IResponseFactory[UserResponse, UserInfoDO] {

  override def toDTO(domain: UserInfoDO): UserResponse = {
    var userResponse = new UserResponse
    BeanUtils.copyProperties(domain, userResponse)
    userResponse
  }


  override def toDTOs(domains: List[UserInfoDO]): List[UserResponse] = {
    domains.map(toDTO(_))
  }
}
