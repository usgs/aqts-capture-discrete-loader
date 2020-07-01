package gov.usgs.wma.waterdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class LoadDiscreteGroundWaterHandler extends SpringBootRequestHandler<RequestObject, ResultObject> {
    private static final Logger LOG = LoggerFactory.getLogger(LoadDiscreteGroundWaterHandler.class);
}
