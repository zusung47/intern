package com.tigensoft.intern.service;


import com.tigensoft.intern.dao.MemberDAO;
import com.tigensoft.intern.dao.MessageDAO;
import com.tigensoft.intern.dto.Criteria;
import com.tigensoft.intern.dto.MessageDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDAO messageDAO;

    private static final Logger logger = LogManager.getLogger(MessageService.class);

    public List<MessageDTO> selectAllMessage() {

        logger.info("===========MessageService selectAllMessage start===========");

        List<MessageDTO> message = messageDAO.selectAllMessageList();
        logger.info("message : {}" , message);
        return messageDAO.selectAllMessageList();
    }

    public MessageDTO selectMessageById(int messageId) {

        logger.info("===========MessageService selectMessageById start===========");

        return messageDAO.selectMessageById(messageId);
    }

    @Transactional
    public void addNewMessage(MessageDTO messageDTO) {

        logger.info("===========MessageService addNewMessage start===========");

        messageDTO.setCreationDate(new Date());
        logger.info(messageDTO.getCreationDate());
        //로그인구현후 로그인유저로변경
        //messageDTO.setCreationUser("testUser");

        messageDAO.addNewMessage(messageDTO);
    }

    @Transactional
    public void deleteMessageById(int messageId) {

        logger.info("===========MessageService deleteMessageById start===========");

        messageDAO.deleteMessageById(messageId);
    }

    @Transactional
    public void updateMessage(MessageDTO messageDTO) {

        logger.info("===========MessageService updateMessage start===========");
        logger.info("dto : {}", messageDTO);
        messageDTO.setCreationDate(new Date());
        messageDAO.updateMessage(messageDTO);
    }

    @Transactional
    public void updateCheckTypeTrue(MessageDTO messageDTO) {

        logger.info("===========MessageService updateCheckTypeTrue start===========");
        messageDTO.setCheckType("true");
        messageDAO.updateCheckTypeTrue(messageDTO);
    }

    @Transactional
    public void updateCheckTypeFalse(MessageDTO messageDTO) {

        logger.info("===========MessageService updateCheckTypeFalse start===========");
        messageDTO.setCheckType("false");
        messageDAO.updateCheckTypeFalse(messageDTO);
    }

    @Transactional
    public void deleteMessageByCheckTypeTrue() {

        logger.info("===========MessageService deleteMessageByCheckTypeTrue start===========");
        messageDAO.deleteMessageByCheckTypeTrue();
    }

    public List<MessageDTO> searchMessage(Criteria cri) {

        logger.info("===========MessageService searchMessage start===========");

        return messageDAO.searchMessage(cri);
    }
}
