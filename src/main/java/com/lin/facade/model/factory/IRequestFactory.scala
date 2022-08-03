package com.lin.facade.model.factory

import com.lin.domain.model.BaseDO
import com.lin.facade.model.request.BaseRequest

/**
 *
 * @author linzihao
 */
trait IRequestFactory[DTO <: BaseRequest, DO <: BaseDO] {

  def toDO(request: DTO): DO

  def toDOs(requests: List[DTO]): List[DO]

}
