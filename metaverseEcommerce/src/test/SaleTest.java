package test;

import java.util.List;

import domain.Sale;
import domain.SaleInfo;
import repository.jdbc.JdbcSaleRepository;
import service.SaleService;

public class SaleTest {
	
	public static void main(String[] args) {
		SaleService saleService = new SaleService(JdbcSaleRepository.getSaleRepository());
		
		Sale findOneSale = saleService.findOneSale(1);
		System.out.println(findOneSale);
		
		List<SaleInfo> findAllSales = saleService.findAllSales(1);
		findAllSales.forEach(System.out::println);
		System.out.println(findAllSales.size());
		
	}

}
