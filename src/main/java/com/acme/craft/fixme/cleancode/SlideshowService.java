package com.acme.craft.fixme.cleancode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlideshowService {

	private ResourceHolderRepository resourceHolderRepository;
	private ResourceHolderResourceRepository resourceHolderResourceRepository;
	private scheduleRepository ScheduleServiceImplSimple;
	private ResourceHolder resourceHolder;
	private Slideshow slideshowTimeline;
	private final int ONE = 1;

	public SlideshowData generateTimelineData(String resourceHolderId) throws Exception {

		resourceHolder = resourceHolderRepository.findOne(resourceHolderId);

		validateResourceHolder(resourceHolder);

		Resource resource = null;
		resource = validateResource(resource);

		Asset defaultAsset = null;
		defaultAsset = assetResources(resource, defaultAsset);

		slideshowTimeline = new Slideshow();

		initDefaultAsset(defaultAsset, slideshowTimeline);

		ResourceSchedule resourceSchedule = ScheduleServiceImplSimple.findOne(resource.getScheduleId());

		validateResourceSchedule(resourceSchedule);

		if (getResourceScheduleSize(resourceSchedule) == 0) {
			throw new Exception("", null);
		}

		Set<String> resourceIds = new HashSet<>();

		addResourceScheduleIds(resourceSchedule, resourceIds);

		Iterable<Resource> resources = resourceHolderResourceRepository.findAll(resourceIds);
		HashMap<String, Asset> assets = resourcesToAssetMap(resources);

		List<SlideshowInterval> timelineInterval = new ArrayList<>();
		int slideNumber = 0;

		Calendar calendar = GregorianCalendar.getInstance();

		for (int i = 0; i < getResourceScheduleSize(resourceSchedule) - ONE; ++i) {
			ResourceSchedule resourceScheduleElement = getResourceSchedule(resourceSchedule).get(i);
			long currentTimeInMillis = calendar.getTimeInMillis();
			long resourceScheduleStartTime = getResourceSchedule(resourceSchedule).get(i + ONE)
					.getStartTime();
			long resourceScheduleEndTime = resourceScheduleElement.getEndTime();


			if (currentTimeInMillis > resourceScheduleStartTime) {
				++slideNumber;
			}

			addToTimeLineInterval(assets, timelineInterval, resourceScheduleElement);
			
			if (defaultAsset != null) {

				if (resourceScheduleEndTime != resourceScheduleStartTime) {
					slideNumber = addSlideNumber(slideNumber, currentTimeInMillis, resourceScheduleEndTime);
					
					timelineInterval.add(defaultDate(resourceScheduleEndTime, resourceScheduleStartTime, defaultAsset));
				}
			}
		}
		
		if (getResourceScheduleSize(resourceSchedule) > 0) {
			ResourceSchedule getLastResourceScheduleElement = getResourceSchedule(resourceSchedule)
					.get(getResourceScheduleSize(resourceSchedule) - ONE);
			
			if (calendar.getTimeInMillis() > getLastResourceScheduleElement.getEndTime()) {
				slideNumber = 0;
			}

			addToTimeLineInterval(assets, timelineInterval, getLastResourceScheduleElement);
		}

		slideshowTimeline.setDate(timelineInterval);
		return new SlideshowData(slideshowTimeline, slideNumber);
	}

	private int addSlideNumber(int slideNumber, long currentTimeInMillis, long resourceScheduleEndTime) {
		if (resourceScheduleEndTime < currentTimeInMillis) {
			++slideNumber;
		}
		return slideNumber;
	}

	private void addToTimeLineInterval(HashMap<String, Asset> assets, List<SlideshowInterval> timelineInterval,
			ResourceSchedule getLastResourceScheduleElement) {
		timelineInterval.add(resourceScheduleToDate(getLastResourceScheduleElement,
				assets.get(getLastResourceScheduleElement.getResourceId())));
	}

	private Resource validateResource(Resource resource) {
		if (resource.getContentId() != null) {
			resource = resourceHolderResourceRepository.findOne(resource.getContentId());
		}
		return resource;
	}

	private ArrayList<ResourceSchedule> getResourceSchedule(ResourceSchedule resourceSchedule) {
		return resourceSchedule.getResourceSchedules();
	}

	private void addResourceScheduleIds(ResourceSchedule resourceSchedule, Set<String> resourceIds) {
		for (ResourceSchedule item : resourceSchedule.getResourceSchedules()) {
			resourceIds.add(item.getResourceId());
		}
	}

	private int getResourceScheduleSize(ResourceSchedule resourceSchedule) {
		return resourceSchedule.getResourceSchedules().size();
	}

	private void validateResourceSchedule(ResourceSchedule resourceSchedule) {
		if (resourceSchedule == null) {
			try {
				throw new Exception("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void initDefaultAsset(Asset defaultAsset, Slideshow timeline) {
		if (defaultAsset != null) {
			timeline.setHeadline("Slideshow");
			timeline.setText("This is your default Slideshow content");
			timeline.setType("default");
			timeline.setAsset(defaultAsset);
		} else {
			timeline.setHeadline("Slideshow");
			timeline.setText("You dont have default content for Slideshow");
			timeline.setType("default");
		}
	}

	private Asset assetResources(Resource resource, Asset defaultAsset) {
		if (resource != null) {
			defaultAsset = resourceToAsset(resource);
		}
		return defaultAsset;
	}

	private void validateResourceHolder(ResourceHolder data) throws Exception {
		if (data == null) {
			throw new Exception("some error");
		}
	}

	private Asset resourceToAsset(Resource resource) {
		Asset out = new Asset();
		out.setMedia(resource.getId());
		out.setCredit(resource.getContentType());
		out.setCaption(resource.getName());
		out.setThumbnail(resource.getId());
		return out;
	}

	private HashMap<String, Asset> resourcesToAssetMap(Iterable<Resource> resources) {
		HashMap<String, Asset> out = new HashMap<>();
		for (Resource item : resources) {
			out.put(item.getId(), resourceToAsset(item));
		}
		return out;
	}

	private SlideshowInterval resourceScheduleToDate(ResourceSchedule schedule, Asset asset) {
		SlideshowInterval out = new SlideshowInterval();
		out.setStartDate(timestampToTimelineDate(schedule.getStartTime()));
		out.setEndDate(timestampToTimelineDate(schedule.getEndTime()));
		out.setHeadline(schedule.getName());
		out.setAsset(asset);
		return out;
	}

	private SlideshowInterval defaultDate(long start, long end, Asset asset) {
		SlideshowInterval out = new SlideshowInterval();
		out.setStartDate(timestampToTimelineDate(start));
		out.setEndDate(timestampToTimelineDate(end));
		out.setHeadline("Default Content");
		out.setAsset(asset);
		return out;
	}

	private String timestampToTimelineDate(long timestamp) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(timestamp);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(calendar.get(Calendar.YEAR)).append(",").append(calendar.get(Calendar.MONTH) + 1)
				.append(",").append(calendar.get(Calendar.DAY_OF_MONTH)).append(",")
				.append(calendar.get(Calendar.HOUR_OF_DAY)).append(",").append(calendar.get(Calendar.MINUTE));
		return stringBuilder.toString();
	}

}
