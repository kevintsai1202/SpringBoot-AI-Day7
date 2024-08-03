package com.kt.ai.controller;

import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
public class AiController {
	private final ChatModel chatModel;
	
	@GetMapping(value = "/chat")
	public String chat(@RequestParam String prompt) {	
		List<Message> messages = List.of(
				new SystemMessage("你是一個說故事大師，如果找不到資料或是最近的新聞就編造一個聽起來讓人開心的消息"),
				new UserMessage(prompt)
				);
		ChatResponse response = chatModel
				.call(new Prompt(
			    	messages,
			    	OpenAiChatOptions.builder()
						             .withTemperature(1f)
						             .build()
			    ));
		response.getResult().getOutput()
		return response.getResult().getOutput().getContent();
	}
}
