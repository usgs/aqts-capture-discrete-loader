package gov.usgs.wma.waterdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class TransformDao {
	private static final Logger LOG = LoggerFactory.getLogger(TransformDao.class);

	@Autowired
	@Qualifier("jdbcTemplateTransform")
	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Value("classpath:sql/getDiscreteGroundWater.sql")
	protected Resource selectQuery;

	public List<DiscreteGroundWater> getDiscreteGroundWater(List<String> fieldVisitIdentifiers) {
		List<DiscreteGroundWater> rtn = Collections.emptyList();
		SqlParameterSource identifiers = new MapSqlParameterSource("ids", fieldVisitIdentifiers);
		try {
			String sql = new String(FileCopyUtils.copyToByteArray(selectQuery.getInputStream()));
			rtn = jdbcTemplate.query(
					sql,
					identifiers,
					new DiscreteGroundWaterRowMapper());
		} catch (EmptyResultDataAccessException e) {
			LOG.info("Couldn't find {} - {} ", fieldVisitIdentifiers, e.getLocalizedMessage());
		} catch (IOException e) {
			LOG.error("Unable to get SQL statement", e);
			throw new RuntimeException(e);
		}
		return rtn;
	}
}
