package prefix.interfaces;

import java.util.List;
import java.util.Map;

import prefix.enums.PrefixTreeType;
import prefix.exception.InvalidKeyException;

public interface PrefixTree {

	PrefixTreeType treeType();

	int maxCharacterSize();

	PrefixTreeNode root();

	Map<String, List<Long>> invertedIndexVsEntityIds();

	void checkKeyValidity(String key) throws InvalidKeyException;

}
