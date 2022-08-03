package com.lin.facade.model.factory

import com.lin.domain.model.BaseDO
import com.lin.facade.model.response.BaseResponse


/**
 * @author linzihao
 */
trait IResponseFactory[DTO <: BaseResponse, DO <: BaseDO] {

  def toDTO(domain: DO): DTO

  def toDTOs(domains: List[DO]): List[DTO]

}
