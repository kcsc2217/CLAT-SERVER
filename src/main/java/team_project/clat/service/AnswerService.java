package team_project.clat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team_project.clat.domain.Answer;
import team_project.clat.domain.Enum.UserType;
import team_project.clat.domain.Member;
import team_project.clat.domain.Message;
import team_project.clat.exception.DuplicateException;
import team_project.clat.exception.NotFoundException;
import team_project.clat.exception.ProfessorAuthorizationException;
import team_project.clat.repository.AnswerRepository;
import team_project.clat.repository.MemberRepository;
import team_project.clat.repository.MessageRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Answer saveAnswer(String answer, String username, Long messageId) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new NotFoundException("해당 메세지는 찾을수 없습니다"));

        Member member = memberRepository.findByUsername(username);

        validationUserType(member); // 유저가 교수인지 검증 로직

        validationMessage(message); // 유저의 메세지에 답글이 존재하는지

        Answer awAnswer = new Answer(answer, member, message);

        Answer save = answerRepository.save(awAnswer);

        return save;

    }

    private static void validationMessage(Message message) {
        if(message.getAnswer() != null) {
            throw new DuplicateException("해당 답글 이미 존재합니다.");
        }
    }

    private static void validationUserType(Member member) {
        if(member.getUserType() != UserType.PROFESSOR){
            throw new ProfessorAuthorizationException("교수만 가능합니다");
        }
    }
}
