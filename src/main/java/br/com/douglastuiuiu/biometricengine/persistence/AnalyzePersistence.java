package br.com.douglastuiuiu.biometricengine.persistence;

import br.com.douglastuiuiu.biometricengine.model.document.Analyze;
import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author douglas
 * @since 21/03/2017
 */
@Repository
public class AnalyzePersistence {

    private static final String REQUEST_ID = "request.id";
    private static final String LAST_UPDATE = "lastUpdate";
    private static final String REQUEST_STATUS = "request.status";
    private static final String STORAGE_STATUS = "storageStatus";
    private static final String REQUEST_BUREAU_ID = "request.bureauId";
    @Value("${batch.scheduler.storagepurge.hours}")
    String hoursToPurge;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Analyze findByAnalyzeRequestId(String analyzeRequestId) {
        ObjectId objID = new ObjectId(analyzeRequestId);
        Query query = new Query(Criteria.where(REQUEST_ID).is(objID));
        return mongoTemplate.findOne(query.with(new Sort(Sort.Direction.DESC, LAST_UPDATE)), Analyze.class);
    }

    public void insert(Analyze analyze) {
        analyze.setRegisterDate(LocalDateTime.now());
        mongoTemplate.insert(analyze);
    }

    public List<Analyze> findExpiredAnalysis() {
        LocalDateTime date = LocalDateTime.now().minusHours(Integer.parseInt(hoursToPurge));
        Query query = new Query(Criteria
                .where(REQUEST_STATUS).in(AnalyzeStatusEnum.PROCCESSED)
                .and(LAST_UPDATE).lt(date)
        );
        return mongoTemplate.find(query.with(new Sort(Sort.Direction.DESC, LAST_UPDATE)), Analyze.class);
    }

    public Analyze updateAndFind(Analyze analyze) {
        Query query = new Query();
        query.addCriteria(Criteria.where(REQUEST_ID).is(analyze.getRequest().getId()));

        Update update = new Update();
        update.set(LAST_UPDATE, LocalDateTime.now());
        update.set(STORAGE_STATUS, analyze.getStorageStatus());

        mongoTemplate.updateFirst(query, update, AnalyzeRequest.class);
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Analyze.class);
    }

    public List<Analyze> findByBureauId(String bureauId) {
        Query query = new Query(Criteria.where(REQUEST_BUREAU_ID).in(bureauId));
        return mongoTemplate.find(query.with(new Sort(Sort.Direction.DESC, LAST_UPDATE)), Analyze.class);
    }
}
