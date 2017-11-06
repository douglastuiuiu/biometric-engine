package br.com.douglastuiuiu.biometricengine.persistence;

import br.com.douglastuiuiu.biometricengine.model.document.AnalyzeRequest;
import br.com.douglastuiuiu.biometricengine.model.enumeration.AnalyzeStatusEnum;
import br.com.douglastuiuiu.biometricengine.model.enumeration.ConsumerTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AnalyzeRequestPersistence {

    private static final String LAST_UPDATE = "lastUpdate";
    private static final String STATUS = "status";
    private static final String BUREAU_ID = "bureauId";
    private static final String PERSON_PHOTO = "person.photo";
    private static final String ID = "id";
    private static final String CONSUMER_TYPE = "consumerType";
    private static final String PERSON_CPF = "person.cpf";
    @Autowired
    private MongoTemplate mongoTemplate;

    public AnalyzeRequest findByStatusAndConsumerTypeAndCpfInOrderByIdDesc(List<AnalyzeStatusEnum> analyzeStatusList, ConsumerTypeEnum consumerType, String cpf) {
        Query query = new Query(Criteria
                .where(STATUS).in(analyzeStatusList)
                .and(CONSUMER_TYPE).is(consumerType)
                .and(PERSON_CPF).is(cpf)
        );
        List<AnalyzeRequest> list = mongoTemplate.find(query.with(new Sort(Sort.Direction.DESC, "_id")).limit(1), AnalyzeRequest.class);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public AnalyzeRequest findById(String id) {
        return mongoTemplate.findById(id, AnalyzeRequest.class);
    }

    public AnalyzeRequest insertAndFind(AnalyzeRequest analyzeRequest) {
        analyzeRequest.setRegisterDate(LocalDateTime.now());
        mongoTemplate.insert(analyzeRequest);
        return findById(analyzeRequest.getId());
    }

    public AnalyzeRequest updateAndFind(AnalyzeRequest analyzeRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(analyzeRequest.getId()));

        Update update = new Update();
        update.set(LAST_UPDATE, LocalDateTime.now());
        update.set(STATUS, analyzeRequest.getStatus());
        if (analyzeRequest.getBureauId() != null) {
            update.set(BUREAU_ID, analyzeRequest.getBureauId());
        }
        if (analyzeRequest.getPerson().getPhoto() != null) {
            update.set(PERSON_PHOTO, analyzeRequest.getPerson().getPhoto());
        }

        mongoTemplate.updateFirst(query, update, AnalyzeRequest.class);

        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), AnalyzeRequest.class);
    }

}
