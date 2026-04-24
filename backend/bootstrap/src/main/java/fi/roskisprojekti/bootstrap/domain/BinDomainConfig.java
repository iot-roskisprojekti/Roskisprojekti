package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.bin.CreateBinUseCase;
import fi.roskisprojekti.application.port.in.bin.DeleteBinUseCase;
import fi.roskisprojekti.application.port.in.bin.FindBinsUseCase;
import fi.roskisprojekti.application.port.in.bin.ModifyBinUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.service.bin.CreateBinService;
import fi.roskisprojekti.application.service.bin.DeleteBinService;
import fi.roskisprojekti.application.service.bin.FindBinsService;
import fi.roskisprojekti.application.service.bin.ModifyBinService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BinDomainConfig {
    @Bean
    public CreateBinUseCase createBinUseCase(BinRepository binRepository) {
        return new CreateBinService(binRepository);
    }

    @Bean
    public ModifyBinUseCase modifyBinUseCase(BinRepository binRepository) {
        return new ModifyBinService(binRepository);
    }

    @Bean
    public FindBinsUseCase findBinsUseCase(BinRepository binRepository) {
        return new FindBinsService(binRepository);
    }

    @Bean
    public DeleteBinUseCase deleteBinsUseCase(BinRepository binRepository) {
        return new DeleteBinService(binRepository);
    }

}
