package com.blitline.image.spring.web;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blitline.image.BlitlinePostback;
import com.blitline.image.spring.postback.BlitlinePostbackHandler;

@RestController
@RequestMapping("/blitline")
public class BlitlinePostbackController {
	private final Log log = LogFactory.getLog(BlitlinePostbackController.class);

	private BlitlinePostbackHandler handler;

	@Autowired
	public void setHandler(BlitlinePostbackHandler handler) {
		this.handler = handler;
	}

	public BlitlinePostbackHandler getHandler() {
		return handler;
	}

	@RequestMapping("/image")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public HttpHeaders handlePostback(@RequestBody BlitlinePostback postback) throws Exception {
		log.info("handling Blitline postback for job " + postback.getJobId());
		handler.handlePostback(postback);
		return new HttpHeaders();
	}

	@PostConstruct
	public void log() {
		log.info("using built-in Blitline image postback endpoint");
	}
}
