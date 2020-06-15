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
    public Integer deleteDiscreteGroundWater(String discreteGroundWaterUniqueId) {
        Integer rowsDeletedCount = null;
        try {
            String sql = new String(FileCopyUtils.copyToByteArray(deleteQuery.getInputStream()));
            rowsDeletedCount = jdbcTemplate.update(
                    sql,
                    discreteGroundWaterUniqueId
            );
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Couldn't find {} - {} ", discreteGroundWaterUniqueId, e.getLocalizedMessage());
        } catch (IOException e) {
            LOG.error("Unable to get SQL statement", e);
            throw new RuntimeException(e);
        }
        return rowsDeletedCount;
    }

    @Transactional
    public int insertDiscreteGroundWater(String fieldVisitIdentifier, List<DiscreteGroundWater> discreteGroundWater) {
        int rowsInsertedCount = 0;
        try {
            String sql = new String(FileCopyUtils.copyToByteArray(insertQuery.getInputStream()));
            int [] rowsInsertedCounts = jdbcTemplate.batchUpdate(
                    sql,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, discreteGroundWater.get(i).getFieldVisitIdentifier());
                            ps.setString(2, discreteGroundWater.get(i).getLocationIdentifier());
                            ps.setTimestamp(3, discreteGroundWater.get(i).getStartTime());
                            ps.setTimestamp(4, discreteGroundWater.get(i).getEndTime());
                            ps.setString(5, discreteGroundWater.get(i).getParty());
                            ps.setString(6, discreteGroundWater.get(i).getRemarks());
                            ps.setString(7, discreteGroundWater.get(i).getWeather());
                            ps.setString(8, discreteGroundWater.get(i).getIsValidHeaderInfo());
                            ps.setString(9, discreteGroundWater.get(i).getCompletedWork());
                            ps.setTimestamp(10, discreteGroundWater.get(i).getLastModified());
                            ps.setString(11, discreteGroundWater.get(i).getParameter());
                            ps.setString(12, discreteGroundWater.get(i).getParmCd());
                            ps.setString(13, discreteGroundWater.get(i).getMonitoringMethod());
                            ps.setString(14, discreteGroundWater.get(i).getFieldVisitValue());
                            ps.setString(15, discreteGroundWater.get(i).getUnit());
                            ps.setString(16, discreteGroundWater.get(i).getUncertainty());
                            ps.setString(17, discreteGroundWater.get(i).getReadingType());
                            ps.setString(18, discreteGroundWater.get(i).getManufacturer());
                            ps.setString(19, discreteGroundWater.get(i).getModel());
                            ps.setString(20, discreteGroundWater.get(i).getSerialNumber());
                            ps.setTimestamp(21, discreteGroundWater.get(i).getFieldVisitTime());
                            ps.setString(22, discreteGroundWater.get(i).getFieldVisitComments());
                            ps.setString(23, discreteGroundWater.get(i).getPublish());
                            ps.setString(24, discreteGroundWater.get(i).getIsValidReadings());
                            ps.setString(25, discreteGroundWater.get(i).getReferencePointUniqueId());
                            ps.setString(26, discreteGroundWater.get(i).getUseLocationDatumAsReference());
                            ps.setString(27, discreteGroundWater.get(i).getReadingQualifier());
                            ps.setString(28, discreteGroundWater.get(i).getReadingQualifiers());
                            ps.setString(29, discreteGroundWater.get(i).getGroundWaterMeasurement());
                        }
                        @Override
                        public int getBatchSize() {
                            return discreteGroundWater.size();
                        }
                    }
            );
            rowsInsertedCount = Arrays.stream(rowsInsertedCounts).sum();
        } catch (EmptyResultDataAccessException e) {
            LOG.info("Couldn't find {} - {} ", fieldVisitIdentifier, e.getLocalizedMessage());
        } catch (IOException e) {
            LOG.error("Unable to get SQL statement", e);
            throw new RuntimeException(e);
        }
        return rowsInsertedCount;
    }
}
