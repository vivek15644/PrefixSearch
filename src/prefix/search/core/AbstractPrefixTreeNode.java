package prefix.search.core;

import prefix.interfaces.PrefixTreeNode;

public abstract class AbstractPrefixTreeNode implements PrefixTreeNode {

	private PrefixTreeNode[] children;
	private boolean isWord;

	public PrefixTreeNode[] getChildren() {
		return children;
	}

	@Override
	public PrefixTreeNode getChildren(int index) {
		return children[index];
	}

	public AbstractPrefixTreeNode(int size) {
		this.children = new PrefixTreeNode[size];
		this.isWord = false;
	}

	@Override
	public void setChildren(PrefixTreeNode[] children) {
		this.children = children;
	}

	@Override
	public void setChildren(int index, PrefixTreeNode treeNode) {
		this.children[index] = treeNode;
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

}
