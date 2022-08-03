package com.nova.felixchat.facade.model.factory

import com.nova.felixchat.domain.model.BaseDO
import com.nova.felixchat.facade.model.request.BaseRequest

/**
 *
 * @author linzihao
 */
trait IRequestFactory[DTO <: BaseRequest, DO <: BaseDO] {

  def toDO(request: DTO): DO

  def toDOs(requests: List[DTO]): List[DO]

}
