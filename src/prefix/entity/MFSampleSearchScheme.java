package prefix.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prefix.search.consumer.interfaces.SearchEntity;
import prefix.search.service.manager.SearchServiceManager;

/**
 * sample entity for testing
 * 
 * @author vivek15644
 *
 */
public class MFSampleSearchScheme implements SearchEntity {

	private long schemeId;
	private String nameOfScheme;

	public static Map<Long, MFSampleSearchScheme> schemeIdVsSchemeMap;

	public static void initialize() {
		MFSampleSearchScheme mfScheme1 = new MFSampleSearchScheme(1, "Tata");
		MFSampleSearchScheme mfScheme2 = new MFSampleSearchScheme(2, "Tata mutual");
		MFSampleSearchScheme mfScheme3 = new MFSampleSearchScheme(3, "fund");
		List<MFSampleSearchScheme> list = new ArrayList<MFSampleSearchScheme>();
		list.add(mfScheme1);
		list.add(mfScheme2);
		list.add(mfScheme3);
		schemeIdVsSchemeMap = new HashMap<Long, MFSampleSearchScheme>();
		for (MFSampleSearchScheme mfSearchScheme : list) {
			schemeIdVsSchemeMap.put(mfSearchScheme.schemeId, mfSearchScheme);
		}
		// this will initialize the prefix tree with the scheme names
		SearchServiceManager.schemeSearchService().initialize(list);

	}

	public MFSampleSearchScheme(long schemeId, String name) {
		this.schemeId = schemeId;
		this.nameOfScheme = name;
	}

	public MFSampleSearchScheme() {
	}

	public void setNameOfScheme(String nameOfScheme) {
		this.nameOfScheme = nameOfScheme;
	}

	public void setSchemeId(long schemeId) {
		this.schemeId = schemeId;
	}

	public String nameOfScheme() {
		return nameOfScheme;
	}

	public long schemeId() {
		return schemeId;
	}

	@Override
	public long id() {
		return schemeId;
	}

	@Override
	public String name() {
		return nameOfScheme;
	}

}
