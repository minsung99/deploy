import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import TinderCard from 'react-tinder-card';
import axios from 'axios';
import confetti from 'canvas-confetti';
import './swipe.css'; // CSS 파일
import '../common.css';
import Footer from '../Common/footer';
import Header from '../Common/header';

const db = [
    { name: '햄버거사랑단', url: process.env.PUBLIC_URL + '/hamburger.jpg' },
    { name: '밥친구구함', url: process.env.PUBLIC_URL + '/pizza.jpg' },
    { name: '호로록', url: process.env.PUBLIC_URL + '/ramen.jpg' },
    { name: '샌디치냠', url: process.env.PUBLIC_URL + '/sandwich.jpg' },
    { name: '엽떡먹장', url: process.env.PUBLIC_URL + '/tteokbokki.jpg' },
];

function Swipe() {
    const navigate = useNavigate();

    const [characters, setCharacters] = useState([]);
    const storedUser = JSON.parse(sessionStorage.getItem('user'));

    useEffect(() => {
        getUsers();
    }, []);

    const getUsers = async () => {
        const users = await axios.get(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users`);
        const filteredUsers = users.data.filter((user) => user.id != storedUser.id);

        setCharacters(filteredUsers);
    };

    const swiped = async (direction, character) => {
        console.log('Removing: ' + character.name);

        // 오른쪽으로 스와이프 했을 때 채팅룸 생성
        if (direction === 'right') {
            try {
                if (!storedUser || !storedUser.id) {
                    alert('로그인이 필요합니다.');
                    return;
                }

                const userId = storedUser.id;

                // 채팅룸 생성 API 호출
                const newRoom = {
                    name: `${character.name}과의 채팅방`,
                    state: "",
                    limitednum: 2,
                };

                const response = await axios.post('https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatrooms', newRoom);
                const createdRoom = response.data;

                // 생성된 채팅룸에 참가
                await axios.post(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatparts?userid=${userId}&roomid=${createdRoom.id}`);
                await axios.post(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatparts?userid=${character.id}&roomid=${createdRoom.id}`);

                // 성공적으로 생성된 경우 알림 및 효과
                confetti({
                    particleCount: 200,
                    spread: 100,
                    origin: { x: 0.5, y: 0.6 }, // 화면 중앙에서 터짐
                    colors: ['#ff4e50', '#ff944e', '#fffdb0'], // 밝은 색상
                });
                alert(`채팅방 '${createdRoom.name}'이 생성되었습니다.`);

                sessionStorage.setItem('enterflag', "JOIN");
                navigate(`/chatroom`, { state: { room: createdRoom } });
            } catch (error) {
                console.error('채팅룸 생성 중 오류 발생:', error);
                alert('채팅룸 생성에 실패했습니다.');
            }
        }

        // 스와이프한 캐릭터 제거
        setCharacters((prev) => prev.filter((prevCharacter) => prevCharacter.id !== character.id));
    };

    const outOfFrame = (character) => {
        console.log(character.name + ' left the screen!');
    };

    return (
        <div className="swipe-container">
            <Header />
            {/* Title 문구 */}
            <h1 className="title">Swipe!</h1>
            <h2 className="subtitle">and find a friend</h2>

            <div className="card-container">
                {characters.map((character) => (
                    <TinderCard
                        className="swipe"
                        key={character.name}
                        onSwipe={(dir) => swiped(dir, character)}
                        onCardLeftScreen={() => outOfFrame(character)}
                    >
                        <div
                            style={{ backgroundImage: `url(${character.profileimg != null ?
                                character.profileimg
                                : process.env.PUBLIC_URL + `/default_user.jpg`})` }}
                            className="swipe-card"
                        >
                            <h3>{character.name}</h3>
                        </div>
                    </TinderCard>
                ))}
            </div>

            {/* Footer */}
            <Footer />
        </div>
    );
}

export default Swipe;
