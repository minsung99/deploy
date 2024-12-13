import React, { useState } from 'react';
import './register.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import SelectMenu from '../Common/selectmenu';

function Register() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [name, setName] = useState('');
    const [foodPreferences, setFoodPreferences] = useState([]);
    const [profileImg, setProfileImg] = useState();
    const [isPopupOpen, setIsPopupOpen] = useState(false);
    const navigate = useNavigate();

    const handleFoodSelection = (food) => {
        if (foodPreferences.find((item) => item.id === food.id)) {
            setFoodPreferences(foodPreferences.filter((item) => item.id !== food.id));
        } else if (foodPreferences.length < 3) {
            setFoodPreferences([...foodPreferences, food]);
        } else {
            alert('최대 3개의 음식을 선택할 수 있습니다.');
        }
    };

    const handleProfileImageChange = async (e) => {
        const file = e.target.files[0];

        if (!file) return;

        const base64 = await toBase64(file);

        const body = {
            requests: [
                {
                    image: {
                        content: base64.split(',')[1], // 이미지의 Base64 데이터만 추출
                    },
                    features: [
                        {
                            type: "FACE_DETECTION", // 얼굴 감지 요청
                        },
                    ],},
            ],};

        const detectedData = await fetch(
            `https://vision.googleapis.com/v1/images:annotate`,
            {
                method: "POST",
                headers: new Headers({
                    Authorization: "Bearer ya29.a0ARW5m77_E0Zx2qsvvWFVmVsdh62Ux1HzvekQUw86ZzxlNYoINz8LfU4od-i2LIH98z-TsNZmzxYDpg6Sc6dI48lrm79BA5utzpJIFsGDNQtkDWPNAgB9fYyamVWnwzIIY7K70AmX7wmfiRSMpw-Pg_XvN1JUD57DzvT6w038bAaCgYKAToSAQ8SFQHGX2MiLjs9Kkr_RxzfVNgCHV7qFw0177",
                    "x-goog-user-project": "analytical-poet-444309-q0"
                }),
                body: JSON.stringify(body),
            },
        )
        .then(res => res.json())
        .catch(() => false);

        if (detectedData?.responses?.[0]?.faceAnnotations?.length > 0) {
            alert("사람 얼굴은 업로드할 수 없습니다. 음식 사진을 올려주세요.");
        } else {
            setProfileImg(base64);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        if (foodPreferences.length === 0) {
            alert('최소 1개의 음식을 선택해야 합니다.');
            return;
        }

        try {
            const requestBody = {
                name,
                email,
                password,
                state: '활동 중',
                profileimg: profileImg,
                food1: foodPreferences[0] ? { id: foodPreferences[0].id } : null,
                food2: foodPreferences[1] ? { id: foodPreferences[1].id } : null,
                food3: foodPreferences[2] ? { id: foodPreferences[2].id } : null,
                rating: 0.0
            };

            console.log('전송 데이터:', requestBody);

            const response = await axios.post('https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users', requestBody, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            console.log('회원가입 성공:', response.data);

            // response body에서 이미지 데이터를 처리
            if (response.data.profileImage) {
                console.log('서버에서 받은 프로필 이미지 URL:', response.data.profileImage);
            }

            navigate('/login');
        } catch (error) {
            console.error('회원가입 실패:', error);
            alert('회원가입 실패: 서버 오류');
        }
    };

    const toBase64 = (file) => new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = (e) => {
            resolve(reader.result);
            console.log(e);
        }
        reader.onerror = reject;

        return reader.result;
    });

    return (
        <div className="register-container">
            <h2 className="register-title">회원가입</h2>
            <p className="register-subtitle">밥친구와 함께할 준비가 되셨나요?</p>
            <form onSubmit={handleSubmit} className="register-form">
                <div className="register-input-container">
                    <span className="register-icon">📧</span>
                    <input
                        type="email"
                        className="register-input"
                        placeholder="이메일을 입력하세요"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="register-input-container">
                    <span className="register-icon">🔒</span>
                    <input
                        type="password"
                        className="register-input"
                        placeholder="비밀번호를 입력하세요"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="register-input-container">
                    <span className="register-icon">🔒</span>
                    <input
                        type="password"
                        className="register-input"
                        placeholder="비밀번호를 다시 입력하세요"
                        value={confirmPassword}
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        required
                    />
                </div>
                <div className="register-input-container">
                    <span className="register-icon">👤</span>
                    <input
                        type="text"
                        className="register-input"
                        placeholder="닉네임을 입력하세요"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div className="register-input-container select-menu-input">
                    <span className="register-icon">🍴</span>
                    <input
                        type="text"
                        placeholder="선호 음식을 선택하세요"
                        value={foodPreferences.map((item) => item.name).join(', ')}
                        onClick={() => setIsPopupOpen(true)}
                        readOnly
                    />
                </div>

                <div className="register-input-container">
                    <img id="profileImg"
                        className='register-profileimg'
                        src={profileImg != null ?
                            profileImg
                            : process.env.PUBLIC_URL + `/default_user.jpg`}></img>
                    <label className="register-img-icon">📷</label>
                    <input
                        type="file"
                        className="register-input"
                        accept="image/*"
                        onChange={handleProfileImageChange}
                    />
                </div>
                <button type="submit" className="register-button">
                    회원가입
                </button>
            </form>
            {/* SelectMenu 팝업 추가 */}
            <SelectMenu
                isPopupOpen={isPopupOpen}
                onClose={() => setIsPopupOpen(false)}
                foodPreferences={foodPreferences}
                handleFoodSelection={handleFoodSelection}
            />
        </div>
    );
}

export default Register;
