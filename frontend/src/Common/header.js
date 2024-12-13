import React from 'react';
import { Link, useNavigate } from 'react-router-dom'; // useNavigate 추가
import './header.css';

function Header() {
    const navigate = useNavigate();

    const handleProfileClick = () => {
        const accessToken = sessionStorage.getItem('accessToken');
        if (accessToken) {
            // 로그인 상태면 MyPage로 이동
            navigate('/mypage');
        } else {
            // 비로그인 상태면 Login으로 이동
            navigate('/login');
        }
    };

    return (
        <header className="header">
            {/* 로고 */}
            <div className="header-logo">
                <Link to="/">
                    <img src={process.env.PUBLIC_URL + '/babchingu_logo.png'} alt="Logo" />
                </Link>
            </div>

            {/* 사람 모양 이모티콘 */}
            <div className="header-icon" onClick={handleProfileClick}>
                <img src={process.env.PUBLIC_URL + '/profile.png'} alt="Profile" />
            </div>
        </header>
    );
}

export default Header;
