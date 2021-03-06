package com.ioextendedgr.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.ioextendedgr.web.viewDto.CompanyView;
import com.ioextendedgr.web.viewDto.ConferenceSessionView;
import com.ioextendedgr.web.viewDto.ConferenceView;
import com.ioextendedgr.web.viewDto.LocationView;
import com.ioextendedgr.web.viewDto.PresenterView;

import static java.lang.String.format;

@Component
public class StubFactory {
	
	Map<Integer, ConferenceView> conferenceViewsByIdMap = new HashMap<Integer, ConferenceView>();
	Map<Integer, PresenterView> presenterViewsByIdMap = new HashMap<Integer, PresenterView>();
	Map<Integer, ConferenceSessionView> conferenceSessionByIdMap = new HashMap<Integer, ConferenceSessionView>();
	
	public StubFactory() {
		init();
	}

	public Collection<ConferenceView> findAllConferences() {
		return conferenceViewsByIdMap.values();
	}
	public ConferenceView findConferenceById(Long id) {
		return conferenceViewsByIdMap.get(id);
	}
	
	public Collection<PresenterView> findAllPresenters() {
		return presenterViewsByIdMap.values();
	}

	public PresenterView findPresenterById(Long id) {
		return presenterViewsByIdMap.get(id);
	}
	
	public Collection<ConferenceSessionView> findAllConferenceSessions() {
		return conferenceSessionByIdMap.values();
	}

	public ConferenceSessionView findConferenceSessionById(Long id) {
		return conferenceSessionByIdMap.get(id);
	}
	
	public Collection<ConferenceSessionView> findConferenceSessionsByConferenceId(Long id) {
		Collection<ConferenceSessionView> conferenceSessionViews = conferenceSessionByIdMap.values();
		Collection<ConferenceSessionView> matches = new ArrayList<ConferenceSessionView>();
		for (ConferenceSessionView currConferenceSessionView : conferenceSessionViews) {
			if (id.equals(currConferenceSessionView.getConferenceId())) {
				matches.add(currConferenceSessionView);
			}
		}
		
		return matches;
	}

	/* ====================================================================== */

	private void init() {
		initializeConferenceViews();
		initializePresenterViews();
		initializeConferenceSessions();
	}
	
	private void initializeConferenceSessions() {
		List<ConferenceSessionView> conferenceSessionViews = createConferenceSessionViews();
		for (ConferenceSessionView currConferenceSessionView : conferenceSessionViews) {
			conferenceSessionByIdMap.put(currConferenceSessionView.getId(), currConferenceSessionView);
		}
	}

	private void initializePresenterViews() {
		List<PresenterView> presenterViews = createPresenterViews();
		for (PresenterView currPresenterView : presenterViews) {
			presenterViewsByIdMap.put(currPresenterView.getId().intValue(), currPresenterView);
		}
		
	}

	private void initializeConferenceViews() {
		List<ConferenceView> createConferenceViews = createConferenceViews();
		for (ConferenceView currConferenceView : createConferenceViews) {
			conferenceViewsByIdMap.put(currConferenceView.getId().intValue(), currConferenceView);
		}
	}

	private List<ConferenceSessionView> createConferenceSessionViews() {
		int conferenceSessionListSize = 10;
		List<ConferenceSessionView> conferenceSessionList = new ArrayList<ConferenceSessionView>();
		
		for (int i = 0; i < conferenceSessionListSize; i++) {
			conferenceSessionList.add(toConferenceSessionView(i));
		}
		
		return conferenceSessionList;
	}


	private List<PresenterView> createPresenterViews() {
		int presenterListSize = 10;
		List<PresenterView> presenterList = new ArrayList<PresenterView>();
		
		for (int i = 0; i < presenterListSize; i++) {
			presenterList.add(toPresenterView(i));
		}
		
		return presenterList;
	}

	private ConferenceSessionView toConferenceSessionView(int index) {
		ConferenceSessionView dto = new ConferenceSessionView();

		List<Integer> conferenceIds = new ArrayList<Integer>(conferenceViewsByIdMap.keySet());
		List<Integer> presenterIds = new ArrayList<Integer>(presenterViewsByIdMap.keySet());

		Random random = new Random();

		dto.setId(50 + index);
		dto.setConferenceId(conferenceIds.get(random.nextInt(conferenceIds.size())));
		dto.setCreateDttm(new Date());
		dto.setDurationMinutes((index % 2 == 0) ? 30 : 60);
		dto.setFullDesc(format("Full description of the session talk for session: %d", dto.getId()));
		dto.setLastUpdateDttm(new Date());
		dto.setName(format("Name of Session for %d", dto.getId()));

		List<Integer> selectedPresenterIds = null;
		if (index % 2 == 0) {
			selectedPresenterIds = toSinglePresenterIdList(random, presenterIds);
		} else {
			selectedPresenterIds = toMultiplePresenterIdsList(random, presenterIds);
		}
		dto.setPresenterIds(selectedPresenterIds);
		dto.setRoomId(-1);
		dto.setShortDesc(format("Short Description Session: %d", dto.getId()));
		dto.setStartDttm(new Date());

		return dto;
	}

	private List<Integer> toMultiplePresenterIdsList(Random random, List<Integer> presenterIds) {
		Set<Integer> presenterIdSet = new HashSet<Integer>();
		presenterIdSet.add(presenterIds.get(random.nextInt(presenterIds.size())));
		presenterIdSet.add(presenterIds.get(random.nextInt(presenterIds.size())));
		presenterIdSet.add(presenterIds.get(random.nextInt(presenterIds.size())));
		presenterIdSet.add(presenterIds.get(random.nextInt(presenterIds.size())));
		return new ArrayList<Integer>(presenterIdSet);
	}

	private List<Integer> toSinglePresenterIdList(Random random, List<Integer> presenterIds) {
		List<Integer> presenterIdList = new ArrayList<Integer>();
		presenterIdList.add(presenterIds.get(random.nextInt(presenterIds.size())));
		return presenterIdList;
	}

	private PresenterView toPresenterView(int index) {
		PresenterView presenterView = new PresenterView();
		presenterView.setCompanyView(toCompanyView(index));
		presenterView.setId(index + 100);
		presenterView.setJobTitle(format("Job Title: %d", presenterView.getId()));
		presenterView.setShortBio(format("This is the short bio for Presenter: %d", presenterView.getId()));
		presenterView.setUserId(presenterView.getId());
		
		return presenterView;
	}

	private CompanyView toCompanyView(int index) {
		CompanyView companyView = new CompanyView();
		companyView.setId(index + 50);
		companyView.setFullDesc(format("Company full description of blah,blah,blah: %d", companyView.getId()));
		companyView.setLogoPath("http://some.url.logo.");
		companyView.setName(format("Company #%d", companyView.getId()));
		companyView.setShortDesc(format("Short description: %d", companyView.getId()));
		return companyView;
	}

	public List<ConferenceView> createConferenceViews() {
		int conferenceListSize = 10;
		List<ConferenceView> conferenceList = new ArrayList<ConferenceView>();
		
		for (int i = 0; i < conferenceListSize; i++) {
			conferenceList.add(toConferenceView(i));
		}
		
		return conferenceList;
		
	}

	private ConferenceView toConferenceView(int index) {
		
		ConferenceView conferenceView = new ConferenceView();
		conferenceView.setCreateDttm(new Date());
		conferenceView.setEndDate(new Date());
		conferenceView.setFullDesc(String.format("Conference: %d The complete full description in all of it's glory", index));
		conferenceView.setId(Long.valueOf(index));
		conferenceView.setLastUpdateDttm(new Date());
		conferenceView.setLocationView(toLocationView(index));
		conferenceView.setName(String.format("Conference %d", index));
		conferenceView.setShortDesc(String.format("Conf %d short desc", index));
		conferenceView.setStartDate(new Date());
		
		return conferenceView;
	}

	private LocationView toLocationView(int index) {
		LocationView dto = new LocationView();
		
		dto.setCreateDttm(new Date());
		dto.setFullDesc(String.format("Full Description Location: %d", index));
		dto.setId(index);
		dto.setLastUpdateDttm(new Date());
		dto.setName(String.format("Location %d", index));
		dto.setParkingInfo(String.format("The parking information for Location: %d is the following....", index));
		dto.setShortDesc(String.format("Location: %d", index));
		
		return dto;
	}

}
