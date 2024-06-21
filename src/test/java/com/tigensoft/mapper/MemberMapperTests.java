package com.tigensoft.mapper;

import com.tigensoft.intern.dao.MemberDAO;
import com.tigensoft.intern.dto.MemberDTO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class MemberMapperTests {

    @Setter(onMethod_ = @Autowired)
    private MemberDAO mapper;

    //멤버 정보 테스트
    @Test
    public void testGetLoginUser() {

        MemberDTO member = mapper.getLoginMember("testuser0");

        log.info(member);

    }
}
