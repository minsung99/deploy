import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";

import "./openchat.css";
import Footer from "../Common/footer";
import Header from "../Common/header";

const OpenChat = () => {
  const navigate = useNavigate();

  const [searchTerm, setSearchTerm] = useState("");
  const [chatRooms, setChatRooms] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isJoinModalOpen, setIsJoinModalOpen] = useState(false);
  const [newRoom, setNewRoom] = useState({ name: "", limitednum: 2, state: "모집 중" });
  const [userId, setUserId] = useState(null);

  const storedUser = JSON.parse(sessionStorage.getItem('user'));

  useEffect(() => {
    // 로그인 정보가 없으면 로그인창으로 쫓겨남
    if (!storedUser || !storedUser.id) {
      alert("로그인이 필요합니다. 로그인 페이지로 이동합니다.");
      navigate("/login");
      return;
    }

    setUserId(storedUser.id);
    const fetchChatRooms = async () => {
      try {
        const response = await axios.get("https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatrooms");
        const mappedData = response.data.map((room) => ({
          id: room.id,
          title: room.name,
          state: room.state,
          members: room.limitednum,
          currentParticipants: 0,
          contains: false,
        })).filter((data) => data.state == "모집 중");
        console.log(mappedData)
        setChatRooms(mappedData);

        await fetchParticipants(response.data.map((room) => room.id));
        setIsLoading(false);
      } catch (error) {
        console.error("채팅방 데이터를 불러오는 중 오류가 발생했습니다.", error);
        setChatRooms([]);
        setIsLoading(false);
      }
    };

    fetchChatRooms();
  }, [navigate]);

  // 방 현재 인원 확인
  const fetchParticipants = async (roomIds) => {
    try {
      const response = await axios.get("https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatparts");
      const containsMe = {};
      const participantCounts = {};

      response.data.forEach((entry) => {
        const roomId = entry.chatroom.id;

        if (storedUser.id == entry.user.id) {
          containsMe[roomId] = true;
        }

        if (!participantCounts[roomId]) {
          participantCounts[roomId] = 0;
        }
        participantCounts[roomId] += 1;
      });

      setChatRooms((prevRooms) =>
        prevRooms.map((room) =>
          roomIds.includes(room.id)
            ? {
              ...room,
              currentParticipants: participantCounts[room.id] || 0,
              state:
                participantCounts[room.id] === room.members
                  ? "모집 마감"
                  : room.state,
              contains: containsMe[room.id],
            }
            : room
        )
      );
    } catch (error) {
      console.error("참여 인원 데이터를 불러오는 중 오류가 발생했습니다.", error);
    }
  };

  const handleRoomClick = (room) => {
    setSelectedRoom(room);
    setIsJoinModalOpen(true);
  };

  const handleJoinRoom = async () => {
    if (!userId) {
      alert("로그인된 사용자만 참여할 수 있습니다.");
      return;
    }

    try {
      const responseData = await axios.post(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatparts?userid=${userId}&roomid=${selectedRoom.id}`);
      console.log(responseData.data);
      setIsJoinModalOpen(false);

      sessionStorage.setItem('enterflag', "JOIN");
      navigate(`/chatroom`, { state: { room: selectedRoom } });
    } catch (error) {
      console.error("채팅방에 참여하는 중 오류가 발생했습니다.", error);
    }
  };

  const handleCreateRoom = async () => {
    try {
      // 방 생성
      const response = await axios.post("https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatrooms", newRoom);
      const createdRoom = response.data;

      // 생성된 방에 참가
      await axios.post(`https://port-0-backend-m3s7orv656142558.sel4.cloudtype.app/chatparts?userid=${userId}&roomid=${createdRoom.id}`);

      // 새로 생성된 방 추가
      setChatRooms([...chatRooms, { id: createdRoom.id, ...newRoom }]);
      setNewRoom({ name: "", state: "모집 중", limitednum: 1 });
      setIsCreateModalOpen(false);

      // 참가 후 자동으로 해당 방으로 이동
      //setSelectedRoom({ id: createdRoom.id, title: createdRoom.name });

      sessionStorage.setItem('enterflag', "JOIN");
      navigate(`/chatroom`, { state: { room: createdRoom } });
    } catch (error) {
      console.error("방 생성 중 오류가 발생했습니다.", error);
    }
  };

  const filteredRooms = chatRooms.filter((room) =>
    room.title?.includes(searchTerm)
  );

  return (
    <>
      <div className="wrapper">
        <Header />
        <div className="container">
          <div className="search-bar">
            <input
              type="text"
              placeholder="검색어를 입력하세요"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button className="creatroom-button" onClick={() => setIsCreateModalOpen(true)}>방 만들기</button>
          </div>
          <div className="chat-list">
            {isLoading ? (
              <div class="is-loading">
                <span>로딩 중...</span>
              </div>
            ) : filteredRooms.length > 0 ? (
              filteredRooms.map((room) => (
                <div
                  key={room.id}
                  className={!room.contains ? "chat-room" : "contains-chat-room"}
                  onClick={() => handleRoomClick(room)}
                >
                  <div className="room-info">
                    <div>
                      <h3>{room.title}</h3>
                      <p>상태: {room.state}</p>
                      <span>현재 인원: {room.currentParticipants || 0} / {room.members}명</span>
                    </div>
                    {room.contains && (
                      <div className="status-badge">참여 중</div>
                    )}
                  </div>
                </div>
              ))
            ) : (
              <p>검색 결과가 없습니다.</p>
            )}
          </div>
        </div>

        {isJoinModalOpen && selectedRoom && (
          <div className="modal">
            <div className="modal-content">
              <h2 className="modal-title">{selectedRoom.title}</h2>
              <p>상태: {selectedRoom.state}</p>
              <p>정원: {selectedRoom.currentParticipants || 0} / {selectedRoom.members}명</p>
              <div className="button-group">
                <button className="modal-button join-button" onClick={handleJoinRoom}>참가</button>
                <button className="modal-button close-button" onClick={() => setIsJoinModalOpen(false)}>닫기</button>
              </div>
            </div>
          </div>
        )}

        {isCreateModalOpen && (
          <div className="modal">
            <div className="modal-content">
              <h2 className="modal-title">새 방 만들기</h2>
              <div className="input-group">
                <label className="input-label">방 이름</label>
                <input
                  className="input-field"
                  type="text"
                  placeholder="방 이름을 입력하세요"
                  value={newRoom.name}
                  onChange={(e) =>
                    setNewRoom({ ...newRoom, name: e.target.value })
                  }
                />
              </div>
              <div className="input-group">
                <label className="input-label">정원</label>
                <input
                  className="input-field"
                  type="number"
                  placeholder="정원"
                  value={newRoom.limitednum}
                  onChange={(e) =>
                    setNewRoom({ ...newRoom, limitednum: parseInt(e.target.value) })
                  }
                />
              </div>
              <div className="button-group">
                <button className="modal-button create-button" onClick={handleCreateRoom}>생성</button>
                <button className="modal-button cancel-button" onClick={() => setIsCreateModalOpen(false)}>취소</button>
              </div>

            </div>
          </div>
        )}



      </div>
      <Footer />
    </>
  );
};

export default OpenChat;
