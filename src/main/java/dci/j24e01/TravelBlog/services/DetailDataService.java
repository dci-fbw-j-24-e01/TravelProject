package dci.j24e01.TravelBlog.services;

import dci.j24e01.TravelBlog.models.DetailData;
import dci.j24e01.TravelBlog.models.VacationPoint;
import dci.j24e01.TravelBlog.repositories.DetailDataRepository;
import dci.j24e01.TravelBlog.repositories.VacationPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailDataService {
    private final DetailDataRepository detailDataRepository;
    private final VacationPointRepository vacationPointRepository;



    @Autowired
    public DetailDataService(VacationPointRepository vacationPointRepository,
                             DetailDataRepository detailDataRepository) {

        this.vacationPointRepository = vacationPointRepository;
        this.detailDataRepository = detailDataRepository;
    }

    public Optional<VacationPoint> findById(Long id) {
        return vacationPointRepository.findById(id);
    }

    public void saveDetailData(DetailData detailData) {
        VacationPoint vacationPoint = vacationPointRepository.findById(detailData.getVacationPoint().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Vacation Point ID"));

        detailData.setVacationPoint(vacationPoint);
        detailDataRepository.save(detailData);
    }
}
