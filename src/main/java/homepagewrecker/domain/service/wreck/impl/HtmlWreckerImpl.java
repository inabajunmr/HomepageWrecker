package homepagewrecker.domain.service.wreck.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import homepagewrecker.domain.service.wreck.HtmlWrecker;
import homepagewrecker.domain.service.wreck.WreckCondition;
import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.Converter;
import relative_to_absolute_path_html.converter.impl.ConverterImpl;
import relative_to_absolute_path_html.reader.Reader;
import relative_to_absolute_path_html.reader.impl.ReaderImpl;

@Service
public class HtmlWreckerImpl implements HtmlWrecker{

	Reader reader = new ReaderImpl();
	Converter converter = new ConverterImpl();

	@Override
	public String wreck(String htmlStr, WreckCondition cond) {
		String convertHtml = converter.convert(htmlStr, new ConvertCondition(cond.getSourceUrl()));
		Document doc = Jsoup.parse(convertHtml);
		Elements elements = doc.getAllElements();
		int size = elements.size();


		//TODO 実験レベル
		for(int i = 0;i < 100; i++){
			Element element = elements.remove((int)Math.random() * size);
			elements.add((int)Math.random() * size, element);
		}

		return elements.html();
	}

}
