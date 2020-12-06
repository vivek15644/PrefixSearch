package prefix.interfaces;

public interface PrefixTreeNode {
	PrefixTreeNode getChildren(int index);

	void setChildren(PrefixTreeNode[] children);

	boolean isWord();

	void setWord(boolean isWord);

	void setChildren(int index, PrefixTreeNode treeNode);
}
