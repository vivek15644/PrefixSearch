package prefix.search.consumer.interfaces;

/**
 * Implement this interface in the entity which is needed to be stored in trie
 * data structure
 * 
 * @author vivek.kumar8
 *
 */
public interface SearchEntity {
	long id();

	String name();

}
