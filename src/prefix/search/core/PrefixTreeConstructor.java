package prefix.search.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prefix.enums.PrefixTreeType;
import prefix.exception.InvalidKeyException;
import prefix.factory.PrefixTreeFactory;
import prefix.interfaces.PrefixTree;
import prefix.interfaces.PrefixTreeNode;
import prefix.search.consumer.interfaces.SearchEntity;

public class PrefixTreeConstructor implements PrefixTree {

	private PrefixTreeNode prefixTreeRoot;
	private PrefixTreeType treeType;
	private Map<String, List<Long>> invertedIndexVsEntityIds;

	public Map<String, List<Long>> invertedIndexVsEntityIds() {
		return invertedIndexVsEntityIds;
	}

	public PrefixTreeConstructor(PrefixTreeType type, List<? extends SearchEntity> entityList) {
		this.invertedIndexVsEntityIds = new HashMap<String, List<Long>>();
		this.treeType = type;
		this.prefixTreeRoot = PrefixTreeFactory.createTreeRoot(type);
		buildPrefixTree(entityList);
	}

	/**
	 * This function will check the validity of the key If key doesn't matches with
	 * the pattern will throw exception
	 * 
	 * @param key
	 * @throws InvalidKeyException
	 */
	@Override
	public void checkKeyValidity(String key) throws InvalidKeyException {
		if (!treeType().getKeyRegex().matcher(key).matches()) {
			throw new InvalidKeyException("Invalid key: " + key);
		}
	}

	/**
	 * this function will insert new key in the existing trie data structure
	 * https://www.geeksforgeeks.org/trie-insert-and-search/
	 * 
	 * @param key
	 */
	private void insertKey(String key) {
//		logger.debug("inserting: {}", key);
		try {
//			checkKeyValidity(key);
			PrefixTreeNode iterator = prefixTreeRoot;
			int currentCharIndex = -1;
			for (int level = 0; level < key.length(); level++) {
				currentCharIndex = treeType().findIndexOfChar(key.charAt(level));
				if (currentCharIndex >= 0 && currentCharIndex < maxCharacterSize()) {
					if (iterator.getChildren(currentCharIndex) == null) {
						iterator.setChildren(currentCharIndex, PrefixTreeFactory.createTreeRoot(treeType));
					}
					iterator = iterator.getChildren(currentCharIndex);
				}

			}
			iterator.setWord(true);
		} catch (Exception e) {
//			logger.error("Invalid key: {},ignored", key);
		}
	}

	/**
	 * This function will create prefix and inverted index map for all type of
	 * entities who implements SearchEntity interface
	 * 
	 * Splitter used here is space because we are trying to search for all the
	 * keywords entered by any user
	 * 
	 * @param entityList
	 */
	private void buildPrefixTree(List<? extends SearchEntity> entityList) {

		// creating inverted index map && prefix tree
		for (SearchEntity entity : entityList) {
			String name = entity.name().toLowerCase();
			String[] nameStrings = name.split(" ");

			for (String stringKey : nameStrings) {
				try {
					checkKeyValidity(stringKey);
					stringKey = stringKey.trim();
					List<Long> schemeIdList = invertedIndexVsEntityIds.getOrDefault(stringKey, new ArrayList<Long>());
					schemeIdList.add(entity.id());
					invertedIndexVsEntityIds.put(stringKey, schemeIdList);
					insertKey(stringKey);
				} catch (Exception e) {
//					logger.error("Invalid key ignored: {}", stringKey);
				}
			}
		}
	}

	@Override
	public PrefixTreeType treeType() {
		return treeType;
	}

	@Override
	public int maxCharacterSize() {
		return treeType.maxCharacterSize();
	}

	@Override
	public PrefixTreeNode root() {
		return prefixTreeRoot;
	}
}
