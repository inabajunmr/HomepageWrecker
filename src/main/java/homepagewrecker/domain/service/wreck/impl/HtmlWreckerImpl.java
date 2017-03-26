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
		Element body = doc.getElementsByTag("body").get(0);
		int size = body.getAllElements().size();
		System.out.println("対象ノードの数" + size);

		//TODO 実験レベル 係数を使って破壊具合をコントロールできるようにする

		//全エレメントの数を25で割った数を平均として、入れ替えをループする
		System.out.println("WRECKC:"+cond.getWreckCoefficient());
		int wreckCount = size / 25 ;
		wreckCount = (wreckCount * cond.getWreckCoefficient()) / 10;
		System.out.println("WRECK:"+wreckCount);

		for(int i = 0; i < wreckCount; i++){
			//変換対象のエレメント
			int targetIndex = ((int)(Math.random() * size));
			Element target = body.getAllElements().get(targetIndex);
			String selfTag = target.tagName();
			String parentTag = target.parent().tagName();

			//ターゲットとなるタグを削除
			target.remove();

			//削除後のエレメント数
			size = body.getAllElements().size();

			if(randomBool(cond.getRegularityCoefficient() * 10)){
				//親が同じタグに差し込む
				Elements sameParents = body.getElementsByTag(parentTag);
				if(sameParents.size() == 0){
					continue;
				}
				int count = 0;
				while(true){
					sameParents.get(random(sameParents.size())).appendChild(target.clone());
					if(count++ > 100) break;
				}

			}else{
				//targetと同一タグの同一階層に差し込む
				Elements sameTarget = body.getElementsByTag(selfTag);
				if(sameTarget.size() == 0){
					continue;
				}

				//TODO たまにnullぽで落ちる
				int count = 0;
				int targetindex = random(sameTarget.size());
				Element insertTargetElement = sameTarget.get(targetindex);
				while(randomBool(cond.getIncreaseCoefficient() * 5)){
					System.out.println("count:" + count);

					insertTargetElement.after(target.clone());
					if(count++ == 30) break;
				}
			}
		}

		return doc.outerHtml();
	}

	/**
	 * 引数の確率でtrueを返却します。
	 * @param percent
	 * @return
	 */
	private boolean randomBool(int percent){
		if(percent >= 100){
			return true;
		}

		if(percent <= 0){
			return false;
		}

		int value = random(100);
		if(value <= percent){
			return true;
		}
		return false;
	}

	/**
	 * 引数を最大値としてランダムな整数を返却します。
	 * @param max
	 * @return
	 */
	private int random(int max){
		return ((int)(Math.random() * max));
	}
}
