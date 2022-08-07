package com.lin.facade.converter

import com.lin.business.domain.UserInfoDO
import com.lin.facade.model.request.UserAddRequest
import com.lin.infrastructure.utils.BeanCopyUtil
import org.springframework.stereotype.Component

/**
 *
 * Scala的@BeanProperty可以取代Lombok
 *
 * @author linzihao
 */
@Component
class UserAddRequestFactory extends IRequestFactory[UserInfoDO, UserAddRequest] {

  override def toDOs(requests: List[UserAddRequest]): List[UserInfoDO] = {
    requests.map(toDO(_))
  }

  override def toDO(request: UserAddRequest): UserInfoDO = {
    var userInfoDo = new UserInfoDO
    BeanCopyUtil.copyProperties(request, userInfoDo)
    userInfoDo
  }
}