package com.lin.domain.assembler

import com.lin.domain.model.BaseDO


/**
 * @author linzihao
 */
trait IAssembler[DO <: BaseDO, PO] {

  def toDO(persistentObj: PO): DO

  def toPO(domain: DO): PO

  def toDOs(persistentObjs: List[PO]): List[DO]

  def toPOs(domains: List[DO]): List[PO]
}
