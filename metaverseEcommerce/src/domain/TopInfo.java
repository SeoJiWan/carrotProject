package domain;

public class TopInfo {
	
	/*
	 * Field
	 */
	private String identification;
	private int saleOrBuyCnt;


	/*
	 * Method
	 */
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public int getSaleOrBuyCnt() {
		return saleOrBuyCnt;
	}
	public void setSaleOrBuyCnt(int saleOrBuyCnt) {
		this.saleOrBuyCnt = saleOrBuyCnt;
	}
	@Override
	public String toString() {
		return "TopInfo [identification=" + identification + ", saleOrBuyCnt=" + saleOrBuyCnt + "]";
	}
}
