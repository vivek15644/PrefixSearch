package prefix.search.service.manager;

import prefix.entity.MFSampleSearchScheme;
import prefix.search.consumer.interfaces.SearchService;
import prefix.search.consumer.sample.service.SchemeSearchService;

public abstract class SearchServiceManager {

	public static SchemeSearchService schemeSearchService() {
		return SchemeSearchService.getInstance(new MFSampleSearchScheme());
	}
}
