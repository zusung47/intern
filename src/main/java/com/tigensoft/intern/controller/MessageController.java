package com.tigensoft.intern.controller;

import com.tigensoft.intern.dto.Criteria;
import com.tigensoft.intern.dto.MessageDTO;
import com.tigensoft.intern.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	private static final Logger logger = LogManager.getLogger(MessageController.class);

	//메세지 전체 조회
	@Secured({"ROLE_MEMBER", "ROLE_ADMIN"})
	@RequestMapping(value="/messageList.do")
	public String messageAllList(Model model) throws Exception {

		logger.info("===========MessageController messageAllList() start===========");

		List<MessageDTO> messageDTOlIST = messageService.selectAllMessage();

		if(!messageDTOlIST.isEmpty()) {
			model.addAttribute("messageDTOlIST", messageDTOlIST);
		} else {
			model.addAttribute("listEmpty", "listEmpty");

			return "/message/message-list";
		}


		return "/message/message-list";
	}

	//메세지 번호별 조회
	@Secured({"ROLE_MEMBER"})
	@RequestMapping(value = "/messageDetailOpen/{messageId}.do")
	public String messageDetailOpen(@PathVariable("messageId") int messageId, Model model) throws Exception {

		logger.info("===========MessageController messageDetailOpen() start===========");
		logger.info("messageId: {}", messageId);

		MessageDTO message = messageService.selectMessageById(messageId);
		model.addAttribute("message", message);

		return "/message/message-detail";
	}

	//메세지 등록 폼 연결
	@Secured({"ROLE_MEMBER"})
	@RequestMapping(value="/messageInsertForm.do")
	public String messageInsertForm() throws Exception {
		return "/message/message-insert";
	}

	//메세지 등록
	@Secured({"ROLE_MEMBER"})
	@ResponseBody
	@RequestMapping(value = "/addMessage.do", method = RequestMethod.POST, consumes = "application/json")
	public void addMessage(@RequestBody MessageDTO messageDTO){

		logger.info("===========MessageController addMessage() start===========");

		logger.info("messageDTO : {}", messageDTO);

		messageService.addNewMessage(messageDTO);
	}

	//메세지 개별 삭제
	@Secured({"ROLE_MEMBER"})
	@ResponseBody
	@RequestMapping(value = "/deleteMessage/{messageId}.do", method = RequestMethod.DELETE)
	public void deleteMessage(@PathVariable("messageId") int messageId) throws Exception {

		logger.info("===========MessageController deleteMessage() start===========");
		logger.info("messageId: {}", messageId);

		messageService.deleteMessageById(messageId);
	}

	//메세지 수정
	@Secured({"ROLE_MEMBER"})
	@ResponseBody
	@RequestMapping(value = "/updateMessage.do", method = RequestMethod.PUT, consumes = "application/json")
	public void updateMessage(@RequestBody MessageDTO messageDTO) throws Exception {

		logger.info("===========MessageController updateMessage() start===========");
		logger.info("messageDTO : {}", messageDTO);

		messageService.updateMessage(messageDTO);
	}

	//체크 상태 true
	@Secured({"ROLE_MEMBER"})
	@ResponseBody
	@RequestMapping( value = "/updateCheckTypeTrue.do", method = RequestMethod.PUT, consumes = "application/json")
	public void updateCheckTypeTrue(@RequestBody MessageDTO messageDTO) throws Exception{
		logger.info("===========MessageController updateCheckTypeTrue() start===========");
		logger.info("messageDTO : {}", messageDTO);

		messageService.updateCheckTypeTrue(messageDTO);
	}

	//체크 상태 false
	@Secured({"ROLE_MEMBER"})
	@ResponseBody
	@RequestMapping( value = "/updateCheckTypeFalse.do", method = RequestMethod.PUT, consumes = "application/json")
	public void updateCheckTypeFalse(@RequestBody MessageDTO messageDTO) throws Exception{
		logger.info("===========MessageController updateCheckTypeFalse() start===========");
		logger.info("messageDTO : {}", messageDTO);

		messageService.updateCheckTypeFalse(messageDTO);
	}

	//체크 상태 true 삭제
	@Secured({"ROLE_MEMBER"})
	@ResponseBody
	@RequestMapping(value = "/deleteMessageByCheckTrue.do", method = RequestMethod.DELETE)
	public void deleteMessageByCheckTypeTrue() throws Exception{
		logger.info("===========MessageController deleteMessageByCheckTypeTrue() start===========");

		messageService.deleteMessageByCheckTypeTrue();
	}

	//검색
	@Secured({"ROLE_MEMBER"})
	@RequestMapping(value = "/searchMessage", method = RequestMethod.GET)
	public String searchMessage(Criteria cri, Model model) throws Exception{

		logger.info("===========MessageController searchMessage() start===========");
		logger.info("cri : {}", cri);

		List<MessageDTO> messageDTOlIST = messageService.searchMessage(cri);
		if(!messageDTOlIST.isEmpty()) {
			model.addAttribute("messageDTOlIST", messageDTOlIST);
			model.addAttribute("cri", cri);
		} else {
			model.addAttribute("listEmpty", "listEmpty");

			return "/message/message-list";
		}


		return "/message/message-list";
	}


//	public String noticeAdd(@RequestParam("uploadFile") MultipartFile uploadFile) {
//
//		logger.info("===========noticecontroller noticeAdd() start===========");
//		logger.info("uploadFile: ", uploadFile);
//
//		String filename = uploadFile.getOriginalFilename();
//
//	}
}
