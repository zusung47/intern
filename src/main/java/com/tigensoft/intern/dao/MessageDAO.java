package com.tigensoft.intern.dao;

import com.tigensoft.intern.dto.MessageDTO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAO {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private static final Logger logger = LogManager.getLogger(MessageDAO.class);

    public List<MessageDTO> selectAllMessageList(){

        logger.info("===========MessageDAO selectAllMessageList start===========");

        try (SqlSession sqlSession = sqlSessionFactory.openSession()){

            return sqlSession.selectList("com.tigensoft.intern.dao.MessageDAO.selectAllMessageList");

        } catch (Exception e) {

            logger.error("MessageDAO selectAllMessageList error", e);
            throw new RuntimeException("Failed to selectAllMessageList", e);

        }
    }

    public MessageDTO selectMessageById(int messageId) {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()){

            logger.info("===========MessageDAO selectMessageById start===========");

            return sqlSession.selectOne("com.tigensoft.intern.dao.MessageDAO.selectMessageById", messageId);

        } catch (Exception e) {

            logger.error("MessageDAO selectMessageById error", e);
            throw new RuntimeException("Failed to selectMessageById", e);

        }
    }

    public void addNewMessage(MessageDTO messageDTO) {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()){

            logger.info("===========MessageDAO addNewMessage start===========");

            sqlSession.insert("com.tigensoft.intern.dao.MessageDAO.addNewMessage", messageDTO);

        } catch (Exception e) {

            logger.info("===========MessageDAO addNewMessage failed===========");

        }
    }

    public void deleteMessageById(int messageId) {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()){

            logger.info("===========MessageDAO deleteMessageById start===========");
            logger.info("messageId : {}", messageId);

            sqlSession.delete("com.tigensoft.intern.dao.MessageDAO.deleteMessageById", messageId);
        } catch (Exception e) {

            logger.info("===========MessageDAO deleteMessageById failed===========");

        }

    }

    public void updateMessage(MessageDTO messageDTO) {

        try (SqlSession sqlSession = sqlSessionFactory.openSession()){

            logger.info("===========MessageDAO updateMessage start===========");

            sqlSession.update("com.tigensoft.intern.dao.MessageDAO.updateMessage", messageDTO);
        } catch (Exception e) {
            logger.info("===========MessageDAO updateMessage failed===========");
        }
    }
}
