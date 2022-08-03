package com.nova.felixchat.facade.model.factory

import com.nova.felixchat.domain.model.BaseDO
import com.nova.felixchat.facade.model.response.BaseResponse

/**
 *
 * @author linzihao
 */
trait IResponseFactory[DTO <: BaseResponse, DO <: BaseDO] {

  def toDTO(domain: DO): DTO

  def toDTOs(domains: List[DO]): List[DTO]

}
