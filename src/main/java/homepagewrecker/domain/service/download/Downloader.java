package homepagewrecker.domain.service.download;

import java.net.URL;
import java.nio.file.Path;

/**
 * 加工対象のファイルをダウンロードするためのクラス
 * @author inabajunmr
 *
 */
public interface Downloader {

	/**
	 * URLにアクセスし、ファイルをダウンロードします。<br>
	 * @param url
	 * @param destination ダウンロードしたファイルの配置先
	 */
	public void download(URL url, Path destination);

}
