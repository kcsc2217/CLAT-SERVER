import http from 'k6/http';
import { sleep, check } from 'k6';

// 옵션: 유저 수를 점진적으로 증가시켜 테스트
export let options = {
    stages: [
        { duration: '30s', target: 10 },  // 30초 동안 10명의 유저로 증가
        { duration: '1m', target: 20 },   // 1분 동안 20명의 유저로 증가
        { duration: '1m', target: 30 },   // 1분 동안 30명의 유저로 증가
        { duration: '30s', target: 0 },   // 30초 동안 유저 수를 0으로 감소
    ],
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95%의 요청이 500ms 이하의 응답 시간을 가져야 함
    },
};

export default function () {
    // 메시지 조회 API 호출
    let res = http.get('http://host.docker.internal:8080/chatRoom/2');

    // 응답 상태 확인
    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    // 평균 3초간 대기 후 다음 요청 (1~5초 사이 랜덤 대기)
    sleep(Math.random() * (5 - 1) + 1);
}
