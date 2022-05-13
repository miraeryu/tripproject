package com.yedam.TripAdvisor.Currency;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyInfo {
	
	private Long result; // 서버 통신여부
	private String cur_unit; //통화 코드
	private String deal_bas_r; //매매 기준율



}
