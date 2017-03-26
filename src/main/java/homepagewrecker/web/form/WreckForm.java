package homepagewrecker.web.form;

public class WreckForm {
	public WreckForm() {
		super();
		this.wreckCoefficient = 100;
		this.regularityCoefficient = 100;
		this.increaseCoefficient = 100;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getWreckCoefficient() {
		return wreckCoefficient;
	}
	public void setWreckCoefficient(int wreckCoefficient) {
		this.wreckCoefficient = wreckCoefficient;
	}
	public int getRegularityCoefficient() {
		return regularityCoefficient;
	}
	public void setRegularityCoefficient(int regularityCoefficient) {
		this.regularityCoefficient = regularityCoefficient;
	}
	public int getIncreaseCoefficient() {
		return increaseCoefficient;
	}
	public void setIncreaseCoefficient(int increaseCoefficient) {
		this.increaseCoefficient = increaseCoefficient;
	}
	
	/**
	 * 破壊対象のサイトURL
	 */
	private String url;
	
	/**
	 * 破壊の度合い
	 */
	private int wreckCoefficient;
	
	/**
	 * 破壊時の秩序の度合い
	 */
	private int regularityCoefficient;
	
	/**
	 * 破壊時の増殖の度合い
	 */
	private int increaseCoefficient;


}
