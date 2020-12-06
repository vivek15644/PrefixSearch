package prefix.search.service;

import java.util.ArrayList;
import java.util.List;

import prefix.interfaces.PrefixTree;
import prefix.interfaces.PrefixTreeNode;

/**
 * Implementation of Prefix search service
 * 
 * @author vivek.kumar8
 *
 */
public class PrefixSearchImplService implements PrefixSearchService {

	@Override
	public List<Long> getEntityIdsByNameKeywords(PrefixTree prefixTree, String searchKeyText) {

		List<String> allIndexStrings = new ArrayList<String>();
		List<Long> resultList = new ArrayList<>();

		// break text string into words
		String[] searchWords = searchKeyText.split(" ");

		// this loop will collect all the full words with the prefix in the search
		// string
		for (String key : searchWords) {
			List<String> allWordsWithKey = getAllWordsWithPrefix(prefixTree, key.trim().toLowerCase());
			if (allWordsWithKey == null || allWordsWithKey.size() == 0) {
				return resultList;
			}
			allIndexStrings.addAll(getAllWordsWithPrefix(prefixTree, key.trim().toLowerCase()));
		}

		// below code will fetch all the common entity ids belonging to entities whose
		// name contains all the search words
		List<Long> entityIds = null;
		for (String invertedIndex : allIndexStrings) {
			entityIds = prefixTree.invertedIndexVsEntityIds().get(invertedIndex);
			if (resultList == null || resultList.size() == 0) {
				resultList.addAll(entityIds);
			} else {
				// taking intersection of all the ids
				resultList.retainAll(entityIds);
			}
			if (resultList == null || resultList.size() == 0)
				break;
		}
		return resultList;
	}

	/**
	 * this function will collect all the words starting with prefix as key
	 */
	@Override
	public List<String> getAllWordsWithPrefix(PrefixTree prefixTree, String key) {
		List<String> resultStrings = new ArrayList<String>();
		try {
			prefixTree.checkKeyValidity(key);
			PrefixTreeNode iterator = prefixTree.root();
			int currentCharIndex = -1;
			boolean isFound = true;
			for (int level = 0; level < key.length() && isFound; level++) {
				currentCharIndex = prefixTree.treeType().findIndexOfChar(key.charAt(level));
				if (iterator.getChildren(currentCharIndex) == null) {
					isFound = false;
				} else {
					iterator = iterator.getChildren(currentCharIndex);
					isFound = true;
				}
			}
			// key is found , collect all the strings with prefix as key
			if (isFound) {
				resultStrings = buildResult(iterator, prefixTree, key);
			}
		} catch (Exception e) {
//			logger.error("Invalid key ignored: {}", key);
		}
		return resultStrings;

	}

	private static List<String> buildResult(PrefixTreeNode suffixStart, PrefixTree prefixTree, String key) {

		List<String> resultList = new ArrayList<String>();

		if (suffixStart != null) {
			if (suffixStart.isWord()) {
				resultList.add(key);
			}
			// build the combination with every children
			String prefix = key;
			for (int childIndex = 0; childIndex < prefixTree.maxCharacterSize(); childIndex++) {
				if (suffixStart.getChildren(childIndex) != null) {
					resultList.addAll(buildResult(suffixStart.getChildren(childIndex), prefixTree,
							prefix + prefixTree.treeType().getChar(childIndex)));
				}
			}
		}
		return resultList;
	}

}
