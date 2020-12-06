package prefix.search.consumer.sample.service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import prefix.enums.PrefixTreeType;
import prefix.interfaces.PrefixTree;
import prefix.search.consumer.interfaces.SearchEntity;
import prefix.search.consumer.interfaces.SearchService;
import prefix.search.core.PrefixTreeConstructor;
import prefix.search.service.PrefixSearchImplService;
import prefix.search.service.PrefixSearchService;

public abstract class AbstractSearchService<T> implements SearchService<SearchEntity> {

	private static PrefixTree schemePrefixTree;

	private static PrefixSearchService prefixSearchService = new PrefixSearchImplService();

	public AbstractSearchService() {
	}

	@Override
	public void initialize(List<? extends SearchEntity> entityList) {
		Instant before = Instant.now();
		schemePrefixTree = new PrefixTreeConstructor(PrefixTreeType.LOWERCASE_PREFIX_TREE, entityList);
		Instant after = Instant.now();
		long delta = Duration.between(before, after).toMillis();
		System.out.println(delta);

	}

	@Override
	public List<Long> getAllEntityIds(String searchKeyWord) {
		List<Long> schemeIds = prefixSearchService.getEntityIdsByNameKeywords(schemePrefixTree, searchKeyWord);
		return schemeIds;
	}

}
