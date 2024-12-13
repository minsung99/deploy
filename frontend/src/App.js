import React, { useEffect } from 'react';

import './App.css';
import { useNavigate } from 'react-router-dom';
import Footer from './Common/footer';
import Header from './Common/header';
import axios from 'axios';

const App = () => {
  const navigate = useNavigate();

  const storedUser = JSON.parse(sessionStorage.getItem('user'));
  useEffect(() => {

    // 로그인 정보가 없으면 로그인창으로 쫓겨남
    if (!storedUser || !storedUser.id) {
      navigate("/login");
    } else {
      navigate("/swipe");
    }
  });

  // GET API호출 예시
  async function getUser(params) {
      try {
        // GET 요청은 params에 실어 보냄
        const response = await axios.get('https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users', {
          params: {
            id: 1
          }
        });
        console.log(response);
      } catch (e) {
        // 실패 시 처리
        console.error(e);
      }
    }

  // POST API호출 예시
  async function postUser(params) {
      try {
        // POST 요청은 body에 실어 보냄
        let result = await axios.post('https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/users', {
          name: 'Fred',
          email: 'test@test.com',
          password: '123',
          food1: {
            id: 1
          }
        });
      } catch (e) {
        // 실패 시 처리
        console.error(e);
      }
    }

  const navigateToChatRoom = () => {
    navigate("/chatroom");
  };

  return (
    <div className="app">
      <Header />
      <main>
      </main>
      <Footer />
    </div>
  );
}

export default App;