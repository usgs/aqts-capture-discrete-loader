package gov.usgs.wma.waterdata;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

@Component
public class ObservationDao {
    private static final Logger LOG = LoggerFactory.getLogger(ObservationDao.class);

    @Autowired
    @Qualifier("jdbcTemplateObservation")
    protected JdbcTemplate jdbcTemplate;

    @Value("classpath:sql/deleteDiscreteGroundWater.sql")
    protected Resource deleteQuery;

    @Value("classpath:sql/insertDiscreteGroundWater.sql")
    protected Resource insertQuery;

    @Transactional
    public Integer deleteDiscreteGroundWater(String fieldVisitIdentifier) {
        Integer rowsDeletedCount = null;
        try {
            String sql = new String(FileCopyUtils.copyToByteArray(deleteQuery.getInputStream()));
            rowsDeletedCount = jdbcTemplate.update(
                    sql,
                    fieldVisitIdentifier
            );
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Couldn't find {} - {}", fieldVisitIdentifier, e.getLocalizedMessage());
        } catch (IOException e) {
            LOG.error("Unable to get SQL statement", e);
            throw new RuntimeException(e);
        }
        return rowsDeletedCount;
    }

    @Transactional
    public int insertDiscreteGroundWater(List<DiscreteGroundWater> discreteGroundWater) {
        int rowsInsertedCount = 0;
        try {
            String sql = new String(FileCopyUtils.copyToByteArray(insertQuery.getInputStream()));
            int [] rowsInsertedCounts = jdbcTemplate.batchUpdate(
                    sql,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, discreteGroundWater.get(i).getFieldVisitIdentifier());
                            ps.setTimestamp(2, discreteGroundWater.get(i).getFieldVisitTime());
                            ps.setString(3, discreteGroundWater.get(i).getParmCd());
                            ps.setTimestamp(4, discreteGroundWater.get(i).getFieldVisitTime());
                            ps.setTimestamp(5, discreteGroundWater.get(i).getFieldVisitTime());
                            ps.setString(6, discreteGroundWater.get(i).getFieldVisitValue());
                            ps.setString(7, discreteGroundWater.get(i).getAgencyCode());
                            ps.setString(8, discreteGroundWater.get(i).getUncertainty());
                            ps.setString(9, discreteGroundWater.get(i).getMonitoringMethod());
                            ps.setString(10, discreteGroundWater.get(i).getMonitoringLocationIdentifier());
                        }
                        @Override
                        public int getBatchSize() {
                            return discreteGroundWater.size();
                        }
                    }
            );
            rowsInsertedCount = Arrays.stream(rowsInsertedCounts).sum();
        } catch (EmptyResultDataAccessException e) {
            LOG.info(e.getLocalizedMessage());
        } catch (IOException e) {
            LOG.error("Unable to get SQL statement", e);
            throw new RuntimeException(e);
        }
        return rowsInsertedCount;
    }
}
