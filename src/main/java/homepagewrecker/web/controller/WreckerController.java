package homepagewrecker.web.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import homepagewrecker.domain.service.download.Downloader;
import homepagewrecker.domain.service.wreck.HtmlWrecker;
import homepagewrecker.domain.service.wreck.WreckCondition;
import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.Converter;
import relative_to_absolute_path_html.converter.impl.ConverterImpl;
import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;
import relative_to_absolute_path_html.reader.impl.ReaderImpl;

@Controller
public class WreckerController {

	@Autowired
	private Downloader downloader;

	@Autowired
	private HtmlWrecker wrecker;

	private Reader reader = new ReaderImpl();

	private Converter converter = new ConverterImpl();

	private final String destination = "/Applications/Eclipse_4.6.2.app/Contents/workspace/HomepageWrecker/src/test/resources";

	//TODO test用なのでXSS対策してないです
    @RequestMapping("inputUrl")
    String viewInputUrlForm() {
        return "wreck/inputUrl";
    }

    @RequestMapping(value = "wreck", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    String wreck(String url) {

    	String destinationPath = destination + "web.html";

    	try {
			downloader.download(new URL(url), Paths.get(destinationPath));
			String html = reader.read(new ReadTargetCondition(Paths.get(destinationPath), StandardCharsets.UTF_8));
			String convertHtml = converter.convert(html, new ConvertCondition(new URL(url)));
			String result = wrecker.wreck(convertHtml, new WreckCondition(new URL(url)));
			return result;
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

        return "wreck/inputUrl";
    }


}
