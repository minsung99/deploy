import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios'; // Axios를 추가로 import
import './login.css';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            // 로그인 API 호출
            const resUser = await axios.get(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users/${email}/${password}`);
            //const resChatRooms = await axios.get(`/chatrooms/login/${resUser.data.id}`);

            delete resUser.data.profileimg;
            // 성공 처리
            console.log('로그인 성공:', resUser.data);

            // 사용자 정보 저장 (예: accessToken, userId 등)
            sessionStorage.setItem('accessToken', resUser.data.accessToken);
            sessionStorage.setItem('user', JSON.stringify(resUser.data)); // 사용자 이름 등 추가 정보 저장 가능
            //sessionStorage.setItem('chatrooms', JSON.stringify(resChatRooms.data));

            // 로그인 성공 시 swipe 페이지로 이동
            navigate('/swipe');
        } catch (error) {
            // 실패 처리
            console.error('로그인 실패:', error);
            alert('로그인에 실패했습니다. 이메일과 비밀번호를 확인하세요.');
        }
    };


    return (
        <div className="login-container">
            <h2 className="login-title">로그인</h2>
            <p className="login-subtitle">밥친구를 만들 준비가 되셨나요?</p>
            <form onSubmit={handleSubmit} className="login-form">
                <div className="login-input-container">
                    <label className="login-icon">👤</label>
                    <input
                        type="text"
                        placeholder="이메일을 입력하세요"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="login-input"
                    />
                </div>
                <div className="login-input-container">
                    <label className="login-icon">🔒</label>
                    <input
                        type="password"
                        placeholder="비밀번호를 입력하세요"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="login-input"
                    />
                </div>
                <button type="submit" className="login-button">로그인</button>
            </form>
            <button
                onClick={() => console.log('Forgot Password 클릭')}
                className="forgot-password"
            >
                비밀번호를 잊으셨나요?
            </button>
            <p className="login-footer">
                밥친구가 처음이세요? <span className="sign-up" onClick={() => navigate('/register')}>회원 가입</span>
            </p>
        </div>
    );
}

export default Login;
