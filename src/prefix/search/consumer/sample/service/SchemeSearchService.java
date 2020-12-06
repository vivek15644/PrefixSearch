package prefix.search.consumer.sample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import prefix.entity.MFSampleSearchScheme;
import prefix.search.consumer.interfaces.SearchEntity;
import prefix.search.service.manager.SearchServiceManager;

/**
 * this is a sample service which gives demo on how to use this library to build
 * prefix tree and to fetch entity ids on the basis of search words
 * 
 * @author vivek.kumar8
 *
 */
public class SchemeSearchService extends AbstractSearchService<MFSampleSearchScheme> {

	private static volatile SchemeSearchService searchInstance;

	private SchemeSearchService(SearchEntity instance) {
	}

	public static SchemeSearchService getInstance(SearchEntity instance) {
		if (searchInstance == null) {
			synchronized (SchemeSearchService.class) {
				if (searchInstance == null) {
					searchInstance = new SchemeSearchService(instance);
				}
			}
		}
		return searchInstance;
	}

	@Override
	public List<MFSampleSearchScheme> getAllEntitiesWithSearchKey(String searchKey) {
		List<Long> schemeIds = this.getAllEntityIds(searchKey);

		/**
		 * build scheme list of required entity
		 */
		List<MFSampleSearchScheme> resultList = new ArrayList<MFSampleSearchScheme>();
		for (long schemeId : schemeIds) {
			resultList.add(MFSampleSearchScheme.schemeIdVsSchemeMap.get(schemeId));
		}
		return resultList;
	}

	public static void main(String[] args) {

		Pattern p = Pattern.compile("[a-z]*");

		System.out.println(p.matcher("viv-").matches());

		System.out.println("hdsa");

		MFSampleSearchScheme.initialize();

		List<MFSampleSearchScheme> resultSchemes = SearchServiceManager.schemeSearchService()
				.getAllEntitiesWithSearchKey("tata mutual");
		System.out.println(resultSchemes);

	}

}
