package com.ioextendedgr.web.controller;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ioextendedgr.web.service.ConferenceService;
import com.ioextendedgr.web.viewDto.ConferenceSessionView;
import com.ioextendedgr.web.viewDto.ConferenceView;

@RestController
@RequestMapping("/api")
public class ConferenceController {
	private static final Log logger = LogFactory.getLog(ConferenceController.class);
	@Autowired
	private ConferenceService conferenceService;

    @RequestMapping("/conference")
    public Collection<ConferenceView> findAllConferences() {
    	logger.info("Starting here!!!!");
    	return conferenceService.findAllConferences();
    }

    @RequestMapping("/conference/{id}")
    public ConferenceView findConferenceById(@PathVariable("id") Integer id) {
    	return conferenceService.findConferenceById(id);
    }

    @RequestMapping("/conference/{id}/conferenceSessions")
    public Collection<ConferenceSessionView> findConferenceSessionsByConferenceId(@PathVariable("id") Integer id) {
    	return conferenceService.findConferenceSessionsByConferenceId(id);
    }
}
