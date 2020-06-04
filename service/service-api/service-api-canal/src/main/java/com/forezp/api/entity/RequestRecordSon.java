package com.forezp.api.entity;

import java.util.List;

public class RequestRecordSon extends RequestRecord {
    List<JournalSon> journalList;

    public List<JournalSon> getJournalList() {
        return journalList;
    }

    public void setJournalList(List<JournalSon> journalList) {
        this.journalList = journalList;
    }
}
