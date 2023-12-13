package gossipme.services;

import gossipme.data.models.Entry;
import gossipme.data.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    EntryRepository entryRepository;
    @Override
    public void creatEntry(String title, String body, String diaryId) {
        Entry entry = new Entry();
        entry.setTitle(title);
        entry.setBody(body);
        entry.setDiaryId(diaryId);
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findAll() {
        return entryRepository.findAll();
    }
}
