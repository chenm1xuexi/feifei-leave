package com.feifei.feifeileave.interfaces.facade;

import com.feifei.feifeileave.application.PersonApplicationService;
import com.feifei.feifeileave.infrastructure.common.api.RichResult;
import com.feifei.feifeileave.interfaces.assembler.PersonAssembler;
import com.feifei.feifeileave.interfaces.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.feifei.feifeileave.infrastructure.common.api.ApiConstants.PERSONAL;

/**
 * 人员信息对外restful接口类
 *
 * @Author: shixiongfei
 * @Date: 2020/4/30 17:42
 */
@RestController
@RequestMapping(PERSONAL)
@Slf4j
@AllArgsConstructor
public class PersonApi {

    PersonApplicationService personApplicationService;

    /**
     * 创建一个人员信息,绑定上下级关系
     *
     * @author shixiongfei
     * @date 2020/4/30 6:06 下午
     * @param personDTO	人员dto
     * @return
     */
    @PostMapping
    public RichResult<?> create(@RequestBody @Validated PersonDTO personDTO) {
        personApplicationService.create(PersonAssembler.toDO(personDTO));
        return RichResult.ok();
    }

}