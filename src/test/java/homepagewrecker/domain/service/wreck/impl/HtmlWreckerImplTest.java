package homepagewrecker.domain.service.wreck.impl;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

import homepagewrecker.domain.service.download.Downloader;
import homepagewrecker.domain.service.download.impl.DownloaderImpl;
import homepagewrecker.domain.service.wreck.HtmlWrecker;
import homepagewrecker.domain.service.wreck.WreckCondition;
import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.Converter;
import relative_to_absolute_path_html.converter.impl.ConverterImpl;
import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;
import relative_to_absolute_path_html.reader.impl.ReaderImpl;

public class HtmlWreckerImplTest {

	private HtmlWrecker wrecker = new HtmlWreckerImpl();

	private Downloader downloader = new DownloaderImpl();

	private Converter converter = new ConverterImpl();

	private Reader reader = new ReaderImpl();

	@Test
	public void test() throws IOException {
		URL targetUrl = new URL("https://www.google.co.jp/search?q=%E3%83%90%E3%83%8A%E3%83%8A%E3%83%9E%E3%83%B3&rlz=1C5CHFA_enJP727JP728&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjxhIrKzuTSAhVJUrwKHSRJBZkQ_AUICigD&biw=1440&bih=799");
		Path target = Paths.get("src/test/resources/test.html");
//		Files.delete(target);
		downloader.download(targetUrl, target);

		String htmlStr = reader.read(new ReadTargetCondition(target, StandardCharsets.UTF_8));
		String convertHtml = converter.convert(htmlStr, new ConvertCondition(targetUrl));
		Files.deleteIfExists(Paths.get("src/test/resources/test2.html"));
		Files.write(Paths.get("src/test/resources/test2.html"), convertHtml.getBytes(), StandardOpenOption.CREATE);

		String html = wrecker.wreck(convertHtml, new WreckCondition(targetUrl));
		Files.delete(target);
		Files.write(target, html.getBytes(), StandardOpenOption.CREATE);
	}

}
