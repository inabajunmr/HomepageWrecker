package homepagewrecker.domain.service.wreck;

import java.net.URL;

public class WreckCondition {
	public WreckCondition(URL sourceUrl, int wreckCoefficient, int regularityCoefficient, int increaseCoefficient) {
		super();
		this.sourceUrl = sourceUrl;
		this.wreckCoefficient = wreckCoefficient;
		this.regularityCoefficient = regularityCoefficient;
		this.increaseCoefficient = increaseCoefficient;
	}

	/**
	 * 係数を全てデフォルトとする場合のコンストラクタ
	 * @param sourceUrl
	 */
	public WreckCondition(URL sourceUrl) {
		super();
		this.sourceUrl = sourceUrl;
		this.wreckCoefficient = 10;
		this.regularityCoefficient = 10;
		this.increaseCoefficient = 10;
	}

	public int getWreckCoefficient() {
		return wreckCoefficient;
	}

	public int getRegularityCoefficient() {
		return regularityCoefficient;
	}

	public int getIncreaseCoefficient() {
		return increaseCoefficient;
	}

	public URL getSourceUrl() {
		return sourceUrl;
	}

	private URL sourceUrl;

	/**
	 * どれくらいめちゃくちゃにするかの係数（10を普通とする）
	 */
	private int wreckCoefficient;

	/**
	 * 秩序係数.低いほど秩序を保ったまま破壊する（10を普通とする.）
	 */
	private int regularityCoefficient;

	/**
	 * 増加係数.高いほど要素数が増える（10を普通とし、0の場合削除のみが行われる）
	 */
	private int increaseCoefficient;

}
