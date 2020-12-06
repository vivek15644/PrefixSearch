package prefix.factory;

import prefix.enums.PrefixTreeType;
import prefix.interfaces.PrefixTreeNode;
import prefix.tree.nodes.LowerCaseCharPrefixTreeNode;

public class PrefixTreeFactory {
	public static PrefixTreeNode createTreeRoot(PrefixTreeType treeType) {
		PrefixTreeNode node = null;
		switch (treeType) {
		case LOWERCASE_PREFIX_TREE:
			node = new LowerCaseCharPrefixTreeNode(treeType.maxCharacterSize());
			break;
		default:
			node = new LowerCaseCharPrefixTreeNode(treeType.maxCharacterSize());
			break;
		}
		return node;
	}

}
