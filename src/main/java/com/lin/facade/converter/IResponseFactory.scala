package com.lin.facade.converter

import com.lin.business.domain.BaseDO
import com.lin.facade.model.response.BaseResponse


/**
 * @author linzihao
 */
trait IResponseFactory[DO <: BaseDO, DTO <: BaseResponse] {

  def toDTO(domain: DO): DTO

  def toDTOs(domains: List[DO]): List[DTO]

}
