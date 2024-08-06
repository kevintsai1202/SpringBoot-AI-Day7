package com.kt.ai.controller;

import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AiController {
	private final ChatModel chatModel;
	
	@PostMapping(value = "/imagequery")
	public String imageQuery(@RequestParam MultipartFile file, @RequestParam String message) {
		UserMessage userMessage = new UserMessage(
				message, 
				new Media(
					MimeTypeUtils.parseMimeType(file.getContentType()),
					file.getResource())
				);
		return chatModel.call(userMessage);
	}
}
