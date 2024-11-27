package dci.j24e01.TravelBlog.services;

import dci.j24e01.TravelBlog.models.DetailData;
import dci.j24e01.TravelBlog.repositories.DetailDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailDataService {
    private final DetailDataRepository repository;

    @Autowired
    public DetailDataService(DetailDataRepository repository) {
        this.repository = repository;
    }

    public Optional<DetailData> findById(Long id) {
        return repository.findById(id);
    }
}
