package com.yedam.TripAdvisor.Currency;

import lombok.Data;

@Data
public class CurrencyDataVO {
	private String countryId;
	private String countryName;
	private String currency;

	public CurrencyDataVO(String countryId, String countryName, String currency) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.currency = currency;
	}
	@Override
	public String toString() {
		return countryId+" "+countryName+" "+currency;
	}


}
