import { check, sleep } from 'k6';
import ws from 'k6/ws';

export default function () {
    const url = 'ws://localhost:8080/ws/569/o53wdghv/websocket'; // 서버 주소
    const res = ws.connect(url, {}, (socket) => {
        console.log('WebSocket에 연결됨');

        // 구독할 주제
        socket.send(JSON.stringify({
            'command': 'SUBSCRIBE',
            'destination': '/sub/chat/1', // 구독할 엔드포인트, 채팅방 ID로 교체
            'id': 'sub-0' // 고유한 구독 ID
        }));

        // 서버로 메시지 전송
        socket.send(JSON.stringify({
            'command': 'SEND',
            'destination': '/pub/chat/message', // 메시지를 보낼 엔드포인트
            'body': JSON.stringify({
                chatRoomId: 1, // 채팅방 ID
                message: '안녕하세요, 세상!' // 보낼 메시지
            })
        }));

        // 메시지를 기다림
        socket.on('message', (message) => {
            console.log(`수신한 메시지: ${message}`);
        });

        // 일정 시간 동안 연결 유지
        sleep(10);
        socket.close();
    });

    check(res, {
        '상태가 101이다': (r) => r && r.status === 101, // WebSocket 연결 성공
    });
}
