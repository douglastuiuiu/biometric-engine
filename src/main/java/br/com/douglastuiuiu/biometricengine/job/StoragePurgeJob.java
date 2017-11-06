package br.com.douglastuiuiu.biometricengine.job;

import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author douglas
 * @since 06/04/2017
 */
@Component
public class StoragePurgeJob {

    private final static Logger logger = LoggerFactory.getLogger(StoragePurgeJob.class);

    @Autowired
    private StorageService storageService;

    @Scheduled(cron = "${batch.scheduler.storagepurge.cron}")
    public void run() {
        try {
            storageService.purgeAnalyzeExpiredFiles();
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
