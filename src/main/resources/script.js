import http from 'k6/http';
import { sleep, check } from 'k6';

// 옵션: 유저 수를 점진적으로 증가시켜 테스트
export let options = {
    stages: [
        { duration: '15s', target: 10 },  // 15초 동안 10명의 유저로 증가
        { duration: '30s', target: 20 },   // 30초 동안 20명의 유저로 증가
        { duration: '15s', target: 30 },   // 15초 동안 30명의 유저로 증가
        { duration: '15s', target: 0 },    // 15초 동안 유저 수를 0으로 감소
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95%의 요청이 500ms 이하의 응답 시간을 가져야 함
    },
};

export default function () {
    // 메시지 조회 API 호출
    let res = http.get('http://host.docker.internal:8080/chat?chatRoomId=30&size=30&messageId=70290');

    // 응답 상태 확인
    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    let messages;
    try {
        messages = res.json().messages; // 응답에서 messages 추출
    } catch (e) {
        console.error('Failed to parse JSON:', e);
        return; // JSON 파싱 실패 시 함수 종료
    }

    // 메시지가 있는지 확

    // 평균 1~5초 간 대기 후 다음 요청
    sleep(Math.random() * (5 - 1) + 1);
}
