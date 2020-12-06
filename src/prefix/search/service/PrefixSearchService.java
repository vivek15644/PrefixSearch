package prefix.search.service;

import java.util.List;

import prefix.interfaces.PrefixTree;

/**
 * This Service can be used to fetch all the entity ids whose name contains the
 * search words
 * 
 * Logic used - Trie data structure and inverted indexing
 * 
 * @author vivek.kumar8
 *
 */
public interface PrefixSearchService {
	List<Long> getEntityIdsByNameKeywords(PrefixTree prefixTree, String searchKeyText);

	List<String> getAllWordsWithPrefix(PrefixTree prefixTree, String key);
}
