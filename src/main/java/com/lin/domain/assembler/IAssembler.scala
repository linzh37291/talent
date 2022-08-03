package com.nova.felixchat.domain.assembler

import com.nova.felixchat.domain.model.BaseDO

trait IAssembler[DO <: BaseDO, PO] {

  def toDO(persistentObj: PO): DO

  def toPO(domain: DO): PO

  def toDOs(persistentObjs: List[PO]): List[DO]

  def toPOs(domains: List[DO]): List[PO]
}
