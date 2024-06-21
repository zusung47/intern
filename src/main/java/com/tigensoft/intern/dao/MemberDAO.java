package com.tigensoft.intern.dao;

import com.tigensoft.intern.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private static final Logger logger = LogManager.getLogger(MemberDAO.class);

    public MemberDTO getLoginMember(String userid) {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            logger.info("===========MemberDAO getLoginMember start===========");

            return sqlSession.selectOne("com.tigensoft.intern.dao.MemberDAO.getLoginMember", userid);

        } catch (Exception e) {

            logger.error("MemberDAO getLoginMember error", e);
            throw new RuntimeException("Failed to getLoginMember", e);

        }
    }
}
