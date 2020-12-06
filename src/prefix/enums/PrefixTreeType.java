package prefix.enums;

import java.util.regex.Pattern;

public enum PrefixTreeType {
	LOWERCASE_PREFIX_TREE {

		@Override
		public int maxCharacterSize() {

			return 26;
		}

		@Override
		public Pattern getKeyRegex() {
			return Pattern.compile("[a-z]*");
		}

		@Override
		public int findIndexOfChar(char input) {
			int index = input - 'a';
			return index < 0 || index >= maxCharacterSize() ? -1 : index;
		}

		@Override
		public char getChar(int index) {
			int charAscii = index + 97;
			return (char) charAscii;
		}

	};

	public abstract int maxCharacterSize();

	public abstract Pattern getKeyRegex();

	public abstract int findIndexOfChar(char input);

	public abstract char getChar(int index);

}
