package homepagewrecker.domain.service.wreck.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import homepagewrecker.domain.service.wreck.HtmlWrecker;
import homepagewrecker.domain.service.wreck.WreckCondition;
import relative_to_absolute_path_html.converter.ConvertCondition;
import relative_to_absolute_path_html.converter.Converter;
import relative_to_absolute_path_html.converter.impl.ConverterImpl;
import relative_to_absolute_path_html.reader.ReadTargetCondition;
import relative_to_absolute_path_html.reader.Reader;
import relative_to_absolute_path_html.reader.impl.ReaderImpl;

@Service
public class HtmlWreckerImpl implements HtmlWrecker{

	Reader reader = new ReaderImpl();
	Converter converter = new ConverterImpl();

	@Override
	public String wreck(Path path, WreckCondition cond) {
		try {
			String html = reader.read(new ReadTargetCondition(path, StandardCharsets.UTF_8));
			String convertHtml = converter.convert(html, new ConvertCondition(cond.getSourceUrl()));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
