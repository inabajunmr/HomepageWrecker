package homepagewrecker.domain.service.wreck;

import java.nio.file.Path;

public interface HtmlWrecker {

	public String wreck(Path path, WreckCondition cond);

}
