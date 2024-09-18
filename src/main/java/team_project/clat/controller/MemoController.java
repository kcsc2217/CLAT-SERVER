package team_project.clat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team_project.clat.domain.Message;
import team_project.clat.dto.MemoResponseDTO;
import team_project.clat.service.MessageService;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MessageService messageService;

    @GetMapping("/{messageId}")
    public MemoResponseDTO memo(@PathVariable Long messageId) {
        Message message = messageService.findByWithMemo(messageId);

        return new MemoResponseDTO(message);
    }
}
