package prefix.search.consumer.interfaces;

import java.util.List;

/**
 * To get more details, Please use SchemeSearchService for reference
 * 
 * @author vivek.kumar8
 *
 * @param <T>
 */
public interface SearchService<T> {
	void initialize(List<? extends T> entityList);

	List<Long> getAllEntityIds(String searchKeyWord);

	List<? extends T> getAllEntitiesWithSearchKey(String searchKeyWord);
}
