package gossipme.services;

import gossipme.data.models.Entry;

import java.util.List;

public interface EntryService {
    void creatEntry(String title,String body,String diaryId);
    List <Entry> findAll ();


}
