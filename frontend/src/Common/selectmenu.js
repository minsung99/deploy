import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './selectmenu.css';

function SelectMenu({ isPopupOpen, onClose, foodPreferences, handleFoodSelection }) {
    const [foodData, setFoodData] = useState({});

    // 음식 데이터 가져오기
    useEffect(() => {
        const fetchFoodData = async () => {
            try {
                const [menuResponse, foodResponse] = await Promise.all([
                    axios.get('https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/menus'),
                    axios.get('https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/food'),
                ]);

                const menuMap = menuResponse.data.reduce((acc, menu) => {
                    acc[menu.id] = menu.name;
                    return acc;
                }, {});

                const groupedFoodData = foodResponse.data.reduce((acc, food) => {
                    const categoryName = menuMap[food.menu.id] || `카테고리 ${food.menu.id}`;
                    if (!acc[categoryName]) {
                        acc[categoryName] = [];
                    }
                    acc[categoryName].push(food);
                    return acc;
                }, {});

                setFoodData(groupedFoodData);
            } catch (error) {
                console.error('데이터 가져오기 실패:', error);
                alert('음식 및 메뉴 데이터를 불러오는 중 오류가 발생했습니다.');
            }
        };

        if (isPopupOpen) {
            fetchFoodData();
        }
    }, [isPopupOpen]);

    if (!isPopupOpen) return null;

    return (
        <div className="popup-overlay">
            <div className="popup-content">
                <h3>선호 음식을 선택하세요 (최대 3개)</h3>
                {Object.entries(foodData).map(([categoryName, foods]) => (
                    <div key={categoryName}>
                        <h4 className="category-title">{categoryName}</h4>
                        {foods.map((food) => (
                            <button
                                key={food.id}
                                className={`option-button ${foodPreferences.find((item) => item.id === food.id) ? 'selected' : ''}`}
                                onClick={() => handleFoodSelection(food)}
                            >
                                {food.name}
                            </button>
                        ))}
                    </div>
                ))}
                <button onClick={onClose} className="confirm-button">
                    확인
                </button>
            </div>
        </div>
    );
}

export default SelectMenu;
