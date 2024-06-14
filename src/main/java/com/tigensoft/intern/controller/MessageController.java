package com.tigensoft.intern.controller;

import com.tigensoft.intern.dto.MessageDTO;
import com.tigensoft.intern.service.MessageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value="/messageList.do")
	public String messageAllList(Model model) throws Exception {

		logger.info("===========MessageController messageAllList() start===========");

		List<MessageDTO> messageDTOlIST = messageService.selectAllMessage();
		model.addAttribute("messageDTOlIST", messageDTOlIST);

		return "/message/message-list";
	}

	//메세지 번호별 조회
	@RequestMapping(value = "/messageDetailOpen/{messageId}.do")
	public String messageDetailOpen(@PathVariable("messageId") int messageId, Model model) throws Exception {

		logger.info("===========MessageController messageDetailOpen() start===========");
		logger.info("messageId: {}", messageId);

		MessageDTO message = messageService.selectMessageById(messageId);
		model.addAttribute("message", message);

		return "/message/message-detail";
	}

	//메세지 등록 폼 연결
	@RequestMapping(value="/messageInsertForm.do")
	public String messageInsertForm() throws Exception {
		return "/message/message-insert";
	}

	//메세지 등록
	@RequestMapping(value = "/addMessage.do", method = RequestMethod.POST)
	public String addMessage(@ModelAttribute MessageDTO messageDTO){

		logger.info("===========MessageController addMessage() start===========");

		logger.info("messageDTO : {}", messageDTO);

		messageService.addNewMessage(messageDTO);

		return "redirect:messageList.do";
	}

	//메세지 개별 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteMessage/{messageId}.do", method = RequestMethod.DELETE)
	public void deleteMessage(@PathVariable("messageId") int messageId) throws Exception {

		logger.info("===========MessageController deleteMessage() start===========");
		logger.info("messageId: {}", messageId);

		messageService.deleteMessageById(messageId);
	}

	//메세지 수정
	@ResponseBody
	@RequestMapping(value = "/updateMessage.do", method = RequestMethod.PUT, consumes = "application/json")
	public void updateMessage(@RequestBody MessageDTO messageDTO) throws Exception {
		logger.info("===========MessageController updateMessage() start===========");
		logger.info("messageDTO : {}", messageDTO);

		messageService.updateMessage(messageDTO);
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
