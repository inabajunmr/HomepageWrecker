package homepagewrecker.domain.service.wreck;

import java.net.URL;

public class WreckCondition {
	public WreckCondition(URL sourceUrl) {
		super();
		this.sourceUrl = sourceUrl;
	}

	public URL getSourceUrl() {
		return sourceUrl;
	}

	private URL sourceUrl;

}
