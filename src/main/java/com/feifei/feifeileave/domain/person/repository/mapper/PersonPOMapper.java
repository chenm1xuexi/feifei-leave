package com.feifei.feifeileave.domain.person.repository.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feifei.feifeileave.domain.person.entity.valueobject.PersonStatus;
import com.feifei.feifeileave.domain.person.repository.po.PersonPO;
import org.apache.ibatis.annotations.Mapper;

import static com.feifei.feifeileave.domain.person.repository.po.PersonPO.COL_PERSON_ID;
import static com.feifei.feifeileave.domain.person.repository.po.PersonPO.COL_PERSON_STATUS;

@Mapper
public interface PersonPOMapper extends BaseMapper<PersonPO> {

    default PersonPO getById(Long personId) {
        QueryWrapper<PersonPO> wrapper = new QueryWrapper<>();
        wrapper.eq(COL_PERSON_ID, personId)
                .eq(COL_PERSON_STATUS, PersonStatus.ENABLE.toString());
        return selectOne(wrapper);
    }
}