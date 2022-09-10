package service;

import java.util.List;

import domain.Sale;
import domain.SaleInfo;
import repository.inerface.SaleRepository;

public class SaleService {

	/*
	 * Field
	 */
	private final SaleRepository saleRepository;

	/*
	 * Constructor
	 */
	public SaleService(SaleRepository saleRepository) {
		this.saleRepository = saleRepository;
	}

	/*
	 * Method
	 */
	// 판매 등록
	public void createSale(Sale sale) {
		saleRepository.insert(sale);
	}

	// 판매 수정 - 판매상태 수정?
	public void updateSale(Sale sale) {
		saleRepository.update(sale);
	}

	// 판매 삭제
	public void deleteSale(int saleId) {
		saleRepository.delete(saleId);
	}

	// 판매 단건조회
	public Sale findOneSale(int saleId) {
		return saleRepository.selectOne(saleId);
	}

	// 판매 전체조회
	public List<SaleInfo> findAllSales(int loginMemberId) {
		return saleRepository.selectAll(loginMemberId);
	}

	// 판매 키워드조회
	public List<SaleInfo> findAllSalesByKeyword(int loginMemberId, String keyword) {
		return saleRepository.selectAllByKeyword(loginMemberId, keyword);
	}

	// 판매 키워드조회
	public List<SaleInfo> findAllSalesByNeighbor(int loginMemberId, int prevEmdCd, int nextEmdCd) {
		return saleRepository.selectAllByNeighbor(loginMemberId, prevEmdCd, nextEmdCd);
	}

}
