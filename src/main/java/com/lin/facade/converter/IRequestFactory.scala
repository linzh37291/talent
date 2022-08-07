package com.lin.facade.converter

import com.lin.business.domain.BaseDO
import com.lin.facade.model.request.BaseRequest

/**
 *
 * @author linzihao
 */
trait IRequestFactory[DO <: BaseDO, DTO <: BaseRequest] {

  def toDO(request: DTO): DO

  def toDOs(requests: List[DTO]): List[DO]

}
