package com.ioextendedgr.web.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ioextendedgr.web.builder.ConferenceViewBuilder;
import com.ioextendedgr.web.repository.ConferenceRepository;
import com.ioextendedgr.web.util.StubFactory;
import com.ioextendedgr.web.viewDto.ConferenceSessionView;
import com.ioextendedgr.web.viewDto.ConferenceView;
import com.ioextendedgr.web.viewDto.PresenterView;

@Component
public class ConferenceService {
	
	@Autowired
	private StubFactory stubFactory;
	
	@Autowired
	private ConferenceRepository conferenceRepository;

	public Collection<ConferenceView> findAllConferences() {
		return ConferenceViewBuilder.build(conferenceRepository.findAll());
	}

	public ConferenceView findConferenceById(Integer id) {
		return ConferenceViewBuilder.build(conferenceRepository.findOne(id));
	}

	public Collection<PresenterView> findAllPresenters() {
		return stubFactory.findAllPresenters();
	}

	public PresenterView findPresenterById(Long id) {
		return stubFactory.findPresenterById(id);
	}

	public Collection<ConferenceSessionView> findAllConferenceSessions() {
		return stubFactory.findAllConferenceSessions();
	}

	public ConferenceSessionView findConferenceSessionById(Long id) {
		return stubFactory.findConferenceSessionById(id);
	}

	public Collection<ConferenceSessionView> findConferenceSessionsByConferenceId(Long id) {
		return stubFactory.findConferenceSessionsByConferenceId(id);
	}

}
