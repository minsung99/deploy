import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';

import './rating.css';
import userEvent from '@testing-library/user-event';

function Rating() {
    const [rating, setRating] = useState(5);
    const [feedback, setFeedback] = useState('');
    const navigate = useNavigate();

    // navigate를 통해 입장했을 때
    const location = useLocation();
    const [roomId, setRoomId] = useState(location?.state?.roomid);
    const [opponent, setOpponent] = useState();

    console.log(roomId);

    useEffect(() => {
        getOpponent();
    }, []);

    // 채팅 내역 조회하고 불러오기
    const getOpponent = async () => {
        const response = await axios.get(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users/chatroom/${roomId}`);

        console.log(response.data[0]);
        setOpponent(response.data[0]);
    };

    const handleStarClick = (index) => {
        setRating(index);
        setFeedback(''); // 새로운 별 선택 시 입력 초기화
    };

    const handleSubmit = async () => {
        opponent.ratingqty += 1;
        opponent.rating = (opponent.rating + rating) / opponent.ratingqty;

        console.log(opponent.ratingqty);

        // 업데이트 API 호출
        await axios.patch(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users/${opponent.id}`, opponent);

        console.log(`${opponent.name}님에게 준 점수: ${rating}점`);
        console.log(`피드백: ${feedback}`);

        // alert("aaa");
        //window.location.replace('/chatroom');
        navigate('/chatroom'); // /swipe로 이동
    };

    return (
        <div className="rating-container">
            <div className="rating-box">
                <h2>식사는 어떠셨나요?</h2>
                <div className="stars">
                    {[...Array(5)].map((_, index) => {
                        const starIndex = index + 1;
                        return (
                            <span
                                key={index}
                                className={`star ${rating >= starIndex ? 'filled' : ''}`}
                                onClick={() => handleStarClick(starIndex)}
                            >
                                ★
                            </span>
                        );
                    })}
                </div>
                {rating > 0 && (
                    <div className="feedback-container">
                        <p>
                            {rating <= 3
                                ? '어떤 점이 밥친구로서 부족한가요?'
                                : '어떤 점이 밥친구로서 만족스러웠나요?'}
                        </p>
                        <textarea
                            placeholder="피드백을 작성해주세요"
                            value={feedback}
                            onChange={(e) => setFeedback(e.target.value)}
                        />
                    </div>
                )}
                <button className="submit-button" onClick={handleSubmit}>
                    확인
                </button>
            </div>
        </div>
    );
}

export default Rating;
