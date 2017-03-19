package homepagewrecker.domain.service.wreck.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
		Element body = doc.getElementsByTag("body").get(0);
		int size = body.getAllElements().size();
		System.out.println("対象ノードの数" + size);

		//TODO 実験レベル
		for(int i = 0;i < 50; i++){
			int deleteTargetIndex = ((int)(Math.random() * size));
			Element element = body.getAllElements().get(deleteTargetIndex);
			if(body.getElementsByTag(element.tagName()).size() >= 2){
				int childSize = body.getElementsByTag(element.tagName()).size();
			}
			System.out.println("削除対象インデックス:" + deleteTargetIndex);
			if(!element.tagName().equals("body")){
				element.remove();
				size = body.getAllElements().size();
				System.out.println("対象ノードの数" + size);

				int addTargetIndex = ((int)(Math.random() * size));
				body.getAllElements().get(addTargetIndex).appendChild(element);

				size = body.getAllElements().size();
				System.out.println("対象ノードの数" + size);
			}
//			Element targetElement = elements.get(deleteTargetIndex);
//			if(targetElement.tagName().equals("body")){
//				continue;
//			}
//			Element element = elements.remove(deleteTargetIndex);
////			elements.add(((int)(Math.random() * size)), element);
		}

		return doc.outerHtml();
	}

}
