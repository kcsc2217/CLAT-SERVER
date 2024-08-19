package team_project.clat.repository;

import org.springframework.stereotype.Repository;
import team_project.clat.service.VerificationCode;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VerificationCodeRepository {

    private final Map<String, VerificationCode> repository = new ConcurrentHashMap<>();
    private final Map<String, String> toRepo = new ConcurrentHashMap<>();

    public void save(VerificationCode verificationCode) {
        repository.put(verificationCode.getCode(), verificationCode);
    }

    public void toSave(String to, String code) {
        toRepo.put(to, code);
    }

    public Optional<VerificationCode> findByCode(String code) {
        return Optional.ofNullable(repository.get(code));
    }
    public Optional<String> findByTo(String to) {
        return Optional.ofNullable(toRepo.get(to));
    }

    public void remove(VerificationCode verificationCode) {
        repository.remove(verificationCode.getCode());
    }
    public void toRemove(String to) {
        repository.remove(to);
    }
}
