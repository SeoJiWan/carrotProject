package service;

import java.util.List;

import domain.SuseongMap;
import repository.jdbc.JdbcSuseongMapRepository;

public class SuseongMapService {
	
	/*
	 * Field
	 */
	private final JdbcSuseongMapRepository jdbcSuseongMapRepository;

	
	/*
	 * Constructor
	 */
	public SuseongMapService(JdbcSuseongMapRepository jdbcSuseongMapRepository) {
		this.jdbcSuseongMapRepository = jdbcSuseongMapRepository;
	}

	/*
	 * Method
	 */
	public List<SuseongMap> findAllRegion() {
		return jdbcSuseongMapRepository.selectAll();
	}
	
	public SuseongMap findOneByName(String emdNn) {
		return jdbcSuseongMapRepository.selectOneByName(emdNn);
	}
	
	public SuseongMap findOneByCode(int emdCd) {
		return jdbcSuseongMapRepository.selectOneByCode(emdCd);
	}

}
