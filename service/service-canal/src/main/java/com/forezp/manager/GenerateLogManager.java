package com.forezp.manager;

import com.forezp.api.entity.*;
import com.forezp.api.service.able.JournalService;
import com.forezp.api.service.able.TablevalueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GenerateLogManager {
    @Resource
    private JournalService journalService;
    @Resource
    private TablevalueService tablevalueService;

    public void GenerateLog(RequestRecordSon requestRecordSon) {
        if (requestRecordSon != null) {
            for (JournalSon journalSon : requestRecordSon.getJournalList()) {
                journalSon.setRequestRecordId(requestRecordSon.getId().toString());
                journalService.save(journalSon);
                if (journalSon.getBeforeColumns() != null) {
                    for (Tablevalue tablevalue : journalSon.getBeforeColumns()) {
                        tablevalue.setJournalId(journalSon.getId().toString());
                        tablevalueService.save(tablevalue);
                    }
                }
                if (journalSon.getAfterColumns() != null) {
                    for (Tablevalue tablevalue : journalSon.getAfterColumns()) {
                        tablevalue.setJournalId(journalSon.getId().toString());
                        tablevalueService.save(tablevalue);
                    }
                }
                if (journalSon.getInsertData() != null) {
                    for (Tablevalue tablevalue : journalSon.getInsertData()) {
                        tablevalue.setJournalId(journalSon.getId().toString());
                        tablevalueService.save(tablevalue);
                    }
                }
                if (journalSon.getDeleteData() != null) {
                    for (Tablevalue tablevalue : journalSon.getDeleteData()) {
                        tablevalue.setJournalId(journalSon.getId().toString());
                        tablevalueService.save(tablevalue);
                    }
                }
            }
        }
    }
}
