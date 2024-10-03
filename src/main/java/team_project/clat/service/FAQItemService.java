package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.FAQItem;
import team_project.clat.exception.GlobalException;
import team_project.clat.repository.FAQItemRepository;
import team_project.clat.exception.type.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FAQItemService {
  private final FAQItemRepository faqItemRepository;

  public List<FAQItem> getFAQItems() {
    return faqItemRepository.findAll();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Transactional
  public FAQItem createFAQItem(FAQItem faqItem) {
    return faqItemRepository.save(new FAQItem(
            null,
            faqItem.getTitle(),
            faqItem.getDescription(),
            faqItem.getComment()
    ));
  }

  @Transactional
  public FAQItem getFAQItem(Long faqId) {
    return faqItemRepository.findById(faqId)
            .orElseThrow(() -> new GlobalException(ErrorCode.PAGE_NOT_FOUND));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Transactional
  public FAQItem updateFAQItem(Long faqItemId, FAQItem faqItemRequest) {
    return faqItemRepository.findById(faqItemId)
            .map(faqItem -> {
              faqItem.setTitle(faqItemRequest.getTitle());
              faqItem.setDescription(faqItemRequest.getDescription());
              faqItem.setComment(faqItemRequest.getComment());
              return faqItemRepository.save(faqItem);
            })
            .orElseThrow(() -> new GlobalException(ErrorCode.PAGE_NOT_FOUND));
  }

  @PreAuthorize("hasRole('ADMIN')")
  @Transactional
  public void deleteFAQItem(Long faqId) {
    faqItemRepository.findById(faqId)
            .ifPresent(faqItemRepository::delete);
  }

}
