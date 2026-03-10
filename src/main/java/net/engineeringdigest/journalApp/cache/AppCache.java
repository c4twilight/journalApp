package net.engineeringdigest.journalApp.cache;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AppCache {

    public enum keys{
        WEATHER_API;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        try {
            List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
            for (ConfigJournalAppEntity configJournalAppEntity : all) {
                appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
            }
        } catch (Exception ex) {
            // Keep app startup alive even when external DB is unavailable.
            log.warn("Unable to refresh app cache from DB. Continuing with in-memory defaults.", ex);
        }
    }

}
